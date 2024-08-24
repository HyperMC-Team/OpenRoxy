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
import org.lwjgl.util.vector.Vector3f;

public class Class181 {
    public static void animate(EntityPlayer player, Class193 model, Class203 data) {
        if (!data.isOnGround()) {
            return;
        }
        if (data.motion.x == 0.0f & data.motion.z == 0.0f) {
            model.renderRotation.setSmoothY(30.0f, 0.3f);
            Vector3f bodyRot = new Vector3f(0.0f, 0.0f, 0.0f);
            bodyRot.x = 20.0f;
            ((Class196)model.bipedBody).rotation.setSmooth(bodyRot, 0.3f);
            ((Class196)model.bipedHead).rotation.setY(model.headRotationY - 30.0f);
            ((Class196)model.bipedHead).rotation.setX(model.headRotationX);
            ((Class196)model.bipedHead).pre_rotation.setSmoothX(-bodyRot.x, 0.3f);
            ((Class196)model.bipedHead).pre_rotation.setSmoothY(-bodyRot.y, 0.3f);
            ((Class196)model.bipedRightLeg).rotation.setSmoothX(-30.0f, 0.3f);
            ((Class196)model.bipedLeftLeg).rotation.setSmoothX(-30.0f, 0.3f);
            ((Class196)model.bipedLeftLeg).rotation.setSmoothY(-25.0f, 0.3f);
            ((Class196)model.bipedRightLeg).rotation.setSmoothZ(10.0f);
            ((Class196)model.bipedLeftLeg).rotation.setSmoothZ(-10.0f);
            model.bipedRightForeLeg.rotation.setSmoothX(30.0f, 0.3f);
            model.bipedLeftForeLeg.rotation.setSmoothX(30.0f, 0.3f);
            ((Class196)model.bipedRightArm).pre_rotation.setSmoothZ(60.0f, 0.3f);
            ((Class196)model.bipedRightArm).rotation.setSmoothX(60.0f, 0.3f);
            ((Class196)model.bipedLeftArm).rotation.setSmoothZ(20.0f, 0.3f);
            ((Class196)model.bipedLeftArm).pre_rotation.setSmoothZ(-80.0f, 0.3f);
            model.bipedRightForeArm.rotation.setSmoothX(-20.0f, 0.3f);
            model.bipedLeftForeArm.rotation.setSmoothX(-60.0f, 0.3f);
            model.renderItemRotation.setSmoothX(65.0f, 0.3f);
            model.renderOffset.setSmoothY(-2.0f);
        } else if (player.isSprinting()) {
            ((Class196)model.bipedBody).rotation.setSmoothY(20.0f, 0.3f);
            ((Class196)model.bipedHead).rotation.setY(model.headRotationY - 20.0f);
            ((Class196)model.bipedHead).rotation.setX(model.headRotationX - 15.0f);
            ((Class196)model.bipedRightLeg).rotation.setSmoothY(0.0f);
            ((Class196)model.bipedLeftLeg).rotation.setSmoothY(0.0f);
            ((Class196)model.bipedRightArm).rotation.setSmoothX(60.0f, 0.3f);
            model.renderItemRotation.setSmoothX(90.0f, 0.3f);
        }
    }
}

