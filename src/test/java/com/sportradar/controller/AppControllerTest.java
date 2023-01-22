package com.sportradar.controller;

import com.sportradar.model.Event;
import com.sportradar.service.EventService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class AppControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EventService eventService;

    @Test
    public void testPrintInformation() throws Exception {
        // Arrange
        List<Event> expectedEvents = Arrays.asList(new Event(), new Event());
        when(eventService.returnMostProbableResults()).thenReturn(expectedEvents);

        // Act
        MvcResult result = mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn();

        // Assert
        assertEquals(expectedEvents, result.getResponse().getContentAsString());
    }
}