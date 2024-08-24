package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import Rename.Class196;
import Rename.Class193;
import Rename.Class203;
import net.minecraft.entity.player.EntityPlayer;

public class Class179 {
    public static void animate(EntityPlayer player, Class193 model, Class203 data) {
        if (data.motion.x == 0.0f & data.motion.z == 0.0f) {
            model.renderRotation.setSmoothY(20.0f);
            model.renderOffset.setSmoothY(-2.0f);
            ((Class196)model.bipedRightArm).rotation.setSmoothX(-90.0f, 0.3f);
            model.bipedRightForeArm.rotation.setSmoothX(-80.0f, 0.3f);
            ((Class196)model.bipedLeftArm).rotation.setSmoothX(-90.0f, 0.3f);
            model.bipedLeftForeArm.rotation.setSmoothX(-80.0f, 0.3f);
            ((Class196)model.bipedRightArm).rotation.setSmoothZ(20.0f, 0.3f);
            ((Class196)model.bipedLeftArm).rotation.setSmoothZ(-20.0f, 0.3f);
            ((Class196)model.bipedBody).rotation.setSmoothX(10.0f, 0.3f);
            ((Class196)model.bipedRightLeg).rotation.setSmoothX(-30.0f, 0.3f);
            ((Class196)model.bipedLeftLeg).rotation.setSmoothX(-30.0f, 0.3f);
            ((Class196)model.bipedLeftLeg).rotation.setSmoothY(-25.0f, 0.3f);
            ((Class196)model.bipedRightLeg).rotation.setSmoothZ(10.0f);
            ((Class196)model.bipedLeftLeg).rotation.setSmoothZ(-10.0f);
            model.bipedRightForeLeg.rotation.setSmoothX(30.0f, 0.3f);
            model.bipedLeftForeLeg.rotation.setSmoothX(30.0f, 0.3f);
            if (data.fistPunchArm) {
                ((Class196)model.bipedRightArm).pre_rotation.setSmoothZ(100.0f, 0.9f);
                ((Class196)model.bipedRightArm).rotation.setSmoothX(-90.0f, 0.9f);
                ((Class196)model.bipedRightArm).pre_rotation.setSmoothX(model.headRotationX, 0.9f);
                model.bipedRightForeArm.rotation.setSmoothX(0.0f, 0.9f);
                ((Class196)model.bipedBody).rotation.setSmoothY(-20.0f, 0.6f);
                ((Class196)model.bipedHead).rotation.setY(model.headRotationY - ((Class196)model.bipedBody).rotation.getY() - 20.0f);
                ((Class196)model.bipedHead).rotation.setX(model.headRotationX - 10.0f);
            } else {
                ((Class196)model.bipedLeftArm).pre_rotation.setSmoothZ(-100.0f, 0.9f);
                ((Class196)model.bipedLeftArm).pre_rotation.setSmoothY(30.0f, 0.9f);
                ((Class196)model.bipedLeftArm).rotation.setSmoothX(-90.0f, 0.9f);
                ((Class196)model.bipedLeftArm).pre_rotation.setSmoothX(model.headRotationX, 0.9f);
                model.bipedLeftForeArm.rotation.setSmoothX(0.0f, 0.9f);
                ((Class196)model.bipedBody).rotation.setSmoothY(20.0f, 0.6f);
                ((Class196)model.bipedHead).rotation.setY(model.headRotationY - 20.0f - ((Class196)model.bipedBody).rotation.getY());
                ((Class196)model.bipedHead).rotation.setX(model.headRotationX - 10.0f);
            }
        } else {
            ((Class196)model.bipedRightArm).rotation.setSmoothX(-90.0f, 0.3f);
            model.bipedRightForeArm.rotation.setSmoothX(-80.0f, 0.3f);
            ((Class196)model.bipedLeftArm).rotation.setSmoothX(-90.0f, 0.3f);
            model.bipedLeftForeArm.rotation.setSmoothX(-80.0f, 0.3f);
            ((Class196)model.bipedRightArm).rotation.setSmoothZ(20.0f, 0.3f);
            ((Class196)model.bipedLeftArm).rotation.setSmoothZ(-20.0f, 0.3f);
            ((Class196)model.bipedBody).rotation.setSmoothX(10.0f, 0.3f);
            ((Class196)model.bipedBody).rotation.setSmoothY(0.0f, 0.3f);
            if (data.fistPunchArm) {
                ((Class196)model.bipedRightArm).pre_rotation.setSmoothZ(100.0f, 0.9f);
                ((Class196)model.bipedRightArm).rotation.setSmoothX(-90.0f, 0.9f);
                ((Class196)model.bipedRightArm).pre_rotation.setSmoothX(-20.0f + model.headRotationX, 0.9f);
                model.bipedRightForeArm.rotation.setSmoothX(0.0f, 0.9f);
                ((Class196)model.bipedBody).rotation.setSmoothY(-20.0f, 0.6f);
                ((Class196)model.bipedHead).rotation.setY(model.headRotationY + 20.0f);
                ((Class196)model.bipedHead).rotation.setX(model.headRotationX - 10.0f);
            } else {
                ((Class196)model.bipedLeftArm).pre_rotation.setSmoothZ(-100.0f, 0.9f);
                ((Class196)model.bipedLeftArm).pre_rotation.setSmoothY(-15.0f, 0.9f);
                ((Class196)model.bipedLeftArm).rotation.setSmoothX(-90.0f, 0.9f);
                ((Class196)model.bipedLeftArm).pre_rotation.setSmoothX(-20.0f + model.headRotationX, 0.9f);
                model.bipedLeftForeArm.rotation.setSmoothX(0.0f, 0.9f);
                ((Class196)model.bipedBody).rotation.setSmoothY(20.0f, 0.6f);
                ((Class196)model.bipedHead).rotation.setY(model.headRotationY - 20.0f);
                ((Class196)model.bipedHead).rotation.setX(model.headRotationX - 10.0f);
            }
        }
    }
}

