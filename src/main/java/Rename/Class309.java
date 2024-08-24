package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
public class Class309
extends Class252 {
    private final Class259 range = new Class259("Range", 4.0, 6.0, 1.0, 0.1);
    private BlockPos blockPos = null;
    private EnumFacing enumFacing = null;
    private boolean breaking = false;
    private float breakPercent = 0.0f;
    private float widthAnim = 0.0f;
    private float alphaAnim = 0.0f;
    private float moveinAnim = 0.0f;
    private boolean canBreak = false;

    public Class309() {
        super("Class309", Class263.World);
    }

    @Class14
    public void onBlockClick(Class43 event) {
        this.breaking = true;
        this.blockPos = event.getClickedBlock();
        this.enumFacing = event.getEnumFacing();
    }

    @Class14
    public void onUpdate(Class50 event) {
        if (this.blockPos == null || this.enumFacing == null) {
            return;
        }
        boolean bl = this.breakPercent * 50.0f >= 100.0f ? Class153.getCenterDistance(this.blockPos) < this.range.getValue() : (this.canBreak = false);
        if (this.canBreak) {
            Class212.sendPacketNoEvent(new Class365(Class365.Action.STOP_DESTROY_BLOCK, this.blockPos, this.enumFacing));
            this.blockPos = null;
            this.enumFacing = null;
            this.breaking = false;
            this.breakPercent = 0.0f;
        }
        if (this.breaking) {
            this.breakPercent += Class309.mc.theWorld.getBlockState(this.blockPos).getBlock().getPlayerRelativeBlockHardness(Class309.mc.thePlayer, Class309.mc.theWorld, this.blockPos);
        }
    }

    @Class14
    public void onRender2D(Class38 event) {
        ScaledResolution sr = new ScaledResolution(mc);
        int x = sr.getScaledWidth() / 2 - 80;
        int y = sr.getScaledHeight() / 2 + 192;
        if (this.breaking) {
            float progress = Math.min(this.breakPercent / Class309.mc.theWorld.getBlockState(this.blockPos).getBlock().getBlockHardness(Class309.mc.theWorld, this.blockPos), 1.0f);
            String string = String.format("%.1f", Float.valueOf(progress * 100.0f)) + "%";
            float x1 = (float)(sr.getScaledWidth() / 2) - 72.0f - (float) Class330.arial16.getStringWidth("100.0%") + 140.0f - 36.0f;
            this.widthAnim = Class461.animateSmooth(this.widthAnim, progress * 140.0f, 8.0f / (float) Minecraft.getDebugFPS());
            this.moveinAnim = Class461.animateSmooth(this.moveinAnim, 18.0f, 4.0f / (float) Minecraft.getDebugFPS());
            this.alphaAnim = Class461.animateSmooth(this.alphaAnim, 255.0f, 2.0f / (float) Minecraft.getDebugFPS());
        } else {
            this.widthAnim = Class461.animateSmooth(this.widthAnim, 0.0f, 8.0f / (float) Minecraft.getDebugFPS());
            this.moveinAnim = Class461.animateSmooth(this.moveinAnim, 0.0f, 4.0f / (float) Minecraft.getDebugFPS());
            this.alphaAnim = Class461.animateSmooth(this.alphaAnim, 0.0f, 2.0f / (float) Minecraft.getDebugFPS());
        }
        Class468.drawRound((float)(sr.getScaledWidth() / 2) - 72.0f, (float)(sr.getScaledHeight() - 120 - 10) - this.moveinAnim, this.widthAnim, 6.0f, 3.0f, Class465.reAlpha(Color.WHITE, (int)this.alphaAnim));
    }

    @Class14
    public void onRender3D(Class39 event) {
        Class465.drawBlockBox(this.blockPos, Color.WHITE, true);
    }

    @Class14
    public void onMotion(Class26 event) {
        if (this.breaking) {
            Class212.sendC0F();
            Class212.sendPacketNoEvent(new Class331());
        }
    }
}

