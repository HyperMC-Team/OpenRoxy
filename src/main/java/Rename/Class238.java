package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

public final class Class238 {
    private long time = -1L;

    public boolean hasTimePassed(long MS) {
        return System.currentTimeMillis() >= this.time + MS;
    }

    public long hasTimeLeft(long MS) {
        return MS + this.time - System.currentTimeMillis();
    }

    public void reset() {
        this.time = System.currentTimeMillis();
    }
}

