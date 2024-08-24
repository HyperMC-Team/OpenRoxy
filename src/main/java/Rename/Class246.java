package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

public final class Class246 {
    public double x;
    public double y;

    public Class246() {
    }

    public Class246(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Class246 offset(double x, double y) {
        return new Class246(this.x + x, this.y + y);
    }

    public Class246 offset(Class246 xy) {
        return this.offset(xy.x, xy.y);
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}

