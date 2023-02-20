package com.lancaster.SymptomChapter.controller;

import com.lancaster.SymptomChapter.service.SpacyClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/spacy")
public class SpacyClassificationController {

    @Autowired
    private SpacyClassificationService service;

    @RequestMapping(value = "classifySymptom/{name}", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<String>> classifySymptom(@PathVariable("name") String name) {
        List<String> c = service.classifySymptom(name);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

}
