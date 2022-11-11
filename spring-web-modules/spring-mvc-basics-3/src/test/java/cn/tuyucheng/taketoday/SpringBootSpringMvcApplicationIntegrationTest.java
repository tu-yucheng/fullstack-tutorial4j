package cn.tuyucheng.taketoday;

import cn.tuyucheng.taketoday.boot.SpringMvcApplication;
import cn.tuyucheng.taketoday.boot.domain.Modes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringMvcApplication.class)
@WebAppConfiguration
class SpringBootSpringMvcApplicationIntegrationTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@BeforeEach
	void setupMockMvc() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	void givenRequestHasBeenMade_whenMeetsAllOfGivenConditions_thenCorrect() throws Exception {
		mockMvc.perform(get("/entity/all"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(4)));
	}

	@Test
	void givenRequestHasBeenMade_whenMeetsFindByDateOfGivenConditions_thenCorrect() throws Exception {
		mockMvc.perform(get("/entity/findbydate/{date}", "2011-12-03T10:15:30"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", equalTo(1)));
	}

	@Test
	void givenRequestHasBeenMade_whenMeetsFindByModeOfGivenConditions_thenCorrect() throws Exception {
		mockMvc.perform(get("/entity/findbymode/{mode}", Modes.ALPHA.name()))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", equalTo(1)));
	}

	@Test
	void givenRequestHasBeenMade_whenMeetsFindByVersionOfGivenConditions_thenCorrect() throws Exception {
		mockMvc.perform(get("/entity/findbyversion")
						.header("Version", "1.0.0"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", equalTo(1)));
	}
}