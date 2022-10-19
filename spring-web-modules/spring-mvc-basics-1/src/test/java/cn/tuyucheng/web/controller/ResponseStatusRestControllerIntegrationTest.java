package cn.tuyucheng.web.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ResponseStatusRestControllerIntegrationTest {

    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new ResponseStatusRestController())
                .build();
    }

    @Test
    void whenTeapotEndpointCalled_thenTeapotResponseObtained() throws Exception {
        this.mockMvc.perform(get("/teapot"))
                .andExpect(status().isIAmATeapot());
    }

    @Test
    void whenEmptyNoContentEndpointCalled_thenNoContentResponseObtained() throws Exception {
        this.mockMvc.perform(get("/empty"))
                .andExpect(status().isNoContent());
    }

    @Test
    void whenEmptyWithoutResponseStatusEndpointCalled_then200ResponseObtained() throws Exception {
        this.mockMvc.perform(get("/empty-no-responsestatus"))
                .andExpect(status().isOk());
    }

    @Test
    void whenCreateWithCreatedEndpointCalled_thenCreatedResponseObtained() throws Exception {
        this.mockMvc.perform(post("/create"))
                .andExpect(status().isCreated());
    }

    @Test
    void whenCreateWithoutResponseStatusEndpointCalled_thenCreatedResponseObtained() throws Exception {
        this.mockMvc.perform(post("/create-no-responsestatus"))
                .andExpect(status().isOk());
    }
}