package cn.tuyucheng.taketoday.restassured.authentication;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

/**
 * For this Live Test we need:
 * * a running instance of the service located in the spring-boot-admin/spring-boot-admin-server module.
 *
 * @see <a href="https://github.com/eugenp/tutorials/tree/master/spring-boot-admin/spring-boot-admin-server">spring-boot-admin/spring-boot-admin-server module</a>
 */
class BasicPreemptiveAuthenticationLiveTest {

	private static final String USER = "admin";
	private static final String PASSWORD = "admin";
	private static final String SVC_URL = "http://localhost:8080/api/applications/";

	@Test
	void givenNoAuthentication_whenRequestSecuredResource_thenUnauthorizedResponse() {
		get(SVC_URL).then()
				.assertThat()
				.statusCode(HttpStatus.OK.value())
				.body(containsString("<form"), containsString("action=\"login\""));
	}

	@Test
	void givenNonPreemptiveBasicAuthentication_whenRequestSecuredResource_thenLoginPageRetrieved() {
		given().auth()
				.basic(USER, PASSWORD)
				.when()
				.get(SVC_URL)
				.then()
				.assertThat()
				.statusCode(HttpStatus.OK.value())
				.body(containsString("<form"), containsString("action=\"login\""));
	}

	@Test
	void givenPreemptiveBasicAuthentication_whenRequestSecuredResource_thenResourceRetrieved() {
		given().auth()
				.preemptive()
				.basic(USER, PASSWORD)
				.when()
				.get(SVC_URL)
				.then()
				.assertThat()
				.statusCode(HttpStatus.OK.value())
				.body("size()", is(1));
	}
}