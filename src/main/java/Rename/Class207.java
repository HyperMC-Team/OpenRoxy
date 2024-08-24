package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.util.ArrayList;
import java.util.List;

public class Class207 {
    public String mob;
    public List<Class205> actions = new ArrayList<Class205>();
    public float visual_DeletePopUp;

    public Class207(String argMob) {
        this.mob = argMob;
        this.visual_DeletePopUp = 0.0f;
    }

    public void applyToModel(Class196 box, String anim, String model) {
        for (Class205 action : this.actions) {
            if (!((action.anim.equalsIgnoreCase(anim) | action.anim.equalsIgnoreCase("all")) & action.model.equalsIgnoreCase(model))) continue;
            if (action.prop == Class205.EnumBoxProperty.ROT) {
                box.rotation.setSmooth(action.axis, action.getNumber(action.axis == Class327.X ? box.rotation.vFinal.x : (action.axis == Class327.Y ? box.rotation.vFinal.y : box.rotation.vFinal.z)), action.smooth);
                continue;
            }
            if (action.prop == Class205.EnumBoxProperty.PREROT) {
                box.pre_rotation.setSmooth(action.axis, action.getNumber(action.axis == Class327.X ? box.pre_rotation.vFinal.x : (action.axis == Class327.Y ? box.pre_rotation.vFinal.y : box.pre_rotation.vFinal.z)), action.smooth);
                continue;
            }
            if (action.prop != Class205.EnumBoxProperty.SCALE) continue;
            if (action.axis == null | action.axis == Class327.X) {
                box.scaleX = action.getNumber(box.scaleX);
            }
            if (action.axis == null | action.axis == Class327.Y) {
                box.scaleY = action.getNumber(box.scaleY);
            }
            if (!(action.axis == null | action.axis == Class327.Z)) continue;
            box.scaleZ = action.getNumber(box.scaleZ);
        }
    }

    public void applyToModel(Class329 box, String anim, String model) {
        for (int i = 0; i < this.actions.size(); ++i) {
            if (!((this.actions.get(i).anim.equalsIgnoreCase(anim) | this.actions.get(i).anim.equalsIgnoreCase("all")) & this.actions.get(i).model.equalsIgnoreCase(model)) || this.actions.get(i).prop != Class205.EnumBoxProperty.ROT) continue;
            box.setSmooth(this.actions.get(i).axis, this.actions.get(i).getNumber(this.actions.get(i).axis == Class327.X ? box.vFinal.x : (this.actions.get(i).axis == Class327.Y ? box.vFinal.y : box.vFinal.z)), this.actions.get(i).smooth);
        }
    }
}

