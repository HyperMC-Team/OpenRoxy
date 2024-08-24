package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderEntityItem;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
public class Class281
extends Class252 {
    public static final Class241 delayTimer = new Class241();

    public Class281() {
        super("Class281", Class263.Render);
    }

    public static int func_177077_a(EntityItem itemIn, double x, double y, double z, float p_177077_8_, IBakedModel p_177077_9_) {
        float pitch;
        ItemStack itemstack = itemIn.getEntityItem();
        Item item = itemstack.getItem();
        boolean var12 = p_177077_9_.isAmbientOcclusion();
        int var13 = RenderEntityItem.func_177078_a(itemstack);
        if (!(item instanceof ItemBlock)) {
            GlStateManager.translate((float)x, (double)((float)y) + 0.1, (float)z);
        } else {
            GlStateManager.translate((float)x, (double)((float)y) + 0.2, (float)z);
        }
        float f = pitch = itemIn.onGround ? 90.0f : itemIn.rotationPitch;
        if (delayTimer.hasReached(5.0)) {
            itemIn.rotationPitch += 1.0f;
        }
        if (itemIn.rotationPitch > 180.0f) {
            itemIn.rotationPitch = -180.0f;
        }
        GlStateManager.rotate(pitch, 1.0f, 0.0f, 0.0f);
        GlStateManager.rotate(itemIn.rotationYaw, 0.0f, 0.0f, 1.0f);
        if (!var12) {
            float var16 = -0.0f * (float)(var13 - 1) * 0.5f;
            float var17 = -0.0f * (float)(var13 - 1) * 0.5f;
            float var18 = -0.046875f * (float)(var13 - 1) * 0.5f;
            GlStateManager.translate(var16, var17, var18);
        }
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        return var13;
    }
}

