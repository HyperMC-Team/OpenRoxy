package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.Vec3;
import org.lwjgl.BufferUtils;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public final class Class169 {
    public static final FloatBuffer MODELVIEW = BufferUtils.createFloatBuffer(16);
    public static final FloatBuffer PROJECTION = BufferUtils.createFloatBuffer(16);
    public static final IntBuffer VIEWPORT = BufferUtils.createIntBuffer(16);
    public static final FloatBuffer TO_SCREEN_BUFFER = BufferUtils.createFloatBuffer(3);
    private static final FloatBuffer colorBuffer;
    private static final Vec3 LIGHT0_POS;
    private static final Vec3 LIGHT1_POS;
    public static final FloatBuffer TO_WORLD_BUFFER;

    private Class169() {
    }

    public static int getMouseX() {
        return Mouse.getX() * Class169.getScreenWidth() / Minecraft.getMinecraft().displayWidth;
    }

    public static int getMouseY() {
        return Class169.getScreenHeight() - Mouse.getY() * Class169.getScreenHeight() / Minecraft.getMinecraft().displayWidth - 1;
    }

    public static int getScreenWidth() {
        return Minecraft.getMinecraft().displayWidth / Class169.getScaleFactor();
    }

    public static int getScreenHeight() {
        return Minecraft.getMinecraft().displayHeight / Class169.getScaleFactor();
    }

    public static int getScaleFactor() {
        int scaleFactor = 1;
        boolean isUnicode = Minecraft.getMinecraft().isUnicode();
        int guiScale = Minecraft.getMinecraft().gameSettings.guiScale;
        if (guiScale == 0) {
            guiScale = 1000;
        }
        while (scaleFactor < guiScale && Minecraft.getMinecraft().displayWidth / (scaleFactor + 1) >= 320 && Minecraft.getMinecraft().displayHeight / (scaleFactor + 1) >= 240) {
            ++scaleFactor;
        }
        if (isUnicode && scaleFactor % 2 != 0 && scaleFactor != 1) {
            --scaleFactor;
        }
        return scaleFactor;
    }

    public static void init() {
    }

    public static float[] getColor(int hex) {
        return new float[]{(float)(hex >> 16 & 0xFF) / 255.0f, (float)(hex >> 8 & 0xFF) / 255.0f, (float)(hex & 0xFF) / 255.0f, (float)(hex >> 24 & 0xFF) / 255.0f};
    }

    public static void glColor(int hex) {
        float[] color = Class169.getColor(hex);
        GlStateManager.color(color[0], color[1], color[2], color[3]);
    }

    public static void rotateX(float angle, double x, double y, double z) {
        GlStateManager.translate(x, y, z);
        GlStateManager.rotate(angle, 1.0f, 0.0f, 0.0f);
        GlStateManager.translate(-x, -y, -z);
    }

    public static void rotateY(float angle, double x, double y, double z) {
        GlStateManager.translate(x, y, z);
        GlStateManager.rotate(angle, 0.0f, 1.0f, 0.0f);
        GlStateManager.translate(-x, -y, -z);
    }

    public static void rotateZ(float angle, double x, double y, double z) {
        GlStateManager.translate(x, y, z);
        GlStateManager.rotate(angle, 0.0f, 0.0f, 1.0f);
        GlStateManager.translate(-x, -y, -z);
    }

    public static Class174 toScreen(Class174 pos) {
        return Class169.toScreen(pos.getX(), pos.getY(), pos.getZ());
    }

    public static Class174 toScreen(double x, double y, double z) {
        boolean result = GLU.gluProject((float)x, (float)y, (float)z, MODELVIEW, PROJECTION, VIEWPORT, TO_SCREEN_BUFFER.clear());
        if (result) {
            return new Class174(TO_SCREEN_BUFFER.get(0), (float)Display.getHeight() - TO_SCREEN_BUFFER.get(1), TO_SCREEN_BUFFER.get(2));
        }
        return null;
    }

    public static Class174 toWorld(Class174 pos) {
        return Class169.toWorld(pos.getX(), pos.getY(), pos.getZ());
    }

    public static Class174 toWorld(double x, double y, double z) {
        boolean result = GLU.gluUnProject((float)x, (float)y, (float)z, MODELVIEW, PROJECTION, VIEWPORT, TO_WORLD_BUFFER.clear());
        if (result) {
            return new Class174(TO_WORLD_BUFFER.get(0), TO_WORLD_BUFFER.get(1), TO_WORLD_BUFFER.get(2));
        }
        return null;
    }

    public static void startSmooth() {
        GL11.glEnable(2848);
        GL11.glEnable(2881);
        GL11.glEnable(2832);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
        GL11.glHint(3153, 4354);
    }

    public static void endSmooth() {
        GL11.glDisable(2848);
        GL11.glDisable(2881);
        GL11.glEnable(2832);
    }

    public static FloatBuffer getModelview() {
        return MODELVIEW;
    }

    public static FloatBuffer getProjection() {
        return PROJECTION;
    }

    public static IntBuffer getViewport() {
        return VIEWPORT;
    }

    public static void enableGUIStandardItemLighting() {
        GlStateManager.pushMatrix();
        GlStateManager.rotate(-30.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(165.0f, 1.0f, 0.0f, 0.0f);
        Class169.enableStandardItemLighting();
        GlStateManager.popMatrix();
    }

    public static void enableStandardItemLighting() {
        GlStateManager.enableLighting();
        GlStateManager.enableLight(0);
        GlStateManager.enableLight(1);
        GlStateManager.enableColorMaterial();
        GlStateManager.colorMaterial(1032, 5634);
        float n = 0.4f;
        float n2 = 0.6f;
        float n3 = 0.0f;
        GL11.glLight(16384, 4611, Class169.setColorBuffer(Class169.LIGHT0_POS.xCoord, Class169.LIGHT0_POS.yCoord, Class169.LIGHT0_POS.zCoord, 0.0));
        GL11.glLight(16384, 4609, Class169.setColorBuffer(0.6f, 0.6f, 0.6f, 1.0));
        GL11.glLight(16384, 4608, Class169.setColorBuffer(0.0, 0.0, 0.0, 1.0));
        GL11.glLight(16384, 4610, Class169.setColorBuffer(0.0, 0.0, 0.0, 1.0));
        GL11.glLight(16385, 4611, Class169.setColorBuffer(Class169.LIGHT1_POS.xCoord, Class169.LIGHT1_POS.yCoord, Class169.LIGHT1_POS.zCoord, 0.0));
        GL11.glLight(16385, 4609, Class169.setColorBuffer(0.6f, 0.6f, 0.6f, 1.0));
        GL11.glLight(16385, 4608, Class169.setColorBuffer(0.0, 0.0, 0.0, 1.0));
        GL11.glLight(16385, 4610, Class169.setColorBuffer(0.0, 0.0, 0.0, 1.0));
        GlStateManager.shadeModel(7424);
        GL11.glLightModel(2899, Class169.setColorBuffer(0.4f, 0.4f, 0.4f, 1.0));
    }

    public static void disableStandardItemLighting() {
        GlStateManager.disableLighting();
        GlStateManager.disableLight(0);
        GlStateManager.disableLight(1);
        GlStateManager.disableColorMaterial();
    }

    private static FloatBuffer setColorBuffer(double p_setColorBuffer_0_, double p_setColorBuffer_2_, double p_setColorBuffer_4_, double p_setColorBuffer_6_) {
        return Class169.setColorBuffer((float)p_setColorBuffer_0_, (float)p_setColorBuffer_2_, (float)p_setColorBuffer_4_, (float)p_setColorBuffer_6_);
    }

    public static void startBlend() {
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
    }

    public static void endBlend() {
        GlStateManager.disableBlend();
    }

    static {
        TO_WORLD_BUFFER = BufferUtils.createFloatBuffer(3);
        colorBuffer = GLAllocation.createDirectFloatBuffer(16);
        LIGHT0_POS = new Vec3(0.2f, 1.0, -0.7f).normalize();
        LIGHT1_POS = new Vec3(-0.2f, 1.0, 0.7f).normalize();
    }
}

