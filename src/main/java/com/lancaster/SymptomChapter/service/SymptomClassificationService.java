package com.lancaster.SymptomChapter.service;

import com.lancaster.SymptomChapter.model.ClassifiedSymptom;

import java.util.List;

public interface SymptomClassificationService {

    List<ClassifiedSymptom> fetchClassifiedSymptoms();

    ClassifiedSymptom saveClassifiedSymptom(ClassifiedSymptom classifiedSymptom);

    ClassifiedSymptom fetchClassifiedSymptomWitSymptomId(int symptomId);

    ClassifiedSymptom updateClassifiedSymptom(ClassifiedSymptom classifiedSymptom, int symptomId);

    List<String> classifySymptom(String symptom);

    List<String> printHelloWorld(String arg);

}
