## 一、概述

国际化是准备应用程序以支持各种语言、区域、文化或政治特定数据的过程。它是任何现代多语言应用程序的重要方面。

为了进一步阅读，我们应该知道有一个非常流行的国际化缩写（可能比实际名称更流行）—— i18n，因为 'i' 和 'n' 之间有 18 个字母。

对于当今的企业计划而言，为来自世界不同地区或多个文化领域的人们提供服务至关重要。不同的文化或语言区域不仅决定了特定语言的描述，还决定了货币、数字表示，甚至不同的日期和时间构成。

例如，让我们关注特定国家/地区的数字。它们有各种小数和千位分隔符：

-   102,300.45（美国）
-   102 300,45（波兰）
-   102.300,45（德国）

还有不同的日期格式：

-   2018 年 1 月 1 日星期一下午 3:20:34 CET（美国）
-   2018 年 1 月 1 日星期一下午 3:20 CET（法国）。
-   2018年1月1日 星期一 下午03时20分34秒 CET (China)

更重要的是，不同的国家有独特的货币符号：

-   1,200.60 英镑（英国）
-   € 1.200,60 (意大利)
-   1 200,60 欧元（法国）
-   1,200.60 美元（美国）

需要知道的一个重要事实是，即使国家/地区具有相同的货币和货币符号（例如法国和意大利），其货币符号的位置也可能不同。

## 2.本地化

在 Java 中，我们可以使用一个很棒的特性，称为Locale类。

它使我们能够快速区分文化区域并适当地格式化我们的内容。这对于国际化进程至关重要。和 i18n 一样，Localization 也有它的缩写——l10n。

使用Locale的主要原因是无需重新编译即可访问所有必需的特定于 locale 的格式。一个应用程序可以同时处理多个语言环境，因此支持新语言很简单。

语言环境通常由语言、国家和变体缩写表示，并用下划线分隔：

-   de (德语)
-   it_CH（意大利语、瑞士）
-   en_US_UNIX（美国，UNIX 平台）

### 2.1。字段

我们已经了解到，Locale由语言代码、国家代码和变体组成。还有两个可能的字段需要设置： script 和 extensions。

让我们看一下字段列表，看看规则是什么：

-   语言 可以是ISO 639 alpha-2 或 alpha-3代码或注册语言子标签。
-   地区 （国家/地区）是ISO 3166 alpha-2国家代码或UN numeric-3区号。
-   Variant 是一个区分大小写的值或一组指定Locale变体的值。
-   脚本必须是有效的ISO 15924 alpha-4代码。
-   Extensions 是一个由单个字符键和字符串值组成的映射。

[IANA 语言子标签注册表包含](https://www.iana.org/assignments/language-subtag-registry/language-subtag-registry)语言、地区、变体和脚本的可能值。

没有可能的扩展值列表，但这些值必须是格式正确的[BCP-47](https://docs.oracle.com/javase/tutorial/i18n/locale/extensions.html)子标签。键和值始终转换为小写。

### 2.2. Locale.Builder

有几种方法可以创建Locale对象。一种可能的方法是使用Locale.Builder。Locale.Builder有五个 setter 方法，我们可以使用它们来构建对象并同时验证这些值：

```java
Locale locale = new Locale.Builder()
  .setLanguage("fr")
  .setRegion("CA")
  .setVariant("POSIX")
  .setScript("Latn")
  .build();
```

上述Locale的字符串表示形式是fr_CA_POSIX_#Latn。

很高兴知道设置“变体”可能有点棘手，因为对变体值没有官方限制，尽管 setter 方法要求它符合BCP -47。

否则，它将抛出 IllformedLocaleException。

在我们需要使用不通过验证的值的情况下，我们可以使用Locale构造函数，因为它们不验证值。

### 2.3. 构造函数

Locale具有三个构造函数：

-   新语言环境（字符串语言）
-   新语言环境（字符串语言，字符串国家）
-   新语言环境（字符串语言、字符串国家、字符串变体）

一个 3 参数构造函数：

```java
Locale locale = new Locale("pl", "PL", "UNIX");
```

有效的变体必须是5 到 8 个字母数字或单个数字后跟 3 个字母数字的字符串。我们只能通过构造函数将“UNIX”应用于变量字段，因为它不满足这些要求。

但是，使用构造函数创建Locale对象有一个缺点——我们不能设置扩展和脚本字段。

### 2.4. 常数

这可能是获取Locales的最简单和最有限的方法。Locale类有几个静态常量代表最流行的国家或语言：

```java
Locale japan = Locale.JAPAN;
Locale japanese = Locale.JAPANESE;
```

### 2.5. 语言标签

创建语言环境的另一种方法 是调用静态工厂方法 forLanguageTag(String languageTag)。此方法需要符合IETF BCP 47标准的字符串。

这就是我们创建 UK Locale的方式：

```java
Locale uk = Locale.forLanguageTag("en-UK");
```

### 2.6. 可用的语言环境

即使我们可以创建多个Locale对象的组合，我们也可能无法使用它们。

需要注意的一个重要注意事项是平台上的语言环境依赖于那些已安装在 Java 运行时中的语言环境。

当我们使用区域设置进行格式化时，不同的格式化程序可能有更小的可用区域设置集，它们安装在运行时中。

让我们检查如何检索可用语言环境的数组：

```java
Locale[] numberFormatLocales = NumberFormat.getAvailableLocales();
Locale[] dateFormatLocales = DateFormat.getAvailableLocales();
Locale[] locales = Locale.getAvailableLocales();
```

之后，我们可以检查我们的语言环境是否 位于可用的语言环境中。

我们应该记住，对于 Java 平台的不同实现和不同的功能领域，可用的区域设置是不同的 。

[Oracle 的 Java SE Development Kit 网页](http://www.oracle.com/technetwork/java/javase/documentation/java9locales-3559485.html)上提供了支持的语言环境的完整列表 。

### 2.7. 默认语言环境

在使用本地化时，我们可能需要知道JVM实例上的默认Locale是什么。幸运的是，有一种简单的方法可以做到这一点：

```java
Locale defaultLocale = Locale.getDefault();
```

此外，我们可以通过调用类似的 setter 方法来指定默认的Locale ：

```java
Locale.setDefault(Locale.CANADA_FRENCH);
```

当我们想要创建 不依赖于 JVM实例的JUnit测试时，这一点尤其重要。

## 3. 数字和货币

本节涉及应符合不同区域设置特定约定的数字和货币格式化程序。

要格式化原始数字类型（int、double）以及它们的等效对象（Integer、Double），我们应该使用NumberFormat类及其静态工厂方法。

有两种方法对我们来说很有趣：

-   NumberFormat.getInstance（区域设置语言环境）
-   NumberFormat.getCurrencyInstance（区域设置语言环境）

让我们检查一个示例代码：

```java
Locale usLocale = Locale.US;
double number = 102300.456d;
NumberFormat usNumberFormat = NumberFormat.getInstance(usLocale);

assertEquals(usNumberFormat.format(number), "102,300.456");
```

正如我们所看到的，它就像创建Locale 并使用它来检索NumberFormat实例并格式化示例编号一样简单。我们可以注意到输出包括特定于语言环境的小数和千位分隔符。

这是另一个例子：

```java
Locale usLocale = Locale.US;
BigDecimal number = new BigDecimal(102_300.456d);

NumberFormat usNumberFormat = NumberFormat.getCurrencyInstance(usLocale); 
assertEquals(usNumberFormat.format(number), "$102,300.46");
```

格式化货币涉及与格式化数字相同的步骤。唯一的区别是格式化程序将货币符号和四舍五入的小数部分附加到两位数。

## 4. 日期和时间

现在，我们将学习日期和时间格式化，这可能比格式化数字更复杂。

首先，我们应该知道，Java 8 中的日期和时间格式发生了显着变化，因为它包含全新的日期/时间API。因此，我们将浏览不同的格式化程序类。

### 4.1。日期时间格式化程序

自 Java 8 引入以来，日期和时间本地化的主要类是DateTimeFormatter类。它对实现TemporalAccessor接口的类进行操作，例如LocalDateTime、LocalDate、 LocalTime或ZonedDateTime。 要创建DateTimeFormatter ，我们必须至少提供一个模式，然后是 Locale。 让我们看一个示例代码：

```java
Locale.setDefault(Locale.US);
LocalDateTime localDateTime = LocalDateTime.of(2018, 1, 1, 10, 15, 50, 500);
String pattern = "dd-MMMM-yyyy HH:mm:ss.SSS";

DateTimeFormatter defaultTimeFormatter = DateTimeFormatter.ofPattern(pattern);
DateTimeFormatter deTimeFormatter = DateTimeFormatter.ofPattern(pattern, Locale.GERMANY);

assertEquals(
  "01-January-2018 10:15:50.000", 
  defaultTimeFormatter.format(localDateTime));
assertEquals(
  "01-Januar-2018 10:15:50.000", 
  deTimeFormatter.format(localDateTime));
```

我们可以看到，在检索 到DateTimeFormatter之后， 我们所要做的就是调用format()方法。

为了更好地理解，我们应该熟悉可能的模式字母。

我们以字母为例：

```plaintext
Symbol  Meaning                     Presentation      Examples
  ------  -------                     ------------      -------
   y       year-of-era                 year              2004; 04
   M/L     month-of-year               number/text       7; 07; Jul; July; J
   d       day-of-month                number            10

   H       hour-of-day (0-23)          number            0
   m       minute-of-hour              number            30
   s       second-of-minute            number            55
   S       fraction-of-second          fraction          978
```

所有可能的带有解释的模式字母都可以在 [DateTimeFormatter](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/format/DateTimeFormatter.html)[的 Java 文档中找到](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/time/format/DateTimeFormatter.html)。 值得知道的是，最终值取决于符号的数量。示例中的“MMMM”打印完整的月份名称，而单个“M”字母将给出没有前导 0 的月份编号。

要完成DateTimeFormatter，让我们看看如何格式化LocalizedDateTime：

```java
LocalDateTime localDateTime = LocalDateTime.of(2018, 1, 1, 10, 15, 50, 500);
ZoneId losAngelesTimeZone = TimeZone.getTimeZone("America/Los_Angeles").toZoneId();

DateTimeFormatter localizedTimeFormatter = DateTimeFormatter
  .ofLocalizedDateTime(FormatStyle.FULL);
String formattedLocalizedTime = localizedTimeFormatter.format(
  ZonedDateTime.of(localDateTime, losAngelesTimeZone));

assertEquals("Monday, January 1, 2018 10:15:50 AM PST", formattedLocalizedTime);
```

为了格式化LocalizedDateTime，我们可以使用ofLocalizedDateTime(FormatStyle dateTimeStyle)方法并提供预定义的FormatStyle。

要更深入地了解 Java 8日期/时间API，我们在[此处](https://www.baeldung.com/java-8-date-time-intro)有一篇现有文章。

### 4.2. DateFormat和SimpleDateFormatter 

由于在使用Dates和Calendars的项目上工作仍然很常见，我们将简要介绍使用 DateFormat和SimpleDateFormat 类格式化日期和时间的功能。

我们来分析一下第一个的能力：

```java
GregorianCalendar gregorianCalendar = new GregorianCalendar(2018, 1, 1, 10, 15, 20);
Date date = gregorianCalendar.getTime();

DateFormat ffInstance = DateFormat.getDateTimeInstance(
  DateFormat.FULL, DateFormat.FULL, Locale.ITALY);
DateFormat smInstance = DateFormat.getDateTimeInstance(
  DateFormat.SHORT, DateFormat.MEDIUM, Locale.ITALY);

assertEquals("giovedì 1 febbraio 2018 10.15.20 CET", ffInstance.format(date));
assertEquals("01/02/18 10.15.20", smInstance.format(date));
```

DateFormat与Dates一起使用 ，并具有三种有用的方法：

-   获取日期时间实例
-   获取日期实例
-   获取时间实例

它们都将DateFormat的预定义值 作为参数。每个方法都是重载的，所以传递 Locale 也是可能的。如果我们想使用自定义模式，就像在DateTimeFormatter中所做的那样，我们可以使用SimpleDateFormat。让我们看一个简短的代码片段：

```java
GregorianCalendar gregorianCalendar = new GregorianCalendar(
  2018, 1, 1, 10, 15, 20);
Date date = gregorianCalendar.getTime();
Locale.setDefault(new Locale("pl", "PL"));

SimpleDateFormat fullMonthDateFormat = new SimpleDateFormat(
  "dd-MMMM-yyyy HH:mm:ss:SSS");
SimpleDateFormat shortMonthsimpleDateFormat = new SimpleDateFormat(
  "dd-MM-yyyy HH:mm:ss:SSS");

assertEquals(
  "01-lutego-2018 10:15:20:000", fullMonthDateFormat.format(date));
assertEquals(
  "01-02-2018 10:15:20:000" , shortMonthsimpleDateFormat.format(date));
```

## 5. 定制

由于一些良好的设计决策，我们不依赖于特定于区域设置的格式模式，我们可以配置几乎每个细节以完全满足输出。

要自定义数字格式，我们可以使用DecimalFormat和DecimalFormatSymbols。

让我们考虑一个简短的例子：

```java
Locale.setDefault(Locale.FRANCE);
BigDecimal number = new BigDecimal(102_300.456d);

DecimalFormat zeroDecimalFormat = new DecimalFormat("000000000.0000");
DecimalFormat dollarDecimalFormat = new DecimalFormat("$###,###.##");

assertEquals(zeroDecimalFormat.format(number), "000102300,4560");
assertEquals(dollarDecimalFormat.format(number), "$102 300,46");

```

[DecimalFormat文档显示了所有可能的](https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/text/DecimalFormat.html) 模式字符。我们现在需要知道的是，“000000000.000”确定前导零或尾随零，',' 是千位分隔符，'.' 是十进制一。

也可以添加货币符号。我们可以在下面看到，使用DateFormatSymbol类可以实现相同的结果：

```java
Locale.setDefault(Locale.FRANCE);
BigDecimal number = new BigDecimal(102_300.456d);

DecimalFormatSymbols decimalFormatSymbols = DecimalFormatSymbols.getInstance();
decimalFormatSymbols.setGroupingSeparator('^');
decimalFormatSymbols.setDecimalSeparator('@');
DecimalFormat separatorsDecimalFormat = new DecimalFormat("$###,###.##");
separatorsDecimalFormat.setGroupingSize(4);
separatorsDecimalFormat.setCurrency(Currency.getInstance(Locale.JAPAN));
separatorsDecimalFormat.setDecimalFormatSymbols(decimalFormatSymbols);

assertEquals(separatorsDecimalFormat.format(number), "$10^2300@46");
```

正如我们所看到的，DecimalFormatSymbols类使我们能够指定我们可以想象的任何数字格式。

要自定义 SimpleDataFormat，我们可以使用DateFormatSymbols。

让我们看看更改日期名称有多简单：

```java
Date date = new GregorianCalendar(2018, 1, 1, 10, 15, 20).getTime();
Locale.setDefault(new Locale("pl", "PL"));

DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
dateFormatSymbols.setWeekdays(new String[]{"A", "B", "C", "D", "E", "F", "G", "H"});
SimpleDateFormat newDaysDateFormat = new SimpleDateFormat(
  "EEEE-MMMM-yyyy HH:mm:ss:SSS", dateFormatSymbols);

assertEquals("F-lutego-2018 10:15:20:000", newDaysDateFormat.format(date));
```

## 6. 资源包

最后， JVM中国际化的关键部分是Resource Bundle机制。

ResourceBundle的目的是为应用程序提供本地化的消息/描述，这些消息/描述可以外部化到单独的文件中。我们在之前的一篇文章中介绍了资源包的使用和配置——资源包 [指南](https://www.baeldung.com/java-resourcebundle)。

## 7. 结论

语言环境 和使用它们的格式化程序是帮助我们创建国际化应用程序的工具。这些工具使我们能够创建一个可以动态适应用户语言或文化设置的应用程序，而无需多次构建，甚至无需担心 Java 是否支持Locale。

在用户可以在任何地方使用任何语言的世界中，应用这些更改的能力意味着我们的应用程序可以更直观，更容易被全球更多用户理解。

在使用 Spring Boot 应用程序时，我们还有一篇关于[Spring Boot 国际化](https://www.baeldung.com/spring-boot-internationalization)的便捷文章。