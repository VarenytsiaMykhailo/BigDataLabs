package com.github.varenytsiamykhailo.BigDataLabs.lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class TestResult {

    private final String TEST_NAME = "testName";
    private final String EXPECTED_RESULT = "expectedResult";
    private final String TEST_PARAMS = "params";
    private final String IS_SUCCESSFUL_TEST = "isSuccessfulTest";

    private String testName;

    private String expectedResult;

    private ArrayList<Integer> testParams;

    private boolean isSuccessfulTest; // false - если тест не был выполнен движком JS и в результате получены Exceptions

    public TestResult(String testName,
                      String expectedResult,
                      ArrayList<Integer> testParams,
                      boolean isSuccessfulTest) {
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.testParams = testParams;
        this.isSuccessfulTest = isSuccessfulTest;
    }

    // Конструктор для парсинга Jackson'ом прилетевшего массива test из ReceivedMessageByPOST
    @JsonCreator
    public TestResult(@JsonProperty(TEST_NAME) String testName,
                      @JsonProperty(EXPECTED_RESULT) String expectedResult,
                      @JsonProperty(TEST_PARAMS) ArrayList<Integer> testParams) {
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.testParams = testParams;
    }

    public String getTestName() {
        return testName;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public ArrayList<Integer> getTestParams() {
        return testParams;
    }

    public boolean isSuccessfulTest() {
        return isSuccessfulTest;
    }
}
