## 1. 概述

本教程提供了通过Maven和Gradle定义Spring Boot应用程序入口点的不同方法。

Spring Boot应用程序的主类是一个包含启动Spring ApplicationContext的public static void main()方法的类。
默认情况下，如果没有显式指定主类，Spring会在编译时在类路径中搜索一个，如果一个都没有或找到多个，则启动失败。

与传统的Java应用程序不同，本教程中讨论的主类不会作为生成的JAR或WAR文件的META-INF/MANIFEST.MF中的Main-Class元数据属性出现。

Spring Boot期望工件的Main-Class元数据属性设置为org.springframework.boot.loader.JarLauncher(或WarLauncher)，
这意味着将我们的主类直接传递给java命令行不会正确启动我们的Spring Boot应用程序。

清单示例如下所示：

