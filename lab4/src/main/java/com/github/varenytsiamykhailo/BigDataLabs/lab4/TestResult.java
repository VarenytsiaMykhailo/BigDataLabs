package com.github.varenytsiamykhailo.BigDataLabs.lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class TestResult {

    private final String TEST_NAME = "testName";
    private final String EXPECTED_RESULT = "expectedResult";
    private final String TEST_PARAMS = "params";
    private final String TEST_RESULT = "testResult";
    
    @JsonProperty(TEST_NAME)
    private String testName;

    @JsonProperty(EXPECTED_RESULT)
    private String expectedResult;

    @JsonProperty(TEST_PARAMS)
    private ArrayList<Integer> testParams;

    @JsonProperty(TEST_RESULT)
    private String testResult; // если тест не был выполнен движком JS и в результате получены Exceptions, то они помещаются в эту строку


    // Конструктор для создания результата после тестирования движком JS
    public TestResult(String testName,
                      String expectedResult,
                      ArrayList<Integer> testParams,
                      String testResult) {
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.testParams = testParams;
        this.testResult = testResult;
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

    public String getTestResult() {
        return testResult;
    }

    public void setTestResult(String testResult) {
        this.testResult = testResult;
    }
}
