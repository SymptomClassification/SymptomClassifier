package com.lancaster.SymptomChapter.service;

import com.lancaster.SymptomChapter.model.SpacyChapters;

import java.util.List;

public interface SpacyClassificationService {
    List<String> classifySymptom(String symptom);

    List<SpacyChapters> fetchSpacyChapters();
}
