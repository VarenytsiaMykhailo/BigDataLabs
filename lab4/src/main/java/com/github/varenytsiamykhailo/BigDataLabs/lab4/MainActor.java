package com.github.varenytsiamykhailo.BigDataLabs.lab4;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.japi.pf.ReceiveBuilder;

public class MainActor extends AbstractActor {

    private static String TEST_EXECUTION_ACTOR_NAME = "TestExecutionActor";

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create().match(ReceivedMessageByPOST.class, message -> {
            for (TestResult testResult : message.getTests()) {
                getContext().actorSelection("/user/" + TEST_EXECUTION_ACTOR_NAME).tell( // Отправляем тест на тестирование в TestExecutionActor
                        new TestForTestExecutionActor(message.getPackageId(), message.getJsScript(), message.getFunctionName(), testResult),
                        ActorRef.noSender()
                );
            }

        }).build();
    }
}
