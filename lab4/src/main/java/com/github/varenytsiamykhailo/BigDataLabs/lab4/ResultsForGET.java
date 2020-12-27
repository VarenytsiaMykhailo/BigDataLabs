package com.github.varenytsiamykhailo.BigDataLabs.lab4;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResultsForGET {
    private static final String PACKAGE_ID = "packageId";
    private static final String TEST_RESULTS = "testResults";

    @JsonProperty(PACKAGE_ID)
    private Integer packageId;

    @JsonProperty(TEST_RESULTS)
    private List<TestResult> testResults;

    public ResultsForGET(@JsonProperty(PACKAGE_ID) Integer packageId, @JsonProperty(TEST_RESULTS) List<TestResult> testResults) {
        this.packageId = packageId;
        this.testResults = testResults;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public List<TestResult> getTests() {
        return tests;
    }
}
