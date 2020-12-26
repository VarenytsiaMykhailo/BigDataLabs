package com.github.varenytsiamykhailo.BigDataLabs.lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class TestResult {

    public TestResult(@JsonProperty("testName") String testName, @JsonProperty("expectedResult") String expectedResult, @JsonProperty("params") String params) {

    }
}
