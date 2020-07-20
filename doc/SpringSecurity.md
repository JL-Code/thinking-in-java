# SpringSecurity

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



