package org.s367118.math.system;

import org.s367118.math.Expression;
import org.s367118.math.logarithmic.Log;

public class LogarithmicPart extends Expression {

    public LogarithmicPart(Expression log3, Expression log5){
        this.log3 = log3;
        this.log5 = log5;
        this.eps = log3.getEps();
    }

    private final Expression log3;
    private final Expression log5;


    @Override
    public Double evaluate(Double x) {
        Double log_3_x = log3.evaluate(x);
        Double log_5_x = log5.evaluate(x);

        if (isZero(log_3_x))
            return Double.NaN;

        return Math.pow((Math.pow((log_5_x / log_3_x), 3) - log_3_x), 2) * log_3_x;
    }
}
