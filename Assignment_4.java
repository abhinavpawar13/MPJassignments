package sample;

import java.io.*;

public class Assignment_4 {

    public static void main(String[] args) {

        String fileName = "sample.txt";

        try {
            FileWriter fw = new FileWriter(fileName);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write("Hello, this is a File I/O example in Java.");
            bw.newLine();
            bw.write("This demonstrates Exception Handling.");

            bw.close();
            fw.close();

            System.out.println("Data written successfully.");

        } catch (IOException e) {
            System.out.println("Error while writing to file: " + e.getMessage());
        }

        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);

            String line;
            System.out.println("\nReading data from file:");

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

            br.close();
            fr.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());

        } catch (IOException e) {
            System.out.println("Error while reading file: " + e.getMessage());

        } finally {
            System.out.println("\nFile operation completed (finally block executed).");
        }
    }
}