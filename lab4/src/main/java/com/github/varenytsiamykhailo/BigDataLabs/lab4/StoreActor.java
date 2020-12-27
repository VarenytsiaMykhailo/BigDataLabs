package com.github.varenytsiamykhailo.BigDataLabs.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
                }).match(Integer.class, message -> {
                    System.out.println("Im Store Actor. Sending the test results for package id: " + message;
                    sender().tell(resultsStore.get(message), ActorRef.noSender());
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

    private List<TestResult> getTestFromStoreCollection(int packageId) {
        ConcurrentLinkedDeque<TestResult> resultsForPackageId = resultsStore.get(packageId);
        if (resultsForPackageId == null) { // Если в хранилище нет результатов для данного packageId
            return new ArrayList<>(); // Вернем пустой список
        } else {
            return Arrays.asList(new TestResult[resultsForPackageId.size()]);
        }
    }
}
