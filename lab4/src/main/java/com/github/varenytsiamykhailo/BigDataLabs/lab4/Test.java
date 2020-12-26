package com.github.varenytsiamykhailo.BigDataLabs.lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Test {
    private final String PACKAGE_ID = "packageId";
    private final String JS_SCRIPT = "jsScript";
    private final String FUNCTION_NAME = "functionName";
    private final String TEST_RESULT = "testResult";

    @JsonProperty(PACKAGE_ID)
    private int packageId;

    @JsonProperty(TEST_RESULT)
    private TestResult test;

    @JsonProperty(JS_SCRIPT)
    private String jsScript;

    @JsonProperty(FUNCTION_NAME)
    private String functionName;
}
