package com.lancaster.SymptomChapter.service;

import com.lancaster.SymptomChapter.model.Chapter;

import java.util.List;

public interface ChapterService {

    List<Chapter> fetchChapters();

    int saveChapter(Chapter chapter);

    Chapter fetchChapterWithName(String name);

    Chapter updateChapter(Chapter chapter, int id);
}
