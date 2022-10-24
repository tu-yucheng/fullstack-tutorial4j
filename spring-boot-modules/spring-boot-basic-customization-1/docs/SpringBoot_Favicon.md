## 1. 概述

favicon是浏览器中显示的一个小网站图标，通常位于地址栏旁边。

在本文中，我们介绍自定义Spring Boot应用程序的favicon的各种方法。

## 2. 覆盖Favicon

覆盖Spring Boot应用程序默认favicon的最简单方法是将新favicon放在resources目录中：

```text
src/main/resources/favicon.ico
```

favicon文件的名称应该为”favicon.ico“。

我们还可以将该文件放在项目resources目录中的static目录中：

```text
src/main/resources/static/favicon.ico
```

Spring Boot在启动时扫描位于resources目录下的favicon.ico文件，然后扫面resources/static目录。

## 3. 使用自定义的位置

除了将favicon放在resources根目录下，我们可能希望将其与应用程序的其他图片一起保存。

我们可以通过在application.properties中禁用默认favicon来实现这一点：

```properties
spring.mvc.favicon.enabled=false
```

**值得注意的是，从Spring Boot 2.2开始，这个配置属性已被弃用**。
此外，Spring Boot不再提供默认的favicon，因为这个图标可以归类为信息泄漏。

然后实现我们的处理程序：

```java

@Configuration
public class FaviconConfiguration {

    @Bean
    public SimpleUrlHandlerMapping myFaviconHandlerMapping() {
        SimpleUrlHandlerMapping mapping = new SimpleUrlHandlerMapping();
        mapping.setOrder(Integer.MIN_VALUE);
        mapping.setUrlMap(Collections.singletonMap("/favicon.ico", faviconRequestHandler()));
        return mapping;
    }

    @Bean
    protected ResourceHttpRequestHandler faviconRequestHandler() {
        ResourceHttpRequestHandler requestHandler = new ResourceHttpRequestHandler();
        ClassPathResource classPathResource = new ClassPathResource("cn/tuyucheng/taketoday/images/");
        List<Resource> locations = List.of(classPathResource);
        requestHandler.setLocations(locations);
        return requestHandler;
    }
}
```

我们对SimpleUrlHandlerMapping设置setOrder(Integer.MIN_VALUE)，以确保它有最高优先级。

通过这种配置，我们可以将favicon文件存储在应用程序结构中的任何位置。

## 4. 优雅的禁用Favicon

如果我们的应用程序不需要任何favicon，我们可以通过设置属性spring.mvc.favicon.enabled为false禁用它。
但是当浏览器查找时，会出现404错误。

我们可以通过自定义FaviconController来避免这种情况，该控制器返回空响应：

```java

@Controller
static class FaviconController {

    @GetMapping("favicon.ico")
    @ResponseBody
    void returnNoFavicon() {
    }
}
```

## 5. 总结

在本文中，我们演示了如何覆盖Spring Boot应用程序的默认favicon，为favicon使用自定义位置，以及如果我们不想使用favicon时，如何避免404错误。