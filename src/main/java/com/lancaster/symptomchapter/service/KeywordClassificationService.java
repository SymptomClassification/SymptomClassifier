package com.lancaster.symptomchapter.service;

import java.util.List;
import java.util.Map;

public interface KeywordClassificationService {

    List<Map<String, String>> classifySymptom(String symptom);

}
