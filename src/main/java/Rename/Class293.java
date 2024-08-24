package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
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
public class Class293
extends Class252 {
    private final Class259 fov = new Class259("Fov", 180.0, 180.0, 90.0, 0.1);
    private final Class259 range = new Class259("Range", 8.0, 10.0, 3.0, 0.1);
    private final Class254 fishRod = new Class254("FishRod", false);
    private final Class259 rotspeed = new Class259("RotationSpeed", 10.0, 10.0, 0.0, 1.0);
    private final Class259 ticks = new Class259("Ticks", 3.0, 10.0, 1.0, 1.0);
    public static final List<EntityPlayer> targets = new ArrayList<EntityPlayer>();
    public static EntityPlayer target;
    public static int tick;
    public static boolean isThrowOut;

    public Class293() {
        super("Class293", Class263.Combat);
    }

    @Class14
    public void onWorld(Class55 event) {
        target = null;
    }

    @Class14
    public void onMotionEvent(Class27 event) {
        if (Class293.mc.thePlayer == null || Class293.mc.theWorld == null) {
            return;
        }
        targets.sort(Comparator.comparingDouble(Class293.mc.thePlayer::getDistanceToEntity));
        target = !targets.isEmpty() ? targets.getFirst() : null;
        for (Entity entity : Class293.mc.theWorld.loadedEntityList) {
            if (!(entity instanceof EntityFishHook fishHook)) continue;
            if (!isThrowOut || (fishHook.caughtEntity == null || fishHook.caughtEntity != target) && !entity.onGround) continue;
            isThrowOut = false;
            Class293.mc.thePlayer.sendQueue.addToSendQueue(new Class333(Class293.mc.thePlayer.inventory.currentItem));
        }
        if (isThrowOut && (Class291.target != null || Class262.getModule(Class278.class).isState() || Class262.getModule(Class304.class).isState())) {
            isThrowOut = false;
            Class293.mc.thePlayer.sendQueue.addToSendQueue(new Class333(Class293.mc.thePlayer.inventory.currentItem));
        }
        if (isThrowOut || Class293.findBall() == -1 && (Class293.findFishRod() == -1 || !this.fishRod.isEnabled()) || Class291.target != null || Class262.getModule(Class278.class).isState() || Class262.getModule(Class304.class).isState()) {
            return;
        }
        for (Entity entity : Class293.mc.theWorld.loadedEntityList) {
            if (!(entity instanceof EntityPlayer) || !((double) Class293.mc.thePlayer.getDistanceToEntity(entity) <= this.range.getValue()) || Class293.mc.thePlayer == entity || Class290.isSameTeam(entity)) continue;
            targets.add((EntityPlayer)entity);
        }
        long delay = this.ticks.getValue().longValue() + 1L;
        if (target != null && (double) Class293.mc.thePlayer.getDistanceToEntity(target) <= this.range.getValue() && Class237.getRotationDifference(target) <= this.fov.getValue() && Class293.mc.thePlayer.canEntityBeSeen(target)) {
            Class247 rotation = Class237.getThrowRotation(target, this.range.getValue().floatValue());
            Class20.setRotations(rotation, this.rotspeed.getValue(), Class167.NORMAL);
            if ((long)(++tick) > delay) {
                if (Class293.findBall() != -1) {
                    Class293.mc.thePlayer.sendQueue.addToSendQueue(new Class333(Class293.findBall() - 36));
                    Class293.mc.thePlayer.sendQueue.addToSendQueue(new Class355(Class293.mc.thePlayer.inventory.getStackInSlot(Class293.findBall() - 36)));
                    if (Class293.mc.thePlayer.isUsingItem()) {
                        mc.getNetHandler().addToSendQueue(new Class355(Class293.mc.thePlayer.getHeldItem()));
                    }
                } else if (Class293.findFishRod() != -1 && !isThrowOut && this.fishRod.isEnabled()) {
                    Class293.mc.thePlayer.sendQueue.addToSendQueue(new Class333(Class293.findFishRod() - 36));
                    Class293.mc.thePlayer.sendQueue.addToSendQueue(new Class355(Class293.mc.thePlayer.inventory.getStackInSlot(Class293.findFishRod() - 36)));
                    isThrowOut = true;
                }
                target = null;
                targets.clear();
                tick = 0;
            }
        } else {
            tick = 0;
        }
    }

    public static int findFishRod() {
        for (int i = 36; i < 45; ++i) {
            ItemStack itemStack = Class293.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
            if (itemStack == null || itemStack.getItem() != Items.fishing_rod) continue;
            return i;
        }
        return -1;
    }

    public static int findBall() {
        for (int i = 36; i < 45; ++i) {
            ItemStack itemStack = Class293.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
            if (itemStack == null || itemStack.getItem() != Items.snowball && itemStack.getItem() != Items.egg || itemStack.stackSize <= 0) continue;
            return i;
        }
        return -1;
    }

    static {
        tick = 0;
        isThrowOut = false;
    }
}

