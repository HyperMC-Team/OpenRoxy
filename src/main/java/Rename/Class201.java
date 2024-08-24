package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class Class201
extends RenderPlayer {
    private final boolean smallArms;

    public Class201(RenderManager renderManager) {
        super(renderManager, false);
        this.smallArms = false;
        this.mainModel = new Class193(0.0f, false);
        this.layerRenderers.clear();
        this.addLayer(new Class200(this));
        this.addLayer(new LayerHeldItem(this));
        this.addLayer(new Class198(this));
        this.addLayer(new Class199((Class193)this.getMainModel()));
    }

    public Class201(RenderManager renderManager, boolean useSmallArms) {
        super(renderManager, useSmallArms);
        this.smallArms = useSmallArms;
        this.mainModel = new Class193(0.0f, useSmallArms);
        this.layerRenderers.clear();
        this.addLayer(new Class200(this));
        this.addLayer(new LayerHeldItem(this));
        this.addLayer(new Class198(this));
        this.addLayer(new Class199((Class193)this.getMainModel()));
    }

    @Override
    public ModelPlayer getMainModel() {
        if (!(this.mainModel instanceof Class193)) {
            this.mainModel = new Class193(0.0f, this.smallArms);
        }
        return (Class193)this.mainModel;
    }

    @Override
    protected void rotateCorpse(AbstractClientPlayer p_77043_1_, float p_77043_2_, float p_77043_3_, float p_77043_4_) {
        super.rotateCorpse(p_77043_1_, p_77043_2_, p_77043_3_, p_77043_4_);
    }

    private void setModelVisibilities(AbstractClientPlayer p_177137_1_) {
        Class193 modelplayer = (Class193)this.getMainModel();
        if (p_177137_1_.isSpectator()) {
            modelplayer.setInvisible(false);
            modelplayer.bipedHead.showModel = true;
            modelplayer.bipedHeadwear.showModel = true;
        } else {
            ItemStack itemstack = p_177137_1_.inventory.getCurrentItem();
            modelplayer.setInvisible(true);
            modelplayer.bipedHeadwear.showModel = p_177137_1_.isWearing(EnumPlayerModelParts.HAT);
            modelplayer.bipedBodyWear.showModel = p_177137_1_.isWearing(EnumPlayerModelParts.JACKET);
            modelplayer.bipedLeftLegwear.showModel = p_177137_1_.isWearing(EnumPlayerModelParts.LEFT_PANTS_LEG);
            modelplayer.bipedRightLegwear.showModel = p_177137_1_.isWearing(EnumPlayerModelParts.RIGHT_PANTS_LEG);
            modelplayer.bipedLeftArmwear.showModel = p_177137_1_.isWearing(EnumPlayerModelParts.LEFT_SLEEVE);
            modelplayer.bipedRightArmwear.showModel = p_177137_1_.isWearing(EnumPlayerModelParts.RIGHT_SLEEVE);
            modelplayer.heldItemLeft = 0;
            modelplayer.aimedBow = false;
            modelplayer.isSneak = p_177137_1_.isSneaking();
            if (itemstack == null) {
                modelplayer.heldItemRight = 0;
            } else {
                modelplayer.heldItemRight = 1;
                if (p_177137_1_.getItemInUseCount() > 0) {
                    EnumAction enumaction = itemstack.getItemUseAction();
                    if (enumaction == EnumAction.BLOCK) {
                        modelplayer.heldItemRight = 3;
                    } else if (enumaction == EnumAction.BOW) {
                        modelplayer.aimedBow = true;
                    }
                }
            }
        }
    }

    @Override
    protected ResourceLocation getEntityTexture(AbstractClientPlayer entity) {
        return entity.getLocationSkin();
    }

    @Override
    protected void preRenderCallback(AbstractClientPlayer p_77041_1_, float p_77041_2_) {
        float f1 = 0.9375f;
        GlStateManager.scale(f1, f1, f1);
        ((Class193)this.getMainModel()).updateWithEntityData(p_77041_1_);
        ((Class193)this.mainModel).postRenderTranslate(0.0625f);
        Class203 data = (Class203) Class203.get(p_77041_1_.getEntityId());
        if (Class262.getModule(Class320.class).swordTrail.isEnabled()) {
            GL11.glPushMatrix();
            float f5 = 0.0625f;
            GL11.glScalef(-f5, -f5, f5);
            data.swordTrail.render();
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glPopMatrix();
        }
        ((Class193)this.getMainModel()).postRenderRotate(0.0625f);
    }

    @Override
    public void renderRightArm(AbstractClientPlayer clientPlayer) {
        float f = 1.0f;
        GlStateManager.color(f, f, f);
        ModelPlayer modelplayer = this.getMainModel();
        this.setModelVisibilities(clientPlayer);
        modelplayer.swingProgress = 0.0f;
        modelplayer.isSneak = false;
        modelplayer.setRotationAngles(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, clientPlayer);
        modelplayer.renderRightArm();
    }

    @Override
    public void renderLeftArm(AbstractClientPlayer clientPlayer) {
        float f = 1.0f;
        GlStateManager.color(f, f, f);
        ModelPlayer modelplayer = this.getMainModel();
        this.setModelVisibilities(clientPlayer);
        modelplayer.isSneak = false;
        modelplayer.swingProgress = 0.0f;
        modelplayer.setRotationAngles(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f, clientPlayer);
        modelplayer.renderLeftArm();
    }

    @Override
    protected void renderLivingAt(AbstractClientPlayer p_77039_1_, double p_77039_2_, double p_77039_4_, double p_77039_6_) {
        super.renderLivingAt(p_77039_1_, p_77039_2_, p_77039_4_, p_77039_6_);
    }
}

