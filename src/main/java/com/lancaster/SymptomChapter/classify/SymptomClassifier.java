package com.lancaster.SymptomChapter.classify;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SymptomClassifier {

    public List<String> classifyInput(String symptom) throws IOException {
        String path = "target/keywordClassify.py";
        ProcessBuilder processBuilder = new ProcessBuilder("python2.7", path, "--arg1", symptom);
        Process process = processBuilder.start();
        processBuilder.redirectErrorStream(true);

        BufferedReader bfr = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line = "";
        List<String> output = new ArrayList<>();

        while ((line = bfr.readLine()) != null) {
            output.add(line);
        }
        bfr.close();

        return output;
    }

}
