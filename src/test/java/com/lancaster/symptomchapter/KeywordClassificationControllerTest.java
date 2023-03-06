package com.lancaster.symptomchapter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lancaster.symptomchapter.controller.KeywordClassificationController;
import com.lancaster.symptomchapter.service.KeywordClassificationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(controllers = KeywordClassificationController.class)
public class KeywordClassificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private KeywordClassificationController controller;

    @Mock
    private KeywordClassificationService service;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testKeywordClassification() throws Exception {
        String name = "someName";

        // Mock the service response
        Map<String, String> result = new HashMap<>();
        result.put("chapterName", "head");
        result.put("subchapterName", "General");
        List<Map<String, String>> responseList = Arrays.asList(result);
        when(service.classifySymptom(name)).thenReturn(responseList);

        // Perform the request
        mockMvc.perform(get("/classifiedSymptoms/classifySymptom/{name}", name)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].chapterName").value("head"))
                .andExpect(jsonPath("$[0].subchapterName").value("General"));

        // Verify the service was called with the correct argument
        verify(service).classifySymptom(name);
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
