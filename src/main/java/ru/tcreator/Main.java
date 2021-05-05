package ru.tcreator;

import java.io.*;
import java.util.Scanner;


public class Main {
    static public void main(String[] main) throws Exception {
        String fileName = "data.csv";
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};

        parseCSV(columnMapping, fileName);



    }

    static public void parseCSV(String[] columns, String fileName) throws Exception {
        File scv = new File("src/main/resources/data.csv");
        System.out.println(scv.canRead());
        try(FileReader fr = new FileReader(scv)) {
            Scanner scanner = new Scanner(fr);
            System.out.println(scanner.nextLine());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
