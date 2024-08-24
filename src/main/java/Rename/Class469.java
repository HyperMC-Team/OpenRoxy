package Rename;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.nio.ByteBuffer;
import java.util.Arrays;

import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;

public class Class469 {
    private static final int[] colorCode = new int[32];
    private final byte[][] charwidth = new byte[256][];
    private final int[] textures = new int[256];
    private final FontRenderContext context = new FontRenderContext(new AffineTransform(), true, true);
    private final Font font;
    private final float size;
    private final int fontWidth;
    private final int fontHeight;
    private final int textureWidth;
    private final int textureHeight;

    public final float drawCenteredString(String text, float x, float y, int color) {
        return this.drawString(text, x - (float)(this.getStringWidth(text) / 2), y, color);
    }

    public void drawStringDynamic(String text, double x2, double y2) {
        Class229.applyGradientHorizontal((float)x2, (float)y2, this.getStringWidth(text), this.getFontHeight(), 1.0f, Class318.color1.getColor(), Class318.color2.getColor(), () -> {
            GlStateManager.enableAlpha();
            GlStateManager.alphaFunc(516, 0.0f);
            this.drawString(text, (float)x2, (float)y2, -1);
        });
    }

    public void drawStringDynamic(String text, double x2, double y2, int tick1, int tick2) {
        Class229.applyGradientHorizontal((float)x2, (float)y2, this.getStringWidth(text), this.getFontHeight(), 1.0f, Class318.color(tick1), Class318.color(tick2), () -> {
            GlStateManager.enableAlpha();
            GlStateManager.alphaFunc(516, 0.0f);
            this.drawString(text, (float)x2, (float)y2, -1);
        });
    }

    public final void drawCenteredStringWithShadow(String text, float x, float y, int color) {
        this.drawStringWithShadow(text, x - (float)(this.getStringWidth(text) / 2), y, color);
    }

    public Class469(Font font) {
        this.font = font;
        this.size = font.getSize2D();
        Arrays.fill(this.textures, -1);
        Rectangle2D maxBounds = font.getMaxCharBounds(this.context);
        this.fontWidth = (int)Math.ceil(maxBounds.getWidth());
        this.fontHeight = (int)Math.ceil(maxBounds.getHeight());
        if (this.fontWidth > 127 || this.fontHeight > 127) {
            throw new IllegalArgumentException("Font size to large!");
        }
        this.textureWidth = this.resizeToOpenGLSupportResolution(this.fontWidth * 16);
        this.textureHeight = this.resizeToOpenGLSupportResolution(this.fontHeight * 16);
    }

    public String trimStringToWidth(String p_trimStringToWidth_1_, int p_trimStringToWidth_2_, boolean p_trimStringToWidth_3_) {
        StringBuilder stringbuilder = new StringBuilder();
        int i = 0;
        int j = p_trimStringToWidth_3_ ? p_trimStringToWidth_1_.length() - 1 : 0;
        int k = p_trimStringToWidth_3_ ? -1 : 1;
        boolean flag = false;
        boolean flag1 = false;
        for (int l = j; l >= 0 && l < p_trimStringToWidth_1_.length() && i < p_trimStringToWidth_2_; l += k) {
            char c0 = p_trimStringToWidth_1_.charAt(l);
            int i1 = this.getStringWidth(String.valueOf(c0));
            if (flag) {
                flag = false;
                if (c0 != 'l' && c0 != 'L') {
                    if (c0 == 'r' || c0 == 'R') {
                        flag1 = false;
                    }
                } else {
                    flag1 = true;
                }
            } else if (i1 < 0) {
                flag = true;
            } else {
                i += i1;
                if (flag1) {
                    ++i;
                }
            }
            if (i > p_trimStringToWidth_2_) break;
            if (p_trimStringToWidth_3_) {
                stringbuilder.insert(0, c0);
                continue;
            }
            stringbuilder.append(c0);
        }
        return stringbuilder.toString();
    }

    public final int getHeight() {
        return this.fontHeight / 2;
    }

    public final int getFontHeight() {
        return this.fontHeight / 2;
    }

    protected final int drawChar(char chr, float x, float y) {
        int region = chr >> 8;
        int id = chr & 0xFF;
        int xTexCoord = (id & 0xF) * this.fontWidth;
        int yTexCoord = (id >> 4) * this.fontHeight;
        byte width = this.getOrGenerateCharWidthMap(region)[id];
        GlStateManager.bindTexture(this.getOrGenerateCharTexture(region));
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc(770, 771);
        GL11.glBegin(7);
        GL11.glTexCoord2d(this.wrapTextureCoord(xTexCoord, this.textureWidth), this.wrapTextureCoord(yTexCoord, this.textureHeight));
        GL11.glVertex2f(x, y);
        GL11.glTexCoord2d(this.wrapTextureCoord(xTexCoord, this.textureWidth), this.wrapTextureCoord(yTexCoord + this.fontHeight, this.textureHeight));
        GL11.glVertex2f(x, y + (float)this.fontHeight);
        GL11.glTexCoord2d(this.wrapTextureCoord(xTexCoord + width, this.textureWidth), this.wrapTextureCoord(yTexCoord + this.fontHeight, this.textureHeight));
        GL11.glVertex2f(x + (float)width, y + (float)this.fontHeight);
        GL11.glTexCoord2d(this.wrapTextureCoord(xTexCoord + width, this.textureWidth), this.wrapTextureCoord(yTexCoord, this.textureHeight));
        GL11.glVertex2f(x + (float)width, y);
        GL11.glEnd();
        return width;
    }

    public int drawString(String str, float x, float y, int color) {
        return this.drawString(str, x, y, color, false);
    }

    public final int drawString(String str, float x, float y, int color, boolean darken) {
        str = str.replace("\u25ac", "=");
        y -= 2.0f;
        x *= 2.0f;
        y *= 2.0f;
        y -= 2.0f;
        int offset = 0;
        if (darken) {
            color = (color & 0xFCFCFC) >> 2 | color & 0xFF000000;
        }
        float r = (float)(color >> 16 & 0xFF) / 255.0f;
        float g = (float)(color >> 8 & 0xFF) / 255.0f;
        float b = (float)(color & 0xFF) / 255.0f;
        float a = (float)(color >> 24 & 0xFF) / 255.0f;
        if (a == 0.0f) {
            a = 1.0f;
        }
        GlStateManager.color(r, g, b, a);
        GL11.glPushMatrix();
        GL11.glScaled(0.5, 0.5, 0.5);
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            char chr = chars[i];
            if (chr == '\u00a7' && i != chars.length - 1) {
                if ((color = "0123456789abcdef".indexOf(chars[++i])) == -1) continue;
                if (darken) {
                    color |= 0x10;
                }
                color = colorCode[color];
                r = (float)(color >> 16 & 0xFF) / 255.0f;
                g = (float)(color >> 8 & 0xFF) / 255.0f;
                b = (float)(color & 0xFF) / 255.0f;
                GlStateManager.color(r, g, b, a);
                continue;
            }
            offset += this.drawChar(chr, x + (float)offset, y);
        }
        GL11.glPopMatrix();
        return offset;
    }

    public float getMiddleOfBox(float height) {
        return height / 2.0f - (float)this.getHeight() / 2.0f;
    }

    public final int getStringWidth(String text) {
        if (text == null) {
            return 0;
        }
        int width = 0;
        char[] currentData = text.toCharArray();
        int size = text.length();
        for (int i = 0; i < size; ++i) {
            char chr = currentData[i];
            char character = text.charAt(i);
            if (character == '\u00a7') {
                ++i;
                continue;
            }
            width += this.getOrGenerateCharWidthMap(chr >> 8)[chr & 0xFF];
        }
        return width / 2;
    }

    public final float getSize() {
        return this.size;
    }

    private int generateCharTexture(int id) {
        int textureId = GL11.glGenTextures();
        int offset = id << 8;
        BufferedImage img = new BufferedImage(this.textureWidth, this.textureHeight, 2);
        Graphics2D g = (Graphics2D)img.getGraphics();
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g.setFont(this.font);
        g.setColor(Color.WHITE);
        FontMetrics fontMetrics = g.getFontMetrics();
        for (int y = 0; y < 16; ++y) {
            for (int x = 0; x < 16; ++x) {
                String chr = String.valueOf((char)(y << 4 | x | offset));
                g.drawString(chr, x * this.fontWidth, y * this.fontHeight + fontMetrics.getAscent());
            }
        }
        GL11.glBindTexture(3553, textureId);
        GL11.glTexParameteri(3553, 10241, 9728);
        GL11.glTexParameteri(3553, 10240, 9728);
        GL11.glTexImage2D(3553, 0, 6408, this.textureWidth, this.textureHeight, 0, 6408, 5121, Class469.imageToBuffer(img));
        return textureId;
    }

    private int getOrGenerateCharTexture(int id) {
        if (this.textures[id] == -1) {
            this.textures[id] = this.generateCharTexture(id);
            return this.textures[id];
        }
        return this.textures[id];
    }

    private int resizeToOpenGLSupportResolution(int size) {
        int power = 0;
        while (size > 1 << power) {
            ++power;
        }
        return 1 << power;
    }

    private byte[] generateCharWidthMap(int id) {
        int offset = id << 8;
        byte[] widthmap = new byte[256];
        for (int i = 0; i < widthmap.length; ++i) {
            widthmap[i] = (byte)Math.ceil(this.font.getStringBounds(String.valueOf((char)(i | offset)), this.context).getWidth());
        }
        return widthmap;
    }

    private byte[] getOrGenerateCharWidthMap(int id) {
        if (this.charwidth[id] == null) {
            this.charwidth[id] = this.generateCharWidthMap(id);
            return this.charwidth[id];
        }
        return this.charwidth[id];
    }

    private double wrapTextureCoord(int coord, int size) {
        return (double)coord / (double)size;
    }

    private static ByteBuffer imageToBuffer(BufferedImage img) {
        int[] arr = img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth());
        ByteBuffer buf = ByteBuffer.allocateDirect(4 * arr.length);
        for (int i : arr) {
            buf.putInt(i << 8 | i >> 24 & 0xFF);
        }
        buf.flip();
        return buf;
    }

    public final void finalized() {
        for (int textureId : this.textures) {
            if (textureId == -1) continue;
            GL11.glDeleteTextures(textureId);
        }
    }

    public final void drawStringWithShadow(String newstr, float i, float i1, int rgb) {
        this.drawString(newstr, i + 0.5f, i1 + 0.5f, rgb, true);
        this.drawString(newstr, i, i1, rgb);
    }

    public void drawStringWithShadow(String z, double x, double positionY, int mainTextColor) {
        this.drawStringWithShadow(z, (float)x, (float)positionY, mainTextColor);
    }

    public float drawStringWithShadow(String text, double x, double y, double sWidth, int color) {
        float shadowWidth = this.drawString(text, (float)(x + sWidth), (float)(y + sWidth), color, true);
        return Math.max(shadowWidth, (float)this.drawString(text, (float)x, (float)y, color, false));
    }

    static {
        for (int i = 0; i < 32; ++i) {
            int base = (i >> 3 & 1) * 85;
            int r = (i >> 2 & 1) * 170 + base;
            int g = (i >> 1 & 1) * 170 + base;
            int b = (i & 1) * 170 + base;
            if (i == 6) {
                r += 85;
            }
            if (i >= 16) {
                r /= 4;
                g /= 4;
                b /= 4;
            }
            Class469.colorCode[i] = (r & 0xFF) << 16 | (g & 0xFF) << 8 | b & 0xFF;
        }
    }
}

