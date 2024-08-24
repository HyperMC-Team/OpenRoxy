package Rename;

public class Class464
extends Class459 {
    private final float easeAmount;

    public Class464(int ms, double endPoint, float easeAmount) {
        super(ms, endPoint);
        this.easeAmount = easeAmount;
    }

    public Class464(int ms, double endPoint, float easeAmount, Class473 direction) {
        super(ms, endPoint, direction);
        this.easeAmount = easeAmount;
    }

    @Override
    protected boolean correctOutput() {
        return true;
    }

    @Override
    protected double getEquation(double x) {
        float shrink = this.easeAmount + 1.0f;
        return Math.max(0.0, 1.0 + (double)shrink * Math.pow(x - 1.0, 3.0) + (double)this.easeAmount * Math.pow(x - 1.0, 2.0));
    }
}

