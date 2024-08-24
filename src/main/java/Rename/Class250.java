package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.io.Serializable;

public class Class250
extends Class244
implements Serializable {
    public Class250(double x, double y, double z, double w) {
        super(x, y, z, w);
    }

    public Class250(double[] v) {
        super(v);
    }

    public Class250(Class251 v1) {
        super(v1);
    }

    public Class250(Class250 v1) {
        super(v1);
    }

    public Class250(Class244 t1) {
        super(t1);
    }

    public Class250(Class245 t1) {
        super(t1);
    }

    public Class250() {
    }

    public Class250(Class242 t1) {
        super(t1.x, t1.y, t1.z, 0.0);
    }

    public final void set(Class242 t1) {
        this.set(t1.x, t1.y, t1.z, 0.0);
    }

    public final double lengthSquared() {
        return this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
    }

    public final double length() {
        return Math.sqrt(this.lengthSquared());
    }

    public final double dot(Class250 v1) {
        return this.x * v1.x + this.y * v1.y + this.z * v1.z + this.w * v1.w;
    }

    public final void normalize(Class250 v1) {
        this.set(v1);
        this.normalize();
    }

    public final void normalize() {
        double d = this.length();
        this.x /= d;
        this.y /= d;
        this.z /= d;
        this.w /= d;
    }

    public final double angle(Class250 v1) {
        double d = this.dot(v1);
        double v1_length = v1.length();
        double v_length = this.length();
        return Math.acos(d / v1_length / v_length);
    }
}

