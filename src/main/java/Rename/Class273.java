package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.util.Iterator;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.item.EntityBoat;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.network.Packet;
import net.minecraft.util.AxisAlignedBB;
import tech.skidonion.obfuscator.annotations.StringEncryption;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
@StringEncryption
public class Class273
extends Class252 {
    private final Class257 mode = new Class257("Mode", "Grim", "Watchdog", "Grim", "AutoJump");
    private final Class257 watchdogmode = new Class257("WatchDog Mode", "Ground", "Glide", "Glide2", "Ground", "Test");
    private final Class254 lagbackcheck = new Class254("LagBackCheck", true);
    public final Class254 strafe = new Class254("Grim-Strafe", false);
    private final Class254 scaffoldCheck = new Class254("Class278 Check", false);
    private final Class254 blinkCheck = new Class254("Class304 Check", false);
    private int inAirTicks;

    public Class273() {
        super("Class273", Class263.Movement);
        this.watchdogmode.addParent(this.mode, modeSetting -> this.mode.is("Watchdog"));
        this.strafe.addParent(this.mode, modeSetting -> this.mode.is("Grim"));
    }

    @Override
    public void onEnable() {
        this.inAirTicks = 0;
        if (Class273.mc.thePlayer == null) {
            return;
        }
        super.onEnable();
    }

    @Override
    public void onDisable() {
        Class273.mc.timer.timerSpeed = 1.0f;
        super.onDisable();
    }

    @Class14
    private void onPacketReceive(Class33 event) {
        Packet<?> packet = event.getPacket();
        if (packet instanceof Class351 && this.lagbackcheck.isEnabled()) {
            Class135.post(Class136.WARNING, "Class273", "Class273 Disabled in LagBack");
            this.setState(false);
        }
    }

    @Class14
    private void onUpdate(Class50 event) {
        this.setSuffix(this.mode.getMode());
        if (this.blinkCheck.isEnabled() && Class262.getModule(Class304.class).isState() || this.scaffoldCheck.isEnabled() && Class262.getModule(Class278.class).isState()) {
            return;
        }
        if (this.mode.is("Watchdog")) {
            if (this.watchdogmode.is("Ground")) {
                if (Class273.mc.thePlayer.onGround && Class210.isMoving()) {
                    Class273.mc.thePlayer.jump();
                    Class210.strafe(0.45);
                }
            } else if (this.watchdogmode.is("Glide")) {
                if (Class210.isMoving()) {
                    if (Class273.mc.thePlayer.offGroundTicks == 10 && Class210.isOnGround(0.769)) {
                        Class273.mc.thePlayer.motionY = 0.0;
                    }
                    if (Class210.isOnGround(0.769) && Class273.mc.thePlayer.offGroundTicks >= 9) {
                        Class210.strafe(0.29);
                    }
                    if (Class273.mc.thePlayer.onGround) {
                        if (Class273.mc.gameSettings.keyBindForward.isPressed()) {
                            Class210.strafe(0.28);
                        } else {
                            Class210.strafe(0.45);
                        }
                        Class273.mc.thePlayer.jump();
                    }
                }
            } else if (this.watchdogmode.is("Glide2")) {
                if (Class210.isMoving()) {
                    if ((Class273.mc.thePlayer.offGroundTicks == 10 || Class273.mc.thePlayer.offGroundTicks == 11) && Class210.isOnGround(0.769)) {
                        Class273.mc.thePlayer.motionY = 0.0;
                        Class210.strafe(0.15);
                    }
                    if (Class273.mc.thePlayer.onGround) {
                        if (Class273.mc.gameSettings.keyBindForward.pressed) {
                            Class210.strafe(0.28);
                        } else {
                            Class210.strafe(0.45);
                        }
                        Class273.mc.thePlayer.jump();
                    }
                }
            } else if (this.watchdogmode.is("Test")) {
                if (Class273.mc.thePlayer.onGround) {
                    if (Class210.isMoving()) {
                        Class273.mc.thePlayer.jump();
                        Class210.setSpeed(Class210.getBaseMoveSpeed() * 1.6);
                        this.inAirTicks = 0;
                    }
                } else {
                    ++this.inAirTicks;
                    if (this.inAirTicks == 1) {
                        Class210.setSpeed(Class210.getBaseMoveSpeed() * 1.16);
                    }
                }
            }
        } else if (this.mode.is("AutoJump") && Class210.isMoving() && Class273.mc.thePlayer.onGround && Class273.mc.thePlayer.isCollidedVertically) {
            Class273.mc.thePlayer.jump();
        }
    }

    @Class14
    private void onPreMotion(Class27 event) {
        if (this.blinkCheck.isEnabled() && Class262.getModule(Class304.class).isState() || this.scaffoldCheck.isEnabled() && Class262.getModule(Class278.class).isState()) {
            return;
        }
        if (Class291.target != null && !Class262.getModule(Class292.class).cantCrit(Class291.target)) {
            return;
        }
        if (this.mode.is("Grim")) {
            AxisAlignedBB playerBox = Class273.mc.thePlayer.boundingBox.expand(1.0, 1.0, 1.0);
            int c = 0;
            Iterator entitys = Class273.mc.theWorld.loadedEntityList.iterator();
            while (true) {
                if (!entitys.hasNext() && !Class262.getModule(Class292.class).isState()) {
                    if (c > 0 && Class210.isMoving()) {
                        double strafeOffset = (double)Math.min(c, 3) * 0.08;
                        float yaw = this.getMoveYaw();
                        double mx = -Math.sin(Math.toRadians(yaw));
                        double mz = Math.cos(Math.toRadians(yaw));
                        Class273.mc.thePlayer.addVelocity(mx * strafeOffset, 0.0, mz * strafeOffset);
                        Class273.mc.gameSettings.keyBindLeft.pressed = c < 4 && Class291.target != null && this.shouldFollow() || GameSettings.isKeyDown(Class273.mc.gameSettings.keyBindLeft);
                        return;
                    }
                    Class273.mc.gameSettings.keyBindLeft.pressed = GameSettings.isKeyDown(Class273.mc.gameSettings.keyBindLeft);
                    return;
                }
                Entity entity = (Entity)entitys.next();
                if (!(entity instanceof EntityLivingBase) && !(entity instanceof EntityBoat) && !(entity instanceof EntityMinecart) && !(entity instanceof EntityFishHook) || entity instanceof EntityArmorStand || entity.getEntityId() == Class273.mc.thePlayer.getEntityId() || !playerBox.intersectsWith(entity.boundingBox) || entity.getEntityId() == -8 || entity.getEntityId() == -1337 || Class262.getModule(Class304.class).isState()) continue;
                ++c;
            }
        }
    }

    public boolean shouldFollow() {
        return this.isState() && Class273.mc.gameSettings.keyBindJump.isKeyDown();
    }

    private float getMoveYaw() {
        EntityPlayerSP thePlayer = Class273.mc.thePlayer;
        float moveYaw = thePlayer.rotationYaw;
        if (thePlayer.moveForward != 0.0f && thePlayer.moveStrafing == 0.0f) {
            moveYaw += thePlayer.moveForward > 0.0f ? 0.0f : 180.0f;
        } else if (thePlayer.moveForward != 0.0f) {
            moveYaw = thePlayer.moveForward > 0.0f ? (moveYaw += thePlayer.moveStrafing > 0.0f ? -45.0f : 45.0f) : (moveYaw -= thePlayer.moveStrafing > 0.0f ? -45.0f : 45.0f);
            moveYaw += thePlayer.moveForward > 0.0f ? 0.0f : 180.0f;
        } else if (thePlayer.moveStrafing != 0.0f) {
            moveYaw += thePlayer.moveStrafing > 0.0f ? -70.0f : 70.0f;
        }
        if (Class291.target != null && Class273.mc.gameSettings.keyBindJump.isKeyDown()) {
            moveYaw = Class20.rotations.x;
        }
        return moveYaw;
    }
}

