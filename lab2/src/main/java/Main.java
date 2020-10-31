import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        BufferedReader csvReader = null;
        try {
            csvReader = new BufferedReader(new FileReader("technicalTask/airports.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String row = "";

        try {
            while ((row = csvReader.readLine()) != null) {
                System.out.println(row);
                //String[] data = row.split(",");
            }
            csvReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
