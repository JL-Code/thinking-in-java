# HTTP APIs「问题详情」- RFC 7807

## 摘要

本文档定义了一个  **「问题详情」**，作为在 HTTP 响应中携带机器可读的错误细节的方式，以达到避免为 HTTP API 定义新的错误响应格式的目的。

## 备忘状态

这是一个互联网标准跟踪文档。

##  1. 介绍

本规范定义了简单的 JSON **[RFC7159]** 和 XML **[W3C.REC-xml-20081126]** 文档格式以实现这个目标。 它们被设计成可以被 HTTP API 重用，它们可以根据自己的需要识别不同的**问题类型**。

因此，API 客户可以被告知高等级的错误类别（使用状态代码）和问题的细粒度细节（使用这些格式之一）。

例如，考虑一个表明客户的账户没有足够的信用的响应。 403 Forbidden 状态码可能被认为是最合适的使用方式，因为它将告知 HTTP 通用软件（如客户端库、缓存和代理）响应的一般语义。

然而，这并没有给 API 客户端足够的信息，说明为什么请求被禁止，适用的账户余额，或者如何纠正问题。 如果这些细节以机器可读的格式包含在响应体中，客户端就可以对其进行适当的处理；例如，触发向账户转移更多的信用。

本规范通过用一个 URI **[RFC3986]**来识别特定类型的问题（例如 "没有信用"）来实现这一目的；HTTP API 可以通过提名在其控制下的新URI 或重用现有的 URI 来实现。

此外，「问题详情」还可以包含其他信息，如确定问题发生的具体原因的 URI，  （例如：给 "上周四 Joe 没有足够信用额度" 这个概念提供一个有效的标识符），这对支持或取证有用。

「问题详情」的数据模型是一个 JSON **[RFC7159]** 对象；当格式化为 JSON 文档时，它使用 `application/problem+json` 媒体类型。 附录A定义了如何用等效的 XML 格式来表达它们，它使用 `application/problem+xml` 媒体类型。

请注意，「问题详情」并不是在 HTTP 中表达问题细节的唯一方式；例如，如果响应仍然是一个资源的表示，通常最好是考虑用该应用程序的格式来描述相关细节。 同样，在很多情况下，有一个合适的 HTTP 状态码，就不需要额外的细节了。

相反，本规范的目的是为那些需要错误格式的应用程序定义常见的错误格式，这样他们就不需要定义自己的错误格式，或者更糟糕的是，诱使他们重新定义现有 HTTP 状态码的语义。 即使应用程序选择不使用它来传达错误，审查其设计也有助于指导在现有格式中传达错误时面临的设计决策。

>  Even if an application  chooses not to use it to convey errors, reviewing its design can help guide the design decisions faced when conveying errors in an existing format.

## 2. 要求

本文档中的关键字“必须”，“不得”，“必须”，“应”，“应不”，“应”，“应不”，“推荐”，“可以”和“可选”是如**[RFC2119]** 中所述进行解释。

> The key words "MUST", "MUST NOT", "REQUIRED", "SHALL", "SHALL NOT",   "SHOULD", "SHOULD NOT", "RECOMMENDED", "MAY", and "OPTIONAL" in this   document are to be interpreted as described in [RFC2119].

## 3.  「问题详情」 JSON 对象

「问题详情」的规范模型是 JSON [RFC7159] 对象。 当序列化为 JSON文档时，该格式使用 `application/problem+json` 媒体类型。

例如，一个带有 JSON 格式的「问题详情」 HTTP 响应：

```http
HTTP/1.1 403 Forbidden
Content-Type: application/problem+json
Content-Language: en

{
  "type": "https://example.com/probs/out-of-credit",
  "title": "You do not have enough credit.",
  "detail": "Your current balance is 30, but that costs 50.",
  "instance": "/account/12345/msgs/abc",
  "balance": 30,
  "accounts": ["/account/12345","/account/67890"]
}
```

这个信用不足的问题（由 `type` 字段中的 `URI` 标识），其中 `title` 指出返回 403 的原因，`instance` 提供发生具体问题情况的参考，并添加两个扩展；`balance` 表示账户的余额，`accounts` 提供了可以为账户充值的链接。这个扩展能力在表达特定问题时允许表达更多的问题信息。

例如：

```http
HTTP/1.1 400 Bad Request
Content-Type: application/problem+json
Content-Language: en

{
  "type": "https://example.net/validation-error",
  "title": "Your request parameters didn't validate.",
  "invalid-params": [ {
      "name": "age",
      "reason": "must be a positive integer"
    },
    {
      "name": "color",
      "reason": "must be 'green', 'red' or 'blue'"
    }
  ]
}
```

> <font color=red>注意：</font>这个必须要求每个子问题必须足够相似 （ invalid-params 必须是 badRequest 的问题子集），才能使用相同的 HTTP 状态码。如果没有，可以使用207（Multi-Status）**[RFC4918]** 代码封装多个状态消息。
>
> 207 Multi-Status：由WebDAV(RFC 2518)扩展的状态码，代表之后的消息体将是一个XML消息，并且可能依照之前子请求数量的不同，包含一系列独立的响应代码。

### 3.1  「问题详情」 对象成员

「问题详情」对象可以具有以下成员：

* `type` (string) 一个 `URI` 地址 **[RFC3986]** 用于描述**问题类型**信息。该规范鼓励在取消引用（dereferenced）时为人类提供可阅读的文档（例如，使用 `HTML` **[W3C.REC-html5-20141028]**）。当这个成员不存在时，其值为设置为 `about:blank` 以表示不存在更多的问题类型信息。
* `title` (string) 该问题类型的简短摘要描述。「它不应该随着问题的发生而改变，本地化除外」。(例如：主动的使用内容协商; 参见 **[RFC7231]**, 3.4 章节)
* `status`(number) 这个是 HTTP 状态码 （**[RFC7231]** 第六章节）<span style="background:yellow">由发生该问题的原始服务器生成</span>。
* `detail` (string) 针对发生该问题的具体解释（说明为什么会发生这个问题）。
* `instance` (string)  确定发生具体问题的 URI 参考。 如果解除引用，它可能会产生也可能不会产生进一步的信息。

### 3.2 「问题详情」 扩展成员

**问题类型**定义可以扩展  「问题详情」 对象其他成员。

例如：我们上面的 “信用不足” 问题定义了两个问题扩展分别是 `balance` 和 `accounts` 来表达更多的问题信息。

使用「问题详情」的客户端必须忽略任何它们无法识别的此类扩展信息。

> <font color=red>注意：</font>因为扩展信息是跟**问题类型**聚合在一个上下文中的，所以如果不定义新的媒体类型，则无法定义新的标准成员。



## 4. 定义新的问题类型

当 `HTTP API` 需要通过定义一个响应来表示一个错误条件时，或许通过定义一个新**问题类型**来做更合适。

在做之前，了解它们（ 「问题详情」对象）的优点是非常重要的，以及其他更好的机制（what's better left to other mechanisms.）。

「问题详情」不是底层实现的调试工具；相反，它们是一种公开更多关于 HTTP 接口本身问题详细信息的方法。新**问题类型**的设计人员需要仔细考虑安全性考虑事项(在该规范的第5节)，特别是通过暴露错误消息来暴露内部实现，从而被人知道如何攻击（可能暴露具体的攻击路径）。

同样，真正普遍的问题（即条件可能适用于 Web 上任何资源）通常可以更好地表达为简单的状态代码。
例如，“禁止写访问” 问题可能不是必须的，因为响应 `PUT` 请求的 `403` 禁止状态码是不言自明的。

最后，你的应用程序可能有一种更合适的方式，以它已经定义的格式传递错误。 「问题详情」旨在避免建立新的  <font color=#D4AC0D>故障</font> 或 <font color=red>错误</font> 响应信息文档格式，而不是替换现有的特定于领域的格式。

也就是说，可以使用 `HTTP` 内容协商向现有 `HTTP API` 添加对 「问题详情」 的支持 (例如，使用 `Accept` 请求头来表明对该格式的偏好；参见 **[RFC7231]**，章节5.3.2)。

新的问题类型定义必须记录：

1. `type` URI (通常带有 ` http` 或 `https` )
2. `title` 一个简短并且准确的标题
3. 一个 HTTP 状态码

在适当的情况下，**问题类型**定义可以指定返回 `Retry-After` 响应头 (**[RFC7231]**，章节7.1.3) 。

问题的 `type URI` 应该解析为解释如何解决问题的 HTML [W3C.REC-html5-20141028] 文档。

**问题类型**定义可以在 「问题详情」 对象上指定其他成员。例如，一个扩展可以使用类型化的链接**[RFC5988]**到另一个可以被机器用来解决问题的资源。

如果定义了这些附加成员，则它们的名称应以字母开头（根据[RFC5234]，附录B.1），并且应包含字母、数字（[RFC5234]，附录B.1）和 "_"（以便可以以 JSON 以外的格式序列化），并且它们应该是三个或更长的字符。

### 4.1 示例

例如：如果您要将 HTTP API  发布到您的在线购物车中，您可能需要指出该用户没有信用（我们上面的示例），因此无法进行购买。

如果你已经有一个特定的应用格式能包含这些信息，你可以不采用  `Problem Details` 格式 。

但是，如果你不想使用在继续使用这种格式了，您可以考虑使用 `Problem Details` 格式 —— 如果你的 API 是基于 JSON 或 XML。

为此，您可以寻找一个已经定义好的适合您的类型 URI。如果有可用的，可以重用该URI。

如果没有可用的 URI，您可以创建并记录一个新的类型 URI(它应该在您的控制下，并随着时间的推移而稳定)、一个适当的标题和将与之一起使用的 HTTP 状态码，以及它的含义和应该如何处理它。

总而言之：`instance` URI 将始终标识发生的具体问题。另一方面，如果在其他地方已经提供了问题类型的适当描述，或者可以为新问题类型创建 URI，则可以重用 URI 类型。

### 4.2 保留问题类型

本规范将 `about:blank` 作为保留**问题类型**。

当 `about:blank`  URI **[RFC6694]** 用作一个**问题类型**时，表示问题除了 HTTP 状态码之外没有其他语义。

当 `about:blank` 被使用时，「问题详情」 对象的 `title`字段推荐与 HTTP 状态码短语相同。（例如：404 时 `title` 为 `Not Found` ，等等）

> <font color=red>请注意：</font>根据 `type` 字段的定义方式（第3.1节），该 `about:blank` URI 是该成员的默认值。因此，任何没有显式声明  `type` 字段的 「问题详情」 对象都隐式使用这个 URI。

## 5. 安全注意事项

在定义新的**问题类型**时，必须仔细审查所包含的内容细节。同样，当实际生成一个问题时—不管它是如何序列化的——所给出的细节也必须仔细检查。否则将出现泄露信息风险，这些泄露的信息可能被用来危害系统、对系统的访问或获取系统用户的隐私等。

<span style='background:yellow'>产生问题信息链接的人应避免通过 HTTP 接口暴露诸如 <code>Stack Dump</code> 等实现细节，因为这会暴露服务器实现及其数据等敏感细节。</span>

`status` 成员复制了来自 HTTP状态码中可用的信息，从而带来了两者之间出现分歧的可能性。。它们的相对优先级并不明确，因此不一致可能表明传输中介已经修改了传输中的 HTTP 状态码（例如，代理或缓存）。

> The "status" member duplicates the information available in the HTTP status code itself, thereby bringing the possibility of disagreement between the two.  Their relative precedence is not clear, since a disagreement might indicate that (for example) an intermediary has modified the HTTP status code in transit (e.g., by a proxy or cache).

因此，那些定义**问题类型**的人以及问题的生产者和消费者需要意识到，通用软件（如代理、负载平衡器、防火墙和病毒扫描器）不太可能知道或尊重该成员中传达的状态码

## 6. IANA 注意事项

该规范定义了两种新的互联网媒体类型 **[RFC6838]**。

### 6.1 application/problem+json

```
Type name:  application
Subtype name:  problem+json
Required parameters:  None
Optional parameters:  None; unrecognized parameters should be ignored
Encoding considerations:  Same as [RFC7159]
Security considerations:  see Section 5 of this document
Interoperability considerations:  None
Published specification:  RFC 7807 (this document)
Applications that use this media type:  HTTP
Fragment identifier considerations:  Same as for application/json
([RFC7159])
Additional information:
Deprecated alias names for this type:  n/a
Magic number(s):  n/a
File extension(s):  n/a
Macintosh file type code(s):  n/a
Person and email address to contact for further information:
Mark Nottingham <mnot@mnot.net>
Intended usage:  COMMON
Restrictions on usage:  None.
Author:  Mark Nottingham <mnot@mnot.net>
Change controller:  IESG
```

### 6.2 application/problem+xml

```
Type name:  application
Subtype name:  problem+xml
Required parameters:  None
Optional parameters:  None; unrecognized parameters should be ignored
Encoding considerations:  Same as [RFC7303]
Security considerations:  see Section 5 of this document
Interoperability considerations:  None
Published specification:  RFC 7807 (this document)
Applications that use this media type:  HTTP
Fragment identifier considerations:  Same as for application/xml (as
specified by Section 5 of [RFC7303])
Additional information:
Deprecated alias names for this type:  n/a
Magic number(s):  n/a
File extension(s):  n/a
Macintosh file type code(s):  n/a
Person and email address to contact for further information:
Mark Nottingham <mnot@mnot.net>
Intended usage:  COMMON
Restrictions on usage:  None.
Author:  Mark Nottingham <mnot@mnot.net>
Change controller:  IESG
```

## 7. 引用

### 7.1 规范引用

* [RFC2119]  Bradner, S., "Key words for use in RFCs to Indicate
  Requirement Levels", BCP 14, RFC 2119,
  DOI 10.17487/RFC2119, March 1997,
  <http://www.rfc-editor.org/info/rfc2119>.

* [RFC3986]  Berners-Lee, T., Fielding, R., and L. Masinter, "Uniform
  Resource Identifier (URI): Generic Syntax", STD 66,
  RFC 3986, DOI 10.17487/RFC3986, January 2005,
  <http://www.rfc-editor.org/info/rfc3986>.

* [RFC5234]  Crocker, D., Ed. and P. Overell, "Augmented BNF for Syntax
  Specifications: ABNF", STD 68, RFC 5234,
  DOI 10.17487/RFC5234, January 2008,
  <http://www.rfc-editor.org/info/rfc5234>.

* [RFC7159]  Bray, T., Ed., "The JavaScript Object Notation (JSON) Data
  Interchange Format", RFC 7159, DOI 10.17487/RFC7159, March
  2014, <http://www.rfc-editor.org/info/rfc7159>.

* [RFC7230]  Fielding, R., Ed. and J. Reschke, Ed., "Hypertext Transfer
  Protocol (HTTP/1.1): Message Syntax and Routing",
  RFC 7230, DOI 10.17487/RFC7230, June 2014,
  <http://www.rfc-editor.org/info/rfc7230>.

* [RFC7231]  Fielding, R., Ed. and J. Reschke, Ed., "Hypertext Transfer
  Protocol (HTTP/1.1): Semantics and Content", RFC 7231,
  DOI 10.17487/RFC7231, June 2014,
  <http://www.rfc-editor.org/info/rfc7231>.

* [W3C.REC-xml-20081126]
  Bray, T., Paoli, J., Sperberg-McQueen, M., Maler, E., and
  F. Yergeau, "Extensible Markup Language (XML) 1.0 (Fifth
  Edition)", W3C Recommendation REC-xml-20081126, November
  2008, <http://www.w3.org/TR/2008/REC-xml-20081126>.

### 7.2 信息参考

* [ISO-19757-2]
  International Organization for Standardization,
  "Information Technology --- Document Schema Definition
  Languages (DSDL) --- Part 2: Grammar-based Validation ---
  RELAX NG", ISO/IEC 19757-2, 2003.

* [RFC4918]  Dusseault, L., Ed., "HTTP Extensions for Web Distributed
  Authoring and Versioning (WebDAV)", RFC 4918,
  DOI 10.17487/RFC4918, June 2007,
  <http://www.rfc-editor.org/info/rfc4918>.

* [RFC5988]  Nottingham, M., "Web Linking", RFC 5988,
  DOI 10.17487/RFC5988, October 2010,
  <http://www.rfc-editor.org/info/rfc5988>.

* [RFC6694]  Moonesamy, S., Ed., "The "about" URI Scheme", RFC 6694,
  DOI 10.17487/RFC6694, August 2012,
  <http://www.rfc-editor.org/info/rfc6694>.

* [RFC6838]  Freed, N., Klensin, J., and T. Hansen, "Media Type
  Specifications and Registration Procedures", BCP 13,
  RFC 6838, DOI 10.17487/RFC6838, January 2013,
  <http://www.rfc-editor.org/info/rfc6838>.

* [RFC7303]  Thompson, H. and C. Lilley, "XML Media Types", RFC 7303,
  DOI 10.17487/RFC7303, July 2014,
  <http://www.rfc-editor.org/info/rfc7303>.

* [W3C.REC-html5-20141028]
  Hickson, I., Berjon, R., Faulkner, S., Leithead, T.,
  Navara, E., O'Connor, E., and S. Pfeiffer, "HTML5", W3C
  Recommendation REC-html5-20141028, October 2014,
  <http://www.w3.org/TR/2014/REC-html5-20141028>.
* [W3C.REC-rdfa-core-20130822]
  Adida, B., Birbeck, M., McCarron, S., and I. Herman, "RDFa
  Core 1.1 - Second Edition", W3C Recommendation
  REC-rdfa-core-20130822, August 2013,
  <http://www.w3.org/TR/2013/REC-rdfa-core-20130822>.

* [W3C.REC-xml-stylesheet-20101028]
  Clark, J., Pieters, S., and H. Thompson, "Associating
  Style Sheets with XML documents 1.0 (Second Edition)", W3C
  Recommendation REC-xml-stylesheet-20101028, October 2010,
  <http://www.w3.org/TR/2010/REC-xml-stylesheet-20101028>.