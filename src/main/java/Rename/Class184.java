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
import Rename.Class203;
import Rename.Class204;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;

public class Class184
extends Class192 {
    @Override
    public String getName() {
        return "jump";
    }

    @Override
    public void animate(EntityLivingBase argEntity, ModelBase argModel, Class204 argData) {
        Class193 model = (Class193)argModel;
        Class203 data = (Class203)argData;
        ((Class196)model.bipedBody).rotation.setSmoothX(0.0f, 0.3f);
        ((Class196)model.bipedBody).rotation.setSmoothY(0.0f, 0.1f);
        ((Class196)model.bipedBody).rotation.setSmoothZ(0.0f, 0.3f);
        ((Class196)model.bipedRightArm).rotation.setSmoothZ(45.0f, 0.05f);
        ((Class196)model.bipedLeftArm).rotation.setSmoothZ(-45.0f, 0.05f);
        ((Class196)model.bipedRightArm).rotation.setSmoothX(0.0f, 0.5f);
        ((Class196)model.bipedLeftArm).rotation.setSmoothX(0.0f, 0.5f);
        ((Class196)model.bipedRightLeg).rotation.setSmoothZ(10.0f, 0.1f);
        ((Class196)model.bipedLeftLeg).rotation.setSmoothZ(-10.0f, 0.1f);
        ((Class196)model.bipedRightLeg).rotation.setSmoothX(-20.0f, 0.1f);
        ((Class196)model.bipedLeftLeg).rotation.setSmoothX(-20.0f, 0.1f);
        ((Class196)model.bipedRightLeg).rotation.setSmoothX(-45.0f, 0.1f);
        ((Class196)model.bipedLeftLeg).rotation.setSmoothX(-45.0f, 0.1f);
        model.bipedRightForeLeg.rotation.setSmoothX(50.0f, 0.1f);
        model.bipedLeftForeLeg.rotation.setSmoothX(50.0f, 0.1f);
        model.bipedRightForeArm.rotation.setSmoothX(0.0f, 0.3f);
        model.bipedLeftForeArm.rotation.setSmoothX(0.0f, 0.3f);
        ((Class196)model.bipedHead).rotation.setY(model.headRotationY);
        ((Class196)model.bipedHead).rotation.setX(model.headRotationX - model.bipedBody.rotateAngleX);
        if (data.ticksAfterLiftoff < 2.0f) {
            ((Class196)model.bipedBody).rotation.setSmoothX(20.0f, 2.0f);
            ((Class196)model.bipedRightLeg).rotation.setSmoothX(0.0f, 2.0f);
            ((Class196)model.bipedLeftLeg).rotation.setSmoothX(0.0f, 2.0f);
            model.bipedRightForeLeg.rotation.setSmoothX(0.0f, 2.0f);
            model.bipedLeftForeLeg.rotation.setSmoothX(0.0f, 2.0f);
            ((Class196)model.bipedRightArm).rotation.setSmoothZ(2.0f, 2.0f);
            ((Class196)model.bipedLeftArm).rotation.setSmoothZ(-2.0f, 2.0f);
            model.bipedRightForeArm.rotation.setSmoothX(-20.0f, 2.0f);
            model.bipedLeftForeArm.rotation.setSmoothX(-20.0f, 2.0f);
        }
        if (argData.motion.x != 0.0f | argData.motion.z != 0.0f) {
            if (argEntity.isSprinting()) {
                float bodyRot = 0.0f;
                float bodyLean = argData.motion.y;
                if (bodyLean < -0.2f) {
                    bodyLean = -0.2f;
                }
                if (bodyLean > 0.2f) {
                    bodyLean = 0.2f;
                }
                bodyLean = bodyLean * -100.0f + 20.0f;
                ((Class196)model.bipedBody).rotation.setSmoothX(bodyLean, 0.3f);
                ((Class196)model.bipedRightLeg).rotation.setSmoothZ(5.0f, 0.3f);
                ((Class196)model.bipedLeftLeg).rotation.setSmoothZ(-5.0f, 0.3f);
                ((Class196)model.bipedRightArm).rotation.setSmoothZ(10.0f, 0.3f);
                ((Class196)model.bipedLeftArm).rotation.setSmoothZ(-10.0f, 0.3f);
                if (data.sprintJumpLeg) {
                    ((Class196)model.bipedRightLeg).rotation.setSmoothX(-45.0f, 0.4f);
                    ((Class196)model.bipedLeftLeg).rotation.setSmoothX(45.0f, 0.4f);
                    ((Class196)model.bipedRightArm).rotation.setSmoothX(50.0f, 0.3f);
                    ((Class196)model.bipedLeftArm).rotation.setSmoothX(-50.0f, 0.3f);
                    bodyRot = 20.0f;
                } else {
                    ((Class196)model.bipedRightLeg).rotation.setSmoothX(45.0f, 0.4f);
                    ((Class196)model.bipedLeftLeg).rotation.setSmoothX(-45.0f, 0.4f);
                    ((Class196)model.bipedRightArm).rotation.setSmoothX(-50.0f, 0.3f);
                    ((Class196)model.bipedLeftArm).rotation.setSmoothX(50.0f, 0.3f);
                    bodyRot = -20.0f;
                }
                ((Class196)model.bipedBody).rotation.setSmoothY(bodyRot, 0.3f);
                ((Class196)model.bipedHead).rotation.setY(model.headRotationY - bodyRot);
                ((Class196)model.bipedHead).rotation.setX(model.headRotationX - 20.0f);
                float var = model.bipedRightLeg.rotateAngleX;
                model.bipedLeftForeLeg.rotation.setSmoothX(var < 0.0f ? 45 : 2, 0.3f);
                model.bipedRightForeLeg.rotation.setSmoothX(var < 0.0f ? 2 : 45, 0.3f);
            } else {
                ((Class196)model.bipedRightLeg).rotation.setSmoothX(-5.0f + 0.5f * (float)((double)(MathHelper.cos(model.armSwing * 0.6662f) * 1.4f * model.armSwingAmount) / Math.PI * 180.0), 1.0f);
                ((Class196)model.bipedLeftLeg).rotation.setSmoothX(-5.0f + 0.5f * (float)((double)(MathHelper.cos(model.armSwing * 0.6662f + (float)Math.PI) * 1.4f * model.armSwingAmount) / Math.PI * 180.0), 1.0f);
                float var = (float)((double)(model.armSwing * 0.6662f) / Math.PI) % 2.0f;
                model.bipedLeftForeLeg.rotation.setSmoothX(var > 1.0f ? 45 : 0, 0.3f);
                model.bipedRightForeLeg.rotation.setSmoothX(var > 1.0f ? 0 : 45, 0.3f);
                model.bipedLeftForeArm.rotation.setSmoothX((float)(Math.cos((double)(model.armSwing * 0.6662f) + 1.5707963267948966) + 1.0) / 2.0f * -20.0f, 1.0f);
                model.bipedRightForeArm.rotation.setSmoothX((float)(Math.cos(model.armSwing * 0.6662f) + 1.0) / 2.0f * -20.0f, 0.3f);
            }
        }
    }
}

