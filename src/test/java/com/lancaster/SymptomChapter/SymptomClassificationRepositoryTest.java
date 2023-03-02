package com.lancaster.SymptomChapter;

import com.lancaster.SymptomChapter.model.ClassifiedSymptom;
import com.lancaster.SymptomChapter.repository.ClassifiedSymptomRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SymptomClassificationRepositoryTest {

    @Mock
    private ClassifiedSymptomRepository mockRepository;

    @Test
    public void testSaveSymptom() {
        ClassifiedSymptom classifiedSymptom = new ClassifiedSymptom(1, 1, 1, 2, 3);
        when(mockRepository.saveClassifiedSymptom(classifiedSymptom)).thenReturn(classifiedSymptom);

        ClassifiedSymptom savedSymptom = mockRepository.saveClassifiedSymptom(classifiedSymptom);

        verify(mockRepository).saveClassifiedSymptom(classifiedSymptom);

        assertEquals(classifiedSymptom, savedSymptom);
    }


}
