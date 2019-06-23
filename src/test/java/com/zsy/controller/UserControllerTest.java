package com.zsy.controller;

import com.zsy.BaseApplicationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class UserControllerTest extends BaseApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void accessLoginPage() throws Exception{
        this.mockMvc.perform(get("/login.html"))
                .andExpect(status().is3xxRedirection());
    }
}
