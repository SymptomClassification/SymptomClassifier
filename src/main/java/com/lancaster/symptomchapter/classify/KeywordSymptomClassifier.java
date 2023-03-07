package com.lancaster.symptomchapter.classify;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class KeywordSymptomClassifier {

    public List<Map<String, String>> getClassificationDefinition(String symptom) throws IOException, InterruptedException {
        List<String> command = new ArrayList<>();
        command.add("python3");
        command.add("src/main/resources/scripts/keywordClassifyName.py");
        command.add(symptom);

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();

        BufferedReader bfr = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        List<Map<String, String>> output = new ArrayList<>();
        while ((line = bfr.readLine()) != null) {
            if (line.equals("[]")) {
                return Arrays.asList(Collections.singletonMap("result", "0"));
            }
            String[] chapters = line.split("\\], \\[");
            for (String chapter : chapters) {
                chapter = chapter.replaceAll("\\[|\\]", "");
                String[] subChapters = chapter.split(", ");
                if (subChapters.length == 1) {
                    Map<String, String> chapterMap = new HashMap<>();
                    chapterMap.put("chapterName", subChapters[0].replaceAll("\"", ""));
                    output.add(chapterMap);
                } else if (subChapters.length == 2) {
                    Map<String, String> chapterMap = new HashMap<>();
                    chapterMap.put("chapterName", subChapters[0].replaceAll("\"", ""));
                    chapterMap.put("subchapterName", subChapters[1].replaceAll("\"", ""));
                    output.add(chapterMap);
                }
            }
        }

        int exitCode = process.waitFor();
        if (exitCode == 0) {
            System.out.println("Output: " + output);
        } else {
            System.err.println("The process returned a non-zero exit code: " + exitCode);
        }

        return output;
    }


    public List<List<Integer>> getClassificationId(String symptom) throws IOException, InterruptedException {
        List<String> command = new ArrayList<>();
        command.add("python3");
        command.add("src/main/resources/scripts/keywordClassifyId.py");
        command.add(symptom);

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();

        BufferedReader bfr = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = bfr.readLine()) != null) {
            sb.append(line);
        }
        String json = sb.toString();

        ObjectMapper mapper = new ObjectMapper();
        List<List<Integer>> output = mapper.readValue(json, new TypeReference<>() {
        });

        int exitCode = process.waitFor();
        if (exitCode == 0) {
            System.out.println("Output: " + output);
        } else {
            System.err.println("The process returned a non-zero exit code: " + exitCode);
        }

        return output;
    }

}
