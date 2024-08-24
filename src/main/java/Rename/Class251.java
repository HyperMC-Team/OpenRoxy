package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.io.Serializable;

public class Class251
extends Class245
implements Serializable {
    public Class251(float x, float y, float z, float w) {
        super(x, y, z, w);
    }

    public Class251(float[] v) {
        super(v);
    }

    public Class251(Class251 v1) {
        super(v1);
    }

    public Class251(Class250 v1) {
        super(v1);
    }

    public Class251(Class244 t1) {
        super(t1);
    }

    public Class251(Class245 t1) {
        super(t1);
    }

    public Class251() {
    }

    public Class251(Class243 t1) {
        super(t1.x, t1.y, t1.z, 0.0f);
    }

    public final void set(Class243 t1) {
        this.set(t1.x, t1.y, t1.z, 0.0f);
    }

    public final float lengthSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
    }

    public final float length() {
        return (float)Math.sqrt(this.lengthSquared());
    }

    public final float dot(Class251 v1) {
        return this.x * v1.x + this.y * v1.y + this.z * v1.z + this.w * v1.w;
    }

    public final void normalize(Class250 v1) {
        this.set(v1);
        this.normalize();
    }

    public final void normalize() {
        double d = this.length();
        this.x = (float)((double)this.x / d);
        this.y = (float)((double)this.y / d);
        this.z = (float)((double)this.z / d);
        this.w = (float)((double)this.w / d);
    }

    public final float angle(Class251 v1) {
        double d = this.dot(v1);
        double v1_length = v1.length();
        double v_length = this.length();
        return (float)Math.acos(d / v1_length / v_length);
    }
}

