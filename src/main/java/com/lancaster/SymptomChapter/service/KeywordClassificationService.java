package com.lancaster.SymptomChapter.service;

import com.lancaster.SymptomChapter.model.ClassifiedSymptom;

import java.util.List;
import java.util.Map;

public interface KeywordClassificationService {

    List<ClassifiedSymptom> fetchClassifiedSymptoms();

    ClassifiedSymptom saveClassifiedSymptom(ClassifiedSymptom classifiedSymptom);

    ClassifiedSymptom fetchClassifiedSymptomWitSymptomId(int symptomId);

    ClassifiedSymptom updateClassifiedSymptom(ClassifiedSymptom classifiedSymptom, int symptomId);

    List<Map<String, String>> classifySymptom(String symptom);

}
