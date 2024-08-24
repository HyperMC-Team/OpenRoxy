package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class Class188
extends Class192 {
    @Override
    public String getName() {
        return "sprint";
    }

    @Override
    public void animate(EntityLivingBase argEntity, ModelBase argModel, Class204 argData) {
        Class193 model = (Class193)argModel;
        ((Class196)model.bipedRightArm).rotation.setSmoothX(1.1f * (float)((double)(MathHelper.cos(model.armSwing * 0.6662f + (float)Math.PI) * 2.0f * model.armSwingAmount * 0.5f) / Math.PI * 180.0));
        ((Class196)model.bipedLeftArm).rotation.setSmoothX(1.1f * (float)((double)(MathHelper.cos(model.armSwing * 0.6662f) * 2.0f * model.armSwingAmount * 0.5f) / Math.PI * 180.0));
        ((Class196)model.bipedRightArm).rotation.setSmoothZ(5.0f, 0.3f);
        ((Class196)model.bipedLeftArm).rotation.setSmoothZ(-5.0f, 0.3f);
        ((Class196)model.bipedRightLeg).rotation.setSmoothX(-5.0f + 0.9f * (float)((double)(MathHelper.cos(model.armSwing * 0.6662f) * 1.4f * model.armSwingAmount) / Math.PI * 180.0), 1.0f);
        ((Class196)model.bipedLeftLeg).rotation.setSmoothX(-5.0f + 0.9f * (float)((double)(MathHelper.cos(model.armSwing * 0.6662f + (float)Math.PI) * 1.4f * model.armSwingAmount) / Math.PI * 180.0), 1.0f);
        ((Class196)model.bipedRightLeg).rotation.setSmoothY(0.0f);
        ((Class196)model.bipedLeftLeg).rotation.setSmoothY(0.0f);
        ((Class196)model.bipedRightLeg).rotation.setSmoothZ(2.0f, 0.2f);
        ((Class196)model.bipedLeftLeg).rotation.setSmoothZ(-2.0f, 0.2f);
        float var = (float)((double)(model.armSwing * 0.6662f) / Math.PI) % 2.0f;
        model.bipedLeftForeLeg.rotation.setSmoothX(var > 1.0f ? 45 : 0, 0.3f);
        model.bipedRightForeLeg.rotation.setSmoothX(var > 1.0f ? 0 : 45, 0.3f);
        model.bipedLeftForeArm.rotation.setSmoothX(var > 1.0f ? -10 : -45, 0.3f);
        model.bipedRightForeArm.rotation.setSmoothX(var > 1.0f ? -45 : -10, 0.3f);
        float var2 = (float)Math.cos(model.armSwing * 0.6662f) * -40.0f;
        float var3 = (float)(Math.cos(model.armSwing * 0.6662f * 2.0f) * 0.5 + 0.5) * 20.0f;
        ((Class196)model.bipedBody).rotation.setSmoothY(var2, 0.5f);
        ((Class196)model.bipedBody).rotation.setSmoothX(var3);
        ((Class196)model.bipedHead).rotation.setSmoothY(model.headRotationY - var2, 0.5f);
        ((Class196)model.bipedHead).rotation.setSmoothX(model.headRotationX - var3);
        float var10 = model.headRotationY * 0.3f;
        var10 = Class328.max(var10, 20.0f);
        var10 = Class328.min(var10, -20.0f);
        ((Class196)model.bipedBody).rotation.setSmoothZ(-var10, 0.3f);
        model.renderOffset.setSmoothY(var3 * 0.15f, 0.9f);
    }
}

