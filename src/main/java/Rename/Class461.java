package Rename;

import Rename.Class231;

public class Class461 {
    private long previousTime = System.nanoTime() / 10000L;
    public static long deltaTime = 500L;

    public void resetTime() {
        long currentTime = System.nanoTime() / 10000L;
        deltaTime = currentTime - this.previousTime;
        this.previousTime = currentTime;
    }

    public static float moveUD(float current, float end, float smoothSpeed, float minSpeed) {
        float movement = (end - current) * smoothSpeed;
        if (movement > 0.0f) {
            movement = Math.max(minSpeed, movement);
            movement = Math.min(end - current, movement);
        } else if (movement < 0.0f) {
            movement = Math.min(-minSpeed, movement);
            movement = Math.max(end - current, movement);
        }
        return current + movement;
    }

    public static float animate1(float value, float target, float speed) {
        float increment;
        float returnValue;
        if (deltaTime <= 1L) {
            deltaTime = 500L;
        }
        if ((double)Math.abs(target - (returnValue = value + (target - value) * (increment = (speed *= 70.0f) * (float)deltaTime / 500.0f) / 200.0f)) < 0.04000000000000001) {
            return target;
        }
        return returnValue;
    }

    public double animate(double value, double target, double speed, boolean tBTV) {
        double increment;
        double returnValue;
        if (deltaTime <= 1L) {
            deltaTime = 500L;
        }
        if (!(Math.abs(target - (returnValue = value + (target - value) * (increment = (speed = speed * Double.valueOf(10.0) / 4.0) * (double)deltaTime / 500.0) / 200.0)) < 0.05 * (4.0 / Double.valueOf(10.0)))) {
            if (tBTV ? returnValue > target : target > returnValue) {
                return target;
            }
            return returnValue;
        }
        return target;
    }

    public static float moveUDFaster(float current, float end) {
        boolean larger;
        float smoothSpeed = Class231.processFPS(0.025f);
        float minSpeed = Class231.processFPS(0.023f);
        boolean bl = larger = end > current;
        if (smoothSpeed < 0.0f) {
            smoothSpeed = 0.0f;
        } else if (smoothSpeed > 1.0f) {
            smoothSpeed = 1.0f;
        }
        if (minSpeed < 0.0f) {
            minSpeed = 0.0f;
        } else if (minSpeed > 1.0f) {
            minSpeed = 1.0f;
        }
        float movement = (end - current) * smoothSpeed;
        if (movement > 0.0f) {
            movement = Math.max(minSpeed, movement);
            movement = Math.min(end - current, movement);
        } else if (movement < 0.0f) {
            movement = Math.min(-minSpeed, movement);
            movement = Math.max(end - current, movement);
        }
        if (larger ? end <= current + movement : end >= current + movement) {
            return end;
        }
        return current + movement;
    }

    public double animateNoFast(double value, double target, double speed) {
        double increment;
        double returnValue;
        if (deltaTime <= 1L) {
            deltaTime = 500L;
        }
        return Math.abs(target - (returnValue = value + (target - value) * (increment = (speed = speed * Double.valueOf(10.0) / 4.0) * (double)deltaTime / 500.0) / 200.0)) < 0.05 * (4.0 / Double.valueOf(10.0)) ? target : returnValue;
    }

    public static float animate(float value, float target, float speed) {
        float increment;
        float returnValue;
        if (deltaTime <= 1L) {
            deltaTime = 500L;
        }
        return (double)Math.abs(target - (returnValue = value + (target - value) * (increment = (speed *= 70.0f) * (float)deltaTime / 500.0f) / 200.0f)) < 0.05 * (4.0 / Double.valueOf(10.0)) ? target : returnValue;
    }

    public double animateRO(double value, double target, double speed) {
        double increment;
        double returnValue;
        if (deltaTime <= 1L) {
            deltaTime = 500L;
        }
        return Math.abs(target - (returnValue = value + (target - value) * (increment = speed * (double)deltaTime / 500.0) / 200.0)) < 0.05 ? target : returnValue;
    }

    public static float animateSmooth(float current, float target, float speed) {
        boolean larger;
        if (current == target) {
            return current;
        }
        boolean bl = larger = target > current;
        if (speed < 0.0f) {
            speed = 0.0f;
        } else if (speed > 1.0f) {
            speed = 1.0f;
        }
        double dif = Math.max(target, (double)current) - Math.min(target, (double)current);
        double factor = dif * (double)speed;
        if (factor < 0.1) {
            factor = 0.1;
        }
        if (larger) {
            if ((current += (float)factor) >= target) {
                current = target;
            }
        } else if (target < current && (current -= (float)factor) <= target) {
            current = target;
        }
        return current;
    }
}

