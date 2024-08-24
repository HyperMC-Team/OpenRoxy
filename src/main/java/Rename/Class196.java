package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.ReadableVector3f;

public class Class196
extends ModelRenderer {
    public Class329 rotation = new Class329();
    public Class329 pre_rotation = new Class329();
    public float scaleX;
    public float scaleY;
    public float scaleZ;
    public int txOffsetX;
    public int txOffsetY;
    public boolean compiled;
    private int displayList;
    public boolean showChildIfHidden = false;

    public Class196(ModelBase argModel) {
        super(argModel);
        this.scaleZ = 1.0f;
        this.scaleY = 1.0f;
        this.scaleX = 1.0f;
    }

    public Class196(ModelBase argModel, String argName) {
        super(argModel, argName);
        this.scaleZ = 1.0f;
        this.scaleY = 1.0f;
        this.scaleX = 1.0f;
    }

    public Class196(ModelBase argModel, int argTexOffsetX, int argTexOffsetY) {
        super(argModel, argTexOffsetX, argTexOffsetY);
        this.txOffsetX = argTexOffsetX;
        this.txOffsetY = argTexOffsetY;
        this.scaleZ = 1.0f;
        this.scaleY = 1.0f;
        this.scaleX = 1.0f;
    }

    public void updateBends(float argTicksPerFrame) {
        this.rotateAngleX = (float)((double)(this.rotation.getX() / 180.0f) * Math.PI);
        this.rotateAngleY = (float)((double)(this.rotation.getY() / 180.0f) * Math.PI);
        this.rotateAngleZ = (float)((double)(this.rotation.getZ() / 180.0f) * Math.PI);
    }

    public Class196 setShowChildIfHidden(boolean arg0) {
        this.showChildIfHidden = arg0;
        return this;
    }

    public void compileDisplayList(float p_78788_1_) {
        this.displayList = GLAllocation.generateDisplayLists(1);
        GL11.glNewList(this.displayList, 4864);
        WorldRenderer worldrenderer = Tessellator.getInstance().getWorldRenderer();
        for (int i = 0; i < this.cubeList.size(); ++i) {
            this.cubeList.get(i).render(worldrenderer, p_78788_1_);
        }
        GL11.glEndList();
        this.compiled = true;
    }

    @Override
    public void render(float p_78785_1_) {
        this.updateBends(p_78785_1_);
        if (!this.compiled) {
            this.compileDisplayList(p_78785_1_);
        }
        GL11.glTranslatef(this.offsetX, this.offsetY, this.offsetZ);
        if (this.rotateAngleX == 0.0f && this.rotateAngleY == 0.0f && this.rotateAngleZ == 0.0f) {
            if (this.rotationPointX == 0.0f && this.rotationPointY == 0.0f && this.rotationPointZ == 0.0f) {
                GL11.glRotatef(-this.pre_rotation.getY(), 0.0f, 1.0f, 0.0f);
                GL11.glRotatef(this.pre_rotation.getX(), 1.0f, 0.0f, 0.0f);
                GL11.glRotatef(this.pre_rotation.getZ(), 0.0f, 0.0f, 1.0f);
                GL11.glScalef(this.scaleX, this.scaleY, this.scaleZ);
                if (!this.isHidden & this.showModel) {
                    GL11.glCallList(this.displayList);
                }
                if ((this.showChildIfHidden || !this.isHidden & this.showModel) && this.childModels != null) {
                    for (int i = 0; i < this.childModels.size(); ++i) {
                        this.childModels.get(i).render(p_78785_1_);
                    }
                }
            } else {
                GL11.glTranslatef(this.rotationPointX * p_78785_1_, this.rotationPointY * p_78785_1_, this.rotationPointZ * p_78785_1_);
                GL11.glRotatef(-this.pre_rotation.getY(), 0.0f, 1.0f, 0.0f);
                GL11.glRotatef(this.pre_rotation.getX(), 1.0f, 0.0f, 0.0f);
                GL11.glRotatef(this.pre_rotation.getZ(), 0.0f, 0.0f, 1.0f);
                GL11.glScalef(this.scaleX, this.scaleY, this.scaleZ);
                if (!this.isHidden & this.showModel) {
                    GL11.glCallList(this.displayList);
                }
                if ((this.showChildIfHidden || !this.isHidden & this.showModel) && this.childModels != null) {
                    for (int i = 0; i < this.childModels.size(); ++i) {
                        this.childModels.get(i).render(p_78785_1_);
                    }
                }
                GL11.glTranslatef(-this.rotationPointX * p_78785_1_, -this.rotationPointY * p_78785_1_, -this.rotationPointZ * p_78785_1_);
            }
        } else {
            GL11.glPushMatrix();
            GL11.glTranslatef(this.rotationPointX * p_78785_1_, this.rotationPointY * p_78785_1_, this.rotationPointZ * p_78785_1_);
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
            GL11.glScalef(this.scaleX, this.scaleY, this.scaleZ);
            if (!this.isHidden & this.showModel) {
                GL11.glCallList(this.displayList);
            }
            if ((this.showChildIfHidden || !this.isHidden & this.showModel) && this.childModels != null) {
                for (int i = 0; i < this.childModels.size(); ++i) {
                    this.childModels.get(i).render(p_78785_1_);
                }
            }
            GL11.glPopMatrix();
        }
        GL11.glTranslatef(-this.offsetX, -this.offsetY, -this.offsetZ);
    }

    public void update(float p_78785_1_) {
        this.rotation.update(p_78785_1_);
        this.pre_rotation.update(p_78785_1_);
        this.updateBends(p_78785_1_);
    }

    @Override
    public void renderWithRotation(float p_78791_1_) {
        this.updateBends(p_78791_1_);
        super.renderWithRotation(p_78791_1_);
    }

    @Override
    public void postRender(float p_78794_1_) {
        this.updateBends(p_78794_1_);
        if (this.rotateAngleX == 0.0f && this.rotateAngleY == 0.0f && this.rotateAngleZ == 0.0f) {
            if (this.rotationPointX != 0.0f || this.rotationPointY != 0.0f || this.rotationPointZ != 0.0f) {
                GL11.glTranslatef(this.rotationPointX * p_78794_1_, this.rotationPointY * p_78794_1_, this.rotationPointZ * p_78794_1_);
                GL11.glRotatef(-this.pre_rotation.getY(), 0.0f, 1.0f, 0.0f);
                GL11.glRotatef(this.pre_rotation.getX(), 1.0f, 0.0f, 0.0f);
                GL11.glRotatef(this.pre_rotation.getZ(), 0.0f, 0.0f, 1.0f);
                GL11.glScalef(this.scaleX, this.scaleY, this.scaleZ);
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
            GL11.glScalef(this.scaleX, this.scaleY, this.scaleZ);
        }
    }

    public Class196 setPosition(float argX, float argY, float argZ) {
        this.rotationPointX = argX;
        this.rotationPointY = argY;
        this.rotationPointZ = argZ;
        return this;
    }

    public Class196 setOffset(float argX, float argY, float argZ) {
        this.offsetX = argX;
        this.offsetY = argY;
        this.offsetZ = argZ;
        return this;
    }

    public Class196 setScale(float argX, float argY, float argZ) {
        this.scaleX = argX;
        this.scaleY = argY;
        this.scaleZ = argZ;
        return this;
    }

    public Class196 resetScale() {
        this.scaleZ = 1.0f;
        this.scaleY = 1.0f;
        this.scaleX = 1.0f;
        return this;
    }

    public void sync(Class196 argBox) {
        if (argBox != null) {
            this.rotateAngleX = argBox.rotateAngleX;
            this.rotateAngleY = argBox.rotateAngleY;
            this.rotateAngleZ = argBox.rotateAngleZ;
            this.rotation.vOld.set(argBox.rotation.vOld);
            this.rotation.completion.set(argBox.rotation.completion);
            this.rotation.vFinal.set(argBox.rotation.vFinal);
            this.rotation.vSmooth.set(argBox.rotation.vSmooth);
            this.rotation.smoothness.set(argBox.rotation.smoothness);
            this.pre_rotation.vOld.set(argBox.pre_rotation.vOld);
            this.pre_rotation.completion.set(argBox.pre_rotation.completion);
            this.pre_rotation.vFinal.set(argBox.pre_rotation.vFinal);
            this.pre_rotation.vSmooth.set(argBox.pre_rotation.vSmooth);
            this.pre_rotation.smoothness.set(argBox.pre_rotation.smoothness);
            this.scaleX = argBox.scaleX;
            this.scaleY = argBox.scaleY;
            this.scaleZ = argBox.scaleZ;
        }
    }

    @Override
    public void addBox(float p_78790_1_, float p_78790_2_, float p_78790_3_, int p_78790_4_, int p_78790_5_, int p_78790_6_, float p_78790_7_) {
        this.cubeList.add(new Class195(this, this.txOffsetX, this.txOffsetY, p_78790_1_, p_78790_2_, p_78790_3_, p_78790_4_, p_78790_5_, p_78790_6_, p_78790_7_));
    }

    public Class195 getBox() {
        return (Class195)this.cubeList.get(0);
    }

    public Class196 offsetBox(float argX, float argY, float argZ) {
        this.getBox().offset(argX, argY, argZ);
        return this;
    }

    public Class196 offsetBox_Add(float argX, float argY, float argZ) {
        this.getBox().offset_add(argX, argY, argZ);
        return this;
    }

    public Class196 resizeBox(float argX, float argY, float argZ) {
        this.getBox().resize(argX, argY, argZ);
        return this;
    }

    public Class196 updateVertices() {
        this.getBox().updateVertexPositions(this);
        this.compiled = false;
        return this;
    }
}

