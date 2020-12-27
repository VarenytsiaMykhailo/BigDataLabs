package com.github.varenytsiamykhailo.BigDataLabs.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;

public class StoreActor extends AbstractActor {

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestResult.class, message -> {
                    System.out.println("Store Actor. Received TestResult with the test name: " + message.getTestName());
                    addTesToStoreCollection();
                }).build();
    }
}
