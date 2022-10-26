## 1. 概述

在本教程中，我们介绍如何在Spring Boot 2中替换EmbeddedServletContainerCustomizer和ConfigurableEmbeddedServletContainer。

这些类是Spring Boot早期版本的一部分，但从Spring Boot 2开始已被删除。当然，**这些功能仍然可以通过接口WebServerFactoryCustomizer和类ConfigurableServletWebServerFactory使用**。

## 2. Spring Boot 2之前

首先，让我们看看使用早期版本的类和接口：

```java
@Component
public class CustomContainer implements EmbeddedServletContainerCustomizer {

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        container.setPort(8080);
        container.setContextPath("");
    }
}
```

这里，我们自定义Servlet容器的端口和上下文路径。

实现此目的的另一种可能性是使用ConfigurableEmbeddedServletContainer的更具体子类，用于配置Tomcat等容器类型：

```java
@Component
public class CustomContainer implements EmbeddedServletContainerCustomizer {

    @Override
    public void customize(ConfigurableEmbeddedServletContainer container) {
        if (container instanceof TomcatEmbeddedServletContainerFactory) {
            TomcatEmbeddedServletContainerFactory tomcatContainer = (TomcatEmbeddedServletContainerFactory) container;
            tomcatContainer.setPort(8080);
            tomcatContainer.setContextPath("");
        }
    }
}
```

## 3. Spring Boot 2

**在Spring Boot 2中，EmbeddedServletContainerCustomizer接口被替换为WebServerFactoryCustomizer，而ConfigurableEmbeddedServletContainer类被替换为ConfigurableServletWebServerFactory**。

下面使用Spring Boot 2中的接口和类重写前面的例子：

```java
public class CustomContainer implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    public void customize(ConfigurableServletWebServerFactory factory) {
        factory.setPort(8080);
        factory.setContextPath("");
    }
}
```

对于第二个例子，在Spring Boot 2中使用TomcatServletWebServerFactory：

```java
@Component
public class CustomContainer implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

    @Override
    public void customize(TomcatServletWebServerFactory factory) {
        factory.setContextPath("");
        factory.setPort(8080);
    }
}
```

同样，我们将JettyServletWebServerFactory和UndertowServletWebServerFactory作为已删除的JettyEmbeddedServletContainerFactory和UndertowEmbeddedServletContainerFactory的替代品。

## 4. 总结

这篇简短的文章演示了如何解决我们在将Spring Boot应用程序升级到版本2.x时可能遇到的问题。