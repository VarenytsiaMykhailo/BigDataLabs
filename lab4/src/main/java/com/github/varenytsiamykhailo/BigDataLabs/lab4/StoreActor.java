package com.github.varenytsiamykhailo.BigDataLabs.lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class StoreActor extends AbstractActor {
    private Map<Integer, ConcurrentLinkedDeque<TestResult>> storeResults = new ConcurrentHashMap<>();


    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestResult.class, message -> {
                    System.out.println("Store Actor. Received TestResult with the test name: " + message.getTestName());
                    addTesToStoreCollection(message);
                }).build();
    }

    void addTesToStoreCollection(TestResult testResult) {

    }
}
