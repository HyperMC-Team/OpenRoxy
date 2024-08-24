package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.awt.Color;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ConcurrentModificationException;
import java.util.List;
import javax.vecmath.Vector2f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector4d;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

public class Class147
        implements Class115 {
    public static double ticks = 0.0;
    public static long lastFrame = 0L;

    public static void drawTargetCapsule(Entity entity, double rad, boolean shade, Color color) {
        GL11.glPushMatrix();
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glEnable(2832);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
        GL11.glHint(3153, 4354);
        GL11.glDepthMask(false);
        GlStateManager.alphaFunc(516, 0.0f);
        if (shade) {
            GL11.glShadeModel(7425);
        }
        GlStateManager.disableCull();
        GL11.glBegin(5);
        Minecraft mc = Minecraft.getMinecraft();
        double x = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * (double)mc.timer.renderPartialTicks - RenderManager.renderPosX;
        double y = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * (double)mc.timer.renderPartialTicks - RenderManager.renderPosY + Math.sin((double)System.currentTimeMillis() / 200.0) + 1.0;
        double z = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * (double)mc.timer.renderPartialTicks - RenderManager.renderPosZ;
        float i = 0.0f;
        while ((double)i < Math.PI * 2) {
            double vecX = x + rad * Math.cos(i);
            double vecZ = z + rad * Math.sin(i);
            Color c = color;
            if (shade) {
                GL11.glColor4f((float)c.getRed() / 255.0f, (float)c.getGreen() / 255.0f, (float)c.getBlue() / 255.0f, 0.0f);
                GL11.glVertex3d(vecX, y - Math.cos((double)System.currentTimeMillis() / 200.0) / 2.0, vecZ);
                GL11.glColor4f((float)c.getRed() / 255.0f, (float)c.getGreen() / 255.0f, (float)c.getBlue() / 255.0f, (float)c.getAlpha() / 255.0f);
            }
            GL11.glVertex3d(vecX, y, vecZ);
            i += 0.09817477f;
        }
        GL11.glEnd();
        if (shade) {
            GL11.glShadeModel(7424);
        }
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GlStateManager.alphaFunc(516, 0.1f);
        GlStateManager.enableCull();
        GL11.glDisable(2848);
        GL11.glDisable(2848);
        GL11.glEnable(2832);
        GL11.glEnable(3553);
        GL11.glPopMatrix();
        GL11.glColor3f(255.0f, 255.0f, 255.0f);
    }

    public static int hexColor(int red, int green, int blue) {
        return Class147.hexColor(red, green, blue, 255);
    }

    public static int hexColor(int red, int green, int blue, int alpha) {
        return alpha << 24 | red << 16 | green << 8 | blue;
    }

    public static void drawPlayerHead(ResourceLocation skin, int x2, int y2, int width, int height) {
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        mc.getTextureManager().bindTexture(skin);
        Gui.drawScaledCustomSizeModalRect(x2, y2, 8.0f, 8.0f, 8, 8, width, height, 64.0f, 64.0f);
    }

    public static void renderBreadCrumbs(List<Vec3> vec3s, Color color, Color color2) {
        Color c1 = Class472.interpolateColorsBackAndForth(50, 3, color, color2, false);
        Color c2 = Class472.interpolateColorsBackAndForth(50, 3, color2, color, false);
        GlStateManager.disableDepth();
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glEnable(2848);
        GL11.glBlendFunc(770, 771);
        int i = 0;
        try {
            for (Vec3 v : vec3s) {
                ++i;
                boolean draw = true;
                double d = v.xCoord;
                mc.getRenderManager();
                double x = d - RenderManager.getRenderPosX();
                double d2 = v.yCoord;
                mc.getRenderManager();
                double y = d2 - RenderManager.getRenderPosY();
                double d3 = v.zCoord;
                mc.getRenderManager();
                double z = d3 - RenderManager.getRenderPosZ();
                double distanceFromPlayer = Class147.mc.thePlayer.getDistance(v.xCoord, v.yCoord - 1.0, v.zCoord);
                int quality = (int)(distanceFromPlayer * 4.0 + 10.0);
                if (quality > 350) {
                    quality = 350;
                }
                if (i % 10 != 0 && distanceFromPlayer > 25.0) {
                    draw = false;
                }
                if (i % 3 == 0 && distanceFromPlayer > 15.0) {
                    draw = false;
                }
                if (!draw) continue;
                GL11.glPushMatrix();
                GL11.glTranslated(x, y, z);
                float scale = 0.04f;
                GL11.glScalef(-0.04f, -0.04f, -0.04f);
                GL11.glRotated(-Class147.mc.getRenderManager().playerViewY, 0.0, 1.0, 0.0);
                GL11.glRotated(Class147.mc.getRenderManager().playerViewX, 1.0, 0.0, 0.0);
                Class147.drawFilledCircle(0, 0, 0.7, c1.hashCode(), quality);
                if (distanceFromPlayer < 4.0) {
                    Class147.drawFilledCircle(0, 0, 1.4, new Color(c1.getRed(), c1.getGreen(), c1.getBlue(), 50).hashCode(), quality);
                }
                if (distanceFromPlayer < 20.0) {
                    Class147.drawFilledCircle(0, 0, 2.3, new Color(c2.getRed(), c2.getGreen(), c2.getBlue(), 30).hashCode(), quality);
                }
                GL11.glScalef(0.8f, 0.8f, 0.8f);
                GL11.glPopMatrix();
            }
        }
        catch (ConcurrentModificationException concurrentModificationException) {
            // empty catch block
        }
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glDisable(3042);
        GlStateManager.enableDepth();
        GL11.glColor3d(255.0, 255.0, 255.0);
    }

    public static void drawTargetESP2D(float x, float y, Color color, Color color2, Color color3, Color color4, float scale, int index) {
        long millis = System.currentTimeMillis() + (long)index * 400L;
        double angle = MathHelper.clamp_double((Math.sin((double)millis / 150.0) + 1.0) / 2.0 * 30.0, 0.0, 30.0);
        double scaled = MathHelper.clamp_double((Math.sin((double)millis / 500.0) + 1.0) / 2.0, 0.8, 1.0);
        double rotate = MathHelper.clamp_double((Math.sin((double)millis / 1000.0) + 1.0) / 2.0 * 360.0, 0.0, 360.0);
        float size = 128.0f * scale * (float)scaled;
        float x2 = (x -= size / 2.0f) + size;
        float y2 = (y -= size / 2.0f) + size;
        GlStateManager.pushMatrix();
        Class147.customRotatedObject2D(x, y, size, size, (float)(rotate += 45.0 - (angle - 15.0)));
        GL11.glDisable(3008);
        GlStateManager.depthMask(false);
        GlStateManager.enableBlend();
        GlStateManager.shadeModel(7425);
        GlStateManager.tryBlendFuncSeparate(770, 1, 1, 0);
        Class147.drawESPImage(new ResourceLocation("bloodline/rectangle.png"), x, y, x2, y2, color, color2, color3, color4);
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.resetColor();
        GlStateManager.shadeModel(7424);
        GlStateManager.depthMask(true);
        GL11.glEnable(3008);
        GlStateManager.popMatrix();
    }

    private static void drawESPImage(ResourceLocation resource, double x, double y, double x2, double y2, Color c, Color c2, Color c3, Color c4) {
        mc.getTextureManager().bindTexture(resource);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer bufferbuilder = tessellator.getWorldRenderer();
        bufferbuilder.begin(9, DefaultVertexFormats.POSITION_TEX_COLOR);
        bufferbuilder.pos(x, y2, 0.0).tex(0.0, 1.0).color(c.getRed(), c.getGreen(), c.getBlue(), c.getAlpha()).endVertex();
        bufferbuilder.pos(x2, y2, 0.0).tex(1.0, 1.0).color(c2.getRed(), c2.getGreen(), c2.getBlue(), c2.getAlpha()).endVertex();
        bufferbuilder.pos(x2, y, 0.0).tex(1.0, 0.0).color(c3.getRed(), c3.getGreen(), c3.getBlue(), c3.getAlpha()).endVertex();
        bufferbuilder.pos(x, y, 0.0).tex(0.0, 0.0).color(c4.getRed(), c4.getGreen(), c4.getBlue(), c4.getAlpha()).endVertex();
        GlStateManager.shadeModel(7425);
        GlStateManager.depthMask(false);
        tessellator.draw();
        GlStateManager.depthMask(true);
        GlStateManager.shadeModel(7424);
    }

    public static void customRotatedObject2D(float oXpos, float oYpos, float oWidth, float oHeight, float rotate) {
        GL11.glTranslated(oXpos + oWidth / 2.0f, oYpos + oHeight / 2.0f, 0.0);
        GL11.glRotated(rotate, 0.0, 0.0, 1.0);
        GL11.glTranslated(-oXpos - oWidth / 2.0f, -oYpos - oHeight / 2.0f, 0.0);
    }

    public static void drawFilledCircle(int x, int y, double r, int c, int quality) {
        float f = (float)(c >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(c >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(c >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(c & 0xFF) / 255.0f;
        GL11.glColor4f(f1, f2, f3, f);
        GL11.glBegin(6);
        for (int i = 0; i <= 360 / quality; ++i) {
            double x2 = Math.sin((double)(i * quality) * Math.PI / 180.0) * r;
            double y2 = Math.cos((double)(i * quality) * Math.PI / 180.0) * r;
            GL11.glVertex2d((double)x + x2, (double)y + y2);
        }
        GL11.glEnd();
    }

    public static Vector2f targetESPSPos(EntityLivingBase entity) {
        EntityRenderer entityRenderer = Class147.mc.entityRenderer;
        float partialTicks = Class147.mc.timer.renderPartialTicks;
        int scaleFactor = new ScaledResolution(mc).getScaleFactor();
        double x = Class146.interpolate(entity.prevPosX, entity.posX, partialTicks);
        double y = Class146.interpolate(entity.prevPosY, entity.posY, partialTicks);
        double z = Class146.interpolate(entity.prevPosZ, entity.posZ, partialTicks);
        double height = entity.height / (entity.isChild() ? 1.75f : 1.0f) / 2.0f;
        double width = 0.0;
        AxisAlignedBB aabb = new AxisAlignedBB(x - 0.0, y, z - 0.0, x + 0.0, y + height, z + 0.0);
        Vector3d[] vectors = new Vector3d[]{new Vector3d(aabb.minX, aabb.minY, aabb.minZ), new Vector3d(aabb.minX, aabb.maxY, aabb.minZ), new Vector3d(aabb.maxX, aabb.minY, aabb.minZ), new Vector3d(aabb.maxX, aabb.maxY, aabb.minZ), new Vector3d(aabb.minX, aabb.minY, aabb.maxZ), new Vector3d(aabb.minX, aabb.maxY, aabb.maxZ), new Vector3d(aabb.maxX, aabb.minY, aabb.maxZ), new Vector3d(aabb.maxX, aabb.maxY, aabb.maxZ)};
        entityRenderer.setupCameraTransform(partialTicks, 0);
        Vector4d position = null;
        Vector3d[] vecs3 = vectors;
        int vecLength = vectors.length;
        for (int vecI = 0; vecI < vecLength; ++vecI) {
            Vector3d vector = vecs3[vecI];
            vector = Class147.project2D(scaleFactor, vector.x - Class147.mc.getRenderManager().viewerPosX, vector.y - Class147.mc.getRenderManager().viewerPosY, vector.z - Class147.mc.getRenderManager().viewerPosZ);
            if (vector == null || !(vector.z >= 0.0) || !(vector.z < 1.0)) continue;
            if (position == null) {
                position = new Vector4d(vector.x, vector.y, vector.z, 0.0);
            }
            position.x = Math.min(vector.x, position.x);
            position.y = Math.min(vector.y, position.y);
            position.z = Math.max(vector.x, position.z);
            position.w = Math.max(vector.y, position.w);
        }
        entityRenderer.setupOverlayRendering();
        if (position != null) {
            return new Vector2f((float)position.x, (float)position.y);
        }
        return null;
    }

    private static Vector3d project2D(int scaleFactor, double x, double y, double z) {
        IntBuffer viewport = GLAllocation.createDirectIntBuffer(16);
        FloatBuffer modelView = GLAllocation.createDirectFloatBuffer(16);
        FloatBuffer projection = GLAllocation.createDirectFloatBuffer(16);
        FloatBuffer vector = GLAllocation.createDirectFloatBuffer(4);
        GL11.glGetFloat(2982, modelView);
        GL11.glGetFloat(2983, projection);
        GL11.glGetInteger(2978, viewport);
        return GLU.gluProject((float)x, (float)y, (float)z, modelView, projection, viewport, vector) ? new Vector3d(vector.get(0) / (float)scaleFactor, ((float)Display.getHeight() - vector.get(1)) / (float)scaleFactor, vector.get(2)) : null;
    }

    public static void drawImage(ResourceLocation resourceLocation, float x, float y, float imgWidth, float imgHeight) {
        Class466.startBlend();
        mc.getTextureManager().bindTexture(resourceLocation);
        Gui.drawModalRectWithCustomSizedTexture(x, y, 0.0f, 0.0f, imgWidth, imgHeight, imgWidth, imgHeight);
        Class466.endBlend();
    }

    public static void glColor(int color) {
        float f = (float)(color >> 24 & 0xFF) / 255.0f;
        float f1 = (float)(color >> 16 & 0xFF) / 255.0f;
        float f2 = (float)(color >> 8 & 0xFF) / 255.0f;
        float f3 = (float)(color & 0xFF) / 255.0f;
        GL11.glColor4f(f1, f2, f3, f);
    }

    public static Color getColor(int color) {
        int f = color >> 24 & 0xFF;
        int f1 = color >> 16 & 0xFF;
        int f2 = color >> 8 & 0xFF;
        int f3 = color & 0xFF;
        return new Color(f1, f2, f3, f);
    }

    public static void drawAxisAlignedBB(AxisAlignedBB axisAlignedBB, boolean outline, int color) {
        GL11.glBlendFunc(770, 771);
        GL11.glEnable(3042);
        GL11.glLineWidth(2.0f);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        Class147.color(color);
        RenderGlobal.renderCustomBoundingBox(axisAlignedBB, outline, true);
        GlStateManager.resetColor();
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glDisable(3042);
    }

    public static Framebuffer createFrameBuffer(Framebuffer framebuffer) {
        return Class147.createFrameBuffer(framebuffer, false);
    }

    public static Framebuffer createFrameBuffer(Framebuffer framebuffer, boolean depth) {
        if (Class147.needsNewFramebuffer(framebuffer)) {
            if (framebuffer != null) {
                framebuffer.deleteFramebuffer();
            }
            return new Framebuffer(Class147.mc.displayWidth, Class147.mc.displayHeight, depth);
        }
        return framebuffer;
    }

    public static boolean needsNewFramebuffer(Framebuffer framebuffer) {
        return framebuffer == null || framebuffer.framebufferWidth != Class147.mc.displayWidth || framebuffer.framebufferHeight != Class147.mc.displayHeight;
    }

    public static void drawTracerLine(Entity entity, float width, Color color, float alpha) {
        float ticks = Class147.mc.timer.renderPartialTicks;
        GL11.glPushMatrix();
        GL11.glLoadIdentity();
        Class147.mc.entityRenderer.orientCamera(ticks);
        double[] pos = Class145.getInterpolatedPos(entity);
        GL11.glDisable(2929);
        Class466.setup2DRendering();
        double yPos = pos[1] + (double)(entity.height / 2.0f);
        GL11.glEnable(2848);
        GL11.glLineWidth(width);
        GL11.glBegin(3);
        Class147.color(color.getRGB(), alpha);
        GL11.glVertex3d(pos[0], yPos, pos[2]);
        GL11.glVertex3d(0.0, Class147.mc.thePlayer.getEyeHeight(), 0.0);
        GL11.glEnd();
        GL11.glDisable(2848);
        GL11.glEnable(2929);
        Class466.end2DRendering();
        GL11.glPopMatrix();
    }

    public static void drawMicrosoftLogo(float x, float y, float size, float spacing, float alpha) {
        float rectSize = size / 2.0f - spacing;
        int alphaVal = (int)(255.0f * alpha);
        Gui.drawRect2(x, y, rectSize, rectSize, new Color(244, 83, 38, alphaVal).getRGB());
        Gui.drawRect2(x + rectSize + spacing, y, rectSize, rectSize, new Color(130, 188, 6, alphaVal).getRGB());
        Gui.drawRect2(x, y + spacing + rectSize, rectSize, rectSize, new Color(5, 166, 241, alphaVal).getRGB());
        Gui.drawRect2(x + rectSize + spacing, y + spacing + rectSize, rectSize, rectSize, new Color(254, 186, 7, alphaVal).getRGB());
    }

    public static void drawMicrosoftLogo(float x, float y, float size, float spacing) {
        Class147.drawMicrosoftLogo(x, y, size, spacing, 1.0f);
    }

    public static void fixBlendIssues() {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, 0.0f);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
    }

    public static void drawUnfilledCircle(double x, double y, float radius, float lineWidth, int color) {
        Class466.setup2DRendering();
        Class147.color(color);
        GL11.glLineWidth(lineWidth);
        GL11.glEnable(2848);
        GL11.glBegin(2);
        for (int i = 0; i <= 360; ++i) {
            GL11.glVertex2d(x + Math.sin((double)i * 3.141526 / 180.0) * (double)radius, y + Math.cos((double)i * 3.141526 / 180.0) * (double)radius);
        }
        GL11.glEnd();
        GL11.glDisable(2848);
        Class466.end2DRendering();
    }

    public static void drawCircle(Entity entity, float partialTicks, double rad, int color, float alpha) {
        ticks += 0.004 * (double)(System.currentTimeMillis() - lastFrame);
        lastFrame = System.currentTimeMillis();
        GL11.glPushMatrix();
        GL11.glDisable(3553);
        GL11.glEnable(3042);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glShadeModel(7425);
        GlStateManager.disableCull();
        double d = Class146.interpolate(entity.lastTickPosX, entity.posX, Class147.mc.timer.renderPartialTicks);
        mc.getRenderManager();
        double x = d - RenderManager.renderPosX;
        double d2 = Class146.interpolate(entity.lastTickPosY, entity.posY, Class147.mc.timer.renderPartialTicks);
        mc.getRenderManager();
        double y = d2 - RenderManager.renderPosY + Math.sin(ticks) + 1.0;
        double d3 = Class146.interpolate(entity.lastTickPosZ, entity.posZ, Class147.mc.timer.renderPartialTicks);
        mc.getRenderManager();
        double z = d3 - RenderManager.renderPosZ;
        GL11.glBegin(5);
        float i = 0.0f;
        while ((double)i < Math.PI * 2) {
            double vecX = x + rad * Math.cos(i);
            double vecZ = z + rad * Math.sin(i);
            Class147.color(color, 0.0f);
            GL11.glVertex3d(vecX, y - Math.sin(ticks + 1.0) / (double)2.7f, vecZ);
            Class147.color(color, 0.52f * alpha);
            GL11.glVertex3d(vecX, y, vecZ);
            i = (float)((double)i + 0.09817477042468103);
        }
        GL11.glEnd();
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glLineWidth(1.5f);
        GL11.glBegin(3);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        Class147.color(color, 0.5f * alpha);
        for (int i2 = 0; i2 <= 180; ++i2) {
            GL11.glVertex3d(x - Math.sin((float)i2 * MathHelper.PI2 / 90.0f) * rad, y, z + Math.cos((float)i2 * MathHelper.PI2 / 90.0f) * rad);
        }
        GL11.glEnd();
        GL11.glShadeModel(7424);
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        GlStateManager.enableCull();
        GL11.glDisable(2848);
        GL11.glEnable(3553);
        GL11.glPopMatrix();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public static void drawFilledCircleNoGL(int x, int y, double r, int c, int quality) {
        Class147.resetColor();
        Class147.setAlphaLimit(0.0f);
        Class466.setup2DRendering();
        Class147.color(c);
        GL11.glBegin(6);
        for (int i = 0; i <= 360 / quality; ++i) {
            double x2 = Math.sin((double)(i * quality) * Math.PI / 180.0) * r;
            double y2 = Math.cos((double)(i * quality) * Math.PI / 180.0) * r;
            GL11.glVertex2d((double)x + x2, (double)y + y2);
        }
        GL11.glEnd();
        Class466.end2DRendering();
    }

    public static void renderBoundingBox(EntityLivingBase entityLivingBase, Color color, float alpha) {
        AxisAlignedBB bb = Class145.getInterpolatedBoundingBox(entityLivingBase);
        GlStateManager.pushMatrix();
        Class466.setup2DRendering();
        Class466.enableCaps(3042, 2832, 2881, 2848);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glLineWidth(1.0f);
        float actualAlpha = 0.3f * alpha;
        GL11.glColor4f(color.getRed(), color.getGreen(), color.getBlue(), actualAlpha);
        Class147.color(color.getRGB(), actualAlpha);
        RenderGlobal.renderCustomBoundingBox(bb, true, true);
        GL11.glDepthMask(true);
        GL11.glEnable(2929);
        Class466.disableCaps();
        Class466.end2DRendering();
        GlStateManager.popMatrix();
    }

    public static void circleNoSmoothRGB(double x, double y, double radius, int color) {
        radius /= 2.0;
        GL11.glEnable(3042);
        GL11.glDisable(3553);
        GL11.glDisable(2884);
        Class147.color(color);
        GL11.glBegin(6);
        for (double i = 0.0; i <= 360.0; i += 1.0) {
            double angle = i * (Math.PI * 2) / 360.0;
            GL11.glVertex2d(x + radius * Math.cos(angle) + radius, y + radius * Math.sin(angle) + radius);
        }
        GL11.glEnd();
        GL11.glEnable(2884);
        GL11.glEnable(3553);
    }

    public static void drawBorderedRect(float x, float y, float width, float height, float outlineThickness, int rectColor, int outlineColor) {
        Gui.drawRect2(x, y, width, height, rectColor);
        GL11.glEnable(2848);
        Class147.color(outlineColor);
        Class466.setup2DRendering();
        GL11.glLineWidth(outlineThickness);
        float cornerValue = (float)((double)outlineThickness * 0.19);
        GL11.glBegin(1);
        GL11.glVertex2d(x, y - cornerValue);
        GL11.glVertex2d(x, y + height + cornerValue);
        GL11.glVertex2d(x + width, y + height + cornerValue);
        GL11.glVertex2d(x + width, y - cornerValue);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x + width, y);
        GL11.glVertex2d(x, y + height);
        GL11.glVertex2d(x + width, y + height);
        GL11.glEnd();
        Class466.end2DRendering();
        GL11.glDisable(2848);
    }

    public static void renderRoundedRect(float x, float y, float width, float height, float radius, int color) {
        Class147.drawGoodCircle(x + radius, y + radius, radius, color);
        Class147.drawGoodCircle(x + width - radius, y + radius, radius, color);
        Class147.drawGoodCircle(x + radius, y + height - radius, radius, color);
        Class147.drawGoodCircle(x + width - radius, y + height - radius, radius, color);
        Gui.drawRect2(x + radius, y, width - radius * 2.0f, height, color);
        Gui.drawRect2(x, y + radius, width, height - radius * 2.0f, color);
    }

    public static void scaleStart(float x, float y, float scale) {
        GL11.glPushMatrix();
        GL11.glTranslatef(x, y, 0.0f);
        GL11.glScalef(scale, scale, 1.0f);
        GL11.glTranslatef(-x, -y, 0.0f);
    }

    public static void scaleEnd() {
        GL11.glPopMatrix();
    }

    public static void drawGoodCircle(double x, double y, float radius, int color) {
        Class147.color(color);
        Class466.setup2DRendering();
        GL11.glEnable(2832);
        GL11.glHint(3153, 4354);
        GL11.glPointSize(radius * (float)(2 * Class147.mc.gameSettings.guiScale));
        GL11.glBegin(0);
        GL11.glVertex2d(x, y);
        GL11.glEnd();
        Class466.end2DRendering();
    }

    public static void fakeCircleGlow(float posX, float posY, float radius, Color color, float maxAlpha) {
        Class147.setAlphaLimit(0.0f);
        GL11.glShadeModel(7425);
        Class466.setup2DRendering();
        Class147.color(color.getRGB(), maxAlpha);
        GL11.glBegin(6);
        GL11.glVertex2d(posX, posY);
        Class147.color(color.getRGB(), 0.0f);
        for (int i = 0; i <= 100; ++i) {
            double angle = (double)i * 0.06283 + 3.1415;
            double x2 = Math.sin(angle) * (double)radius;
            double y2 = Math.cos(angle) * (double)radius;
            GL11.glVertex2d((double)posX + x2, (double)posY + y2);
        }
        GL11.glEnd();
        Class466.end2DRendering();
        GL11.glShadeModel(7424);
        Class147.setAlphaLimit(1.0f);
    }

    public static double animate(double endPoint, double current, double speed) {
        boolean shouldContinueAnimation;
        boolean bl = shouldContinueAnimation = endPoint > current;
        if (speed < 0.0) {
            speed = 0.0;
        } else if (speed > 1.0) {
            speed = 1.0;
        }
        double dif = Math.max(endPoint, current) - Math.min(endPoint, current);
        double factor = dif * speed;
        return current + (shouldContinueAnimation ? factor : -factor);
    }

    public static void rotateStart(float x, float y, float width, float height, float rotation) {
        GL11.glPushMatrix();
        GL11.glTranslatef(x += width / 2.0f, y += height / 3.0f, 0.0f);
        GL11.glRotatef(rotation, 0.0f, 0.0f, 1.0f);
        GL11.glTranslatef(-x, -y, 0.0f);
    }

    public static void rotateStartReal(float x, float y, float width, float height, float rotation) {
        GL11.glPushMatrix();
        GL11.glTranslatef(x, y, 0.0f);
        GL11.glRotatef(rotation, 0.0f, 0.0f, 1.0f);
        GL11.glTranslatef(-x, -y, 0.0f);
    }

    public static void rotateEnd() {
        GL11.glPopMatrix();
    }

    public static void drawClickGuiArrow(float x, float y, float size, Class139 animation, int color) {
        GL11.glTranslatef(x, y, 0.0f);
        Class147.color(color);
        Class466.setup2DRendering();
        GL11.glBegin(5);
        double interpolation = Class146.interpolate(0.0, (double)size / 2.0, animation.getOutput().floatValue());
        if ((double)animation.getOutput().floatValue() >= 0.48) {
            GL11.glVertex2d(size / 2.0f, Class146.interpolate((double)size / 2.0, 0.0, animation.getOutput().floatValue()));
        }
        GL11.glVertex2d(0.0, interpolation);
        if ((double)animation.getOutput().floatValue() < 0.48) {
            GL11.glVertex2d(size / 2.0f, Class146.interpolate((double)size / 2.0, 0.0, animation.getOutput().floatValue()));
        }
        GL11.glVertex2d(size, interpolation);
        GL11.glEnd();
        Class466.end2DRendering();
        GL11.glTranslatef(-x, -y, 0.0f);
    }

    public static void drawCircleNotSmooth(double x, double y, double radius, int color) {
        radius /= 2.0;
        Class466.setup2DRendering();
        GL11.glDisable(2884);
        Class147.color(color);
        GL11.glBegin(6);
        for (double i = 0.0; i <= 360.0; i += 1.0) {
            double angle = i * 0.01745;
            GL11.glVertex2d(x + radius * Math.cos(angle) + radius, y + radius * Math.sin(angle) + radius);
        }
        GL11.glEnd();
        GL11.glEnable(2884);
        Class466.end2DRendering();
    }

    public static void scissor(double x, double y, double width, double height, Runnable data) {
        GL11.glEnable(3089);
        Class147.scissor(x, y, width, height);
        data.run();
        GL11.glDisable(3089);
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

    public static void scissorStart(double x, double y, double width, double height) {
        GL11.glEnable(3089);
        ScaledResolution sr = new ScaledResolution(mc);
        double scale = sr.getScaleFactor();
        double finalHeight = height * scale;
        double finalY = ((double)sr.getScaledHeight() - y) * scale;
        double finalX = x * scale;
        double finalWidth = width * scale;
        GL11.glScissor((int)finalX, (int)(finalY - finalHeight), (int)finalWidth, (int)finalHeight);
    }

    public static void scissorEnd() {
        GL11.glDisable(3089);
    }

    public static void setAlphaLimit(float limit) {
        GlStateManager.enableAlpha();
        GlStateManager.alphaFunc(516, (float)((double)limit * 0.01));
    }

    public static void color(int color, float alpha) {
        float r = (float)(color >> 16 & 0xFF) / 255.0f;
        float g = (float)(color >> 8 & 0xFF) / 255.0f;
        float b = (float)(color & 0xFF) / 255.0f;
        GlStateManager.color(r, g, b, alpha);
    }

    public static void color(int color) {
        Class147.color(color, (float)(color >> 24 & 0xFF) / 255.0f);
    }

    public static void bindTexture(int texture) {
        GL11.glBindTexture(3553, texture);
    }

    public static void resetColor() {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    }

    public static boolean isHovered(float mouseX, float mouseY, float x, float y, float width, float height) {
        return mouseX >= x && mouseY >= y && mouseX < x + width && mouseY < y + height;
    }

    public static void drawGradientRect(double left, double top, double right, double bottom, int startColor, int endColor) {
        Class466.setup2DRendering();
        GL11.glEnable(2848);
        GL11.glShadeModel(7425);
        GL11.glPushMatrix();
        GL11.glBegin(7);
        Class147.color(startColor);
        GL11.glVertex2d(left, top);
        GL11.glVertex2d(left, bottom);
        Class147.color(endColor);
        GL11.glVertex2d(right, bottom);
        GL11.glVertex2d(right, top);
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glDisable(2848);
        Class466.end2DRendering();
        Class147.resetColor();
    }

    public static void drawGradientRectBordered(double left, double top, double right, double bottom, double width, int startColor, int endColor, int borderStartColor, int borderEndColor) {
        Class147.drawGradientRect(left + width, top + width, right - width, bottom - width, startColor, endColor);
        Class147.drawGradientRect(left + width, top, right - width, top + width, borderStartColor, borderEndColor);
        Class147.drawGradientRect(left, top, left + width, bottom, borderStartColor, borderEndColor);
        Class147.drawGradientRect(right - width, top, right, bottom, borderStartColor, borderEndColor);
        Class147.drawGradientRect(left + width, bottom - width, right - width, bottom, borderStartColor, borderEndColor);
    }

    public static boolean isHovering(float x, float y, float width, float height, int mouseX, int mouseY) {
        return (float)mouseX >= x && (float)mouseY >= y && (float)mouseX < x + width && (float)mouseY < y + height;
    }

    public static void doGlScissor(float x, float y, float windowWidth2, float windowHeight2) {
        int scaleFactor = 1;
        float k = Class147.mc.gameSettings.guiScale;
        if (k == 0.0f) {
            k = 1000.0f;
        }
        while ((float)scaleFactor < k && Class147.mc.displayWidth / (scaleFactor + 1) >= 320 && Class147.mc.displayHeight / (scaleFactor + 1) >= 240) {
            ++scaleFactor;
        }
        GL11.glScissor((int)(x * (float)scaleFactor), (int)((float) Class147.mc.displayHeight - (y + windowHeight2) * (float)scaleFactor), (int)(windowWidth2 * (float)scaleFactor), (int)(windowHeight2 * (float)scaleFactor));
    }
}
