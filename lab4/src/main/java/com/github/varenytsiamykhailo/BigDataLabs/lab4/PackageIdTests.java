package com.github.varenytsiamykhailo.BigDataLabs.lab4;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PackageIdTests {

    private final String PACKAGE_ID = "packageId";
    private final String JS_SCRIPT = "jsScript";
    private final String FUNCTION_NAME = "functionName";
    private final String TEST_RESULTS = "testResults";

    @JsonProperty(PACKAGE_ID)
    private Integer packageId;

    @JsonProperty(JS_SCRIPT)
    private String jsScript;

    @JsonProperty(FUNCTION_NAME)
    private String functionName;

    @JsonProperty(TEST_RESULT)
    private TestResult testResult;

    @JsonCreator
    public Test(@JsonProperty(PACKAGE_ID) Integer packageId,
                @JsonProperty(JS_SCRIPT) String jsScript,
                @JsonProperty(FUNCTION_NAME) String functionName,
                @JsonProperty(TEST_RESULT) TestResult testResult) {
        this.packageId = packageId;
        this.jsScript = jsScript;
        this.functionName = functionName;
        this.testResult = testResult;
    }

}
