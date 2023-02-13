package com.lancaster.SymptomChapter.service;

import com.lancaster.SymptomChapter.model.ClassifiedSymptom;

import java.util.List;

public interface SymptomClassificationService {

    List<ClassifiedSymptom> fetchClassifiedSymptoms();

    ClassifiedSymptom saveClassifiedSymptom(ClassifiedSymptom classifiedSymptom);

    ClassifiedSymptom fetchClassifiedSymptomWithName(String name);

    ClassifiedSymptom updateClassifiedSymptom(ClassifiedSymptom classifiedSymptom, String name);

    List<String> classifySymptom(String symptom);

}
