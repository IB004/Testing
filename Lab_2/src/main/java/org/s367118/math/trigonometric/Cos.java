package org.s367118.math.trigonometric;

import org.s367118.math.Expression;
import org.s367118.math.ReachedMaxIterationsException;

public class Cos extends Expression {
    public Cos(double eps){
        this.eps = eps;
    }
    @Override
    public Double evaluate(Double x) {
        if (Math.abs(x) > 25)
            return evaluate(x % (2 * Math.PI));

        double res = 0;
        double term = Double.MAX_VALUE;

        int n = 0;
        double factorOf2n = 1;
        int sign = 1;
        while(Math.abs(term) >= eps){
            term = sign * Math.pow(x, 2*n) / factorOf2n;
            res += term;

            n += 1;
            factorOf2n *= (2*n - 1) * 2*n;
            sign *= -1;

            if (n > MAX_ITERATIONS)
                throw new ReachedMaxIterationsException(String.format("Cos(%f)", x), eps, MAX_ITERATIONS);
        }
        return res;
    }
}
