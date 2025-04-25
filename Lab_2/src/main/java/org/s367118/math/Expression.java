package org.s367118.math;

import lombok.Getter;
import org.s367118.math.result.Result;

import java.util.stream.Stream;

public abstract class Expression {
    @Getter
    protected double eps = 0;
    public final static int MAX_ITERATIONS = 100000;
    public abstract Double evaluate(Double x);

    public Stream<Result> evaluateRange(Double from, Double to, Integer n){
        if (from >= to)
            throw new IllegalArgumentException(String.format("Range from %f to %f.", from, to));
        if (n < 2)
            throw new IllegalArgumentException(String.format("Argument n is %d, but should be greater 2.", n));
        double step = (to - from) / (n-1);
        return Stream.iterate(from, x -> x <= to + step/2, x -> x + step).map(x -> new Result(x, evaluate(x)));
    }

    public Boolean isZero(Double x){
        return Math.abs(x) <= 1e-5;
    }
}
