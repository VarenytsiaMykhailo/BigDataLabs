package test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        BufferedReader csvReader = null;
        try {
            csvReader = new BufferedReader(new FileReader("technicalTask/flights.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String row = "";

        try {
            while ((row = csvReader.readLine()) != null) {
                // System.out.println(row);
                String[] data = row.split(",");
                //Integer destAirportId = Integer.parseInt(data[14]);
                //System.out.println(destAirportId);
                System.out.println(Arrays.toString(data));
            }
            csvReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
