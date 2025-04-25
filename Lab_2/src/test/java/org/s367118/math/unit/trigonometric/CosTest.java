package org.s367118.math.unit.trigonometric;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.s367118.math.Expression;
import org.s367118.math.ReachedMaxIterationsException;
import org.s367118.math.trigonometric.Cos;

import static org.junit.jupiter.api.Assertions.*;


class CosTest {
    Expression cos = new Cos(1e-4);

    @ParameterizedTest
    @CsvFileSource(files="src/test/resources/cos.csv")
    public void pointsTest(Double x, Double expected){
        assertEquals(expected,  cos.evaluate(x), 1e-3);
    }

    @ParameterizedTest
    @CsvFileSource(files="src/test/resources/cos.csv")
    public void periodTest(Double x, Double expected){
        final double PERIOD = 2* Math.PI;
        final double EPS = 1e-3;

        Double res = cos.evaluate(x);
        assertEquals(cos.evaluate(x + 1 * PERIOD) ,  res, EPS);
        assertEquals(cos.evaluate(x + -1 * PERIOD) ,  res, EPS);
        assertEquals(cos.evaluate(x + 5 * PERIOD) ,  res, EPS);
        assertEquals(cos.evaluate(x + -8 * PERIOD) ,  res, EPS);
    }

    @ParameterizedTest
    @CsvFileSource(files="src/test/resources/cos.csv")
    public void symmetryTest(Double x, Double expected){
        assertEquals(cos.evaluate(x),  cos.evaluate(-x), 1e-3);
    }

    @Test
    public void cosMaxIterationsTest(){
        Expression cos = new Cos(-1);

        assertThrows(ReachedMaxIterationsException.class, () -> cos.evaluate(1.0));
    }
}