# SpringSecurity OAuth2 扩展登录方式

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



