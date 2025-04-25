package org.s367118.math.unit.trigonometric;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.s367118.math.Expression;
import org.s367118.math.Mocks;
import org.s367118.math.trigonometric.Sec;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class SecTest {
    Expression cos = Mocks.getCosMock();
    Expression sec = new Sec(cos);

    @Test
    public void secCallsCosTest() {
        final double arg = 1.0;

        sec.evaluate(arg);

        verify(cos).evaluate(arg);
    }

    @ParameterizedTest
    @CsvFileSource(files="src/test/resources/simple/sec.csv")
    public void secSimplePointsTest(Double x, Double expected){
        assertEquals(expected,  sec.evaluate(x), 1e-3);
    }

}