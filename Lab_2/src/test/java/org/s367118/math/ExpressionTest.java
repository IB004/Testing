package org.s367118.math;

import org.junit.jupiter.api.Test;
import org.s367118.math.result.Result;
import org.s367118.math.trigonometric.Cos;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExpressionTest {
    Expression expression = new Cos(1e-3);

    @Test
    void evaluateRangeWrongRangeTest() {
        int n = 20;

        assertThrows(IllegalArgumentException.class, () -> expression.evaluateRange(10.0, -10.0, n));
    }

    @Test
    void evaluateRangeWrongNTest() {
        int n = -1;

        assertThrows(IllegalArgumentException.class, () -> expression.evaluateRange(10.0, -10.0, n));
    }

    @Test
    void evaluateRangeCountTest() {
        int n = 20;

        Stream<Result> results = expression.evaluateRange(-10.0, 10.0, n);

        assertEquals(n, results.count());
    }

}