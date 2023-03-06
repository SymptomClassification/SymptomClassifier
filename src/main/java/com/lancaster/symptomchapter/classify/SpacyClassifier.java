package com.lancaster.symptomchapter.classify;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SpacyClassifier {

    public List<String> classifySymptom(String symptom) throws IOException, InterruptedException {
        List<String> command = new ArrayList<>();
        command.add("python3");
        command.add("target/spacyClassification/classify.py");
        command.add(symptom);

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();

        BufferedReader bfr = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        List<String> output = new ArrayList<>();

        while ((line = bfr.readLine()) != null) {
            output.add(line);
        }

        int exitCode = process.waitFor();
        if (exitCode == 0) {
            System.out.println("Output: " + output);
        } else {
            System.err.println("The process returned a non-zero exit code: " + exitCode);
        }

        return output;
    }

}
