# SpringSecurity OAuth2 扩展额外的登录方式

## 登录方式

* 图片验证码登录
* 手机验证码登录
* 账号密码登录
* 微信扫码登录
* 钉钉扫码登录

## 集成要求

1. 不侵入 Spring Security OAuth2 的原有代码。
2. 对于不同的登录方式不扩展新的端点，使用 **/oauth/token** 可以适配所有的登录方式。
3. 可以对所有登录方式进行兼容，抽象一套模型只要简单的开发就可以集成登录。



## ClientCredentialsTokenEndpointFilter

> ClientCredentialsTokenEndpointFilter 拦截 /oauth/token 请求，提取 client_id、client_secret 构建 UsernamePasswordAuthenticationToken 进行客户端凭据验证，验证通过后才能进入 /oauth/token 进行下一步的令牌颁发流程。



## TokenEndpoint

```java
@RequestMapping(value = "/oauth/token", method=RequestMethod.POST)
public ResponseEntity<OAuth2AccessToken> postAccessToken(
      Principal principal, @RequestParam Map<String, String> parameters)
      throws HttpRequestMethodNotSupportedException {

   if (!(principal instanceof Authentication)) {
      throw new InsufficientAuthenticationException(
            "There is no client authentication. Try adding an appropriate authentication filter.");
   }
	
   // 从 principal 中提取 clientId 后通过 clientDetailsService.loadClientByClientId 加载客户端详细信息，用于进一步验证。
   String clientId = getClientId(principal);
   ClientDetails authenticatedClient = getClientDetailsService().loadClientByClientId(clientId);

   // 通过请求参数与认证后的客户端信息构建一个 tokenRequest
   TokenRequest tokenRequest = getOAuth2RequestFactory().createTokenRequest(parameters, authenticatedClient);

   // Only validate client details if a client is authenticated during this request.
   // Double check to make sure that the client ID is the same in the token request and authenticated client.
  //仅在此请求期间验证了客户端的情况下才验证客户端详细信息。
   //仔细检查以确保令牌请求和经过身份验证的客户端中的客户端ID相同。
   if (StringUtils.hasText(clientId) && !clientId.equals(tokenRequest.getClientId())) {
      throw new InvalidClientException("Given client ID does not match authenticated client");
   }

   // 验证令牌请求中请求的scope 是否与提供的客户端的 scope 匹配（request scope <= client scope）
   if (authenticatedClient != null) {
      oAuth2RequestValidator.validateScope(tokenRequest, authenticatedClient);
   }

   // 检查请求是否包含 grant type,没有grant type 则直接抛出异常。
   if (!StringUtils.hasText(tokenRequest.getGrantType())) {
      throw new InvalidRequestException("Missing grant type");
   }

  // 若 grant type 为 implicit （隐式模式）则抛出 not supported 异常
   if (tokenRequest.getGrantType().equals("implicit")) {
      throw new InvalidGrantException("Implicit grant type not supported from token endpoint");
   }

   if (isAuthCodeRequest(parameters) && !tokenRequest.getScope().isEmpty()) {
      // The scope was requested or determined during the authorization step
      logger.debug("Clearing scope of incoming token request");
      tokenRequest.setScope(Collections.<String>emptySet());
   } else if (isRefreshTokenRequest(parameters)) { // 判断是否为刷新令牌请求
      if (StringUtils.isEmpty(parameters.get("refresh_token"))) {
         throw new InvalidRequestException("refresh_token parameter not provided");
      }
      // A refresh token has its own default scopes, so we should ignore any added by the factory here.
      tokenRequest.setScope(OAuth2Utils.parseParameterList(parameters.get(OAuth2Utils.SCOPE)));
   }

   OAuth2AccessToken token = getTokenGranter().grant(tokenRequest.getGrantType(), tokenRequest);
   if (token == null) {
      throw new UnsupportedGrantTypeException("Unsupported grant type");
   }

   return getResponse(token);
}
```

**getOAuth2RequestFactory().createTokenRequest(parameters, authenticatedClient)** 

DefaultOAuth2RequestFactory 工厂的默认实现

```java
public TokenRequest createTokenRequest(Map<String, String> requestParameters, ClientDetails authenticatedClient) {

   String clientId = requestParameters.get(OAuth2Utils.CLIENT_ID);
   if (clientId == null) {
      // if the clientId wasn't passed in in the map, we add pull it from the authenticated client object
      clientId = authenticatedClient.getClientId();
   }
   else {
      // otherwise, make sure that they match
      if (!clientId.equals(authenticatedClient.getClientId())) {
         throw new InvalidClientException("Given client ID does not match authenticated client");
      }
   }
   String grantType = requestParameters.get(OAuth2Utils.GRANT_TYPE);

   Set<String> scopes = extractScopes(requestParameters, clientId);
   TokenRequest tokenRequest = new TokenRequest(requestParameters, clientId, scopes, grantType);

   return tokenRequest;
}
```





## ProviderManager

ProviderManager 类 authenticate 方法：

> result = provider.authenticate(authentication);

```java
public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		Class<? extends Authentication> toTest = authentication.getClass();
		AuthenticationException lastException = null;
		AuthenticationException parentException = null;
		Authentication result = null;
		Authentication parentResult = null;
		for (AuthenticationProvider provider : getProviders()) {
			if (!provider.supports(toTest)) {
				continue;
			}
			try {
				result = provider.authenticate(authentication);
        if (result != null) {
					copyDetails(authentication, result);
          // 跳出 providers 循环
					break;
				}
				省略...
			}
			catch (AccountStatusException | InternalAuthenticationServiceException e) {
				prepareException(e, authentication);
				// SEC-546: Avoid polling additional providers if auth failure is due to
				// invalid account status
				throw e;
			} catch (AuthenticationException e) {
				lastException = e;
			}
		}
      省略...
			return result;
		}
			省略...
	}
```

## CompositeTokenGranter

CompositeTokenGranter 类：

```java
public class CompositeTokenGranter implements TokenGranter {

   private final List<TokenGranter> tokenGranters;

   public CompositeTokenGranter(List<TokenGranter> tokenGranters) {
      this.tokenGranters = new ArrayList<TokenGranter>(tokenGranters);
   }
   
   @Override
   public OAuth2AccessToken grant(String grantType, TokenRequest tokenRequest) {
      for (TokenGranter granter : tokenGranters) {
         OAuth2AccessToken grant = granter.grant(grantType, tokenRequest);
         if (grant!=null) {
            return grant;
         }
      }
      return null;
   }
  
   public void addTokenGranter(TokenGranter tokenGranter) {
      if (tokenGranter == null) {
         throw new IllegalArgumentException("Token granter is null");
      }
      tokenGranters.add(tokenGranter);
   }

}
```



## AuthorizationServerEndpointsConfigurer

AuthorizationServerEndpointsConfigurer 类 **getDefaultTokenGranters** 方法：

```java
private List<TokenGranter> getDefaultTokenGranters() {
  ClientDetailsService clientDetails = clientDetailsService();
  AuthorizationServerTokenServices tokenServices = tokenServices();
  AuthorizationCodeServices authorizationCodeServices = authorizationCodeServices();
  OAuth2RequestFactory requestFactory = requestFactory();

  List<TokenGranter> tokenGranters = new ArrayList<TokenGranter>();
  tokenGranters.add(new AuthorizationCodeTokenGranter(tokenServices, authorizationCodeServices, clientDetails,
                                                      requestFactory));
  tokenGranters.add(new RefreshTokenGranter(tokenServices, clientDetails, requestFactory));
  ImplicitTokenGranter implicit = new ImplicitTokenGranter(tokenServices, clientDetails, requestFactory);
  tokenGranters.add(implicit);
  tokenGranters.add(new ClientCredentialsTokenGranter(tokenServices, clientDetails, requestFactory));
  if (authenticationManager != null) {
    tokenGranters.add(new ResourceOwnerPasswordTokenGranter(authenticationManager, tokenServices,
                                                            clientDetails, requestFactory));
  }
  return tokenGranters;
}
```

**AuthorizationServerEndpointsConfigurer$TokenGranter** 匿名内部类：

```java
private TokenGranter tokenGranter() {
  if (tokenGranter == null) {
    tokenGranter = new TokenGranter() {
      private CompositeTokenGranter delegate;

      @Override
      public OAuth2AccessToken grant(String grantType, TokenRequest tokenRequest) {
        if (delegate == null) {
          delegate = new CompositeTokenGranter(getDefaultTokenGranters());
        }
        return delegate.grant(grantType, tokenRequest);
      }
    };
  }
  return tokenGranter;
}
```



TokenRequest 对象：

![image-20200721150606149](/Users/codeme/IdeaProjects/java-example/doc/assets/tokenRequest.png)



## ResourceOwnerPasswordTokenGranter 

ResourceOwnerPasswordTokenGranter 类 getOAuth2Authentication 方法：

```java
@Override
	protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {

		Map<String, String> parameters = new LinkedHashMap<String, String>(tokenRequest.getRequestParameters());
		String username = parameters.get("username");
		String password = parameters.get("password");
		// Protect from downstream leaks of password
		parameters.remove("password");
		// UsernamePasswordAuthenticationToken 决定了具体认证 AbstractUserDetailsAuthenticationProvider 及其实现子类 DaoAuthenticationProvider 
		Authentication userAuth = new UsernamePasswordAuthenticationToken(username, password);
		((AbstractAuthenticationToken) userAuth).setDetails(parameters);
		try {
      // 调用 AbstractUserDetailsAuthenticationProvider 的实现类 DaoAuthenticationProvider 提供的认证方法
			userAuth = authenticationManager.authenticate(userAuth);
		}
		catch (AccountStatusException ase) {
			//covers expired, locked, disabled cases (mentioned in section 5.2, draft 31)
			throw new InvalidGrantException(ase.getMessage());
		}
		catch (BadCredentialsException e) {
			// If the username/password are wrong the spec says we should send 400/invalid grant
			throw new InvalidGrantException(e.getMessage());
		}
		catch (UsernameNotFoundException e) {
			// If the user is not found, report a generic error message
			throw new InvalidGrantException(e.getMessage());
		}
		if (userAuth == null || !userAuth.isAuthenticated()) {
			throw new InvalidGrantException("Could not authenticate user: " + username);
		}
		
		OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);		
		return new OAuth2Authentication(storedOAuth2Request, userAuth);
	}
```

## DaoAuthenticationProvider

DaoAuthenticationProvider 类 additionalAuthenticationChecks 方法：

获取到当前请求上下文中 **UsernamePasswordAuthenticationToken** 中的凭据信息：`String presentedPassword = authentication.getCredentials().toString();` ，然后与当前 **UserDetails** 中的凭据信息比对：`passwordEncoder.matches(presentedPassword, userDetails.getPassword())` 。

```java
protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		if (authentication.getCredentials() == null) {
			logger.debug("Authentication failed: no credentials provided");

			throw new BadCredentialsException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.badCredentials",
					"Bad credentials"));
		}

		String presentedPassword = authentication.getCredentials().toString();

		if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
			logger.debug("Authentication failed: password does not match stored value");

			throw new BadCredentialsException(messages.getMessage(
					"AbstractUserDetailsAuthenticationProvider.badCredentials",
					"Bad credentials"));
		}
	}
```





```java
@Override
public SysUserAuthentication authenticate(IntegrationAuthentication integrationAuthentication) {
	...
  //通过手机号码查询用户
  SysUserAuthentication sysUserAuthentication = this.sysUserClient.findUserByPhoneNumber(username);
  if (sysUserAuthentication != null) {
    //将密码设置为验证码
    sysUserAuthentication.setPassword(passwordEncoder.encode(password));
  }
  return sysUserAuthentication;
}
```



## 参考文档

* [Spring Security OAuth2 优雅的集成短信验证码登录以及第三方登录](https://segmentfault.com/a/1190000014371789)
* [谈谈短信验证码机制](https://www.jianshu.com/p/47a6ccf5c269)

