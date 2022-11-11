package cn.tuyucheng.taketoday.headers.controller;

import cn.tuyucheng.taketoday.spring.headers.controller.ReadHeaderRestController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig(ReadHeaderRestControllerIntegrationTest.Config.class)
@ExtendWith(SpringExtension.class)
class ReadHeaderRestControllerIntegrationTest {

	private MockMvc mockMvc;

	@BeforeEach
	void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(new ReadHeaderRestController()).build();
	}

	@Test
	void whenGetRequestSentToAllHeaders_thenStatusOkAndTextReturned() throws Exception {
		mockMvc.perform(get("/listHeaders").header("my-header", "Test"))
				.andExpect(status().isOk())
				.andExpect(content().string("Listed 1 headers"));
	}

	@Test
	void whenGetRequestSentToMultiValue_thenStatusOkAndTextReturned() throws Exception {
		mockMvc.perform(get("/multiValue").header("my-header", "ABC", "DEF", "GHI"))
				.andExpect(status().isOk())
				.andExpect(content().string("Listed 1 headers"));
	}

	@Test
	void whenGetRequestSentToGreeting_thenStatusOKAndGreetingReturned() throws Exception {
		mockMvc.perform(get("/greeting").header(HttpHeaders.ACCEPT_LANGUAGE, "de"))
				.andExpect(status().isOk())
				.andExpect(content().string("Hallo!"));
	}

	@Test
	void whenPrioritizedListGetRequestSentToGreeting_thenStatusOKAndGreetingReturned() throws Exception {
		mockMvc.perform(get("/greeting").header(HttpHeaders.ACCEPT_LANGUAGE, "fr,en,de"))
				.andExpect(status().isOk())
				.andExpect(content().string("Bonjour!"));
	}

	@Test
	void whenWeightedListGetRequestSentToGreeting_thenStatusOKAndGreetingReturned() throws Exception {
		mockMvc.perform(get("/greeting").header(HttpHeaders.ACCEPT_LANGUAGE, "Accept-Language: es; q=1.0, de; q=0.5"))
				.andExpect(status().isOk())
				.andExpect(content().string("Hola!"));
	}

	@Test
	void whenGetRequestSentToDouble_thenStatusOKAndCorrectResultReturned() throws Exception {
		mockMvc.perform(get("/double").header("my-number", 2))
				.andExpect(status().isOk())
				.andExpect(content().string("2 * 2 = 4"));
	}

	@Test
	void whenGetRequestSentToGetBaseUrl_thenStatusOkAndHostReturned() throws Exception {
		mockMvc.perform(get("/getBaseUrl").header("host", "localhost:8080"))
				.andExpect(status().isOk())
				.andExpect(content().string("Base URL = http://localhost:8080"));
	}

	@Test
	void whenGetRequestSentToNonRequiredHeaderWithoutHeader_thenStatusOKAndMessageReturned() throws Exception {
		mockMvc.perform(get("/nonRequiredHeader"))
				.andExpect(status().isOk())
				.andExpect(content().string("Was the optional header present? No!"));
	}

	@Test
	void whenGetRequestSentToDefaultWithoutHeader_thenStatusOKAndMessageReturned() throws Exception {
		mockMvc.perform(get("/default"))
				.andExpect(status().isOk())
				.andExpect(content().string("Optional Header is 3600"));
	}

	@Configuration
	static class Config {

	}
}