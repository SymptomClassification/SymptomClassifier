package com.lancaster.symptomchapter.service;

import com.lancaster.symptomchapter.classify.SpacyClassifier;
import com.lancaster.symptomchapter.model.SpacyChapters;
import com.lancaster.symptomchapter.model.SpacyClassifiedSymptom;
import com.lancaster.symptomchapter.model.Symptom;
import com.lancaster.symptomchapter.repository.SpacyClassificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class SpacyClassificationServiceImpl implements SpacyClassificationService {

    private final SpacyClassifier spacyClassifier = new SpacyClassifier();

    @Autowired
    private SpacyClassificationRepository repo;

    @Autowired
    private SymptomService symptomService;


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

        List<Map<String, String>> classificationDefinitions;
        try {
            classificationDefinitions = spacyClassifier.classifySymptom(symptom);
            for (int i = 0; i < classificationDefinitions.size(); i++) {
                String chapterName = classificationDefinitions.get(i).get("ChapterName");
                String subchapterName = classificationDefinitions.get(i).get("SubchapterName");

                SpacyClassifiedSymptom spacyClassifiedSymptom = new SpacyClassifiedSymptom();
                spacyClassifiedSymptom.setSymptomId(symptomId);
                spacyClassifiedSymptom.setChapter(chapterName);
                spacyClassifiedSymptom.setSubchapter(subchapterName);
                repo.saveSpacyClassifiedSymptom(spacyClassifiedSymptom);
            }
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }


        return classificationDefinitions;
    }

    @Override
    public List<SpacyChapters> fetchSpacyChapters() {
        return repo.fetchSpacyChapters();
    }
}
