package com.lancaster.symptomchapter.service;

import com.lancaster.symptomchapter.classify.KeywordSymptomClassifier;
import com.lancaster.symptomchapter.model.KeywordClassifiedSymptom;
import com.lancaster.symptomchapter.model.Symptom;
import com.lancaster.symptomchapter.repository.KeywordClassifiedSymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class KeywordClassificationServiceImpl implements KeywordClassificationService {

    @Autowired
    private KeywordClassifiedSymptomRepository repo;

    @Autowired
    private SymptomService symptomService;

    KeywordSymptomClassifier keywordSymptomClassifier = new KeywordSymptomClassifier();

    @Override
    public KeywordClassifiedSymptom saveClassifiedSymptom(KeywordClassifiedSymptom keywordClassifiedSymptom) {
        return repo.saveClassifiedSymptom(keywordClassifiedSymptom);
    }

    @Override
    public List<Map<String, String>> classifySymptom(String symptom) {
        // Save symptom
        Symptom symptomModel = new Symptom();
        symptomModel.setSymptom(symptom);
        int symptomId = 0;
        try {
            symptomId = symptomService.saveSymptom(symptomModel);
        } catch (Exception e) {
            System.out.println("Symptom already exists");
        }

        // Save classification
        List<List<Integer>> classificationIds;
        try {
            classificationIds = keywordSymptomClassifier.getClassificationId(symptom);
            for (int i = 0; i < classificationIds.get(0).size(); i++) {
                int chapterId = classificationIds.get(0).get(i);
                int subChapterId = classificationIds.get(1).get(i);
                KeywordClassifiedSymptom keywordClassifiedSymptom = new KeywordClassifiedSymptom();
                keywordClassifiedSymptom.setSymptomId(symptomId);
                keywordClassifiedSymptom.setChapterId(chapterId);
                keywordClassifiedSymptom.setSubchapterId(subChapterId);
                keywordClassifiedSymptom.setSecondsubId(0);
                saveClassifiedSymptom(keywordClassifiedSymptom);
            }

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Return classification
        try {
            return keywordSymptomClassifier.getClassificationDefinition(symptom);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
