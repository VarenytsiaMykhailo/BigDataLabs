package com.github.varenytsiamykhailo.BigDataLabs.lab4;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class StoreActor extends AbstractActor {

    private Map<Integer, ConcurrentLinkedDeque<TestResult>> resultsStore = new ConcurrentHashMap<>(); // id пакета и список тестов, соответствующий этому пакету

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(TestResult.class, message -> {
                    System.out.println("Store Actor. Received TestResult with the test name: " + message.getTestName());
                    addTesToStoreCollection(message);
                }).build();
    }

    void addTesToStoreCollection(TestResult testResult) {
        
        Integer packageId = testResult.getPackageId();
        ConcurrentLinkedDeque<TestResult> resultsForPackageId = resultsStore.get(packageId);

        if (resultsForPackageId == null) { // Если в хранилище еще нет тестов для нужного packageId, то добавим их
            resultsForPackageId = new ConcurrentLinkedDeque<>();
            resultsForPackageId.add(testResult);
            resultsStore.put(packageId, resultsForPackageId);
        } else {
            resultsForPackageId.add(testResult);
        }
    }
}
