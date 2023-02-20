package com.lancaster.SymptomChapter.service;

import com.lancaster.SymptomChapter.classify.SpacyClassifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SpacyClassificationServiceImpl implements SpacyClassificationService {

    SpacyClassifier spacyClassifier = new SpacyClassifier();

    @Override
    public List<String> classifySymptom(String symptom) {
        try {
            return spacyClassifier.classifySymptom(symptom);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
