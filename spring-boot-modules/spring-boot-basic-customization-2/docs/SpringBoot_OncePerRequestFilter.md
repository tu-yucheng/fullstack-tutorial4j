## 1. 概述

在本教程中，我们介绍OncePerRequestFilter，这是Spring中的一种特殊类型的过滤器。我们将通过一个简单的示例了解它解决了什么问题并了解如何使用它。

## 2. 什么是OncePerRequestFilter？

过滤器可以在Servlet执行之前或之后调用，**当一个请求被分派给一个Servlet时，RequestDispatcher可以将它转发给另一个Servlet。另一个Servlet可能也有相同的过滤器。在这种情况下，同一个过滤器会被多次调用**。

但是，我们可能希望确保每个请求只调用一次特定的过滤器。一个常见的用例是使用Spring Security时。当请求通过过滤器链时，我们可能希望某些身份验证操作只针对该请求发生一次。

在这种情况下，我们可以继承OncePerRequestFilter。**Spring保证OncePerRequestFilter对于给定的请求只执行一次**。

## 3. 对同步请求使用OncePerRequestFilter

让我们举个例子来了解如何使用这个过滤器。我们定义一个继承OncePerRequestFilter的类AuthenticationFilter，并重写doFilterInternal()方法：

```java
@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String usrName = request.getHeader("userName");
        logger.info("Successfully authenticated user  " + usrName);
        filterChain.doFilter(request, response);
    }
}
```

由于OncePerRequestFilter仅支持HTTP请求，因此我们无需像在实现Filter接口时那样强制转换请求和响应对象。

## 4. 对异步请求使用OncePerRequestFilter

对于异步请求，默认情况下不会应用OncePerRequestFilter。我们需要重写shouldNotFilterAsyncDispatch()和shouldNotFilterErrorDispatch()方法来支持这一点。

有时，我们只需要在初始请求线程中应用过滤器，而不是在异步调度中创建的其他线程中应用。其他时候，我们可能需要在每个额外线程中至少调用一次过滤器。在这种情况下，我们需要重写shouldNotFilterAsyncDispatch()方法。

如果shouldNotFilterAsyncDispatch()方法返回true，则不会为后续的异步调度调用过滤器。但是，如果它返回false，将为每个异步调度调用过滤器，每个线程只调用一次。

类似地，**我们重写shouldNotFilterErrorDispatch()方法并返回true或false，具体取决于我们是否要过滤错误调度**：

```java
@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String usrName = request.getHeader("userName");
        logger.info("Successfully authenticated user  " + usrName);
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilterAsyncDispatch() {
        return false;
    }

    @Override
    protected boolean shouldNotFilterErrorDispatch() {
        return false;
    }
}
```

## 5. 有条件地跳过请求

通过重写shouldNotFilter()方法，我们可以有条件地仅对某些特定请求应用过滤器并跳过其他请求：

```java
@Override
protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
    return Boolean.TRUE.equals(request.getAttribute(SHOULD_NOT_FILTER));
}
```

## 6. 快速示例

让我们看一个简单的示例来了解OncePerRequestFilter的行为。首先，我们定义一个使用Spring的DeferredResult异步处理请求的控制器：

```java
@Controller
public class HelloController implements AutoCloseable {

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    private Logger logger = LoggerFactory.getLogger(HelloController.class);

    @GetMapping(path = "/greeting")
    public DeferredResult<String> hello(HttpServletResponse response) throws Exception {
        DeferredResult<String> deferredResult = new DeferredResult<>();
        executorService.submit(() -> perform(deferredResult));
        return deferredResult;
    }

    private void perform(DeferredResult<String> dr) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        dr.setResult("OK");
    }

    @Override
    public void close() throws Exception {
        executorService.shutdownNow();
    }
}
```

当异步处理请求时，两个线程都经过同一个过滤器链。因此，过滤器被调用了两次：第一次是容器线程处理请求时，第二次是在异步调度程序完成后。当异步处理完成后，响应将返回给客户端。

现在，让我们定义一个实现OncePerRequestFilter的过滤器：

```java
@Component
public class MyOncePerRequestFilter extends OncePerRequestFilter {
    private final Logger logger = LoggerFactory.getLogger(MyOncePerRequestFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
            throws ServletException, IOException {
        logger.info("Inside Once Per Request Filter originated by request {}", request.getRequestURI());
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilterAsyncDispatch() {
        return true;
    }
}
```

在上面的代码中，我们使shouldNotFilterAsyncDispatch()方法返回true。这是为了演示我们的过滤器只为容器线程调用一次，而不会为后续的异步线程调用。

通过运行应用程序主类并访问控制器端点，我们可以证明这一点：

```shell
curl -X GET http://localhost:8080/greeting 
```

控制台的输出为：

```text
... c.t.t.o.MyOncePerRequestFilter: Inside Once Per Request Filter originated by request /greeting
```

如果我们希望请求和异步调度都调用我们的过滤器，我们只需要重写shouldNotFilterAsyncDispatch()以返回false：

```java
@Override
protected boolean shouldNotFilterAsyncDispatch() {
    return false;
}
```

控制台的输出为：

```text
... [nio-8080-exec-3] c.t.t.o.MyOncePerRequestFilter: Inside Once Per Request Filter originated by request /greeting
... [nio-8080-exec-4] c.t.t.o.MyOncePerRequestFilter: Inside Once Per Request Filter originated by request /greeting
```

从上面的输出中我们可以看到，我们的过滤器被调用了两次 - 首先是容器线程，然后是另一个线程。

## 7. 总结

在本文中，我们通过一些实际示例了解了OncePerRequestFilter，它解决了哪些问题，以及如何实现它。