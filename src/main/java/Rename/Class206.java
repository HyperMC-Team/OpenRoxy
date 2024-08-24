package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.util.ArrayList;
import java.util.List;

public class Class206 {
    public String filename;
    public String displayName;
    public String author;
    public String description;
    public static List<Class207> targets = new ArrayList<Class207>();

    public static Class207 getTargetByID(String argID) {
        for (Class207 target : targets) {
            if (!target.mob.equalsIgnoreCase(argID)) continue;
            return target;
        }
        return null;
    }

    public static void animate(Class193 model, String target, String anim) {
        Class207 bendsTarget = Class206.getTargetByID(target);
        if (bendsTarget == null) {
            return;
        }
        bendsTarget.applyToModel((Class196)model.bipedBody, anim, "body");
        bendsTarget.applyToModel((Class196)model.bipedHead, anim, "head");
        bendsTarget.applyToModel((Class196)model.bipedLeftArm, anim, "leftArm");
        bendsTarget.applyToModel((Class196)model.bipedRightArm, anim, "rightArm");
        bendsTarget.applyToModel((Class196)model.bipedLeftLeg, anim, "leftLeg");
        bendsTarget.applyToModel((Class196)model.bipedRightLeg, anim, "rightLeg");
        bendsTarget.applyToModel(model.bipedLeftForeArm, anim, "leftForeArm");
        bendsTarget.applyToModel(model.bipedRightForeArm, anim, "rightForeArm");
        bendsTarget.applyToModel(model.bipedLeftForeLeg, anim, "leftForeLeg");
        bendsTarget.applyToModel(model.bipedRightForeLeg, anim, "rightForeLeg");
        bendsTarget.applyToModel(model.renderItemRotation, anim, "itemRotation");
        bendsTarget.applyToModel(model.renderRotation, anim, "playerRotation");
    }
}

