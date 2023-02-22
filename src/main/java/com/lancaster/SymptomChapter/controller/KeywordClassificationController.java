package com.lancaster.SymptomChapter.controller;

import com.lancaster.SymptomChapter.model.ClassifiedSymptom;
import com.lancaster.SymptomChapter.service.KeywordClassificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/classifiedSymptoms")
public class KeywordClassificationController {

    @Autowired
    private KeywordClassificationService service;

    @RequestMapping()
    public List<ClassifiedSymptom> fetchClassifiedSymptoms() {
        return service.fetchClassifiedSymptoms();
    }

    @RequestMapping(value = "create", method = RequestMethod.POST, produces = {
            MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClassifiedSymptom> createClassifiedSymptom(@Validated @RequestBody ClassifiedSymptom symptom) {
        return new ResponseEntity<>(service.saveClassifiedSymptom(symptom), HttpStatus.OK);
    }

    @RequestMapping(value = "classifiedSymptom/{id}", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ClassifiedSymptom> findClassifiedSymptomWithName(@PathVariable("id") int symptomId) {
        return new ResponseEntity<>(service.fetchClassifiedSymptomWitSymptomId(symptomId), HttpStatus.OK);
    }

    @RequestMapping(value = "update/{id}", method = RequestMethod.PUT, produces = {
            MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ClassifiedSymptom> updateClassifiedSymptom(@RequestBody ClassifiedSymptom symptom, @PathVariable("id") int symptomId) {
        ClassifiedSymptom c = service.updateClassifiedSymptom(symptom, symptomId);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @RequestMapping(value = "classifySymptom/{name}", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Map<String, String>>> classifySymptom(@PathVariable("name") String name) {
        List<Map<String, String>> c = service.classifySymptom(name);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

}
