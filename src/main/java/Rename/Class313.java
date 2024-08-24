package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemSword;
import net.minecraft.network.Packet;
import tech.skidonion.obfuscator.annotations.StringEncryption;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
@StringEncryption
public class Class313
extends Class252 {
    private EntityLivingBase target;
    private int currentItem = 0;

    public Class313() {
        super("Class313", Class263.Combat);
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Class14
    public void onWorld(Class55 event) {
        this.setSuffix("");
    }

    @Class14
    public void onUpdate(Class50 event) {
        if (this.target != null) {
            EntityLivingBase entity = Class291.target;
            if (entity == this.target) {
                ArrayList slots = new ArrayList(Class313.mc.thePlayer.inventoryContainer.inventorySlots.stream().filter(slot -> slot.getHasStack() && slot.getStack().getItem() instanceof ItemSword).sorted(Comparator.comparingDouble(value -> Class219.getSwordStrength(((Slot)value).getStack())).reversed()).collect(Collectors.toList()));
                if (this.currentItem >= slots.size()) {
                    this.currentItem = 0;
                }
                Class313.mc.playerController.windowClick(0, ((Slot)slots.get(this.currentItem)).slotNumber, Class313.mc.thePlayer.inventory.currentItem, 2, Class313.mc.thePlayer);
                Class313.mc.thePlayer.swingItem();
                ++this.currentItem;
            } else {
                this.target = null;
            }
        }
    }

    @Class14
    public void onPacketSend(Class35 event) {
        Packet<?> packet = event.getPacket();
        if (packet instanceof Class334 packetUseEntity) {
            if (((Class334)event.getPacket()).getAction() == Class334.Action.ATTACK && packetUseEntity.getEntityFromWorld(Class313.mc.theWorld) instanceof EntityLivingBase) {
                if (this.target == null) {
                    this.currentItem = 0;
                }
                this.target = (EntityLivingBase)packetUseEntity.getEntityFromWorld(Class313.mc.theWorld);
            }
        }
    }
}

