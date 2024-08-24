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

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

public class Class148 {
    public static Class463 roundedShader = new Class463("roundedRect");
    public static Class463 roundedOutlineShader = new Class463("roundRectOutline");
    private static final Class463 roundedTexturedShader = new Class463("roundRectTexture");
    private static final Class463 roundedGradientShader = new Class463("roundedRectGradient");

    public static void drawRound(float x, float y, float width, float height, float radius, Color color) {
        Class148.drawRound(x, y, width, height, radius, false, color);
    }

    public static void drawGradientHorizontal(float x, float y, float width, float height, float radius, Color left, Color right) {
        Class148.drawGradientRound(x, y, width, height, radius, left, left, right, right);
    }

    public static void drawGradientVertical(float x, float y, float width, float height, float radius, Color top, Color bottom) {
        Class148.drawGradientRound(x, y, width, height, radius, bottom, top, bottom, top);
    }

    public static void drawGradientCornerLR(float x, float y, float width, float height, float radius, Color topLeft, Color bottomRight) {
        Color mixedColor = Class460.interpolateColorC(topLeft, bottomRight, 0.5f);
        Class148.drawGradientRound(x, y, width, height, radius, mixedColor, topLeft, bottomRight, mixedColor);
    }

    public static void drawGradientCornerRL(float x, float y, float width, float height, float radius, Color bottomLeft, Color topRight) {
        Color mixedColor = Class460.interpolateColorC(topRight, bottomLeft, 0.5f);
        Class148.drawGradientRound(x, y, width, height, radius, bottomLeft, mixedColor, mixedColor, topRight);
    }

    public static void drawGradientRound(float x, float y, float width, float height, float radius, Color bottomLeft, Color topLeft, Color bottomRight, Color topRight) {
        Class147.setAlphaLimit(0.0f);
        Class147.resetColor();
        Class466.startBlend();
        roundedGradientShader.init();
        Class148.setupRoundedRectUniforms(x, y, width, height, radius, roundedGradientShader);
        roundedGradientShader.setUniformf("color1", (float)topLeft.getRed() / 255.0f, (float)topLeft.getGreen() / 255.0f, (float)topLeft.getBlue() / 255.0f, (float)topLeft.getAlpha() / 255.0f);
        roundedGradientShader.setUniformf("color2", (float)bottomLeft.getRed() / 255.0f, (float)bottomLeft.getGreen() / 255.0f, (float)bottomLeft.getBlue() / 255.0f, (float)bottomLeft.getAlpha() / 255.0f);
        roundedGradientShader.setUniformf("color3", (float)topRight.getRed() / 255.0f, (float)topRight.getGreen() / 255.0f, (float)topRight.getBlue() / 255.0f, (float)topRight.getAlpha() / 255.0f);
        roundedGradientShader.setUniformf("color4", (float)bottomRight.getRed() / 255.0f, (float)bottomRight.getGreen() / 255.0f, (float)bottomRight.getBlue() / 255.0f, (float)bottomRight.getAlpha() / 255.0f);
        Class463.drawQuads(x - 1.0f, y - 1.0f, width + 2.0f, height + 2.0f);
        roundedGradientShader.unload();
        Class466.endBlend();
    }

    public static void drawRound(float x, float y, float width, float height, float radius, boolean blur, Color color) {
        Class147.resetColor();
        Class466.startBlend();
        GL11.glBlendFunc(770, 771);
        Class147.setAlphaLimit(0.0f);
        roundedShader.init();
        Class148.setupRoundedRectUniforms(x, y, width, height, radius, roundedShader);
        roundedShader.setUniformi("blur", blur ? 1 : 0);
        roundedShader.setUniformf("color", (float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f);
        Class463.drawQuads(x - 1.0f, y - 1.0f, width + 2.0f, height + 2.0f);
        roundedShader.unload();
        Class466.endBlend();
    }

    public static void drawRoundOutline(float x, float y, float width, float height, float radius, float outlineThickness, Color color, Color outlineColor) {
        Class147.resetColor();
        Class466.startBlend();
        GL11.glBlendFunc(770, 771);
        Class147.setAlphaLimit(0.0f);
        roundedOutlineShader.init();
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        Class148.setupRoundedRectUniforms(x, y, width, height, radius, roundedOutlineShader);
        roundedOutlineShader.setUniformf("outlineThickness", outlineThickness * (float)sr.getScaleFactor());
        roundedOutlineShader.setUniformf("color", (float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f);
        roundedOutlineShader.setUniformf("outlineColor", (float)outlineColor.getRed() / 255.0f, (float)outlineColor.getGreen() / 255.0f, (float)outlineColor.getBlue() / 255.0f, (float)outlineColor.getAlpha() / 255.0f);
        Class463.drawQuads(x - (2.0f + outlineThickness), y - (2.0f + outlineThickness), width + (4.0f + outlineThickness * 2.0f), height + (4.0f + outlineThickness * 2.0f));
        roundedOutlineShader.unload();
        Class466.endBlend();
    }

    public static void drawRoundTextured(float x, float y, float width, float height, float radius, float alpha) {
        Class147.resetColor();
        Class147.setAlphaLimit(0.0f);
        Class466.startBlend();
        roundedTexturedShader.init();
        roundedTexturedShader.setUniformi("textureIn", 0);
        Class148.setupRoundedRectUniforms(x, y, width, height, radius, roundedTexturedShader);
        roundedTexturedShader.setUniformf("alpha", alpha);
        Class463.drawQuads(x - 1.0f, y - 1.0f, width + 2.0f, height + 2.0f);
        roundedTexturedShader.unload();
        Class466.endBlend();
    }

    private static void setupRoundedRectUniforms(float x, float y, float width, float height, float radius, Class463 roundedTexturedShader) {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        roundedTexturedShader.setUniformf("location", x * (float)sr.getScaleFactor(), (float)Minecraft.getMinecraft().displayHeight - height * (float)sr.getScaleFactor() - y * (float)sr.getScaleFactor());
        roundedTexturedShader.setUniformf("rectSize", width * (float)sr.getScaleFactor(), height * (float)sr.getScaleFactor());
        roundedTexturedShader.setUniformf("radius", radius * (float)sr.getScaleFactor());
    }
}

