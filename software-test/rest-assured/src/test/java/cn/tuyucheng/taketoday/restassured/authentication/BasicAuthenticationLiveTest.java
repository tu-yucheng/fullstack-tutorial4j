package cn.tuyucheng.taketoday.restassured.authentication;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;

/**
 * For this Live Test we need:
 * * a running instance of the service located in the spring-security-web-rest-basic-auth module.
 *
 * @see <a href="https://github.com/tu-yucheng/fullstack-tutorial4j/tree/master/spring-security-modules/spring-security-web-rest-basic-auth">spring-security-web-rest-basic-auth module</a>
 */
class BasicAuthenticationLiveTest {

	private static final String USER = "user1";
	private static final String PASSWORD = "user1Pass";
	private static final String SVC_URL = "http://localhost:8082/spring-security-web-rest-basic-auth/api/foos/1";

	@Test
	void givenNoAuthentication_whenRequestSecuredResource_thenUnauthorizedResponse() {
		get(SVC_URL).then()
				.assertThat()
				.statusCode(HttpStatus.UNAUTHORIZED.value());
	}

	@Test
	void givenBasicAuthentication_whenRequestSecuredResource_thenResourceRetrieved() {
		given().auth()
				.basic(USER, PASSWORD)
				.when()
				.get(SVC_URL)
				.then()
				.assertThat()
				.statusCode(HttpStatus.OK.value());
	}
}