package Rename;

import java.awt.Color;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public class Class468 {
    public static Class463 roundedShader = new Class463("bloodline/shader/roundedRect.frag");
    public static Class463 roundedOutlineShader = new Class463("bloodline/shader/roundRectOutline.frag");
    private static final Class463 roundedTexturedShader = new Class463("bloodline/shader/roundRectTextured.frag");
    private static final Class463 roundedGradientShader = new Class463("bloodline/shader/roundedRectGradient.frag");
    private static final Class463 circleShader = new Class463("arc");

    public static void drawGradientRoundLR(float x, float y, float width, float height, float radius, Color color1, Color color2) {
        Class468.drawGradientRound(x, y, width, height, radius, color1, color2, color2, color1);
    }

    public static void drawCircle(float x, float y, float radius, float progress, int change, Color color, float smoothness) {
        Class466.startBlend();
        float borderThickness = 1.0f;
        circleShader.init();
        circleShader.setUniformf("radialSmoothness", smoothness);
        circleShader.setUniformf("radius", radius);
        circleShader.setUniformf("borderThickness", borderThickness);
        circleShader.setUniformf("progress", progress);
        circleShader.setUniformi("change", change);
        circleShader.setUniformf("color", (float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f);
        float wh = radius + 10.0f;
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        circleShader.setUniformf("pos", (x + (wh / 2.0f - (radius + borderThickness) / 2.0f)) * (float)sr.getScaleFactor(), (float)Minecraft.getMinecraft().displayHeight - (radius + borderThickness) * (float)sr.getScaleFactor() - (y + (wh / 2.0f - (radius + borderThickness) / 2.0f)) * (float)sr.getScaleFactor());
        Class463.drawQuads(x, y, wh, wh);
        circleShader.unload();
        Class466.endBlend();
    }

    public static void color(double red, double green, double blue, double alpha) {
        GL11.glColor4d(red, green, blue, alpha);
    }

    public static void color(Color color) {
        if (color == null) {
            color = Color.white;
        }
        Class468.color((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f);
    }

    public static void circle(double x, double y, double radius, double sides, boolean filled, Color color) {
        Class468.polygon(x, y, radius, sides, filled, color);
    }

    public static void polygon(double x, double y, double sideLength, double amountOfSides, boolean filled, Color color) {
        sideLength /= 2.0;
        Class468.start();
        if (color != null) {
            Class468.color(color);
        }
        if (!filled) {
            GL11.glLineWidth(2.0f);
        }
        GL11.glEnable(2848);
        Class468.begin(filled ? 6 : 3);
        for (double i = 0.0; i <= amountOfSides / 4.0; i += 1.0) {
            double angle = i * 4.0 * (Math.PI * 2) / 360.0;
            Class468.vertex(x + sideLength * Math.cos(angle) + sideLength, y + sideLength * Math.sin(angle) + sideLength);
        }
        Class468.end();
        GL11.glDisable(2848);
        Class468.stop();
    }

    public static void start() {
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.disableTexture2D();
        GlStateManager.disableCull();
        GlStateManager.disableAlpha();
        GlStateManager.disableDepth();
    }

    public static void vertex(double x, double y) {
        GL11.glVertex2d(x, y);
    }

    public static void begin(int glMode) {
        GL11.glBegin(glMode);
    }

    public static void end() {
        GL11.glEnd();
    }

    public static void stop() {
        GlStateManager.enableDepth();
        GlStateManager.enableAlpha();
        GlStateManager.enableCull();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.resetColor();
    }

    public static void drawRound(float x, float y, float width, float height, float radius, Color color) {
        Class465.resetColor();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        roundedShader.init();
        Class468.setupRoundedRectUniforms(x, y, width, height, radius, roundedShader);
        roundedShader.setUniformf("color", (float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f);
        Class463.drawQuads(x - 1.0f, y - 1.0f, width + 2.0f, height + 2.0f);
        roundedShader.unload();
        GlStateManager.disableBlend();
    }

    public static void drawRoundNoOffset(float x, float y, float width, float height, float radius, Color color) {
        Class472.resetColor();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        roundedShader.init();
        Class468.setupRoundedRectUniforms(x, y, width, height, radius, roundedShader);
        roundedShader.setUniformf("color", (float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f);
        Class463.drawQuads(x, y, width, height);
        roundedShader.unload();
        GlStateManager.disableBlend();
    }

    public static void drawRound(float x, float y, float width, float height, float radius, boolean blur, Color color) {
        Class465.resetColor();
        GlStateManager.enableBlend();
        GL11.glBlendFunc(770, 771);
        Class465.setAlphaLimit(0.0f);
        roundedShader.init();
        Class468.setupRoundedRectUniforms(x, y, width, height, radius, roundedShader);
        roundedShader.setUniformi("blur", blur ? 1 : 0);
        roundedShader.setUniformf("color", (float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f);
        Class463.drawQuads(x - 1.0f, y - 1.0f, width + 2.0f, height + 2.0f);
        roundedShader.unload();
        GlStateManager.disableBlend();
    }

    public static void drawGradientRound(float x, float y, float width, float height, float radius, Color bottomLeft, Color topLeft, Color bottomRight, Color topRight) {
        Class465.resetColor();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        roundedGradientShader.init();
        Class468.setupRoundedRectUniforms(x, y, width, height, radius, roundedGradientShader);
        roundedGradientShader.setUniformf("color1", (float)topLeft.getRed() / 255.0f, (float)topLeft.getGreen() / 255.0f, (float)topLeft.getBlue() / 255.0f, (float)topLeft.getAlpha() / 255.0f);
        roundedGradientShader.setUniformf("color2", (float)bottomRight.getRed() / 255.0f, (float)bottomRight.getGreen() / 255.0f, (float)bottomRight.getBlue() / 255.0f, (float)bottomRight.getAlpha() / 255.0f);
        roundedGradientShader.setUniformf("color3", (float)bottomLeft.getRed() / 255.0f, (float)bottomLeft.getGreen() / 255.0f, (float)bottomLeft.getBlue() / 255.0f, (float)bottomLeft.getAlpha() / 255.0f);
        roundedGradientShader.setUniformf("color4", (float)topRight.getRed() / 255.0f, (float)topRight.getGreen() / 255.0f, (float)topRight.getBlue() / 255.0f, (float)topRight.getAlpha() / 255.0f);
        Class463.drawQuads(x - 1.0f, y - 1.0f, width + 2.0f, height + 2.0f);
        roundedGradientShader.unload();
        GlStateManager.disableBlend();
    }

    public static void drawRoundOutline(float x, float y, float width, float height, float radius, float outlineThickness, Color color, Color outlineColor) {
        Class465.resetColor();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        roundedOutlineShader.init();
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        Class468.setupRoundedRectUniforms(x, y, width, height, radius, roundedOutlineShader);
        roundedOutlineShader.setUniformf("outlineThickness", outlineThickness * (float)sr.getScaleFactor());
        roundedOutlineShader.setUniformf("color", (float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f);
        roundedOutlineShader.setUniformf("outlineColor", (float)outlineColor.getRed() / 255.0f, (float)outlineColor.getGreen() / 255.0f, (float)outlineColor.getBlue() / 255.0f, (float)outlineColor.getAlpha() / 255.0f);
        Class463.drawQuads(x - (2.0f + outlineThickness), y - (2.0f + outlineThickness), width + 4.0f + outlineThickness * 2.0f, height + 4.0f + outlineThickness * 2.0f);
        roundedOutlineShader.unload();
        GlStateManager.disableBlend();
    }

    public static void drawGradientCornerLR(float x, float y, float width, float height, float radius, Color topLeft, Color bottomRight) {
        Color mixedColor = Class472.interpolateColorC(topLeft, bottomRight, 0.5f);
        Class468.drawGradientRound(x, y, width, height, radius, mixedColor, topLeft, bottomRight, mixedColor);
    }

    public static void drawGradientCornerRL(float x, float y, float width, float height, float radius, Color bottomLeft, Color topRight) {
        Color mixedColor = Class472.interpolateColorC(topRight, bottomLeft, 0.5f);
        Class468.drawGradientRound(x, y, width, height, radius, bottomLeft, mixedColor, mixedColor, topRight);
    }

    public static void drawRoundTextured(float x, float y, float width, float height, float radius, float alpha) {
        Class465.resetColor();
        roundedTexturedShader.init();
        roundedTexturedShader.setUniformi("textureIn", 0);
        Class468.setupRoundedRectUniforms(x, y, width, height, radius, roundedTexturedShader);
        roundedTexturedShader.setUniformf("alpha", alpha);
        Class463.drawQuads(x - 1.0f, y - 1.0f, width + 2.0f, height + 2.0f);
        roundedTexturedShader.unload();
        GlStateManager.disableBlend();
    }

    private static void setupRoundedRectUniforms(float x, float y, float width, float height, float radius, Class463 roundedTexturedShader) {
        ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
        roundedTexturedShader.setUniformf("location", x * (float)sr.getScaleFactor(), (float)Minecraft.getMinecraft().displayHeight - height * (float)sr.getScaleFactor() - y * (float)sr.getScaleFactor());
        roundedTexturedShader.setUniformf("rectSize", width * (float)sr.getScaleFactor(), height * (float)sr.getScaleFactor());
        roundedTexturedShader.setUniformf("radius", radius * (float)sr.getScaleFactor());
    }

    public static void round(float x, float y, float width, float height, float radius, Color color) {
        Class465.resetColor();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        roundedShader.init();
        Class468.setupRoundedRectUniforms(x, y, width, height, radius, roundedShader);
        roundedShader.setUniformf("color", (float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f);
        Class463.drawQuads(x - 1.0f, y - 1.0f, width + 2.0f, height + 2.0f);
        roundedShader.unload();
        GlStateManager.disableBlend();
    }

    public static void rect(float x, float y, float width, float height) {
        GL11.glBegin(7);
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex2f(x, y);
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex2f(x, y + height);
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex2f(x + width, y + height);
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex2f(x + width, y);
        GL11.glEnd();
    }

    public static void round(int x, int y, int width, int height, float radius, int rgb) {
        Class468.round((float)x, (float)y, (float)width, (float)height, radius, new Color(rgb));
    }
}

