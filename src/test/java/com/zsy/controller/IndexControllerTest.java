package com.zsy.controller;

import com.zsy.BaseApplicationTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

public class IndexControllerTest extends BaseApplicationTest {

    private MockMvc mockMvc;

    @Before
    void setUp(WebApplicationContext wac){
        this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    @Test
    void accessIndexWithoutLogin(){
        this.mockMvc.perform(get("/")
                .accept(MediaType.APPLICATION_FORM_URLENCODED));
    }
}
