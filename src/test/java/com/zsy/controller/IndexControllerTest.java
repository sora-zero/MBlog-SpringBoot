package com.zsy.controller;

import com.zsy.BaseApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class IndexControllerTest extends BaseApplicationTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    public void accessIndexWithoutLogin() throws Exception{
        this.mockMvc.perform(get("/")
                .accept(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection());
    }
}
