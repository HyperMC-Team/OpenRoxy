package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import net.minecraft.network.Packet;

public class Class211
implements Class3 {
    public static final Class211 INSTANCE = new Class211();
    public static int noMovePackets = 0;

    public static void packetEvent(Packet<?> packet) {
        if (packet instanceof Class354) {
            noMovePackets = ((Class354)packet).isMoving() ? 0 : (noMovePackets = noMovePackets + 1);
        }
    }
}

