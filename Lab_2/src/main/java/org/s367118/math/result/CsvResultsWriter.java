package org.s367118.math.result;

import lombok.AllArgsConstructor;
import org.s367118.math.Expression;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

@AllArgsConstructor
public class CsvResultsWriter {
    private Expression expression;

    public void write(String fileName, Double from, Double to, Integer n) throws IOException {
        String result = expression.evaluateRange(from, to, n)
                .filter(res -> Math.abs(res.getY()) < 12)
                .filter(res -> !Double.isNaN(res.getY()))
                .map(res -> String.format(Locale.UK, "%f; %f", res.getX(), res.getY()))
                .collect(Collectors.joining ("\n"));

        Path path = Path.of(fileName);
        Files.writeString(path, result, StandardCharsets.UTF_8,
                StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
    }

}