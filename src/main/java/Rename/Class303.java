package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.awt.Color;
import java.util.LinkedList;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
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
public class Class303
extends Class252 {
    private final Class257 mode = new Class257("Mode", "Basic", "Basic", "Balance");
    private final Class259 speed = new Class259("TimerSpeed", 3.0, 6.0, 1.0, 0.1);
    private final Class254 dis = new Class254("AutoDisable", true);
    private final Class254 dissca = new Class254("AutoDisSca", true);
    private final Class254 poslook = new Class254("PosLook", true);
    private final Class254 debug = new Class254("DeBug", true);
    private final Class254 render = new Class254("Render", true);
    private final LinkedList<Packet<INetHandler>> inBus = new LinkedList();
    private final Class239 stopWatch = new Class239();
    private int balance = 0;
    private boolean disable;

    public Class303() {
        super("Class303", Class263.Player);
    }

    @Override
    public void onEnable() {
        this.balance = 0;
        this.stopWatch.reset();
        if (this.dis.isEnabled()) {
            this.disable = false;
        }
    }

    @Override
    public void onDisable() {
        if (Class303.mc.thePlayer == null) {
            return;
        }
        if (this.mode.is("Balance")) {
            this.inBus.forEach(packet -> packet.processPacket(mc.getNetHandler()));
            this.inBus.clear();
        }
        Class303.mc.timer.timerSpeed = 1.0f;
    }

    @Class14
    public void onPacket(Class33 event) {
        if (this.mode.is("Balance")) {
            Packet<?> packet = event.getPacket();
            if (packet instanceof Class391) {
                event.setCancelled();
                mc.getNetHandler().addToSendQueue(new Class353(0, (short) 0, true));
            }
            if (packet instanceof Class349) {
                inBus.add((Packet<INetHandler>) packet);
                event.setCancelled();
            }
            if (packet instanceof Class384) {
                inBus.add((Packet<INetHandler>) packet);
                event.setCancelled();
            }
            if (packet instanceof Class382) {
                inBus.add((Packet<INetHandler>) packet);
                event.setCancelled();
            }
            if (packet instanceof Class351 && this.poslook.isEnabled()) {
                this.setState(false);
            }
        }
    }

    @Class14
    public void onPacket(Class35 event) {
        Packet<?> packet;
        if (this.mode.is("Balance") && (packet = event.getPacket()) instanceof Class354) {
            Class354 c03 = (Class354)packet;
            if (!c03.rotating && !Class210.isMoving()) {
                event.setCancelled();
            }
            if (!event.isCancelled() || Class262.getModule(Class304.class).isState()) {
                this.balance -= 50;
            }
            this.balance += (int)this.stopWatch.getElapsedTime();
            this.stopWatch.reset();
        }
    }

    @Class14
    public void onUpdate(Class50 event) {
        if (this.mode.is("Balance")) {
            if (this.balance < 200) {
                Class303.mc.timer.timerSpeed = 1.0f;
                if (this.dis.isEnabled() && this.disable) {
                    this.setState(false);
                    if (this.dissca.isEnabled() && Class262.getModule(Class278.class).isState()) {
                        Class262.getModule(Class278.class).setState(false);
                    }
                }
            } else {
                Class303.mc.timer.timerSpeed = (float)this.speed.getValue().doubleValue();
                if (this.dis.isEnabled()) {
                    this.disable = true;
                }
            }
        }
    }

    @Class14
    public void onMotion(Class26 event) {
        if (this.mode.is("Basic")) {
            Class303.mc.timer.timerSpeed = this.speed.getValue().floatValue();
        }
        if (Class303.mc.thePlayer.ticksExisted % 20 == 0 && this.debug.isEnabled() && this.mode.is("Balance")) {
            Class162.addChatMessage("BalanceTimer: " + this.balance);
        }
    }

    @Class14
    public void onWorld(Class55 event) {
        if (event == null) {
            return;
        }
        this.setState(false);
        this.stopWatch.reset();
    }

    @Class14
    public void onRender2D(Class38 event) {
        ScaledResolution sr = new ScaledResolution(mc);
        if (this.render.isEnabled() && this.mode.is("Balance")) {
            int startX = sr.getScaledWidth() / 2 - 68;
            int startY = sr.getScaledHeight() / 2 + 30;
            int Packet2 = this.balance;
            GlStateManager.disableAlpha();
            String text = "" + Packet2;
            Class120.tenacityFont18.drawString(text, (float)(startX + 10 + 60) - Class120.tenacityFont18.getStringWidth(text) / 2.0f, (float)(startY + 20), new Color(225, 225, 225, 100).getRGB());
            Class468.drawGradientRound(startX + 10, (float)((double)startY + 7.5), 120.0f, 3.0f, 3.0f, new Color(0, 0, 0, 200), new Color(0, 0, 0, 150), new Color(0, 0, 0, 150), new Color(0, 0, 0, 150));
            Class468.drawGradientRound(startX + 10, (float)((double)startY + 7.5), Math.min((float)Packet2 / 50.0f, 120.0f), 3.0f, 3.0f, new Color(241, 59, 232, 170), new Color(241, 59, 232, 170), new Color(241, 59, 232, 170), new Color(241, 59, 232, 170));
            GlStateManager.disableAlpha();
        }
    }
}

