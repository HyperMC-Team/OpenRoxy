package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.io.Serializable;

public abstract class Class242
implements Serializable {
    public double x;
    public double y;
    public double z;

    public Class242(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Class242(double[] t) {
        this.x = t[0];
        this.y = t[1];
        this.z = t[2];
    }

    public Class242(Class242 t1) {
        this.x = t1.x;
        this.y = t1.y;
        this.z = t1.z;
    }

    public Class242(Class243 t1) {
        this.x = t1.x;
        this.y = t1.y;
        this.z = t1.z;
    }

    public Class242() {
        this.x = 0.0;
        this.y = 0.0;
        this.z = 0.0;
    }

    public final void set(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public final void set(double[] t) {
        this.x = t[0];
        this.y = t[1];
        this.z = t[2];
    }

    public final void set(Class242 t1) {
        this.x = t1.x;
        this.y = t1.y;
        this.z = t1.z;
    }

    public final void set(Class243 t1) {
        this.x = t1.x;
        this.y = t1.y;
        this.z = t1.z;
    }

    public final void get(double[] t) {
        t[0] = this.x;
        t[1] = this.y;
        t[2] = this.z;
    }

    public final void get(Class242 t) {
        t.x = this.x;
        t.y = this.y;
        t.z = this.z;
    }

    public final void add(Class242 t1, Class242 t2) {
        this.x = t1.x + t2.x;
        this.y = t1.y + t2.y;
        this.z = t1.z + t2.z;
    }

    public final void add(Class242 t1) {
        this.x += t1.x;
        this.y += t1.y;
        this.z += t1.z;
    }

    public final void sub(Class242 t1, Class242 t2) {
        this.x = t1.x - t2.x;
        this.y = t1.y - t2.y;
        this.z = t1.z - t2.z;
    }

    public final void sub(Class242 t1) {
        this.x -= t1.x;
        this.y -= t1.y;
        this.z -= t1.z;
    }

    public final void negate(Class242 t1) {
        this.x = -t1.x;
        this.y = -t1.y;
        this.z = -t1.z;
    }

    public final void negate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
    }

    public final void scale(double s, Class242 t1) {
        this.x = s * t1.x;
        this.y = s * t1.y;
        this.z = s * t1.z;
    }

    public final void scale(double s) {
        this.x *= s;
        this.y *= s;
        this.z *= s;
    }

    public final void scaleAdd(double s, Class242 t1, Class242 t2) {
        this.x = s * t1.x + t2.x;
        this.y = s * t1.y + t2.y;
        this.z = s * t1.z + t2.z;
    }

    public final void scaleAdd(double s, Class242 t1) {
        this.x = s * this.x + t1.x;
        this.y = s * this.y + t1.y;
        this.z = s * this.z + t1.z;
    }

    public int hashCode() {
        long xbits = Double.doubleToLongBits(this.x);
        long ybits = Double.doubleToLongBits(this.y);
        long zbits = Double.doubleToLongBits(this.z);
        return (int)(xbits ^ xbits >> 32 ^ ybits ^ ybits >> 32 ^ zbits ^ zbits >> 32);
    }

    public boolean equals(Class242 t1) {
        return t1 != null && this.x == t1.x && this.y == t1.y && this.z == t1.z;
    }

    public boolean epsilonEquals(Class242 t1, double epsilon) {
        return Math.abs(t1.x - this.x) <= epsilon && Math.abs(t1.y - this.y) <= epsilon && Math.abs(t1.z - this.z) <= epsilon;
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ")";
    }

    public final void absolute(Class242 t) {
        this.set(t);
        this.absolute();
    }

    public final void absolute() {
        if (this.x < 0.0) {
            this.x = -this.x;
        }
        if (this.y < 0.0) {
            this.y = -this.y;
        }
        if (this.z < 0.0) {
            this.z = -this.z;
        }
    }

    public final void clamp(float min, float max) {
        this.clampMin(min);
        this.clampMax(max);
    }

    public final void clamp(float min, float max, Class242 t) {
        this.set(t);
        this.clamp(min, max);
    }

    public final void clampMin(float min) {
        if (this.x < (double)min) {
            this.x = min;
        }
        if (this.y < (double)min) {
            this.y = min;
        }
        if (this.z < (double)min) {
            this.z = min;
        }
    }

    public final void clampMin(float min, Class242 t) {
        this.set(t);
        this.clampMin(min);
    }

    public final void clampMax(float max, Class242 t) {
        this.set(t);
        this.clampMax(max);
    }

    public final void clampMax(float max) {
        if (this.x > (double)max) {
            this.x = max;
        }
        if (this.y > (double)max) {
            this.y = max;
        }
        if (this.z > (double)max) {
            this.z = max;
        }
    }

    public final void clamp(double min, double max) {
        this.clampMin(min);
        this.clampMax(max);
    }

    public final void clamp(double min, double max, Class242 t) {
        this.set(t);
        this.clamp(min, max);
    }

    public final void clampMin(double min) {
        if (this.x < min) {
            this.x = min;
        }
        if (this.y < min) {
            this.y = min;
        }
        if (this.z < min) {
            this.z = min;
        }
    }

    public final void clampMin(double min, Class242 t) {
        this.set(t);
        this.clampMin(min);
    }

    public final void clampMax(double max, Class242 t) {
        this.set(t);
        this.clampMax(max);
    }

    public final void clampMax(double max) {
        if (this.x > max) {
            this.x = max;
        }
        if (this.y > max) {
            this.y = max;
        }
        if (this.z > max) {
            this.z = max;
        }
    }

    public final void interpolate(Class242 t1, Class242 t2, float alpha) {
        this.set(t1);
        this.interpolate(t2, alpha);
    }

    public final void interpolate(Class242 t1, float alpha) {
        float beta = 1.0f - alpha;
        this.x = (double)beta * this.x + (double)alpha * t1.x;
        this.y = (double)beta * this.y + (double)alpha * t1.y;
        this.z = (double)beta * this.z + (double)alpha * t1.z;
    }

    public final void interpolate(Class242 t1, Class242 t2, double alpha) {
        this.set(t1);
        this.interpolate(t2, alpha);
    }

    public final void interpolate(Class242 t1, double alpha) {
        double beta = 1.0 - alpha;
        this.x = beta * this.x + alpha * t1.x;
        this.y = beta * this.y + alpha * t1.y;
        this.z = beta * this.z + alpha * t1.z;
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return this.z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}

