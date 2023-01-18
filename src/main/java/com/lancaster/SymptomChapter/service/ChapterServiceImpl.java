package com.lancaster.SymptomChapter.service;

import com.lancaster.SymptomChapter.model.Chapter;
import com.lancaster.SymptomChapter.repository.ChapterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterServiceImpl implements ChapterService {

    @Autowired
    private ChapterRepository repo;

    @Override
    public List<Chapter> fetchChapters() {
        return repo.fetchAllRooms();
    }
}
