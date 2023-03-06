package com.lancaster.symptomchapter.service;

import com.lancaster.symptomchapter.model.KeywordClassifiedSymptom;

import java.util.List;
import java.util.Map;

public interface KeywordClassificationService {

    KeywordClassifiedSymptom saveClassifiedSymptom(KeywordClassifiedSymptom keywordClassifiedSymptom);

    List<Map<String, String>> classifySymptom(String symptom);

}
