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

public class Class190
extends Class192 {
    @Override
    public String getName() {
        return "swimming";
    }

    @Override
    public void animate(EntityLivingBase argEntity, ModelBase argModel, Class204 argData) {
        Class193 model = (Class193)argModel;
        Class203 data = (Class203)argData;
        float armSway = (MathHelper.cos(data.ticks * 0.1625f) + 1.0f) / 2.0f;
        float armSway2 = (-MathHelper.sin(data.ticks * 0.1625f) + 1.0f) / 2.0f;
        float legFlap = MathHelper.cos(data.ticks * 0.4625f);
        float foreArmSway = (float)((double)(data.ticks * 0.1625f) % (Math.PI * 2)) / ((float)Math.PI * 2);
        float foreArmStretch = armSway * 2.0f;
        foreArmStretch -= 1.0f;
        foreArmStretch = Class328.min(foreArmStretch, 0.0f);
        if (data.motion.x == 0.0f & data.motion.z == 0.0f) {
            armSway = (MathHelper.cos(data.ticks * 0.0825f) + 1.0f) / 2.0f;
            armSway2 = (-MathHelper.sin(data.ticks * 0.0825f) + 1.0f) / 2.0f;
            legFlap = MathHelper.cos(data.ticks * 0.2625f);
            ((Class196)model.bipedHead).pre_rotation.setSmoothX(0.0f, 0.3f);
            ((Class196)model.bipedLeftArm).rotation.setSmoothX(armSway2 * 30.0f - 15.0f, 0.3f);
            ((Class196)model.bipedRightArm).rotation.setSmoothX(armSway2 * 30.0f - 15.0f, 0.3f);
            ((Class196)model.bipedLeftArm).rotation.setSmoothZ(-armSway * 30.0f);
            ((Class196)model.bipedRightArm).rotation.setSmoothZ(armSway * 30.0f);
            model.bipedLeftForeArm.rotation.setSmoothX(armSway2 * -40.0f, 0.3f);
            model.bipedRightForeArm.rotation.setSmoothX(armSway2 * -40.0f, 0.3f);
            ((Class196)model.bipedLeftLeg).rotation.setSmoothX(legFlap * 40.0f, 0.3f);
            ((Class196)model.bipedRightLeg).rotation.setSmoothX(-legFlap * 40.0f, 0.3f);
            model.bipedLeftForeLeg.rotation.setSmoothX(5.0f, 0.4f);
            model.bipedRightForeLeg.rotation.setSmoothX(5.0f, 0.4f);
            ((Class196)model.bipedBody).rotation.setSmoothX(armSway * 10.0f);
            ((Class196)model.bipedHead).rotation.setSmoothX(model.headRotationX);
            ((Class196)model.bipedHead).rotation.setSmoothY(model.headRotationY);
        } else {
            ((Class196)model.bipedHead).pre_rotation.setSmoothX(-70.0f - armSway * -20.0f, 0.3f);
            model.renderRotation.setSmoothX(70.0f, 0.3f);
            model.renderOffset.setSmoothZ(10.0f, 0.3f);
            ((Class196)model.bipedLeftArm).pre_rotation.setSmoothY(90.0f, 0.3f);
            ((Class196)model.bipedRightArm).pre_rotation.setSmoothY(-90.0f, 0.3f);
            ((Class196)model.bipedLeftArm).rotation.setSmoothX(armSway * -120.0f - 45.0f, 0.3f);
            ((Class196)model.bipedRightArm).rotation.setSmoothX(armSway * -120.0f - 45.0f, 0.3f);
            ((Class196)model.bipedLeftArm).pre_rotation.setSmoothZ(armSway * -20.0f);
            ((Class196)model.bipedRightArm).pre_rotation.setSmoothZ(-(armSway * -20.0f));
            model.bipedLeftForeArm.rotation.setSmoothX(foreArmSway < 0.55f | (double)foreArmSway > 0.9 ? foreArmStretch * -60.0f : -60.0f, 0.3f);
            model.bipedRightForeArm.rotation.setSmoothX(foreArmSway < 0.55f | (double)foreArmSway > 0.9 ? foreArmStretch * -60.0f : -60.0f, 0.3f);
            ((Class196)model.bipedLeftLeg).rotation.setSmoothX(legFlap * 40.0f, 0.3f);
            ((Class196)model.bipedRightLeg).rotation.setSmoothX(-legFlap * 40.0f, 0.3f);
            model.bipedLeftForeLeg.rotation.setSmoothX(5.0f, 0.4f);
            model.bipedRightForeLeg.rotation.setSmoothX(5.0f, 0.4f);
            ((Class196)model.bipedBody).rotation.setSmoothX(armSway * -20.0f);
            ((Class196)model.bipedHead).rotation.setSmoothX(model.headRotationX);
            ((Class196)model.bipedHead).rotation.setSmoothY(model.headRotationY);
            model.renderItemRotation.setSmoothX(armSway * 120.0f, 0.3f);
        }
    }
}

