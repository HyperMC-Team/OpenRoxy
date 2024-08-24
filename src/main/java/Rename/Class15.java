package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.network.Packet;

public class Class15
implements Class3 {
    public boolean C08;
    public boolean C07;
    private boolean C02;
    public boolean C09;
    public boolean delayAttack;
    public boolean delay;
    public int playerSlot = -1;
    public int serverSlot = -1;

    @Class14(value=0)
    public void onSendPacket(Class35 e) {
        if (e.isCancelled()) {
            return;
        }
        if (e.getPacket() instanceof Class334) {
            if (this.C07) {
                e.setCancelled(true);
                return;
            }
            this.C02 = true;
        } else if (e.getPacket() instanceof Class355) {
            this.C08 = true;
        } else if (e.getPacket() instanceof Class365) {
            this.C07 = true;
        } else if (e.getPacket() instanceof Class333) {
            if (((Class333)e.getPacket()).getSlotId() == this.playerSlot && ((Class333)e.getPacket()).getSlotId() == this.serverSlot) {
                e.setCancelled(true);
                return;
            }
            this.C09 = true;
            this.serverSlot = this.playerSlot = ((Class333)e.getPacket()).getSlotId();
        }
    }

    @Class14
    public void onReceivePacket(Class33 e) {
        Packet<?> packet = e.getPacket();
        if (packet instanceof Class374 packet2) {
            if (packet2.getHeldItemHotbarIndex() >= 0 && packet2.getHeldItemHotbarIndex() < InventoryPlayer.getHotbarSize()) {
                this.serverSlot = packet2.getHeldItemHotbarIndex();
            }
        } else if (e.getPacket() instanceof Class368 && Class15.mc.thePlayer != null) {
            if (((Class368)e.getPacket()).getEntityID() != Class15.mc.thePlayer.getEntityId()) {
                return;
            }
            this.playerSlot = -1;
        }
    }

    @Class14(value=0)
    public void onPostUpdate(Class49 e) {
        if (this.delay) {
            this.delayAttack = false;
            this.delay = false;
        }
        if (this.C08 || this.C09) {
            this.delay = true;
            this.delayAttack = true;
        }
        this.C09 = false;
        this.C02 = false;
        this.C07 = false;
        this.C08 = false;
    }
}

