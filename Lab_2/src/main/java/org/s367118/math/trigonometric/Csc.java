package org.s367118.math.trigonometric;


import org.s367118.math.Expression;


public class Csc extends Expression {

    public Csc(Expression sin) {
        this.sin = sin;
        this.eps = sin.getEps();
    }

    private final Expression sin;

    @Override
    public Double evaluate(Double x) {
        Double sinValue = sin.evaluate(x);

        if (isZero(sinValue))
            return Double.NaN;

        return 1 / sinValue;
    }
}