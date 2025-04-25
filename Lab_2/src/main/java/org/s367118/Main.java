package org.s367118;

import org.s367118.math.logarithmic.Log;
import org.s367118.math.result.CsvResultsWriter;
import org.s367118.math.result.DesmosResultsWriter;
import org.s367118.math.Expression;
import org.s367118.math.logarithmic.Ln;
import org.s367118.math.system.ExpressionSystem;
import org.s367118.math.system.LogarithmicPart;
import org.s367118.math.system.TrigonometricPart;
import org.s367118.math.trigonometric.*;

import java.io.IOException;


public class Main {
    public static void main(String[] args) throws IOException {

        Expression ln = new Ln(1e-4);
        Expression cos = new Cos(1e-4);
        Expression sin = new Sin(cos);
        Expression tan = new Tan(sin, cos);
        Expression cot = new Cot(cos, sin);
        Expression sec = new Sec(cos);
        Expression csc = new Csc(sin);

        Expression logarithmicPart = new LogarithmicPart(new Log(3.0, ln), new Log(5.0, ln));
        Expression trigonometricPart = new TrigonometricPart(cos, sin, tan, cot, sec, csc);
        Expression system = new ExpressionSystem(trigonometricPart, logarithmicPart);

        DesmosResultsWriter desmosResultsWriter = new DesmosResultsWriter(system);
        System.out.println(desmosResultsWriter.write(-10, 10, 100));

        CsvResultsWriter csvResultsWriter = new CsvResultsWriter(system);
        csvResultsWriter.write("results/system.csv", -15.0, 15.0, 10000);
    }
}