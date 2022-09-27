package cn.tuyucheng.taketoday.web;

import cn.tuyucheng.taketoday.multipleauthproviders.MultipleAuthProvidersApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = MultipleAuthProvidersApplication.class)
class MultipleAuthProvidersApplicationIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void givenMemUsers_whenGetPingWithValidUser_thenOk() {
        ResponseEntity<String> result = makeRestCallToGetPing("memuser", "pass");

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody()).isEqualTo("OK");
    }

    @Test
    void givenExternalUsers_whenGetPingWithValidUser_thenOK() {
        ResponseEntity<String> result = makeRestCallToGetPing("externaluser", "pass");

        assertThat(result.getStatusCodeValue()).isEqualTo(200);
        assertThat(result.getBody()).isEqualTo("OK");
    }

    @Test
    void givenAuthProviders_whenGetPingWithNoCred_then401() {
        ResponseEntity<String> result = makeRestCallToGetPing();

        assertThat(result.getStatusCodeValue()).isEqualTo(401);
    }

    @Test
    void givenAuthProviders_whenGetPingWithBadCred_then401() {
        ResponseEntity<String> result = makeRestCallToGetPing("user", "bad_password");

        assertThat(result.getStatusCodeValue()).isEqualTo(401);
    }

    private ResponseEntity<String> makeRestCallToGetPing(String username, String password) {
        return restTemplate.withBasicAuth(username, password)
                .getForEntity("/api/ping", String.class, Collections.emptyMap());
    }

    private ResponseEntity<String> makeRestCallToGetPing() {
        return restTemplate.getForEntity("/api/ping", String.class, Collections.emptyMap());
    }
}