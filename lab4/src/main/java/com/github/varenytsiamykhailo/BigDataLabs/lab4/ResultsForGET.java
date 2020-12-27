package com.github.varenytsiamykhailo.BigDataLabs.lab4;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResultsForGET {
    private static final String PACKAGE_ID = "packageId";
    private static final String TESTS = "tests";

    @JsonProperty(PACKAGE_ID)
    private Integer packageId;

    @JsonProperty(TESTS)
    private List<TestResult> tests;

    public ResultsForGET(@JsonProperty(PACKAGE_ID) Integer packageId, @JsonProperty(TESTS) List<TestResult> tests) {
        this.packageId = packageId;
        this.tests = tests;
    }

    public Integer getPackageId() {
        return packageId;
    }

    public List<TestResult> getTests() {
        return tests;
    }
}
