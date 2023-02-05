package com.lancaster.SymptomChapter.service;

import com.lancaster.SymptomChapter.classify.SymptomClassifier;
import com.lancaster.SymptomChapter.model.ClassifiedSymptom;
import com.lancaster.SymptomChapter.repository.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
// test for git web hook
@Service
public class SymptomClassificationServiceImpl implements SymptomClassificationService {

    @Autowired
    private SymptomRepository repo;

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
    public ClassifiedSymptom fetchClassifiedSymptomWithName(String name) {
        return repo.fetchSymptomWithName(name).get();
    }

    @Override
    public ClassifiedSymptom updateClassifiedSymptom(ClassifiedSymptom classifiedSymptom, String name) {
        return repo.updateSymptom(classifiedSymptom, name).get();
    }

    @Override
    public List<String> classifySymptom(String symptom) {
        return symptomClassifier.parseResult(symptom);
    }
}
