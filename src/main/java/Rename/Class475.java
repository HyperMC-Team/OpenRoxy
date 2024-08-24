package Rename;

import java.util.Random;

public class Class475 {
    private long lastMS;

    public long getCurrentMS() {
        return System.nanoTime() / 1000000L;
    }

    public boolean hasReached(double milliseconds) {
        return (double)(this.getCurrentMS() - this.lastMS) >= milliseconds;
    }

    public static long randomDelay(int minDelay, int maxDelay) {
        return Class475.nextInt(minDelay, maxDelay);
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

