package Rename;

import com.viaversion.viaversion.util.MathUtil;
import java.awt.Color;
import java.awt.image.BufferedImage;
import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;
import net.optifine.util.MathUtils;

public class Class460 {
    public static Color withAlpha(Color color, int alpha) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), MathUtil.clamp(0, 255, alpha));
    }

    public static Color mixColors(Color color1, Color color2, double percent) {
        double inverse_percent = 1.0 - percent;
        int redPart = (int)((double)color1.getRed() * percent + (double)color2.getRed() * inverse_percent);
        int greenPart = (int)((double)color1.getGreen() * percent + (double)color2.getGreen() * inverse_percent);
        int bluePart = (int)((double)color1.getBlue() * percent + (double)color2.getBlue() * inverse_percent);
        return new Color(redPart, greenPart, bluePart);
    }

    public static Color getBlendColor(double current, double max) {
        long base = Math.round(max / 5.0);
        if (current >= (double)(base * 5L)) {
            return new Color(15, 255, 15);
        }
        if (current >= (double)(base * 4L)) {
            return new Color(165, 255, 0);
        }
        if (current >= (double)(base * 3L)) {
            return new Color(255, 190, 0);
        }
        return current >= (double)(base * 2L) ? new Color(255, 90, 0) : new Color(255, 0, 0);
    }

    public static Color getRandomColor() {
        return new Color(Color.HSBtoRGB((float)Math.random(), (float)(0.5 + Math.random() / 2.0), (float)(0.5 + Math.random() / 2.0)));
    }

    public static int overwriteAlphaComponent(int colour, int alphaComponent) {
        int red = colour >> 16 & 0xFF;
        int green = colour >> 8 & 0xFF;
        int blue = colour & 0xFF;
        return (alphaComponent & 0xFF) << 24 | (red & 0xFF) << 16 | (green & 0xFF) << 8 | blue & 0xFF;
    }

    public static int getColor(int red, int green, int blue) {
        return Class460.getColor(red, green, blue, 255);
    }

    public static int getColor(int red, int green, int blue, int alpha) {
        int color = MathHelper.clamp_int(alpha, 0, 255) << 24;
        color |= MathHelper.clamp_int(red, 0, 255) << 16;
        color |= MathHelper.clamp_int(green, 0, 255) << 8;
        return color |= MathHelper.clamp_int(blue, 0, 255);
    }

    public static Color tripleColor(int rgbValue) {
        return Class460.tripleColor(rgbValue, 1.0f);
    }

    public static Color tripleColor(int rgbValue, float alpha) {
        alpha = Math.min(1.0f, Math.max(0.0f, alpha));
        return new Color(rgbValue, rgbValue, rgbValue, (int)(255.0f * alpha));
    }

    public static int removeAlphaComponent(int colour) {
        int red = colour >> 16 & 0xFF;
        int green = colour >> 8 & 0xFF;
        int blue = colour & 0xFF;
        return (red & 0xFF) << 16 | (green & 0xFF) << 8 | blue & 0xFF;
    }

    public static Color getRainbow() {
        return new Color(Color.HSBtoRGB((float)((double)Minecraft.getMinecraft().thePlayer.ticksExisted / 50.0 + Math.sin(0.032)) % 1.0f, 0.5f, 1.0f));
    }

    public static Color[] getAnalogousColor(Color color) {
        Color[] colors = new Color[2];
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        float degree = 0.083333336f;
        float newHueAdded = hsb[0] + degree;
        colors[0] = new Color(Color.HSBtoRGB(newHueAdded, hsb[1], hsb[2]));
        float newHueSubtracted = hsb[0] - degree;
        colors[1] = new Color(Color.HSBtoRGB(newHueSubtracted, hsb[1], hsb[2]));
        return colors;
    }


    public static float hueToRGB(float p, float q, float t) {
        float newT = t;
        if (t < 0.0f) {
            newT = t + 1.0f;
        }
        if (newT > 1.0f) {
            newT -= 1.0f;
        }
        if (newT < 0.16666667f) {
            return p + (q - p) * 6.0f * newT;
        }
        if (newT < 0.5f) {
            return q;
        }
        return newT < 0.6666667f ? p + (q - p) * (0.6666667f - newT) * 6.0f : p;
    }

    public static float[] rgbToHSL(Color rgb) {
        float red = (float)rgb.getRed() / 255.0f;
        float green = (float)rgb.getGreen() / 255.0f;
        float blue = (float)rgb.getBlue() / 255.0f;
        float max = Math.max(Math.max(red, green), blue);
        float min = Math.min(Math.min(red, green), blue);
        float c = (max + min) / 2.0f;
        float[] hsl = new float[]{c, c, c};
        if (max == min) {
            hsl[1] = 0.0f;
            hsl[0] = 0.0f;
        } else {
            float d = max - min;
            float f = hsl[1] = (double)hsl[2] > 0.5 ? d / (2.0f - max - min) : d / (max + min);
            if (max == red) {
                hsl[0] = (green - blue) / d + (float)(green < blue ? 6 : 0);
            } else if (max == blue) {
                hsl[0] = (blue - red) / d + 2.0f;
            } else if (max == green) {
                hsl[0] = (red - green) / d + 4.0f;
            }
            hsl[0] = hsl[0] / 6.0f;
        }
        return hsl;
    }

    public static Color imitateTransparency(Color backgroundColor, Color accentColor, float percentage) {
        return new Color(Class460.interpolateColor(backgroundColor, accentColor, 255.0f * percentage / 255.0f));
    }

    public static int applyOpacity(int color, float opacity) {
        Color old = new Color(color);
        return Class460.applyOpacity(old, opacity).getRGB();
    }

    public static Color applyOpacity(Color color, float opacity) {
        opacity = Math.min(1.0f, Math.max(0.0f, opacity));
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), (int)((float)color.getAlpha() * opacity));
    }

    public static Color darker(Color color, float FACTOR) {
        return new Color(Math.max((int)((float)color.getRed() * FACTOR), 0), Math.max((int)((float)color.getGreen() * FACTOR), 0), Math.max((int)((float)color.getBlue() * FACTOR), 0), color.getAlpha());
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

    public static Color averageColor(BufferedImage bi, int width, int height, int pixelStep) {
        int x;
        int[] color = new int[3];
        for (x = 0; x < width; x += pixelStep) {
            for (int y = 0; y < height; y += pixelStep) {
                Color pixel = new Color(bi.getRGB(x, y));
                color[0] = color[0] + pixel.getRed();
                color[1] = color[1] + pixel.getGreen();
                color[2] = color[2] + pixel.getBlue();
            }
        }
        x = width * height / (pixelStep * pixelStep);
        return new Color(color[0] / x, color[1] / x, color[2] / x);
    }

    public static Color rainbow(int speed, int index, float saturation, float brightness, float opacity) {
        int angle = (int)((System.currentTimeMillis() / (long)speed + (long)index) % 360L);
        float hue = (float)angle / 360.0f;
        Color color = new Color(Color.HSBtoRGB(hue, saturation, brightness));
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), Math.max(0, Math.min(255, (int)(opacity * 255.0f))));
    }

    public static Color rainbow() {
        Color currentColor = new Color(Color.HSBtoRGB((float)(System.nanoTime() + 400000L) / 1.0E10f % 1.0f, 1.0f, 1.0f));
        return new Color((float)currentColor.getRed() / 255.0f, (float)currentColor.getGreen() / 255.0f, (float)currentColor.getBlue() / 255.0f, (float)currentColor.getAlpha() / 255.0f);
    }

    public static Color interpolateColorsBackAndForth(int speed, int index, Color start, Color end, boolean trueColor) {
        int angle = (int)((System.currentTimeMillis() / (long)speed + (long)index) % 360L);
        angle = (angle >= 180 ? 360 - angle : angle) * 2;
        return trueColor ? Class460.interpolateColorHue(start, end, (float)angle / 360.0f) : Class460.interpolateColorC(start, end, (float)angle / 360.0f);
    }

    public static int interpolateColor(Color color1, Color color2, float amount) {
        amount = Math.min(1.0f, Math.max(0.0f, amount));
        return Class460.interpolateColorC(color1, color2, amount).getRGB();
    }

    public static int interpolateColor(int color1, int color2, float amount) {
        amount = Math.min(1.0f, Math.max(0.0f, amount));
        Color cColor1 = new Color(color1);
        Color cColor2 = new Color(color2);
        return Class460.interpolateColorC(cColor1, cColor2, amount).getRGB();
    }

    public static Color interpolateColorC(Color color1, Color color2, float amount) {
        amount = Math.min(1.0f, Math.max(0.0f, amount));
        return new Color(MathUtils.interpolateInt(color1.getRed(), color2.getRed(), amount), MathUtils.interpolateInt(color1.getGreen(), color2.getGreen(), amount), MathUtils.interpolateInt(color1.getBlue(), color2.getBlue(), amount), MathUtils.interpolateInt(color1.getAlpha(), color2.getAlpha(), amount));
    }

    public static Color interpolateColorHue(Color color1, Color color2, float amount) {
        amount = Math.min(1.0f, Math.max(0.0f, amount));
        float[] color1HSB = Color.RGBtoHSB(color1.getRed(), color1.getGreen(), color1.getBlue(), null);
        float[] color2HSB = Color.RGBtoHSB(color2.getRed(), color2.getGreen(), color2.getBlue(), null);
        Color resultColor = Color.getHSBColor(MathUtils.interpolateFloat(color1HSB[0], color2HSB[0], amount), MathUtils.interpolateFloat(color1HSB[1], color2HSB[1], amount), MathUtils.interpolateFloat(color1HSB[2], color2HSB[2], amount));
        return new Color(resultColor.getRed(), resultColor.getGreen(), resultColor.getBlue(), MathUtils.interpolateInt(color1.getAlpha(), color2.getAlpha(), amount));
    }

    public static Color fade(int speed, int index, Color color, float alpha) {
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        int angle = (int)((System.currentTimeMillis() / (long)speed + (long)index) % 360L);
        angle = (angle > 180 ? 360 - angle : angle) + 180;
        Color colorHSB = new Color(Color.HSBtoRGB(hsb[0], hsb[1], (float)angle / 360.0f));
        return new Color(colorHSB.getRed(), colorHSB.getGreen(), colorHSB.getBlue(), Math.max(0, Math.min(255, (int)(alpha * 255.0f))));
    }

    private static float getAnimationEquation(int index, int speed) {
        int angle = (int)((System.currentTimeMillis() / (long)speed + (long)index) % 360L);
        return (float)((angle > 180 ? 360 - angle : angle) + 180) / 360.0f;
    }

    public static int[] createColorArray(int color) {
        return new int[]{Class460.bitChangeColor(color, 16), Class460.bitChangeColor(color, 8), Class460.bitChangeColor(color, 0), Class460.bitChangeColor(color, 24)};
    }

    public static int getOppositeColor(int color) {
        int R = Class460.bitChangeColor(color, 0);
        int G = Class460.bitChangeColor(color, 8);
        int B = Class460.bitChangeColor(color, 16);
        int A = Class460.bitChangeColor(color, 24);
        R = 255 - R;
        G = 255 - G;
        B = 255 - B;
        return R + (G << 8) + (B << 16) + (A << 24);
    }

    private static int bitChangeColor(int color, int bitChange) {
        return color >> bitChange & 0xFF;
    }
}

