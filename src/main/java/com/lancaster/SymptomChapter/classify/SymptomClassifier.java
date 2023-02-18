package com.lancaster.SymptomChapter.classify;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SymptomClassifier {

    public List<String> getClassificationDefinition(String symptom) throws IOException, InterruptedException {
        List<String> command = new ArrayList<>();
        command.add("python3");
        command.add("target/keywordClassifyName.py");
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

    public List<List<Integer>> getClassificationId(String symptom) throws IOException, InterruptedException {
        List<String> command = new ArrayList<>();
        command.add("python3");
        command.add("target/keywordClassifyId.py");
        command.add(symptom);

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream(true);

        Process process = processBuilder.start();

        BufferedReader bfr = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        List<List<Integer>> output = new ArrayList<>();

        while ((line = bfr.readLine()) != null) {
            List<Integer> row = new ArrayList<>();
            try {
                String[] tokens = line.replaceAll("\\[|\\]|\\s", "").split(",");
                for (int i = 0; i < tokens.length; i += 2) {
                    int value1 = Integer.parseInt(tokens[i]);
                    int value2 = Integer.parseInt(tokens[i+1]);
                    row.add(value1);
                    row.add(value2);
                    output.add(new ArrayList<>(row));
                    row.clear();
                }
            } catch (NumberFormatException e) {
                // skip this line and continue with the next one
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

}
