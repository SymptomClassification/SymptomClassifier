package com.lancaster.SymptomChapter.classify;

import org.python.core.PyObject;
import org.python.util.PythonInterpreter;

import java.util.Arrays;
import java.util.List;

public class SymptomClassifier {
    PythonInterpreter interpreter = new PythonInterpreter();

    public String[] classifySymptom(String symptom) {
        interpreter.execfile("src/main/resources/scripts/keywordClassify.py");
        interpreter.exec("result = pipeline(\"+\"'\"+" + symptom + "\"'\"+\")");
        PyObject result = interpreter.get("result");
        return result.toString().split(",");
    }

    public List<String> parseResult(String symptom) {
        String[] result = classifySymptom(symptom);
        String chapterName = result[0];
        String subChapterName = result[1];
        String chapterId = result[2];
        String subChapterId = result[3];
        return Arrays.asList(chapterName, subChapterName, chapterId, subChapterId);
    }
}
