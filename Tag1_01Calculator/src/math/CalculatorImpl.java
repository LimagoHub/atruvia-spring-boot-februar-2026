package math;

public class CalculatorImpl implements Calculator {

    @Override
    public double add(double a, double b) {
        return a + b;
    }

    @Override
    public double substract(double a, double b) {
        return add(a, -b);
    }

    @Override
    public double multiply(final double a, final double b) {
        return a * b;
    }
}
