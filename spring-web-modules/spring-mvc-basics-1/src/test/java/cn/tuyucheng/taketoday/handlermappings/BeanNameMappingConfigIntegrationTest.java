package cn.tuyucheng.taketoday.handlermappings;

import cn.tuyucheng.taketoday.config.BeanNameUrlHandlerMappingConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration
@ContextConfiguration(classes = BeanNameUrlHandlerMappingConfig.class)
class BeanNameMappingConfigIntegrationTest {

	@Autowired
	private WebApplicationContext webAppContext;
	private MockMvc mockMvc;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
	}

	@Test
	void whenBeanNameMapping_thenMappedOK() throws Exception {
		mockMvc.perform(get("/beanNameUrl")).
				andExpect(status().isOk()).
				andExpect(view().name("welcome")).
				andDo(print());
	}
}