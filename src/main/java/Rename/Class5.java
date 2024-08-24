package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

public abstract class Class5
implements Class7,
        Class9 {
    private final byte type;

    protected Class5(byte eventType) {
        this.type = eventType;
    }

    @Override
    public byte getType() {
        return this.type;
    }
}

