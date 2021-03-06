package com.github.varenytsiamykhailo.BigDataLabs.lab4;


import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;
import scala.Int;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.ArrayList;

public class TestExecutionActor extends AbstractActor {

    private static String STORE_ACTOR_NAME = "StoreActor";

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestForTestExecutionActor.class, message -> {
                    System.out.println("Im TestExecutionActor. Starting test");
                    TestResult testResult = message.getTestResult();
                    TestResult executedTestResult = executeTestByJSEngine(
                            message.getPackageId(),
                            message.getJsScript(),
                            testResult.getTestName(),
                            testResult.getTestParams(),
                            message.getFunctionName(),
                            testResult.getExpectedResult()
                    );
                    System.out.println("Sending test result for the test name: " + executedTestResult.getTestName() + " package id: " + executedTestResult.getPackageId() + " to StoreActor");
                    getContext().actorSelection("/user/" + STORE_ACTOR_NAME).tell(executedTestResult, ActorRef.noSender());
                }).build();
    }

    private TestResult executeTestByJSEngine(Integer packageId,
                                             String codeForTest,
                                             String testName,
                                             ArrayList<Integer> testParams,
                                             String functionName,
                                             String expectedResult) {
        String result;
        try {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
            engine.eval(codeForTest);

            Invocable invocable = (Invocable) engine;
            result = invocable.invokeFunction(functionName, testParams.toArray()).toString();

            System.out.println("Test finished. Expected result: " + expectedResult + " received test result: " + result);
        } catch (ScriptException | NoSuchMethodException e) {
            result = e.getMessage();
        }
        return new TestResult(packageId, testName, expectedResult, testParams, result);
    }

}
