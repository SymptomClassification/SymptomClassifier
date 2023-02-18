package com.lancaster.SymptomChapter.service;

import com.lancaster.SymptomChapter.classify.SymptomClassifier;
import com.lancaster.SymptomChapter.model.ClassifiedSymptom;
import com.lancaster.SymptomChapter.model.Symptom;
import com.lancaster.SymptomChapter.repository.ClassifiedSymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SymptomClassificationServiceImpl implements SymptomClassificationService {

    @Autowired
    private ClassifiedSymptomRepository repo;

    @Autowired
    private SymptomService symptomService;

    SymptomClassifier symptomClassifier = new SymptomClassifier();


    @Override
    public List<ClassifiedSymptom> fetchClassifiedSymptoms() {
        return repo.fetchClassifiedSymptoms();
    }

    @Override
    public ClassifiedSymptom saveClassifiedSymptom(ClassifiedSymptom classifiedSymptom) {
        return repo.saveClassifiedSymptom(classifiedSymptom);
    }

    @Override
    public ClassifiedSymptom fetchClassifiedSymptomWitSymptomId(int symptomId) {
        return repo.fetchClassifiedSymptomWithSymptomId(symptomId).get();
    }

    @Override
    public ClassifiedSymptom updateClassifiedSymptom(ClassifiedSymptom classifiedSymptom, int symptomId) {
        return repo.updateClassifiedSymptom(classifiedSymptom, symptomId).get();
    }

    @Override
    public List<String> classifySymptom(String symptom) {
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
                System.out.println("Chapter: " + chapterId + " Subchapter: " + subChapterId);

                ClassifiedSymptom classifiedSymptom = new ClassifiedSymptom();
                classifiedSymptom.setSymptomId(symptomId);
                classifiedSymptom.setChapterId(chapterId);
                classifiedSymptom.setSubchapterId(subChapterId);
                classifiedSymptom.setSecondsubId(0);
                saveClassifiedSymptom(classifiedSymptom);
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
