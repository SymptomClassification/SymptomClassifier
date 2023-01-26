package com.lancaster.SymptomChapter.service;

import com.lancaster.SymptomChapter.model.Chapter;
import com.lancaster.SymptomChapter.repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterRepository repo;

    @Override
    public List<Chapter> fetchChapters() {
        return repo.fetchChapters();
    }

    @Override
    public void saveChapter(Chapter chapter) {
        repo.saveChapter(chapter);
    }

    @Override
    public Chapter fetchChapterWithName(String name) {
        return repo.fetchChapterWithName(name).get();
    }

    @Override
    public Chapter updateChapter(Chapter chapter, int id) {
        return repo.updateChapter(chapter, id).get();
    }

}
