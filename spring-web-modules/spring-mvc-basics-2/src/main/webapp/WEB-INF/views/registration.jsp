<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User Registration</title>
</head>
<body>
<form:form method="POST" modelAttribute="user" action="register">
    <form:label path="email">Email: </form:label>
    <form:input path="email" type="text"/>
    <br/>
    <form:label path="password">Password: </form:label>
    <form:input path="password" type="password"/>
    <br/>
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>