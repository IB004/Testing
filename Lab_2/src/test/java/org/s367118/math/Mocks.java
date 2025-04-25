package org.s367118.math;

import java.util.*;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Mocks {

    private final static Double[][] lnValues = {
        {-1.0, Double.NaN},
        {0.0, Double.NaN},
        {0.1, -2.3025},
        {1.0, 0.0},
        {2.0, 0.6931},
        {3.0, 1.0986},
        {5.0, 1.6094},
        {8.0, 2.0794},
        {10.0, 2.3025},
    };

    private final static Double[][] log3Values = {
        {-1.0, Double.NaN},
        {0.0, Double.NaN},
        {1.0, 0.0},
        {3.0, 1.0},
        {5.0, 1.4649},
    };

    private final static Double[][] log5Values = {
        {-1.0, Double.NaN},
        {0.0, Double.NaN},
        {1.0, 0.0},
        {3.0, 0.6826},
        {5.0, 1.0},
    };

    private final static Double[][] cosValues = {
        {-100.0 - Math.PI/2, 0.5063},
        {-100.0, 0.8623},
        {-8.0 - Math.PI/2, -0.9893},
        {-8.0, -0.1455},
        {-6.28318530718 - Math.PI/2, 0.0},
        {-6.28318530718, 1.0},
        {-4.71238898038 - Math.PI/2, 1.0},
        {-4.71238898038, 0.0},
        {-3.14159265359 - Math.PI/2, 0.0},
        {-3.14159265359, -1.0},
        {-1.57079632679 - Math.PI/2, -1.0},
        {-1.57079632679, 0.0},
        {-1.0 - Math.PI/2, -0.8414},
        {-1.0, 0.5403},
        {0.0 - Math.PI/2, 0.0},
        {0.0, 1.0},
        {1.0 - Math.PI/2, 0.8414},
        {1.0, 0.5403},
    };

    public static Expression getLnMock(){
        // return getMock(Arrays.stream(lnValues).toList());
        List<Double[]> results = CsvXYReader.read("src/test/resources/ln.csv");
        return getMock(results);
    }
    public static Expression getLog3Mock(){
        return getMock(Arrays.stream(log3Values).toList());
    }
    public static Expression getLog5Mock(){
        return getMock(Arrays.stream(log5Values).toList());
    }

    public static Expression getCosMock(){
        return getMock(Arrays.stream(cosValues).toList());
    }
    public static Expression getSinMock(){
        List<Double[]> results = CsvXYReader.read("src/test/resources/simple/sin.csv");
        return getMock(results);
    }
    public static Expression getTanMock(){
        List<Double[]> results = CsvXYReader.read("src/test/resources/simple/tan.csv");
        return getMock(results);
    }
    public static Expression getCotMock(){
        List<Double[]> results = CsvXYReader.read("src/test/resources/simple/cot.csv");
        return getMock(results);
    }
    public static Expression getSecMock(){
        List<Double[]> results = CsvXYReader.read("src/test/resources/simple/sec.csv");
        return getMock(results);
    }
    public static Expression getCscMock(){
        List<Double[]> results = CsvXYReader.read("src/test/resources/simple/csc.csv");
        return getMock(results);
    }

    public static Expression getTrigonometricPartMock(){
        List<Double[]> results = CsvXYReader.read("src/test/resources/simple/trigonometricPart.csv");
        return getMock(results);
    }
    public static Expression getLogarithmicMock(){
        List<Double[]> results = CsvXYReader.read("src/test/resources/simple/logarithmicPart.csv");
        return getMock(results);
    }
    private static Expression getMock(List<Double[]> values){
        Expression mock = mock();
        for (Double[] entry : values) {
            when(mock.evaluate(entry[0])).thenReturn(entry[1]);
        }
        return mock;
    }
}
