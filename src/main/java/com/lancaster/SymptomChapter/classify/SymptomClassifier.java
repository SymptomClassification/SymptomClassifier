package com.lancaster.SymptomChapter.classify;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SymptomClassifier {

    public List<String> classifyInput(String symptom) throws IOException, InterruptedException {
        List<String> command = new ArrayList<>();
        command.add("python2.7");
        command.add("target/keywordClassify.py");
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

    public List<String> printHelloWorld(String arg) throws IOException, InterruptedException {
        List<String> command = new ArrayList<>();
        command.add("python2.7");
        command.add("target/hello_world.py");
        command.add(arg);

        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process process = processBuilder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        List<String> output = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
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
