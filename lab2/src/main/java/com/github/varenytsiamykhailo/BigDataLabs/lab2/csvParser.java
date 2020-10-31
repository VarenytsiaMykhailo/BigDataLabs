package com.github.varenytsiamykhailo.BigDataLabs.lab2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class csvParser {

    public String getColumnsArray(String filePath) {
        BufferedReader csvReader = null;
        try {
            csvReader = new BufferedReader(new FileReader(filePath));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            String row = "";
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                Integer destAirportId = Integer.parseInt(data[14]);
                System.out.println(destAirportId);
                System.out.println(Arrays.toString(data));

            }
            csvReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
