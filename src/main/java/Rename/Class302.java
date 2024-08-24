package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.util.LinkedList;

import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
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
public class Class302
extends Class252 {
    public Class254 antiSB;
    private double x;
    private double y;
    private double z;
    private boolean onGround = false;
    private Class247 rotation;
    private final LinkedList<Packet<INetHandler>> inBus = new LinkedList();

    public Class302() {
        super("Class302", Class263.Player);
        this.antiSB = new Class254("Anti SB", false);
    }

    @Override
    public void onEnable() {
        if (Class302.mc.thePlayer == null) {
            return;
        }
        this.onGround = Class302.mc.thePlayer.onGround;
        this.x = Class302.mc.thePlayer.posX;
        this.y = Class302.mc.thePlayer.posY;
        this.z = Class302.mc.thePlayer.posZ;
        this.rotation = new Class247(Class302.mc.thePlayer.rotationYaw, Class302.mc.thePlayer.rotationPitch);
        float f = Class302.mc.gameSettings.mouseSensitivity * 0.6f + 0.2f;
        float gcd = f * f * f * 1.2f;
        Class247 rotation = this.rotation;
        rotation.x -= this.rotation.x % gcd;
        Class247 rotation2 = this.rotation;
        rotation2.y -= this.rotation.y % gcd;
    }

    @Override
    public void onDisable() {
        this.inBus.forEach(packet -> packet.processPacket(mc.getNetHandler()));
        this.inBus.clear();
        Class212.sendPacketNoEvent(new Class354.C04PacketPlayerPosition(this.x, this.y + 1337.0, this.z, this.onGround));
        if (this.antiSB.isEnabled() && !Class302.mc.thePlayer.onGround) {
            this.setState(true);
        }
    }

    @Class14
    public void onSend(Class35 event) {
        Packet<?> packet = event.getPacket();
        if (packet instanceof Class355 c08) {
            Class247 current = new Class247(Class302.mc.thePlayer.rotationYaw, Class302.mc.thePlayer.rotationPitch);
            float f = Class302.mc.gameSettings.mouseSensitivity * 0.6f + 0.2f;
            float gcd = f * f * f * 1.2f;
            current.x -= current.x % gcd;
            current.y -= current.y % gcd;
            if (this.rotation.x == current.x && this.rotation.y == current.y) {
                return;
            }
            this.rotation = current;
            event.setCancelled(true);
            Class212.sendPacketNoEvent(new Class354.C05PacketPlayerLook(current.x, current.y, this.onGround));
            if (this.isBlock()) {
                Class212.sendPacketNoEvent(c08);
            } else {
                Class212.sendPacketNoEvent(new Class355(Class302.mc.thePlayer.getHeldItem()));
            }
        }
        if (event.getPacket() instanceof Class354) {
            Class278 scaffold = Class262.getModule(Class278.class);
            if (scaffold.isState()) {
                scaffold.setState(false);
            }
            event.setCancelled(true);
        }
    }

    @Class14
    private void onPacket(Class33 event) {
        if (Class275.isBlockUnder()) {
            // empty if block
        }
    }

    @Class14
    public void onUpdate(Class50 event) {
        Class302.mc.thePlayer.motionX = 0.0;
        Class302.mc.thePlayer.motionY = 0.0;
        Class302.mc.thePlayer.motionZ = 0.0;
        Class302.mc.thePlayer.setPosition(this.x, this.y, this.z);
    }
}

