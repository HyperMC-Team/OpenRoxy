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

public class Class229 {
    private static final Class463 gradientMaskShader = new Class463("gradientMask");
    private static final Class463 gradientShader = new Class463("gradient");

    public static void drawGradient(float x2, float y2, float width, float height, float alpha, Color bottomLeft, Color topLeft, Color bottomRight, Color topRight) {
        ScaledResolution sr = new ScaledResolution(Class3.mc);
        Class465.setAlphaLimit(0.0f);
        Class465.resetColor();
        Class466.startBlend();
        gradientShader.init();
        gradientShader.setUniformf("location", x2 * (float)sr.getScaleFactor(), (float)Minecraft.getMinecraft().displayHeight - height * (float)sr.getScaleFactor() - y2 * (float)sr.getScaleFactor());
        gradientShader.setUniformf("rectSize", width * (float)sr.getScaleFactor(), height * (float)sr.getScaleFactor());
        gradientShader.setUniformf("color1", (float)bottomLeft.getRed() / 255.0f, (float)bottomLeft.getGreen() / 255.0f, (float)bottomLeft.getBlue() / 255.0f, alpha);
        gradientShader.setUniformf("color2", (float)topLeft.getRed() / 255.0f, (float)topLeft.getGreen() / 255.0f, (float)topLeft.getBlue() / 255.0f, alpha);
        gradientShader.setUniformf("color3", (float)bottomRight.getRed() / 255.0f, (float)bottomRight.getGreen() / 255.0f, (float)bottomRight.getBlue() / 255.0f, alpha);
        gradientShader.setUniformf("color4", (float)topRight.getRed() / 255.0f, (float)topRight.getGreen() / 255.0f, (float)topRight.getBlue() / 255.0f, alpha);
        Class463.drawQuads(x2, y2, width, height);
        gradientShader.unload();
        Class466.endBlend();
    }

    public static void drawGradient(float x2, float y2, float width, float height, Color bottomLeft, Color topLeft, Color bottomRight, Color topRight) {
        ScaledResolution sr = new ScaledResolution(Class3.mc);
        Class465.resetColor();
        Class466.startBlend();
        gradientShader.init();
        gradientShader.setUniformf("location", x2 * (float)sr.getScaleFactor(), (float)Minecraft.getMinecraft().displayHeight - height * (float)sr.getScaleFactor() - y2 * (float)sr.getScaleFactor());
        gradientShader.setUniformf("rectSize", width * (float)sr.getScaleFactor(), height * (float)sr.getScaleFactor());
        gradientShader.setUniformf("color1", (float)bottomLeft.getRed() / 255.0f, (float)bottomLeft.getGreen() / 255.0f, (float)bottomLeft.getBlue() / 255.0f, (float)bottomLeft.getAlpha() / 255.0f);
        gradientShader.setUniformf("color2", (float)topLeft.getRed() / 255.0f, (float)topLeft.getGreen() / 255.0f, (float)topLeft.getBlue() / 255.0f, (float)topLeft.getAlpha() / 255.0f);
        gradientShader.setUniformf("color3", (float)bottomRight.getRed() / 255.0f, (float)bottomRight.getGreen() / 255.0f, (float)bottomRight.getBlue() / 255.0f, (float)bottomRight.getAlpha() / 255.0f);
        gradientShader.setUniformf("color4", (float)topRight.getRed() / 255.0f, (float)topRight.getGreen() / 255.0f, (float)topRight.getBlue() / 255.0f, (float)topRight.getAlpha() / 255.0f);
        Class463.drawQuads(x2, y2, width, height);
        gradientShader.unload();
        Class466.endBlend();
    }

    public static void drawGradientLR(float x2, float y2, float width, float height, float alpha, Color left, Color right) {
        Class229.drawGradient(x2, y2, width, height, alpha, left, left, right, right);
    }

    public static void drawGradientTB(float x2, float y2, float width, float height, float alpha, Color top, Color bottom) {
        Class229.drawGradient(x2, y2, width, height, alpha, bottom, top, bottom, top);
    }

    public static void applyGradientHorizontal(float x2, float y2, float width, float height, float alpha, Color left, Color right, Runnable content) {
        Class229.applyGradient(x2, y2, width, height, alpha, left, left, right, right, content);
    }

    public static void applyGradientVertical(float x2, float y2, float width, float height, float alpha, Color top, Color bottom, Runnable content) {
        Class229.applyGradient(x2, y2, width, height, alpha, bottom, top, bottom, top, content);
    }

    public static void applyGradientCornerRL(float x2, float y2, float width, float height, float alpha, Color bottomLeft, Color topRight, Runnable content) {
        Color mixedColor = Class460.interpolateColorC(topRight, bottomLeft, 0.5f);
        Class229.applyGradient(x2, y2, width, height, alpha, bottomLeft, mixedColor, mixedColor, topRight, content);
    }

    public static void applyGradientCornerLR(float x2, float y2, float width, float height, float alpha, Color bottomRight, Color topLeft, Runnable content) {
        Color mixedColor = Class460.interpolateColorC(bottomRight, topLeft, 0.5f);
        Class229.applyGradient(x2, y2, width, height, alpha, mixedColor, topLeft, bottomRight, mixedColor, content);
    }

    public static void applyGradient(float x2, float y2, float width, float height, float alpha, Color bottomLeft, Color topLeft, Color bottomRight, Color topRight, Runnable content) {
        Class465.resetColor();
        Class466.startBlend();
        gradientMaskShader.init();
        ScaledResolution sr = new ScaledResolution(Class3.mc);
        gradientMaskShader.setUniformf("location", x2 * (float)sr.getScaleFactor(), (float)Minecraft.getMinecraft().displayHeight - height * (float)sr.getScaleFactor() - y2 * (float)sr.getScaleFactor());
        gradientMaskShader.setUniformf("rectSize", width * (float)sr.getScaleFactor(), height * (float)sr.getScaleFactor());
        gradientMaskShader.setUniformf("alpha", alpha);
        gradientMaskShader.setUniformi("tex", 0);
        gradientMaskShader.setUniformf("color1", (float)bottomLeft.getRed() / 255.0f, (float)bottomLeft.getGreen() / 255.0f, (float)bottomLeft.getBlue() / 255.0f);
        gradientMaskShader.setUniformf("color2", (float)topLeft.getRed() / 255.0f, (float)topLeft.getGreen() / 255.0f, (float)topLeft.getBlue() / 255.0f);
        gradientMaskShader.setUniformf("color3", (float)bottomRight.getRed() / 255.0f, (float)bottomRight.getGreen() / 255.0f, (float)bottomRight.getBlue() / 255.0f);
        gradientMaskShader.setUniformf("color4", (float)topRight.getRed() / 255.0f, (float)topRight.getGreen() / 255.0f, (float)topRight.getBlue() / 255.0f);
        content.run();
        gradientMaskShader.unload();
        Class466.endBlend();
    }
}

