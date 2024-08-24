package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.awt.Color;
import java.util.Objects;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.Items;
import tech.skidonion.obfuscator.annotations.StringEncryption;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
@StringEncryption
public class Class264
extends Class252 {
    public Class259 duringSendTicks = new Class259("DuringSendTicks", 1.0, 10.0, 0.0, 1.0);
    public Class259 delay = new Class259("Delay", 9.0, 10.0, 0.0, 1.0);
    public Class254 auto = new Class254("Auto", false);
    private final boolean stopMove = true;
    public boolean noCancelC02 = false;
    public boolean noC02 = false;
    private int slot = -1;
    private int c03s = 0;
    private int c02s = 0;
    private boolean canStart = false;
    public static boolean eating = false;
    public static boolean pulsing = false;
    public static boolean restart = false;

    public Class264() {
        super("Gapple", Class263.Misc);
    }

    @Override
    public void onEnable() {
        this.c03s = 0;
        this.slot = Class219.findItem(36, 45, Items.golden_apple);
        if (this.slot != -1) {
            this.slot -= 36;
        }
    }

    @Override
    public void onDisable() {
        eating = false;
        if (this.canStart) {
            pulsing = false;
            Class19.Method8();
        }
        Objects.requireNonNull(this);
        Class18.Method7();
    }

    @Class14
    public void onTick(Class52 event) {
        if (mc.thePlayer == null || mc.thePlayer.isDead) {
            Class19.Method8();
            this.setState(false);
            return;
        }
        if (this.slot == -1) {
            Class162.addChatMessage("没苹果");
            this.setState(false);
            return;
        }
        if (eating) {
            Objects.requireNonNull(this);
            Class18.Method6();
            if (!Class19.storing) {
                Class19.Method2(Class333.class);
                Class19.Method4(Class365.class, it -> ((Class365)it).getStatus() == Class365.Action.RELEASE_USE_ITEM);
                Class19.Method4(Class355.class, it -> ((Class355)it).getPosition().getY() == -1);
                if (!mc.thePlayer.serverSprintState) {
                    Class212.sendPacket(new Class364(mc.thePlayer, Class364.Action.START_SPRINTING));
                    mc.thePlayer.setSprinting(true);
                    mc.thePlayer.serverSprintState = true;
                }
                Class19.Method4(Class334.class, it -> this.noCancelC02);
                Class19.Method4(Class331.class, it -> this.noCancelC02);
                Class19.Method3(Class354.class, packet -> ++this.c03s);
                Class19.setReleaseAction(Class354.class, packet -> --this.c03s);
                Class19.Method6(Class334.class, packet -> !eating && this.noC02);
                Class19.Method3(Class334.class, packet -> ++this.c02s);
                Class19.setReleaseAction(Class334.class, packet -> --this.c02s);
                this.canStart = true;
            }
        } else {
            eating = true;
        }
        if (this.c03s >= 32) {
            eating = false;
            pulsing = true;
            Class19.Method5();
            Class19.Method1(new Class333(this.slot), true);
            Class19.Method1(new Class355(mc.thePlayer.inventoryContainer.getSlot(this.slot + 36).getStack()), true);
            Class19.Method8();
            Class19.Method1(new Class333(mc.thePlayer.inventory.currentItem), true);
            pulsing = false;
            this.setState(false);
            Class162.addChatMessage("Eat");
            if (this.auto.isEnabled()) {
                if (Class291.target.getName() != null) {
                    Class162.addChatMessage("Stop");
                    restart = true;
                    this.setState(true);
                    restart = false;
                    Class162.addChatMessage("Restart");
                }
            } else {
                restart = false;
            }
            return;
        }
        if (this.delay.getValue() == 0.0) {
            int i = 0;
            while ((double)i < this.duringSendTicks.getValue()) {
                Class19.Method7(true);
                ++i;
            }
        }
    }

    @Class14
    public void onRender2D(Class38 event) {
        float target = (float)(120.0 * ((double)this.c03s / 32.0)) * 0.8333333f;
        int startX = sr.getScaledWidth() / 2 - 68;
        int startY = sr.getScaledHeight() / 2 + 30;
        GlStateManager.disableAlpha();
        String text = target + "%";
        Class120.tenacityFont18.drawString(text, (float)(startX + 10 + 60) - Class120.tenacityFont18.getStringWidth(text) / 2.0f, (float)(startY + 20), new Color(225, 225, 225, 100).getRGB());
        Class468.drawGradientRound(startX + 10, (float)((double)startY + 7.5), 120.0f, 2.0f, 3.0f, new Color(0, 0, 0, 200), new Color(0, 0, 0, 150), new Color(0, 0, 0, 150), new Color(0, 0, 0, 150));
        Class468.drawGradientRound(startX + 10, (float)((double)startY + 7.5), Math.min(target, 120.0f), 2.0f, 3.0f, new Color(241, 59, 232, 170), new Color(241, 59, 232, 170), new Color(241, 59, 232, 170), new Color(241, 59, 232, 170));
        GlStateManager.disableAlpha();
    }
}

