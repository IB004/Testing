package org.s367118.math.unit.trigonometric;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.s367118.math.Expression;
import org.s367118.math.Mocks;
import org.s367118.math.trigonometric.Cot;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class CotTest {
    Expression sin = Mocks.getSinMock();
    Expression cos = Mocks.getCosMock();

    Expression cot = new Cot(cos, sin);

    @Test
    public void cotCallsSinAndCosTest() {
        final double arg = 1.0;

        cot.evaluate(arg);

        verify(cos).evaluate(arg);
        verify(sin).evaluate(arg);
    }

    @ParameterizedTest
    @CsvFileSource(files="src/test/resources/simple/cot.csv")
    public void cotSimplePointsTest(Double x, Double expected){
        assertEquals(expected,  cot.evaluate(x), 1e-3);
    }

}