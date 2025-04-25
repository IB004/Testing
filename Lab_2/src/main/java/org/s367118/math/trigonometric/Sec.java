package org.s367118.math.trigonometric;


import org.s367118.math.Expression;

public class Sec extends Expression {

    public Sec(Expression cos) {
        this.cos = cos;
        this.eps = cos.getEps();
    }

    private final Expression cos;
    @Override
    public Double evaluate(Double x) {
        Double cosValue = cos.evaluate(x);

        if (isZero(cosValue))
            return Double.NaN;

        return 1 / cosValue;
    }
}