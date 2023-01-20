package com.lancaster.SymptomChapter.controller;


import com.lancaster.SymptomChapter.model.Chapter;
import com.lancaster.SymptomChapter.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/chapters")
public class ChapterController {

    @Autowired
    private ChapterService service;

    @GetMapping()
    public List<Chapter> fetchChapters() {
        return service.fetchChapters();
    }

}
