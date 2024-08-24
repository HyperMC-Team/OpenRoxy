package Rename;

public class Class480
extends Class459 {
    public Class480(int ms, double endPoint) {
        super(ms, endPoint);
    }

    public Class480(int ms, double endPoint, Class473 direction) {
        super(ms, endPoint, direction);
    }

    @Override
    protected double getEquation(double x) {
        return -2.0 * Math.pow(x, 3.0) + 3.0 * Math.pow(x, 2.0);
    }
}

