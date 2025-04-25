package org.s367118.math.unit.system;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.s367118.math.Expression;
import org.s367118.math.Mocks;
import org.s367118.math.system.LogarithmicPart;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

class LogarithmicPartTest {
    Expression log3 = Mocks.getLog3Mock();
    Expression log5 = Mocks.getLog5Mock();
    Expression logarithmicPart = new LogarithmicPart(log3, log5);

    @Test
    public void logarithmicPartCallsLogsTest() {
        final double arg = 5.0;

        logarithmicPart.evaluate(arg);

        verify(log3).evaluate(arg);
        verify(log5).evaluate(arg);
    }

    @ParameterizedTest
    @CsvFileSource(files="src/test/resources/simple/logarithmicPart.csv")
    public void logarithmicPartSimplePointsTest(Double x, Double expected){
        assertEquals(expected, logarithmicPart.evaluate(x), 1e-3);
    }
}