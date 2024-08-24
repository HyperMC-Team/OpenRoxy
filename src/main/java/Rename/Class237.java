package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import tech.skidonion.obfuscator.annotations.StringEncryption;

@StringEncryption
public final class Class237
implements Class3 {
    public static Class247 calculate(Class249 from, Class249 to) {
        Class249 diff = to.subtract(from);
        double distance = Math.hypot(diff.getX(), diff.getZ());
        float yaw = (float)(MathHelper.atan2(diff.getZ(), diff.getX()) * 57.2957763671875) - 90.0f;
        float pitch = (float)(-(MathHelper.atan2(diff.getY(), distance) * 57.2957763671875));
        return new Class247(yaw, pitch);
    }

    public static Class247 calculate(Entity entity) {
        return Class237.calculate(entity.getCustomPositionVector().add(0.0, Math.max(0.0, Math.min(Class237.mc.thePlayer.posY - entity.posY + (double) Class237.mc.thePlayer.getEyeHeight(), (entity.getEntityBoundingBox().maxY - entity.getEntityBoundingBox().minY) * 0.9)), 0.0));
    }

    public static Class247 getTestRotation(Entity target) {
        double yDist = target.posY - Class237.mc.thePlayer.posY;
        Vec3 pos = yDist >= 1.7 ? new Vec3(target.posX, target.posY, target.posZ) : (yDist <= -1.7 ? new Vec3(target.posX, target.posY + (double)target.getEyeHeight(), target.posZ) : new Vec3(target.posX, target.posY + (double)(target.getEyeHeight() / 2.0f), target.posZ));
        Vec3 vec = new Vec3(Class237.mc.thePlayer.posX, Class237.mc.thePlayer.getEntityBoundingBox().minY + (double) Class237.mc.thePlayer.getEyeHeight(), Class237.mc.thePlayer.posZ);
        double xDist = pos.xCoord - vec.xCoord;
        double yDist2 = pos.yCoord - vec.yCoord;
        double zDist = pos.zCoord - vec.zCoord;
        float yaw = (float)Math.toDegrees(Math.atan2(zDist, xDist)) - 90.0f;
        float pitch = (float)(-Math.toDegrees(Math.atan2(yDist2, Math.sqrt(xDist * xDist + zDist * zDist))));
        return new Class247(yaw, Math.min(Math.max(pitch, -90.0f), 90.0f));
    }

    public static Class247 getHVHRotation(Entity entity) {
        if (entity == null) {
            return null;
        }
        double diffX = entity.posX - Class237.mc.thePlayer.posX;
        double diffZ = entity.posZ - Class237.mc.thePlayer.posZ;
        Vec3 BestPos = Class237.getNearestPointBB(Class237.mc.thePlayer.getPositionEyes(1.0f), entity.getEntityBoundingBox());
        Class235 myEyePos = new Class235(Class237.mc.thePlayer.posX, Class237.mc.thePlayer.posY + (double) Class237.mc.thePlayer.getEyeHeight(), Class237.mc.thePlayer.posZ);
        double diffY = BestPos.yCoord - myEyePos.getY();
        double dist = MathHelper.sqrt_double(diffX * diffX + diffZ * diffZ);
        float yaw = (float)(Math.atan2(diffZ, diffX) * 180.0 / Math.PI) - 90.0f;
        float pitch = (float)(-(Math.atan2(diffY, dist) * 180.0 / Math.PI));
        return new Class247(yaw, pitch);
    }

    public static Vec3 getNearestPointBB(Vec3 eye, AxisAlignedBB box) {
        double[] origin = new double[]{eye.xCoord, eye.yCoord, eye.zCoord};
        double[] destMins = new double[]{box.minX, box.minY, box.minZ};
        double[] destMaxs = new double[]{box.maxX, box.maxY, box.maxZ};
        for (int i = 0; i < 3; ++i) {
            if (origin[i] > destMaxs[i]) {
                origin[i] = destMaxs[i];
                continue;
            }
            if (!(origin[i] < destMins[i])) continue;
            origin[i] = destMins[i];
        }
        return new Vec3(origin[0], origin[1], origin[2]);
    }

    public static Class247 getRotations(Entity entity) {
        double pX = Minecraft.getMinecraft().thePlayer.posX;
        double pY = Minecraft.getMinecraft().thePlayer.posY + (double)Minecraft.getMinecraft().thePlayer.getEyeHeight();
        double pZ = Minecraft.getMinecraft().thePlayer.posZ;
        double eX = entity.posX;
        double eY = entity.posY + (double)(entity.height / 2.0f);
        double eZ = entity.posZ;
        double dX = pX - eX;
        double dY = pY - eY;
        double dZ = pZ - eZ;
        double dH = Math.sqrt(Math.pow(dX, 2.0) + Math.pow(dZ, 2.0));
        double yaw = Math.toDegrees(Math.atan2(dZ, dX)) + 90.0;
        double pitch = Math.toDegrees(Math.atan2(dH, dY));
        return new Class247((float)yaw, (float)(90.0 - pitch));
    }

    public static Class247 getRotations(Entity target, Entity player) {
        double pX = player.posX;
        double pY = player.posY + (double)player.getEyeHeight();
        double pZ = player.posZ;
        double eX = target.posX;
        double eY = target.posY + (double)(target.height / 2.0f);
        double eZ = target.posZ;
        double dX = pX - eX;
        double dY = pY - eY;
        double dZ = pZ - eZ;
        double dH = Math.sqrt(Math.pow(dX, 2.0) + Math.pow(dZ, 2.0));
        double yaw = Math.toDegrees(Math.atan2(dZ, dX)) + 90.0;
        double pitch = Math.toDegrees(Math.atan2(dH, dY));
        return new Class247((float)yaw, (float)(90.0 - pitch));
    }

    public static Class247 calculate(Entity entity, boolean adaptive, double range) {
        Class247 normalRotations = Class237.calculate(entity);
        if (!adaptive || Class236.rayCast(normalRotations, range).typeOfHit == MovingObjectPosition.MovingObjectType.ENTITY) {
            return normalRotations;
        }
        for (double yPercent = 1.0; yPercent >= 0.0; yPercent -= 0.25) {
            for (double xPercent = 1.0; xPercent >= -0.5; xPercent -= 0.5) {
                for (double zPercent = 1.0; zPercent >= -0.5; zPercent -= 0.5) {
                    Class247 adaptiveRotations = Class237.calculate(entity.getCustomPositionVector().add((entity.getEntityBoundingBox().maxX - entity.getEntityBoundingBox().minX) * xPercent, (entity.getEntityBoundingBox().maxY - entity.getEntityBoundingBox().minY) * yPercent, (entity.getEntityBoundingBox().maxZ - entity.getEntityBoundingBox().minZ) * zPercent));
                    if (Class236.rayCast(adaptiveRotations, range).typeOfHit != MovingObjectPosition.MovingObjectType.ENTITY) continue;
                    return adaptiveRotations;
                }
            }
        }
        return normalRotations;
    }

    public static Class247 getThrowRotation(Entity entity, double maxRange) {
        if (entity == null) {
            return null;
        }
        Minecraft mc = Minecraft.getMinecraft();
        double deltaX = entity.posX - mc.thePlayer.posX - mc.thePlayer.motionX;
        double deltaY = entity.posY + (double)entity.getEyeHeight() - (mc.thePlayer.posY + (double)mc.thePlayer.getEyeHeight());
        double deltaZ = entity.posZ - mc.thePlayer.posZ - mc.thePlayer.motionZ;
        double horizontalDistance = MathHelper.sqrt_double(deltaX * deltaX + deltaZ * deltaZ);
        float yaw = (float)(Math.atan2(deltaZ, deltaX) * 180.0 / Math.PI) - 90.0f;
        float pitch = (float)(-(Math.atan2(deltaY, horizontalDistance) * 180.0 / Math.PI));
        float finalYaw = mc.thePlayer.rotationYaw + MathHelper.wrapAngleTo180_float(yaw - mc.thePlayer.rotationYaw);
        float finalPitch = mc.thePlayer.rotationPitch + MathHelper.wrapAngleTo180_float(pitch - mc.thePlayer.rotationPitch);
        return new Class247(finalYaw, finalPitch);
    }

    public static Class247 toRotation(Vec3 vec, boolean predict) {
        Vec3 eyesPos = new Vec3(Class237.mc.thePlayer.posX, Class237.mc.thePlayer.getEntityBoundingBox().minY + (double) Class237.mc.thePlayer.getEyeHeight(), Class237.mc.thePlayer.posZ);
        if (predict) {
            eyesPos.addVector(Class237.mc.thePlayer.motionX, Class237.mc.thePlayer.motionY, Class237.mc.thePlayer.motionZ);
        }
        double diffX = vec.xCoord - eyesPos.xCoord;
        double diffY = vec.yCoord - eyesPos.yCoord;
        double diffZ = vec.zCoord - eyesPos.zCoord;
        return new Class247(MathHelper.wrapAngleTo180_float((float)Math.toDegrees(Math.atan2(diffZ, diffX)) - 90.0f), MathHelper.wrapAngleTo180_float((float)(-Math.toDegrees(Math.atan2(diffY, Math.sqrt(diffX * diffX + diffZ * diffZ))))));
    }

    public static Vec3 getCenter(AxisAlignedBB bb) {
        return new Vec3(bb.minX + (bb.maxX - bb.minX) * 0.5, bb.minY + (bb.maxY - bb.minY) * 0.5, bb.minZ + (bb.maxZ - bb.minZ) * 0.5);
    }

    public static double getRotationDifference(Class247 a, Class247 b2) {
        return Math.hypot(Class237.getAngleDifference(a.getX(), b2.getX()), a.getY() - b2.getY());
    }

    public static float getAngleDifference(float a, float b2) {
        return ((a - b2) % 360.0f + 540.0f) % 360.0f - 180.0f;
    }

    public static double getRotationDifference(Entity entity) {
        Class247 rotation = Class237.toRotation(Class237.getCenter(entity.getEntityBoundingBox()), true);
        return Class237.getRotationDifference(rotation, new Class247(Class237.mc.thePlayer.rotationYaw, Class237.mc.thePlayer.rotationPitch));
    }

    public static Class247 calculate(Vec3 to, EnumFacing enumFacing) {
        return Class237.calculate(new Class249(to.xCoord, to.yCoord, to.zCoord), enumFacing);
    }

    public static Class247 calculate(Vec3 to) {
        return Class237.calculate(Class237.mc.thePlayer.getCustomPositionVector().add(0.0, Class237.mc.thePlayer.getEyeHeight(), 0.0), new Class249(to.xCoord, to.yCoord, to.zCoord));
    }

    public static Class247 calculate(Class249 to) {
        return Class237.calculate(Class237.mc.thePlayer.getCustomPositionVector().add(0.0, Class237.mc.thePlayer.getEyeHeight(), 0.0), to);
    }

    public static Class247 calculate(Class249 position, EnumFacing enumFacing) {
        double x = position.getX() + 0.5;
        double y = position.getY() + 0.5;
        double z = position.getZ() + 0.5;
        return Class237.calculate(new Class249(x += (double)enumFacing.getDirectionVec().getX() * 0.5, y += (double)enumFacing.getDirectionVec().getY() * 0.5, z += (double)enumFacing.getDirectionVec().getZ() * 0.5));
    }

    public static Class247 applySensitivityPatch(Class247 rotation) {
        Class247 previousRotation = Class237.mc.thePlayer.getPreviousRotation();
        float mouseSensitivity = (float)((double) Class237.mc.gameSettings.mouseSensitivity * (1.0 + Math.random() / 1.0E7) * (double)0.6f + (double)0.2f);
        double multiplier = (double)(mouseSensitivity * mouseSensitivity * mouseSensitivity * 8.0f) * 0.15;
        float yaw = previousRotation.x + (float)((double)Math.round((double)(rotation.x - previousRotation.x) / multiplier) * multiplier);
        float pitch = previousRotation.y + (float)((double)Math.round((double)(rotation.y - previousRotation.y) / multiplier) * multiplier);
        return new Class247(yaw, MathHelper.clamp_float(pitch, -90.0f, 90.0f));
    }

    public static Class247 applySensitivityPatch(Class247 rotation, Class247 previousRotation) {
        float mouseSensitivity = (float)((double) Class237.mc.gameSettings.mouseSensitivity * (1.0 + Math.random() / 1.0E7) * (double)0.6f + (double)0.2f);
        double multiplier = (double)(mouseSensitivity * mouseSensitivity * mouseSensitivity * 8.0f) * 0.15;
        float yaw = previousRotation.x + (float)((double)Math.round((double)(rotation.x - previousRotation.x) / multiplier) * multiplier);
        float pitch = previousRotation.y + (float)((double)Math.round((double)(rotation.y - previousRotation.y) / multiplier) * multiplier);
        return new Class247(yaw, MathHelper.clamp_float(pitch, -90.0f, 90.0f));
    }

    public static Class247 relateToPlayerRotation(Class247 rotation) {
        Class247 previousRotation = Class237.mc.thePlayer.getPreviousRotation();
        float yaw = previousRotation.x + MathHelper.wrapAngleTo180_float(rotation.x - previousRotation.x);
        float pitch = MathHelper.clamp_float(rotation.y, -90.0f, 90.0f);
        return new Class247(yaw, pitch);
    }

    public static Class247 resetRotation(Class247 rotation) {
        if (rotation == null) {
            return null;
        }
        float yaw = rotation.x + MathHelper.wrapAngleTo180_float(Class237.mc.thePlayer.rotationYaw - rotation.x);
        float pitch = Class237.mc.thePlayer.rotationPitch;
        return new Class247(yaw, pitch);
    }

    public static Class247 smooth(Class247 lastRotation, Class247 targetRotation, double speed) {
        float yaw = targetRotation.x;
        float pitch = targetRotation.y;
        float lastYaw = lastRotation.x;
        float lastPitch = lastRotation.y;
        if (speed != 0.0) {
            float rotationSpeed = (float)speed;
            double deltaYaw = MathHelper.wrapAngleTo180_float(targetRotation.x - lastRotation.x);
            double deltaPitch = pitch - lastPitch;
            double distance = Math.sqrt(deltaYaw * deltaYaw + deltaPitch * deltaPitch);
            double distributionYaw = Math.abs(deltaYaw / distance);
            double distributionPitch = Math.abs(deltaPitch / distance);
            double maxYaw = (double)rotationSpeed * distributionYaw;
            double maxPitch = (double)rotationSpeed * distributionPitch;
            float moveYaw = (float)Math.max(Math.min(deltaYaw, maxYaw), -maxYaw);
            float movePitch = (float)Math.max(Math.min(deltaPitch, maxPitch), -maxPitch);
            yaw = lastYaw + moveYaw;
            pitch = lastPitch + movePitch;
            for (int i = 1; i <= (int)((double)((float)Minecraft.getDebugFPS() / 20.0f) + Math.random() * 10.0); ++i) {
                if (Math.abs(moveYaw) + Math.abs(movePitch) > 1.0f) {
                    yaw = (float)((double)yaw + (Math.random() - 0.5) / 1000.0);
                    pitch = (float)((double)pitch - Math.random() / 200.0);
                }
                Class247 rotations = new Class247(yaw, pitch);
                Class247 fixedRotations = Class237.applySensitivityPatch(rotations);
                yaw = fixedRotations.x;
                pitch = Math.max(-90.0f, Math.min(90.0f, fixedRotations.y));
            }
        }
        return new Class247(yaw, pitch);
    }

    private static float[] getRotationsByVec(Vec3 origin, Vec3 position) {
        Vec3 difference = position.subtract(origin);
        double distance = difference.flat().lengthVector();
        float yaw = (float)Math.toDegrees(Math.atan2(difference.zCoord, difference.xCoord)) - 90.0f;
        float pitch = (float)(-Math.toDegrees(Math.atan2(difference.yCoord, distance)));
        return new float[]{yaw, pitch};
    }

    public static float[] getRotationBlock(BlockPos pos) {
        return Class237.getRotationsByVec(Class237.mc.thePlayer.getPositionVector().addVector(0.0, Class237.mc.thePlayer.getEyeHeight(), 0.0), new Vec3((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5));
    }

    public static Vec3 getVectorForRotation(Class247 rotation) {
        float yawCos = MathHelper.cos(-rotation.getX() * ((float)Math.PI / 180) - (float)Math.PI);
        float yawSin = MathHelper.sin(-rotation.getX() * ((float)Math.PI / 180) - (float)Math.PI);
        float pitchCos = -MathHelper.cos(-rotation.getY() * ((float)Math.PI / 180));
        float pitchSin = MathHelper.sin(-rotation.getY() * ((float)Math.PI / 180));
        return new Vec3(yawSin * pitchCos, pitchSin, yawCos * pitchCos);
    }

    public static float[] getBlockRotations(double x, double y, double z) {
        double var4 = x - Class237.mc.thePlayer.posX + 0.5;
        double var6 = z - Class237.mc.thePlayer.posZ + 0.5;
        double var8 = y - (Class237.mc.thePlayer.posY + (double) Class237.mc.thePlayer.getEyeHeight() - 1.0);
        double var14 = MathHelper.sqrt_double(var4 * var4 + var6 * var6);
        float var12 = (float)(Math.atan2(var6, var4) * 180.0 / Math.PI) - 90.0f;
        return new float[]{var12, (float)(-Math.atan2(var8, var14) * 180.0 / Math.PI)};
    }

    private Class237() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

