package com.lancaster.SymptomChapter.controller;


import com.lancaster.SymptomChapter.model.Chapter;
import com.lancaster.SymptomChapter.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chapters")
public class ChapterController {

    @Autowired
    private ChapterService service;

    @GetMapping()
    public List<Chapter> fetchChapters() {
        return service.fetchChapters();
    }

    @RequestMapping(value = "create", method = RequestMethod.POST, produces = {
            MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void createChapter(@Validated @RequestBody Chapter chapter) {
        service.saveChapter(chapter);
    }

    @RequestMapping(value = "chapter/{name}", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Chapter> findChapterWithName(@PathVariable("name") String name) {
        return new ResponseEntity<>(service.fetchChapterWithName(name), HttpStatus.OK);
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.PUT, produces = {
            MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Chapter> updateChapter(@RequestBody Chapter chapter, @PathVariable("id") int id) {
        Chapter c = service.updateChapter(chapter, id);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }


}
