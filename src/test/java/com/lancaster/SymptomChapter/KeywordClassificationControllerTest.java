package com.lancaster.SymptomChapter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lancaster.SymptomChapter.controller.KeywordClassificationController;
import com.lancaster.SymptomChapter.model.ClassifiedSymptom;
import com.lancaster.SymptomChapter.service.KeywordClassificationService;
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
import static org.mockito.Mockito.verify;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;



@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(controllers = KeywordClassificationController.class)
public class KeywordClassificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private KeywordClassificationController classifiedSymptomController;

    @Mock
    private KeywordClassificationService service;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(classifiedSymptomController).build();
    }

    @Test
    public void testFindClassifiedSymptomWithName() throws Exception {
        ClassifiedSymptom symptom = new ClassifiedSymptom(4, 1, 2, 2, 2);
        given(service.fetchClassifiedSymptomWitSymptomId(1)).willReturn(symptom);
        mockMvc.perform(get("/classifiedSymptoms/classifiedSymptom/{id}", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateClassifiedSymptom() throws Exception {

        ClassifiedSymptom symptom = new ClassifiedSymptom(1, 1, 1, 2, 3);

        ClassifiedSymptom mockClassifiedSymptom = new ClassifiedSymptom(1, 1, 1, 2, 3);

        mockMvc.perform(put("/classifiedSymptoms/update/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(symptom)))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.name", is("Fever")))
//                .andExpect(jsonPath("$.chapterId", is(1)))
//                .andExpect(jsonPath("$.subchapterId", is(2)))
//                .andExpect(jsonPath("$.secondsubId", is(3)));

//        verify(service, times(1)).updateClassifiedSymptom(symptom, "Fever");
//        verifyNoMoreInteractions(service);

    }

    @Test
    public void testClassifySymptom() throws Exception {
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
