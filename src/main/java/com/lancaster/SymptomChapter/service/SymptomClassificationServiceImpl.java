package com.lancaster.SymptomChapter.service;

import com.lancaster.SymptomChapter.classify.SymptomClassifier;
import com.lancaster.SymptomChapter.model.ClassifiedSymptom;
import com.lancaster.SymptomChapter.repository.ClassifiedSymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SymptomClassificationServiceImpl implements SymptomClassificationService {

    @Autowired
    private ClassifiedSymptomRepository repo;

    SymptomClassifier symptomClassifier = new SymptomClassifier();


    @Override
    public List<ClassifiedSymptom> fetchClassifiedSymptoms() {
        return repo.fetchSymptoms();
    }

    @Override
    public ClassifiedSymptom saveClassifiedSymptom(ClassifiedSymptom classifiedSymptom) {
        return repo.saveSymptom(classifiedSymptom);
    }

    @Override
    public ClassifiedSymptom fetchClassifiedSymptomWitSymptomId(int symptomId) {
        return repo.fetchSymptomWithSymptomId(symptomId).get();
    }

    @Override
    public ClassifiedSymptom updateClassifiedSymptom(ClassifiedSymptom classifiedSymptom, int symptomId) {
        return repo.updateSymptom(classifiedSymptom, symptomId).get();
    }

    @Override
    public List<String> classifySymptom(String symptom) {
        try {
            return symptomClassifier.classifyInput(symptom);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<String> printHelloWorld() {
        try {
            return symptomClassifier.printHelloWorld();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
