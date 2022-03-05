package com.excel.io;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by nawed2010 on 17/09/19.
 */
public class ExcelMain {

    public static void main(String[] args) throws Exception {
        System.out.println("==============================");
        //System.out.println(new File(".").getAbsolutePath());

        File file = new File("excel_java.csv");

        /*List<String> lines = Files.lines(Paths.get("excel_java.csv"))
                .skip(1).collect(Collectors.toList());*/

        List<String> lines = Files.lines(file.toPath())
                .skip(1).collect(Collectors.toList());

        List<String> uniqueCountries = lines.stream()
                .map(o -> o.split(",")[4]).distinct().collect(Collectors.toList());

        System.out.println(uniqueCountries);
        for(String country : uniqueCountries) {
            System.out.println("Processing for "+country);
            lines.stream().filter(o -> country.equalsIgnoreCase(o.split(",")[4]))
                    .mapToInt(o -> Integer.parseInt(o.split(",")[5]))
                    .average()
                    .ifPresent(System.out::println);
        }
    }
}
