package com.lancaster.SymptomChapter;

import com.lancaster.SymptomChapter.model.ClassifiedSymptom;
import com.lancaster.SymptomChapter.repository.ClassifiedSymptomRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DriverManager.class)
public class SymptomClassificationRepositoryTest {

    @Mock
    private Connection mockConnection;

    @Mock
    private PreparedStatement mockPreparedStatement;

    @InjectMocks
    private ClassifiedSymptomRepository classifiedSymptomRepository;

    @Test
    public void testSaveSymptom() throws SQLException {
        PowerMockito.mockStatic(DriverManager.class);
        PowerMockito.when(DriverManager.getConnection(anyString())).thenReturn(mockConnection);

        ClassifiedSymptom classifiedSymptom = new ClassifiedSymptom(1, 1, 1, 2, 3);

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeUpdate()).thenReturn(1);


        ClassifiedSymptom savedSymptom = classifiedSymptomRepository.saveClassifiedSymptom(classifiedSymptom);

        verify(mockConnection).prepareStatement("INSERT INTO classifiedsymptom (symptomId, chapterId, subchapterId, secondsubId) " +
                " VALUES (?, ?, ?, ?)");
        verify(mockPreparedStatement).setInt(1, 1);
        verify(mockPreparedStatement).setInt(2, 1);
        verify(mockPreparedStatement).setInt(3, 2);
        verify(mockPreparedStatement).setInt(4, 3);
        verify(mockPreparedStatement).executeUpdate();

        assertEquals(classifiedSymptom, savedSymptom);
    }


}
