package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import com.mojang.authlib.GameProfile;
import java.awt.Color;
import java.util.LinkedList;
import java.util.UUID;
import java.util.concurrent.ConcurrentLinkedQueue;

import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IProjectile;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Keyboard;
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
public class Class304
extends Class252 {
    private static EntityOtherPlayerMP fakePlayer;
    public final Class257 releasemode = new Class257("Release Mode", "Latency", "Instant", "Latency");
    private final Class259 speed = new Class259("Class273", 10.0, 100.0, 1.0, 1.0);
    private final Class254 slowPoll = new Class254("Release Slow", true);
    private final Class259 pollDelay = new Class259("Release Delay", 100.0, 100.0, 0.0, 1.0);
    private final Class259 startPollDelay = new Class259("Start Release Delay", 2000.0, 8000.0, 1000.0, 200.0);
    private final Class254 pulse = new Class254("Pulse", false);
    private final Class259 delay = new Class259("Pulse Delay", 100.0, 5000.0, 0.0, 10.0);
    private final Class254 confirmTransaction = new Class254("C0F", true);
    private final Class254 action = new Class254("Action", true);
    private final Class254 interact = new Class254("Interact", true);
    private final Class254 itemChange = new Class254("ItemChange", true);
    private final Class254 usingItem = new Class254("UsingItem", true);
    private final Class254 debug = new Class254("Debug", true);
    private final Class216 blinkUtils = new Class216();
    private final Class241 timer = new Class241();
    private final LinkedList<Packet<INetHandler>> inBus = new LinkedList();
    private final ConcurrentLinkedQueue<Packet<?>> packets = new ConcurrentLinkedQueue();
    private final ConcurrentLinkedQueue<Packet<?>> testPackets = new ConcurrentLinkedQueue();
    private final Class241 timer2 = new Class241();
    private boolean sendPacket = false;
    private double x;
    private double y;
    private double z;
    private double motionX;
    private double motionY;
    private double motionZ;
    private boolean pressed;

    public Class304() {
        super("Class304", Class263.Player);
        this.speed.addParent(this.releasemode, modeSetting -> modeSetting.is("Latency"));
        this.slowPoll.addParent(this.releasemode, modeSetting -> modeSetting.is("Latency"));
        this.pollDelay.addParent(this.releasemode, modeSetting -> modeSetting.is("Latency"));
        this.startPollDelay.addParent(this.releasemode, modeSetting -> modeSetting.is("Latency"));
        this.confirmTransaction.addParent(this.releasemode, modeSetting -> modeSetting.is("Instant"));
        this.action.addParent(this.releasemode, modeSetting -> modeSetting.is("Instant"));
        this.interact.addParent(this.releasemode, modeSetting -> modeSetting.is("Instant"));
        this.itemChange.addParent(this.releasemode, modeSetting -> modeSetting.is("Instant"));
        this.usingItem.addParent(this.releasemode, modeSetting -> modeSetting.is("Instant"));
        this.delay.addParent(this.pulse, a -> this.pulse.isEnabled());
    }

    @Override
    public void onEnable() {
        if (Class304.mc.thePlayer == null) {
            return;
        }
        if (this.releasemode.is("Latency")) {
            if (!this.packets.isEmpty() && this.sendPacket) {
                return;
            }
            this.sendPacket = false;
            this.x = Class304.mc.thePlayer.posX;
            this.y = Class304.mc.thePlayer.posY;
            this.z = Class304.mc.thePlayer.posZ;
            this.motionX = Class304.mc.thePlayer.motionX;
            this.motionY = Class304.mc.thePlayer.motionY;
            this.motionZ = Class304.mc.thePlayer.motionZ;
            this.pressed = Class304.mc.gameSettings.keyBindSneak.isPressed();
            this.packets.clear();
            this.timer2.reset();
            fakePlayer = new EntityOtherPlayerMP(Class304.mc.theWorld, new GameProfile(new UUID(69L, 96L), "[Class304]" + Class304.mc.thePlayer.getName()));
            fakePlayer.copyLocationAndAnglesFrom(Class304.mc.thePlayer);
            Class304.fakePlayer.rotationYawHead = Class304.mc.thePlayer.rotationYawHead;
            Class304.mc.theWorld.addEntityToWorld(-1337, fakePlayer);
        }
    }

    @Override
    public void onDisable() {
        if (this.releasemode.is("Latency")) {
            this.timer.reset();
            if (Keyboard.isKeyDown(42)) {
                Class304.mc.thePlayer.setPosition(this.x, this.y, this.z);
                Class304.mc.thePlayer.motionX = this.motionX;
                Class304.mc.thePlayer.motionY = this.motionY;
                Class304.mc.thePlayer.motionZ = this.motionZ;
                this.sendPacket = false;
                for (Packet<?> packet2 : this.packets) {
                    Class355 c08;
                    if (!(packet2 instanceof Class355) || Class304.mc.theWorld.getBlockState((c08 = (Class355)packet2).getPosition()).getBlock() == null) continue;
                    Class304.mc.theWorld.setBlockToAir(c08.getPosition());
                }
                Class304.mc.gameSettings.keyBindSneak.setPressed(this.pressed);
                this.packets.clear();
                return;
            }
            if (!this.packets.isEmpty()) {
                this.sendPacket = true;
                this.setState(true);
                return;
            }
            if (fakePlayer != null) {
                Class304.mc.theWorld.removeEntityFromWorld(fakePlayer.getEntityId());
                fakePlayer = null;
            }
        } else {
            this.blinkUtils.release();
        }
        this.inBus.forEach(packet -> packet.processPacket(mc.getNetHandler()));
        this.inBus.clear();
    }

    @Class14
    public void onMotion(Class27 event) {
        this.setSuffix(this.releasemode.getMode());
    }

    @Class14
    private void onRender2D(Class38 event) {
        ScaledResolution sr;
        if (this.releasemode.is("Latency") && this.debug.isEnabled()) {
            sr = new ScaledResolution(mc);
            int startX = sr.getScaledWidth() / 2 - 68;
            int startY = sr.getScaledHeight() / 2 + 30;
            GlStateManager.disableAlpha();
            String text = "" + this.packets.size();
            Class120.tenacityFont18.drawString(text, (float)(startX + 10 + 60) - Class120.tenacityFont18.getStringWidth(text) / 2.0f, (float)(startY + 20), new Color(225, 225, 225, 100).getRGB());
            Class468.drawGradientRound(startX + 10, (float)((double)startY + 7.5), 120.0f, 3.0f, 3.0f, new Color(0, 0, 0, 200), new Color(0, 0, 0, 150), new Color(0, 0, 0, 150), new Color(0, 0, 0, 150));
            Class468.drawGradientRound(startX + 10, (float)((double)startY + 7.5), Math.min((float)this.packets.size() / 10.0f, 60.0f), 3.0f, 3.0f, new Color(241, 59, 232, 170), new Color(241, 59, 232, 170), new Color(241, 59, 232, 170), new Color(241, 59, 232, 170));
            GlStateManager.disableAlpha();
        }
        if (this.releasemode.is("Instant") && this.debug.isEnabled()) {
            sr = new ScaledResolution(mc);
            Class304.mc.fontRendererObj.drawStringWithShadow("Stored Packets: " + this.blinkUtils.getClient().size(), (float)sr.getScaledWidth() / 2.0f - 50.0f, (float)sr.getScaledHeight() / 2.0f + 40.0f, -1);
        }
    }

    @Class14
    private void onT1ck(Class52 event) {
        if (this.releasemode.is("Latency")) {
            if (Class304.mc.thePlayer == null) {
                this.packets.clear();
                this.testPackets.clear();
                this.setState(false);
            }
            for (Entity entity : Class304.mc.theWorld.loadedEntityList) {
                float dZ;
                float dY;
                float dX;
                float distance;
                if (!(entity instanceof IProjectile) || entity.onGround || !(MathHelper.sqrt_float(distance = (dX = (float)(this.x - entity.posX)) * dX + (dY = (float)(this.y - entity.posY)) * dY + (dZ = (float)(this.z - entity.posZ)) * dZ) < 8.0f)) continue;
                while (!this.packets.isEmpty()) {
                    this.poll(2);
                }
            }
            if (this.timer2.hasTimePassed(this.startPollDelay.getValue().longValue()) && this.slowPoll.isEnabled() && this.timer.hasTimePassed(this.pollDelay.getValue().longValue()) && this.packets.size() >= 100) {
                this.poll(2);
                this.timer.reset();
            }
        } else {
            if (this.pulse.isEnabled()) {
                this.setSuffix(this.timer.getTimePassed());
            } else {
                this.setSuffix("");
            }
            if (this.timer.hasTimePassed(this.delay.getValue().intValue())) {
                if (this.pulse.isEnabled()) {
                    this.blinkUtils.release();
                }
                this.timer.reset();
            }
        }
    }

    private void poll(int count) {
        for (int i = 0; i < count; ++i) {
            Packet<?> packet = this.packets.poll();
            if (packet instanceof Class354.C04PacketPlayerPosition || packet instanceof Class354.C06PacketPlayerPosLook) {
                Class354 wrapper = (Class354)packet;
                Class304.fakePlayer.posX = wrapper.getX();
                Class304.fakePlayer.posY = wrapper.getY();
                Class304.fakePlayer.posZ = wrapper.getZ();
                Class304.fakePlayer.onGround = wrapper.isOnGround();
                Class304.fakePlayer.rotationPitch = wrapper.getPitch();
                Class304.fakePlayer.rotationYaw = wrapper.getYaw();
            }
            Class212.sendPacketNoEvent(packet);
        }
    }

    @Class14
    private void onPacketSend(Class35 event) {
        if (Class304.mc.thePlayer == null || Class304.mc.thePlayer.isDead || mc.isSingleplayer() || Class304.mc.thePlayer.ticksExisted < 50) {
            this.packets.clear();
            return;
        }
        if (this.releasemode.is("Instant")) {
            Packet<?> packet = event.getPacket();
            if (packet instanceof Class354 || packet instanceof Class353 && this.confirmTransaction.isEnabled() || packet instanceof Class364 && this.action.isEnabled() || (packet instanceof Class334 || packet instanceof Class331) && this.interact.isEnabled() || packet instanceof Class333 && this.itemChange.isEnabled() || packet instanceof Class355 && this.usingItem.isEnabled()) {
                this.blinkUtils.startBlink(event, packet);
            }
        } else {
            if (event.getPacket() instanceof Class362) {
                return;
            }
            if (this.sendPacket) {
                if (event.getPacket() instanceof Class334) {
                    event.setCancelled();
                    return;
                }
                this.testPackets.add(event.getPacket());
                event.setCancelled(true);
                return;
            }
            this.packets.add(event.getPacket());
            event.setCancelled(true);
        }
    }

    @Class14
    private void onPacketReceive(Class33 event) {
        if (this.releasemode.is("Latency") && event.getPacket() instanceof Class349 && ((Class349)event.getPacket()).getEntityID() == Class304.mc.thePlayer.getEntityId()) {
            this.setState(false);
        }
    }

    @Class14
    public void onTick(Class52 event) {
        if (Class304.mc.currentScreen != null) {
            this.setState(false);
            return;
        }
        if (this.releasemode.is("Latency") && this.sendPacket) {
            if (this.packets.isEmpty()) {
                while (!this.testPackets.isEmpty()) {
                    Class212.sendPacketNoEvent(this.testPackets.poll());
                }
                if (fakePlayer != null) {
                    Class304.mc.theWorld.removeEntityFromWorld(fakePlayer.getEntityId());
                    fakePlayer = null;
                }
                this.setState(false);
                return;
            }
            double test = 0.0;
            while (!this.packets.isEmpty() && !((test += 1.0) >= this.speed.getValue())) {
                this.poll(1);
            }
        }
    }

    public static EntityOtherPlayerMP getFakePlayer() {
        return fakePlayer;
    }
}

