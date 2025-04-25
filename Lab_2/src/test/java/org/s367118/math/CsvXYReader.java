package org.s367118.math;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CsvXYReader {
    public static List<Double[]> read(String filename) {
        List<Double[]> results = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                results.add(getResultFromLine(scanner.nextLine()));
            }
        }
        catch (Exception e){
            throw new RuntimeException(filename);
        }
        return results;
    }
    private static Double[] getResultFromLine(String line) {
        Double x;
        Double y;
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            x = Double.valueOf(rowScanner.next());
            y = Double.valueOf(rowScanner.next());
        }
        return new Double[] {x, y};
    }
}
