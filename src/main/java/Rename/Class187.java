package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import Rename.Class192;
import Rename.Class196;
import Rename.Class193;
import Rename.Class204;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class Class187
extends Class192 {
    @Override
    public String getName() {
        return "sneak";
    }

    @Override
    public void animate(EntityLivingBase argEntity, ModelBase argModel, Class204 argData) {
        Class193 model = (Class193)argModel;
        float var = (float)((double)(model.armSwing * 0.6662f) / Math.PI) % 2.0f;
        ((Class196)model.bipedRightLeg).rotation.setSmoothX(-5.0f + 1.1f * (float)((double)(MathHelper.cos(model.armSwing * 0.6662f) * 1.4f * model.armSwingAmount) / Math.PI * 180.0), 1.0f);
        ((Class196)model.bipedLeftLeg).rotation.setSmoothX(-5.0f + 1.1f * (float)((double)(MathHelper.cos(model.armSwing * 0.6662f + (float)Math.PI) * 1.4f * model.armSwingAmount) / Math.PI * 180.0), 1.0f);
        ((Class196)model.bipedRightLeg).rotation.setSmoothZ(10.0f);
        ((Class196)model.bipedLeftLeg).rotation.setSmoothZ(-10.0f);
        ((Class196)model.bipedRightArm).rotation.setSmoothX(-20.0f + 20.0f * MathHelper.cos(model.armSwing * 0.6662f + (float)Math.PI));
        ((Class196)model.bipedLeftArm).rotation.setSmoothX(-20.0f + 20.0f * MathHelper.cos(model.armSwing * 0.6662f));
        model.bipedLeftForeLeg.rotation.setSmoothX(var > 1.0f ? 45 : 10, 0.3f);
        model.bipedRightForeLeg.rotation.setSmoothX(var > 1.0f ? 10 : 45, 0.3f);
        model.bipedLeftForeArm.rotation.setSmoothX(var > 1.0f ? -10 : -45, 0.01f);
        model.bipedRightForeArm.rotation.setSmoothX(var > 1.0f ? -45 : -10, 0.01f);
        float var2 = 25.0f + (float)Math.cos(model.armSwing * 0.6662f * 2.0f) * 5.0f;
        ((Class196)model.bipedBody).rotation.setSmoothX(var2);
        ((Class196)model.bipedHead).rotation.setX(model.headRotationX - ((Class196)model.bipedBody).rotation.getX());
    }
}

