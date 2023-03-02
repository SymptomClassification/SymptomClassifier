package com.lancaster.SymptomChapter.service;

import com.lancaster.SymptomChapter.classify.SymptomClassifier;
import com.lancaster.SymptomChapter.model.KeywordClassifiedSymptom;
import com.lancaster.SymptomChapter.model.Symptom;
import com.lancaster.SymptomChapter.repository.ClassifiedSymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class KeywordClassificationServiceImpl implements KeywordClassificationService {

    @Autowired
    private ClassifiedSymptomRepository repo;

    @Autowired
    private SymptomService symptomService;

    SymptomClassifier symptomClassifier = new SymptomClassifier();


    @Override
    public List<KeywordClassifiedSymptom> fetchClassifiedSymptoms() {
        return repo.fetchClassifiedSymptoms();
    }

    @Override
    public KeywordClassifiedSymptom saveClassifiedSymptom(KeywordClassifiedSymptom keywordClassifiedSymptom) {
        return repo.saveClassifiedSymptom(keywordClassifiedSymptom);
    }

    @Override
    public KeywordClassifiedSymptom fetchClassifiedSymptomWitSymptomId(int symptomId) {
        return repo.fetchClassifiedSymptomWithSymptomId(symptomId).get();
    }

    @Override
    public KeywordClassifiedSymptom updateClassifiedSymptom(KeywordClassifiedSymptom keywordClassifiedSymptom, int symptomId) {
        return repo.updateClassifiedSymptom(keywordClassifiedSymptom, symptomId).get();
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
            classificationIds = symptomClassifier.getClassificationId(symptom);
            for (List<Integer> ids : classificationIds) {
                int chapterId = ids.get(0);
                int subChapterId = ids.get(1);
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
            return symptomClassifier.getClassificationDefinition(symptom);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
