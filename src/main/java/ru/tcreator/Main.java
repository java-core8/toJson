package ru.tcreator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;



public class Main {
    final static String rootPath = "src/main/resources/";
    static public void main(String[] main) throws Exception {
        String fileName = "data.csv";
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        List<Employee> emp = parseCSV(columnMapping, rootPath + fileName);

        writeStringToFile(parseToJson(emp), "employee.json", rootPath);

    }

    static public List<Employee> parseCSV(String[] columns, String fileName) throws Exception {
        File scv = new File(fileName);
        if (scv.exists()) {
            try (CSVReader csvReader = new CSVReader(new FileReader(scv))) {

                ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
                strategy.setType(Employee.class);
                strategy.setColumnMapping(columns);
                CsvToBean<Employee> emploCsv = new CsvToBeanBuilder<Employee>(csvReader)
                        .withMappingStrategy(strategy)
                        .build();
                return emploCsv.parse();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    static public String parseToJson(List<Employee> obj) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Type listType = new TypeToken<List<Employee>>() {}.getType();
        return gson.toJson(obj, listType);
    }

    static public void writeStringToFile(String text, String filename, String path) throws IOException {
        Path filePath = Paths.get(path, filename);
        File file = new File(path, filename);
        try {
            file.createNewFile();
            if (file.exists()) {
                Files.writeString(filePath, text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
