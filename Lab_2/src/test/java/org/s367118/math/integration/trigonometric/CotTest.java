package org.s367118.math.integration.trigonometric;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.s367118.math.Expression;
import org.s367118.math.Mocks;
import org.s367118.math.trigonometric.Cos;
import org.s367118.math.trigonometric.Cot;
import org.s367118.math.trigonometric.Sin;
import org.s367118.math.trigonometric.Tan;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

class CotTest {
    Expression cos = new Cos(1e-4);
    Expression sin = new Sin(cos);
    Expression cot = new Tan(cos, sin);

    @ParameterizedTest
    @CsvFileSource(files="src/test/resources/simple/cot.csv")
    public void pointsTest(Double x, Double expected){
        assertEquals(expected,  cot.evaluate(x), 1e-3);
    }

    @ParameterizedTest
    @CsvFileSource(files="src/test/resources/simple/cot.csv")
    public void periodTest(Double x){
        final double PERIOD = 2* Math.PI;
        final double EPS = 1e-3;

        Double res = cot.evaluate(x);
        assertEquals(cot.evaluate(x + 1 * PERIOD) ,  res, EPS);
        assertEquals(cot.evaluate(x + -1 * PERIOD) ,  res, EPS);
        assertEquals(cot.evaluate(x + 5 * PERIOD) ,  res, EPS);
        assertEquals(cot.evaluate(x + -8 * PERIOD) ,  res, EPS);
    }

    @ParameterizedTest
    @CsvFileSource(files="src/test/resources/simple/cot.csv")
    public void symmetryTest(Double x){
        assertEquals(cot.evaluate(x),  -cot.evaluate(-x), 1e-3);
    }
}