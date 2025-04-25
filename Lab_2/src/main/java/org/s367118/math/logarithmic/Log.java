package org.s367118.math.logarithmic;

import org.s367118.math.Expression;


public class Log extends Expression {

    public Log(Double base, Expression ln){
        this.ln = ln;
        this.lnBase = ln.evaluate(base);
        this.eps = ln.getEps();
    }
    private final Expression ln;
    private final Double lnBase;

    @Override
    public Double evaluate(Double x) {
        if (isZero(lnBase))
            return Double.NaN;

        return ln.evaluate(x) / lnBase;
    }
}