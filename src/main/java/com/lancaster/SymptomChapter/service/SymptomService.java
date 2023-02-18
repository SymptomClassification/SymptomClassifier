package com.lancaster.SymptomChapter.service;

import com.lancaster.SymptomChapter.model.Symptom;

import java.io.IOException;
import java.util.List;

public interface SymptomService {

    int saveSymptom(Symptom symptom);

    List<Symptom> fetchSymptoms();

    Symptom getSymptomWithId(int id);

    Symptom updateSymptom(Symptom symptom, int id);

    List<List<Integer>> getClassificationId(String symptom);
}
