package com.github.varenytsiamykhailo.BigDataLabs.lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class TestResult {

    private String testName;

    private String expectedResult;

    private ArrayList<Integer> params;

    private boolean isSuccessfulTest;

    public TestResult(@JsonProperty("testName") String testName, @JsonProperty("expectedResult") String expectedResult, @JsonProperty("params") isSuccessfulTest params, boolean isSuccessfulTest) {
        this.testName = testName;
        this.expectedResult = expectedResult;
        this.params = params;
        this.isSuccessfulTest = isSuccessfulTest;
    }
}
