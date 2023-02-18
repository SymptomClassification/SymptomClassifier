package com.lancaster.SymptomChapter.service;

import com.lancaster.SymptomChapter.classify.SymptomClassifier;
import com.lancaster.SymptomChapter.model.Symptom;
import com.lancaster.SymptomChapter.repository.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class SymptomServiceImpl implements SymptomService {

    @Autowired
    private SymptomRepository repo;

    SymptomClassifier symptomClassifier = new SymptomClassifier();

    @Override
    public int saveSymptom(Symptom symptom) {
        return repo.saveSymptom(symptom);
    }

    @Override
    public List<Symptom> fetchSymptoms() {
        return repo.fetchSymptoms();
    }

    @Override
    public Symptom getSymptomWithId(int id) {
        return repo.fetchSymptomWithId(id).get();
    }

    @Override
    public Symptom updateSymptom(Symptom symptom, int id) {
        return repo.updateSymptom(symptom, id).get();
    }

    @Override
    public List<List<Integer>> getClassificationId(String symptom) {
        try {
            return symptomClassifier.getClassificationId(symptom);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
