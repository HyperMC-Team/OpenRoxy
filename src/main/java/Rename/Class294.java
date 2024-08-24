package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityTNTPrimed;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.network.Packet;
import net.minecraft.util.ChatComponentText;
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
public class Class294
extends Class252 {
    private final Class257 mode = new Class257("Mode", "GrimAC", "GrimAC", "Watchdog");
    private final Class254 invalidEntity = new Class254("Attack Invalid Entity", true);
    private final Class254 debug = new Class254("Debug", true);
    public boolean shouldVelo;
    Entity target;

    public Class294() {
        super("Class294", Class263.Combat);
        this.invalidEntity.addParent(this.mode, modeSetting -> this.mode.is("GrimAC"));
        this.debug.addParent(this.mode, modeSetting -> this.mode.is("GrimAC"));
    }

    @Class14
    public void onReceive(Class33 event) {
        Class349 s12;
        Packet<?> packet;
        if (this.isNull()) {
            return;
        }
        if (this.mode.is("GrimAC") && (packet = event.getPacket()) instanceof Class349) {
            s12 = (Class349)packet;
            double strength = new Class249(s12.getMotionX(), s12.getMotionY(), s12.getMotionZ()).length();
            if (s12.getEntityID() == Class294.mc.thePlayer.getEntityId() && !Class294.mc.thePlayer.isOnLadder() && !Class294.mc.thePlayer.isInWeb) {
                this.target = this.getNearTarget();
                if (this.target == null) {
                    return;
                }
                if (Class294.mc.thePlayer.getDistanceToEntity(this.target) > 3.3f) {
                    this.reset();
                    return;
                }
                this.shouldVelo = true;
                if (this.debug.isEnabled()) {
                    Class294.mc.thePlayer.addChatMessage(new ChatComponentText("[" + ChatFormatting.RED + "Received Class294" + ChatFormatting.RESET + "]" + strength + " " + (Class294.mc.thePlayer.onGround ? "on Ground" : "on Air") + (this.target != null ? " - Distance: " + Class294.mc.thePlayer.getClosestDistanceToEntity(this.target) : "")));
                }
            }
        }
        if (this.mode.is("Watchdog")) {
            this.setSuffix("Watchdog");
            packet = event.getPacket();
            if (packet instanceof Class349) {
                s12 = (Class349)packet;
                if (Class294.mc.thePlayer != null && s12.getEntityID() == Class294.mc.thePlayer.getEntityId()) {
                    s12.motionX *= 0;
                    s12.motionZ *= 0;
                }
            }
        }
    }

    @Class14
    public void onWorld(Class55 event) {
        if (this.mode.is("GrimAC")) {
            this.reset();
        }
    }

    private Entity getNearTarget() {
        Entity target = null;
        EntityLivingBase clientTarget = Class291.target;
        if (clientTarget != null) {
            target = clientTarget;
            return target;
        }
        for (Entity entity : Class294.mc.theWorld.loadedEntityList) {
            EntityArrow entityArrow;
            if (entity.equals(Class294.mc.thePlayer) || entity.isDead || !this.invalidEntity.isEnabled()) continue;
            if (entity instanceof EntityArrow && (entityArrow = (EntityArrow)entity).getTicksInGround() <= 0) {
                target = entityArrow;
            }
            if (entity instanceof EntitySnowball) {
                target = entity;
            }
            if (entity instanceof EntityEgg) {
                target = entity;
            }
            if (entity instanceof EntityTNTPrimed) {
                target = entity;
            }
            if (!(entity instanceof EntityFishHook)) continue;
            target = entity;
        }
        return target;
    }

    @Class14
    public void onUpdate(Class50 event) {
        if (this.isNull()) {
            return;
        }
        if (this.mode.is("GrimAC") && this.shouldVelo) {
            if (Class294.mc.thePlayer.hurtTime == 0) {
                this.reset();
                return;
            }
            if ((double) Class294.mc.thePlayer.getDistanceToEntity(this.target) > 3.0) {
                this.reset();
                return;
            }
            if (Class262.getModule(Class264.class).isState() && Class262.getModule(Class264.class).noC02 && Class264.eating) {
                return;
            }
            for (int i = 0; i < 5; ++i) {
                if (!Class294.mc.thePlayer.serverSprintState) {
                    Class212.sendPacket(new Class364(Class294.mc.thePlayer, Class364.Action.START_SPRINTING));
                    Class294.mc.thePlayer.setSprinting(true);
                    Class294.mc.thePlayer.serverSprintState = true;
                }
                if (Class262.getModule(Class264.class).isState() && Class262.getModule(Class264.class).noC02 && Class264.eating) {
                    return;
                }
                Class212.sendPacket(new Class331());
                Class212.sendPacket(new Class334(this.target, Class334.Action.ATTACK));
                Class294.mc.thePlayer.motionX *= 0.6f;
                Class294.mc.thePlayer.motionZ *= 0.6f;
            }
            this.shouldVelo = false;
        }
    }

    private void reset() {
        this.shouldVelo = false;
        this.target = null;
    }
}

