package org.s367118.math.unit.system;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.s367118.math.Expression;
import org.s367118.math.Mocks;
import org.s367118.math.system.ExpressionSystem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ExpressionSystemTest {
    private static final double LOGARITHMIC_RESULT = 2.0;
    private static final double TRIGONOMETRIC_RESULT = 3.0;
    Expression expressionSystem;

    @BeforeEach
    public void init(){
        Expression logarithmicPart = mock();
        when(logarithmicPart.evaluate(anyDouble())).thenReturn(LOGARITHMIC_RESULT);

        Expression trigonometricPart = mock();
        when(trigonometricPart.evaluate(anyDouble())).thenReturn(TRIGONOMETRIC_RESULT);

        expressionSystem = new ExpressionSystem(trigonometricPart, logarithmicPart);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-100, -1, -0.0001, 0})
    public void chooseTrigonometricPart(double x){
        assertEquals(TRIGONOMETRIC_RESULT, expressionSystem.evaluate(x));
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.0001, 1, 10, 1000})
    public void chooseLogarithmicPart(double x){
        assertEquals(LOGARITHMIC_RESULT, expressionSystem.evaluate(x));
    }

    @ParameterizedTest
    @CsvFileSource(files="src/test/resources/simple/expressionSystem.csv")
    public void simplePointsTest(Double x, Double expected){
        Expression trigonometricPart = Mocks.getTrigonometricPartMock();
        Expression logarithmicPart = Mocks.getLogarithmicMock();

        Expression system = new ExpressionSystem(trigonometricPart, logarithmicPart);
        assertEquals(expected, system.evaluate(x), 1e-3);
    }

}