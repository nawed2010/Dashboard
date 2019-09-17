package com.excel.io;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by nawed2010 on 17/09/19.
 */
public class ExcelMain {

    public static void main(String[] args) throws Exception {
        System.out.println("==============================");
        //System.out.println(new File(".").getAbsolutePath());

        List<String> lines = Files.lines(Paths.get("excel_java.csv"))
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
