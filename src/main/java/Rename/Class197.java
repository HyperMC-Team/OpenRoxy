package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import net.minecraft.client.model.ModelBase;
import org.lwjgl.opengl.GL11;

public class Class197
extends Class196 {
    Class196 momModel;
    Class196 seperatedModel;

    public Class197(ModelBase argModel) {
        super(argModel);
    }

    public Class197(ModelBase argModel, String argName) {
        super(argModel, argName);
    }

    public Class197(ModelBase argModel, int argTexOffsetX, int argTexOffsetY) {
        super(argModel, argTexOffsetX, argTexOffsetY);
    }

    public Class197 setMother(Class196 argMom) {
        this.momModel = argMom;
        return this;
    }

    public Class197 setSeperatedPart(Class196 argPart) {
        this.seperatedModel = argPart;
        return this;
    }

    @Override
    public void postRender(float p_78794_1_) {
        this.updateBends(p_78794_1_);
        this.momModel.postRender(p_78794_1_);
        if (!this.isHidden && this.showModel) {
            if (this.rotateAngleX == 0.0f && this.rotateAngleY == 0.0f && this.rotateAngleZ == 0.0f) {
                if (this.rotationPointX != 0.0f || this.rotationPointY != 0.0f || this.rotationPointZ != 0.0f) {
                    GL11.glTranslatef(this.rotationPointX * p_78794_1_, this.rotationPointY * p_78794_1_, this.rotationPointZ * p_78794_1_);
                    GL11.glRotatef(-this.pre_rotation.getY(), 0.0f, 1.0f, 0.0f);
                    GL11.glRotatef(this.pre_rotation.getX(), 1.0f, 0.0f, 0.0f);
                    GL11.glRotatef(this.pre_rotation.getZ(), 0.0f, 0.0f, 1.0f);
                }
            } else {
                GL11.glTranslatef(this.rotationPointX * p_78794_1_, this.rotationPointY * p_78794_1_, this.rotationPointZ * p_78794_1_);
                GL11.glRotatef(-this.pre_rotation.getY(), 0.0f, 1.0f, 0.0f);
                GL11.glRotatef(this.pre_rotation.getX(), 1.0f, 0.0f, 0.0f);
                GL11.glRotatef(this.pre_rotation.getZ(), 0.0f, 0.0f, 1.0f);
                if (this.rotateAngleZ != 0.0f) {
                    GL11.glRotatef(this.rotateAngleZ * 57.295776f, 0.0f, 0.0f, 1.0f);
                }
                if (this.rotateAngleY != 0.0f) {
                    GL11.glRotatef(this.rotateAngleY * 57.295776f, 0.0f, 1.0f, 0.0f);
                }
                if (this.rotateAngleX != 0.0f) {
                    GL11.glRotatef(this.rotateAngleX * 57.295776f, 1.0f, 0.0f, 0.0f);
                }
            }
        }
        this.seperatedModel.postRender(p_78794_1_);
        GL11.glTranslatef(-this.seperatedModel.rotationPointX * p_78794_1_, -this.seperatedModel.rotationPointY * p_78794_1_, -this.seperatedModel.rotationPointZ * p_78794_1_);
    }
}

