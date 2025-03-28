package org.s367118.arccos;

import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public class RangeProvider {
    public static Stream<Double> range(double start, double end, double step){
        return DoubleStream.iterate(start, d -> d <= end, d -> d + step).boxed();
    }
}
