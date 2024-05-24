package com.org.LearningNavigator.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


public class EasterEggServiceTest {

    @InjectMocks
    private EasterEggService easterEggService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetRandomFactAboutNumber() {
        int number = 5;
        String expectedResponse = "5 is the number of fingers on a human hand.";
        when(restTemplate.getForObject("http://numbersapi.com/" + number, String.class)).thenReturn(expectedResponse);

        String actualResponse = easterEggService.getRandomFactAboutNumber(number);

        assertEquals(expectedResponse, actualResponse);
    }
}

