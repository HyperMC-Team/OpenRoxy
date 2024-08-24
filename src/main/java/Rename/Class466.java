package Rename;

import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public class Class466 {
    public static int[] enabledCaps = new int[32];

    public static void startBlend() {
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
    }

    public static void setupRendering(int mode, Runnable runnable) {
        GlStateManager.glBegin(mode);
        runnable.run();
        GlStateManager.glEnd();
    }

    public static void enableDepth() {
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
    }

    public static void disableCaps() {
        for (int cap : enabledCaps) {
            GL11.glDisable(cap);
        }
    }

    public static void enableCaps(int ... caps) {
        int[] var1 = caps;
        int var2 = caps.length;
        for (int var3 = 0; var3 < var2; ++var3) {
            int cap = var1[var3];
            GL11.glEnable(cap);
        }
        enabledCaps = caps;
    }

    public static void enableTexture2D() {
        GL11.glEnable(3553);
    }

    public static void disableTexture2D() {
        GL11.glDisable(3553);
    }

    public static void enableBlending() {
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
    }

    public static void disableDepth() {
        GL11.glDepthMask(false);
        GL11.glDisable(2929);
    }

    public static void disableBlending() {
        GL11.glDisable(3042);
    }

    public static void endBlend() {
        GlStateManager.disableBlend();
    }

    public static void render(int mode, Runnable render) {
        GlStateManager.glBegin(mode);
        render.run();
        GlStateManager.glEnd();
    }

    public static void setup2DRendering(Runnable f) {
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        f.run();
        GL11.glEnable(3553);
        GlStateManager.disableBlend();
    }

    public static void setup2DRendering() {
        Class466.setup2DRendering(true);
    }

    public static void setup2DRendering(boolean blend) {
        if (blend) {
            Class466.startBlend();
        }
        GlStateManager.disableTexture2D();
    }

    public static void end2DRendering() {
        GlStateManager.enableTexture2D();
        Class466.endBlend();
    }

    public static void rotate(float x, float y, float rotate, Runnable f) {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, 0.0f);
        GlStateManager.rotate(rotate, 0.0f, 0.0f, -1.0f);
        GlStateManager.translate(-x, -y, 0.0f);
        f.run();
        GlStateManager.popMatrix();
    }
}

