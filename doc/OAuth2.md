# OAuth2 

## 定义 

> The OAuth 2.0 authorization framework enables a third-party application to obtain limited access to an HTTP service, either on behalf of a resource owner by orchestrating an approval interaction between the resource owner and the HTTP service, or by allowing the third-party application to obtain access on its own behalf.
>
> OAuth 2.0 授权框架允许第三方应用程序获得对HTTP服务的有限访问权限，可以通过在资源所有者和HTTP服务之间协调审批交互来代表资源所有者，也可以通过允许第三方应用程序代表自己获得访问权限。

OAuth2 是一个授权框架 [RFC](https://tools.ietf.org/html/rfc6749)。它定义了四种授权模式。



###  Client Authentication

A client MAY use the "client_id" request parameter to identify itself when sending requests to the token endpoint. In the "authorization_code" "grant_type" request to the token endpoint, an unauthenticated client MUST send its "client_id" to prevent itself from inadvertently accepting a code intended for a client with a different "client_id". This protects the client from substitution of the authentication code. (It provides no additional security for the protected resource.)

当向令牌端点发送请求时，客户端可以使用“client_id”请求参数来标识自己。在向令牌终结点发出的“授权代码”“授予类型”请求中，未经身份验证的客户端必须发送其“客户端标识”，以防止自己无意中接受一个针对具有不同“客户端标识”的客户端的代码。这样可以防止客户端替换身份验证码。（它不为受保护的资源提供额外的安全性。）



## AuthenticationCode Grant

### Access Token Request

```http
POST /token HTTP/1.1
Host: server.example.com
Authorization: Basic czZCaGRSa3F0MzpnWDFmQmF0M2JW
Content-Type: application/x-www-form-urlencoded

grant_type=authorization_code&code=SplxlOBeZQQYbYS6WxSbIA
&redirect_uri=https%3A%2F%2Fclient%2Eexample%2Ecom%2Fcb
```



## ResourceOwnerPasswordCredentials Grant

### Access Token Request

> czZCaGRSa3F0MzpnWDFmQmF0M2JW 为 client_id 与 client_secret 的 base64 编码。

```http
POST /token HTTP/1.1
Host: server.example.com
Authorization: Basic czZCaGRSa3F0MzpnWDFmQmF0M2JW 
Content-Type: application/x-www-form-urlencoded 

grant_type=password&username=johndoe&password=A3ddj3w
```

