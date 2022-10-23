package cn.tuyucheng.taketoday.spring.controller.push;

import cn.tuyucheng.taketoday.spring.configuration.PushConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig(PushConfiguration.class)
class PushControllerIntegrationTest {
    @Autowired
    private WebApplicationContext webAppContext;
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext)
                .build();
    }

    @Test
    void whenDemoWithPushGETisPerformed_thenRetrievedStatusOk() throws Exception {
        mockMvc.perform(get("/demoWithPush"))
                .andExpect(status().isOk());
    }

    @Test
    void whenDemoWithoutPushGETisPerformed_thenRetrievedStatusOk() throws Exception {
        mockMvc.perform(get("/demoWithoutPush"))
                .andExpect(status().isOk());
    }
}