package cn.tuyucheng.taketoday.inmemory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = InMemoryAuthApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
class InMemoryAuthControllerIntegrationTest {

	@Autowired
	private TestRestTemplate template;

	@Test
	void givenRequestOnPublicService_shouldSucceedWith200() throws Exception {
		ResponseEntity<String> result = template.getForEntity("/public/hello", String.class);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	void givenRequestOnPrivateService_shouldFailWith401() throws Exception {
		ResponseEntity<String> result = template.getForEntity("/private/hello", String.class);
		assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
	}

	@Test
	void givenAuthRequestOnPrivateService_shouldSucceedWith200() throws Exception {
		ResponseEntity<String> result = template.withBasicAuth("spring", "secret")
				.getForEntity("/private/hello", String.class);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	@Test
	void givenInvalidAuthRequestOnPrivateService_shouldSucceedWith200() throws Exception {
		ResponseEntity<String> result = template.withBasicAuth("spring", "wrong")
				.getForEntity("/private/hello", String.class);
		assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
	}
}