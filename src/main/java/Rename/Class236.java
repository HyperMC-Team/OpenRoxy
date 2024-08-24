package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.optifine.reflect.Reflector;

public final class Class236
implements Class3 {
    public static MovingObjectPosition rayCast(Class247 rotation, double range) {
        return Class236.rayCast(rotation, range, 0.0f);
    }

    public static MovingObjectPosition rayCast(Class247 rotation, double range, float expand) {
        return Class236.rayCast(rotation, range, expand, Class236.mc.thePlayer);
    }

    public static MovingObjectPosition raytraceLegit(float yaw, float pitch, float lastYaw, float lastPitch) {
        float partialTicks = Class236.mc.timer.renderPartialTicks;
        float blockReachDistance = Class236.mc.playerController.getBlockReachDistance();
        Vec3 vec3 = Class236.mc.thePlayer.getPositionEyes(partialTicks);
        float f = lastPitch + (pitch - lastPitch) * partialTicks;
        float f1 = lastYaw + (yaw - lastYaw) * partialTicks;
        Vec3 vec31 = Class236.mc.thePlayer.getVectorForRotation(f, f1);
        Vec3 vec32 = vec3.addVector(vec31.xCoord * (double)blockReachDistance, vec31.yCoord * (double)blockReachDistance, vec31.zCoord * (double)blockReachDistance);
        return Class236.mc.theWorld.rayTraceBlocks(vec3, vec32, false, false, true);
    }

    public static boolean rayCast(EntityLivingBase target, float yaw, float pitch, float lastYaw, float lastPitch, double reach) {
        Entity entity = mc.getRenderViewEntity();
        Entity pointedEntity = null;
        if (entity != null && Class236.mc.theWorld != null) {
            float partialTicks = Class236.mc.timer.renderPartialTicks;
            double d0 = Class236.mc.playerController.getBlockReachDistance();
            Class236.mc.objectMouseOver = Class236.raytraceLegit(yaw, pitch, lastYaw, lastPitch);
            double d1 = d0;
            Vec3 vec3 = entity.getPositionEyes(partialTicks);
            boolean flag = false;
            int i = 3;
            if (Class236.mc.playerController.extendedReach()) {
                d0 = 6.0;
                d1 = 6.0;
            } else if (d0 > reach) {
                flag = true;
            }
            if (Class236.mc.objectMouseOver != null) {
                d1 = Class236.mc.objectMouseOver.hitVec.distanceTo(vec3);
            }
            float aaaa = lastPitch + (pitch - lastPitch) * partialTicks;
            float bbbb = lastYaw + (yaw - lastYaw) * partialTicks;
            Vec3 vec31 = Class236.mc.thePlayer.getVectorForRotation(aaaa, bbbb);
            Vec3 vec32 = vec3.addVector(vec31.xCoord * d0, vec31.yCoord * d0, vec31.zCoord * d0);
            pointedEntity = null;
            Vec3 vec33 = null;
            float f = 1.0f;
            List<Entity> list = Class236.mc.theWorld.getEntitiesInAABBexcluding(entity, entity.getEntityBoundingBox().addCoord(vec31.xCoord * d0, vec31.yCoord * d0, vec31.zCoord * d0).expand(f, f, f), Predicates.and(EntitySelectors.NOT_SPECTATING, new Predicate<Entity>(){

                public boolean apply(Entity p_apply_1_) {
                    return p_apply_1_.canBeCollidedWith();
                }
            }));
            double d2 = d1;
            for (int j = 0; j < list.size(); ++j) {
                double d3;
                Entity entity1 = list.get(j);
                float f1 = entity1.getCollisionBorderSize();
                AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().expand(f1, f1, f1);
                MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(vec3, vec32);
                if (axisalignedbb.isVecInside(vec3)) {
                    if (!(d2 >= 0.0)) continue;
                    pointedEntity = entity1;
                    vec33 = movingobjectposition == null ? vec3 : movingobjectposition.hitVec;
                    d2 = 0.0;
                    continue;
                }
                if (movingobjectposition == null || !((d3 = vec3.distanceTo(movingobjectposition.hitVec)) < d2) && d2 != 0.0) continue;
                boolean flag1 = false;
                if (Reflector.ForgeEntity_canRiderInteract.exists()) {
                    flag1 = Reflector.callBoolean(entity1, Reflector.ForgeEntity_canRiderInteract);
                }
                if (!flag1 && entity1 == entity.ridingEntity) {
                    if (d2 != 0.0) continue;
                    pointedEntity = entity1;
                    vec33 = movingobjectposition.hitVec;
                    continue;
                }
                pointedEntity = entity1;
                vec33 = movingobjectposition.hitVec;
                d2 = d3;
            }
            if (pointedEntity != null && flag && vec3.distanceTo(vec33) > reach) {
                pointedEntity = null;
                Class236.mc.objectMouseOver = new MovingObjectPosition(MovingObjectPosition.MovingObjectType.MISS, vec33, null, new BlockPos(vec33));
            }
            if (pointedEntity != null && (d2 < d1 || Class236.mc.objectMouseOver == null)) {
                Class236.mc.objectMouseOver = new MovingObjectPosition(pointedEntity, vec33);
            }
        }
        return pointedEntity != null && pointedEntity == target;
    }

    public static MovingObjectPosition rayCast(Class247 rotation, double range, float expand, Entity entity) {
        float partialTicks = Class236.mc.timer.renderPartialTicks;
        if (entity != null && Class236.mc.theWorld != null) {
            MovingObjectPosition objectMouseOver = entity.rayTraceCustom(range, rotation.x, rotation.y);
            double d1 = range;
            Vec3 vec3 = entity.getPositionEyes(partialTicks);
            if (objectMouseOver != null) {
                d1 = objectMouseOver.hitVec.distanceTo(vec3);
            }
            Vec3 vec31 = Class236.mc.thePlayer.getVectorForRotation(rotation.y, rotation.x);
            Vec3 vec32 = vec3.addVector(vec31.xCoord * range, vec31.yCoord * range, vec31.zCoord * range);
            Entity pointedEntity = null;
            Vec3 vec33 = null;
            float f = 1.0f;
            List<Entity> list = Class236.mc.theWorld.getEntitiesInAABBexcluding(entity, entity.getEntityBoundingBox().addCoord(vec31.xCoord * range, vec31.yCoord * range, vec31.zCoord * range).expand(1.0, 1.0, 1.0), Predicates.and(EntitySelectors.NOT_SPECTATING, Entity::canBeCollidedWith));
            double d2 = d1;
            for (Entity entity1 : list) {
                double d3;
                float f1 = entity1.getCollisionBorderSize() + expand;
                AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().expand(f1, f1, f1);
                MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(vec3, vec32);
                if (axisalignedbb.isVecInside(vec3)) {
                    if (!(d2 >= 0.0)) continue;
                    pointedEntity = entity1;
                    vec33 = movingobjectposition == null ? vec3 : movingobjectposition.hitVec;
                    d2 = 0.0;
                    continue;
                }
                if (movingobjectposition == null || !((d3 = vec3.distanceTo(movingobjectposition.hitVec)) < d2) && d2 != 0.0) continue;
                pointedEntity = entity1;
                vec33 = movingobjectposition.hitVec;
                d2 = d3;
            }
            if (pointedEntity != null && (d2 < d1 || objectMouseOver == null)) {
                objectMouseOver = new MovingObjectPosition(pointedEntity, vec33);
            }
            return objectMouseOver;
        }
        return null;
    }

    public static boolean overBlock(Class247 rotation, EnumFacing enumFacing, BlockPos pos, boolean strict) {
        MovingObjectPosition movingObjectPosition = Class236.mc.thePlayer.rayTraceCustom(4.5, rotation.x, rotation.y);
        if (movingObjectPosition == null) {
            return false;
        }
        Vec3 hitVec = movingObjectPosition.hitVec;
        if (hitVec == null) {
            return false;
        }
        return movingObjectPosition.getBlockPos().equals(pos) && (!strict || movingObjectPosition.sideHit == enumFacing);
    }

    public static MovingObjectPosition blockInfo(Class247 rotation) {
        return Class236.mc.thePlayer.rayTraceCustom(4.5, rotation.x, rotation.y);
    }

    public static boolean overBlock(EnumFacing enumFacing, BlockPos pos, boolean strict) {
        MovingObjectPosition movingObjectPosition = Class236.mc.objectMouseOver;
        if (movingObjectPosition == null) {
            return false;
        }
        Vec3 hitVec = movingObjectPosition.hitVec;
        if (hitVec == null) {
            return false;
        }
        return movingObjectPosition.getBlockPos().equals(pos) && (!strict || movingObjectPosition.sideHit == enumFacing);
    }

    public static Entity raycastEntity(Double range, Float yaw, Float pitch) {
        Entity renderViewEntity = mc.getRenderViewEntity();
        if (renderViewEntity != null && Class236.mc.theWorld != null) {
            double blockReachDistance = range;
            Vec3 eyePosition = renderViewEntity.getPositionEyes(1.0f);
            float yawCos = MathHelper.cos(-yaw.floatValue() * ((float)Math.PI / 180) - (float)Math.PI);
            float yawSin = MathHelper.sin(-yaw.floatValue() * ((float)Math.PI / 180) - (float)Math.PI);
            float pitchCos = -MathHelper.cos(-pitch.floatValue() * ((float)Math.PI / 180));
            float pitchSin = MathHelper.sin(-pitch.floatValue() * ((float)Math.PI / 180));
            Vec3 entityLook = new Vec3(yawSin * pitchCos, pitchSin, yawCos * pitchCos);
            Vec3 vector = eyePosition.addVector(entityLook.xCoord * blockReachDistance, entityLook.yCoord * blockReachDistance, entityLook.zCoord * blockReachDistance);
            List<Entity> entityList = Class236.mc.theWorld.getEntitiesInAABBexcluding(renderViewEntity, renderViewEntity.getEntityBoundingBox().expand(entityLook.xCoord * blockReachDistance, entityLook.yCoord * blockReachDistance, entityLook.zCoord * blockReachDistance).expand(1.0, 1.0, 1.0), (Predicate<? super Entity>)(entity -> entity != null && (entity.getEntityId() != Class236.mc.thePlayer.getEntityId() || !((EntityPlayer)entity).isSpectator()) && entity.canBeCollidedWith()));
            Entity pointedEntity = null;
            for (Entity entity2 : entityList) {
                double eyeDistance;
                double collisionBorderSize = entity2.getCollisionBorderSize();
                AxisAlignedBB axisAlignedBB = entity2.getEntityBoundingBox().expand(collisionBorderSize, collisionBorderSize, collisionBorderSize);
                MovingObjectPosition movingObjectPosition = axisAlignedBB.calculateIntercept(eyePosition, vector);
                if (axisAlignedBB.isVecInside(eyePosition)) {
                    if (!(blockReachDistance >= 0.0)) continue;
                    pointedEntity = entity2;
                    blockReachDistance = 0.0;
                    continue;
                }
                if (movingObjectPosition == null || !((eyeDistance = eyePosition.distanceTo(movingObjectPosition.hitVec)) < blockReachDistance) && blockReachDistance != 0.0) continue;
                if (entity2 == renderViewEntity.ridingEntity && !renderViewEntity.canBeCollidedWith()) {
                    if (blockReachDistance != 0.0) continue;
                    pointedEntity = entity2;
                    continue;
                }
                pointedEntity = entity2;
                blockReachDistance = eyeDistance;
            }
            return pointedEntity;
        }
        return null;
    }

    private Class236() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

