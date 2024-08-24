package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import com.mojang.authlib.GameProfile;
import Rename.Class193;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.tileentity.TileEntitySkull;
import net.minecraft.util.EnumFacing;

public class Class199
implements LayerRenderer {
    private final Class193 model;

    public Class199(Class193 p_i46120_1_) {
        this.model = p_i46120_1_;
    }

    public void doRenderLayer(EntityLivingBase p_177141_1_, float p_177141_2_, float p_177141_3_, float p_177141_4_, float p_177141_5_, float p_177141_6_, float p_177141_7_, float p_177141_8_) {
        ItemStack itemstack = p_177141_1_.getCurrentArmor(3);
        if (itemstack != null && itemstack.getItem() != null) {
            float f7;
            boolean flag;
            Item item = itemstack.getItem();
            Minecraft minecraft = Minecraft.getMinecraft();
            GlStateManager.pushMatrix();
            if (p_177141_1_.isSneaking()) {
                GlStateManager.translate(0.0f, 0.2f, 0.0f);
            }
            boolean bl = flag = p_177141_1_ instanceof EntityVillager || p_177141_1_ instanceof EntityZombie && ((EntityZombie)p_177141_1_).isVillager();
            if (!flag && p_177141_1_.isChild()) {
                f7 = 2.0f;
                float f8 = 1.4f;
                GlStateManager.scale(f8 / f7, f8 / f7, f8 / f7);
                GlStateManager.translate(0.0f, 16.0f * p_177141_8_, 0.0f);
            }
            this.model.bipedBody.postRender(0.0625f);
            this.model.bipedHead.postRender(0.0625f);
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            if (item instanceof ItemBlock) {
                f7 = 0.625f;
                GlStateManager.translate(0.0f, -0.25f, 0.0f);
                GlStateManager.rotate(180.0f, 0.0f, 1.0f, 0.0f);
                GlStateManager.scale(f7, -f7, -f7);
                if (flag) {
                    GlStateManager.translate(0.0f, 0.1875f, 0.0f);
                }
                minecraft.getItemRenderer().renderItem(p_177141_1_, itemstack, ItemCameraTransforms.TransformType.HEAD);
            } else if (item == Items.skull) {
                f7 = 1.1875f;
                GlStateManager.scale(f7, -f7, -f7);
                if (flag) {
                    GlStateManager.translate(0.0f, 0.0625f, 0.0f);
                }
                GameProfile gameprofile = null;
                if (itemstack.hasTagCompound()) {
                    NBTTagCompound nbttagcompound = itemstack.getTagCompound();
                    if (nbttagcompound.hasKey("SkullOwner", 10)) {
                        gameprofile = NBTUtil.readGameProfileFromNBT(nbttagcompound.getCompoundTag("SkullOwner"));
                    } else if (nbttagcompound.hasKey("SkullOwner", 8)) {
                        gameprofile = TileEntitySkull.updateGameprofile(new GameProfile(null, nbttagcompound.getString("SkullOwner")));
                        nbttagcompound.setTag("SkullOwner", NBTUtil.writeGameProfile(new NBTTagCompound(), gameprofile));
                    }
                }
                TileEntitySkullRenderer.instance.renderSkull(-0.5f, 0.0f, -0.5f, EnumFacing.UP, 180.0f, itemstack.getMetadata(), gameprofile, -1);
            }
            GlStateManager.popMatrix();
        }
    }

    @Override
    public boolean shouldCombineTextures() {
        return true;
    }
}

