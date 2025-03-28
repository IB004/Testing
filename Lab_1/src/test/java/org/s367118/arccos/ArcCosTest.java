package org.s367118.arccos;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Random;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ArcCosTest {
    private static final int TERMS_COUNT = 150;


    @ParameterizedTest
    @CsvFileSource(files="src/test/resources/arccos.csv")
    public void tableTest(double x, double answer) {
        ArcCos arcCos = new ArcCos(TERMS_COUNT);

        double result = arcCos.count(x);

        Assertions.assertEquals(answer, result, 0.05);
    }


    @ParameterizedTest
    @MethodSource("middleRangeProvider")
    public void middleRangeTest(double x) {
        ArcCos arcCos = new ArcCos(TERMS_COUNT);

        double result = arcCos.count(x);

        Assertions.assertEquals(Math.acos(x), result, 0.001);
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("leftRangeProvider")
    public void leftRangeTest(double x) {
        ArcCos arcCos = new ArcCos(TERMS_COUNT);

        double result = arcCos.count(x);

        Assertions.assertEquals(Math.acos(x), result, 0.05);
    }

    @ParameterizedTest(name = "[{index}] {0}")
    @MethodSource("rightRangeProvider")
    public void rightRangeTest(double x) {
        ArcCos arcCos = new ArcCos(TERMS_COUNT);

        double result = arcCos.count(x);

        Assertions.assertEquals(Math.acos(x), result, 0.05);
    }
    private Stream<Double> middleRangeProvider(){
        return RangeProvider.range(-0.9,  0.9, 0.1);
    }

    private Stream<Double> leftRangeProvider(){
        return RangeProvider.range(-1.2,  -0.9, 0.05);
    }

    private Stream<Double> rightRangeProvider(){
        return RangeProvider.range(0.9,  1.2, 0.05);
    }

    @ParameterizedTest
    @ValueSource(ints = {-10, -2, -1, 0})
    public void negativeOrZeroTermsTest(int terms){
        ArcCos arcCos = new ArcCos(terms);
        double rand = -1 + 2 * new Random().nextDouble();

        double result = arcCos.count(rand);

        Assertions.assertEquals(Double.NaN, result);
    }
}
