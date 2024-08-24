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

public class Class185
extends Class192 {
    @Override
    public String getName() {
        return "mining";
    }

    @Override
    public void animate(EntityLivingBase argEntity, ModelBase argModel, Class204 argData) {
        Class193 model = (Class193)argModel;
        Class203 data = (Class203)argData;
        EntityPlayer player = (EntityPlayer)argEntity;
        ((Class196)model.bipedRightLeg).rotation.setSmoothZ(10.0f, 0.3f);
        ((Class196)model.bipedLeftLeg).rotation.setSmoothZ(-10.0f, 0.3f);
        model.renderOffset.setSmoothY(-1.5f, 0.3f);
        if (player.isSwingInProgress) {
            float speed = 1.8f;
            float progress = (float)player.ticksExisted * speed / 20.0f % 1.0f;
            float progress2 = (float)(player.ticksExisted - 2) * speed / 20.0f % 1.0f;
            float armSwing = (MathHelper.cos(progress * (float)Math.PI * 2.0f) + 1.0f) / 2.0f * -60.0f - 30.0f + model.headRotationX * 0.5f - 30.0f;
            float armYRot = 30.0f + MathHelper.cos((armSwing - 90.0f) / 180.0f * 3.14f) * -5.0f;
            ((Class196)model.bipedRightArm).rotation.setSmoothX(armSwing, 0.7f);
            ((Class196)model.bipedRightArm).rotation.setSmoothY(-armYRot, 0.7f);
            model.renderItemRotation.setSmoothZ(-30.0f, 0.3f);
            ((Class196)model.bipedBody).rotation.setSmoothY(MathHelper.sin(progress2 * (float)Math.PI * 2.0f) * -20.0f);
            ((Class196)model.bipedHead).rotation.setSmoothX(model.headRotationX - model.bipedBody.rotateAngleX);
            ((Class196)model.bipedHead).rotation.setSmoothY(model.headRotationY - model.bipedBody.rotateAngleY);
        }
    }
}

