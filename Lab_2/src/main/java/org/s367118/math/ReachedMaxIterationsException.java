package org.s367118.math;

public class ReachedMaxIterationsException extends RuntimeException{
    public ReachedMaxIterationsException(String message, double eps, int maxIterations){
        super(formatMessage(message, eps, maxIterations));
    }

    private static String formatMessage(String message, double eps, int maxIterations){
        return String.format(
                "%s: reached max %d iterations while trying to evaluate expression with eps = %f.",
                message, maxIterations, eps);
    }
}
