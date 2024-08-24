package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.Packet;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import tech.skidonion.obfuscator.annotations.StringEncryption;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
@StringEncryption
public class Class296
extends Class252 {
    private final Class254 sword = new Class254("Sword", true);
    private final Class254 food = new Class254("Food", false);
    private final Class254 bow = new Class254("Bow", false);
    public boolean dropped = false;

    public Class296() {
        super("Class297", Class263.Movement);
    }

    @Class14
    public void onSlow(Class31 event) {
        if (this.isNull()) {
            return;
        }
        if (this.isSlow()) {
            event.setCancelled(true);
        }
        Class296.mc.thePlayer.setSprinting(true);
    }

    @Class14
    public void onWorld(Class54 event) {
        if (this.isNull()) {
            return;
        }
        this.dropped = false;
    }

    @Class14
    public void onUpdate(Class50 event) {
        if (this.isSlow()) {
            if (!this.isBow() && !this.isFood()) {
                Class212.sendPacket(new Class365(Class365.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
                Class212.sendPacket(new Class360(0, 36, 0, 2, new ItemStack(Block.getBlockById(166)), (short) 0));
            }
            if (this.isBow()) {
                Class212.sendPacket(new Class333(Class296.mc.thePlayer.inventory.currentItem % 8 + 1));
                Class212.sendPacket(new Class355(Class296.mc.thePlayer.getHeldItem()));
                Class212.sendPacket(new Class333(Class296.mc.thePlayer.inventory.currentItem));
            }
        }
        if (this.dropped) {
            Class296.mc.gameSettings.keyBindUseItem.setPressed(false);
        }
        if (!Class296.mc.thePlayer.isUsingItem()) {
            this.dropped = false;
        }
    }

    @Class14
    public void onPostMotion(Class26 e) {
        if (this.isSlow() && !this.isFood()) {
            Class212.sendPacket(new Class355(Class296.mc.thePlayer.getHeldItem()));
            Class296.mc.playerController.sendUseItem(Class296.mc.thePlayer, Class296.mc.theWorld, Class296.mc.thePlayer.getHeldItem());
            if (Class296.mc.thePlayer.getHeldItem() != null && Class296.mc.thePlayer.getHeldItem().getItem() instanceof ItemSword && Class296.mc.thePlayer.isUsingItem()) {
                for (int i = 0; i < 8; ++i) {
                    Class212.send1_12Block();
                }
            }
        }
    }

    @Class14
    public void onPacketSend(Class35 event) {
        Packet<INetHandlerPlayServer> wrapper;
        Packet<?> packet = event.getPacket();
        if (packet instanceof Class355) {
            wrapper = (Class355)packet;
            if (this.food.isEnabled() && ((Class355)wrapper).getStack() != null && !(Class296.mc.theWorld.getBlockState(((Class355)wrapper).getPosition()).getBlock() instanceof BlockContainer) && ((Class355)wrapper).getStack().getItem() instanceof ItemFood && !this.dropped && ((Class355)wrapper).getStack().getStackSize() > 1) {
                event.setCancelled(true);
                Class212.sendPacket(new Class365(Class365.Action.DROP_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
                Class212.sendPacketNoEvent(new Class355(((Class355)wrapper).getStack()));
                this.dropped = true;
            }
        }
        if (this.food.isEnabled() && this.dropped && packet instanceof Class365 && ((Class365)(wrapper = (Class365)packet)).getStatus() == Class365.Action.RELEASE_USE_ITEM) {
            event.setCancelled(true);
        }
    }

    public boolean isSlow() {
        ItemStack heldItem = Class296.mc.thePlayer.getHeldItem();
        Item item = heldItem.getItem();
        return Class296.mc.thePlayer.isUsingItem() && (item instanceof ItemSword && this.sword.isEnabled() || item instanceof ItemFood && this.food.isEnabled() || item instanceof ItemBow && this.bow.isEnabled());
    }
}

