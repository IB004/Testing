package org.s367118.math.result;

import lombok.AllArgsConstructor;
import org.s367118.math.Expression;
import org.s367118.math.result.Result;

import java.util.Locale;
import java.util.stream.Collectors;

@AllArgsConstructor
public class DesmosResultsWriter {
    private Expression expression;

    public String write(double from, double to, int n){
        String result = expression.evaluateRange(from, to, n)
                .map(res -> Double.isNaN(res.getY()) ? new Result(res.getX(), 0.0) : res)
                .map(res -> String.format(Locale.UK, "(%f, %f)", res.getX(), res.getY()))
                .collect(Collectors.joining (", "));
        return "[" + result + "]";
    }
}
