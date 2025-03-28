package org.s367118.arccos;

public class ArcCos {
    public ArcCos(int termsCount){
        this.termsCount = termsCount;
    }
    int termsCount;

    // https://pro-prof.com/wp-content/uploads/2015/10/arccos.png
    public double count(double x){
        if (Math.abs(x) > 1 || termsCount <= 0)
            return Double.NaN;

        double sum = 0;
        double _2nf = 1;
        double _nf = 1;
        double i = 1;
        for (int n = 0; n < termsCount; n++) {
            double term = Math.pow(x, 2*n + 1) * _nf / (_2nf * (2*n + 1));
            sum += term;
            _nf *= i++;
            _2nf *= i++;
        }
        return Math.PI / 2 - sum;
    }

}
