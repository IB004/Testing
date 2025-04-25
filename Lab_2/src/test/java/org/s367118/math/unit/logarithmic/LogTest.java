package org.s367118.math.unit.logarithmic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.s367118.math.Expression;
import org.s367118.math.Mocks;
import org.s367118.math.logarithmic.Log;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class LogTest {
    Expression ln = Mocks.getLnMock();

    @Test
    public void logCallsLnTest() {
        final double base = 2.0;
        final double arg = 11.0;
        Expression log = new Log(base, ln);

        log.evaluate(arg);

        verify(ln).evaluate(base);
        verify(ln).evaluate(arg);
    }

    @ParameterizedTest
    @CsvFileSource(files="src/test/resources/simple/log.csv")
    public void logarithmicPartSimplePointsTest(Double base, Double x, Double expected){
        Expression log = new Log(base, ln);

        assertEquals(expected,  log.evaluate(x), 1e-3);
    }

}