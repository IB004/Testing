package org.s367118.math.unit.trigonometric;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.s367118.math.Expression;
import org.s367118.math.Mocks;
import org.s367118.math.trigonometric.Sin;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.verify;

class SinTest {
    Expression cos = Mocks.getCosMock();
    Expression sin = new Sin(cos);

    @Test
    public void sinCallsCosTest() {
        final double arg = 1.0;

        sin.evaluate(arg);

        verify(cos).evaluate(anyDouble());
    }

    @ParameterizedTest
    @CsvFileSource(files="src/test/resources/simple/sin.csv")
    public void sinSimplePointsTest(Double x, Double expected){
        assertEquals(expected,  sin.evaluate(x), 1e-3);
    }

}