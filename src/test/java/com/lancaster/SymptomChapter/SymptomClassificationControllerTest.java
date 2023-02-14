package com.lancaster.SymptomChapter;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.lancaster.SymptomChapter.controller.SymptomClassificationController;
import com.lancaster.SymptomChapter.model.ClassifiedSymptom;
import com.lancaster.SymptomChapter.service.SymptomClassificationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(controllers = SymptomClassificationController.class)
public class SymptomClassificationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private SymptomClassificationController classifiedSymptomController;

    @Mock
    private SymptomClassificationService service;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(classifiedSymptomController).build();
    }

    @Test
    public void testFindClassifiedSymptomWithName() throws Exception {
        ClassifiedSymptom symptom = new ClassifiedSymptom(4, "symptom2.1", 2, 2, 2);
        given(service.fetchClassifiedSymptomWitSymptomId("symptom2.1")).willReturn(symptom);
        mockMvc.perform(get("/api/v1/classifiedSymptoms/findClassifiedSymptomWithName/{name}", "symptom2.1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateClassifiedSymptom() throws Exception {

        ClassifiedSymptom symptom = new ClassifiedSymptom(1, "Fever", 1, 2, 3);

        ClassifiedSymptom mockClassifiedSymptom = new ClassifiedSymptom(1, "Fever", 1, 2, 3);

        mockMvc.perform(put("/api/v1/classifiedSymptoms/updateClassifiedSymptom/{name}", "Fever")
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

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
