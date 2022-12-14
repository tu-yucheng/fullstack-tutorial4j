## Spring Security Login - 1

This module contains articles about login mechanisms with Spring Security.

### Relevant Articles:

- [Spring Security Form Login](docs/SpringSecurity_FormLogin.md)
- [Spring Security Logout](docs/SpringSecurity_Logout.md)
- [Spring HTTP/HTTPS Channel Security](docs/SpringSecurity_HttpsChannel.md)
- [Spring Security – Customize the 403 Forbidden/Access Denied Page](docs/SpringSecurity_Custom_AccessDeniedPage.md)
- [Spring Security – Redirect to the Previous URL After Login](docs/SpringSecurity_RedirectLogin.md)
- [Spring Security Custom AuthenticationFailureHandler](docs/SpringSecurity_Custom_AuthenticationFailureHandler.md)
- [Extra Login Fields with Spring Security](docs/SpringSecurity_Extra_LoginFields.md)

- More articles: [[next -->]](../spring-security-web-login-2/README.md)

### Build the Project

```
mvn clean install
```

### Run the Project

- Run the application using Maven Cargo plugin.

```
mvn cargo:run
```

- Go to the login page at http://localhost:8082/spring-security-web-login/login.html
- Login using ```user1/user1Pass``` details.