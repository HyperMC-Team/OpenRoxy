package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemFishingRod;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSnowball;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Cylinder;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
public class Class324
extends Class252 {
    float yaw;
    float pitch;
    float f;

    public Class324() {
        super("Projection", Class263.Render);
    }

    @Class14
    public void onMotion(Class27 e) {
        this.yaw = e.getYaw();
        this.pitch = e.getPitch();
    }

    @Class14
    public void onR3D(Class39 e) {
        boolean isBow = false;
        float pitchDifference = 0.0f;
        float motionFactor = 1.5f;
        float motionSlowdown = 0.99f;
        if (Class324.mc.thePlayer.getCurrentEquippedItem() != null) {
            float size;
            float gravity;
            Item heldItem = Class324.mc.thePlayer.getCurrentEquippedItem().getItem();
            if (heldItem instanceof ItemBow) {
                isBow = true;
                gravity = 0.05f;
                size = 0.3f;
                float power = (float) Class324.mc.thePlayer.getItemInUseDuration() / 20.0f;
                power = (power * power + power * 2.0f) / 3.0f;
                if ((double)f < 0.1) {
                    return;
                }
                if (power > 1.0f) {
                    power = 1.0f;
                }
                motionFactor = power * 3.0f;
            } else if (heldItem instanceof ItemFishingRod) {
                gravity = 0.04f;
                size = 0.25f;
                motionSlowdown = 0.92f;
            } else if (ItemPotion.isSplash(Class324.mc.thePlayer.getCurrentEquippedItem().getMetadata())) {
                gravity = 0.05f;
                size = 0.25f;
                pitchDifference = -20.0f;
                motionFactor = 0.5f;
            } else {
                if (!(heldItem instanceof ItemSnowball || heldItem instanceof ItemEnderPearl || heldItem instanceof ItemEgg || heldItem.equals(Item.getItemById(46)))) {
                    return;
                }
                gravity = 0.03f;
                size = 0.25f;
            }
            double posX = RenderManager.renderPosX - (double)(MathHelper.cos(this.yaw / 180.0f * (float)Math.PI) * 0.16f);
            double posY = RenderManager.renderPosY + (double) Class324.mc.thePlayer.getEyeHeight() - (double)0.1f;
            double posZ = RenderManager.renderPosZ - (double)(MathHelper.sin(this.yaw / 180.0f * (float)Math.PI) * 0.16f);
            double motionX = (double)(-MathHelper.sin(this.yaw / 180.0f * (float)Math.PI) * MathHelper.cos(this.pitch / 180.0f * (float)Math.PI)) * (isBow ? 1.0 : 0.4);
            double motionY = (double)(-MathHelper.sin((this.pitch + pitchDifference) / 180.0f * (float)Math.PI)) * (isBow ? 1.0 : 0.4);
            double motionZ = (double)(MathHelper.cos(this.yaw / 180.0f * (float)Math.PI) * MathHelper.cos(this.pitch / 180.0f * (float)Math.PI)) * (isBow ? 1.0 : 0.4);
            float distance = MathHelper.sqrt_double(motionX * motionX + motionY * motionY + motionZ * motionZ);
            motionX /= distance;
            motionY /= distance;
            motionZ /= distance;
            motionX *= motionFactor;
            motionY *= motionFactor;
            motionZ *= motionFactor;
            MovingObjectPosition landingPosition = null;
            boolean hasLanded = false;
            boolean hitEntity = false;
            Class465.enableRender3D(true);
            Class465.color(new Color(145, 222, 0, 243).getRGB());
            GL11.glLineWidth(2.0f);
            GL11.glBegin(3);
            while (!hasLanded && posY > 0.0) {
                Vec3 posBefore = new Vec3(posX, posY, posZ);
                Vec3 posAfter = new Vec3(posX + motionX, posY + motionY, posZ + motionZ);
                landingPosition = Class324.mc.theWorld.rayTraceBlocks(posBefore, posAfter, false, true, false);
                posBefore = new Vec3(posX, posY, posZ);
                posAfter = new Vec3(posX + motionX, posY + motionY, posZ + motionZ);
                if (landingPosition != null) {
                    hasLanded = true;
                    posAfter = new Vec3(landingPosition.hitVec.xCoord, landingPosition.hitVec.yCoord, landingPosition.hitVec.zCoord);
                }
                AxisAlignedBB arrowBox = new AxisAlignedBB(posX - (double)size, posY - (double)size, posZ - (double)size, posX + (double)size, posY + (double)size, posZ + (double)size);
                List<?> entityList = this.getEntitiesWithinAABB(arrowBox.addCoord(motionX, motionY, motionZ).expand(1.0, 1.0, 1.0));
                for (Object o : entityList) {
                    MovingObjectPosition possibleEntityLanding;
                    Entity var18 = (Entity)o;
                    if (!var18.canBeCollidedWith() || var18 == Class324.mc.thePlayer || (possibleEntityLanding = var18.getEntityBoundingBox().expand(size, size, size).calculateIntercept(posBefore, posAfter)) == null) continue;
                    hitEntity = true;
                    hasLanded = true;
                    landingPosition = possibleEntityLanding;
                }
                BlockPos var20 = new BlockPos(posX += motionX, posY += motionY, posZ += motionZ);
                Block var21 = Class324.mc.theWorld.getBlockState(var20).getBlock();
                if (var21.getMaterial() == Material.water) {
                    motionX *= 0.6;
                    motionY *= 0.6;
                    motionZ *= 0.6;
                } else {
                    motionX *= motionSlowdown;
                    motionY *= motionSlowdown;
                    motionZ *= motionSlowdown;
                }
                motionY -= gravity;
                GL11.glVertex3d(posX - RenderManager.renderPosX, posY - RenderManager.renderPosY, posZ - RenderManager.renderPosZ);
            }
            GL11.glEnd();
            GL11.glPushMatrix();
            GL11.glTranslated(posX - RenderManager.renderPosX, posY - RenderManager.renderPosY, posZ - RenderManager.renderPosZ);
            if (landingPosition != null) {
                int side = landingPosition.sideHit.getIndex();
                if (side == 1 && heldItem instanceof ItemEnderPearl) {
                    Class465.color(new Color(35, 218, 255, 255).getRGB());
                } else if (side == 2) {
                    GlStateManager.rotate(90.0f, 1.0f, 0.0f, 0.0f);
                } else if (side == 3) {
                    GlStateManager.rotate(90.0f, 1.0f, 0.0f, 0.0f);
                } else if (side == 4) {
                    GlStateManager.rotate(90.0f, 0.0f, 0.0f, 1.0f);
                } else if (side == 5) {
                    GlStateManager.rotate(90.0f, 0.0f, 0.0f, 1.0f);
                }
                if (hitEntity) {
                    Class465.color(new Color(35, 218, 255, 255).getRGB());
                }
            }
            this.renderPoint();
            GL11.glPopMatrix();
            Class465.disableRender3D(true);
        }
    }

    private void renderPoint() {
        GL11.glBegin(1);
        GL11.glVertex3d(-0.5, 0.0, 0.0);
        GL11.glVertex3d(0.0, 0.0, 0.0);
        GL11.glVertex3d(0.0, 0.0, -0.5);
        GL11.glVertex3d(0.0, 0.0, 0.0);
        GL11.glVertex3d(0.5, 0.0, 0.0);
        GL11.glVertex3d(0.0, 0.0, 0.0);
        GL11.glVertex3d(0.0, 0.0, 0.5);
        GL11.glVertex3d(0.0, 0.0, 0.0);
        GL11.glEnd();
        Cylinder c = new Cylinder();
        GlStateManager.rotate(90.0f, 1.0f, 0.0f, 0.0f);
        c.setDrawStyle(100011);
        c.draw(0.5f, 0.5f, 0.0f, 256, 27);
    }

    private List<?> getEntitiesWithinAABB(AxisAlignedBB axisalignedBB) {
        ArrayList<Entity> list = new ArrayList<Entity>();
        int chunkMinX = MathHelper.floor_double((axisalignedBB.minX - 2.0) / 16.0);
        int chunkMaxX = MathHelper.floor_double((axisalignedBB.maxX + 2.0) / 16.0);
        int chunkMinZ = MathHelper.floor_double((axisalignedBB.minZ - 2.0) / 16.0);
        int chunkMaxZ = MathHelper.floor_double((axisalignedBB.maxZ + 2.0) / 16.0);
        for (int x = chunkMinX; x <= chunkMaxX; ++x) {
            for (int z = chunkMinZ; z <= chunkMaxZ; ++z) {
                if (!Class324.mc.theWorld.getChunkProvider().chunkExists(x, z)) continue;
                Class324.mc.theWorld.getChunkFromChunkCoords(x, z).getEntitiesWithinAABBForEntity(Class324.mc.thePlayer, axisalignedBB, list, EntitySelectors.selectAnything);
            }
        }
        return list;
    }
}

