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

import java.util.concurrent.CompletionStage;

public class HttpServer {

    public void run() {

        // Инициализация http сервера
        ActorSystem actorSystem = ActorSystem.create("s");
        final Http http = Http.get(actorSystem);
        final ActorMaterializer materializer = ActorMaterializer.create(actorSystem);

        MainHttp instance = new MainHttp(actorSystem);

        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow =
                instance.createRoute(system).flow(system, materializer);

        final CompletionStage<ServerBinding> binding = http.bindAndHandle(
                routeFlow,
                ConnectHttp.toHost("localhost", 8080),
                materializer
        );
        System.out.println("Server online at http://localhost:8080/\nPress RETURN to stop...");
        System.in.read();
        binding
                .thenCompose(ServerBinding::unbind)
                .thenAccept(unbound -> system.terminate());
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
            mainActor = actorSystem.actorOf(Props.create(StoreActor.class), "StoreActor");
            storeActor = actorSystem.actorOf(Props.create(StoreActor.class), "StoreActor");

        }

    }

}


