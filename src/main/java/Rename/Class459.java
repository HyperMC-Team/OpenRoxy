package Rename;

public abstract class Class459 {
    public Class483 timerUtil;
    protected int duration;
    protected double endPoint;
    protected Class473 direction;
    private Class226 easing;
    private long startTime;

    public Class459(int ms, double endPoint) {
        this(ms, endPoint, Class473.FORWARDS);
    }

    public Class459(Class226 easing, long duration) {
        this.easing = easing;
        this.startTime = System.currentTimeMillis();
        this.duration = (int)duration;
    }

    public Class459(int ms, double endPoint, Class473 direction) {
        this.timerUtil = new Class483();
        this.duration = ms;
        this.endPoint = endPoint;
        this.direction = direction;
    }

    public boolean finished(Class473 direction) {
        return this.isDone() && this.direction.equals(direction);
    }

    public double getLinearOutput() {
        return 1.0 - (double)this.timerUtil.getTime() / (double)this.duration * this.endPoint;
    }

    public double getEndPoint() {
        return this.endPoint;
    }

    public void setEndPoint(double endPoint) {
        this.endPoint = endPoint;
    }

    public void reset() {
        this.timerUtil.reset();
    }

    public boolean isDone() {
        return this.timerUtil.hasTimeElapsed(this.duration);
    }

    public void changeDirection() {
        this.setDirection(this.direction.opposite());
    }

    public Class473 getDirection() {
        return this.direction;
    }

    public void setState(boolean sb) {
        if (sb) {
            this.setDirection(Class473.FORWARDS);
        } else {
            this.setDirection(Class473.BACKWARDS);
        }
    }

    public boolean isState() {
        return this.direction.forwards();
    }

    public Class459 setDirection(Class473 direction) {
        if (this.direction != direction) {
            this.direction = direction;
            this.timerUtil.setTime(System.currentTimeMillis() - ((long)this.duration - Math.min(this.duration, this.timerUtil.getTime())));
        }
        return this;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    protected boolean correctOutput() {
        return false;
    }

    public double getOutput() {
        if (this.direction.forwards()) {
            return this.isDone() ? this.endPoint : this.getEquation((double)this.timerUtil.getTime() / (double)this.duration) * this.endPoint;
        }
        if (this.isDone()) {
            return 0.0;
        }
        if (this.correctOutput()) {
            double revTime = Math.min(this.duration, Math.max(0L, (long)this.duration - this.timerUtil.getTime()));
            return this.getEquation(revTime / (double)this.duration) * this.endPoint;
        }
        return (1.0 - this.getEquation((double)this.timerUtil.getTime() / (double)this.duration)) * this.endPoint;
    }

    protected abstract double getEquation(double var1);
}

