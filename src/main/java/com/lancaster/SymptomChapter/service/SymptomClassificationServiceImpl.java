package com.lancaster.SymptomChapter.service;

import com.lancaster.SymptomChapter.model.ClassifiedSymptom;
import com.lancaster.SymptomChapter.repository.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SymptomClassificationServiceImpl implements SymptomClassificationService {

    @Autowired
    private SymptomRepository repo;


    @Override
    public List<ClassifiedSymptom> fetchClassifiedSymptoms() {
        return repo.fetchSymptoms();
    }

    @Override
    public void saveClassifiedSymptom(ClassifiedSymptom classifiedSymptom) {
        repo.saveSymptom(classifiedSymptom);
    }

    @Override
    public ClassifiedSymptom fetchClassifiedSymptomWithName(String name) {
        return repo.fetchSymptomWithName(name).get();
    }

    @Override
    public ClassifiedSymptom updateClassifiedSymptom(ClassifiedSymptom classifiedSymptom, String name) {
        return repo.updateSymptom(classifiedSymptom, name).get();
    }
}
