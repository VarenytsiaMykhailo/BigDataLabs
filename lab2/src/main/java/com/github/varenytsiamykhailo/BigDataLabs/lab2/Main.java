package com.github.varenytsiamykhailo.BigDataLabs.lab2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        BufferedReader csvReader = new BufferedReader(new FileReader("technicalTask/flights.csv");
        String row = "";
        while (true) {
            try {
                if (!((row = csvReader.readLine()) != null))
                    break;
                String[] data = row.split(",");
                // do something with the data
                csvReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
