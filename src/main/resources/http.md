# Http详解

## 一、简介

HTTP是Hyper Text Transfer Protocol（超文本传输协议）的缩写，是一个属于应用层的面向对象的无状态协议。

默认HTTP的端口号为80，HTTPS的端口号为443。

HTTP协议永远都是客户端发起请求，服务器回送响应。这样就限制了使用HTTP协议，无法实现在客户端没有发起请求的时候，服务器将消息推送给客户端。HTTP协议是一个无状态的协议，同一个客户端的这次请求和上次请求是没有对应关系。

## 二、特点

- HTTP是无连接：无连接的含义是限制每次连接只处理一个请求。服务器处理完客户的请求，并收到客户的应答后，即断开连接。采用这种方式可以节省传输时间。
- HTTP是媒体独立的：这意味着，只要客户端和服务器知道如何处理的数据内容，任何类型的数据都可以通过HTTP发送。客户端以及服务器指定使用适合的MIME-type内容类型。
- HTTP是无状态：HTTP协议是无状态协议。无状态是指协议对于事务处理没有记忆能力。缺少状态意味着如果后续处理需要前面的信息，则它必须重传，这样可能导致每次连接传送的数据量增大。另一方面，在服务器不需要先前信息时它的应答就较快。

## 三、工作流程

一次HTTP操作称为一个事务，其工作过程可分为四步：

1. 首先客户机与服务器需要建立连接。只要单击某个超级链接，HTTP的工作开始。

2. 建立连接后，客户机发送一个请求给服务器，请求方式的格式为：统一资源标识符（URL）、协议版本号，后边是MIME信息包括请求修饰符、客户机信息和可能的内容。

3. 服务器接到请求后，给予相应的响应信息，其格式为一个状态行，包括信息的协议版本号、一个成功或错误的代码，后边是MIME信息包括服务器信息、实体信息和可能的内容。

4. 客户端接收服务器所返回的信息通过浏览器显示在用户的显示屏上，然后客户机与服务器断开连接。

   如果在以上过程中的某一步出现错误，那么产生错误的信息将返回到客户端，有显示屏输出。对于用户来说，这些过程是由HTTP自己完成的，用户只要用鼠标点击，等待信息显示就可以了。

## 四、请求方式

1. GET：请求指定的页面信息，并返回实体主体。
2. HEAD：类似于get请求，只不过返回的响应中没有具体的内容，用于获取报头
3. POST：向指定资源提交数据进行处理请求（例如提交表单或者上传文件）。数据被包含在请求体中。POST请求可能会导致新的资源的建立和/或已有资源的修改。
4. PUT：从客户端向服务器传送的数据取代指定的文档的内容。
5. DELETE：请求服务器删除指定的页面。
6. OPTIONS：允许客户端查看服务器的性能。
7. TRACE：回显服务器收到的请求，主要用于测试或诊断。

GET与POST方法有以下区别：

1. 在客户端，Get方式在通过URL提交数据，数据在URL中可以看到；POST方式，数据放置在HTML HEADER内提交。

2. GET方式提交的数据最多只能有1024字节，而POST则没有此限制。
3. 安全性问题。正如在（1）中提到，使用 Get 的时候，参数会显示在地址栏上，而 Post 不会。所以，如果这些数据是中文数据而且是非敏感数据，那么使用 get；如果用户输入的数据不是中文字符而且包含敏感数据，那么还是使用 post为好。
4. 安全的和幂等的。所谓安全的意味着该操作用于获取信息而非修改信息。幂等的意味着对同一 URL 的多个请求应该返回同样的结果。完整的定义并不像看起来那样严格。换句话说，GET 请求一般不应产生副作用。从根本上讲，其目标是当用户打开一个链接时，她可以确信从自身的角度来看没有改变资源。比如，新闻站点的头版不断更新。虽然第二次请求会返回不同的一批新闻，该操作仍然被认为是安全的和幂等的，因为它总是返回当前的新闻。反之亦然。POST 请求就不那么轻松了。POST 表示可能改变服务器上的资源的请求。仍然以新闻站点为例，读者对文章的注解应该通过 POST 请求实现，因为在注解提交之后站点已经不同了（比方说文章下面出现一条注解）。

## 五、消息结构

客户端请求消息包括：

1. 请求行

   POST http://192.168.2.217:8080/index.jsp HTTP/1.1

   方法 [空格] 请求URI [空格] 版本号 [回车换行]

2. 请求报文头

3. 空行

4. 消息体

服务器响应消息包括：

1. 状态行

   版本号 [空格] 状态码 [空格] 原因 [回车换行]

2. 消息报文头

3. 空行

4. 消息体

## 六、请求头

HTTP最常见的请求头如下：

- Accept：浏览器可接受的MIME类型；
- Accept-Charset：浏览器可接受的字符集；
- Accept-Encoding：浏览器能够进行解码的数据编码方式，比如gzip。Servlet能够向支持gzip的浏览器返回经gzip编码的HTML页面。许多情形下这可以减少5到10倍的下载时间；
- Accept-Language：浏览器所希望的语言种类，当服务器能够提供一种以上的语言版本时要用到；
- Authorization：授权信息，通常出现在对服务器发送的WWW-Authenticate头的应答中；
- Connection：表示是否需要持久连接。如果Servlet看到这里的值为“Keep-Alive”，或者看到请求使用的是HTTP 1.1（HTTP 1.1默认进行持久连接），它就可以利用持久连接的优点，当页面包含多个元素时（例如Applet，图片），显著地减少下载所需要的时间。要实现这一点，Servlet需要在应答中发送一个Content-Length头，最简单的实现方法是：先把内容写入ByteArrayOutputStream，然后在正式写出内容之前计算它的大小；
- Content-Length：表示请求消息正文的长度；
-  Cookie：这是最重要的请求头信息之一；
- Host：初始URL中的主机和端口；
- If-Modified-Since：只有当所请求的内容在指定的日期之后又经过修改才返回它，否则返回304“Not Modified”应答；
- Pragma：指定“no-cache”值表示服务器必须返回一个刷新后的文档，即使它是代理服务器而且已经有了页面的本地拷贝；
- Referer：包含一个URL，用户从该URL代表的页面出发访问当前请求的页面。
- User-Agent：浏览器类型，如果Servlet返回的内容与浏览器类型有关则该值非常有用；

## 七、响应头

- Allow：服务器支持哪些请求方法（如GET、POST等）。
- Content-Encoding：文档的编码（Encode）方法。只有在解码之后才可以得到Content-Type头指定的内容类型。利用gzip压缩文档能够显著地减少HTML文档的下载时间。Java的GZIPOutputStream可以很方便地进行gzip压缩，但只有Unix上的Netscape和Windows上的IE 4、IE 5才支持它。因此，Servlet应该通过查看Accept-Encoding头（即request.getHeader("Accept-Encoding")）检查浏览器是否支持gzip，为支持gzip的浏览器返回经gzip压缩的HTML页面，为其他浏览器返回普通页面；
- Content-Length：表示内容长度。只有当浏览器使用持久HTTP连接时才需要这个数据。如果你想要利用持久连接的优势，可以把输出文档写入ByteArrayOutputStram，完成后查看其大小，然后把该值放入Content-Length头，最后通过byteArrayStream.writeTo(response.getOutputStream()发送内容；
- Content-Type： 表示后面的文档属于什么MIME类型。Servlet默认为text/plain，但通常需要显式地指定为text/html。由于经常要设置Content-Type，因此HttpServletResponse提供了一个专用的方法setContentTyep。 可在web.xml文件中配置扩展名和MIME类型的对应关系；
- Date：当前的GMT时间。你可以用setDateHeader来设置这个头以避免转换时间格式的麻烦；
- Expires：指明应该在什么时候认为文档已经过期，从而不再缓存它。
- Last-Modified：文档的最后改动时间。客户可以通过If-Modified-Since请求头提供一个日期，该请求将被视为一个条件GET，只有改动时间迟于指定时间的文档才会返回，否则返回一个304（Not Modified）状态。Last-Modified也可用setDateHeader方法来设置；
-  Location：表示客户应当到哪里去提取文档。Location通常不是直接设置的，而是通过HttpServletResponse的sendRedirect方法，该方法同时设置状态代码为302；
- Refresh：表示浏览器应该在多少时间之后刷新文档，以秒计。
- Server：服务器名字。Servlet一般不设置这个值，而是由Web服务器自己设置。
- Set-Cookie：设置和页面关联的Cookie。Servlet不应使用response.setHeader("Set-Cookie", ...)，而是应使用HttpServletResponse提供的专用方法addCookie。参见下文有关Cookie设置的讨论。

## 八、状态码

- 200 OK：请求成功。一般用于GET与POST请求
- 201 Created：已创建。成功请求并创建了新的资源
- 202 Accepted：已接受。已经接受请求，但未处理完成
- 203 Non-Authoritative Information：非授权信息。请求成功。但返回的meta信息不在原始的服务器，而是一个副本
- 204 No Content：无内容。服务器成功处理，但未返回内容。在未更新网页的情况下，可确保浏览器继续显示当前文档
- 205 Reset Content：重置内容。服务器处理成功，用户终端（例如：浏览器）应重置文档视图。可通过此返回码清除浏览器的表单域
- 206 Partial Content：部分内容。服务器成功处理了部分GET请求
- 300 Multiple Choices：多种选择。请求的资源可包括多个位置，相应可返回一个资源特征与地址的列表用于用户终端（例如：浏览器）选择
- 301 Moved Permanently：永久移动。请求的资源已被永久的移动到新URI，返回信息会包括新的URI，浏览器会自动定向到新URI。今后任何新的请求都应使用新的URI代替
- 302 Found：临时移动。与301类似。但资源只是临时被移动。客户端应继续使用原有URI
- 303 See Other：查看其它地址。与301类似。使用GET和POST请求查看
- 304 Not Modified：未修改。所请求的资源未修改，服务器返回此状态码时，不会返回任何资源。客户端通常会缓存访问过的资源，通过提供一个头信息指出客户端希望只返回在指定日期之后修改的资源
- 305 Use Proxy：使用代理。所请求的资源必须通过代理访问
- 307 Temporary Redirect：临时重定向。与302类似。使用GET请求重定向
- 400 Bad Request：客户端请求的语法错误，服务器无法理解
- 401 Unauthorized：请求要求用户的身份认证
- 402 Payment Required：保留，将来使用
- 403 Forbidden：服务器理解请求客户端的请求，但是拒绝执行此请求
- 404 Not Found：服务器无法根据客户端的请求找到资源（网页）。通过此代码，网站设计人员可设置"您所请求的资源无法找到"的个性页面
- 405 Method Not Allowed：客户端请求中的方法被禁止
- 406 Not Acceptable：服务器无法根据客户端请求的内容特性完成请求
- 407 Proxy Authentication Required：请求要求代理的身份认证，与401类似，但请求者应当使用代理进行授权
- 408 Request Time-out：服务器等待客户端发送的请求时间过长，超时
- 409 Conflict：服务器完成客户端的PUT请求是可能返回此代码，服务器处理请求时发生了冲突
- 410 Gone：客户端请求的资源已经不存在。410不同于404，如果资源以前有现在被永久删除了可使用410代码，网站设计人员可通过301代码指定资源的新位置
- 411 Length Required：服务器无法处理客户端发送的不带Content-Length的请求信息
- 412 Precondition Failed：客户端请求信息的先决条件错误
- 413 Request Entity Too Large：由于请求的实体过大，服务器无法处理，因此拒绝请求。为防止客户端的连续请求，服务器可能会关闭连接。如果只是服务器暂时无法处理，则会包含一个Retry-After的响应信息
- 414 Request-URI Too Large：请求的URI过长（URI通常为网址），服务器无法处理
- 415 Unsupported Media Type：服务器无法处理请求附带的媒体格式
- 416 Requested range not satisfiable：客户端请求的范围无效
- 417 Expectation Failed：服务器无法满足Expect的请求头信息
- 500 Internal Server Error：服务器内部错误，无法完成请求
- 501 Not Implemented：服务器不支持请求的功能，无法完成请求
- 502 Bad Gateway：充当网关或代理的服务器，从远端服务器接收到了一个无效的请求
- 503 Service Unavailable：由于超载或系统维护，服务器暂时的无法处理客户端的请求。延时的长度可包含在服务器的Retry-After头信息中
- 504 Gateway Time-out：充当网关或代理的服务器，未及时从远端服务器获取请求
- 505 HTTP Version not supported：服务器不支持请求的HTTP协议的版本，无法完成处理

## 九、cookie和session

Cookie和Session都为了用来保存状态信息，都是保存[客户端](http://nonfu.me/tag/%E5%AE%A2%E6%88%B7%E7%AB%AF)状态的机制，它们都是为了解决[HTTP](http://nonfu.me/tag/http)无状态的问题而所做的努力。

### 9.1 两者比较

Cookie和Session有以下明显的不同点：

1. Cookie将状态保存在客户端，Session将状态保存在[服务器](http://nonfu.me/tag/%E6%9C%8D%E5%8A%A1%E5%99%A8)端；
2. Cookies是服务器在本地机器上存储的小段文本并随每一个请求发送至同一个服务器。Cookie最早在RFC2109中实现，后续RFC2965做了增强。网络服务器用HTTP头向客户端发送[cookie](http://nonfu.me/tag/cookie)s，在客户终端，浏览器解析这些cookies并将它们保存为一个本地文件，它会自动将同一服务器的任何请求缚上这些cookies。Session并没有在HTTP的协议中定义；
3. Session是针对每一个用户的，变量的值保存在服务器上，用一个sessionID来区分是哪个用户session变量,这个值是通过用户的浏览器在访问的时候返回给服务器，当客户禁用cookie时，这个值也可能设置为由get来返回给服务器；
4. 就安全性来说：当你访问一个使用session 的站点，同时在自己机子上建立一个cookie，建议在服务器端的SESSION机制更安全些。因为它不会任意读取客户存储的信息。

### 9.2 Session机制

Session机制是一种服务器端的机制，服务器使用一种类似于散列表的结构（也可能就是使用散列表）来保存信息。

当程序需要为某个客户端的请求创建一个session的时候，服务器首先检查这个客户端的请求里是否已包含了一个session标识 - 称为 session id，如果已包含一个session id则说明以前已经为此客户端创建过session，服务器就按照session id把这个 session检索出来使用（如果检索不到，可能会新建一个），如果客户端请求不包含session id，则为此客户端创建一个session并且生成一个与此session相关联的session id，session id的值应该是一个既不会重复，又不容易被找到规律以仿造的字符串，这个 session id将被在本次响应中返回给客户端保存。

### 9.3 session实现方式

1. 使用Cookie来实现

   服务器给每个Session分配一个唯一的SessionID，并通过Cookie发送给客户端。

   当客户端发起新的请求的时候，将在Cookie头中携带这个SessionID。这样服务器能够找到这个客户端对应的Session。

2. 使用URL回显来实现

   URL回写是指服务器在发送给浏览器页面的所有链接中都携带SessionID的参数，这样客户端点击任何一个链接都会把SessionID带回服务器。

   如果直接在浏览器输入服务端资源的url来请求该资源，那么Session是匹配不到的。

   Tomcat对Session的实现，是一开始同时使用Cookie和URL回写机制，如果发现客户端支持Cookie，就继续使用Cookie，停止使用URL回写。如果发现Cookie被禁用，就一直使用URL回写。jsp开发处理到Session的时候，对页面中的链接记得使用response.encodeURL() 。

### 9.4 在J2EE项目中Session失效的几种情况

1. Session超时

   session在指定时间内失效，例如30分钟，若在30分钟内没有操作，则Session会失效。可以在web.xml中设置。

2. 使用session.invalidate()明确的去掉Session。

### 9.5 Cookie的流程

服务器在响应消息中用Set-Cookie头将Cookie的内容回送给客户端，客户端在新的请求中将相同的内容携带在Cookie头中发送给服务器。从而实现会话的保持。

## 十、断点续传和多线程下载的原理

**断点续传：**

HTTP协议的GET方法，支持只请求某个资源的某一部分

206 Partial Content 部分内容响应

Range 请求的资源范围

Content-Range 响应的资源范围

在连接断开重连时，客户端只请求该资源未下载的部分，而不是重新请求整个资源，来实现断点续传。

分块请求资源实例：

Eg1：Range: bytes=306302- ：请求这个资源从306302个字节到末尾的部分；

Eg2：Content-Range: bytes 306302-604047/604048：响应中指示携带的是该资源的第306302-604047的字节，该资源共604048个字节；

客户端通过并发的请求相同资源的不同片段，来实现对某个资源的并发分块下载。

**多线程下载：**

下载工具开启多个发出HTTP请求的线程；

每个http请求只请求资源文件的一部分：Content-Range: bytes 20000-40000/47000；

合并每个线程下载的文件。

## 十一、https原理

请求流程：

![https请求流程](https://ws1.sinaimg.cn/large/006tNc79ly1fml19yxxj4j30a90bcdgd.jpg)

https通信的优点：

1. 客户端产生的密钥只有客户端和服务器端能得到；
2. 加密的数据只有客户端和服务器端才能得到明文；
3. 客户端到服务端的通信是安全的。