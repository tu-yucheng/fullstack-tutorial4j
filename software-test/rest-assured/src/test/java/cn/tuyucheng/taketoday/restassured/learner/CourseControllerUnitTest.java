package cn.tuyucheng.taketoday.restassured.learner;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;

import static io.restassured.http.ContentType.JSON;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@ExtendWith(MockitoExtension.class)
class CourseControllerUnitTest {

	@Mock
	private CourseService courseService;
	@InjectMocks
	private CourseController courseController;
	@InjectMocks
	private CourseControllerExceptionHandler courseControllerExceptionHandler;

	@BeforeEach
	void initialiseRestAssuredMockMvcStandalone() {
		RestAssuredMockMvc.standaloneSetup(courseController, courseControllerExceptionHandler);
	}

	@Test
	void givenNoExistingCoursesWhenGetCoursesThenRespondWithStatusOkAndEmptyArray() {
		when(courseService.getCourses()).thenReturn(Collections.emptyList());

		given()
				.when()
				.get("/courses")
				.then()
				.log().ifValidationFails()
				.statusCode(OK.value())
				.contentType(JSON)
				.body(is(equalTo("[]")));
	}

	@Test
	void givenNoMatchingCoursesWhenGetCoursesThenRespondWithStatusNotFound() {
		String nonMatchingCourseCode = "nonMatchingCourseCode";

		when(courseService.getCourse(nonMatchingCourseCode)).thenThrow(new CourseNotFoundException(nonMatchingCourseCode));

		given()
				.when()
				.get("/courses/" + nonMatchingCourseCode)
				.then()
				.log().ifValidationFails()
				.statusCode(NOT_FOUND.value());
	}
}