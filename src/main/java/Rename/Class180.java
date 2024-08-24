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

public class Class180 {
    public static void animate(EntityPlayer player, Class193 model, Class203 data) {
        if (!(data.motion.x == 0.0f & data.motion.z == 0.0f)) {
            return;
        }
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
        ((Class196)model.bipedHead).rotation.setY(model.headRotationY - 20.0f);
        ((Class196)model.bipedHead).rotation.setX(model.headRotationX - 10.0f);
    }
}

