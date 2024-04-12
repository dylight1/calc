package ru.neoflex.practice.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@WebMvcTest(CalcController.class)
@AutoConfigureMockMvc
class CalcControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CalcController calcController;

    @Test
    public void Sum() throws Exception {

        int a = 5;
        int b = 3;
        int expectedSum = a+b;

        BDDMockito.given(calcController.Sum(a,b))
                .willReturn(a+b);

        mockMvc.perform(MockMvcRequestBuilders.get("/plus/{a}/{b}",a, b))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(expectedSum)));
    }

    @Test
    public void Diff() throws Exception {

        int a = 40;
        int b = 13;
        int expectedDiff = a-b;

        BDDMockito.given(calcController.Diff(a,b))
                .willReturn(a-b);

        mockMvc.perform(MockMvcRequestBuilders.get("/minus/{a}/{b}", a, b))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(expectedDiff)));
    }
}