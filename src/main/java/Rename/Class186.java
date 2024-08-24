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
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.MathHelper;

public class Class186
extends Class192 {
    @Override
    public String getName() {
        return "riding";
    }

    @Override
    public void animate(EntityLivingBase argEntity, ModelBase argModel, Class204 argData) {
        Class193 model = (Class193)argModel;
        Class203 data = (Class203)argData;
        EntityPlayer player = (EntityPlayer)argEntity;
        model.renderOffset.setSmoothY(1.5f, 0.3f);
        ((Class196)model.bipedBody).rotation.setSmoothY(0.0f, 0.3f);
        ((Class196)model.bipedBody).rotation.setSmoothZ(0.0f, 0.3f);
        ((Class196)model.bipedRightLeg).rotation.setSmoothX(-85.0f, 0.3f);
        ((Class196)model.bipedRightLeg).rotation.setSmoothY(45.0f, 0.3f);
        ((Class196)model.bipedLeftLeg).rotation.setSmoothX(-85.0f, 0.3f);
        ((Class196)model.bipedLeftLeg).rotation.setSmoothY(-45.0f, 0.3f);
        model.bipedRightForeLeg.rotation.setSmoothX(60.0f);
        model.bipedLeftForeLeg.rotation.setSmoothX(60.0f);
        if (argData.motion.x == 0.0f & argData.motion.z == 0.0f) {
            ((Class196)model.bipedRightArm).rotation.setSmoothX(-10.0f, 0.3f);
            ((Class196)model.bipedLeftArm).rotation.setSmoothX(-10.0f, 0.3f);
            ((Class196)model.bipedRightArm).rotation.setSmoothY(-10.0f, 0.3f);
            ((Class196)model.bipedLeftArm).rotation.setSmoothY(10.0f, 0.3f);
            ((Class196)model.bipedBody).rotation.setSmoothX(0.0f, 0.3f);
            model.bipedRightForeArm.rotation.setSmoothX(-20.0f, 0.3f);
            model.bipedLeftForeArm.rotation.setSmoothX(-20.0f, 0.3f);
        } else {
            float jiggle = MathHelper.cos((float)player.ticksExisted * 0.6f) * model.armSwingAmount;
            float jiggle_hard = MathHelper.cos((float)player.ticksExisted * 0.3f) * model.armSwingAmount;
            if (jiggle_hard < 0.0f) {
                jiggle_hard = -jiggle_hard;
            }
            model.renderOffset.setSmoothY(1.5f + jiggle_hard * 20.0f, 0.7f);
            ((Class196)model.bipedBody).rotation.setSmoothX(40.0f + jiggle * 300.0f, 0.3f);
            ((Class196)model.bipedRightArm).rotation.setSmoothX(-45.0f, 0.3f);
            ((Class196)model.bipedLeftArm).rotation.setSmoothX(-45.0f, 0.3f);
            ((Class196)model.bipedRightArm).rotation.setSmoothY(-10.0f, 0.3f);
            ((Class196)model.bipedLeftArm).rotation.setSmoothY(10.0f, 0.3f);
            model.bipedRightForeArm.rotation.setSmoothX(-30.0f, 0.3f);
            model.bipedLeftForeArm.rotation.setSmoothX(-30.0f, 0.3f);
        }
        ((Class196)model.bipedHead).rotation.setSmoothY(model.headRotationY, 0.3f);
        ((Class196)model.bipedHead).rotation.setSmoothX(model.headRotationX - model.bipedBody.rotateAngleX / (float)Math.PI * 180.0f, 0.3f);
    }
}

