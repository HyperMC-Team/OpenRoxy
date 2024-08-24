package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.awt.Color;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.Packet;
import net.minecraft.util.Vec3;
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
public class Class312
extends Class252 {
    public static EntityLivingBase target;
    private final Class259 amount = new Class259("Amount", 1.0, 3.0, 1.0, 0.1);
    private final Class259 range = new Class259("Range", 2.0, 8.0, 2.0, 0.1);
    private final Class259 interval = new Class259("IntervalTick", 1.0, 10.0, 0.0, 1.0);
    private final Class254 esp = new Class254("Esp", false);
    private Vec3 realTargetPosition = new Vec3(0.0, 0.0, 0.0);
    public static double realX;
    public static double realY;
    public static double realZ;
    int tick = 0;

    public Class312() {
        super("Class312", Class263.Combat);
    }

    @Class14
    public void onAttack(Class41 e) {
        target = (EntityLivingBase)e.getTarget();
    }

    @Class14
    public void onTick(Class52 e) {
        if ((double)this.tick <= this.interval.getValue()) {
            ++this.tick;
        }
        if (target != null && (double) Class312.mc.thePlayer.getDistanceToEntity(target) <= this.range.getValue()) {
            Vec3 vec3 = new Vec3(Class312.target.posX, Class312.target.posY, Class312.target.posZ);
            if (vec3.distanceTo(this.realTargetPosition) < this.amount.getValue() && (double)this.tick > this.interval.getValue()) {
                Class312.target.posX = Class312.target.lastTickPosX;
                Class312.target.posY = Class312.target.lastTickPosY;
                Class312.target.posZ = Class312.target.lastTickPosZ;
                this.tick = 0;
            }
        }
    }

    @Class14
    public void onPacketReceive(Class33 e) {
        Class406 s18;
        Packet<?> packet = e.getPacket();
        if (packet instanceof Class406 && (s18 = (Class406)packet).getEntityId() == target.getEntityId()) {
            this.realTargetPosition = new Vec3((double)s18.getX() / 32.0, (double)s18.getY() / 32.0, (double)s18.getZ() / 32.0);
            realX = (double)s18.getX() / 32.0;
            realY = (double)s18.getY() / 32.0;
            realZ = (double)s18.getZ() / 32.0;
        }
    }

    @Class14
    public void onRender3D(Class39 event) {
        if (this.esp.isEnabled()) {
            Class147.renderBoundingBox(target, Color.red, 100.0f);
            Class147.resetColor();
        }
    }

    @Override
    public void onDisable() {
        target = null;
        this.tick = 0;
    }
}

