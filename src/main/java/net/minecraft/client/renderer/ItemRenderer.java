package net.minecraft.client.renderer;

import Rename.Class1;
import Rename.Class262;
import Rename.Class306;
import Rename.Class241;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Items;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.src.Config;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.MapData;
import net.optifine.DynamicLights;
import net.optifine.reflect.Reflector;
import net.optifine.shaders.Shaders;
import org.lwjgl.opengl.GL11;

public class ItemRenderer {
    private static final ResourceLocation RES_MAP_BACKGROUND = new ResourceLocation("textures/map/map_background.png");
    private static final ResourceLocation RES_UNDERWATER_OVERLAY = new ResourceLocation("textures/misc/underwater.png");
    private final Minecraft mc;
    private ItemStack itemToRender;
    Class241 timer = new Class241();
    private float equippedProgress;
    private float prevEquippedProgress;
    private final RenderManager renderManager;
    private final RenderItem itemRenderer;
    private int equippedItemSlot = -1;

    public ItemRenderer(Minecraft mcIn) {
        this.mc = mcIn;
        this.renderManager = mcIn.getRenderManager();
        this.itemRenderer = mcIn.getRenderItem();
    }

    public void renderItem(EntityLivingBase entityIn, ItemStack heldStack, ItemCameraTransforms.TransformType transform) {
        if (heldStack != null) {
            Item item = heldStack.getItem();
            Block block = Block.getBlockFromItem(item);
            GlStateManager.pushMatrix();
            if (this.itemRenderer.shouldRenderItemIn3D(heldStack)) {
                GlStateManager.scale(2.0f, 2.0f, 2.0f);
                if (!(!this.isBlockTranslucent(block) || Config.isShaders() && Shaders.renderItemKeepDepthMask)) {
                    GlStateManager.depthMask(false);
                }
            }
            this.itemRenderer.renderItemModelForEntity(heldStack, entityIn, transform);
            if (this.isBlockTranslucent(block)) {
                GlStateManager.depthMask(true);
            }
            GlStateManager.popMatrix();
        }
    }

    private boolean isBlockTranslucent(Block blockIn) {
        return blockIn != null && blockIn.getBlockLayer() == EnumWorldBlockLayer.TRANSLUCENT;
    }

    private void rotateArroundXAndY(float angle, float angleY) {
        GlStateManager.pushMatrix();
        GlStateManager.rotate(angle, 1.0f, 0.0f, 0.0f);
        GlStateManager.rotate(angleY, 0.0f, 1.0f, 0.0f);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.popMatrix();
    }

    private void setLightMapFromPlayer(AbstractClientPlayer clientPlayer) {
        int i = this.mc.theWorld.getCombinedLight(new BlockPos(clientPlayer.posX, clientPlayer.posY + (double)clientPlayer.getEyeHeight(), clientPlayer.posZ), 0);
        if (Config.isDynamicLights()) {
            i = DynamicLights.getCombinedLight(this.mc.getRenderViewEntity(), i);
        }
        float f = i & 0xFFFF;
        float f1 = i >> 16;
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, f, f1);
    }

    private void rotateWithPlayerRotations(EntityPlayerSP entityplayerspIn, float partialTicks) {
        float f = entityplayerspIn.prevRenderArmPitch + (entityplayerspIn.renderArmPitch - entityplayerspIn.prevRenderArmPitch) * partialTicks;
        float f1 = entityplayerspIn.prevRenderArmYaw + (entityplayerspIn.renderArmYaw - entityplayerspIn.prevRenderArmYaw) * partialTicks;
        GlStateManager.rotate((entityplayerspIn.rotationPitch - f) * 0.1f, 1.0f, 0.0f, 0.0f);
        GlStateManager.rotate((entityplayerspIn.rotationYaw - f1) * 0.1f, 0.0f, 1.0f, 0.0f);
    }

    private float getMapAngleFromPitch(float pitch) {
        float f = 1.0f - pitch / 45.0f + 0.1f;
        f = MathHelper.clamp_float(f, 0.0f, 1.0f);
        f = -MathHelper.cos(f * (float)Math.PI) * 0.5f + 0.5f;
        return f;
    }

    private void renderRightArm(RenderPlayer renderPlayerIn) {
        GlStateManager.pushMatrix();
        GlStateManager.rotate(54.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(64.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.rotate(-62.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.translate(0.25f, -0.85f, 0.75f);
        renderPlayerIn.renderRightArm(this.mc.thePlayer);
        GlStateManager.popMatrix();
    }

    private void renderLeftArm(RenderPlayer renderPlayerIn) {
        GlStateManager.pushMatrix();
        GlStateManager.rotate(92.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(45.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.rotate(41.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.translate(-0.3f, -1.1f, 0.45f);
        renderPlayerIn.renderLeftArm(this.mc.thePlayer);
        GlStateManager.popMatrix();
    }

    private void renderPlayerArms(AbstractClientPlayer clientPlayer) {
        this.mc.getTextureManager().bindTexture(clientPlayer.getLocationSkin());
        Render render = this.renderManager.getEntityRenderObject(this.mc.thePlayer);
        RenderPlayer renderplayer = (RenderPlayer)render;
        if (!clientPlayer.isInvisible()) {
            GlStateManager.disableCull();
            this.renderRightArm(renderplayer);
            this.renderLeftArm(renderplayer);
            GlStateManager.enableCull();
        }
    }

    private void renderItemMap(AbstractClientPlayer clientPlayer, float pitch, float equipmentProgress, float swingProgress) {
        float f = -0.4f * MathHelper.sin(MathHelper.sqrt_float(swingProgress) * (float)Math.PI);
        float f1 = 0.2f * MathHelper.sin(MathHelper.sqrt_float(swingProgress) * (float)Math.PI * 2.0f);
        float f2 = -0.2f * MathHelper.sin(swingProgress * (float)Math.PI);
        GlStateManager.translate(f, f1, f2);
        float f3 = this.getMapAngleFromPitch(pitch);
        GlStateManager.translate(0.0f, 0.04f, -0.72f);
        GlStateManager.translate(0.0f, equipmentProgress * -1.2f, 0.0f);
        GlStateManager.translate(0.0f, f3 * -0.5f, 0.0f);
        GlStateManager.rotate(90.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(f3 * -85.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.rotate(0.0f, 1.0f, 0.0f, 0.0f);
        this.renderPlayerArms(clientPlayer);
        float f4 = MathHelper.sin(swingProgress * swingProgress * (float)Math.PI);
        float f5 = MathHelper.sin(MathHelper.sqrt_float(swingProgress) * (float)Math.PI);
        GlStateManager.rotate(f4 * -20.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(f5 * -20.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.rotate(f5 * -80.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.scale(0.38f, 0.38f, 0.38f);
        GlStateManager.rotate(90.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(180.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.rotate(0.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.translate(-1.0f, -1.0f, 0.0f);
        GlStateManager.scale(0.015625f, 0.015625f, 0.015625f);
        this.mc.getTextureManager().bindTexture(RES_MAP_BACKGROUND);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GL11.glNormal3f((float)0.0f, (float)0.0f, (float)-1.0f);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos(-7.0, 135.0, 0.0).tex(0.0, 1.0).endVertex();
        worldrenderer.pos(135.0, 135.0, 0.0).tex(1.0, 1.0).endVertex();
        worldrenderer.pos(135.0, -7.0, 0.0).tex(1.0, 0.0).endVertex();
        worldrenderer.pos(-7.0, -7.0, 0.0).tex(0.0, 0.0).endVertex();
        tessellator.draw();
        MapData mapdata = Items.filled_map.getMapData(this.itemToRender, this.mc.theWorld);
        if (mapdata != null) {
            this.mc.entityRenderer.getMapItemRenderer().renderMap(mapdata, false);
        }
    }

    private void renderPlayerArm(AbstractClientPlayer clientPlayer, float equipProgress, float swingProgress) {
        float f = -0.3f * MathHelper.sin(MathHelper.sqrt_float(swingProgress) * (float)Math.PI);
        float f1 = 0.4f * MathHelper.sin(MathHelper.sqrt_float(swingProgress) * (float)Math.PI * 2.0f);
        float f2 = -0.4f * MathHelper.sin(swingProgress * (float)Math.PI);
        GlStateManager.translate(f, f1, f2);
        GlStateManager.translate(0.64000005f, -0.6f, -0.71999997f);
        GlStateManager.translate(0.0f, equipProgress * -0.6f, 0.0f);
        GlStateManager.rotate(45.0f, 0.0f, 1.0f, 0.0f);
        float f3 = MathHelper.sin(swingProgress * swingProgress * (float)Math.PI);
        float f4 = MathHelper.sin(MathHelper.sqrt_float(swingProgress) * (float)Math.PI);
        GlStateManager.rotate(f4 * 70.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(f3 * -20.0f, 0.0f, 0.0f, 1.0f);
        this.mc.getTextureManager().bindTexture(clientPlayer.getLocationSkin());
        GlStateManager.translate(-1.0f, 3.6f, 3.5f);
        GlStateManager.rotate(120.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.rotate(200.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.rotate(-135.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.scale(1.0f, 1.0f, 1.0f);
        GlStateManager.translate(5.6f, 0.0f, 0.0f);
        Render render = this.renderManager.getEntityRenderObject(this.mc.thePlayer);
        GlStateManager.disableCull();
        RenderPlayer renderplayer = (RenderPlayer)render;
        renderplayer.renderRightArm(this.mc.thePlayer);
        GlStateManager.enableCull();
    }

    private void doItemUsedTransformations(float swingProgress) {
        float f = -0.4f * MathHelper.sin(MathHelper.sqrt_float(swingProgress) * (float)Math.PI);
        float f1 = 0.2f * MathHelper.sin(MathHelper.sqrt_float(swingProgress) * (float)Math.PI * 2.0f);
        float f2 = -0.2f * MathHelper.sin(swingProgress * (float)Math.PI);
        GlStateManager.translate(f, f1, f2);
    }

    private void performDrinking(AbstractClientPlayer clientPlayer, float partialTicks) {
        float f = (float)clientPlayer.getItemInUseCount() - partialTicks + 1.0f;
        float f1 = f / (float)this.itemToRender.getMaxItemUseDuration();
        float f2 = MathHelper.abs(MathHelper.cos(f / 4.0f * (float)Math.PI) * 0.1f);
        if (f1 >= 0.8f) {
            f2 = 0.0f;
        }
        GlStateManager.translate(0.0f, f2, 0.0f);
        float f3 = 1.0f - (float)Math.pow(f1, 27.0);
        GlStateManager.translate(f3 * 0.6f, f3 * -0.5f, f3 * 0.0f);
        GlStateManager.rotate(f3 * 90.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(f3 * 10.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.rotate(f3 * 30.0f, 0.0f, 0.0f, 1.0f);
    }

    private void transformFirstPersonItem(float equipProgress, float swingProgress) {
        GlStateManager.translate(0.56f, -0.52f, -0.71999997f);
        GlStateManager.translate(0.0f, equipProgress * -0.6f, 0.0f);
        GlStateManager.rotate(45.0f, 0.0f, 1.0f, 0.0f);
        float f = MathHelper.sin(swingProgress * swingProgress * (float)Math.PI);
        float f1 = MathHelper.sin(MathHelper.sqrt_float(swingProgress) * (float)Math.PI);
        GlStateManager.rotate(f * -20.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(f1 * -20.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.rotate(f1 * -80.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.scale(0.4f, 0.4f, 0.4f);
    }

    private void doBowTransformations(float partialTicks, AbstractClientPlayer clientPlayer) {
        GlStateManager.rotate(-18.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.rotate(-12.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-8.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.translate(-0.9f, 0.2f, 0.0f);
        float f = (float)this.itemToRender.getMaxItemUseDuration() - ((float)clientPlayer.getItemInUseCount() - partialTicks + 1.0f);
        float f1 = f / 20.0f;
        f1 = (f1 * f1 + f1 * 2.0f) / 3.0f;
        if (f1 > 1.0f) {
            f1 = 1.0f;
        }
        if (f1 > 0.1f) {
            float f2 = MathHelper.sin((f - 0.1f) * 1.3f);
            float f3 = f1 - 0.1f;
            float f4 = f2 * f3;
            GlStateManager.translate(f4 * 0.0f, f4 * 0.01f, f4 * 0.0f);
        }
        GlStateManager.translate(f1 * 0.0f, f1 * 0.0f, f1 * 0.1f);
        GlStateManager.scale(1.0f, 1.0f, 1.0f + f1 * 0.2f);
    }

    private void doBlockTransformations() {
        GlStateManager.translate(-0.5f, 0.2f, 0.0f);
        GlStateManager.rotate(30.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-80.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.rotate(60.0f, 0.0f, 1.0f, 0.0f);
    }

    private void doBlockTransformations(float qq) {
        GlStateManager.translate(-0.5f, qq, 0.0f);
        GlStateManager.rotate(30.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-80.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.rotate(60.0f, 0.0f, 1.0f, 0.0f);
    }

    /*
     * Enabled aggressive block sorting
     */
    public void renderItemInFirstPerson(float partialTicks) {
        if (!Config.isShaders() || !Shaders.isSkipRenderHand()) {
            float f = 1.0f - (this.prevEquippedProgress + (this.equippedProgress - this.prevEquippedProgress) * partialTicks);
            EntityPlayerSP abstractclientplayer = this.mc.thePlayer;
            float swingProgress = abstractclientplayer.getSwingProgress(partialTicks);
            float f3 = abstractclientplayer.prevRotationPitch + (abstractclientplayer.rotationPitch - abstractclientplayer.prevRotationPitch) * partialTicks;
            float f4 = abstractclientplayer.prevRotationYaw + (abstractclientplayer.rotationYaw - abstractclientplayer.prevRotationYaw) * partialTicks;
            this.rotateArroundXAndY(f3, f4);
            this.setLightMapFromPlayer(abstractclientplayer);
            this.rotateWithPlayerRotations(abstractclientplayer, partialTicks);
            GlStateManager.enableRescaleNormal();
            GL11.glPushMatrix();
            Class306 animations = Class262.getModule(Class306.class);
            if (this.itemToRender != null) {
                boolean canblock = animations.fake.isEnabled();
                if (this.itemToRender.getItem() instanceof ItemMap) {
                    this.renderItemMap(abstractclientplayer, f3, f, swingProgress);
                } else if (abstractclientplayer.getItemInUseCount() > 0 || canblock) {
                    float sb = MathHelper.sin(swingProgress * swingProgress * (float)Math.PI);
                    EnumAction enumaction = this.itemToRender.getItemUseAction();
                    if (canblock) {
                        enumaction = EnumAction.BLOCK;
                    }
                    switch (enumaction) {
                        case NONE: {
                            this.transformFirstPersonItem(f, swingProgress);
                            break;
                        }
                        case EAT: 
                        case DRINK: {
                            this.performDrinking(abstractclientplayer, partialTicks);
                            this.transformFirstPersonItem(f, swingProgress);
                            break;
                        }
                        case BLOCK: {
                            if (animations.isState()) {
                                if (this.mc.thePlayer.isUsingItem()) {
                                    GL11.glTranslated((double)animations.blockingX.getValue(), (double)animations.blockingY.getValue(), (double)animations.blockingZ.getValue());
                                }
                                GL11.glTranslated((double)animations.x.getValue(), (double)animations.y.getValue(), (double)animations.z.getValue());
                            }
                            if (animations.isState()) {
                                float var15;
                                float v = swingProgress * 0.8f - swingProgress * swingProgress * 0.8f;
                                if (animations.mode.is("Exhibition")) {
                                    float f5 = MathHelper.sin((float)((double)MathHelper.sqrt_float(swingProgress) * 3.1));
                                    GL11.glTranslated((double)0.0, (double)0.0, (double)0.0);
                                    this.transformFirstPersonItem(-0.1f, 1.0f);
                                    GlStateManager.rotate(-f5 * 50.0f / 2.0f, f5 / 2.0f, -0.0f, 4.0f);
                                    GlStateManager.rotate(-f5 * 30.0f, 1.0f, f5 / 2.0f, -0.0f);
                                    this.doBlockTransformations();
                                }
                                if (animations.mode.is("Jello")) {
                                    GlStateManager.translate(0.7, (double)-0.4f, (double)-0.8f);
                                    GlStateManager.rotate(40.0f, -0.1f, 2.0f + v * 5.0f, v * 5.0f);
                                    GlStateManager.scale(0.4, 0.4, 0.4);
                                    this.doBlockTransformations();
                                }
                                if (animations.mode.is("Exhibition2")) {
                                    GlStateManager.translate(0.0f, f * -0.6f, 0.0f);
                                    var15 = MathHelper.sin(MathHelper.sqrt_float(swingProgress) * (float)Math.PI);
                                    this.transformFirstPersonItem(f - 0.2f, swingProgress);
                                    GlStateManager.rotate(var15 * -40.0f, -0.3f, 1.0f, 1.3f);
                                    GlStateManager.rotate(var15 * 100.0f / 2.0f, var15 / 2.0f, -0.0f, 4.0f);
                                    GlStateManager.rotate(var15 * 40.0f, 1.0f, var15 / 2.0f, -0.0f);
                                    GlStateManager.rotate(var15 * -20.7f, 0.3f, 0.1f, 0.4f);
                                    GlStateManager.scale(0.9f, 0.9f, 0.9f);
                                    this.doBlockTransformations();
                                }
                                if (animations.mode.is("External")) {
                                    this.transformFirstPersonItem(f, 0.0f);
                                    this.doBlockTransformations();
                                    var15 = MathHelper.sin(MathHelper.sqrt_float(swingProgress) * (float)Math.PI);
                                    GlStateManager.translate(-0.3f, 0.2f, 0.2f);
                                    GlStateManager.rotate(-var15 * 50.0f, -100.0f, -1000.0f, 10200.0f);
                                    GlStateManager.rotate(-var15 * 20.0f, 1.0f, -0.1f, -1.5f);
                                    float size = 0.75f;
                                    GlStateManager.scale(size, size, size);
                                }
                                if (animations.mode.is("Styles")) {
                                    this.transformFirstPersonItem(f, 0.0f);
                                    GlStateManager.translate(-0.5f, 0.2f, 0.0f);
                                    GlStateManager.rotate(30.0f, 0.0f, 1.0f, 0.0f);
                                    GlStateManager.rotate(-80.0f, 1.0f, 0.0f, 0.0f);
                                    GlStateManager.rotate(60.0f, 0.0f, 1.0f, 0.0f);
                                    float var11 = MathHelper.sin(MathHelper.sqrt_float(swingProgress) * (float)Math.PI);
                                    GlStateManager.translate(-0.05f, 0.2f, 0.0f);
                                    GlStateManager.rotate(-var11 * 70.0f / 2.0f, -8.0f, -0.0f, 9.0f);
                                    GlStateManager.rotate(-var11 * 70.0f, 1.0f, -0.4f, -0.0f);
                                }
                                if (animations.mode.is("Leaked")) {
                                    this.transformFirstPersonItem(f, 0.0f);
                                    double progress = Math.abs(1L - this.timer.hasTimeLeft(300L) / 300L);
                                    double offset = (-Math.pow(progress < 1.0 ? progress - 0.5 : 0.5, 2.0) + 0.25) * 100.0;
                                    GlStateManager.translate(0.0f, 0.0f, 0.0f);
                                    GlStateManager.rotate((float)(offset + 40.0), 0.0f, 1.0f, 0.0f);
                                    GlStateManager.rotate(-92.0f, 1.0f, 0.0f, 0.0f);
                                    GlStateManager.rotate(53.0f, 0.0f, 1.0f + v * 5.0f, v * 5.0f);
                                }
                                if (animations.mode.is("Sigma")) {
                                    this.transformFirstPersonItem(f * 0.5f, 0.0f);
                                    GlStateManager.rotate(-sb * 55.0f / 2.0f, -8.0f, -0.0f, 9.0f);
                                    GlStateManager.rotate(-sb * 45.0f, 1.0f, sb / 2.0f, -0.0f);
                                    this.doBlockTransformations();
                                    GL11.glTranslated((double)1.2, (double)0.3, (double)0.5);
                                    GL11.glTranslatef((float)-1.0f, (float)(this.mc.thePlayer.isSneaking() ? -0.1f : -0.2f), (float)0.2f);
                                }
                                if (!animations.mode.is("1.7")) break;
                                this.transformFirstPersonItem(f, swingProgress);
                                GlStateManager.translate(0.0, 0.3, 0.0);
                                this.doBlockTransformations();
                                break;
                            }
                            this.transformFirstPersonItem(f, 0.0f);
                            this.doBlockTransformations();
                            break;
                        }
                        case BOW: {
                            this.transformFirstPersonItem(f, swingProgress);
                            this.doBowTransformations(partialTicks, abstractclientplayer);
                            break;
                        }
                    }
                } else {
                    this.doItemUsedTransformations(swingProgress);
                    this.transformFirstPersonItem(f, swingProgress);
                }
                if (animations.isState()) {
                    if (this.mc.thePlayer.isUsingItem()) {
                        GL11.glTranslated((double)animations.blockingX.getValue(), (double)animations.blockingY.getValue(), (double)animations.blockingZ.getValue());
                    }
                    GL11.glTranslated((double)animations.x.getValue(), (double)animations.y.getValue(), (double)animations.z.getValue());
                }
                this.renderItem(abstractclientplayer, this.itemToRender, ItemCameraTransforms.TransformType.FIRST_PERSON);
            } else if (!abstractclientplayer.isInvisible()) {
                this.renderPlayerArm(abstractclientplayer, f, swingProgress);
            }
            GL11.glPopMatrix();
            GlStateManager.disableRescaleNormal();
            RenderHelper.disableStandardItemLighting();
        }
    }

    public void renderOverlays(float partialTicks) {
        GlStateManager.disableAlpha();
        if (this.mc.thePlayer.isEntityInsideOpaqueBlock()) {
            IBlockState iblockstate = this.mc.theWorld.getBlockState(new BlockPos(this.mc.thePlayer));
            BlockPos blockpos = new BlockPos(this.mc.thePlayer);
            EntityPlayerSP entityplayer = this.mc.thePlayer;
            for (int i = 0; i < 8; ++i) {
                double d0 = entityplayer.posX + (double)(((float)((i >> 0) % 2) - 0.5f) * entityplayer.width * 0.8f);
                double d1 = entityplayer.posY + (double)(((float)((i >> 1) % 2) - 0.5f) * 0.1f);
                double d2 = entityplayer.posZ + (double)(((float)((i >> 2) % 2) - 0.5f) * entityplayer.width * 0.8f);
                BlockPos blockpos1 = new BlockPos(d0, d1 + (double)entityplayer.getEyeHeight(), d2);
                IBlockState iblockstate1 = this.mc.theWorld.getBlockState(blockpos1);
                if (!iblockstate1.getBlock().isVisuallyOpaque()) continue;
                iblockstate = iblockstate1;
                blockpos = blockpos1;
            }
            if (iblockstate.getBlock().getRenderType() != -1) {
                Object object = Reflector.getFieldValue(Reflector.RenderBlockOverlayEvent_OverlayType_BLOCK);
                if (!Reflector.callBoolean(Reflector.ForgeEventFactory_renderBlockOverlay, this.mc.thePlayer, Float.valueOf(partialTicks), object, iblockstate, blockpos)) {
                    this.renderBlockInHand(partialTicks, this.mc.getBlockRendererDispatcher().getBlockModelShapes().getTexture(iblockstate));
                }
            }
        }
        if (!this.mc.thePlayer.isSpectator()) {
            if (this.mc.thePlayer.isInsideOfMaterial(Material.water) && !Reflector.callBoolean(Reflector.ForgeEventFactory_renderWaterOverlay, this.mc.thePlayer, Float.valueOf(partialTicks))) {
                this.renderWaterOverlayTexture(partialTicks);
            }
            if (this.mc.thePlayer.isBurning() && !Reflector.callBoolean(Reflector.ForgeEventFactory_renderFireOverlay, this.mc.thePlayer, Float.valueOf(partialTicks))) {
                this.renderFireInFirstPerson(partialTicks);
            }
        }
        GlStateManager.enableAlpha();
    }

    private void renderBlockInHand(float partialTicks, TextureAtlasSprite atlas) {
        this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        float f = 0.1f;
        GlStateManager.color(0.1f, 0.1f, 0.1f, 0.5f);
        GlStateManager.pushMatrix();
        float f1 = -1.0f;
        float f2 = 1.0f;
        float f3 = -1.0f;
        float f4 = 1.0f;
        float f5 = -0.5f;
        float f6 = atlas.getMinU();
        float f7 = atlas.getMaxU();
        float f8 = atlas.getMinV();
        float f9 = atlas.getMaxV();
        worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
        worldrenderer.pos(-1.0, -1.0, -0.5).tex(f7, f9).endVertex();
        worldrenderer.pos(1.0, -1.0, -0.5).tex(f6, f9).endVertex();
        worldrenderer.pos(1.0, 1.0, -0.5).tex(f6, f8).endVertex();
        worldrenderer.pos(-1.0, 1.0, -0.5).tex(f7, f8).endVertex();
        tessellator.draw();
        GlStateManager.popMatrix();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
    }

    private void renderWaterOverlayTexture(float partialTicks) {
        if (!Config.isShaders() || Shaders.isUnderwaterOverlay()) {
            this.mc.getTextureManager().bindTexture(RES_UNDERWATER_OVERLAY);
            Tessellator tessellator = Tessellator.getInstance();
            WorldRenderer worldrenderer = tessellator.getWorldRenderer();
            float f = this.mc.thePlayer.getBrightness(partialTicks);
            GlStateManager.color(f, f, f, 0.5f);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.pushMatrix();
            float f1 = 4.0f;
            float f2 = -1.0f;
            float f3 = 1.0f;
            float f4 = -1.0f;
            float f5 = 1.0f;
            float f6 = -0.5f;
            float f7 = -this.mc.thePlayer.rotationYaw / 64.0f;
            float f8 = this.mc.thePlayer.rotationPitch / 64.0f;
            worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
            worldrenderer.pos(-1.0, -1.0, -0.5).tex(4.0f + f7, 4.0f + f8).endVertex();
            worldrenderer.pos(1.0, -1.0, -0.5).tex(0.0f + f7, 4.0f + f8).endVertex();
            worldrenderer.pos(1.0, 1.0, -0.5).tex(0.0f + f7, 0.0f + f8).endVertex();
            worldrenderer.pos(-1.0, 1.0, -0.5).tex(4.0f + f7, 0.0f + f8).endVertex();
            tessellator.draw();
            GlStateManager.popMatrix();
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            GlStateManager.disableBlend();
        }
    }

    private void renderFireInFirstPerson(float partialTicks) {
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 0.9f);
        GlStateManager.depthFunc(519);
        GlStateManager.depthMask(false);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        float f = 1.0f;
        for (int i = 0; i < 2; ++i) {
            GlStateManager.pushMatrix();
            TextureAtlasSprite textureatlassprite = this.mc.getTextureMapBlocks().getAtlasSprite("minecraft:blocks/fire_layer_1");
            this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
            float f1 = textureatlassprite.getMinU();
            float f2 = textureatlassprite.getMaxU();
            float f3 = textureatlassprite.getMinV();
            float f4 = textureatlassprite.getMaxV();
            float f5 = (0.0f - f) / 2.0f;
            float f6 = f5 + f;
            float f7 = 0.0f - f / 2.0f;
            float f8 = f7 + f;
            float f9 = -0.5f;
            GlStateManager.translate((float)(-(i * 2 - 1)) * 0.24f, -0.3f, 0.0f);
            GlStateManager.rotate((float)(i * 2 - 1) * 10.0f, 0.0f, 1.0f, 0.0f);
            worldrenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
            worldrenderer.setSprite(textureatlassprite);
            worldrenderer.pos(f5, f7, f9).tex(f2, f4).endVertex();
            worldrenderer.pos(f6, f7, f9).tex(f1, f4).endVertex();
            worldrenderer.pos(f6, f8, f9).tex(f1, f3).endVertex();
            worldrenderer.pos(f5, f8, f9).tex(f2, f3).endVertex();
            tessellator.draw();
            GlStateManager.popMatrix();
        }
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.disableBlend();
        GlStateManager.depthMask(true);
        GlStateManager.depthFunc(515);
    }

    public void updateEquippedItem() {
        this.prevEquippedProgress = this.equippedProgress;
        EntityPlayerSP entityplayer = this.mc.thePlayer;
        ItemStack itemstack = Class1.instance.Method12().getSpoofedStack();
        boolean flag = false;
        if (this.itemToRender != null && itemstack != null) {
            if (!this.itemToRender.getIsItemStackEqual(itemstack)) {
                boolean flag1;
                if (Reflector.ForgeItem_shouldCauseReequipAnimation.exists() && !(flag1 = Reflector.callBoolean(this.itemToRender.getItem(), Reflector.ForgeItem_shouldCauseReequipAnimation, this.itemToRender, itemstack, this.equippedItemSlot != Class1.instance.Method12().getSpoofedSlot()))) {
                    this.itemToRender = itemstack;
                    this.equippedItemSlot = Class1.instance.Method12().getSpoofedSlot();
                    return;
                }
                flag = true;
            }
        } else {
            flag = this.itemToRender != null || itemstack != null;
        }
        float f2 = 0.4f;
        float f = flag ? 0.0f : 1.0f;
        float f1 = MathHelper.clamp_float(f - this.equippedProgress, -f2, f2);
        this.equippedProgress += f1;
        if (this.equippedProgress < 0.1f) {
            this.itemToRender = itemstack;
            this.equippedItemSlot = Class1.instance.Method12().getSpoofedSlot();
            if (Config.isShaders()) {
                Shaders.setItemToRenderMain(itemstack);
            }
        }
    }

    public void resetEquippedProgress() {
        this.equippedProgress = 0.0f;
    }

    public void resetEquippedProgress2() {
        this.equippedProgress = 0.0f;
    }
}

