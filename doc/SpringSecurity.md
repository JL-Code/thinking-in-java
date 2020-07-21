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

CompositeTokenGranter 类 grant 方法：

```java
public OAuth2AccessToken grant(String grantType, TokenRequest tokenRequest) {
  for (TokenGranter granter : tokenGranters) {
    OAuth2AccessToken grant = granter.grant(grantType, tokenRequest);
    if (grant!=null) {
      return grant;
    }
  }
  return null;
}
```

## AuthorizationServerEndpointsConfigurer

AuthorizationServerEndpointsConfigurer 类 getDefaultTokenGranters 方法：

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

tokenGranter 方法：

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

		Authentication userAuth = new UsernamePasswordAuthenticationToken(username, password);
		((AbstractAuthenticationToken) userAuth).setDetails(parameters);
		try {
      // 调用认证供应商提供的认证方法
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