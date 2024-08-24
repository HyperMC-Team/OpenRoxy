package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.io.Serializable;

public abstract class Class245
implements Serializable {
    public float x;
    public float y;
    public float z;
    public float w;

    public Class245(float x, float y, float z, float w) {
        this.set(x, y, z, w);
    }

    public Class245(float[] t) {
        this.set(t);
    }

    public Class245(Class245 t1) {
        this.set(t1);
    }

    public Class245(Class244 t1) {
        this.set(t1);
    }

    public Class245() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
        this.w = 0.0f;
    }

    public final void set(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public final void set(float[] t) {
        this.x = t[0];
        this.y = t[1];
        this.z = t[2];
        this.w = t[3];
    }

    public final void set(Class245 t1) {
        this.x = t1.x;
        this.y = t1.y;
        this.z = t1.z;
        this.w = t1.w;
    }

    public final void set(Class244 t1) {
        this.x = (float)t1.x;
        this.y = (float)t1.y;
        this.z = (float)t1.z;
        this.w = (float)t1.w;
    }

    public final void get(float[] t) {
        t[0] = this.x;
        t[1] = this.y;
        t[2] = this.z;
        t[3] = this.w;
    }

    public final void get(Class245 t) {
        t.x = this.x;
        t.y = this.y;
        t.z = this.z;
        t.w = this.w;
    }

    public final void add(Class245 t1, Class245 t2) {
        this.x = t1.x + t2.x;
        this.y = t1.y + t2.y;
        this.z = t1.z + t2.z;
        this.w = t1.w + t2.w;
    }

    public final void add(Class245 t1) {
        this.x += t1.x;
        this.y += t1.y;
        this.z += t1.z;
        this.w += t1.w;
    }

    public final void sub(Class245 t1, Class245 t2) {
        this.x = t1.x - t2.x;
        this.y = t1.y - t2.y;
        this.z = t1.z - t2.z;
        this.w = t1.w - t2.w;
    }

    public final void sub(Class245 t1) {
        this.x -= t1.x;
        this.y -= t1.y;
        this.z -= t1.z;
        this.w -= t1.w;
    }

    public final void negate(Class245 t1) {
        this.x = -t1.x;
        this.y = -t1.y;
        this.z = -t1.z;
        this.w = -t1.w;
    }

    public final void negate() {
        this.x = -this.x;
        this.y = -this.y;
        this.z = -this.z;
        this.w = -this.w;
    }

    public final void scale(float s, Class245 t1) {
        this.x = s * t1.x;
        this.y = s * t1.y;
        this.z = s * t1.z;
        this.w = s * t1.w;
    }

    public final void scale(float s) {
        this.x *= s;
        this.y *= s;
        this.z *= s;
        this.w *= s;
    }

    public final void scaleAdd(float s, Class245 t1, Class245 t2) {
        this.x = s * t1.x + t2.x;
        this.y = s * t1.y + t2.y;
        this.z = s * t1.z + t2.z;
        this.w = s * t1.w + t2.w;
    }

    public final void scaleAdd(float s, Class245 t1) {
        this.x = s * this.x + t1.x;
        this.y = s * this.y + t1.y;
        this.z = s * this.z + t1.z;
        this.w = s * this.z + t1.w;
    }

    public int hashCode() {
        return Float.floatToIntBits(this.x) ^ Float.floatToIntBits(this.y) ^ Float.floatToIntBits(this.z) ^ Float.floatToIntBits(this.w);
    }

    public boolean equals(Class245 t1) {
        return t1 != null && this.x == t1.x && this.y == t1.y && this.z == t1.z && this.w == t1.w;
    }

    public boolean epsilonEquals(Class245 t1, float epsilon) {
        return Math.abs(t1.x - this.x) <= epsilon && Math.abs(t1.y - this.y) <= epsilon && Math.abs(t1.z - this.z) <= epsilon && Math.abs(t1.w - this.w) <= epsilon;
    }

    public String toString() {
        return "(" + this.x + ", " + this.y + ", " + this.z + ", " + this.w + ")";
    }

    public final void clamp(float min, float max, Class245 t) {
        this.set(t);
        this.clamp(min, max);
    }

    public final void clampMin(float min, Class245 t) {
        this.set(t);
        this.clampMin(min);
    }

    public final void clampMax(float max, Class245 t) {
        this.set(t);
        this.clampMax(max);
    }

    public final void absolute(Class245 t) {
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
        if (this.w < min) {
            this.w = min;
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
        if (this.w > max) {
            this.w = max;
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
        if ((double)this.w < 0.0) {
            this.w = -this.w;
        }
    }

    public final void interpolate(Class245 t1, Class245 t2, float alpha) {
        this.set(t1);
        this.interpolate(t2, alpha);
    }

    public final void interpolate(Class245 t1, float alpha) {
        float beta = 1.0f - alpha;
        this.x = beta * this.x + alpha * t1.x;
        this.y = beta * this.y + alpha * t1.y;
        this.z = beta * this.z + alpha * t1.z;
        this.w = beta * this.w + alpha * t1.w;
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

    public float getW() {
        return this.w;
    }

    public void setW(float w) {
        this.w = w;
    }
}

