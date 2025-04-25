package org.s367118.math.integration.trigonometric;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.s367118.math.Expression;
import org.s367118.math.trigonometric.Cos;
import org.s367118.math.trigonometric.Sec;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SecTest {
    Expression cos = new Cos(1e-4);
    Expression sec = new Sec(cos);

    @ParameterizedTest
    @CsvFileSource(files="src/test/resources/simple/sec.csv")
    public void pointsTest(Double x, Double expected){
        assertEquals(expected,  sec.evaluate(x), 1e-3);
    }

    @ParameterizedTest
    @CsvFileSource(files="src/test/resources/simple/sec.csv")
    public void periodTest(Double x){
        final double PERIOD = 2* Math.PI;
        final double EPS = 1e-3;

        Double res = sec.evaluate(x);
        assertEquals(sec.evaluate(x + 1 * PERIOD) ,  res, EPS);
        assertEquals(sec.evaluate(x + -1 * PERIOD) ,  res, EPS);
        assertEquals(sec.evaluate(x + 5 * PERIOD) ,  res, EPS);
        assertEquals(sec.evaluate(x + -8 * PERIOD) ,  res, EPS);
    }

    @ParameterizedTest
    @CsvFileSource(files="src/test/resources/simple/sec.csv")
    public void symmetryTest(Double x){
        assertEquals(sec.evaluate(x),  sec.evaluate(-x), 1e-3);
    }

}