package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemPickaxe;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

public class Class193
extends ModelPlayer {
    public Class196 bipedRightForeArm;
    public Class196 bipedLeftForeArm;
    public Class196 bipedRightForeLeg;
    public Class196 bipedLeftForeLeg;
    public Class196 bipedRightForeArmwear;
    public Class196 bipedLeftForeArmwear;
    public Class196 bipedRightForeLegwear;
    public Class196 bipedLeftForeLegwear;
    public Class329 renderOffset = new Class329();
    public Class329 renderRotation = new Class329();
    public Class329 renderItemRotation = new Class329();
    public Class202 swordTrail = new Class202();
    public float headRotationX;
    public float headRotationY;
    public float armSwing;
    public float armSwingAmount;
    private final ModelRenderer bipedCape;
    private final ModelRenderer bipedDeadmau5Head;
    private final boolean smallArms;

    public Class193(float p_i46304_1_, boolean p_i46304_2) {
        this(p_i46304_1_, p_i46304_2, true);
    }

    public Class193(float p_i46304_1_, boolean p_i46304_2_, boolean bigTexture) {
        super(p_i46304_1_, p_i46304_2_);
        this.textureWidth = 64;
        this.textureHeight = bigTexture ? 64 : 32;
        this.smallArms = p_i46304_2_;
        this.bipedDeadmau5Head = new Class196(this, 24, 0);
        this.bipedDeadmau5Head.addBox(-3.0f, -6.0f, -1.0f, 6, 6, 1, p_i46304_1_);
        this.bipedCape = new Class196(this, 0, 0);
        this.bipedCape.setTextureSize(64, 32);
        this.bipedCape.addBox(-5.0f, 0.0f, -1.0f, 10, 16, 1, p_i46304_1_);
        this.bipedHeadwear = new Class196(this, 32, 0);
        this.bipedHeadwear.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, p_i46304_1_ + 0.5f);
        this.bipedHeadwear.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bipedBody = new Class196(this, 16, 16);
        this.bipedBody.addBox(-4.0f, -12.0f, -2.0f, 8, 12, 4, p_i46304_1_);
        this.bipedBody.setRotationPoint(0.0f, 12.0f, 0.0f);
        this.bipedHead = new Class196(this, 0, 0).setShowChildIfHidden(true);
        this.bipedHead.addBox(-4.0f, -8.0f, -4.0f, 8, 8, 8, p_i46304_1_);
        this.bipedHead.setRotationPoint(0.0f, -12.0f, 0.0f);
        if (p_i46304_2_) {
            this.bipedLeftArm = new Class197(this, 32, 48).setMother((Class196)this.bipedBody).setShowChildIfHidden(true);
            this.bipedLeftArm.addBox(-1.0f, -2.0f, -2.0f, 3, 6, 4, p_i46304_1_);
            this.bipedLeftArm.setRotationPoint(5.0f, -9.5f, 0.0f);
            this.bipedRightArm = new Class197(this, 40, 16).setMother((Class196)this.bipedBody).setShowChildIfHidden(true);
            this.bipedRightArm.addBox(-2.0f, -2.0f, -2.0f, 3, 6, 4, p_i46304_1_);
            this.bipedRightArm.setRotationPoint(-5.0f, -9.5f, 0.0f);
            this.bipedLeftArmwear = new Class196(this, 48, 48);
            this.bipedLeftArmwear.addBox(-1.0f, -2.0f, -2.0f, 3, 6, 4, p_i46304_1_ + 0.25f);
            ((Class196)this.bipedLeftArmwear).getBox().resY -= 0.25f;
            ((Class196)this.bipedLeftArmwear).getBox().updateVertexPositions(this.bipedLeftArmwear);
            this.bipedLeftArmwear.setRotationPoint(0.0f, 0.0f, 0.0f);
            this.bipedRightArmwear = new Class196(this, 40, 32);
            this.bipedRightArmwear.addBox(-2.0f, -2.0f, -2.0f, 3, 6, 4, p_i46304_1_ + 0.25f);
            ((Class196)this.bipedRightArmwear).getBox().resY -= 0.25f;
            ((Class196)this.bipedRightArmwear).getBox().updateVertexPositions(this.bipedRightArmwear);
            this.bipedRightArmwear.setRotationPoint(0.0f, 0.0f, 0.0f);
            ((Class196)this.bipedRightArm).offsetBox_Add(-0.01f, 0.0f, -0.01f).resizeBox(3.02f, 6.0f, 4.02f).updateVertices();
            ((Class196)this.bipedLeftArm).offsetBox_Add(-0.01f, 0.0f, -0.01f).resizeBox(3.02f, 6.0f, 4.02f).updateVertices();
            this.bipedLeftForeArm = new Class196(this, 32, 54);
            this.bipedLeftForeArm.addBox(-1.0f, 0.0f, -4.0f, 3, 6, 4, p_i46304_1_);
            this.bipedLeftForeArm.setRotationPoint(0.0f, 4.0f, 2.0f);
            this.bipedLeftForeArm.getBox().offsetTextureQuad(this.bipedLeftForeArm, 3, 0.0f, -6.0f);
            this.bipedRightForeArm = new Class196(this, 40, 22);
            this.bipedRightForeArm.addBox(-2.0f, 0.0f, -4.0f, 3, 6, 4, p_i46304_1_);
            this.bipedRightForeArm.setRotationPoint(0.0f, 4.0f, 2.0f);
            this.bipedRightForeArm.getBox().offsetTextureQuad(this.bipedRightForeArm, 3, 0.0f, -6.0f);
            this.bipedLeftForeArmwear = new Class196(this, 48, 54);
            this.bipedLeftForeArmwear.addBox(-1.0f, 0.0f, -4.0f, 3, 6, 4, p_i46304_1_ + 0.25f);
            this.bipedLeftForeArmwear.getBox().resY -= 0.25f;
            this.bipedLeftForeArmwear.getBox().offsetY += 0.25f;
            this.bipedLeftForeArmwear.getBox().updateVertexPositions(this.bipedLeftForeArmwear);
            this.bipedLeftForeArmwear.setRotationPoint(0.0f, 0.0f, 0.0f);
            this.bipedLeftForeArmwear.getBox().offsetTextureQuad(this.bipedLeftForeArmwear, 3, 0.0f, -6.0f);
            this.bipedRightForeArmwear = new Class196(this, 40, 38);
            this.bipedRightForeArmwear.addBox(-2.0f, 0.0f, -4.0f, 3, 6, 4, p_i46304_1_ + 0.25f);
            this.bipedRightForeArmwear.getBox().resY -= 0.25f;
            this.bipedRightForeArmwear.getBox().offsetY += 0.25f;
            this.bipedRightForeArmwear.getBox().updateVertexPositions(this.bipedRightForeArmwear);
            this.bipedRightForeArmwear.setRotationPoint(0.0f, 0.0f, 0.0f);
            this.bipedRightForeArmwear.getBox().offsetTextureQuad(this.bipedRightForeArmwear, 3, 0.0f, -6.0f);
        } else {
            this.bipedLeftArm = new Class197(this, 32, 48).setMother((Class196)this.bipedBody).setShowChildIfHidden(true);
            this.bipedLeftArm.addBox(-1.0f, -2.0f, -2.0f, 4, 6, 4, p_i46304_1_);
            this.bipedLeftArm.setRotationPoint(5.0f, -10.0f, 0.0f);
            this.bipedRightArm = new Class197(this, 40, 16).setMother((Class196)this.bipedBody).setShowChildIfHidden(true);
            this.bipedRightArm.addBox(-3.0f, -2.0f, -2.0f, 4, 6, 4, p_i46304_1_);
            this.bipedRightArm.setRotationPoint(-5.0f, -10.0f, 0.0f);
            this.bipedLeftArmwear = new Class196(this, 48, 48);
            this.bipedLeftArmwear.addBox(-1.0f, -2.0f, -2.0f, 4, 6, 4, p_i46304_1_ + 0.25f);
            ((Class196)this.bipedLeftArmwear).getBox().resY -= 0.25f;
            ((Class196)this.bipedLeftArmwear).getBox().updateVertexPositions(this.bipedLeftArmwear);
            this.bipedLeftArmwear.setRotationPoint(0.0f, 0.0f, 0.0f);
            this.bipedRightArmwear = new Class196(this, 40, 32);
            this.bipedRightArmwear.addBox(-3.0f, -2.0f, -2.0f, 4, 6, 4, p_i46304_1_ + 0.25f);
            this.bipedRightArmwear.setRotationPoint(0.0f, 0.0f, 0.0f);
            ((Class196)this.bipedRightArm).offsetBox_Add(-0.01f, 0.0f, -0.01f).resizeBox(4.02f, 6.0f, 4.02f).updateVertices();
            ((Class196)this.bipedLeftArm).offsetBox_Add(-0.01f, 0.0f, -0.01f).resizeBox(4.02f, 6.0f, 4.02f).updateVertices();
            this.bipedLeftForeArm = new Class196(this, 32, 54);
            this.bipedLeftForeArm.addBox(-1.0f, 0.0f, -4.0f, 4, 6, 4, p_i46304_1_);
            this.bipedLeftForeArm.setRotationPoint(0.0f, 4.0f, 2.0f);
            this.bipedLeftForeArm.getBox().offsetTextureQuad(this.bipedLeftForeArm, 3, 0.0f, -6.0f);
            this.bipedRightForeArm = new Class196(this, 40, 22);
            this.bipedRightForeArm.addBox(-3.0f, 0.0f, -4.0f, 4, 6, 4, p_i46304_1_);
            this.bipedRightForeArm.setRotationPoint(0.0f, 4.0f, 2.0f);
            this.bipedRightForeArm.getBox().offsetTextureQuad(this.bipedRightForeArm, 3, 0.0f, -6.0f);
            this.bipedLeftForeArmwear = new Class196(this, 48, 54);
            this.bipedLeftForeArmwear.addBox(-1.0f, 0.0f, -4.0f, 4, 6, 4, p_i46304_1_ + 0.25f);
            this.bipedLeftForeArmwear.getBox().resY -= 0.25f;
            this.bipedLeftForeArmwear.getBox().offsetY += 0.25f;
            this.bipedLeftForeArmwear.getBox().updateVertexPositions(this.bipedLeftForeArmwear);
            this.bipedLeftForeArmwear.setRotationPoint(0.0f, 0.0f, 0.0f);
            this.bipedLeftForeArmwear.getBox().offsetTextureQuad(this.bipedLeftForeArmwear, 3, 0.0f, -6.0f);
            this.bipedRightForeArmwear = new Class196(this, 40, 38);
            this.bipedRightForeArmwear.addBox(-3.0f, 0.0f, -4.0f, 4, 6, 4, p_i46304_1_ + 0.25f);
            this.bipedRightForeArmwear.setRotationPoint(0.0f, 0.0f, 0.0f);
            this.bipedRightForeArmwear.getBox().offsetTextureQuad(this.bipedRightForeArmwear, 3, 0.0f, -6.0f);
        }
        this.bipedRightLeg = new Class196(this, 0, 16);
        this.bipedRightLeg.addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4, p_i46304_1_);
        this.bipedRightLeg.setRotationPoint(-1.9f, 12.0f, 0.0f);
        this.bipedLeftLeg = new Class196(this, 16, 48);
        this.bipedLeftLeg.addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4, p_i46304_1_);
        this.bipedLeftLeg.setRotationPoint(1.9f, 12.0f, 0.0f);
        this.bipedLeftLegwear = new Class196(this, 0, 48);
        this.bipedLeftLegwear.addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4, p_i46304_1_ + 0.25f);
        ((Class196)this.bipedLeftLegwear).getBox().resY -= 0.25f;
        ((Class196)this.bipedLeftLegwear).getBox().updateVertexPositions(this.bipedLeftLegwear);
        this.bipedLeftLegwear.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bipedRightLegwear = new Class196(this, 0, 32);
        this.bipedRightLegwear.addBox(-2.0f, 0.0f, -2.0f, 4, 6, 4, p_i46304_1_ + 0.25f);
        ((Class196)this.bipedRightLegwear).getBox().resY -= 0.25f;
        ((Class196)this.bipedRightLegwear).getBox().updateVertexPositions(this.bipedRightLegwear);
        this.bipedRightLegwear.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bipedBodyWear = new Class196(this, 16, 32);
        this.bipedBodyWear.addBox(-4.0f, -12.0f, -2.0f, 8, 12, 4, p_i46304_1_ + 0.25f);
        this.bipedBodyWear.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bipedRightForeLeg = new Class196(this, 0, 22);
        this.bipedRightForeLeg.addBox(-2.0f, 0.0f, 0.0f, 4, 6, 4, p_i46304_1_);
        this.bipedRightForeLeg.setRotationPoint(0.0f, 6.0f, -2.0f);
        this.bipedRightForeLeg.getBox().offsetTextureQuad(this.bipedRightForeLeg, 3, 0.0f, -6.0f);
        this.bipedLeftForeLeg = new Class196(this, 16, 54);
        this.bipedLeftForeLeg.addBox(-2.0f, 0.0f, 0.0f, 4, 6, 4, p_i46304_1_);
        this.bipedLeftForeLeg.setRotationPoint(0.0f, 6.0f, -2.0f);
        this.bipedLeftForeLeg.getBox().offsetTextureQuad(this.bipedLeftForeLeg, 3, 0.0f, -6.0f);
        this.bipedRightForeLegwear = new Class196(this, 0, 38);
        this.bipedRightForeLegwear.addBox(-2.0f, 0.0f, 0.0f, 4, 6, 4, p_i46304_1_ + 0.25f);
        this.bipedRightForeLegwear.getBox().resY -= 0.25f;
        this.bipedRightForeLegwear.getBox().offsetY += 0.25f;
        this.bipedRightForeLegwear.getBox().updateVertexPositions(this.bipedRightForeLegwear);
        this.bipedRightForeLegwear.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bipedRightForeLegwear.getBox().offsetTextureQuad(this.bipedRightForeLegwear, 3, 0.0f, -6.0f);
        this.bipedLeftForeLegwear = new Class196(this, 0, 54);
        this.bipedLeftForeLegwear.addBox(-2.0f, 0.0f, 0.0f, 4, 6, 4, p_i46304_1_ + 0.25f);
        this.bipedLeftForeLegwear.getBox().resY -= 0.25f;
        this.bipedLeftForeLegwear.getBox().offsetY += 0.25f;
        this.bipedLeftForeLegwear.getBox().updateVertexPositions(this.bipedLeftForeLegwear);
        this.bipedLeftForeLegwear.setRotationPoint(0.0f, 0.0f, 0.0f);
        this.bipedLeftForeLegwear.getBox().offsetTextureQuad(this.bipedLeftForeLegwear, 3, 0.0f, -6.0f);
        this.bipedBody.addChild(this.bipedRightArm);
        this.bipedBody.addChild(this.bipedLeftArm);
        this.bipedBody.addChild(this.bipedHead);
        this.bipedBody.addChild(this.bipedBodyWear);
        this.bipedHead.addChild(this.bipedHeadwear);
        this.bipedRightArm.addChild(this.bipedRightForeArm);
        this.bipedLeftArm.addChild(this.bipedLeftForeArm);
        this.bipedRightArm.addChild(this.bipedRightArmwear);
        this.bipedLeftArm.addChild(this.bipedLeftArmwear);
        this.bipedRightForeArm.addChild(this.bipedRightForeArmwear);
        this.bipedLeftForeArm.addChild(this.bipedLeftForeArmwear);
        this.bipedRightLeg.addChild(this.bipedRightForeLeg);
        this.bipedLeftLeg.addChild(this.bipedLeftForeLeg);
        this.bipedRightLeg.addChild(this.bipedRightLegwear);
        this.bipedLeftLeg.addChild(this.bipedLeftLegwear);
        this.bipedRightForeLeg.addChild(this.bipedRightForeLegwear);
        this.bipedLeftForeLeg.addChild(this.bipedLeftForeLegwear);
        ((Class197)this.bipedRightArm).setSeperatedPart(this.bipedRightForeArm);
        ((Class197)this.bipedLeftArm).setSeperatedPart(this.bipedLeftForeArm);
        ((Class196)this.bipedRightLeg).offsetBox_Add(-0.01f, 0.0f, -0.01f).resizeBox(4.02f, 6.0f, 4.02f).updateVertices();
        ((Class196)this.bipedLeftLeg).offsetBox_Add(-0.01f, 0.0f, -0.01f).resizeBox(4.02f, 6.0f, 4.02f).updateVertices();
    }

    @Override
    public void render(Entity argEntity, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float p_78088_7_) {
        this.setRotationAngles(p_78088_2_, p_78088_3_, p_78088_4_, p_78088_5_, p_78088_6_, p_78088_7_, argEntity);
        GL11.glPushMatrix();
        if (this.isChild) {
            float f6 = 2.0f;
            GL11.glPushMatrix();
            GL11.glScalef(1.5f / f6, 1.5f / f6, 1.5f / f6);
            GL11.glTranslatef(0.0f, 16.0f * p_78088_7_, 0.0f);
            this.bipedHead.render(p_78088_7_);
            GL11.glPopMatrix();
            GL11.glPushMatrix();
            GL11.glScalef(1.0f / f6, 1.0f / f6, 1.0f / f6);
            GL11.glTranslatef(0.0f, 24.0f * p_78088_7_, 0.0f);
            this.bipedBody.render(p_78088_7_);
            this.bipedRightArm.render(p_78088_7_);
            this.bipedLeftArm.render(p_78088_7_);
            this.bipedRightLeg.render(p_78088_7_);
            this.bipedLeftLeg.render(p_78088_7_);
            this.bipedHeadwear.render(p_78088_7_);
            GL11.glPopMatrix();
        } else {
            this.bipedBody.render(p_78088_7_);
            this.bipedRightLeg.render(p_78088_7_);
            this.bipedLeftLeg.render(p_78088_7_);
        }
        GL11.glPopMatrix();
    }

    @Override
    public void setRotationAngles(float argSwingTime, float argSwingAmount, float argArmSway, float argHeadY, float argHeadX, float argNr6, Entity argEntity) {
        if (Minecraft.getMinecraft().theWorld == null) {
            return;
        }
        if (Minecraft.getMinecraft().theWorld.isRemote && Minecraft.getMinecraft().isGamePaused()) {
            return;
        }
        Class203 data = (Class203) Class203.get(argEntity.getEntityId());
        this.armSwing = argSwingTime;
        this.armSwingAmount = argSwingAmount;
        this.headRotationX = argHeadX;
        this.headRotationY = argHeadY;
        if (Minecraft.getMinecraft().currentScreen != null) {
            this.headRotationY = 0.0f;
        }
        ((Class196)this.bipedHead).sync(data.head);
        ((Class196)this.bipedHeadwear).sync(data.headwear);
        ((Class196)this.bipedBody).sync(data.body);
        ((Class196)this.bipedRightArm).sync(data.rightArm);
        ((Class196)this.bipedLeftArm).sync(data.leftArm);
        ((Class196)this.bipedRightLeg).sync(data.rightLeg);
        ((Class196)this.bipedLeftLeg).sync(data.leftLeg);
        this.bipedRightForeArm.sync(data.rightForeArm);
        this.bipedLeftForeArm.sync(data.leftForeArm);
        this.bipedRightForeLeg.sync(data.rightForeLeg);
        this.bipedLeftForeLeg.sync(data.leftForeLeg);
        this.renderOffset.set(data.renderOffset);
        this.renderRotation.set(data.renderRotation);
        this.renderItemRotation.set(data.renderItemRotation);
        this.swordTrail = data.swordTrail;
        if (Class203.get(argEntity.getEntityId()).canBeUpdated()) {
            this.renderOffset.setSmooth(new Vector3f(0.0f, -1.0f, 0.0f), 0.5f);
            this.renderRotation.setSmooth(new Vector3f(0.0f, 0.0f, 0.0f), 0.5f);
            this.renderItemRotation.setSmooth(new Vector3f(0.0f, 0.0f, 0.0f), 0.5f);
            ((Class196)this.bipedHead).resetScale();
            ((Class196)this.bipedHeadwear).resetScale();
            ((Class196)this.bipedBody).resetScale();
            ((Class196)this.bipedRightArm).resetScale();
            ((Class196)this.bipedLeftArm).resetScale();
            ((Class196)this.bipedRightLeg).resetScale();
            ((Class196)this.bipedLeftLeg).resetScale();
            this.bipedRightForeArm.resetScale();
            this.bipedLeftForeArm.resetScale();
            this.bipedRightForeLeg.resetScale();
            this.bipedLeftForeLeg.resetScale();
            Class208.tempData = Class203.get(argEntity.getEntityId());
            if (argEntity.isRiding()) {
                Class209.getByEntity(argEntity).get("riding").animate((EntityLivingBase)argEntity, this, Class203.get(argEntity.getEntityId()));
                Class206.animate(this, "player", "riding");
            } else if (argEntity.isInWater()) {
                Class209.getByEntity(argEntity).get("swimming").animate((EntityLivingBase)argEntity, this, Class203.get(argEntity.getEntityId()));
                Class206.animate(this, "player", "swimming");
            } else if (!Class203.get(argEntity.getEntityId()).isOnGround() | Class203.get(argEntity.getEntityId()).ticksAfterTouchdown < 2.0f) {
                Class209.getByEntity(argEntity).get("jump").animate((EntityLivingBase)argEntity, this, Class203.get(argEntity.getEntityId()));
                Class206.animate(this, "player", "jump");
            } else {
                if (Class203.get(argEntity.getEntityId()).motion.x == 0.0f & Class203.get(argEntity.getEntityId()).motion.z == 0.0f) {
                    Class209.getByEntity(argEntity).get("stand").animate((EntityLivingBase)argEntity, this, Class203.get(argEntity.getEntityId()));
                    Class206.animate(this, "player", "stand");
                } else if (argEntity.isSprinting()) {
                    Class209.getByEntity(argEntity).get("sprint").animate((EntityLivingBase)argEntity, this, Class203.get(argEntity.getEntityId()));
                    Class206.animate(this, "player", "sprint");
                } else {
                    Class209.getByEntity(argEntity).get("walk").animate((EntityLivingBase)argEntity, this, Class203.get(argEntity.getEntityId()));
                    Class206.animate(this, "player", "walk");
                }
                if (argEntity.isSneaking()) {
                    Class209.getByEntity(argEntity).get("sneak").animate((EntityLivingBase)argEntity, this, Class203.get(argEntity.getEntityId()));
                    Class206.animate(this, "player", "sneak");
                }
            }
            if (this.aimedBow) {
                Class209.getByEntity(argEntity).get("bow").animate((EntityLivingBase)argEntity, this, Class203.get(argEntity.getEntityId()));
                Class206.animate(this, "player", "bow");
            } else if (((EntityPlayer)argEntity).getCurrentEquippedItem() != null && ((EntityPlayer)argEntity).getCurrentEquippedItem().getItem() instanceof ItemPickaxe || ((EntityPlayer)argEntity).getCurrentEquippedItem() != null && Block.getBlockFromItem(((EntityPlayer)argEntity).getCurrentEquippedItem().getItem()) != null) {
                Class209.getByEntity(argEntity).get("mining").animate((EntityLivingBase)argEntity, this, Class203.get(argEntity.getEntityId()));
                Class206.animate(this, "player", "mining");
            } else if (((EntityPlayer)argEntity).getCurrentEquippedItem() != null && ((EntityPlayer)argEntity).getCurrentEquippedItem().getItem() instanceof ItemAxe) {
                Class209.getByEntity(argEntity).get("axe").animate((EntityLivingBase)argEntity, this, Class203.get(argEntity.getEntityId()));
                Class206.animate(this, "player", "axe");
            } else {
                Class209.getByEntity(argEntity).get("attack").animate((EntityLivingBase)argEntity, this, Class203.get(argEntity.getEntityId()));
            }
            ((Class196)this.bipedHead).update(data.ticksPerFrame);
            ((Class196)this.bipedHeadwear).update(data.ticksPerFrame);
            ((Class196)this.bipedBody).update(data.ticksPerFrame);
            ((Class196)this.bipedLeftArm).update(data.ticksPerFrame);
            ((Class196)this.bipedRightArm).update(data.ticksPerFrame);
            ((Class196)this.bipedLeftLeg).update(data.ticksPerFrame);
            ((Class196)this.bipedRightLeg).update(data.ticksPerFrame);
            this.bipedLeftForeArm.update(data.ticksPerFrame);
            this.bipedRightForeArm.update(data.ticksPerFrame);
            this.bipedLeftForeLeg.update(data.ticksPerFrame);
            this.bipedRightForeLeg.update(data.ticksPerFrame);
            this.renderOffset.update(data.ticksPerFrame);
            this.renderRotation.update(data.ticksPerFrame);
            this.renderItemRotation.update(data.ticksPerFrame);
            this.swordTrail.update(data.ticksPerFrame);
            data.updatedThisFrame = true;
        }
        ((Class203) Class203.get(argEntity.getEntityId())).syncModelInfo(this);
    }

    public void postRender(float argScale) {
        GlStateManager.translate(this.renderOffset.vSmooth.x * argScale, -this.renderOffset.vSmooth.y * argScale, this.renderOffset.vSmooth.z * argScale);
        GlStateManager.rotate(this.renderRotation.getX(), 1.0f, 0.0f, 0.0f);
        GlStateManager.rotate(this.renderRotation.getY(), 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(this.renderRotation.getZ(), 0.0f, 0.0f, 1.0f);
    }

    public void postRenderTranslate(float argScale) {
        GlStateManager.translate(this.renderOffset.vSmooth.x * argScale, -this.renderOffset.vSmooth.y * argScale, this.renderOffset.vSmooth.z * argScale);
    }

    public void postRenderRotate(float argScale) {
        GlStateManager.rotate(this.renderRotation.getX(), 1.0f, 0.0f, 0.0f);
        GlStateManager.rotate(this.renderRotation.getY(), 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(this.renderRotation.getZ(), 0.0f, 0.0f, 1.0f);
    }

    @Override
    public void renderRightArm() {
        this.bipedRightArm.render(0.0625f);
        this.bipedRightArmwear.render(0.0625f);
    }

    @Override
    public void renderLeftArm() {
        this.bipedLeftArm.render(0.0625f);
        this.bipedLeftArmwear.render(0.0625f);
    }

    @Override
    public void setInvisible(boolean invisible) {
        super.setInvisible(invisible);
        this.bipedLeftArmwear.showModel = invisible;
        this.bipedRightArmwear.showModel = invisible;
        this.bipedLeftLegwear.showModel = invisible;
        this.bipedRightLegwear.showModel = invisible;
        this.bipedBodyWear.showModel = invisible;
        this.bipedCape.showModel = invisible;
        this.bipedDeadmau5Head.showModel = invisible;
    }

    @Override
    public void postRenderArm(float scale) {
        if (this.smallArms) {
            this.bipedRightArm.rotationPointX += 1.0f;
            this.bipedRightArm.postRender(scale);
            this.bipedRightArm.rotationPointX -= 1.0f;
        } else {
            this.bipedRightArm.postRender(scale);
        }
    }

    public void updateWithEntityData(AbstractClientPlayer argPlayer) {
        Class203 data = (Class203) Class203.get(argPlayer.getEntityId());
        if (data != null) {
            this.renderOffset.set(data.renderOffset);
            this.renderRotation.set(data.renderRotation);
            this.renderItemRotation.set(data.renderItemRotation);
        }
    }
}

