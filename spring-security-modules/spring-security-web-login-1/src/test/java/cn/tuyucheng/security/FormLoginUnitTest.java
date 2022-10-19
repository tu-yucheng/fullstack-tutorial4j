package cn.tuyucheng.security;

import cn.tuyucheng.security.config.SecSecurityConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.Filter;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SecSecurityConfig.class})
@WebAppConfiguration
class FormLoginUnitTest {

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private Filter springSecurityFilterChain;

    private MockMvc mvc;

    @BeforeEach
    void setup() {
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilters(springSecurityFilterChain)
                .build();
    }

    @Test
    void givenValidRequestWithValidCredentials_shouldLoginSuccessfully() throws Exception {
        mvc.perform(formLogin("/perform_login").user("user1").password("user1Pass"))
                .andExpect(status().isFound())
                .andExpect(authenticated().withUsername("user1"));
    }

    @Test
    void givenValidRequestWithInvalidCredentials_shouldFailWith401() throws Exception {
        MvcResult result = mvc.perform(formLogin("/perform_login").user("random").password("random")).andReturn();
                /*.andExpect(status().isUnauthorized())
                .andDo(print())
                .andExpect(unauthenticated())
                .andReturn();*/

        assertTrue(result.getResponse().getContentAsString().contains("Bad credentials"));
    }
}