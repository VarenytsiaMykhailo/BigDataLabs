package com.github.varenytsiamykhailo.BigDataLabs.lab4;

import java.io.IOException;

public class AppRunner {

    public static void main(String[] args) {

        HttpServer httpServer = new HttpServer("localhost", 11111);

        try {
            httpServer.run();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
