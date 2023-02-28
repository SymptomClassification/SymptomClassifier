package com.lancaster.SymptomChapter.service;

import com.lancaster.SymptomChapter.classify.SpacyClassifier;
import com.lancaster.SymptomChapter.model.SpacyChapters;
import com.lancaster.SymptomChapter.repository.SpacyClassificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SpacyClassificationServiceImpl implements SpacyClassificationService {

    private final SpacyClassifier spacyClassifier = new SpacyClassifier();

    @Autowired
    private SpacyClassificationRepository repo;

    @Override
    public List<String> classifySymptom(String symptom) {
        try {
            return spacyClassifier.classifySymptom(symptom);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<SpacyChapters> fetchSpacyChapters() {
        return repo.fetchSpacyChapters();
    }
}
