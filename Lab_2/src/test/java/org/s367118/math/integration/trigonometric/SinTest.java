package org.s367118.math.integration.trigonometric;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.s367118.math.Expression;
import org.s367118.math.trigonometric.Cos;
import org.s367118.math.trigonometric.Sin;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SinTest {
    Expression cos = new Cos(1e-4);
    Expression sin = new Sin(cos);

    @ParameterizedTest
    @CsvFileSource(files="src/test/resources/sin.csv")
    public void pointsTest(Double x, Double expected){
        assertEquals(expected,  sin.evaluate(x), 1e-3);
    }

    @ParameterizedTest
    @CsvFileSource(files="src/test/resources/sin.csv")
    public void periodTest(Double x){
        final double PERIOD = 2* Math.PI;
        final double EPS = 1e-3;

        Double res = sin.evaluate(x);
        assertEquals(sin.evaluate(x + 1 * PERIOD) ,  res, EPS);
        assertEquals(sin.evaluate(x + -1 * PERIOD) ,  res, EPS);
        assertEquals(sin.evaluate(x + 5 * PERIOD) ,  res, EPS);
        assertEquals(sin.evaluate(x + -8 * PERIOD) ,  res, EPS);
    }

    @ParameterizedTest
    @CsvFileSource(files="src/test/resources/sin.csv")
    public void symmetryTest(Double x){
        assertEquals(sin.evaluate(x),  -sin.evaluate(-x), 1e-3);
    }

}