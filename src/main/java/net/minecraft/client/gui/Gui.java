package net.minecraft.client.gui;

import Rename.Class147;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import Rename.Class466;

public class Gui {
    public static final ResourceLocation optionsBackground = new ResourceLocation("textures/gui/options_background.png");
    public static final ResourceLocation statIcons = new ResourceLocation("textures/gui/container/stats_icons.png");
    public static final ResourceLocation icons = new ResourceLocation("textures/gui/icons.png");
    protected static float zLevel;

    public static void drawRect2(double x, double y, double width, double height, int color) {
        Class147.resetColor();
        Class147.setAlphaLimit(0.0f);
        Class466.setup2DRendering(true);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldrenderer.pos(x, y, 0.0).color(color).endVertex();
        worldrenderer.pos(x, y + height, 0.0).color(color).endVertex();
        worldrenderer.pos(x + width, y + height, 0.0).color(color).endVertex();
        worldrenderer.pos(x + width, y, 0.0).color(color).endVertex();
        tessellator.draw();
        Class466.end2DRendering();
    }

    public static void drawGradientRectSideways2(double x, double y, double width, double height, int startColor, int endColor) {
        Gui.drawGradientRectSideways(x, y, x + width, y + height, startColor, endColor);
    }

    public static void drawGradientRectSideways(double left, double top, double right, double bottom, int startColor, int endColor) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        Class147.setAlphaLimit(0.0f);
        Class147.resetColor();
        Class466.setup2DRendering(true);
        GlStateManager.shadeModel(7425);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldrenderer.pos(right, top, zLevel).color(endColor).endVertex();
        worldrenderer.pos(left, top, zLevel).color(startColor).endVertex();
        worldrenderer.pos(left, bottom, zLevel).color(startColor).endVertex();
        worldrenderer.pos(right, bottom, zLevel).color(endColor).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        Class466.end2DRendering();
    }

    public static void drawGradientRect2(double x, double y, double width, double height, int startColor, int endColor) {
        Gui.drawGradientRect(x, y, x + width, y + height, startColor, endColor);
    }

    public static void drawGradientRect(double left, double top, double right, double bottom, int startColor, int endColor) {
        Class147.setAlphaLimit(0.0f);
        Class147.resetColor();
        Class466.setup2DRendering(true);
        GlStateManager.shadeModel(7425);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldrenderer.pos(right, top, zLevel).color(startColor).endVertex();
        worldrenderer.pos(left, top, zLevel).color(startColor).endVertex();
        worldrenderer.pos(left, bottom, zLevel).color(endColor).endVertex();
        worldrenderer.pos(right, bottom, zLevel).color(endColor).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        Class466.end2DRendering();
    }

    protected void drawHorizontalLine(int startX, int endX, int y, int color) {
        if (endX < startX) {
            int i = startX;
            startX = endX;
            endX = i;
        }
        Gui.drawRect(startX, y, endX + 1, y + 1, color);
    }

    protected void drawVerticalLine(int x, int startY, int endY, int color) {
        if (endY < startY) {
            int i = startY;
            startY = endY;
            endY = i;
        }
        Gui.drawRect(x, startY + 1, x + 1, endY, color);
    }

    public static void drawRect(int left, int top, int right, int bottom, int color) {
        if (left < right) {
            int i = left;
            left = right;
            right = i;
        }
        if (top < bottom) {
            int j = top;
            top = bottom;
            bottom = j;
        }
        float f3 = (float)(color >> 24 & 0xFF) / 255.0f;
        float f = (float)(color >> 16 & 0xFF) / 255.0f;
        float f1 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f2 = (float)(color & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f, f1, f2, f3);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        worldrenderer.pos(left, bottom, 0.0).endVertex();
        worldrenderer.pos(right, bottom, 0.0).endVertex();
        worldrenderer.pos(right, top, 0.0).endVertex();
        worldrenderer.pos(left, top, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawRect(double left, double top, double right, double bottom, int color) {
        if (left < right) {
            double i = left;
            left = right;
            right = i;
        }
        if (top < bottom) {
            double j = top;
            top = bottom;
            bottom = j;
        }
        float f3 = (float)(color >> 24 & 0xFF) / 255.0f;
        float f = (float)(color >> 16 & 0xFF) / 255.0f;
        float f1 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f2 = (float)(color & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f, f1, f2, f3);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        worldrenderer.pos(left, bottom, 0.0).endVertex();
        worldrenderer.pos(right, bottom, 0.0).endVertex();
        worldrenderer.pos(right, top, 0.0).endVertex();
        worldrenderer.pos(left, top, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    protected void drawGradientRect(int left, int top, int right, int bottom, int startColor, int endColor) {
        float f = (float)(startColor >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(startColor >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(startColor >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(startColor & 0xFF) / 255.0f;
        float f4 = (float)(endColor >> 24 & 0xFF) / 255.0f;
        float f5 = (float)(endColor >> 16 & 0xFF) / 255.0f;
        float f6 = (float)(endColor >> 8 & 0xFF) / 255.0f;
        float f7 = (float)(endColor & 0xFF) / 255.0f;
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        worldrenderer.pos(right, top, zLevel).color(f1, f2, f3, f).endVertex();
        worldrenderer.pos(left, top, zLevel).color(f1, f2, f3, f).endVertex();
        worldrenderer.pos(left, bottom, zLevel).color(f5, f6, f7, f4).endVertex();
        worldrenderer.pos(right, bottom, zLevel).color(f5, f6, f7, f4).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public void drawCenteredString(FontRenderer fontRendererIn, String text, int x, int y, int color) {
        fontRendererIn.drawStringWithShadow(text, x - fontRendererIn.getStringWidth(text) / 2, y, color);
    }

    public void drawString(FontRenderer fontRendererIn, String text, int x, int y, int color) {
        fontRendererIn.drawStringWithShadow(text, x, y, color);
    }

    public void drawTexturedModalRect(int x, int y, int textureX, int textureY, int width, int height) {
        float f = 0.00390625f;
        float f1 = 0.00390625f;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos(x + 0, y + height, zLevel).tex((float)(textureX + 0) * f, (float)(textureY + height) * f1).endVertex();
        worldrenderer.pos(x + width, y + height, zLevel).tex((float)(textureX + width) * f, (float)(textureY + height) * f1).endVertex();
        worldrenderer.pos(x + width, y + 0, zLevel).tex((float)(textureX + width) * f, (float)(textureY + 0) * f1).endVertex();
        worldrenderer.pos(x + 0, y + 0, zLevel).tex((float)(textureX + 0) * f, (float)(textureY + 0) * f1).endVertex();
        tessellator.draw();
    }

    public static void drawTexturedModalRect2(float xCoord, float yCoord, int minU, int minV, int maxU, int maxV) {
        float f = 0.00390625f;
        float f1 = 0.00390625f;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos(xCoord + 0.0f, yCoord + (float)maxV, zLevel).tex((float)minU * f, (float)(minV + maxV) * f1).endVertex();
        worldrenderer.pos(xCoord + (float)maxU, yCoord + (float)maxV, zLevel).tex((float)(minU + maxU) * f, (float)(minV + maxV) * f1).endVertex();
        worldrenderer.pos(xCoord + (float)maxU, yCoord + 0.0f, zLevel).tex((float)(minU + maxU) * f, (float)minV * f1).endVertex();
        worldrenderer.pos(xCoord + 0.0f, yCoord + 0.0f, zLevel).tex((float)minU * f, (float)minV * f1).endVertex();
        tessellator.draw();
    }

    public void drawTexturedModalRect(float xCoord, float yCoord, int minU, int minV, int maxU, int maxV) {
        float f = 0.00390625f;
        float f1 = 0.00390625f;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos(xCoord + 0.0f, yCoord + (float)maxV, zLevel).tex((float)(minU + 0) * f, (float)(minV + maxV) * f1).endVertex();
        worldrenderer.pos(xCoord + (float)maxU, yCoord + (float)maxV, zLevel).tex((float)(minU + maxU) * f, (float)(minV + maxV) * f1).endVertex();
        worldrenderer.pos(xCoord + (float)maxU, yCoord + 0.0f, zLevel).tex((float)(minU + maxU) * f, (float)(minV + 0) * f1).endVertex();
        worldrenderer.pos(xCoord + 0.0f, yCoord + 0.0f, zLevel).tex((float)(minU + 0) * f, (float)(minV + 0) * f1).endVertex();
        tessellator.draw();
    }

    public void drawTexturedModalRect(int xCoord, int yCoord, TextureAtlasSprite textureSprite, int widthIn, int heightIn) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos(xCoord + 0, yCoord + heightIn, zLevel).tex(textureSprite.getMinU(), textureSprite.getMaxV()).endVertex();
        worldrenderer.pos(xCoord + widthIn, yCoord + heightIn, zLevel).tex(textureSprite.getMaxU(), textureSprite.getMaxV()).endVertex();
        worldrenderer.pos(xCoord + widthIn, yCoord + 0, zLevel).tex(textureSprite.getMaxU(), textureSprite.getMinV()).endVertex();
        worldrenderer.pos(xCoord + 0, yCoord + 0, zLevel).tex(textureSprite.getMinU(), textureSprite.getMinV()).endVertex();
        tessellator.draw();
    }

    public static void drawModalRectWithCustomSizedTexture(int x, int y, float u, float v, int width, int height, float textureWidth, float textureHeight) {
        float f = 1.0f / textureWidth;
        float f1 = 1.0f / textureHeight;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos(x, y + height, 0.0).tex(u * f, (v + (float)height) * f1).endVertex();
        worldrenderer.pos(x + width, y + height, 0.0).tex((u + (float)width) * f, (v + (float)height) * f1).endVertex();
        worldrenderer.pos(x + width, y, 0.0).tex((u + (float)width) * f, v * f1).endVertex();
        worldrenderer.pos(x, y, 0.0).tex(u * f, v * f1).endVertex();
        tessellator.draw();
    }

    public static void drawModalRectWithCustomSizedTexture(float x, float y, float u, float v, float width, float height, float textureWidth, float textureHeight) {
        float f = 1.0f / textureWidth;
        float f1 = 1.0f / textureHeight;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos(x, y + height, 0.0).tex(u * f, (v + height) * f1).endVertex();
        worldrenderer.pos(x + width, y + height, 0.0).tex((u + width) * f, (v + height) * f1).endVertex();
        worldrenderer.pos(x + width, y, 0.0).tex((u + width) * f, v * f1).endVertex();
        worldrenderer.pos(x, y, 0.0).tex(u * f, v * f1).endVertex();
        tessellator.draw();
    }

    public static void drawScaledCustomSizeModalRect(int x, int y, float u, float v, int uWidth, int vHeight, int width, int height, float tileWidth, float tileHeight) {
        float f = 1.0f / tileWidth;
        float f1 = 1.0f / tileHeight;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos(x, y + height, 0.0).tex(u * f, (v + (float)vHeight) * f1).endVertex();
        worldrenderer.pos(x + width, y + height, 0.0).tex((u + (float)uWidth) * f, (v + (float)vHeight) * f1).endVertex();
        worldrenderer.pos(x + width, y, 0.0).tex((u + (float)uWidth) * f, v * f1).endVertex();
        worldrenderer.pos(x, y, 0.0).tex(u * f, v * f1).endVertex();
        tessellator.draw();
    }
}

