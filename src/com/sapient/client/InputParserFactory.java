package com.sapient.client;

import com.sapient.input.CsvInputParser;
import com.sapient.input.ExcelInputParser;
import com.sapient.input.InputParser;

/**
 * Created by nawed2010 on 19/09/19.
 */
public class InputParserFactory {

    public static InputParser getInputParser(String type, String filePath) {
        if(type == null || type.isEmpty()) {
            return null;
        } else if(type.equalsIgnoreCase("CSV")) {
            return new CsvInputParser(filePath);
        } else if(type.equalsIgnoreCase("excel")) {
            return new ExcelInputParser(filePath);
        }

        return null;
    }
}
