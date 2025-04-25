package org.s367118.math.unit.trigonometric;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.s367118.math.Expression;
import org.s367118.math.Mocks;
import org.s367118.math.trigonometric.Tan;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class TanTest {
    Expression cos = Mocks.getCosMock();
    Expression sin = Mocks.getSinMock();
    Expression tan = new Tan(sin, cos);

    @Test
    public void tanCallsSinAndCosTest() {
        final double arg = 1.0;

        tan.evaluate(arg);

        verify(sin).evaluate(arg);
        verify(cos).evaluate(arg);
    }

    @ParameterizedTest
    @CsvFileSource(files="src/test/resources/simple/tan.csv")
    public void tanSimplePointsTest(Double x, Double expected){
        assertEquals(expected,  tan.evaluate(x), 1e-3);
    }
}