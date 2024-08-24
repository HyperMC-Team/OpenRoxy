package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;

public class Class203
extends Class204 {
    public static List<Class203> dataList = new ArrayList<Class203>();
    public Class196 head;
    public Class196 headwear;
    public Class196 body;
    public Class196 rightArm;
    public Class196 leftArm;
    public Class196 rightLeg;
    public Class196 leftLeg;
    public Class196 ears;
    public Class196 cloak;
    public Class196 rightForeArm;
    public Class196 leftForeArm;
    public Class196 rightForeLeg;
    public Class196 leftForeLeg;
    public Class329 renderOffset = new Class329();
    public Class329 renderRotation = new Class329();
    public Class329 renderItemRotation = new Class329();
    public Class202 swordTrail = new Class202();
    public boolean sprintJumpLeg = false;
    public boolean fistPunchArm = false;
    public int currentAttack = 0;

    public Class203(int argEntityID) {
        super(argEntityID);
    }

    public void syncModelInfo(Class193 argModel) {
        if (this.head == null) {
            this.head = new Class196(argModel);
        }
        this.head.sync((Class196)argModel.bipedHead);
        if (this.headwear == null) {
            this.headwear = new Class196(argModel);
        }
        this.headwear.sync((Class196)argModel.bipedHeadwear);
        if (this.body == null) {
            this.body = new Class196(argModel);
        }
        this.body.sync((Class196)argModel.bipedBody);
        if (this.rightArm == null) {
            this.rightArm = new Class196(argModel);
        }
        this.rightArm.sync((Class196)argModel.bipedRightArm);
        if (this.leftArm == null) {
            this.leftArm = new Class196(argModel);
        }
        this.leftArm.sync((Class196)argModel.bipedLeftArm);
        if (this.rightLeg == null) {
            this.rightLeg = new Class196(argModel);
        }
        this.rightLeg.sync((Class196)argModel.bipedRightLeg);
        if (this.leftLeg == null) {
            this.leftLeg = new Class196(argModel);
        }
        this.leftLeg.sync((Class196)argModel.bipedLeftLeg);
        if (this.rightForeArm == null) {
            this.rightForeArm = new Class196(argModel);
        }
        this.rightForeArm.sync(argModel.bipedRightForeArm);
        if (this.leftForeArm == null) {
            this.leftForeArm = new Class196(argModel);
        }
        this.leftForeArm.sync(argModel.bipedLeftForeArm);
        if (this.rightForeLeg == null) {
            this.rightForeLeg = new Class196(argModel);
        }
        this.rightForeLeg.sync(argModel.bipedRightForeLeg);
        if (this.leftForeLeg == null) {
            this.leftForeLeg = new Class196(argModel);
        }
        this.leftForeLeg.sync(argModel.bipedLeftForeLeg);
        this.renderOffset.set(argModel.renderOffset);
        this.renderRotation.set(argModel.renderRotation);
        this.renderItemRotation.set(argModel.renderItemRotation);
        this.swordTrail = argModel.swordTrail;
    }

    public static void add(Class203 argData) {
        dataList.add(argData);
    }

    public static Class204 get(int argEntityID) {
        for (int i = 0; i < dataList.size(); ++i) {
            if (Class203.dataList.get(i).entityID != argEntityID) continue;
            return dataList.get(i);
        }
        Class203 newData = new Class203(argEntityID);
        if (Minecraft.getMinecraft().theWorld.getEntityByID(argEntityID) != null) {
            dataList.add(newData);
        }
        return newData;
    }

    @Override
    public void update(float argPartialTicks) {
        super.update(argPartialTicks);
        if (this.ticksAfterPunch > 20.0f) {
            this.currentAttack = 0;
        }
        if (this.ticksAfterThrowup - this.ticksPerFrame == 0.0f) {
            this.sprintJumpLeg = !this.sprintJumpLeg;
        }
    }

    @Override
    public void onLiftoff() {
        super.onLiftoff();
    }

    @Override
    public void onPunch() {
        if (this.getEntity().getHeldItem() != null) {
            if (this.ticksAfterPunch > 6.0f) {
                if (this.currentAttack == 0) {
                    this.currentAttack = 1;
                    this.ticksAfterPunch = 0.0f;
                } else if (this.ticksAfterPunch < 15.0f) {
                    if (this.currentAttack == 1) {
                        this.currentAttack = 2;
                    } else if (this.currentAttack == 2) {
                        this.currentAttack = !Class262.getModule(Class320.class).spinAttack.isEnabled() || this.getEntity().isRiding() ? 1 : 3;
                    } else if (this.currentAttack == 3) {
                        this.currentAttack = 1;
                    }
                    this.ticksAfterPunch = 0.0f;
                }
            }
        } else {
            this.fistPunchArm = !this.fistPunchArm;
            this.ticksAfterPunch = 0.0f;
        }
    }
}

