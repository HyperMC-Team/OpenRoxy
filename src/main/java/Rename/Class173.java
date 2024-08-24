package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

public final class Class173 {
    private float x;
    private float y;

    public Class173() {
        this(0.0f, 0.0f);
    }

    public Class173(Class173 vec) {
        this(vec.x, vec.y);
    }

    public Class173(double x, double y) {
        this((float)x, (float)y);
    }

    public Class173(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Class173 setX(float x) {
        this.x = x;
        return this;
    }

    public Class173 setY(float y) {
        this.y = y;
        return this;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public Class173 add(Class173 vec) {
        return new Class173(this.x + vec.x, this.y + vec.y);
    }

    public Class173 add(double x, double y) {
        return this.add(new Class173(x, y));
    }

    public Class173 add(float x, float y) {
        return this.add(new Class173(x, y));
    }

    public Class173 sub(Class173 vec) {
        return new Class173(this.x - vec.x, this.y - vec.y);
    }

    public Class173 sub(double x, double y) {
        return this.sub(new Class173(x, y));
    }

    public Class173 sub(float x, float y) {
        return this.sub(new Class173(x, y));
    }

    public Class173 scale(float scale) {
        return new Class173(this.x * scale, this.y * scale);
    }

    public Class174 toVec3() {
        return new Class174(this.x, this.y, 0.0);
    }

    public Class173 copy() {
        return new Class173(this);
    }

    public Class173 transfer(Class173 vec) {
        this.x = vec.x;
        this.y = vec.y;
        return this;
    }

    public float distanceTo(Class173 vec) {
        double dx = this.x - vec.x;
        double dy = this.y - vec.y;
        return (float)Math.sqrt(dx * dx + dy * dy);
    }

    public Class174 toScreen() {
        return Class169.toWorld(this.toVec3());
    }

    public String toString() {
        return "Vec2{x=" + this.x + ", y=" + this.y + "}";
    }
}

