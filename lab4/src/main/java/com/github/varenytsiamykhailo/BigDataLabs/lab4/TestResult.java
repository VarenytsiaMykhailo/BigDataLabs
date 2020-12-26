package com.github.varenytsiamykhailo.BigDataLabs.lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class TestResult {

    private final String TEST_NAME = "testName";
    private final String EXPECTED_RESULT = "expectedResult";
    private final String PARAMS = "params";
    private final String IS_SUCCESSFUL = "isSuccessful";

    private String testName;

    private String expectedResult;

    private ArrayList<Integer> params;

    private boolean isSuccessfulTest;

    public TestResult(String testName, String expectedResult, ArrayList<Integer> params, boolean isSuccessfulTest) {
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.params = params;
        this.isSuccessfulTest = isSuccessfulTest;
    }

    @JsonCreator
    public TestResult(@JsonProperty(TEST_NAME) String testName, @JsonProperty(EXPECTED_RESULT) String expectedResult, @JsonProperty("params") ArrayList<Integer> params) {
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.params = params;
    }

    public String getTestName() {
        return testName;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public ArrayList<Integer> getParams() {
        return params;
    }

    public boolean isSuccessfulTest() {
        return isSuccessfulTest;
    }
}
