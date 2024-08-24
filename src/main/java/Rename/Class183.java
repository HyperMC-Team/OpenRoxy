package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class Class183
extends Class192 {
    @Override
    public String getName() {
        return "bow";
    }

    @Override
    public void animate(EntityLivingBase argEntity, ModelBase argModel, Class204 argData) {
        Class193 model = (Class193)argModel;
        Class203 data = (Class203)argData;
        EntityPlayer player = (EntityPlayer)argEntity;
        float aimedBowDuration = 0.0f;
        if (player != null) {
            aimedBowDuration = player.getItemInUseDuration();
        }
        if (aimedBowDuration > 15.0f) {
            aimedBowDuration = 15.0f;
        }
        if (aimedBowDuration < 10.0f) {
            ((Class196)model.bipedRightArm).rotation.setSmoothX(0.0f, 0.3f);
            ((Class196)model.bipedLeftArm).rotation.setSmoothX(0.0f, 0.3f);
            ((Class196)model.bipedBody).rotation.setSmoothX(30.0f, 0.3f);
            ((Class196)model.bipedBody).rotation.setSmoothY(0.0f, 0.3f);
            ((Class196)model.bipedRightArm).rotation.setSmoothZ(0.0f);
            ((Class196)model.bipedRightArm).rotation.setSmoothX(-30.0f);
            ((Class196)model.bipedLeftArm).rotation.setSmoothX(-30.0f);
            ((Class196)model.bipedLeftArm).rotation.setSmoothY(80.0f);
            float var = aimedBowDuration / 10.0f;
            model.bipedLeftForeArm.rotation.setSmoothX(var * -50.0f);
            ((Class196)model.bipedHead).rotation.setSmoothX(model.headRotationX - 30.0f, 0.3f);
        } else {
            float var1 = 20.0f - (aimedBowDuration - 10.0f) / 5.0f * 20.0f;
            ((Class196)model.bipedBody).rotation.setSmoothX(var1, 0.3f);
            float var = (aimedBowDuration - 10.0f) / 5.0f * -25.0f;
            ((Class196)model.bipedBody).rotation.setSmoothY(var + model.headRotationY, 0.3f);
            ((Class196)model.bipedRightArm).rotation.setSmoothX(-90.0f - var1, 0.3f);
            ((Class196)model.bipedLeftArm).rotation.setSmoothX(-30.0f);
            ((Class196)model.bipedLeftArm).rotation.setSmoothY(80.0f);
            float var2 = aimedBowDuration / 10.0f;
            model.bipedLeftForeArm.rotation.setSmoothX(var2 * -30.0f);
            ((Class196)model.bipedRightArm).pre_rotation.setSmoothY(var);
            float var5 = -90.0f + model.headRotationX;
            var5 = Class328.min(var5, -120.0f);
            ((Class196)model.bipedLeftArm).pre_rotation.setSmoothX(var5, 0.3f);
            ((Class196)model.bipedRightArm).rotation.setSmoothX(model.headRotationX - 90.0f);
            ((Class196)model.bipedHead).rotation.setY(-var);
            ((Class196)model.bipedHead).pre_rotation.setSmoothX(-var1, 0.3f);
            ((Class196)model.bipedHead).rotation.setX(model.headRotationX);
        }
    }
}

