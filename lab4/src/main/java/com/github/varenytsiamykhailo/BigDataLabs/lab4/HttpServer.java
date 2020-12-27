package com.github.varenytsiamykhailo.BigDataLabs.lab4;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.Route;
import akka.pattern.PatternsCS;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

import java.util.concurrent.CompletionStage;

import static akka.http.javadsl.server.Directives.*;

public class HttpServer {

    private String host;

    private int port;

    public HttpServer(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() {

        // Инициализация http сервера
        ActorSystem actorSystem = ActorSystem.create("actorSystem");
        final Http http = Http.get(actorSystem);
        final ActorMaterializer materializer = ActorMaterializer.create(actorSystem);

        MainHttp mainHttp = new MainHttp(actorSystem);

        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = mainHttp.createRoute().flow(actorSystem, materializer);

        final CompletionStage<ServerBinding> binding = http.bindAndHandle(
                routeFlow,
                ConnectHttp.toHost(host, port),
                materializer
        );

        System.out.println("Server online at port " + port + "\nPress RETURN to stop...");

        binding.thenCompose(ServerBinding::unbind).thenAccept(unbound -> actorSystem.terminate());
    }

    private class MainHttp {

        private String MAIN_ACTOR_NAME = "MainActor";
        private String STORE_ACTOR_NAME = "StoreActor";
        private String TEST_EXECUTION_ACTOR_NAME = "TestExecutionActor";

        ActorSystem actorSystem;

        private ActorRef mainActor;

        private ActorRef storeActor;

        private ActorRef testExecutionActor;

        public MainHttp(ActorSystem actorSystem) {
            this.actorSystem = actorSystem;
            setActors();
        }

        private void setActors() {
            mainActor = actorSystem.actorOf(Props.create(MainActor.class), MAIN_ACTOR_NAME);
            storeActor = actorSystem.actorOf(Props.create(StoreActor.class), STORE_ACTOR_NAME);
            testExecutionActor = actorSystem.actorOf(Props.create(TestExecutionActor.class), TEST_EXECUTION_ACTOR_NAME);
        }

        private Route createRoute() {
            return post( // Если запрос с методом POST - посылаем данные на обработку в mainActor
                    () -> entity(Jackson.unmarshaller(PackageIdTests.class), message -> {
                        System.out.println("Calling MainActor");
                        mainActor.tell(message, ActorRef.noSender());
                        return complete("Start test");
                    })
            ).orElse(get( // Если запрос с методом GET - выдаем результат, хранящийся в storeActor
                    () -> parameter("packageId", key -> {
                        System.out.println("Calling StoreActor");
                        CompletionStage<Object> result = PatternsCS.ask(storeActor, Integer.parseInt(key), 5000);
                        return completeOKWithFuture(result, Jackson.marshaller());
                    })));
        }

    }

}


