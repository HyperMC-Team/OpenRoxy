package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;

public class Class33
extends Class4 {
    private final Packet<?> packet;
    private final INetHandler netHandler;

    public Packet<?> getPacket() {
        return this.packet;
    }

    public INetHandler getNetHandler() {
        return this.netHandler;
    }

    public Class33(Packet<?> packet, INetHandler netHandler) {
        this.packet = packet;
        this.netHandler = netHandler;
    }
}

