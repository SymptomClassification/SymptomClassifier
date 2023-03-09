package com.lancaster.symptomchapter.service;

import com.lancaster.symptomchapter.model.SpacyChapters;

import java.util.List;
import java.util.Map;

public interface SpacyClassificationService {
    List<Map<String, String>> classifySymptom(String symptom);

    List<SpacyChapters> fetchSpacyChapters();

}
