package com.lancaster.symptomchapter.service;

import com.lancaster.symptomchapter.model.SpacyChapters;

import java.util.List;

public interface SpacyClassificationService {
    List<String> classifySymptom(String symptom);

    List<SpacyChapters> fetchSpacyChapters();
}
