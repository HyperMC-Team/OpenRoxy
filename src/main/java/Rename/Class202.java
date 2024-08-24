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
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.ReadableVector3f;
import org.lwjgl.util.vector.Vector3f;

public class Class202 {
    public List<TrailPart> trailPartList = new ArrayList<TrailPart>();

    public void reset() {
        this.trailPartList.clear();
    }

    public void render() {
        Minecraft.getMinecraft().getTextureManager().bindTexture(Class320.texture_NULL);
        GL11.glDepthFunc(515);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(2884);
        GL11.glDisable(2896);
        GL11.glHint(3152, 4354);
        GL11.glShadeModel(7425);
        GL11.glPushMatrix();
        GL11.glBegin(7);
        for (int i = 0; i < this.trailPartList.size(); ++i) {
            TrailPart part = this.trailPartList.get(i);
            float alpha = part.ticksExisted / 5.0f;
            alpha = Class328.max(alpha, 1.0f);
            alpha = 1.0f - alpha;
            GL11.glColor4f(1.0f, 1.0f, 1.0f, alpha);
            Vector3f[] point = new Vector3f[]{new Vector3f(0.0f, 0.0f, -8.0f + 8.0f * alpha), new Vector3f(0.0f, 0.0f, -8.0f - 8.0f * alpha)};
            Class328.rotateX(point, part.itemRotation.getX());
            Class328.rotateY(point, part.itemRotation.getY());
            Class328.rotateZ(point, part.itemRotation.getZ());
            Class328.translate(point, new Vector3f(-1.0f, -6.0f, 0.0f));
            Class328.rotateX(point, part.foreArm.rotateAngleX / (float)Math.PI * 180.0f);
            Class328.rotateY(point, part.foreArm.rotateAngleY / (float)Math.PI * 180.0f);
            Class328.rotateZ(point, part.foreArm.rotateAngleZ / (float)Math.PI * 180.0f);
            Class328.rotateX(point, part.foreArm.pre_rotation.getX());
            Class328.rotateY(point, part.foreArm.pre_rotation.getY());
            Class328.rotateZ(point, -part.foreArm.pre_rotation.getZ());
            Class328.translate(point, new Vector3f(0.0f, -4.0f, 0.0f));
            Class328.rotateX(point, part.arm.rotateAngleX / (float)Math.PI * 180.0f);
            Class328.rotateY(point, part.arm.rotateAngleY / (float)Math.PI * 180.0f);
            Class328.rotateZ(point, part.arm.rotateAngleZ / (float)Math.PI * 180.0f);
            Class328.rotateX(point, part.arm.pre_rotation.getX());
            Class328.rotateY(point, part.arm.pre_rotation.getY());
            Class328.rotateZ(point, -part.arm.pre_rotation.getZ());
            Class328.translate(point, new Vector3f(-5.0f, 10.0f, 0.0f));
            Class328.rotateX(point, part.body.rotateAngleX / (float)Math.PI * 180.0f);
            Class328.rotateY(point, part.body.rotateAngleY / (float)Math.PI * 180.0f);
            Class328.rotateZ(point, part.body.rotateAngleZ / (float)Math.PI * 180.0f);
            Class328.rotateX(point, part.body.pre_rotation.getX());
            Class328.rotateY(point, part.body.pre_rotation.getY());
            Class328.rotateZ(point, part.body.pre_rotation.getZ());
            Class328.translate(point, new Vector3f(0.0f, 12.0f, 0.0f));
            Class328.rotateX(point, part.renderRotation.getX());
            Class328.rotateY(point, part.renderRotation.getY());
            Class328.translate(point, part.renderOffset);
            if (i > 0) {
                GL11.glVertex3f(point[1].x, point[1].y, point[1].z);
                GL11.glVertex3f(point[0].x, point[0].y, point[0].z);
                GL11.glVertex3f(point[0].x, point[0].y, point[0].z);
                GL11.glVertex3f(point[1].x, point[1].y, point[1].z);
            } else {
                GL11.glVertex3f(point[0].x, point[0].y, point[0].z);
                GL11.glVertex3f(point[1].x, point[1].y, point[1].z);
            }
            if (i != this.trailPartList.size() - 1) continue;
            GL11.glVertex3f(point[1].x, point[1].y, point[1].z);
            GL11.glVertex3f(point[0].x, point[0].y, point[0].z);
        }
        GL11.glEnd();
        GL11.glPopMatrix();
        GL11.glEnable(3553);
        GL11.glDisable(2884);
        GL11.glEnable(2896);
    }

    public void add(Class193 argModel) {
        TrailPart newPart = new TrailPart(this, argModel);
        newPart.body.sync((Class196)argModel.bipedBody);
        newPart.body.setPosition(argModel.bipedBody.rotationPointX, argModel.bipedBody.rotationPointY, argModel.bipedBody.rotationPointZ);
        newPart.body.setOffset(argModel.bipedBody.offsetX, argModel.bipedBody.offsetY, argModel.bipedBody.offsetZ);
        newPart.arm.sync((Class196)argModel.bipedRightArm);
        newPart.arm.setPosition(argModel.bipedRightArm.rotationPointX, argModel.bipedRightArm.rotationPointY, argModel.bipedRightArm.rotationPointZ);
        newPart.arm.setOffset(argModel.bipedRightArm.offsetX, argModel.bipedRightArm.offsetY, argModel.bipedRightArm.offsetZ);
        newPart.foreArm.sync(argModel.bipedRightForeArm);
        newPart.foreArm.setPosition(argModel.bipedRightForeArm.rotationPointX, argModel.bipedRightForeArm.rotationPointY, argModel.bipedRightForeArm.rotationPointZ);
        newPart.foreArm.setOffset(argModel.bipedRightForeArm.offsetX, argModel.bipedRightForeArm.offsetY, argModel.bipedRightForeArm.offsetZ);
        newPart.renderOffset.set(argModel.renderOffset.vSmooth);
        newPart.renderRotation.set(argModel.renderRotation.vSmooth);
        newPart.itemRotation.set(argModel.renderItemRotation.vSmooth);
        this.trailPartList.add(newPart);
    }

    public void update(float argPartialTicks) {
        int i;
        for (i = 0; i < this.trailPartList.size(); ++i) {
            this.trailPartList.get(i).ticksExisted += argPartialTicks;
        }
        for (i = 0; i < this.trailPartList.size(); ++i) {
            if (!(this.trailPartList.get(i).ticksExisted > 20.0f)) continue;
            this.trailPartList.remove(this.trailPartList.get(i));
        }
    }

    public class TrailPart {
        public Class196 body;
        public Class196 arm;
        public Class196 foreArm;
        public Vector3f renderRotation = new Vector3f();
        public Vector3f renderOffset = new Vector3f();
        public Vector3f itemRotation = new Vector3f();
        float ticksExisted;

        public TrailPart(Class202 this$0, Class193 argModel) {
            this.body = new Class196(argModel);
            this.arm = new Class196(argModel);
            this.foreArm = new Class196(argModel);
        }
    }
}

