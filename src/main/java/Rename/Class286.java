package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import net.minecraft.network.Packet;
import tech.skidonion.obfuscator.annotations.Renamer;
import tech.skidonion.obfuscator.annotations.StringEncryption;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
@Renamer
@StringEncryption
public class Class286
extends Class252 {
    private final Class259 delay = new Class259("Delay", 1000.0, 5000.0, 30.0, 1.0);
    private final List<Packet<?>> packetList = new CopyOnWriteArrayList();
    private final Class241 timer = new Class241();

    public Class286() {
        super("Class286", Class263.Misc);
    }

    @Class14
    private void onPacketSend(Class35 event) {
        if (Class286.mc.thePlayer != null && Class286.mc.theWorld != null && Class286.mc.thePlayer.isServerWorld()) {
            if (event.getPacket() instanceof Class359 && Class286.mc.thePlayer.isEntityAlive()) {
                this.packetList.add(event.getPacket());
                event.setCancelled();
            }
            if (this.timer.hasTimePassed(this.delay.getValue().intValue())) {
                if (!this.packetList.isEmpty()) {
                    for (Packet<?> packet : this.packetList) {
                        Class212.sendPacketNoEvent(packet);
                        this.packetList.remove(packet);
                    }
                }
                Class212.sendPacketNoEvent(new Class359(10000));
                this.timer.reset();
            }
        }
    }
}

