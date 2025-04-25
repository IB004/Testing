package org.s367118.math.trigonometric;

import org.s367118.math.Expression;

public class Tan extends Expression {

    public Tan(Expression sin, Expression cos) {
        this.sin = sin;
        this.cos = cos;
        this.eps = cos.getEps();
    }

    private final Expression sin;
    private final Expression cos;
    @Override
    public Double evaluate(Double x) {
        Double sinValue = sin.evaluate(x);
        Double cosValue = cos.evaluate(x);

        if (isZero(cosValue))
            return Double.NaN;

        return  sinValue / cosValue;
    }
}
