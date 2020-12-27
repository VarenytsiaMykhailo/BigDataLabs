package com.github.varenytsiamykhailo.BigDataLabs.lab4;


import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;

public class TestExecutionActor extends AbstractActor {

    private static String STORE_ACTOR_NAME = "StoreActor";

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().match(TestForTestExecutionActor.class, message -> {

            TestResult testResult = message.getTestResult();


        }).build();
    }

    private void executeTestByJSEngine(String codeForTest,
                                       String testName,
                                       ArrayList<Integer> testParams,
                                       String functionName,
                                       String expectedResult) {
        

    }

}
