package com.lancaster.symptomchapter;

import com.lancaster.symptomchapter.model.KeywordClassifiedSymptom;
import com.lancaster.symptomchapter.repository.KeywordClassifiedSymptomRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class KeywordClassificationRepositoryTest {

    @Mock
    private KeywordClassifiedSymptomRepository mockRepository;

    @Test
    public void testSaveSymptom() {
        KeywordClassifiedSymptom keywordClassifiedSymptom = new KeywordClassifiedSymptom(1, 1, 1, 2, 3);
        when(mockRepository.saveClassifiedSymptom(keywordClassifiedSymptom)).thenReturn(keywordClassifiedSymptom);

        KeywordClassifiedSymptom savedSymptom = mockRepository.saveClassifiedSymptom(keywordClassifiedSymptom);

        verify(mockRepository).saveClassifiedSymptom(keywordClassifiedSymptom);

        assertEquals(keywordClassifiedSymptom, savedSymptom);
        assertEquals(1, savedSymptom.getSymptomId());
    }


}
