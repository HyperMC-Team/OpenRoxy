package Rename;

import java.awt.Color;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;

public final class Class472 {
    public static int getRGB(int r, int g, int b) {
        return Class472.getRGB(r, g, b, 255);
    }

    public static void resetColor() {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public static Color getRainbow(int speed, int offset, float saturation) {
        float hue = (System.currentTimeMillis() + (long)offset) % (long)speed;
        return Color.getHSBColor(hue / (float)speed, saturation, 1.0f);
    }

    public static Color injectAlpha(Color color, int alpha) {
        alpha = MathHelper.clamp_int(alpha, 0, 255);
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
    }

    public static void glColor(int hex) {
        float alpha = (float)(hex >> 24 & 0xFF) / 255.0f;
        float red = (float)(hex >> 16 & 0xFF) / 255.0f;
        float green = (float)(hex >> 8 & 0xFF) / 255.0f;
        float blue = (float)(hex & 0xFF) / 255.0f;
        GL11.glColor4f(red, green, blue, alpha);
    }

    public static int getColor(float hueOffset, float saturation, float brightness) {
        float speed = 4500.0f;
        float hue = (float)(System.currentTimeMillis() % (long)((int)speed)) / speed;
        return Color.HSBtoRGB(hue - hueOffset / 54.0f, saturation, brightness);
    }

    public static Color interpolateColorC(Color color1, Color color2, float amount) {
        amount = Math.min(1.0f, Math.max(0.0f, amount));
        return new Color(Class171.interpolateInt(color1.getRed(), color2.getRed(), amount), Class171.interpolateInt(color1.getGreen(), color2.getGreen(), amount), Class171.interpolateInt(color1.getBlue(), color2.getBlue(), amount), Class171.interpolateInt(color1.getAlpha(), color2.getAlpha(), amount));
    }

    public static int getRGB(int r, int g, int b, int a) {
        return (a & 0xFF) << 24 | (r & 0xFF) << 16 | (g & 0xFF) << 8 | b & 0xFF;
    }

    public static int[] splitRGB(int rgb) {
        int[] ints = new int[]{rgb >> 16 & 0xFF, rgb >> 8 & 0xFF, rgb & 0xFF};
        return ints;
    }

    public static Color interpolateColorsBackAndForth(int speed, int index, Color start, Color end, boolean trueColor) {
        int angle = (int)((System.currentTimeMillis() / (long)speed + (long)index) % 360L);
        angle = (angle >= 180 ? 360 - angle : angle) * 2;
        return trueColor ? Class472.interpolateColorHue(start, end, (float)angle / 360.0f) : Class472.interpolateColorC(start, end, (float)angle / 360.0f);
    }

    public static Color interpolateColorHue(Color color1, Color color2, float amount) {
        amount = Math.min(1.0f, Math.max(0.0f, amount));
        float[] color1HSB = Color.RGBtoHSB(color1.getRed(), color1.getGreen(), color1.getBlue(), null);
        float[] color2HSB = Color.RGBtoHSB(color2.getRed(), color2.getGreen(), color2.getBlue(), null);
        Color resultColor = Color.getHSBColor(Class171.interpolateFloat(color1HSB[0], color2HSB[0], amount), Class171.interpolateFloat(color1HSB[1], color2HSB[1], amount), Class171.interpolateFloat(color1HSB[2], color2HSB[2], amount));
        return Class472.applyOpacity(resultColor, (float) Class171.interpolateInt(color1.getAlpha(), color2.getAlpha(), amount) / 255.0f);
    }

    public static Color applyOpacity(Color color, float opacity) {
        opacity = Math.min(1.0f, Math.max(0.0f, opacity));
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)((float)color.getAlpha() * opacity));
    }

    public static int interpolateColor(Color color1, Color color2, float amount) {
        amount = Math.min(1.0f, Math.max(0.0f, amount));
        return Class472.interpolateColorC(color1, color2, amount).getRGB();
    }

    public static Color brighter(Color color, float FACTOR) {
        int r = color.getRed();
        int g = color.getGreen();
        int b = color.getBlue();
        int alpha = color.getAlpha();
        int i = (int)(1.0 / (1.0 - (double)FACTOR));
        if (r == 0 && g == 0 && b == 0) {
            return new Color(i, i, i, alpha);
        }
        if (r > 0 && r < i) {
            r = i;
        }
        if (g > 0 && g < i) {
            g = i;
        }
        if (b > 0 && b < i) {
            b = i;
        }
        return new Color(Math.min((int)((float)r / FACTOR), 255), Math.min((int)((float)g / FACTOR), 255), Math.min((int)((float)b / FACTOR), 255), alpha);
    }

    public static int getRGB(int rgb) {
        return 0xFF000000 | rgb;
    }

    public static int reAlpha(int rgb, int alpha) {
        return Class472.getRGB(Class472.getRed(rgb), Class472.getGreen(rgb), Class472.getBlue(rgb), alpha);
    }

    public static int getRed(int rgb) {
        return rgb >> 16 & 0xFF;
    }

    public static int getGreen(int rgb) {
        return rgb >> 8 & 0xFF;
    }

    public static int getBlue(int rgb) {
        return rgb & 0xFF;
    }

    public static int getAlpha(int rgb) {
        return rgb >> 24 & 0xFF;
    }
}

