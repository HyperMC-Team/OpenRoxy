package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.awt.Color;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.shader.Framebuffer;
import tech.skidonion.obfuscator.annotations.Renamer;
import tech.skidonion.obfuscator.annotations.StringEncryption;

@Renamer
@StringEncryption
public class Class138
implements Class3 {
    private static Class139 progressAnim;
    private static Class139 fadeAnim;
    private static Framebuffer framebuffer;
    private static boolean isFadedOut;

    private static void drawScreen(float width, float height) {
        Gui.drawRect2(0.0, 0.0, width, height, Color.BLACK.getRGB());
        Class138.drawSplashBackground(width, height);
        float reduceAlpha = fadeAnim == null ? 0.0f : fadeAnim.getOutput().floatValue();
        int alphaValue = (int)((1.0f - reduceAlpha) * 255.0f);
        if (alphaValue <= 0) {
            isFadedOut = true;
        }
        if (!isFadedOut) {
            Class330.arial40.drawCenteredString("MOJANG", width / 2.0f, (height - (float) Class330.arial40.getHeight()) / 2.0f, new Color(255, 255, 255, alphaValue).getRGB());
        }
        float rectWidth = Class330.arial40.getStringWidth("MOJANG") + 110;
        float rectHeight = 5.0f;
        float roundX = width / 2.0f - rectWidth / 2.0f;
        float roundY = height / 2.0f - rectHeight / 2.0f + 40.0f;
        if (Class138.progressAnim.timerUtil.getTime() >= 1800L && fadeAnim == null) {
            fadeAnim = new Class140(600, 1.0);
        }
        float progress = progressAnim.getOutput().floatValue();
        Class148.drawRoundOutline(roundX - 2.0f, roundY - 2.0f, rectWidth + 4.0f, rectHeight + 4.0f, rectHeight / 2.0f - 0.25f, 1.0f, new Color(0, 0, 0, 0), new Color(255, 255, 255, alphaValue));
        Class148.drawRound(roundX, roundY, rectWidth * progress, rectHeight, rectHeight / 2.0f - 0.25f, new Color(255, 255, 255, alphaValue));
    }

    public static void drawScreen() {
        ScaledResolution sr = new ScaledResolution(mc);
        int scaleFactor = sr.getScaleFactor();
        framebuffer = Class147.createFrameBuffer(framebuffer);
        progressAnim = new Class140(2400, 1.0);
        while (!progressAnim.isDone()) {
            framebuffer.framebufferClear();
            framebuffer.bindFramebuffer(true);
            GlStateManager.matrixMode(5889);
            GlStateManager.loadIdentity();
            GlStateManager.ortho(0.0, sr.getScaledWidth(), sr.getScaledHeight(), 0.0, 1000.0, 3000.0);
            GlStateManager.matrixMode(5888);
            GlStateManager.loadIdentity();
            GlStateManager.translate(0.0f, 0.0f, -2000.0f);
            GlStateManager.disableLighting();
            GlStateManager.disableFog();
            GlStateManager.disableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.color(0.0f, 0.0f, 0.0f, 0.0f);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(770, 771);
            Class138.drawScreen(sr.getScaledWidth(), sr.getScaledHeight());
            framebuffer.unbindFramebuffer();
            framebuffer.framebufferRender(sr.getScaledWidth() * scaleFactor, sr.getScaledHeight() * scaleFactor);
            Class147.setAlphaLimit(1.0f);
            mc.updateDisplay();
        }
    }

    public static void drawSplashBackground(float width, float height) {
        float alpha = fadeAnim == null ? 1.0f : 1.0f - fadeAnim.getOutput().floatValue();
        Class147.resetColor();
        GlStateManager.color(1.0f, 1.0f, 1.0f, alpha);
        Gui.drawRect(0.0, 0.0, width, height, new Color(239, 50, 61, (int)(alpha * 255.0f)).getRGB());
    }

    static {
        isFadedOut = false;
    }
}

