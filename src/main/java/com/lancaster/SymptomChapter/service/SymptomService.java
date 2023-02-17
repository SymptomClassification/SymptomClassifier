package com.lancaster.SymptomChapter.service;

import com.lancaster.SymptomChapter.model.Symptom;

import java.util.List;

public interface SymptomService {

    Symptom saveSymptom(Symptom symptom);

    List<Symptom> fetchSymptoms();

    Symptom getSymptomWithId(int id);

    Symptom updateSymptom(Symptom symptom, int id);
}
