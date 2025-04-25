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

public class RealSystemTest {
    private static final Double EPS = 1e-3;
    private static final Double LARGE_EPS = 0.05;

    Expression ln = new Ln(1e-4);
    Expression cos = new Cos(1e-4);
    Expression sin = new Sin(cos);
    Expression tan = new Tan(sin, cos);
    Expression cot = new Cot(cos, sin);
    Expression sec = new Sec(cos);
    Expression csc = new Csc(sin);

    Expression logarithmicPart = new LogarithmicPart(new Log(3.0, ln), new Log(5.0, ln));
    Expression trigonometricPart = new TrigonometricPart(cos, sin, tan, cot, sec, csc);

    Expression system = new ExpressionSystem(trigonometricPart, logarithmicPart);


    @Test
    public void aroundLogNoneTest(){
        double arg = 1.0;

        Double beforeArg = system.evaluate(arg-EPS);
        Double onArg =  system.evaluate(arg);
        Double afterArg = system.evaluate(arg+EPS);

        assertTrue(beforeArg < 0);
        assertTrue(onArg.isNaN());
        assertTrue(afterArg > 0);
    }

    @Test
    public void aroundLogMaxTest(){
        double arg = 1.12353;
        double expected = 0.004767;

        Double beforeArg = system.evaluate(arg-EPS);
        Double onArg =  system.evaluate(arg);
        Double afterArg = system.evaluate(arg+EPS);

        assertTrue(beforeArg < onArg);
        assertEquals(expected, onArg, EPS);
        assertTrue(afterArg < onArg);
    }

    @Test
    public void aroundLogMinTest(){
        double arg = 1.418253;
        double expected = 0.0;

        Double beforeArg = system.evaluate(arg - EPS);
        Double onArg =  system.evaluate(arg);
        Double afterArg = system.evaluate(arg + EPS);

        assertTrue(beforeArg > onArg);
        assertEquals(expected, onArg, EPS);
        assertTrue(afterArg > onArg);
    }

    @Test
    public void aroundTrigMaxTest(){
        double arg = -2.30006;
        double expected = -3.50874;

        Double beforeArg = system.evaluate(arg-EPS);
        Double onArg =  system.evaluate(arg);
        Double afterArg = system.evaluate(arg+EPS);

        assertTrue(beforeArg < onArg);
        assertEquals(expected, onArg, EPS);
        assertTrue(afterArg < onArg);
    }

    @Test
    public void aroundTrigLargeMinTest(){
        double arg = -0.92812;
        double expected = 5.79999;

        Double beforeArg = system.evaluate(arg - EPS);
        Double onArg =  system.evaluate(arg);
        Double afterArg = system.evaluate(arg + EPS);

        assertTrue(beforeArg > onArg);
        assertEquals(expected, onArg, EPS);
        assertTrue(afterArg > onArg);
    }

    @Test
    public void aroundTrigSmallMinTest(){
        double arg = -4.58223;
        double expected = 0.96784;

        Double beforeArg = system.evaluate(arg - EPS);
        Double onArg =  system.evaluate(arg);
        Double afterArg = system.evaluate(arg + EPS);

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
    @CsvFileSource(files="src/test/resources/logarithmicPart.csv")
    public void logarithmicPartTest(Double x, Double expected){
        assertEquals(expected, system.evaluate(x), EPS);
    }

    @ParameterizedTest
    @CsvFileSource(files="src/test/resources/trigonometricPart.csv")
    public void trigonometricPartTest(Double x, Double expected){
        assertEquals(expected, system.evaluate(x), EPS);
    }

    @ParameterizedTest
    @CsvFileSource(files="src/test/resources/systemLargeEps.csv")
    public void largeEpsTest(Double x, Double expected){
        assertEquals(expected, system.evaluate(x), LARGE_EPS);
    }

}
