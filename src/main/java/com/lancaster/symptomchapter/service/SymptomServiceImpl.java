package com.lancaster.symptomchapter.service;

import com.lancaster.symptomchapter.classify.KeywordSymptomClassifier;
import com.lancaster.symptomchapter.model.Symptom;
import com.lancaster.symptomchapter.repository.SymptomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SymptomServiceImpl implements SymptomService {

    @Autowired
    private SymptomRepository repo;

    KeywordSymptomClassifier keywordSymptomClassifier = new KeywordSymptomClassifier();

    @Override
    public int saveSymptom(Symptom symptom) {
        return repo.saveSymptom(symptom);
    }

}
