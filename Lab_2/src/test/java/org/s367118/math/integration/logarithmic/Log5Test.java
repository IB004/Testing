package org.s367118.math.integration.logarithmic;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.s367118.math.Expression;
import org.s367118.math.logarithmic.Ln;
import org.s367118.math.logarithmic.Log;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Log5Test {
    Expression ln = new Ln(1e-4);
    Expression log = new Log(5.0, ln);

    @ParameterizedTest
    @CsvFileSource(files="src/test/resources/log5.csv")
    public void logarithmicPartSimplePointsTest(Double x, Double expected){
        assertEquals(expected, log.evaluate(x), 1e-3);
    }

}