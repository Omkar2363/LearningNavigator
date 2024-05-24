package com.org.LearningNavigator.controllers;

import com.org.LearningNavigator.services.EasterEggService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(EasterEggController.class)
public class EasterEggControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EasterEggService easterEggService;

    @InjectMocks
    private EasterEggController easterEggController;

    @Test
    public void testGetRandomFactAboutNumber() throws Exception {
        int number = 75;
        String expectedResponse = "75 is the age in years that the Saguaro Cactus, found in southwestern US, must be to grow branches.";

        when(easterEggService.getRandomFactAboutNumber(number)).thenReturn(expectedResponse);

        mockMvc.perform(get("/hidden-feature/{number}", number))
                .andExpect(status().isOk())
                .andExpect(content().string(expectedResponse));
    }
}