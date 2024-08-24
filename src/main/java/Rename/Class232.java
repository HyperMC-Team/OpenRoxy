package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.shader.Framebuffer;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;

public class Class232 {
    private static final Minecraft mc = Minecraft.getMinecraft();

    public static void dispose() {
        GL11.glDisable(2960);
        GlStateManager.disableAlpha();
        GlStateManager.disableBlend();
    }

    public static void erase(boolean invert) {
        GL11.glStencilFunc(invert ? 514 : 517, 1, 65535);
        GL11.glStencilOp(7680, 7680, 7681);
        GlStateManager.colorMask(true, true, true, true);
        GlStateManager.enableAlpha();
        GlStateManager.enableBlend();
        GL11.glAlphaFunc(516, 0.0f);
    }

    public static void write(boolean renderClipLayer) {
        Class232.checkSetupFBO();
        GL11.glClearStencil(0);
        GL11.glClear(1024);
        GL11.glEnable(2960);
        GL11.glStencilFunc(519, 1, 65535);
        GL11.glStencilOp(7680, 7680, 7681);
        if (!renderClipLayer) {
            GlStateManager.colorMask(false, false, false, false);
        }
    }

    public static void write(boolean renderClipLayer, Framebuffer fb) {
        Class232.checkSetupFBO(fb);
        GL11.glClearStencil(0);
        GL11.glClear(1024);
        GL11.glEnable(2960);
        GL11.glStencilFunc(519, 1, 65535);
        GL11.glStencilOp(7680, 7680, 7681);
        if (!renderClipLayer) {
            GlStateManager.colorMask(false, false, false, false);
        }
    }

    public static void checkSetupFBO() {
        Framebuffer fbo = mc.getFramebuffer();
        if (fbo != null && fbo.depthBuffer > -1) {
            Class232.setupFBO(fbo);
            fbo.depthBuffer = -1;
        }
    }

    public static void checkSetupFBO(Framebuffer fbo) {
        if (fbo != null && fbo.depthBuffer > -1) {
            Class232.setupFBO(fbo);
            fbo.depthBuffer = -1;
        }
    }

    public static void setupFBO(Framebuffer framebuffer) {
        EXTFramebufferObject.glDeleteRenderbuffersEXT(framebuffer.depthBuffer);
        int stencilDepthBufferID = EXTFramebufferObject.glGenRenderbuffersEXT();
        EXTFramebufferObject.glBindRenderbufferEXT(36161, stencilDepthBufferID);
        EXTFramebufferObject.glRenderbufferStorageEXT(36161, 34041, Class232.mc.displayWidth, Class232.mc.displayHeight);
        EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36128, 36161, stencilDepthBufferID);
        EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36096, 36161, stencilDepthBufferID);
    }

    public static void initStencilToWrite() {
        mc.getFramebuffer().bindFramebuffer(false);
        Class232.checkSetupFBO(mc.getFramebuffer());
        GL11.glClear(1024);
        GL11.glEnable(2960);
        GL11.glStencilFunc(519, 1, 1);
        GL11.glStencilOp(7681, 7681, 7681);
        GL11.glColorMask(false, false, false, false);
    }

    public static void readStencilBuffer(int ref) {
        GL11.glColorMask(true, true, true, true);
        GL11.glStencilFunc(514, ref, 1);
        GL11.glStencilOp(7680, 7680, 7680);
    }

    public static void uninitStencilBuffer() {
        GL11.glDisable(2960);
    }
}

