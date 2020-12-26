package com.github.varenytsiamykhailo.BigDataLabs.lab4;

public class AppRunner {

    public static void main(String[] args) {

        HttpServer httpServer = new HttpServer("localhost", 11111);

        httpServer.run();
        
    }
}
