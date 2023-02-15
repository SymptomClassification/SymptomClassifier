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
@RequestMapping("/classifiedSymptoms")
public class SymptomClassificationController {

    @Autowired
    private SymptomClassificationService service;

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
    public ResponseEntity<List<String>> classifySymptom(@PathVariable("name") String name) {
        List<String> c = service.classifySymptom(name);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

    @RequestMapping(value = "printHelloWorld", method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<String>> printHelloWorld() {
        List<String> c = service.printHelloWorld();
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

}
