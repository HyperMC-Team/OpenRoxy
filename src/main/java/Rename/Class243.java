package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.io.Serializable;

public abstract class Class243
implements Serializable {
    public float x;
    public float y;
    public float z;

    public Class243(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Class243(float[] t) {
        this.x = t[0];
        this.y = t[1];
        this.z = t[2];
    }

    public Class243(Class243 t1) {
        this.x = t1.x;
        this.y = t1.y;
        this.z = t1.z;
    }

    public Class243(Class242 t1) {
        this.x = (float)t1.x;
        this.y = (float)t1.y;
        this.z = (float)t1.z;
    }

    public Class243() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
    }

    public final void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public final void set(float[] t) {
        this.x = t[0];
        this.y = t[1];
        this.z = t[2];
    }

    public final void set(Class243 t1) {
        this.x = t1.x;
        this.y = t1.y;
        this.z = t1.z;
    }

    public final void set(Class242 t1) {
        this.x = (float)t1.x;
        this.y = (float)t1.y;
        this.z = (float)t1.z;
    }

    public final void get(float[] t) {
        t[0] = this.x;
        t[1] = this.y;
        t[2] = this.z;
    }

    public final void get(Class243 t) {
        t.x = this.x;
        t.y = this.y;
        t.z = this.z;
    }

    public final void add(Class243 t1, Class243 t2) {
        this.x = t1.x + t2.x;
        this.y = t1.y + t2.y;
        this.z = t1.z + t2.z;
    }

    public final void add(Class243 t1) {
        this.x += t1.x;
        this.y += t1.y;
        this.z += t1.z;
    }

    public final void sub(Class243 t1, Class243 t2) {
        this.x = t1.x - t2.x;
        this.y = t1.y - t2.y;
        this.z = t1.z - t2.z;
    }

    public final void sub(Class243 t1) {
        this.x -= t1.x;
        this.y -= t1.y;
        this.z -= t1.z;
    }

    public final void negate(Class243 t1) {
        this.x = -t1.x;
        this.y = -t1.y;
        this.z = -t1.z;
    }

    public final void negate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
    }

    public final void scale(float s, Class243 t1) {
        this.x = s * t1.x;
        this.y = s * t1.y;
        this.z = s * t1.z;
    }

    public final void scale(float s) {
        this.x *= s;
        this.y *= s;
        this.z *= s;
    }

    public final void scaleAdd(float s, Class243 t1, Class243 t2) {
        this.x = s * t1.x + t2.x;
        this.y = s * t1.y + t2.y;
        this.z = s * t1.z + t2.z;
    }

    public final void scaleAdd(float s, Class243 t1) {
        this.x = s * this.x + t1.x;
        this.y = s * this.y + t1.y;
        this.z = s * this.z + t1.z;
    }

    public int hashCode() {
        int xbits = Float.floatToIntBits(this.x);
        int ybits = Float.floatToIntBits(this.y);
        int zbits = Float.floatToIntBits(this.z);
        return xbits ^ ybits ^ zbits;
    }

    public boolean equals(Class243 t1) {
        return t1 != null && this.x == t1.x && this.y == t1.y && this.z == t1.z;
    }

    public boolean epsilonEquals(Class243 t1, float epsilon) {
        return Math.abs(t1.x - this.x) <= epsilon && Math.abs(t1.y - this.y) <= epsilon && Math.abs(t1.z - this.z) <= epsilon;
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ")";
    }

    public final void clamp(float min, float max, Class243 t) {
        this.set(t);
        this.clamp(min, max);
    }

    public final void clampMin(float min, Class243 t) {
        this.set(t);
        this.clampMin(min);
    }

    public final void clampMax(float max, Class243 t) {
        this.set(t);
        this.clampMax(max);
    }

    public final void absolute(Class243 t) {
        this.set(t);
        this.absolute();
    }

    public final void clamp(float min, float max) {
        this.clampMin(min);
        this.clampMax(max);
    }

    public final void clampMin(float min) {
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

    public final void clampMax(float max) {
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

    public final void absolute() {
        if ((double)this.x < 0.0) {
            this.x = -this.x;
        }
        if ((double)this.y < 0.0) {
            this.y = -this.y;
        }
        if ((double)this.z < 0.0) {
            this.z = -this.z;
        }
    }

    public final void interpolate(Class243 t1, Class243 t2, float alpha) {
        this.set(t1);
        this.interpolate(t2, alpha);
    }

    public final void interpolate(Class243 t1, float alpha) {
        float beta = 1.0f - alpha;
        this.x = beta * this.x + alpha * t1.x;
        this.y = beta * this.y + alpha * t1.y;
        this.z = beta * this.z + alpha * t1.z;
    }

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return this.z;
    }

    public void setZ(float z) {
        this.z = z;
    }
}

