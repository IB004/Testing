package org.s367118.math.system;

import org.s367118.math.Expression;

public class ExpressionSystem extends Expression {

    public ExpressionSystem(Expression trigonometricPart, Expression logarithmicPart) {
        this.trigonometricPart = trigonometricPart;
        this.logarithmicPart = logarithmicPart;
    }

    private final Expression trigonometricPart;
    private final Expression logarithmicPart;

    @Override
    public Double evaluate(Double x) {
        if (x <= 0)
            return trigonometricPart.evaluate(x);
        return logarithmicPart.evaluate(x);
    }
}
