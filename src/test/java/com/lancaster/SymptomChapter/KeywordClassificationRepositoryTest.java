package com.lancaster.SymptomChapter;

import com.lancaster.SymptomChapter.model.KeywordClassifiedSymptom;
import com.lancaster.SymptomChapter.repository.KeywordClassifiedSymptomRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

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

    @Test
    public void testUpdateClassifiedSymptom() {
        KeywordClassifiedSymptom keywordClassifiedSymptom = new KeywordClassifiedSymptom(1, 1, 1, 2, 3);
        when(mockRepository.fetchClassifiedSymptomWithSymptomId(1)).thenReturn(Optional.of(keywordClassifiedSymptom));

        KeywordClassifiedSymptom updatedSymptom = new KeywordClassifiedSymptom(2, 2, 2, 3, 4);
        when(mockRepository.updateClassifiedSymptom(updatedSymptom, 1)).thenReturn(Optional.of(updatedSymptom));

        Optional<KeywordClassifiedSymptom> savedSymptom = mockRepository.updateClassifiedSymptom(updatedSymptom, 1);

        verify(mockRepository).updateClassifiedSymptom(updatedSymptom, 1);

        assertTrue(savedSymptom.isPresent());
        assertEquals(updatedSymptom, savedSymptom.get());
        assertEquals(2, savedSymptom.get().getSymptomId());
        assertEquals(2, savedSymptom.get().getChapterId());
    }

}
