package org.s367118.math.unit.trigonometric;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.s367118.math.Expression;
import org.s367118.math.Mocks;
import org.s367118.math.trigonometric.Csc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class CscTest {
    Expression sin = Mocks.getSinMock();
    Expression csc = new Csc(sin);

    @Test
    public void cscCallsCosTest() {
        final double arg = 1.0;

        csc.evaluate(arg);

        verify(sin).evaluate(arg);
    }

    @ParameterizedTest
    @CsvFileSource(files="src/test/resources/simple/csc.csv")
    public void cscSimplePointsTest(Double x, Double expected){
        assertEquals(expected,  csc.evaluate(x), 1e-3);
    }

}