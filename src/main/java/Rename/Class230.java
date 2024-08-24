package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import org.lwjgl.opengl.GL11;

public class Class230 {
    private static final Minecraft mc = Minecraft.getMinecraft();
    public static boolean ring_c = false;

    public static void renderBlock(BlockPos blockPos, int color, boolean outline, boolean shade) {
        Class230.renderBox(blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1.0, 1.0, 1.0, color, outline, shade);
    }

    public static void drawRect(float x, float y, float width, float height, Color color) {
        Class230.drawRect(x, y, x + width, y + height, color.getRGB());
    }

    public static void renderBlock(BlockPos blockPos, int color, double y2, boolean outline, boolean shade) {
        Class230.renderBox(blockPos.getX(), blockPos.getY(), blockPos.getZ(), 1.0, y2, 1.0, color, outline, shade);
    }

    public static void resetColor() {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public static void scissor(double x, double y, double width, double height) {
        ScaledResolution sr = new ScaledResolution(mc);
        double scale = sr.getScaleFactor();
        y = (double)sr.getScaledHeight() - y;
        GL11.glScissor((int)(x *= scale), (int)((y *= scale) - (height *= scale)), (int)(width *= scale), (int)height);
    }

    public static void drawRoundedRect(float x, float y, float width, float height, float edgeRadius, int color, float borderWidth, int borderColor) {
        double angleRadians;
        int i;
        if (color == 0xFFFFFF) {
            color = Color.WHITE.getRGB();
        }
        if (borderColor == 0xFFFFFF) {
            borderColor = Color.WHITE.getRGB();
        }
        if (edgeRadius < 0.0f) {
            edgeRadius = 0.0f;
        }
        if (edgeRadius > width / 2.0f) {
            edgeRadius = width / 2.0f;
        }
        if (edgeRadius > height / 2.0f) {
            edgeRadius = height / 2.0f;
        }
        Class230.drawRect(x + edgeRadius, y + edgeRadius, width - edgeRadius * 2.0f, height - edgeRadius * 2.0f, color);
        Class230.drawRect(x + edgeRadius, y, width - edgeRadius * 2.0f, edgeRadius, color);
        Class230.drawRect(x + edgeRadius, y + height - edgeRadius, width - edgeRadius * 2.0f, edgeRadius, color);
        Class230.drawRect(x, y + edgeRadius, edgeRadius, height - edgeRadius * 2.0f, color);
        Class230.drawRect(x + width - edgeRadius, y + edgeRadius, edgeRadius, height - edgeRadius * 2.0f, color);
        Class230.enableRender2D();
        Class465.color(color);
        GL11.glBegin(6);
        float centerX = x + edgeRadius;
        float centerY = y + edgeRadius;
        GL11.glVertex2d(centerX, centerY);
        int vertices = (int)Math.min(Math.max(edgeRadius, 10.0f), 90.0f);
        for (i = 0; i < vertices + 1; ++i) {
            angleRadians = Math.PI * 2 * (double)(i + 180) / (double)(vertices * 4);
            GL11.glVertex2d((double)centerX + Math.sin(angleRadians) * (double)edgeRadius, (double)centerY + Math.cos(angleRadians) * (double)edgeRadius);
        }
        GL11.glEnd();
        GL11.glBegin(6);
        centerX = x + width - edgeRadius;
        centerY = y + edgeRadius;
        GL11.glVertex2d(centerX, centerY);
        vertices = (int)Math.min(Math.max(edgeRadius, 10.0f), 90.0f);
        for (i = 0; i < vertices + 1; ++i) {
            angleRadians = Math.PI * 2 * (double)(i + 90) / (double)(vertices * 4);
            GL11.glVertex2d((double)centerX + Math.sin(angleRadians) * (double)edgeRadius, (double)centerY + Math.cos(angleRadians) * (double)edgeRadius);
        }
        GL11.glEnd();
        GL11.glBegin(6);
        centerX = x + edgeRadius;
        centerY = y + height - edgeRadius;
        GL11.glVertex2d(centerX, centerY);
        vertices = (int)Math.min(Math.max(edgeRadius, 10.0f), 90.0f);
        for (i = 0; i < vertices + 1; ++i) {
            angleRadians = Math.PI * 2 * (double)(i + 270) / (double)(vertices * 4);
            GL11.glVertex2d((double)centerX + Math.sin(angleRadians) * (double)edgeRadius, (double)centerY + Math.cos(angleRadians) * (double)edgeRadius);
        }
        GL11.glEnd();
        GL11.glBegin(6);
        centerX = x + width - edgeRadius;
        centerY = y + height - edgeRadius;
        GL11.glVertex2d(centerX, centerY);
        vertices = (int)Math.min(Math.max(edgeRadius, 10.0f), 90.0f);
        for (i = 0; i < vertices + 1; ++i) {
            angleRadians = Math.PI * 2 * (double)i / (double)(vertices * 4);
            GL11.glVertex2d((double)centerX + Math.sin(angleRadians) * (double)edgeRadius, (double)centerY + Math.cos(angleRadians) * (double)edgeRadius);
        }
        GL11.glEnd();
        Class465.color(borderColor);
        GL11.glLineWidth(borderWidth);
        GL11.glBegin(3);
        centerX = x + edgeRadius;
        centerY = y + edgeRadius;
        for (i = vertices = (int)Math.min(Math.max(edgeRadius, 10.0f), 90.0f); i >= 0; --i) {
            angleRadians = Math.PI * 2 * (double)(i + 180) / (double)(vertices * 4);
            GL11.glVertex2d((double)centerX + Math.sin(angleRadians) * (double)edgeRadius, (double)centerY + Math.cos(angleRadians) * (double)edgeRadius);
        }
        GL11.glVertex2d(x + edgeRadius, y);
        GL11.glVertex2d(x + width - edgeRadius, y);
        centerX = x + width - edgeRadius;
        centerY = y + edgeRadius;
        for (i = vertices; i >= 0; --i) {
            angleRadians = Math.PI * 2 * (double)(i + 90) / (double)(vertices * 4);
            GL11.glVertex2d((double)centerX + Math.sin(angleRadians) * (double)edgeRadius, (double)centerY + Math.cos(angleRadians) * (double)edgeRadius);
        }
        GL11.glVertex2d(x + width, y + edgeRadius);
        GL11.glVertex2d(x + width, y + height - edgeRadius);
        centerX = x + width - edgeRadius;
        centerY = y + height - edgeRadius;
        for (i = vertices; i >= 0; --i) {
            angleRadians = Math.PI * 2 * (double)i / (double)(vertices * 4);
            GL11.glVertex2d((double)centerX + Math.sin(angleRadians) * (double)edgeRadius, (double)centerY + Math.cos(angleRadians) * (double)edgeRadius);
        }
        GL11.glVertex2d(x + width - edgeRadius, y + height);
        GL11.glVertex2d(x + edgeRadius, y + height);
        centerX = x + edgeRadius;
        centerY = y + height - edgeRadius;
        for (i = vertices; i >= 0; --i) {
            angleRadians = Math.PI * 2 * (double)(i + 270) / (double)(vertices * 4);
            GL11.glVertex2d((double)centerX + Math.sin(angleRadians) * (double)edgeRadius, (double)centerY + Math.cos(angleRadians) * (double)edgeRadius);
        }
        GL11.glVertex2d(x, y + height - edgeRadius);
        GL11.glVertex2d(x, y + edgeRadius);
        GL11.glEnd();
        Class230.disableRender2D();
    }

    public static void enableRender2D() {
        GL11.glEnable(3042);
        GL11.glDisable(2884);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(1.0f);
    }

    public static void disableRender2D() {
        GL11.glDisable(3042);
        GL11.glEnable(2884);
        GL11.glEnable(3553);
        GL11.glDisable(2848);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableTexture2D();
    }

    public static void drawBorderedRect(float x, float y, float x2, float y2, float l1, int col1, int col2) {
        Class230.drawRect(x, y, x2, y2, col2);
        float f = (float)(col1 >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(col1 >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(col1 >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(col1 & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glLineWidth(l1);
        GL11.glBegin(1);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glVertex2d(x2, y);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x2, y);
        GL11.glVertex2d(x, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glEnd();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glPopMatrix();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 255.0f);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }

    public static void drawPartialCircle(int cx, int cy, float r, float startAngle, float arcAngle, float lineWidth, Color color) {
        GL11.glPushMatrix();
        Class169.startBlend();
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glBlendFunc(770, 771);
        GL11.glLineWidth(lineWidth);
        GL11.glColor4f((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f);
        GL11.glBegin(3);
        float endAngle = startAngle + arcAngle;
        for (float angle = startAngle; angle < endAngle; angle += 1.0f) {
            float rad = (float)Math.toRadians(angle);
            float x = (float)((double)cx + Math.cos(rad) * (double)r);
            float y = (float)((double)cy + Math.sin(rad) * (double)r);
            GL11.glVertex2f(x, y);
        }
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        Class169.endBlend();
        GL11.glPopMatrix();
    }

    public static void drawRect(double left, double top, double right, double bottom, int color) {
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

    public static void drawOutline(float x, float y, float x2, float y2, float lineWidth, int color) {
        float f5 = (float)(color >> 24 & 0xFF) / 255.0f;
        float f6 = (float)(color >> 16 & 0xFF) / 255.0f;
        float f7 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f8 = (float)(color & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glPushMatrix();
        GL11.glColor4f(f6, f7, f8, f5);
        GL11.glLineWidth(lineWidth);
        GL11.glBegin(1);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glVertex2d(x2, y);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x2, y);
        GL11.glVertex2d(x, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
    }

    public static void renderBox(int x, int y, int z, double x2, double y2, double z2, int color, boolean outline, boolean shade) {
        double xPos = (double)x - Class230.mc.getRenderManager().viewerPosX;
        double yPos = (double)y - Class230.mc.getRenderManager().viewerPosY;
        double zPos = (double)z - Class230.mc.getRenderManager().viewerPosZ;
        GL11.glPushMatrix();
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        float n8 = (float)(color >> 24 & 0xFF) / 255.0f;
        float n9 = (float)(color >> 16 & 0xFF) / 255.0f;
        float n10 = (float)(color >> 8 & 0xFF) / 255.0f;
        float n11 = (float)(color & 0xFF) / 255.0f;
        GL11.glColor4f(n9, n10, n11, n8);
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB(xPos, yPos, zPos, xPos + x2, yPos + y2, zPos + z2);
        if (outline) {
            RenderGlobal.drawSelectionBoundingBox(axisAlignedBB);
        }
        if (shade) {
            Class230.drawBoundingBox(axisAlignedBB, n9, n10, n11);
        }
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public static void drawPolygon(double n, double n2, double n3, int n4, int n5) {
        if (n4 < 3) {
            return;
        }
        float n6 = (float)(n5 >> 24 & 0xFF) / 255.0f;
        float n7 = (float)(n5 >> 16 & 0xFF) / 255.0f;
        float n8 = (float)(n5 >> 8 & 0xFF) / 255.0f;
        float n9 = (float)(n5 & 0xFF) / 255.0f;
        Tessellator getInstance = Tessellator.getInstance();
        WorldRenderer getWorldRenderer = getInstance.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GL11.glColor4f(n7, n8, n9, n6);
        getWorldRenderer.begin(6, DefaultVertexFormats.POSITION);
        for (int i = 0; i < n4; ++i) {
            double n10 = Math.PI * 2 * (double)i / (double)n4 + Math.toRadians(180.0);
            getWorldRenderer.pos(n + Math.sin(n10) * n3, n2 + Math.cos(n10) * n3, 0.0).endVertex();
        }
        getInstance.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawBoundingBox(AxisAlignedBB abb, float r, float g, float b) {
        Class230.drawBoundingBox(abb, r, g, b, 0.25f);
    }

    public static void drawBoundingBox(AxisAlignedBB abb, float r, float g, float b, float a) {
        Tessellator ts = Tessellator.getInstance();
        WorldRenderer vb = ts.getWorldRenderer();
        vb.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vb.pos(abb.minX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vb.pos(abb.maxX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vb.pos(abb.minX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vb.pos(abb.minX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vb.pos(abb.minX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        ts.draw();
        vb.begin(7, DefaultVertexFormats.POSITION_COLOR);
        vb.pos(abb.minX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.minX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.minZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.maxY, abb.maxZ).color(r, g, b, a).endVertex();
        vb.pos(abb.maxX, abb.minY, abb.maxZ).color(r, g, b, a).endVertex();
        ts.draw();
    }

    public static void dGR(int left, int top, int right, int bottom, int startColor, int endColor) {
        int j;
        if (left < right) {
            j = left;
            left = right;
            right = j;
        }
        if (top < bottom) {
            j = top;
            top = bottom;
            bottom = j;
        }
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
        worldrenderer.pos(right, top, 0.0).color(f1, f2, f3, f).endVertex();
        worldrenderer.pos(left, top, 0.0).color(f1, f2, f3, f).endVertex();
        worldrenderer.pos(left, bottom, 0.0).color(f5, f6, f7, f4).endVertex();
        worldrenderer.pos(right, bottom, 0.0).color(f5, f6, f7, f4).endVertex();
        tessellator.draw();
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
    }

    public static void db(int w, int h, int r) {
        int c = r == -1 ? -1089466352 : r;
        Gui.drawRect(0, 0, w, h, c);
    }

    public static void d2p(double x, double y, int radius, int sides, int color) {
        float a = (float)(color >> 24 & 0xFF) / 255.0f;
        float r = (float)(color >> 16 & 0xFF) / 255.0f;
        float g = (float)(color >> 8 & 0xFF) / 255.0f;
        float b = (float)(color & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(r, g, b, a);
        worldrenderer.begin(6, DefaultVertexFormats.POSITION);
        for (int i = 0; i < sides; ++i) {
            double angle = Math.PI * 2 * (double)i / (double)sides + Math.toRadians(180.0);
            worldrenderer.pos(x + Math.sin(angle) * (double)radius, y + Math.cos(angle) * (double)radius, 0.0).endVertex();
        }
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawArrow(float x, float y, int color, double width, double length) {
        GL11.glPushMatrix();
        GL11.glEnable(2848);
        GL11.glDisable(3553);
        Class230.glColor(color);
        GL11.glLineWidth((float)width);
        float halfWidth = (float)(width / 2.0);
        float xOffset = halfWidth / 2.0f;
        float yOffset = halfWidth / 2.0f;
        GL11.glBegin(1);
        GL11.glVertex2d(x - xOffset, y + yOffset);
        GL11.glVertex2d((double)x + length - (double)xOffset, (double)y - length + (double)yOffset);
        GL11.glVertex2d((double)x + length - (double)xOffset, (double)y - length + (double)yOffset);
        GL11.glVertex2d((double)x + 2.0 * length - (double)xOffset, y + yOffset);
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(2848);
        GL11.glPopMatrix();
    }

    public static void glColor(int n) {
        GL11.glColor4f((float)(n >> 16 & 0xFF) / 255.0f, (float)(n >> 8 & 0xFF) / 255.0f, (float)(n & 0xFF) / 255.0f, (float)(n >> 24 & 0xFF) / 255.0f);
    }

    public static void drawRoundedGradientOutlinedRectangle(float n, float n2, float n3, float n4, float n5, int n6, int n7, int n8) {
        n = (float)((double)n * 2.0);
        n2 = (float)((double)n2 * 2.0);
        n3 = (float)((double)n3 * 2.0);
        n4 = (float)((double)n4 * 2.0);
        GL11.glPushAttrib(1);
        GL11.glScaled(0.5, 0.5, 0.5);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBegin(9);
        Class230.glColor(n6);
        for (int i = 0; i <= 90; i += 3) {
            double n9 = (float)i * ((float)Math.PI / 180);
            GL11.glVertex2d((double)(n + n5) + Math.sin(n9) * (double)n5 * -1.0, (double)(n2 + n5) + Math.cos(n9) * (double)n5 * -1.0);
        }
        for (int j = 90; j <= 180; j += 3) {
            double n10 = (float)j * ((float)Math.PI / 180);
            GL11.glVertex2d((double)(n + n5) + Math.sin(n10) * (double)n5 * -1.0, (double)(n4 - n5) + Math.cos(n10) * (double)n5 * -1.0);
        }
        for (int k = 0; k <= 90; k += 3) {
            double n11 = (float)k * ((float)Math.PI / 180);
            GL11.glVertex2d((double)(n3 - n5) + Math.sin(n11) * (double)n5, (double)(n4 - n5) + Math.cos(n11) * (double)n5);
        }
        for (int l = 90; l <= 180; l += 3) {
            double n12 = (float)l * ((float)Math.PI / 180);
            GL11.glVertex2d((double)(n3 - n5) + Math.sin(n12) * (double)n5, (double)(n2 + n5) + Math.cos(n12) * (double)n5);
        }
        GL11.glEnd();
        GL11.glPushMatrix();
        GL11.glShadeModel(7425);
        GL11.glLineWidth(2.0f);
        GL11.glBegin(2);
        if ((long)n7 != 0L) {
            Class230.glColor(n7);
        }
        for (int n13 = 0; n13 <= 90; n13 += 3) {
            double n14 = (float)n13 * ((float)Math.PI / 180);
            GL11.glVertex2d((double)(n + n5) + Math.sin(n14) * (double)n5 * -1.0, (double)(n2 + n5) + Math.cos(n14) * (double)n5 * -1.0);
        }
        for (int n15 = 90; n15 <= 180; n15 += 3) {
            double n16 = (float)n15 * ((float)Math.PI / 180);
            GL11.glVertex2d((double)(n + n5) + Math.sin(n16) * (double)n5 * -1.0, (double)(n4 - n5) + Math.cos(n16) * (double)n5 * -1.0);
        }
        if (n8 != 0) {
            Class230.glColor(n8);
        }
        for (int n17 = 0; n17 <= 90; n17 += 3) {
            double n18 = (float)n17 * ((float)Math.PI / 180);
            GL11.glVertex2d((double)(n3 - n5) + Math.sin(n18) * (double)n5, (double)(n4 - n5) + Math.cos(n18) * (double)n5);
        }
        for (int n19 = 90; n19 <= 180; n19 += 3) {
            double n20 = (float)n19 * ((float)Math.PI / 180);
            GL11.glVertex2d((double)(n3 - n5) + Math.sin(n20) * (double)n5, (double)(n2 + n5) + Math.cos(n20) * (double)n5);
        }
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glScaled(2.0, 2.0, 2.0);
        GL11.glPopAttrib();
        GL11.glLineWidth(1.0f);
        GL11.glShadeModel(7424);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public static void drawRoundedRectangle(float n, float n2, float n3, float n4, float n5, int n6) {
        n = (float)((double)n * 2.0);
        n2 = (float)((double)n2 * 2.0);
        n3 = (float)((double)n3 * 2.0);
        n4 = (float)((double)n4 * 2.0);
        GL11.glPushAttrib(0);
        GL11.glScaled(0.5, 0.5, 0.5);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBegin(9);
        Class230.glColor(n6);
        for (int i = 0; i <= 90; i += 3) {
            double n7 = (float)i * ((float)Math.PI / 180);
            GL11.glVertex2d((double)(n + n5) + Math.sin(n7) * (double)n5 * -1.0, (double)(n2 + n5) + Math.cos(n7) * (double)n5 * -1.0);
        }
        for (int j = 90; j <= 180; j += 3) {
            double n8 = (float)j * ((float)Math.PI / 180);
            GL11.glVertex2d((double)(n + n5) + Math.sin(n8) * (double)n5 * -1.0, (double)(n4 - n5) + Math.cos(n8) * (double)n5 * -1.0);
        }
        for (int k = 0; k <= 90; k += 3) {
            double n9 = (float)k * ((float)Math.PI / 180);
            GL11.glVertex2d((double)(n3 - n5) + Math.sin(n9) * (double)n5, (double)(n4 - n5) + Math.cos(n9) * (double)n5);
        }
        for (int l = 90; l <= 180; l += 3) {
            double n10 = (float)l * ((float)Math.PI / 180);
            GL11.glVertex2d((double)(n3 - n5) + Math.sin(n10) * (double)n5, (double)(n2 + n5) + Math.cos(n10) * (double)n5);
        }
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glScaled(2.0, 2.0, 2.0);
        GL11.glPopAttrib();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public static void drawRoundedGradientRect(float x, float y, float x2, float y2, float n5, int n6, int n7, int n8, int n9) {
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glPushAttrib(0);
        GL11.glScaled(0.5, 0.5, 0.5);
        x = (float)((double)x * 2.0);
        y = (float)((double)y * 2.0);
        x2 = (float)((double)x2 * 2.0);
        y2 = (float)((double)y2 * 2.0);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        Class230.glColor(n6);
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glBegin(9);
        for (int i = 0; i <= 90; i += 3) {
            double n10 = (float)i * ((float)Math.PI / 180);
            GL11.glVertex2d((double)(x + n5) + Math.sin(n10) * (double)n5 * -1.0, (double)(y + n5) + Math.cos(n10) * (double)n5 * -1.0);
        }
        Class230.glColor(n7);
        for (int j = 90; j <= 180; j += 3) {
            double n11 = (float)j * ((float)Math.PI / 180);
            GL11.glVertex2d((double)(x + n5) + Math.sin(n11) * (double)n5 * -1.0, (double)(y2 - n5) + Math.cos(n11) * (double)n5 * -1.0);
        }
        Class230.glColor(n8);
        for (int k = 0; k <= 90; k += 3) {
            double n12 = (float)k * ((float)Math.PI / 180);
            GL11.glVertex2d((double)(x2 - n5) + Math.sin(n12) * (double)n5, (double)(y2 - n5) + Math.cos(n12) * (double)n5);
        }
        Class230.glColor(n9);
        for (int l = 90; l <= 180; l += 3) {
            double n13 = (float)l * ((float)Math.PI / 180);
            GL11.glVertex2d((double)(x2 - n5) + Math.sin(n13) * (double)n5, (double)(y + n5) + Math.cos(n13) * (double)n5);
        }
        GL11.glEnd();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glDisable(3042);
        GL11.glEnable(3553);
        GL11.glScaled(2.0, 2.0, 2.0);
        GL11.glPopAttrib();
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glDisable(2848);
        GL11.glShadeModel(7424);
    }

    public static int setAlpha(int rgb, double alpha) {
        if (alpha < 0.0 || alpha > 1.0) {
            alpha = 0.5;
        }
        int red = rgb >> 16 & 0xFF;
        int green = rgb >> 8 & 0xFF;
        int blue = rgb & 0xFF;
        int alphaInt = (int)(alpha * 255.0);
        int rgba = alphaInt << 24 | red << 16 | green << 8 | blue;
        return rgba;
    }
}

