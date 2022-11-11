package cn.tuyucheng.web.controller;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class SampleControllerLiveTest {

	private static final String SERVICE_BASE_URL = "/spring-mvc-basics";

	@Test
	void whenSampleEndpointCalled_thenOkResponseObtained() {
		RestAssured.get(SERVICE_BASE_URL + "/sample")
				.then()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	void whenSample2EndpointCalled_thenOkResponseObtained() {
		RestAssured.get(SERVICE_BASE_URL + "/sample2")
				.then()
				.statusCode(HttpStatus.OK.value());
	}

	@Test
	void whenSample3EndpointCalled_thenOkResponseObtained() {
		RestAssured.get(SERVICE_BASE_URL + "/sample3")
				.then()
				.statusCode(HttpStatus.OK.value());
	}
}