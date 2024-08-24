package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

public class Class239 {
    private long millis;

    public void setMillis(long millis) {
        this.millis = millis;
    }

    public Class239() {
        this.reset();
    }

    public boolean finished(long delay) {
        return System.currentTimeMillis() - delay >= this.millis;
    }

    public void reset() {
        this.millis = System.currentTimeMillis();
    }

    public long getMillis() {
        return this.millis;
    }

    public long getElapsedTime() {
        return System.currentTimeMillis() - this.millis;
    }
}

