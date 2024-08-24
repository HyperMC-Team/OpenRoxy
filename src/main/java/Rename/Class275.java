package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.util.ArrayList;

import net.minecraft.block.BlockAir;
import net.minecraft.network.Packet;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
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
public class Class275
extends Class252 {
    private final Class257 mode = new Class257("Mode", "GrimAC", "GrimAC", "Watchdog");
    public Class259 pullbackTime = new Class259("Pullback Time", 1000.0, 2000.0, 1000.0, 100.0);
    public Class259 catcherDistance = new Class259("Catcher Distance", 3.0, 50.0, 1.0, 1.0);
    public Class259 catcherTicks = new Class259("Catcher Ticks", 10.0, 100.0, 1.0, 1.0);
    public Class259 stuckDistance = new Class259("Class302 Distance", 4.0, 50.0, 1.0, 1.0);
    private float[] last = new float[3];
    public Class241 timer = new Class241();
    public ArrayList<Packet<?>> packets = new ArrayList();

    public Class275() {
        super("Class275", Class263.Player);
    }

    @Class14
    public void onMotion(Class27 event) {
        this.setSuffix(this.mode.getMode());
        if (this.mode.is("Watchdog") && !this.isAboveVoid()) {
            this.last = new float[]{(float) Class275.mc.thePlayer.posX, (float) Class275.mc.thePlayer.posY, (float) Class275.mc.thePlayer.posZ};
        }
    }

    @Override
    public void onDisable() {
        if (this.mode.is("GrimAC")) {
            if (Class262.getModule(Class302.class).isState()) {
                Class262.getModule(Class302.class).setState(false);
            }
            Class18.Method7();
        }
    }

    @Class14
    public void onUpdate(Class50 event) {
        if (this.isNull()) {
            return;
        }
        if (this.catcherDistance.getValue() >= this.stuckDistance.getValue()) {
            this.catcherDistance.setValue(this.stuckDistance.getValue() - 1.0);
            Class162.addChatMessage("Catcher Distance \u5fc5\u987b\u5927\u4e8e Class302 Distance, \u5df2\u5c06Catcher Distance\u8bbe\u7f6e\u4e3a" + (this.stuckDistance.getValue() - 1.0));
        }
        if (Class262.getModule(Class304.class).isState()) {
            return;
        }
        if (this.mode.is("GrimAC")) {
            if ((double) Class275.mc.thePlayer.fallDistance >= this.catcherDistance.getValue() && (double) Class275.mc.thePlayer.fallDistance < this.stuckDistance.getValue()) {
                if (!Class275.isBlockUnder()) {
                    Class18.Method6();
                    Class262.getModule(Class278.class).setState(true);
                } else {
                    Class18.Method7();
                    Class262.getModule(Class278.class).setState(false);
                }
            }
            if ((double) Class275.mc.thePlayer.fallDistance > this.stuckDistance.getValue() && !Class262.getModule(Class302.class).isState() && !Class275.isBlockUnder()) {
                Class262.getModule(Class302.class).setState(true);
            }
        }
    }

    @Class14
    public void onSend(Class35 event) {
        if (this.mode.is("Watchdog")) {
            if (Class275.mc.thePlayer != null && Class275.mc.thePlayer.ticksExisted < 100) {
                this.packets.clear();
                return;
            }
            if (event.getPacket() instanceof Class354) {
                if (this.isAboveVoid() && !Class275.mc.thePlayer.onGround) {
                    event.setCancelled(true);
                    this.packets.add(event.getPacket());
                    double doublePrimitive = this.pullbackTime.getValue();
                    long longValue = (long)doublePrimitive;
                    if (this.timer.hasTimePassed(longValue)) {
                        Class275.mc.thePlayer.setPosition(this.last[0], (double)this.last[1] + 0.1, this.last[2]);
                        mc.getNetHandler().getNetworkManager().sendPacket(new Class354.C04PacketPlayerPosition(this.last[0], this.last[1], this.last[2], true));
                        Class275.mc.thePlayer.motionY = 0.0;
                        Class275.mc.thePlayer.motionX = -Class275.mc.thePlayer.motionX / 2.0;
                        Class275.mc.thePlayer.motionZ = -Class275.mc.thePlayer.motionZ / 2.0;
                        this.packets.clear();
                        this.timer.reset();
                    }
                } else {
                    if (!this.packets.isEmpty()) {
                        for (Packet<?> packet : this.packets) {
                            Class212.sendPacketNoEvent(packet);
                        }
                        this.packets.clear();
                    }
                    this.timer.reset();
                }
            }
        }
    }

    @Class14
    public void onReceive(Class33 event) {
        Packet<?> packet = event.getPacket();
        if (packet instanceof Class351 packet2) {
            if (this.mode.is("Watchdog")) {
                this.packets.clear();
                float x = (float)packet2.getX();
                float y = (float)packet2.getY();
                float z = (float)packet2.getZ();
                if (!this.isAboveVoid(x, y, z)) {
                    this.last[0] = x;
                    this.last[1] = y;
                    this.last[2] = z;
                }
            }
        }
        if (Class275.isBlockUnder() && this.mode.is("GrimAC")) {
            Class275.mc.thePlayer.fallDistance = 0.0f;
            if (Class262.getModule(Class302.class).isState()) {
                Class262.getModule(Class302.class).setState(false);
            }
        }
    }

    public static boolean isBlockUnder() {
        if (Class275.mc.thePlayer.posY < 0.0) {
            return false;
        }
        for (int offset = 0; offset < (int) Class275.mc.thePlayer.posY + 2; offset += 2) {
            AxisAlignedBB bb = Class275.mc.thePlayer.getEntityBoundingBox().offset(0.0, -offset, 0.0);
            if (Class275.mc.theWorld.getCollidingBoundingBoxes(Class275.mc.thePlayer, bb).isEmpty()) continue;
            return true;
        }
        return false;
    }

    public boolean isAboveVoid(float x, float y, float z) {
        if (Class275.mc.thePlayer == null) {
            return false;
        }
        if (y < 0.0f) {
            return true;
        }
        for (int i = (int)(y - 1.0f); i >= 1; --i) {
            if (Class275.mc.theWorld.getBlockState(new BlockPos(x, i, z)).getBlock() instanceof BlockAir) continue;
            return false;
        }
        return !Class275.mc.thePlayer.onGround;
    }

    public boolean isAboveVoid() {
        if (Class275.mc.thePlayer == null) {
            return false;
        }
        if (Class275.mc.thePlayer.posY < 0.0) {
            return true;
        }
        for (int i = (int)(Class275.mc.thePlayer.posY - 1.0); i >= 1; --i) {
            if (Class275.mc.theWorld.getBlockState(new BlockPos(Class275.mc.thePlayer.posX, i, Class275.mc.thePlayer.posZ)).getBlock() instanceof BlockAir) continue;
            return false;
        }
        return !Class275.mc.thePlayer.onGround;
    }
}

