package parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CsvColorMapParser {

    public static final String CSV_FIELD_SEPARATOR = ",";

    public static List<List<Integer>> loadCSVInputAsColorMap(String fileName) {
        List<List<Integer>> colorMap = new ArrayList();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                colorMap.add(parseCsvInputLine(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            throw new Error("Unable to read CSV Input file", e);
        }

        return colorMap;
    }

    private static List<Integer> parseCsvInputLine(String csvInputLine) {
        try {
            return Arrays.asList(csvInputLine.split(CSV_FIELD_SEPARATOR))
                    .stream()
                    .map(Integer::valueOf)
                    .collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new Error("Unparsable CSV Input file.", e);
        }
    }
}
