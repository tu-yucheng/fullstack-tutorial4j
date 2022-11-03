## 1. 概述

本教程介绍Maven的编译插件，它是Maven构建工具的核心插件之一。有关其他核心插件的概述，请参阅[本文]()。

## 2. 插件Goals(目标)

**compiler插件用于编译Maven项目的源代码**，这个插件有两个goal，它们已经绑定到默认生命周期的特定阶段：

+ compile - 编译主要源文件
+ testCompile - 编译测试源文件

以下是pom中的编译插件：

```xml
<plugin>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.8.1</version>
    <configuration>
        ...
    </configuration>
</plugin>
```

## 3. 配置

**默认情况下，编译插件编译与Java 5兼容的源代码，并且生成的class文件也可以与Java 5一起使用，而不管使用的是什么JDK**。我们可以在configuration标签中修改这些设置：

```xml
<configuration>
    <source>1.8</source>
    <target>1.8</target>
    ...
</configuration>
```

为了方便起见，我们可以将Java版本设置为pom的属性：

```xml
<properties>
    <maven.compiler.source>1.8</maven.compiler.source>
    <maven.compiler.target>1.8</maven.compiler.target>
</properties>
```

有时我们想将参数传递给javac编译器，这就是compilerArgs参数派上用场的地方。例如，我们可以为编译插件指定以下配置来警告unchecked的操作：

```xml
<configuration>
    <compilerArgs>
        <arg>-Xlint:unchecked</arg>
    </compilerArgs>
</configuration>
```

当编译以下类时：

```java
public class Data {
    List<String> textList = new ArrayList();

    public void addText(String text) {
        textList.add(text);
    }

    public List getTextList() {
        return this.textList;
    }
}
```

我们会在控制台上看到unchecked的警告：

```
[WARNING] /D:/java-workspace/intellij-workspace/maven-modules/maven-compiler-plugin-java-9/src/main/java/cn/tuyucheng/taketoday/maven/java9/Data.java:[7,27] 未经检查的转换
  需要: java.util.List<java.lang.String>
  找到:    java.util.ArrayList
```

由于编译插件的两个goal都自动绑定到Maven默认生命周期的各个阶段，因此我们可以使用命令mvn compile和mvn test-compile来执行这些goal。

## 4. Java 9

### 4.1 配置

在Java 8之前，我们使用的版本号为1.X，其中X代表Java的版本，比如1.8代表Java 8。对于Java 9及以上版本，我们可以直接使用版本号：

```xml
<configuration>
    <source>9</source>
    <target>9</target>
</configuration>
```

同样，我们可以使用以下属性定义版本：

```xml
<properties>
    <maven.compiler.source>9</maven.compiler.source>
    <maven.compiler.target>9</maven.compiler.target>
</properties>
```

Maven在3.5.0中增加了对Java 9的支持，所以我们至少需要这个版本及以上的Maven版本。并且还需要至少3.8.0版本的maven-compiler-plugin：

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.8.1</version>
            <configuration>
                <source>9</source>
                <target>9</target>
            </configuration>
        </plugin>
    </plugins>
</build>
```

### 4.2 构建

现在是时候测试我们的配置了。首先，我们创建一个MavenCompilerPlugin类，在这个类中我们从另一个模块导入一个包。一个简单的例子是javax.xml.XMLConstants.XML_NS_PREFIX：

```java
public class MavenCompilerPlugin {
    
    public static void main(String[] args) {
        System.out.println("The XML namespace prefix is: " + XML_NS_PREFIX);
    }
}
```

接下来，让我们编译它：

```
mvn -q clean compile exec:java -Dexec.mainClass="cn.tuyucheng.taketoday.maven.java9.MavenCompilerPlugin"
```

但是，当使用Java 9时，我们会得到一个错误：

```java
[ERROR] COMPILATION ERROR :
[ERROR] .../MavenCompilerPlugin.java:[3,20]
  package javax.xml is not visible
  (package javax.xml is declared in module java.xml,
  but module cn.tuyucheng.taketoday.maven.java9 does not read it)
[ERROR] .../MavenCompilerPlugin.java:[3,1]
  static import only from classes and interfaces
[ERROR] .../MavenCompilerPlugin.java:[7,62]
  cannot find symbol
symbol:   variable XML_NS_PREFIX
location: class cn.tuyucheng.taketoday.maven.java9.MavenCompilerPlugin
```

错误显示这个包位于一个单独的模块中，而我们尚未将其包含在build中。

解决这个问题的最简单方法是创建module-info.java类并表明我们需要java.xml模块：

```java
module cn.tuyucheng.taketoday.maven.java9 {
    requires java.xml;
}
```

现在再次编译，控制台的输出为：

```text
The XML namespace prefix is: xml
```

## 5. 总结

在本文中，我们回顾了编译插件并介绍了如何使用它，并介绍了Maven对Java 9的支持。