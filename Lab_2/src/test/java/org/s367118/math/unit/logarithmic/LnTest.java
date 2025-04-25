package org.s367118.math.unit.logarithmic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.s367118.math.Expression;
import org.s367118.math.ReachedMaxIterationsException;
import org.s367118.math.logarithmic.Ln;

import static org.junit.jupiter.api.Assertions.*;

class LnTest {

    private static final double EPS = 1e-4;
    Expression ln = new Ln(EPS);

    @ParameterizedTest
    @CsvFileSource(files="src/test/resources/ln.csv")
    public void pointsTest(Double x, Double expected){
        assertEquals(expected,  ln.evaluate(x), 1e-2);
    }

    @Test
    public void lnMaxIterationsTest(){
        assertThrows(ReachedMaxIterationsException.class, () -> {
            Expression ln = new Ln(-1);
            ln.evaluate(1.0);
        });
    }
}