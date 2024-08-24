package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

public class Class249 {
    private final double x;
    private final double y;
    private final double z;

    public Class249(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Class249 add(double x, double y, double z) {
        return new Class249(this.x + x, this.y + y, this.z + z);
    }

    public Class249 add(Class249 vector) {
        return this.add(vector.x, vector.y, vector.z);
    }

    public Class249 subtract(double x, double y, double z) {
        return this.add(-x, -y, -z);
    }

    public Class249 subtract(Class249 vector) {
        return this.add(-vector.x, -vector.y, -vector.z);
    }

    public double length() {
        return Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public Class249 multiply(double v) {
        return new Class249(this.x * v, this.y * v, this.z * v);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Class249 vector)) {
            return false;
        }
        return Math.floor(this.x) == Math.floor(vector.x) && Math.floor(this.y) == Math.floor(vector.y) && Math.floor(this.z) == Math.floor(vector.z);
    }
}

