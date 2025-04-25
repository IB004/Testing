package org.s367118.math.integration.system;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.s367118.math.Expression;
import org.s367118.math.logarithmic.Ln;
import org.s367118.math.logarithmic.Log;
import org.s367118.math.system.LogarithmicPart;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LogarithmicPartTest {
    private static final Double EPS = 1e-3;

    Expression ln = new Ln(1e-4);

    Expression logarithmicPart = new LogarithmicPart(new Log(3.0, ln), new Log(5.0, ln));


    @Test
    public void aroundLogNoneTest(){
        double arg = 1.0;

        Double beforeArg = logarithmicPart.evaluate(arg-EPS);
        Double onArg =  logarithmicPart.evaluate(arg);
        Double afterArg = logarithmicPart.evaluate(arg+EPS);

        assertTrue(beforeArg < 0);
        assertTrue(onArg.isNaN());
        assertTrue(afterArg > 0);
    }

    @Test
    public void aroundLogMaxTest(){
        double arg = 1.12353;
        double expected = 0.004767;

        Double beforeArg = logarithmicPart.evaluate(arg-EPS);
        Double onArg =  logarithmicPart.evaluate(arg);
        Double afterArg = logarithmicPart.evaluate(arg+EPS);

        assertTrue(beforeArg < onArg);
        assertEquals(expected, onArg, EPS);
        assertTrue(afterArg < onArg);
    }

    @Test
    public void aroundLogMinTest(){
        double arg = 1.418253;
        double expected = 0.0;

        Double beforeArg = logarithmicPart.evaluate(arg - EPS);
        Double onArg =  logarithmicPart.evaluate(arg);
        Double afterArg = logarithmicPart.evaluate(arg + EPS);

        assertTrue(beforeArg > onArg);
        assertEquals(expected, onArg, EPS);
        assertTrue(afterArg > onArg);
    }


    @ParameterizedTest
    @CsvFileSource(files="src/test/resources/logarithmicPart.csv")
    public void logarithmicPartTest(Double x, Double expected){
        assertEquals(expected, logarithmicPart.evaluate(x), EPS);
    }


}
