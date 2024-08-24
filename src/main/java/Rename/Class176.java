package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSword;
import org.lwjgl.util.vector.Vector3f;

public class Class176 {
    public static void animate(EntityPlayer player, Class193 model, Class203 data) {
        if (data.ticksAfterPunch < 0.5f) {
            model.swordTrail.reset();
        }
        if (player.getCurrentEquippedItem() != null && data.ticksAfterPunch < 4.0f & player.getCurrentEquippedItem().getItem() instanceof ItemSword) {
            model.swordTrail.add(model);
        }
        float attackState = data.ticksAfterPunch / 10.0f;
        float armSwing = attackState * 3.0f;
        armSwing = Class328.max(armSwing, 1.0f);
        if (!player.isRiding()) {
            model.renderRotation.setSmoothY(30.0f, 0.7f);
        }
        Vector3f bodyRot = new Vector3f(0.0f, 0.0f, 0.0f);
        bodyRot.x = 20.0f - armSwing * 20.0f;
        bodyRot.y = -90.0f * armSwing;
        ((Class196)model.bipedBody).rotation.setSmooth(bodyRot, 0.9f);
        ((Class196)model.bipedHead).rotation.setY(model.headRotationY);
        ((Class196)model.bipedHead).rotation.setX(model.headRotationX);
        ((Class196)model.bipedHead).pre_rotation.setSmoothX(-model.bipedBody.rotateAngleX, 0.9f);
        ((Class196)model.bipedHead).pre_rotation.setSmoothY(-model.bipedBody.rotateAngleY - 30.0f, 0.9f);
        ((Class196)model.bipedRightArm).pre_rotation.setSmoothZ(60.0f, 0.3f);
        ((Class196)model.bipedRightArm).rotation.setSmoothX(-20.0f + armSwing * 100.0f, 3.0f);
        ((Class196)model.bipedRightArm).rotation.setSmoothX(60.0f - armSwing * 180.0f, 3.0f);
        ((Class196)model.bipedRightArm).rotation.setSmoothY(0.0f, 0.9f);
        ((Class196)model.bipedRightArm).rotation.setSmoothZ(0.0f, 0.9f);
        ((Class196)model.bipedLeftArm).rotation.setSmoothZ(20.0f, 0.3f);
        ((Class196)model.bipedLeftArm).pre_rotation.setSmoothZ(-80.0f, 0.3f);
        model.bipedRightForeArm.rotation.setSmoothX(-20.0f, 0.3f);
        model.bipedLeftForeArm.rotation.setSmoothX(-60.0f, 0.3f);
        if (data.motion.x == 0.0f & data.motion.z == 0.0f) {
            ((Class196)model.bipedRightLeg).rotation.setSmoothX(-30.0f, 0.3f);
            ((Class196)model.bipedLeftLeg).rotation.setSmoothX(-30.0f, 0.3f);
            ((Class196)model.bipedRightLeg).rotation.setSmoothY(0.0f, 0.3f);
            ((Class196)model.bipedLeftLeg).rotation.setSmoothY(-25.0f, 0.3f);
            ((Class196)model.bipedRightLeg).rotation.setSmoothZ(10.0f);
            ((Class196)model.bipedLeftLeg).rotation.setSmoothZ(-10.0f);
            model.bipedRightForeLeg.rotation.setSmoothX(30.0f, 0.3f);
            model.bipedLeftForeLeg.rotation.setSmoothX(30.0f, 0.3f);
            if (!player.isRiding()) {
                model.renderOffset.setSmoothY(-2.0f);
            }
        } else {
            ((Class196)model.bipedBody).rotation.setSmoothY(-70.0f * armSwing, 0.9f);
        }
        model.renderItemRotation.setSmoothX(180.0f, 0.9f);
    }
}

