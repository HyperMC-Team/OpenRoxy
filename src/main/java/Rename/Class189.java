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
import org.lwjgl.util.vector.Vector3f;

public class Class189
extends Class192 {
    @Override
    public String getName() {
        return "stand";
    }

    @Override
    public void animate(EntityLivingBase argEntity, ModelBase argModel, Class204 argData) {
        Class193 model = (Class193)argModel;
        Class203 data = (Class203)argData;
        ((Class196)model.bipedBody).rotation.setSmooth(new Vector3f(0.0f, 0.0f, 0.0f), 0.5f);
        ((Class196)model.bipedRightLeg).rotation.setSmoothZ(2.0f, 0.2f);
        ((Class196)model.bipedLeftLeg).rotation.setSmoothZ(-2.0f, 0.2f);
        ((Class196)model.bipedRightLeg).rotation.setSmoothX(0.0f, 0.1f);
        ((Class196)model.bipedLeftLeg).rotation.setSmoothX(0.0f, 0.1f);
        ((Class196)model.bipedRightLeg).rotation.setSmoothY(5.0f);
        ((Class196)model.bipedLeftLeg).rotation.setSmoothY(-5.0f);
        ((Class196)model.bipedRightArm).rotation.setSmoothX(0.0f, 0.1f);
        ((Class196)model.bipedLeftArm).rotation.setSmoothX(0.0f, 0.1f);
        model.bipedRightForeLeg.rotation.setSmoothX(4.0f, 0.1f);
        model.bipedLeftForeLeg.rotation.setSmoothX(4.0f, 0.1f);
        model.bipedRightForeArm.rotation.setSmoothX(-4.0f, 0.1f);
        model.bipedLeftForeArm.rotation.setSmoothX(-4.0f, 0.1f);
        ((Class196)model.bipedHead).rotation.setX(model.headRotationX);
        ((Class196)model.bipedHead).rotation.setY(model.headRotationY);
        ((Class196)model.bipedBody).rotation.setSmoothX((float)((Math.cos(data.ticks / 10.0f) - 1.0) / 2.0) * -3.0f);
        ((Class196)model.bipedLeftArm).rotation.setSmoothZ(-((float)((Math.cos((double)(data.ticks / 10.0f) + 1.5707963267948966) - 1.0) / 2.0)) * -5.0f);
        ((Class196)model.bipedRightArm).rotation.setSmoothZ(-((float)((Math.cos((double)(data.ticks / 10.0f) + 1.5707963267948966) - 1.0) / 2.0)) * 5.0f);
    }
}

