package com.lancaster.SymptomChapter.service;

import com.lancaster.SymptomChapter.model.Chapter;

import java.util.List;
import java.util.Optional;

public interface ChapterService {

    List<Chapter> fetchChapters();

    void saveChapter(Chapter chapter);

    Chapter fetchChapterWithName(String name);

    Chapter updateChapter(Chapter chapter, int id);

}
