package com.lancaster.SymptomChapter.controller;

import com.lancaster.SymptomChapter.model.ClassifiedSymptom;
import com.lancaster.SymptomChapter.service.SymptomClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/classifiedSymptoms")
public class SymptomClassificationController {

    @Autowired
    private SymptomClassificationService service;

    @RequestMapping("fetchClassifiedSymptoms")
    public List<ClassifiedSymptom> fetchClassifiedSymptoms() {
        return service.fetchClassifiedSymptoms();
    }

    @RequestMapping(value = "createClassifiedSymptom", method = RequestMethod.POST, produces = {
            MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public void createClassifiedSymptom(@Validated @RequestBody ClassifiedSymptom symptom) {
        service.saveClassifiedSymptom(symptom);
    }

    @RequestMapping(value = "findClassifiedSymptomWithName/{name}", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ClassifiedSymptom> findClassifiedSymptomWithName(@PathVariable("name") String name) {
        return new ResponseEntity<>(service.fetchClassifiedSymptomWithName(name), HttpStatus.OK);
    }

    @RequestMapping(value = "updateClassifiedSymptom/{name}", method = RequestMethod.PUT, produces = {
            MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ClassifiedSymptom> updateClassifiedSymptom(@RequestBody ClassifiedSymptom symptom, @PathVariable("name") String name) {
        ClassifiedSymptom c = service.updateClassifiedSymptom(symptom, name);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }


}
