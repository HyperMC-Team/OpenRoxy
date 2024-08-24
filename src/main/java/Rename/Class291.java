package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.awt.Color;
import java.util.concurrent.atomic.AtomicBoolean;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSword;
import net.minecraft.network.Packet;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
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
@NativeObfuscation
public class Class291
extends Class252 {
    private final Class257 attackmode = new Class257("AttackMode", "Post", "Post", "Pre", "Tick");
    private final Class259 maxcps = new Class259("MaxCPS", 8.0, 20.0, 0.0, 1.0);
    private final Class259 mincps = new Class259("MinCPS", 6.0, 20.0, 0.0, 1.0);
    private final Class259 startrange = new Class259("StartRange", 3.3f, 6.0, 1.0, 0.1f);
    private final Class259 range = new Class259("Range", 3.0, 6.0, 1.0, 0.1f);
    private final Class259 rotationspeed = new Class259("RotationSpeed", 10.0, 10.0, 0.0, 1.0);
    private final Class257 autoblockmods = new Class257("AutoBlockMods", "Off", "GrimAC", "Class76", "Off");
    private final Class257 moveFix = new Class257("Class167", "Silent", "Silent", "Strict", "None", "BackSprint");
    private final Class257 espmode = new Class257("ESPMode", "None", "Jello", "Box", "None", "Nursultan");
    private final Class254 keepsprint = new Class254("KeepSprint", true);
    private final Class254 autodis = new Class254("AutoDisable", true);
    public AtomicBoolean block = new AtomicBoolean();
    public final Class241 attackTimer = new Class241();
    private final Class139 Anim = new Class140(600, 1.0);
    public static EntityLivingBase target;
    private EntityLivingBase ESPTarget;

    public Class291() {
        super("KillAura", Class263.Combat);
    }

    @Override
    public void onDisable() {
        target = null;
    }

    @Override
    public void onEnable() {
        target = null;
    }

    @Class14
    public void onWorld(Class55 event) {
        target = null;
    }

    @Class14
    public void onPreUpdate(Class50 event) {
        if (this.isNull()) {
            return;
        }
        if (Class291.mc.thePlayer.ticksExisted < 10 && this.autodis.isEnabled()) {
            this.setState(false);
        }
        for (Entity entity : Class291.mc.theWorld.loadedEntityList) {
            if (this.notAttack(entity)) continue;
            target = (EntityLivingBase)entity;
        }
        if (this.isPlayerNear()) {
            switch (this.autoblockmods.getMode()) {
                case "Class76": {
                    if (!this.isSword()) break;
                    Class212.sendPacket(new Class333(Class291.mc.thePlayer.inventory.currentItem % 8 + 1));
                    Class291.mc.thePlayer.sendQueue.addToSendQueue(new Class355(Class291.mc.thePlayer.inventory.getCurrentItem()));
                    Class212.send1_12Block();
                    Class212.sendPacket(new Class333(Class291.mc.thePlayer.inventory.currentItem));
                    break;
                }
                case "Off": {
                    break;
                }
                case "GrimAC": {
                    if (!this.isSword()) break;
                    Class291.mc.playerController.sendUseItem(Class291.mc.thePlayer, Class291.mc.theWorld, Class291.mc.thePlayer.getHeldItem());
                }
            }
        }
        if (target == null) {
            return;
        }
        if (this.notAttack(target)) {
            target = null;
            Class212.releaseUseItem(true);
            Class291.mc.thePlayer.stopUsingItem();
            return;
        }
        if ((double) Class291.mc.thePlayer.getClosestDistanceToEntity(target) <= this.range.getValue() + (double)0.02f) {
            Class247 rotation = Class237.getRotations(target);
            Class20.setRotations(rotation, this.rotationspeed.getValue(), this.getMovementFixType());
        }
        if (this.attackmode.is("Pre")) {
            return;
        }
        this.attack();
    }

    @Class14
    public void onPost(Class26 event) {
        this.setSuffix(this.attackmode.getMode());
        if (this.isNull()) {
            return;
        }
        if (Class291.mc.thePlayer.ticksExisted < 10 && this.autodis.isEnabled()) {
            this.setState(false);
        }
        for (Entity entity : Class291.mc.theWorld.loadedEntityList) {
            if (this.notAttack(entity)) continue;
            target = (EntityLivingBase)entity;
        }
        if (target == null) {
            return;
        }
        if (this.notAttack(target)) {
            if (target != null) {
                Class291.mc.gameSettings.keyBindUseItem.pressed = false;
            }
            target = null;
            return;
        }
        if (this.autoblockmods.is("Class76") && this.isSword()) {
            Class291.mc.thePlayer.setItemInUse(Class291.mc.thePlayer.getHeldItem(), 72000);
            if (Class291.mc.thePlayer.isUsingItem() && this.isSword()) {
                Class291.mc.thePlayer.sendQueue.addToSendQueue(new Class355(Class291.mc.thePlayer.inventory.getCurrentItem()));
                for (int i = 1; i < 5; ++i) {
                    Class212.send1_12Block();
                }
            }
        }
        if (this.attackmode.is("Post")) {
            return;
        }
        this.attack();
    }

    public boolean isPlayerNear() {
        for (Entity entity : Class291.mc.theWorld.loadedEntityList) {
            if (this.entityCant(entity) || !(entity instanceof EntityPlayer)) continue;
            return true;
        }
        return false;
    }

    @Class14
    public void onReceive(Class33 event) {
        if (this.isNull()) {
            return;
        }
        Packet<?> packet = event.getPacket();
        if (packet instanceof Class378 s2f) {
            if (target != null && this.autoblockmods.is("GrimAC") && s2f.getItem().getItem() instanceof ItemSword && this.isSword() && Class291.mc.thePlayer.isUsingItem()) {
                event.setCancelled();
            }
        }
    }

    public void attack() {
        if (Class291.isLookingAtEntity(this.isGapple() ? Class20.rotations : Class215.currentRotation, target) && this.shouldAttack()) {
            if (this.keepsprint.isEnabled()) {
                Class291.mc.playerController.attackEntityNoSlow(target);
                this.attackTimer.reset();
            } else {
                Class291.mc.playerController.attackEntity(Class291.mc.thePlayer, target);
                this.attackTimer.reset();
            }
        }
    }

    private boolean notAttack(Entity entity) {
        Class278 sca = Class262.getModule(Class278.class);
        Class304 blink = Class262.getModule(Class304.class);
        Class303 timer = Class262.getModule(Class303.class);
        Class265 antiBot = Class262.getModule(Class265.class);
        return !(entity instanceof EntityLivingBase) || entity == Class291.mc.thePlayer || !entity.isEntityAlive() || !((double) Class291.mc.thePlayer.getClosestDistanceToEntity(entity) < this.startrange.getValue()) || sca.isState() || entity == Class304.getFakePlayer() || blink.isState() || Class262.getModule(Class302.class).isState() || antiBot.isServerBot(entity) || Class290.isSameTeam(entity) || timer.isState() || Class56.isIRCFriend(entity) || entity instanceof EntityVillager || entity instanceof EntitySquid;
    }

    private boolean entityCant(Entity entity) {
        Class278 sca = Class262.getModule(Class278.class);
        Class304 blink = Class262.getModule(Class304.class);
        Class303 timer = Class262.getModule(Class303.class);
        Class265 antiBot = Class262.getModule(Class265.class);
        return !(entity instanceof EntityLivingBase) || entity == Class291.mc.thePlayer || !entity.isEntityAlive() || !(Class291.mc.thePlayer.getClosestDistanceToEntity(entity) < 6.0f) || sca.isState() || entity == Class304.getFakePlayer() || blink.isState() || Class262.getModule(Class302.class).isState() || antiBot.isServerBot(entity) || Class290.isSameTeam(entity) || timer.isState() || Class56.isIRCFriend(entity);
    }

    private Class167 getMovementFixType() {
        return switch (this.moveFix.getMode()) {
            case "None" -> Class167.OFF;
            case "Silent" -> Class167.NORMAL;
            case "Strict" -> Class167.TRADITIONAL;
            case "BackSprint" -> Class167.BACKWARDS_SPRINT;
            default -> throw new IllegalStateException("Unexpected value: " + this.moveFix.getMode());
        };
    }

    @Class14
    public void onTick(Class52 event) {
        if (this.attackmode.is("Tick")) {
            this.attack();
        }
    }

    @Class14
    public void onRender3D(Class39 event) {
        switch (this.espmode.getMode()) {
            case "Box": {
                this.Anim.setDirection(target != null ? Class141.FORWARDS : Class141.BACKWARDS);
                if (target != null) {
                    this.ESPTarget = target;
                }
                if (this.Anim.finished(Class141.BACKWARDS)) {
                    this.ESPTarget = null;
                }
                if (this.ESPTarget == null) break;
                Class147.renderBoundingBox(this.ESPTarget, new Color(255, 255, 255, 134), this.Anim.getOutput().floatValue());
                break;
            }
            case "Jello": {
                if (target == null) {
                    return;
                }
                Class147.drawTargetCapsule(target, 0.5, true, new Color(126, 0, 252, 203));
                break;
            }
            case "Nursultan": {
                if (target == null) {
                    return;
                }
                float dst = Class291.mc.thePlayer.getDistanceToEntity(target);
                javax.vecmath.Vector2f vector2f = Class147.targetESPSPos(target);
                if (vector2f == null) break;
                Class147.drawTargetESP2D(vector2f.x, vector2f.y, Color.RED, Color.BLUE, Color.GREEN, Color.PINK, 1.0f - MathHelper.clamp_float(Math.abs(dst - 6.0f) / 60.0f, 0.0f, 0.75f), 1);
            }
        }
    }

    public float cps() {
        return (float) Class171.getRandom(this.mincps.getValue(), this.maxcps.getValue());
    }

    public boolean shouldAttack() {
        return this.attackTimer.hasReached(1000.0 / ((double)this.cps() * 1.5));
    }

    public static boolean isLookingAtEntity(Class247 rotations, Entity target) {
        double range = 3.0;
        Vec3 src = Class291.mc.thePlayer.getPositionEyes(1.0f);
        Vec3 rotationVec = Class291.getVectorForRotation(rotations.y, rotations.x);
        Vec3 dest = src.addVector(rotationVec.xCoord * range, rotationVec.yCoord * range, rotationVec.zCoord * range);
        MovingObjectPosition obj = Class291.mc.theWorld.rayTraceBlocks(src, dest, false, false, true);
        if (obj == null) {
            return false;
        }
        return target.getEntityBoundingBox().expand(0.1f, 0.1f, 0.1f).calculateIntercept(src, dest) != null;
    }

    protected static Vec3 getVectorForRotation(float p_getVectorForRotation_1_, float p_getVectorForRotation_2_) {
        float f = MathHelper.cos(-p_getVectorForRotation_2_ * ((float)Math.PI / 180) - (float)Math.PI);
        float f1 = MathHelper.sin(-p_getVectorForRotation_2_ * ((float)Math.PI / 180) - (float)Math.PI);
        float f2 = -MathHelper.cos(-p_getVectorForRotation_1_ * ((float)Math.PI / 180));
        float f3 = MathHelper.sin(-p_getVectorForRotation_1_ * ((float)Math.PI / 180));
        return new Vec3(f1 * f2, f3, f * f2);
    }

    public static EntityLivingBase getTarget() {
        return target;
    }
}

