package Rename;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Timer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;

public class Class465
implements Class3 {
    private static final Map<Integer, Boolean> glCapMap = new HashMap<Integer, Boolean>();

    public static void drawBox(double x, double y, double z, float entityWidth, float entityHeight, Color color) {
        Class465.beginOpenGL(color, false, true, false, 2.0f);
        Class465.drawFilledBox(x - (double)(entityWidth / 2.0f), y, z - (double)(entityWidth / 2.0f), entityWidth, entityHeight, entityWidth, color);
        Class465.endOpenGL(true, false);
    }

    public static int colorSwitch(Color firstColor, Color secondColor, float time, int index, long timePerIndex, double speed) {
        return Class465.colorSwitch(firstColor, secondColor, time, index, timePerIndex, speed, 255.0);
    }

    public static int colorSwitch(Color firstColor, Color secondColor, float time, int index, long timePerIndex, double speed, double alpha) {
        long now = (long)(speed * (double)System.currentTimeMillis() + (double)((long)index * timePerIndex));
        float redDiff = (float)(firstColor.getRed() - secondColor.getRed()) / time;
        float greenDiff = (float)(firstColor.getGreen() - secondColor.getGreen()) / time;
        float blueDiff = (float)(firstColor.getBlue() - secondColor.getBlue()) / time;
        int red = Math.round((float)secondColor.getRed() + redDiff * (float)(now % (long)time));
        int green = Math.round((float)secondColor.getGreen() + greenDiff * (float)(now % (long)time));
        int blue = Math.round((float)secondColor.getBlue() + blueDiff * (float)(now % (long)time));
        float redInverseDiff = (float)(secondColor.getRed() - firstColor.getRed()) / time;
        float greenInverseDiff = (float)(secondColor.getGreen() - firstColor.getGreen()) / time;
        float blueInverseDiff = (float)(secondColor.getBlue() - firstColor.getBlue()) / time;
        int inverseRed = Math.round((float)firstColor.getRed() + redInverseDiff * (float)(now % (long)time));
        int inverseGreen = Math.round((float)firstColor.getGreen() + greenInverseDiff * (float)(now % (long)time));
        int inverseBlue = Math.round((float)firstColor.getBlue() + blueInverseDiff * (float)(now % (long)time));
        if (now % ((long)time * 2L) < (long)time) {
            return Class460.getColor(inverseRed, inverseGreen, inverseBlue, (int)alpha);
        }
        return Class460.getColor(red, green, blue, (int)alpha);
    }

    public static int hexColor(int red, int green, int blue) {
        return Class465.hexColor(red, green, blue, 255);
    }

    public static int hexColor(int red, int green, int blue, int alpha) {
        return alpha << 24 | red << 16 | green << 8 | blue;
    }

    public static void drawImageWithOpacity(String location, int x, int y, int width, int height, float opacity) {
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.color(1.0f, 1.0f, 1.0f, opacity);
        Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(location));
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0f, 0.0f, width, height, (float)width, (float)height);
        GlStateManager.disableBlend();
    }

    public static double deltaTime() {
        return Minecraft.getDebugFPS() > 0 ? 1.0 / (double)Minecraft.getDebugFPS() : 1.0;
    }

    public static void drawImageTest(ResourceLocation image, int x, int y, int width, int height) {
        GL11.glDisable(2929);
        GL11.glEnable(3042);
        GL11.glDepthMask(false);
        GL14.glBlendFuncSeparate(770, 771, 1, 0);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        mc.getTextureManager().bindTexture(image);
        Class465.drawModalRectWithCustomSizedTexture(x, y, 0.0f, 0.0f, width, height, width, height);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glEnable(2929);
    }

    public static void drawBox(EntityLivingBase player, Color color, float partialTicks) {
        double x = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double)partialTicks - Class465.mc.getRenderManager().viewerPosX;
        double y = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double)partialTicks - Class465.mc.getRenderManager().viewerPosY;
        double z = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double)partialTicks - Class465.mc.getRenderManager().viewerPosZ;
        float expand = 0.2f;
        Class465.drawBox(x, y, z, player.width + expand, player.height + expand, color);
    }

    public static void drawFilledBox(double x, double y, double z, double sizeX, double sizeY, double sizeZ, Color color) {
        Class465.beginOpenGL(color, true, true, true, 1.5f);
        GL11.glBegin(7);
        double[][] vertices = new double[][]{{x, y, z, x, y + sizeY, z, x + sizeX, y + sizeY, z, x + sizeX, y, z}, {x, y, z + sizeZ, x + sizeX, y, z + sizeZ, x + sizeX, y + sizeY, z + sizeZ, x, y + sizeY, z + sizeZ}, {x, y + sizeY, z, x + sizeX, y + sizeY, z, x + sizeX, y + sizeY, z + sizeZ, x, y + sizeY, z + sizeZ}, {x, y, z, x, y, z + sizeZ, x + sizeX, y, z + sizeZ, x + sizeX, y, z}, {x, y, z, x, y + sizeY, z, x, y + sizeY, z + sizeZ, x, y, z + sizeZ}, {x + sizeX, y, z, x + sizeX, y, z + sizeZ, x + sizeX, y + sizeY, z + sizeZ, x + sizeX, y + sizeY, z}};
        for (double[] face : vertices) {
            for (int i = 0; i < face.length; i += 3) {
                GL11.glVertex3d(face[i], face[i + 1], face[i + 2]);
            }
        }
        GL11.glEnd();
        Class465.endOpenGL(true, true);
    }

    public static void enableRender3D(boolean disableDepth) {
        if (disableDepth) {
            GL11.glDepthMask(false);
            GL11.glDisable(2929);
        }
        GL11.glDisable(3008);
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(1.0f);
    }

    public static void disableRender3D(boolean enableDepth) {
        if (enableDepth) {
            GL11.glDepthMask(true);
            GL11.glEnable(2929);
        }
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glEnable(3008);
        GL11.glDisable(2848);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }

    private static void beginOpenGL(Color color, boolean depthTest, boolean blend, boolean lineSmooth, float lineWidth) {
        GL11.glPushAttrib(24844);
        GL11.glPushMatrix();
        if (blend) {
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
        }
        GL11.glColor4f((float)color.getRed() / 255.0f, (float)color.getGreen() / 255.0f, (float)color.getBlue() / 255.0f, (float)color.getAlpha() / 255.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2884);
        GL11.glDepthMask((!depthTest ? 1 : 0) != 0);
        if (depthTest) {
            GL11.glEnable(2929);
        } else {
            GL11.glDisable(2929);
        }
        if (lineSmooth) {
            GL11.glEnable(2848);
            GL11.glHint(3154, 4354);
            GL11.glLineWidth(lineWidth);
        }
    }

    public static void setGlCap(int cap, boolean state) {
        glCapMap.put(cap, GL11.glGetBoolean(cap));
        Class465.setGlState(cap, state);
    }

    public static void setGlState(int cap, boolean state) {
        if (state) {
            GL11.glEnable(cap);
        } else {
            GL11.glDisable(cap);
        }
    }

    private static void endOpenGL(boolean blend, boolean lineSmooth) {
        if (!blend) {
            GL11.glDisable(3042);
        }
        if (lineSmooth) {
            GL11.glDisable(2848);
        }
        GL11.glDisable(2929);
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GL11.glEnable(2884);
        GL11.glEnable(3553);
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glLineWidth(1.0f);
        GL11.glPopMatrix();
        GL11.glPopAttrib();
    }

    public static void drawShadow(float x, float y2, float x2, float y22) {
        Class465.drawTexturedRect(x - 9.0f, y2 - 9.0f, 9.0f, 9.0f, new ResourceLocation("shaders/paneltopleft.png"), Color.white.getRGB());
        Class465.drawTexturedRect(x - 9.0f, y22, 9.0f, 9.0f, new ResourceLocation("shaders/panelbottomleft.png"), Color.white.getRGB());
        Class465.drawTexturedRect(x2, y22, 9.0f, 9.0f, new ResourceLocation("shaders/panelbottomright.png"), Color.white.getRGB());
        Class465.drawTexturedRect(x2, y2 - 9.0f, 9.0f, 9.0f, new ResourceLocation("shaders/paneltopright.png"), Color.white.getRGB());
        Class465.drawTexturedRect(x - 9.0f, y2, 9.0f, y22 - y2, new ResourceLocation("shaders/panelleft.png"), Color.white.getRGB());
        Class465.drawTexturedRect(x2, y2, 9.0f, y22 - y2, new ResourceLocation("shaders/panelright.png"), Color.white.getRGB());
        Class465.drawTexturedRect(x, y2 - 9.0f, x2 - x, 9.0f, new ResourceLocation("shaders/paneltop.png"), Color.white.getRGB());
        Class465.drawTexturedRect(x, y22, x2 - x, 9.0f, new ResourceLocation("shaders/panelbottom.png"), Color.white.getRGB());
    }

    public static void quickDrawRect(float x, float y, float x2, float y2) {
        GL11.glBegin(7);
        GL11.glVertex2d(x2, y);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x, y2);
        GL11.glVertex2d(x2, y2);
        GL11.glEnd();
    }

    public static void drawTexturedRect(float x, float y2, float width, float height, ResourceLocation image, int color) {
        GL11.glPushMatrix();
        boolean enableBlend = GL11.glIsEnabled(3042);
        boolean disableAlpha = !GL11.glIsEnabled(3008);
        boolean bl = disableAlpha;
        if (!enableBlend) {
            GL11.glEnable(3042);
        }
        if (!disableAlpha) {
            GL11.glDisable(3008);
        }
        Minecraft.getMinecraft().getTextureManager().bindTexture(image);
        Class465.glColor(color);
        Class465.drawModalRectWithCustomSizedTexture(x, y2, 0.0f, 0.0f, width, height, width, height);
        if (!enableBlend) {
            GL11.glDisable(3042);
        }
        if (!disableAlpha) {
            GL11.glEnable(3008);
        }
        GL11.glPopMatrix();
    }

    public static void drawModalRectWithCustomSizedTexture(float x, float y2, float u2, float v2, float width, float height, float textureWidth, float textureHeight) {
        float f = 1.0f / textureWidth;
        float f1 = 1.0f / textureHeight;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos(x, y2 + height, 0.0).tex(u2 * f, (v2 + height) * f1).endVertex();
        worldrenderer.pos(x + width, y2 + height, 0.0).tex((u2 + width) * f, (v2 + height) * f1).endVertex();
        worldrenderer.pos(x + width, y2, 0.0).tex((u2 + width) * f, v2 * f1).endVertex();
        worldrenderer.pos(x, y2, 0.0).tex(u2 * f, v2 * f1).endVertex();
        tessellator.draw();
    }

    public static void drawImage(ResourceLocation imageLocation, double x, double y, double width, double height, int color) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        GlStateManager.disableAlpha();
        mc.getTextureManager().bindTexture(imageLocation);
        Class465.glColor(color);
        Gui.drawModalRectWithCustomSizedTexture((float)x, (float)y, 0.0f, 0.0f, (float)width, (float)height, (float)width, (float)height);
        GlStateManager.resetColor();
        GlStateManager.bindTexture(0);
        GlStateManager.enableAlpha();
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
    }

    public static void drawRoundedRect(float left, float top, float right, float bottom, int color) {
        Class465.drawRect1(left - 0.5f, top + 0.5f, left, bottom - 0.5f, color);
        Class465.drawRect1(right, top + 0.5f, right + 0.5f, bottom - 0.5f, color);
        Class465.drawRect1(left + 0.5f, top - 0.5f, right - 0.5f, top, color);
        Class465.drawRect1(left + 0.5f, bottom, right - 0.5f, bottom + 0.5f, color);
        Class465.drawRect1(left, top, right, bottom, color);
    }

    public static void drawRect1(float left, float top, float right, float bottom, int color) {
        float f3;
        if (left < right) {
            f3 = left;
            left = right;
            right = f3;
        }
        if (top < bottom) {
            f3 = top;
            top = bottom;
            bottom = f3;
        }
        f3 = (float)(color >> 24 & 0xFF) / 255.0f;
        float f = (float)(color >> 16 & 0xFF) / 255.0f;
        float f1 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f2 = (float)(color & 0xFF) / 255.0f;
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer WorldRenderer2 = tessellator.getWorldRenderer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(f, f1, f2, f3);
        WorldRenderer2.begin(7, DefaultVertexFormats.POSITION);
        WorldRenderer2.pos(left, bottom, 0.0).endVertex();
        WorldRenderer2.pos(right, bottom, 0.0).endVertex();
        WorldRenderer2.pos(right, top, 0.0).endVertex();
        WorldRenderer2.pos(left, top, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static boolean isHovering(float x, float y, float width, float height, int mouseX, int mouseY) {
        return (float)mouseX >= x && (float)mouseY >= y && (float)mouseX < x + width && (float)mouseY < y + height;
    }

    public static void resetColor() {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public static void setAlphaLimit(float limit) {
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, (float)((double)limit * 0.01));
    }

    public static void stopGlScissor() {
        GL11.glDisable(3089);
        GL11.glPopMatrix();
    }

    public static void startGlScissor(int x, int y, int width, int height) {
        int scaleFactor = new ScaledResolution(mc).getScaleFactor();
        GL11.glPushMatrix();
        GL11.glEnable(3089);
        int var10000 = x * scaleFactor;
        int var10001 = Class465.mc.displayHeight - (y + height) * scaleFactor;
        int var10002 = width * scaleFactor;
        GL11.glScissor(var10000, var10001, var10002, (height += 14) * scaleFactor);
    }

    public static void drawLoadingCircleFast(float x, float y, Color color) {
        for (int i = 0; i < 2; ++i) {
            int rot = (int)(System.nanoTime() / 1200000L * (long)i % 360L);
            Class465.drawCircle(x, y, (float)i * 7.0f, rot - 180, rot, color);
        }
    }

    public static void drawLoadingCircleNormal(float x, float y, Color color) {
        for (int i = 0; i < 2; ++i) {
            int rot = (int)(System.nanoTime() / 1200000L * (long)i % 360L);
            Class465.drawCircle(x, y, (float)i * 7.0f, rot - 180, rot, color);
        }
    }

    public static void drawLoadingCircleSlow(float x, float y, Color color) {
        for (int i = 0; i < 2; ++i) {
            int rot = (int)(System.nanoTime() / 8200000L * (long)i % 360L);
            Class465.drawCircle(x, y, (float)i * 7.0f, rot - 180, rot, color);
        }
    }

    public static void drawCircle(float x, float y, float radius, int start, int end, Color color) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        Class465.glColor(color.getRGB());
        GL11.glEnable(2848);
        GL11.glLineWidth(2.5f);
        GL11.glBegin(3);
        for (float i = (float)end; i >= (float)start; i -= 4.0f) {
            GL11.glVertex2f((float)((double)x + Math.cos(i * MathHelper.PI / 180.0f) * (double)(radius * 1.001f)), (float)((double)y + Math.sin(i * MathHelper.PI / 180.0f) * (double)(radius * 1.001f)));
        }
        GL11.glEnd();
        GL11.glDisable(2848);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawCircle2(double x, double y, float radius, int color) {
        if (radius != 0.0f) {
            float correctRadius = radius * 2.0f;
            Class466.setup2DRendering(() -> {
                Class465.glColor(color);
                GL11.glEnable(2832);
                GL11.glHint(3153, 4354);
                GL11.glPointSize(correctRadius);
                Class466.setupRendering(0, () -> GL11.glVertex2d(x, y));
                GL11.glDisable(2832);
                GlStateManager.resetColor();
            });
        }
    }

    public static void glColor(int color) {
        float f = (float)(color >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(color >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(color & 0xFF) / 255.0f;
        GL11.glColor4f(f1, f2, f3, f);
    }

    public static void drawRect(double left, double top, double right, double bottom, int color) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        double minX = Math.min(left, right);
        double maxX = Math.max(left, right);
        double minY = Math.min(top, bottom);
        double maxY = Math.max(top, bottom);
        float alpha = (float)(color >> 24 & 0xFF) / 255.0f;
        float red = (float)(color >> 16 & 0xFF) / 255.0f;
        float green = (float)(color >> 8 & 0xFF) / 255.0f;
        float blue = (float)(color & 0xFF) / 255.0f;
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(red, green, blue, alpha);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        worldrenderer.pos(minX, maxY, 0.0).endVertex();
        worldrenderer.pos(maxX, maxY, 0.0).endVertex();
        worldrenderer.pos(maxX, minY, 0.0).endVertex();
        worldrenderer.pos(minX, minY, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static Color reAlpha(Color color, int alpha) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }

    public static void color(int color) {
        GL11.glColor4ub((byte)(color >> 16 & 0xFF), (byte)(color >> 8 & 0xFF), (byte)(color & 0xFF), (byte)(color >> 24 & 0xFF));
    }

    public static void drawLoadingCircle(float x, float y) {
        for (int i = 0; i < 2; ++i) {
            int rot = (int)(System.nanoTime() / 5000000L * (long)i % 360L);
            Class465.drawCircle(x, y, i * 8, rot - 180, rot);
        }
    }

    public static void drawCircle(float x, float y, float radius, int start, int end) {
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        Class465.glColor(Color.WHITE.getRGB());
        GL11.glEnable(2848);
        GL11.glLineWidth(3.0f);
        GL11.glBegin(3);
        for (float i = (float)end; i >= (float)start; i -= 4.0f) {
            GL11.glVertex2f((float)((double)x + Math.cos((double)i * Math.PI / 180.0) * (double)(radius * 1.001f)), (float)((double)y + Math.sin((double)i * Math.PI / 180.0) * (double)(radius * 1.001f)));
        }
        GL11.glEnd();
        GL11.glDisable(2848);
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    public static void drawEntityESP(double x, double y, double z, double x1, double y1, double z1, float red, float green, float blue, float alpha) {
        GL11.glPushMatrix();
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glLineWidth(1.0f);
        GL11.glColor4f(red, green, blue, 1.0f);
        GL11.glColor4f(red, green, blue, alpha);
        Class465.drawBoundingBox(new AxisAlignedBB(x, y, z, x1, y1, z1));
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
        GL11.glPopMatrix();
    }

    public static void drawBoundingBox(AxisAlignedBB aa) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(aa.minX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.minX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.minX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.minZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.maxY, aa.maxZ).endVertex();
        worldRenderer.pos(aa.maxX, aa.minY, aa.maxZ).endVertex();
        tessellator.draw();
    }

    public static void drawBlockBox(BlockPos blockPos, Color color, boolean outline) {
        RenderManager renderManager = mc.getRenderManager();
        Timer timer = Class465.mc.timer;
        double x = (double)blockPos.getX() - RenderManager.renderPosX;
        double y = (double)blockPos.getY() - RenderManager.renderPosY;
        double z = (double)blockPos.getZ() - RenderManager.renderPosZ;
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB(x, y, z, x + 1.0, y + 1.0, z + 1.0);
        Block block = Class465.mc.theWorld.getBlockState(blockPos).getBlock();
        if (block != null) {
            EntityPlayerSP player = Class465.mc.thePlayer;
            double posX = player.lastTickPosX + (player.posX - player.lastTickPosX) * (double)timer.renderPartialTicks;
            double posY = player.lastTickPosY + (player.posY - player.lastTickPosY) * (double)timer.renderPartialTicks;
            double posZ = player.lastTickPosZ + (player.posZ - player.lastTickPosZ) * (double)timer.renderPartialTicks;
            axisAlignedBB = block.getSelectedBoundingBox(Class465.mc.theWorld, blockPos).expand(0.002f, 0.002f, 0.002f).offset(-posX, -posY, -posZ);
        }
        GL11.glBlendFunc(770, 771);
        Class465.enableGlCap(3042);
        Class465.disableGlCap(3553, 2929);
        GL11.glDepthMask(false);
        Class465.glColor(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha() != 255 ? color.getAlpha() : (outline ? 26 : 35));
        Class465.drawFilledBox(axisAlignedBB);
        if (outline) {
            GL11.glLineWidth(1.0f);
            Class465.enableGlCap(2848);
            Class465.glColor(color.getRGB());
            RenderGlobal.drawSelectionBoundingBox(axisAlignedBB);
        }
        GlStateManager.resetColor();
        GL11.glDepthMask(true);
        Class465.resetCaps();
    }

    public static void disableGlCap(int ... caps) {
        int[] var1 = caps;
        int var2 = caps.length;
        for (int var3 = 0; var3 < var2; ++var3) {
            int cap = var1[var3];
            Class465.setGlCap(cap, false);
        }
    }

    public static void drawFilledBox(AxisAlignedBB axisAlignedBB) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldRenderer = tessellator.getWorldRenderer();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        tessellator.draw();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.minX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.minZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.maxY, axisAlignedBB.maxZ).endVertex();
        worldRenderer.pos(axisAlignedBB.maxX, axisAlignedBB.minY, axisAlignedBB.maxZ).endVertex();
        tessellator.draw();
    }

    public static void glColor(int red, int green, int blue, int alpha) {
        GlStateManager.color((float)red / 255.0f, (float)green / 255.0f, (float)blue / 255.0f, (float)alpha / 255.0f);
    }

    public static void resetCaps() {
        glCapMap.forEach(Class465::setGlState);
    }

    public static void enableGlCap(int cap) {
        Class465.setGlCap(cap, true);
    }

    public static void scissor(double x, double y, double width, double height) {
        ScaledResolution sr = new ScaledResolution(mc);
        double scale = sr.getScaleFactor();
        double finalHeight = height * scale;
        double finalY = ((double)sr.getScaledHeight() - y) * scale;
        double finalX = x * scale;
        double finalWidth = width * scale;
        GL11.glScissor((int)finalX, (int)(finalY - finalHeight), (int)finalWidth, (int)finalHeight);
    }
}

