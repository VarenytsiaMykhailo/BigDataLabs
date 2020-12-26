package com.github.varenytsiamykhailo.BigDataLabs.lab4;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.ConnectHttp;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;

import java.util.concurrent.CompletionStage;

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

        binding
                .thenCompose(ServerBinding::unbind)
                .thenAccept(unbound -> actorSystem.terminate());
    }

    private class MainHttp {

        ActorSystem actorSystem;

        private ActorRef mainActor;

        private ActorRef storeActor;

        private ActorRef testExecutionActor;

        public MainHttp(ActorSystem actorSystem) {
            this.actorSystem = actorSystem;
            setActors();
        }

        private void setActors() {
            mainActor = actorSystem.actorOf(Props.create(MainActor.class), "MainActor");
            storeActor = actorSystem.actorOf(Props.create(StoreActor.class), "StoreActor");
            testExecutionActor = actorSystem.actorOf(Props.create(TestExecutionActor.class), "TestExecutionActor");
        }

    }

}


