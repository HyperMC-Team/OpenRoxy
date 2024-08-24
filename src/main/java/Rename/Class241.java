package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.util.Random;

public class Class241 {
    private final long time = -1L;
    private long lastMS;
    private final boolean run = true;

    public long getCurrentMS() {
        return System.nanoTime() / 1000000L;
    }

    public long getTimeElapsed() {
        return System.currentTimeMillis() - this.lastMS;
    }

    public void setTimeElapsed(long time) {
        this.lastMS = System.currentTimeMillis() - time;
    }

    public long getTimePassed() {
        return System.currentTimeMillis() - this.lastMS;
    }

    public boolean hasTimePassed(long time) {
        return System.currentTimeMillis() - this.lastMS >= time;
    }

    public boolean hasReached(double milliseconds) {
        return (double)(this.getCurrentMS() - this.lastMS) >= milliseconds;
    }

    public long hasTimeLeft(long ms) {
        return ms + this.lastMS - System.currentTimeMillis();
    }

    public static long randomDelay(int minDelay, int maxDelay) {
        return Class241.nextInt(minDelay, maxDelay);
    }

    public static int nextInt(int startInclusive, int endExclusive) {
        return endExclusive - startInclusive <= 0 ? startInclusive : startInclusive + new Random().nextInt(endExclusive - startInclusive);
    }

    public int convertToMS(int d) {
        return 1000 / d;
    }

    public long getElapsedTime() {
        return System.currentTimeMillis() - this.lastMS;
    }

    public void reset() {
        this.lastMS = this.getCurrentMS();
    }

    public boolean delay(float milliSec) {
        return (float)(this.getCurrentMS() - this.lastMS) >= milliSec;
    }

    public boolean isDelayComplete(double valueState) {
        return (double)(System.currentTimeMillis() - this.lastMS) >= valueState;
    }

    public double getLastDelay() {
        return this.getCurrentMS() - this.getLastMS();
    }

    public long getLastMS() {
        return this.lastMS;
    }

    public final long getDifference() {
        return this.getCurrentMS() - this.lastMS;
    }

    public final boolean hasPassed(long milliseconds) {
        return this.getCurrentMS() - this.lastMS > milliseconds;
    }

    public boolean hasTimeElapsed(long time, boolean reset) {
        if (System.currentTimeMillis() - this.lastMS > time) {
            if (reset) {
                this.reset();
            }
            return true;
        }
        return false;
    }

    public boolean hasTimeElapsed2(long milliseconds) {
        return this.run && this.getElapsedTime() >= milliseconds;
    }

    public boolean hasTimeElapsed(long time) {
        return System.currentTimeMillis() - this.lastMS > time;
    }

    public long getLastMs() {
        return this.lastMS;
    }

    public void setTime(long time) {
        this.lastMS = time;
    }
}

