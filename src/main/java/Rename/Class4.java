package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

public abstract class Class4
implements Class7,
        Class6 {
    private boolean cancelled;

    protected Class4() {
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean state) {
        this.cancelled = state;
    }

    @Override
    public void setCancelled() {
        this.cancelled = true;
    }
}

