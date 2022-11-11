package cn.tuyucheng.taketoday.cachedrequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(
		loader = AnnotationConfigWebContextLoader.class,
		classes = {
				HttpRequestDemoConfig.class,
				ContentCachingFilter.class,
				PrintRequestContentFilter.class,
				PersonController.class
		}
)
@WebAppConfiguration
class PersonControllerIntegrationTest {

	ObjectMapper objectMapper = new ObjectMapper();
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;
	@Autowired
	private ContentCachingFilter contentCachingFilter;

	@Autowired
	private PrintRequestContentFilter printRequestContentFilter;

	@BeforeEach
	void setup() throws Exception {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
				.addFilter(contentCachingFilter, "/**")
				.addFilter(printRequestContentFilter, "/**")
				.build();
	}

	@Test
	void whenValidInput_thenCreateBook() throws IOException, Exception {
		// assign - given
		Person person = new Person("sumit", "abc", 100);

		// act - when
		ResultActions result = mockMvc.perform(post("/person")
				.accept(APPLICATION_JSON)
				.contentType(APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(person)));

		// assert - then
		result.andExpect(status().isNoContent());
	}
}