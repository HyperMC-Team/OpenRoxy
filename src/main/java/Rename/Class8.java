package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

public abstract class Class8
implements Class7 {
    private boolean stopped;

    protected Class8() {
    }

    public void stop() {
        this.stopped = true;
    }

    public boolean isStopped() {
        return this.stopped;
    }
}

