package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.EXTFramebufferObject;
import org.lwjgl.opengl.GL11;

public class Class222
implements Class3 {
    private static ShaderGroup blurShader;
    private static Framebuffer buffer;
    private static float lastScale;
    private static int lastScaleWidth;
    private static int lastScaleHeight;
    private static final ResourceLocation shader;
    private static final Class241 updateTimer;

    public static void initFboAndShader() {
        try {
            buffer = new Framebuffer(Class222.mc.displayWidth, Class222.mc.displayHeight, true);
            buffer.setFramebufferColor(0.0f, 0.0f, 0.0f, 0.0f);
            blurShader = new ShaderGroup(mc.getTextureManager(), mc.getResourceManager(), buffer, shader);
            blurShader.createBindFramebuffers(Class222.mc.displayWidth, Class222.mc.displayHeight);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void setShaderConfigs(float intensity, float blurWidth, float blurHeight) {
        blurShader.getShaders().get(0).getShaderManager().getShaderUniform("Radius").set(intensity);
        blurShader.getShaders().get(1).getShaderManager().getShaderUniform("Radius").set(intensity);
        blurShader.getShaders().get(0).getShaderManager().getShaderUniform("BlurDir").set(blurWidth, blurHeight);
        blurShader.getShaders().get(1).getShaderManager().getShaderUniform("BlurDir").set(blurHeight, blurWidth);
    }

    public static void blurArea(float x, float y, float width, float height, boolean setupOverlay) {
        if (!Class222.blurEnabled()) {
            return;
        }
        ScaledResolution scale = new ScaledResolution(mc);
        float factor = scale.getScaleFactor();
        int factor2 = scale.getScaledWidth();
        int factor3 = scale.getScaledHeight();
        if (lastScale != factor || lastScaleWidth != factor2 || lastScaleHeight != factor3 || buffer == null || blurShader == null) {
            Class222.initFboAndShader();
        }
        lastScale = factor;
        lastScaleWidth = factor2;
        lastScaleHeight = factor3;
        GL11.glEnable(3089);
        Class147.doGlScissor(x, y, width, height);
        GL11.glPushMatrix();
        buffer.framebufferRenderExt(Class222.mc.displayWidth, Class222.mc.displayHeight, true);
        GL11.glPopMatrix();
        GL11.glDisable(3089);
        if (setupOverlay) {
            Class222.mc.entityRenderer.setupOverlayRendering();
        }
        GlStateManager.enableDepth();
    }

    public static void blurRoundArea(float x, float y, float width, float height, float roundRadius, boolean setupOverlay) {
        if (!Class222.blurEnabled()) {
            return;
        }
        ScaledResolution scale = new ScaledResolution(mc);
        float factor = scale.getScaleFactor();
        int factor2 = scale.getScaledWidth();
        int factor3 = scale.getScaledHeight();
        if (lastScale != factor || lastScaleWidth != factor2 || lastScaleHeight != factor3 || buffer == null || blurShader == null) {
            Class222.initFboAndShader();
        }
        lastScale = factor;
        lastScaleWidth = factor2;
        lastScaleHeight = factor3;
        GL11.glEnable(3089);
        Class147.doGlScissor(x, y, width, height);
        GL11.glPushMatrix();
        buffer.framebufferRenderExt(Class222.mc.displayWidth, Class222.mc.displayHeight, true);
        GL11.glPopMatrix();
        GL11.glDisable(3089);
        if (setupOverlay) {
            Class222.mc.entityRenderer.setupOverlayRendering();
        }
        GlStateManager.enableDepth();
    }

    public static void updateBlurBuffer(boolean setupOverlay) {
        if (!Class222.blurEnabled()) {
            return;
        }
        if (updateTimer.delay(16.666666f) && blurShader != null) {
            mc.getFramebuffer().unbindFramebuffer();
            Class222.setShaderConfigs(50.0f, 0.0f, 1.0f);
            buffer.bindFramebuffer(true);
            mc.getFramebuffer().framebufferRenderExt(Class222.mc.displayWidth, Class222.mc.displayHeight, true);
            if (OpenGlHelper.shadersSupported) {
                GlStateManager.matrixMode(5890);
                GlStateManager.pushMatrix();
                GlStateManager.loadIdentity();
                blurShader.loadShaderGroup(Class222.mc.timer.renderPartialTicks);
                GlStateManager.popMatrix();
            }
            buffer.unbindFramebuffer();
            mc.getFramebuffer().bindFramebuffer(true);
            if (mc.getFramebuffer() != null && Class222.mc.getFramebuffer().depthBuffer > -1) {
                Class222.setupFBO(mc.getFramebuffer());
                Class222.mc.getFramebuffer().depthBuffer = -1;
            }
            if (setupOverlay) {
                Class222.mc.entityRenderer.setupOverlayRendering();
            }
        }
    }

    public static void setupFBO(Framebuffer fbo) {
        EXTFramebufferObject.glDeleteRenderbuffersEXT(fbo.depthBuffer);
        int stencil_depth_buffer_ID = EXTFramebufferObject.glGenRenderbuffersEXT();
        EXTFramebufferObject.glBindRenderbufferEXT(36161, stencil_depth_buffer_ID);
        EXTFramebufferObject.glRenderbufferStorageEXT(36161, 34041, Class222.mc.displayWidth, Class222.mc.displayHeight);
        EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36128, 36161, stencil_depth_buffer_ID);
        EXTFramebufferObject.glFramebufferRenderbufferEXT(36160, 36096, 36161, stencil_depth_buffer_ID);
    }

    public static boolean isFastRenderEnabled() {
        return Config.isFastRender();
    }

    public static boolean blurEnabled() {
        return !Class222.isFastRenderEnabled();
    }

    static {
        shader = new ResourceLocation("shaders/post/blur.json");
        updateTimer = new Class241();
    }
}

