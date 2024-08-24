package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.util.Objects;

import net.minecraft.item.Item;
import net.minecraft.item.ItemAppleGold;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemBucketMilk;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.Packet;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
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
public class Class297
extends Class252 {
    private final Class257 mode = new Class257("Mode", "Vanilla", "Vanilla", "Grim");
    private final Class254 sword = new Class254("Sword", true);
    private final Class254 food = new Class254("Food", false);
    private final Class254 bow = new Class254("Bow", false);
    boolean slow;

    public Class297() {
        super("NoSlowDown", Class263.Movement);
    }

    private boolean isHoldingPotionAndSword(ItemStack stack, boolean checkPotionFood) {
        if (stack == null) {
            return false;
        }
        if (stack.getItem() instanceof ItemAppleGold && checkPotionFood) {
            return true;
        }
        if (stack.getItem() instanceof ItemPotion && checkPotionFood) {
            return !ItemPotion.isSplash(stack.getMetadata());
        }
        if (stack.getItem() instanceof ItemFood && checkPotionFood) {
            return true;
        }
        if (stack.getItem() instanceof ItemSword) {
            return true;
        }
        if (stack.getItem() instanceof ItemBow) {
            return checkPotionFood;
        }
        return stack.getItem() instanceof ItemBucketMilk && checkPotionFood;
    }

    @Class14
    public void onSlowDown(Class31 event) {
        if (this.isGapple()) {
            return;
        }
        ItemStack itemStack = Class297.mc.thePlayer.getHeldItem();
        event.setCancelled(itemStack.getItem() instanceof ItemAppleGold && !itemStack.getItem().hasEffect(itemStack) && !this.slow || this.isHoldingPotionAndSword(Class297.mc.thePlayer.getHeldItem(), false));
        if (Class297.mc.thePlayer.isUsingItem() && Class297.mc.thePlayer.moveForward > 0.0f) {
            Class297.mc.thePlayer.setSprinting(true);
        }
    }

    @Class14
    public void onPre(Class27 event) {
        if (this.isGapple()) {
            return;
        }
        if (this.isNull()) {
            return;
        }
        this.setSuffix("Drop");
        if (this.slow && !Class297.mc.thePlayer.isUsingItem()) {
            this.slow = false;
        }
        if (Objects.requireNonNull(this.mode.getMode()).equals("Grim") && !this.isFood() && this.isSlow() && !this.isFood()) {
            Class212.sendPacket(new Class333(Class297.mc.thePlayer.inventory.currentItem % 8 + 1));
            Class212.send1_12Block();
            Class212.sendPacket(new Class333(Class297.mc.thePlayer.inventory.currentItem));
        }
    }

    @Class14
    public void onPost(Class26 event) {
        if (this.isGapple()) {
            Class212.send1_12Block();
            return;
        }
        if (!this.isFood() && this.mode.getMode().equals("Grim") && this.isSlow() && !this.isFood() && !this.isBow() && Class297.mc.thePlayer.getHeldItem() != null && Class297.mc.thePlayer.getHeldItem().getItem() instanceof ItemSword) {
            Class212.send1_12Block();
        }
    }

    @Class14
    public void onPacketReceive(Class33 event) {
        if (this.isGapple()) {
            return;
        }
        Packet<?> packet = event.getPacket();
        ItemStack itemStack = Class297.mc.thePlayer.getHeldItem();
        if (Class297.mc.thePlayer != null && Class297.mc.theWorld != null && Class297.mc.theWorld.isRemote && Class297.mc.thePlayer.getHeldItem() != null && this.mode.is("Grim") && packet instanceof Class378 s2f) {
            if (itemStack.getItem() instanceof ItemAppleGold && !itemStack.getItem().hasEffect(itemStack) && s2f.getWindowId() == 0 && s2f.getItem().getItem() instanceof ItemAppleGold) {
                Class297.mc.thePlayer.inventory.getCurrentItem().stackSize = s2f.getItem().stackSize;
                event.setCancelled(true);
            }
        }
    }

    @Class14
    public void onPacketSend(Class35 event) {
        if (this.isGapple()) {
            return;
        }
        Packet<?> packet = event.getPacket();
        if (this.mode.is("Grim")) {
            if (Class297.mc.thePlayer == null || Class297.mc.theWorld == null || !Class297.mc.theWorld.isRemote || Class297.mc.thePlayer.getHeldItem() == null) {
                return;
            }
            ItemStack itemStack = Class297.mc.thePlayer.getHeldItem();
            if (itemStack != null && itemStack.getItem() instanceof ItemAppleGold && !itemStack.getItem().hasEffect(itemStack)) {
                if (packet instanceof Class355 && !this.slow) {
                    mc.getNetHandler().addToSendQueue(new Class365(Class365.Action.DROP_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN));
                    this.slow = true;
                }
                if (packet instanceof Class365 && ((Class365)packet).getStatus() == Class365.Action.RELEASE_USE_ITEM) {
                    event.setCancelled(true);
                    this.slow = true;
                }
            }
        }
    }

    public boolean isSlow() {
        if (this.isGapple()) {
            return false;
        }
        ItemStack heldItem = Class297.mc.thePlayer.getHeldItem();
        Item item = heldItem.getItem();
        return Class297.mc.thePlayer.isUsingItem() && (item instanceof ItemSword && this.sword.isEnabled() || item instanceof ItemFood && this.food.isEnabled() || item instanceof ItemBow && this.bow.isEnabled());
    }
}

