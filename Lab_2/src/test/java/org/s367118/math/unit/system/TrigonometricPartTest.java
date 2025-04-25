package org.s367118.math.unit.system;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.s367118.math.Expression;
import org.s367118.math.Mocks;
import org.s367118.math.system.TrigonometricPart;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TrigonometricPartTest {
    Expression cos = Mocks.getCosMock();
    Expression sin = Mocks.getSinMock();
    Expression tan = Mocks.getTanMock();
    Expression cot = Mocks.getCotMock();
    Expression sec = Mocks.getSecMock();
    Expression csc = Mocks.getCscMock();

    Expression trigonometricPart = new TrigonometricPart(cos, sin, tan, cot, sec, csc);

    @Test
    public void trigonometricPartCallsOtherFunctionsTest() {
        final double arg = 5.0;

        trigonometricPart.evaluate(arg);

        verify(cos, atLeast(1)).evaluate(arg);
        verify(sin, atLeast(1)).evaluate(arg);
        verify(tan, atLeast(1)).evaluate(arg);
        verify(cot, atLeast(1)).evaluate(arg);
        verify(sec, atLeast(1)).evaluate(arg);
        verify(csc, atLeast(1)).evaluate(arg);
    }

    @ParameterizedTest
    @CsvFileSource(files="src/test/resources/simple/trigonometricPart.csv")
    public void logarithmicPartSimplePointsTest(Double x, Double expected){
        assertEquals(expected, trigonometricPart.evaluate(x), 1e-3);
    }
}