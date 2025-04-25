package org.s367118.math.logarithmic;

import org.s367118.math.Expression;
import org.s367118.math.ReachedMaxIterationsException;

public class Ln extends Expression {
    final double ln2;

    public Ln(double eps){
        this.eps = eps;
        this.ln2 = evaluate(2.0);
    }
    @Override
    public Double evaluate(Double x) {
        if (x <= 0)
            return Double.NaN;

        if (x > 2)
            return ln2 + evaluate(x / 2);

        double res = 0;
        double term = Double.MAX_VALUE;

        int n = 1;
        int sign = 1;
        while(Math.abs(term) >= eps){
            term = sign * Math.pow(x-1, n) / n;
            res += term;

            n += 1;
            sign *= -1;

            if (n > MAX_ITERATIONS)
                throw new ReachedMaxIterationsException(String.format("Ln(%f)", x), eps, MAX_ITERATIONS);
        }
        return res;
    }
}
