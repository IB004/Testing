package org.s367118.math.trigonometric;

import org.s367118.math.Expression;

public class Sin extends Expression {

    public Sin(Expression cos){
        this.cos = cos;
        this.eps = cos.getEps();
    }
    private final Expression cos;

    @Override
    public Double evaluate(Double x) {
        return cos.evaluate (x - Math.PI / 2);
    }
}
