package org.s367118.math.integration.system;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.s367118.math.Expression;
import org.s367118.math.logarithmic.Ln;
import org.s367118.math.logarithmic.Log;
import org.s367118.math.system.ExpressionSystem;
import org.s367118.math.system.LogarithmicPart;
import org.s367118.math.system.TrigonometricPart;
import org.s367118.math.trigonometric.*;

import static org.junit.jupiter.api.Assertions.*;

public class TrigonometricPartTest {
    private static final Double EPS = 1e-3;
    Expression cos = new Cos(1e-4);
    Expression sin = new Sin(cos);
    Expression tan = new Tan(sin, cos);
    Expression cot = new Cot(cos, sin);
    Expression sec = new Sec(cos);
    Expression csc = new Csc(sin);
    Expression trigonometricPart = new TrigonometricPart(cos, sin, tan, cot, sec, csc);

    @Test
    public void aroundTrigMaxTest(){
        double arg = -2.30006;
        double expected = -3.50874;

        Double beforeArg = trigonometricPart.evaluate(arg-EPS);
        Double onArg =  trigonometricPart.evaluate(arg);
        Double afterArg = trigonometricPart.evaluate(arg+EPS);

        assertTrue(beforeArg < onArg);
        assertEquals(expected, onArg, EPS);
        assertTrue(afterArg < onArg);
    }

    @Test
    public void aroundTrigLargeMinTest(){
        double arg = -0.92812;
        double expected = 5.79999;

        Double beforeArg = trigonometricPart.evaluate(arg - EPS);
        Double onArg =  trigonometricPart.evaluate(arg);
        Double afterArg = trigonometricPart.evaluate(arg + EPS);

        assertTrue(beforeArg > onArg);
        assertEquals(expected, onArg, EPS);
        assertTrue(afterArg > onArg);
    }

    @Test
    public void aroundTrigSmallMinTest(){
        double arg = -4.58223;
        double expected = 0.96784;

        Double beforeArg = trigonometricPart.evaluate(arg - EPS);
        Double onArg =  trigonometricPart.evaluate(arg);
        Double afterArg = trigonometricPart.evaluate(arg + EPS);

        assertTrue(beforeArg > onArg);
        assertEquals(expected, onArg, EPS);
        assertTrue(afterArg > onArg);
    }

    @ParameterizedTest
    @CsvFileSource(files="src/test/resources/trigonometricPart.csv")
    public void trigonometricPartPeriodicTest(Double arg){
        final double PERIOD = 2*Math.PI;

        Double zero = trigonometricPart.evaluate(arg - 0 * PERIOD);
        Double one =  trigonometricPart.evaluate(arg - 1 * PERIOD);
        Double two = trigonometricPart.evaluate(arg - 2 * PERIOD);
        Double ten = trigonometricPart.evaluate(arg - 10 * PERIOD);
        Double hundred = trigonometricPart.evaluate(arg - 100 * PERIOD);

        double[] same = {zero, zero, zero, zero, zero};
        double[] different = {zero, one, two, ten, hundred};
        assertArrayEquals(same, different, EPS);
    }

    @ParameterizedTest
    @CsvFileSource(files="src/test/resources/trigonometricPart.csv")
    public void trigonometricPartTest(Double x, Double expected){
        assertEquals(expected, trigonometricPart.evaluate(x), EPS);
    }


}
