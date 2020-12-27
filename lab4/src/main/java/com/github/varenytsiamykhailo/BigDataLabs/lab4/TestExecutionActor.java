package com.github.varenytsiamykhailo.BigDataLabs.lab4;


import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;

public class TestExecutionActor extends AbstractActor {

    private static String STORE_ACTOR_NAME = "StoreActor";

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().match(TestExecutionActor.class, message -> {
            for (TestResult testResult : message.getTests()) {
                getContext().actorSelection("/user/" + TEST_EXECUTION_ACTOR_NAME).tell( // Отправляем тест на тестирование в TestExecutionActor
                        new TestForTestExecutionActor(message.getPackageId(), message.getJsScript(), message.getFunctionName(), testResult),
                        ActorRef.noSender()
                );
            }

        }).build();
    }

}
