package com.github.varenytsiamykhailo.BigDataLabs.lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

public class MainActor extends AbstractActor {

    private String TEST_EXECUTION_ACTOR_NAME = "TestExecutionActor";

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().match(PackageIdTests.class, message -> {
            for (int i = 0; i < ; i++) {
                
            }

        }).build();
    }
}
