package net.minecraft.client.renderer.entity;

import java.util.Random;
import Rename.Class262;
import Rename.Class281;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

public class RenderEntityItem
extends Render<EntityItem> {
    private final RenderItem itemRenderer;
    private Random field_177079_e = new Random();

    public RenderEntityItem(RenderManager renderManagerIn, RenderItem p_i46167_2_) {
        super(renderManagerIn);
        this.itemRenderer = p_i46167_2_;
        this.shadowSize = 0.15f;
        this.shadowOpaque = 0.75f;
    }

    private int func_177077_a(EntityItem itemIn, double p_177077_2_, double p_177077_4_, double p_177077_6_, float p_177077_8_, IBakedModel p_177077_9_) {
        Minecraft mc = Minecraft.getMinecraft();
        boolean itemPhysics = Class262.getModule(Class281.class).isState();
        ItemStack itemstack = itemIn.getEntityItem();
        Item item = itemstack.getItem();
        if (item == null) {
            return 0;
        }
        boolean flag = p_177077_9_.isGui3d();
        int i = RenderEntityItem.func_177078_a(itemstack);
        float f = 0.25f;
        float f1 = itemPhysics ? -0.13f : MathHelper.sin(((float)itemIn.getAge() + p_177077_8_) / 10.0f + itemIn.hoverStart) * 0.1f + 0.1f;
        float f2 = p_177077_9_.getItemCameraTransforms().getTransform((ItemCameraTransforms.TransformType)ItemCameraTransforms.TransformType.GROUND).scale.y;
        GlStateManager.translate((float)p_177077_2_, (float)p_177077_4_ + f1 + 0.25f * f2, (float)p_177077_6_);
        if (flag || this.renderManager.options != null) {
            float f3 = (((float)itemIn.getAge() + p_177077_8_) / 20.0f + itemIn.hoverStart) * 57.295776f;
            if (itemPhysics) {
                if (itemIn.onGround) {
                    double var = (itemIn.posX + itemIn.motionX * (double)mc.timer.renderPartialTicks) * 200.0 + (itemIn.posZ + itemIn.motionZ * (double)mc.timer.renderPartialTicks) * 200.0;
                    GlStateManager.rotate((float)var, 0.0f, 1.0f, 0.0f);
                    if (item instanceof ItemBlock && ((ItemBlock)item).getBlock() instanceof BlockTrapDoor) {
                        GlStateManager.rotate(0.0f, 1.0f, 0.0f, 0.0f);
                    } else if (!(item instanceof ItemBlock) || ((ItemBlock)item).getBlock().isPassable(mc.theWorld, mc.thePlayer.getPosition())) {
                        GlStateManager.rotate(90.0f, 1.0f, 0.0f, 0.0f);
                    }
                } else {
                    double x = (itemIn.posX + itemIn.motionX * (double)mc.timer.renderPartialTicks) * 200.0;
                    double y = (itemIn.posY + itemIn.motionY * (double)mc.timer.renderPartialTicks) * 200.0;
                    double z = (itemIn.posZ + itemIn.motionZ * (double)mc.timer.renderPartialTicks) * 200.0;
                    GlStateManager.rotate((float)x, 1.0f, 0.0f, 0.0f);
                    GlStateManager.rotate((float)y, 0.0f, 1.0f, 0.0f);
                    GlStateManager.rotate((float)z, 0.0f, 0.0f, 1.0f);
                }
            } else {
                GlStateManager.rotate(f3, 0.0f, 1.0f, 0.0f);
            }
        }
        if (!flag) {
            float f6 = -0.0f * (float)(i - 1) * 0.5f;
            float f4 = -0.0f * (float)(i - 1) * 0.5f;
            float f5 = -0.046875f * (float)(i - 1) * 0.5f;
            GlStateManager.translate(f6, f4, f5);
        }
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        return i;
    }

    public static int func_177078_a(ItemStack stack) {
        int i = 1;
        if (stack.stackSize > 48) {
            i = 5;
        } else if (stack.stackSize > 32) {
            i = 4;
        } else if (stack.stackSize > 16) {
            i = 3;
        } else if (stack.stackSize > 1) {
            i = 2;
        }
        return i;
    }

    @Override
    public void doRender(EntityItem entity, double x, double y, double z, float entityYaw, float partialTicks) {
        ItemStack itemstack = entity.getEntityItem();
        this.field_177079_e.setSeed(187L);
        boolean flag = false;
        if (this.bindEntityTexture(entity)) {
            this.renderManager.renderEngine.getTexture(this.getEntityTexture(entity)).setBlurMipmap(false, false);
            flag = true;
        }
        GlStateManager.enableRescaleNormal();
        GlStateManager.alphaFunc(516, 0.1f);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.pushMatrix();
        IBakedModel ibakedmodel = this.itemRenderer.getItemModelMesher().getItemModel(itemstack);
        int i = this.func_177077_a(entity, x, y, z, partialTicks, ibakedmodel);
        for (int j = 0; j < i; ++j) {
            if (ibakedmodel.isGui3d()) {
                GlStateManager.pushMatrix();
                if (j > 0) {
                    float f = (this.field_177079_e.nextFloat() * 2.0f - 1.0f) * 0.15f;
                    float f1 = (this.field_177079_e.nextFloat() * 2.0f - 1.0f) * 0.15f;
                    float f2 = (this.field_177079_e.nextFloat() * 2.0f - 1.0f) * 0.15f;
                    GlStateManager.translate(f, f1, f2);
                }
                GlStateManager.scale(0.5f, 0.5f, 0.5f);
                ibakedmodel.getItemCameraTransforms().applyTransform(ItemCameraTransforms.TransformType.GROUND);
                this.itemRenderer.renderItem(itemstack, ibakedmodel);
                GlStateManager.popMatrix();
                continue;
            }
            GlStateManager.pushMatrix();
            ibakedmodel.getItemCameraTransforms().applyTransform(ItemCameraTransforms.TransformType.GROUND);
            this.itemRenderer.renderItem(itemstack, ibakedmodel);
            GlStateManager.popMatrix();
            float f3 = ibakedmodel.getItemCameraTransforms().ground.scale.x;
            float f4 = ibakedmodel.getItemCameraTransforms().ground.scale.y;
            float f5 = ibakedmodel.getItemCameraTransforms().ground.scale.z;
            GlStateManager.translate(0.0f * f3, 0.0f * f4, 0.046875f * f5);
        }
        GlStateManager.popMatrix();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableBlend();
        this.bindEntityTexture(entity);
        if (flag) {
            this.renderManager.renderEngine.getTexture(this.getEntityTexture(entity)).restoreLastBlurMipmap();
        }
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityItem entity) {
        return TextureMap.locationBlocksTexture;
    }
}

