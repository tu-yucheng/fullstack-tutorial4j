package cn.tuyucheng.taketoday.customuserdetails;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@Import(PasswordEncoderConfiguration.class)
class ViewControllerIntegrationTest {

	@Autowired
	private WebApplicationContext context;
	MockMvc mvc;

	@BeforeEach
	void setup() {
		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.build();
	}

	@Test
	void givenUser_whenPerformingGet_thenReturnsIndex() throws Exception {
		mvc.perform(get("/index").with(user("user").password("password")))
				.andExpect(status().isOk())
				.andExpect(view().name("userdetails"));
	}
}