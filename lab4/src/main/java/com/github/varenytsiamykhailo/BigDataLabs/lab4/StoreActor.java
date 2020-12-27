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
                    System.out.println("Im Store Actor. Received TestResult with the test name: " + message.getTestName() + " and the package id: " +  message.getPackageId());
                    addTestToStoreCollection(message);
                }).build();
    }

    void addTestToStoreCollection(TestResult testResult) {
        System.out.println("Adding test to the store: " + testResult.getTestName() + " of package id: " + testResult.getPackageId());
        Integer packageId = testResult.getPackageId();
        ConcurrentLinkedDeque<TestResult> resultsForPackageId = resultsStore.get(packageId);

        if (resultsForPackageId == null) { // Если в хранилище еще нет тестов для нужного packageId, то добавим их
            System.out.println("There was no previous results for this package id. Creating the store and adding results");
            resultsForPackageId = new ConcurrentLinkedDeque<>();
            resultsForPackageId.add(testResult);
            resultsStore.put(packageId, resultsForPackageId);
        } else {
            System.out.println("There was previous results for this package id. Adding results");
            resultsForPackageId.add(testResult);
        }
    }
}
