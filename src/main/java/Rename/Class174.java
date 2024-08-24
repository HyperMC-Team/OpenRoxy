package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

public final class Class174 {
    private double x;
    private double y;
    private double z;

    public Class174() {
        this(0.0, 0.0, 0.0);
    }

    public Class174(Class174 vec) {
        this(vec.x, vec.y, vec.z);
    }

    public Class174(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Class174 setX(double x) {
        this.x = x;
        return this;
    }

    public Class174 setY(double y) {
        this.y = y;
        return this;
    }

    public Class174 setZ(double z) {
        this.z = z;
        return this;
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

    public Class174 add(Class174 vec) {
        return this.add(vec.x, vec.y, vec.z);
    }

    public Class174 add(double x, double y, double z) {
        return new Class174(this.x + x, this.y + y, this.z + z);
    }

    public Class174 sub(Class174 vec) {
        return new Class174(this.x - vec.x, this.y - vec.y, this.z - vec.z);
    }

    public Class174 sub(double x, double y, double z) {
        return new Class174(this.x - x, this.y - y, this.z - z);
    }

    public Class174 scale(float scale) {
        return new Class174(this.x * (double)scale, this.y * (double)scale, this.z * (double)scale);
    }

    public Class174 copy() {
        return new Class174(this);
    }

    public Class174 transfer(Class174 vec) {
        this.x = vec.x;
        this.y = vec.y;
        this.z = vec.z;
        return this;
    }

    public double distanceTo(Class174 vec) {
        double dx = this.x - vec.x;
        double dy = this.y - vec.y;
        double dz = this.z - vec.z;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    public Class173 rotationsTo(Class174 vec) {
        double[] diff = new double[]{vec.x - this.x, vec.y - this.y, vec.z - this.z};
        double hDist = Math.sqrt(diff[0] * diff[0] + diff[2] * diff[2]);
        return new Class173(Math.toDegrees(Math.atan2(diff[2], diff[0])) - 90.0, -Math.toDegrees(Math.atan2(diff[1], hDist)));
    }

    public Class174 toScreen() {
        return Class169.toScreen(this);
    }

    public String toString() {
        return "Vec3{x=" + this.x + ", y=" + this.y + ", z=" + this.z + "}";
    }
}

