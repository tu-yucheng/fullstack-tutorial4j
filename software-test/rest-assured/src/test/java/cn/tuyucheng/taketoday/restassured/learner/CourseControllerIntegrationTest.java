package cn.tuyucheng.taketoday.restassured.learner;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.context.WebApplicationContext;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
class CourseControllerIntegrationTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@BeforeEach
	void initialiseRestAssuredMockMvcWebApplicationContext() {
		RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
	}

	@Test
	void givenNoMatchingCourseCodeWhenGetCourseThenRespondWithStatusNotFound() {
		String nonMatchingCourseCode = "nonMatchingCourseCode";

		given()
				.when()
				.get("/courses/" + nonMatchingCourseCode)
				.then()
				.log().ifValidationFails()
				.statusCode(NOT_FOUND.value());
	}
}