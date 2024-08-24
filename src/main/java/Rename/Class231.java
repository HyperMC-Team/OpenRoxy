package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public final class Class231
implements Class3 {
    public static double delta;

    public static double interpolate(double current, double old, double scale) {
        return old + (current - old) * scale;
    }

    public static float processFPS(float defV) {
        float defF = 1000.0f;
        int limitFPS = Math.abs(Minecraft.getDebugFPS());
        return defV / (limitFPS <= 0 ? 1.0f : (float)limitFPS / 1000.0f);
    }

    public static void drawCircle(float x, float y, float r, float lineWidth, boolean isFull, int color) {
        Class231.drawCircle(x, y, r, 10, lineWidth, 360, isFull, color);
    }

    public static void drawCircle(float cx, float cy, double r, int segments, float lineWidth, int part, boolean isFull, int c) {
        GL11.glScalef(0.5f, 0.5f, 0.5f);
        r *= 2.0;
        cx *= 2.0f;
        cy *= 2.0f;
        float f2 = (float)(c >> 24 & 0xFF) / 255.0f;
        float f3 = (float)(c >> 16 & 0xFF) / 255.0f;
        float f4 = (float)(c >> 8 & 0xFF) / 255.0f;
        float f5 = (float)(c & 0xFF) / 255.0f;
        GL11.glEnable(3042);
        GL11.glLineWidth(lineWidth);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        GL11.glColor4f(f3, f4, f5, f2);
        GL11.glBegin(3);
        for (int i = segments - part; i <= segments; ++i) {
            double x = Math.sin((double)i * Math.PI / 180.0) * r;
            double y = Math.cos((double)i * Math.PI / 180.0) * r;
            GL11.glVertex2d((double)cx + x, (double)cy + y);
            if (!isFull) continue;
            GL11.glVertex2d(cx, cy);
        }
        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GL11.glScalef(2.0f, 2.0f, 2.0f);
    }

    public static Color getBlendColor(double current, double max) {
        long base = Math.round(max / 5.0);
        if (current >= (double)(base * 5L)) {
            return new Color(15, 255, 15);
        }
        if (current >= (double)(base << 2)) {
            return new Color(166, 255, 0);
        }
        if (current >= (double)(base * 3L)) {
            return new Color(255, 191, 0);
        }
        if (current >= (double)(base << 1)) {
            return new Color(255, 89, 0);
        }
        return new Color(255, 0, 0);
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

    public static void setColor(int colorHex) {
        float alpha = (float)(colorHex >> 24 & 0xFF) / 255.0f;
        float red = (float)(colorHex >> 16 & 0xFF) / 255.0f;
        float green = (float)(colorHex >> 8 & 0xFF) / 255.0f;
        float blue = (float)(colorHex & 0xFF) / 255.0f;
        GL11.glColor4f(red, green, blue, alpha);
    }

    private Class231() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

