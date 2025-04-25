package org.s367118.math.trigonometric;

import org.s367118.math.Expression;

public class Cot extends Expression {
    public Cot(Expression cos, Expression sin) {
        this.cos = cos;
        this.sin = sin;
        this.eps = sin.getEps();
    }

    private final Expression cos;
    private final Expression sin;

    @Override
    public Double evaluate(Double x) {
        Double cosValue = cos.evaluate(x);
        Double sinValue = sin.evaluate(x);

        if (isZero(sinValue))
            return Double.NaN;

        return cosValue / sinValue;
    }
}