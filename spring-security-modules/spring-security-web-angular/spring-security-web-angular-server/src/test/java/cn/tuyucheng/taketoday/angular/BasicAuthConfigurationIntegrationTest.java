package cn.tuyucheng.taketoday.angular;

import cn.tuyucheng.taketoday.angular.basicauth.SpringBootSecurityApplication;
import cn.tuyucheng.taketoday.angular.vo.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = SpringBootSecurityApplication.class)
class BasicAuthConfigurationIntegrationTest {

    TestRestTemplate restTemplate;
    URL base;

    @LocalServerPort
    int port;

    @BeforeEach
    void setUp() throws MalformedURLException {
        restTemplate = new TestRestTemplate("user", "password");
        base = new URL("http://localhost:" + port);
    }

    @Test
    void givenCorrectCredentials_whenLogin_ThenSuccess() throws IllegalStateException, IOException {
        restTemplate = new TestRestTemplate();
        User user = new User();
        user.setUserName("user");
        user.setPassword("password");
        ResponseEntity<String> response = restTemplate.postForEntity(base.toString() + "/login", user, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("true"));
    }

    @Test
    void givenWrongCredentials_whenLogin_ThenReturnFalse() throws IllegalStateException, IOException {
        restTemplate = new TestRestTemplate();
        User user = new User();
        user.setUserName("user");
        user.setPassword("wrongpassword");
        ResponseEntity<String> response = restTemplate.postForEntity(base.toString() + "/login", user, String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("false"));
    }

    @Test
    void givenLoggedInUser_whenRequestsHomePage_ThenSuccess() throws IllegalStateException, IOException {
        ResponseEntity<String> response = restTemplate.getForEntity(base.toString() + "/user", String.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().contains("user"));
    }

    @Test
    void givenWrongCredentials_whenRequestsHomePage_ThenUnauthorized() throws IllegalStateException, IOException {
        restTemplate = new TestRestTemplate("user", "wrongpassword");
        ResponseEntity<String> response = restTemplate.getForEntity(base.toString() + "/user", String.class);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertTrue(response.getBody().contains("Unauthorized"));
    }
}