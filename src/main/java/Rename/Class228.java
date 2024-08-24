package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import org.lwjgl.opengl.GL11;

public class Class228 {
    private static final Minecraft mc = Minecraft.getMinecraft();
    private static final HashMap<Integer, Integer> shadowCache = new HashMap();

    public static void drawGlow(float x, float y, float width, float height, int blurRadius, Color color) {
        if (!Class228.mc.gameSettings.ofFastRender) {
            GL11.glPushMatrix();
            GlStateManager.alphaFunc(516, 0.01f);
            float _X = (x -= (float)blurRadius) - 0.25f;
            float _Y = (y -= (float)blurRadius) + 0.25f;
            int identifier = (int)((width += (float)(blurRadius * 2)) * (height += (float)(blurRadius * 2)) + width + (float)(color.hashCode() * blurRadius) + (float)blurRadius);
            GL11.glEnable(3553);
            GL11.glDisable(2884);
            GL11.glEnable(3008);
            GlStateManager.enableBlend();
            int texId = -1;
            if (shadowCache.containsKey(identifier)) {
                texId = shadowCache.get(identifier);
                GlStateManager.bindTexture(texId);
            } else {
                if (width <= 0.0f) {
                    width = 1.0f;
                }
                if (height <= 0.0f) {
                    height = 1.0f;
                }
                BufferedImage original = new BufferedImage((int)width, (int)height, 3);
                Graphics g = original.getGraphics();
                g.setColor(color);
                g.fillRect(blurRadius, blurRadius, (int)(width - (float)(blurRadius * 2)), (int)(height - (float)(blurRadius * 2)));
                g.dispose();
                Class227 op = new Class227(blurRadius);
                BufferedImage blurred = op.filter(original, null);
                texId = TextureUtil.uploadTextureImageAllocate(TextureUtil.glGenTextures(), blurred, true, false);
                shadowCache.put(identifier, texId);
            }
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glBegin(7);
            GL11.glTexCoord2f(0.0f, 0.0f);
            GL11.glVertex2f(_X, _Y);
            GL11.glTexCoord2f(0.0f, 1.0f);
            GL11.glVertex2f(_X, _Y + height);
            GL11.glTexCoord2f(1.0f, 1.0f);
            GL11.glVertex2f(_X + width, _Y + height);
            GL11.glTexCoord2f(1.0f, 0.0f);
            GL11.glVertex2f(_X + width, _Y);
            GL11.glEnd();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.resetColor();
            GL11.glEnable(2884);
            GL11.glPopMatrix();
        }
    }

    public static void drawGlow(float x, float y, float width, float height, int blurRadius, Color color, Runnable cutMethod) {
        GL11.glPushMatrix();
        GlStateManager.alphaFunc(516, 0.01f);
        float _X = (x -= (float)blurRadius) - 0.25f;
        float _Y = (y -= (float)blurRadius) + 0.25f;
        int identifier = (int)((width += (float)(blurRadius * 2)) * (height += (float)(blurRadius * 2)) + width + (float)(color.hashCode() * blurRadius) + (float)blurRadius);
        Class232.write(false);
        cutMethod.run();
        Class232.erase(false);
        GL11.glEnable(3553);
        GL11.glDisable(2884);
        GL11.glEnable(3008);
        GlStateManager.enableBlend();
        int texId = -1;
        if (shadowCache.containsKey(identifier)) {
            texId = shadowCache.get(identifier);
            GlStateManager.bindTexture(texId);
        } else {
            if (width <= 0.0f) {
                width = 1.0f;
            }
            if (height <= 0.0f) {
                height = 1.0f;
            }
            BufferedImage original = new BufferedImage((int)width, (int)height, 3);
            Graphics g = original.getGraphics();
            g.setColor(color);
            g.fillRect(blurRadius, blurRadius, (int)(width - (float)(blurRadius * 2)), (int)(height - (float)(blurRadius * 2)));
            g.dispose();
            Class227 op = new Class227(blurRadius);
            BufferedImage blurred = op.filter(original, null);
            texId = TextureUtil.uploadTextureImageAllocate(TextureUtil.glGenTextures(), blurred, true, false);
            shadowCache.put(identifier, texId);
        }
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glBegin(7);
        GL11.glTexCoord2f(0.0f, 0.0f);
        GL11.glVertex2f(_X, _Y);
        GL11.glTexCoord2f(0.0f, 1.0f);
        GL11.glVertex2f(_X, _Y + height);
        GL11.glTexCoord2f(1.0f, 1.0f);
        GL11.glVertex2f(_X + width, _Y + height);
        GL11.glTexCoord2f(1.0f, 0.0f);
        GL11.glVertex2f(_X + width, _Y);
        GL11.glEnd();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
        GlStateManager.resetColor();
        Class232.dispose();
        GL11.glEnable(2884);
        GL11.glPopMatrix();
    }
}

