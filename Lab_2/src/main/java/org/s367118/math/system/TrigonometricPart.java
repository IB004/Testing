package org.s367118.math.system;

import org.s367118.math.Expression;

public class TrigonometricPart extends Expression {

    public TrigonometricPart(
            Expression cos,
            Expression sin,
            Expression tan,
            Expression cot,
            Expression sec,
            Expression csc) {
        this.cos = cos;
        this.sin = sin;
        this.tan = tan;
        this.cot = cot;
        this.sec = sec;
        this.csc = csc;
        this.eps = Math.max(sin.getEps(), cos.getEps());
    }
    private final Expression cos;
    private final Expression sin;
    private final Expression tan;
    private final Expression cot;
    private final Expression sec;
    private final Expression csc;


    @Override
    public Double evaluate(Double x) {
        Double sin_x = sin.evaluate(x);
        Double cos_x = cos.evaluate(x);
        Double tan_x = tan.evaluate(x);
        Double cot_x = cot.evaluate(x);
        Double sec_x = sec.evaluate(x);
        Double csc_x = csc.evaluate(x);

        if(isZero(Math.abs(x) % (2 * Math.PI) - 1.5 * Math.PI ))
            return 1.0;

        if (isZero(sin_x) || (sin_x < 0 && isZero(cos_x)))
            return Double.NaN;

        return (((((((((cot_x + sin_x) * tan_x) / sin_x) / cos_x) + cos_x) + Math.pow((cot_x - cot_x), 3)) / sec_x) - (sec_x - (cos_x / (tan_x / sec_x)))) * csc_x);
    }
}
