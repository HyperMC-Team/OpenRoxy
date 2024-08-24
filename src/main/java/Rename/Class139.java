package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

public abstract class Class139 {
    public Class150 timerUtil = new Class150();
    protected int duration;
    protected double endPoint;
    protected Class141 direction;

    public Class139(int ms, double endPoint) {
        this(ms, endPoint, Class141.FORWARDS);
    }

    public Class139(int ms, double endPoint, Class141 direction) {
        this.duration = ms;
        this.endPoint = endPoint;
        this.direction = direction;
    }

    public boolean finished(Class141 direction) {
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

    public Class141 getDirection() {
        return this.direction;
    }

    public Class139 setDirection(Class141 direction) {
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

    public Double getOutput() {
        if (this.direction.forwards()) {
            if (this.isDone()) {
                return this.endPoint;
            }
            return this.getEquation((double)this.timerUtil.getTime() / (double)this.duration) * this.endPoint;
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

