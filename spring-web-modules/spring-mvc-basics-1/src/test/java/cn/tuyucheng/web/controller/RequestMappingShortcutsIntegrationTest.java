package cn.tuyucheng.web.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class RequestMappingShortcutsIntegrationTest {

	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new RequestMappingShortcutsController()).build();
	}

	@Test
	void givenUrl_whenGetRequest_thenFindGetResponse() throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/get");

		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.string("GET Response");

		this.mockMvc.perform(builder)
				.andExpect(contentMatcher)
				.andExpect(status().isOk());
	}

	@Test
	void givenUrl_whenPostRequest_thenFindPostResponse() throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post("/post");

		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.string("POST Response");

		this.mockMvc.perform(builder)
				.andExpect(contentMatcher)
				.andExpect(status().isOk());
	}

	@Test
	void givenUrl_whenPutRequest_thenFindPutResponse() throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.put("/put");

		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.string("PUT Response");

		this.mockMvc.perform(builder)
				.andExpect(contentMatcher)
				.andExpect(status().isOk());
	}

	@Test
	void givenUrl_whenDeleteRequest_thenFindDeleteResponse() throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.delete("/delete");

		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.string("DELETE Response");

		this.mockMvc.perform(builder)
				.andExpect(contentMatcher)
				.andExpect(status().isOk());
	}

	@Test
	void givenUrl_whenPatchRequest_thenFindPatchResponse() throws Exception {
		MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.patch("/patch");

		ResultMatcher contentMatcher = MockMvcResultMatchers.content()
				.string("PATCH Response");

		this.mockMvc.perform(builder)
				.andExpect(contentMatcher)
				.andExpect(status().isOk());
	}
}