package com.lancaster.SymptomChapter.service;

import java.util.List;

public interface SpacyClassificationService {
    List<String> classifySymptom(String symptom);
}
