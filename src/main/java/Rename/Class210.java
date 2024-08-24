package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.util.Arrays;

import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.potion.Potion;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovementInput;

public final class Class210
implements Class3 {
    public static final double WALK_SPEED = 0.221;
    public static final double BUNNY_SLOPE = 0.66;
    public static final double MOD_SPRINTING = 1.3f;
    public static final double MOD_SNEAK = 0.3f;
    public static final double MOD_ICE = 2.5;
    public static final double MOD_WEB = 0.4751131221719457;
    public static final double JUMP_HEIGHT = 0.42f;
    public static final double BUNNY_FRICTION = 159.9f;
    public static final double Y_ON_GROUND_MIN = 1.0E-5;
    public static final double Y_ON_GROUND_MAX = 0.0626;
    public static final double AIR_FRICTION = 0.98f;
    public static final double WATER_FRICTION = 0.8f;
    public static final double LAVA_FRICTION = 0.5;
    public static final double MOD_SWIM = 0.5203620003898759;
    public static final double[] MOD_DEPTH_STRIDER = new double[]{1.0, 1.4304347400741908, 1.7347825295420372, 1.9217390955733897};
    public static final double UNLOADED_CHUNK_MOTION = -0.09800000190735147;
    public static final double HEAD_HITTER_MOTION = -0.0784000015258789;

    public static boolean isMoving() {
        return Class210.mc.thePlayer.moveForward != 0.0f || Class210.mc.thePlayer.moveStrafing != 0.0f;
    }

    public static boolean enoughMovementForSprinting() {
        return Math.abs(Class210.mc.thePlayer.moveForward) >= 0.8f || Math.abs(Class210.mc.thePlayer.moveStrafing) >= 0.8f;
    }

    public static boolean canSprint(boolean legit) {
        return legit ? !(!(Class210.mc.thePlayer.moveForward >= 0.8f) || Class210.mc.thePlayer.isCollidedHorizontally || Class210.mc.thePlayer.getFoodStats().getFoodLevel() <= 6 && !Class210.mc.thePlayer.capabilities.allowFlying || Class210.mc.thePlayer.isPotionActive(Potion.blindness) || Class210.mc.thePlayer.isUsingItem() || Class210.mc.thePlayer.isSneaking()) : Class210.enoughMovementForSprinting();
    }

    public static double movementDelta() {
        return Math.hypot(Class210.mc.thePlayer.posX - Class210.mc.thePlayer.prevPosX, Class210.mc.thePlayer.posZ - Class210.mc.thePlayer.prevPosZ);
    }

    public static double speedPotionAmp(double amp) {
        return Class210.mc.thePlayer.isPotionActive(Potion.moveSpeed) ? (double)(Class210.mc.thePlayer.getActivePotionEffect(Potion.moveSpeed).getAmplifier() + 1) * amp : 0.0;
    }

    public static double jumpMotion() {
        return Class210.jumpBoostMotion(0.42f);
    }

    public static double jumpBoostMotion(double motionY) {
        if (Class210.mc.thePlayer.isPotionActive(Potion.jump)) {
            return motionY + (double)((float)(Class210.mc.thePlayer.getActivePotionEffect(Potion.jump).getAmplifier() + 1) * 0.1f);
        }
        return motionY;
    }

    public static Class246 getMotion(double moveSpeed) {
        MovementInput movementInput = Class210.mc.thePlayer.movementInput;
        double moveForward = movementInput.moveForward;
        double moveStrafe = movementInput.moveStrafe;
        double rotationYaw = Class210.mc.thePlayer.movementYaw;
        if (moveForward != 0.0 || moveStrafe != 0.0) {
            if (moveStrafe > 0.0) {
                moveStrafe = 1.0;
            } else if (moveStrafe < 0.0) {
                moveStrafe = -1.0;
            }
            if (moveForward != 0.0) {
                if (moveStrafe > 0.0) {
                    rotationYaw += moveForward > 0.0 ? -45.0 : 45.0;
                } else if (moveStrafe < 0.0) {
                    rotationYaw += moveForward > 0.0 ? 45.0 : -45.0;
                }
                moveStrafe = 0.0;
                if (moveForward > 0.0) {
                    moveForward = 1.0;
                } else if (moveForward < 0.0) {
                    moveForward = -1.0;
                }
            }
            double cos = Math.cos(Math.toRadians(rotationYaw + 90.0));
            double sin = Math.sin(Math.toRadians(rotationYaw + 90.0));
            return new Class246(moveForward * moveSpeed * cos + moveStrafe * moveSpeed * sin, moveForward * moveSpeed * sin - moveStrafe * moveSpeed * cos);
        }
        return new Class246(0.0, 0.0);
    }

    public static int depthStriderLevel() {
        return EnchantmentHelper.getDepthStriderModifier(Class210.mc.thePlayer);
    }

    public static float fallDistanceForDamage() {
        float fallDistanceReq = 3.0f;
        if (Class210.mc.thePlayer.isPotionActive(Potion.jump)) {
            int amplifier = Class210.mc.thePlayer.getActivePotionEffect(Potion.jump).getAmplifier();
            fallDistanceReq += (float)(amplifier + 1);
        }
        return fallDistanceReq;
    }

    public static double roundToGround(double posY) {
        return (double)Math.round(posY / 0.015625) * 0.015625;
    }

    public static double predictedMotion(double motion) {
        return (motion - 0.08) * (double)0.98f;
    }

    public static void forward(double speed) {
        double yaw = Class210.direction();
        Class210.mc.thePlayer.motionX = -Math.sin(yaw) * speed;
        Class210.mc.thePlayer.motionZ = Math.cos(yaw) * speed;
    }

    public static double predictedMotion(double motion, int ticks) {
        if (ticks == 0) {
            return motion;
        }
        double predicted = motion;
        for (int i = 0; i < ticks; ++i) {
            predicted = (predicted - 0.08) * (double)0.98f;
        }
        return predicted;
    }

    public static void jumpRandom(double motion) {
        Class210.mc.thePlayer.motionY = motion + Math.random() / 500.0;
    }

    public static void strafe() {
        Class210.strafe(Class210.speed());
    }

    public static void strafe(double speed) {
        if (!Class210.isMoving()) {
            return;
        }
        double yaw = Class210.direction();
        Class210.mc.thePlayer.motionX = (double)(-MathHelper.sin((float)yaw)) * speed;
        Class210.mc.thePlayer.motionZ = (double)MathHelper.cos((float)yaw) * speed;
    }

    public static void strafe(double speed, float yaw) {
        if (!Class210.isMoving()) {
            return;
        }
        yaw = (float)Math.toRadians(yaw);
        Class210.mc.thePlayer.motionX = (double)(-MathHelper.sin(yaw)) * speed;
        Class210.mc.thePlayer.motionZ = (double)MathHelper.cos(yaw) * speed;
    }

    public static void stop() {
        Class210.mc.thePlayer.motionX = 0.0;
        Class210.mc.thePlayer.motionZ = 0.0;
    }

    public static double direction() {
        float rotationYaw = Class210.mc.thePlayer.movementYaw;
        if (Class210.mc.thePlayer.moveForward < 0.0f) {
            rotationYaw += 180.0f;
        }
        float forward = 1.0f;
        if (Class210.mc.thePlayer.moveForward < 0.0f) {
            forward = -0.5f;
        } else if (Class210.mc.thePlayer.moveForward > 0.0f) {
            forward = 0.5f;
        }
        if (Class210.mc.thePlayer.moveStrafing > 0.0f) {
            rotationYaw -= 70.0f * forward;
        }
        if (Class210.mc.thePlayer.moveStrafing < 0.0f) {
            rotationYaw += 70.0f * forward;
        }
        return Math.toRadians(rotationYaw);
    }

    public static double wrappedDirection() {
        float rotationYaw = Class210.mc.thePlayer.movementYaw;
        if (Class210.mc.thePlayer.moveForward < 0.0f && Class210.mc.thePlayer.moveStrafing == 0.0f) {
            rotationYaw += 180.0f;
        }
        if (Class210.mc.thePlayer.moveStrafing > 0.0f) {
            rotationYaw -= 90.0f;
        }
        if (Class210.mc.thePlayer.moveStrafing < 0.0f) {
            rotationYaw += 90.0f;
        }
        return Math.toRadians(rotationYaw);
    }

    public static double direction(float rotationYaw, double moveForward, double moveStrafing) {
        if (moveForward < 0.0) {
            rotationYaw += 180.0f;
        }
        float forward = 1.0f;
        if (moveForward < 0.0) {
            forward = -0.5f;
        } else if (moveForward > 0.0) {
            forward = 0.5f;
        }
        if (moveStrafing > 0.0) {
            rotationYaw -= 90.0f * forward;
        }
        if (moveStrafing < 0.0) {
            rotationYaw += 90.0f * forward;
        }
        return Math.toRadians(rotationYaw);
    }

    public static double speed() {
        return Math.hypot(Class210.mc.thePlayer.motionX, Class210.mc.thePlayer.motionZ);
    }

    public static void fixMovement(Class30 event, float yaw) {
        float forward = event.getForward();
        float strafe = event.getStrafe();
        double angle = MathHelper.wrapAngleTo180_double(Math.toDegrees(Class210.direction(Class210.mc.thePlayer.rotationYaw, forward, strafe)));
        if (forward == 0.0f && strafe == 0.0f) {
            return;
        }
        float closestForward = 0.0f;
        float closestStrafe = 0.0f;
        float closestDifference = Float.MAX_VALUE;
        for (float predictedForward = -1.0f; predictedForward <= 1.0f; predictedForward += 1.0f) {
            for (float predictedStrafe = -1.0f; predictedStrafe <= 1.0f; predictedStrafe += 1.0f) {
                double predictedAngle;
                double difference;
                if (predictedStrafe == 0.0f && predictedForward == 0.0f || !((difference = Math.abs(angle - (predictedAngle = MathHelper.wrapAngleTo180_double(Math.toDegrees(Class210.direction(yaw, predictedForward, predictedStrafe)))))) < (double)closestDifference)) continue;
                closestDifference = (float)difference;
                closestForward = predictedForward;
                closestStrafe = predictedStrafe;
            }
        }
        event.setForward(closestForward);
        event.setStrafe(closestStrafe);
    }

    public static double getMCFriction() {
        float f = 0.91f;
        if (Class210.mc.thePlayer.onGround) {
            f = Class210.mc.theWorld.getBlockState(new BlockPos(MathHelper.floor_double(Class210.mc.thePlayer.posX), MathHelper.floor_double(Class210.mc.thePlayer.getEntityBoundingBox().minY) - 1, MathHelper.floor_double(Class210.mc.thePlayer.posZ))).getBlock().slipperiness * 0.91f;
        }
        return f;
    }

    public static double[] moveFlying(float strafe, float forward, boolean onGround, float yaw, boolean sprinting) {
        float f;
        float friction = 0.02f;
        float playerWalkSpeed = Class210.mc.thePlayer.getAIMoveSpeed();
        if (onGround) {
            float f4 = 0.54600006f;
            float f2 = 0.9999998f;
            friction = playerWalkSpeed / 2.0f * 0.9999998f;
        }
        if (sprinting) {
            friction = (float)((double)friction + (double)(onGround ? playerWalkSpeed / 2.0f : 0.02f) * 0.3);
        }
        if ((f = strafe * strafe + forward * forward) >= 1.0E-4f) {
            if ((f = MathHelper.sqrt_float(f)) < 1.0f) {
                f = 1.0f;
            }
            f = friction / f;
            float f1 = MathHelper.sin(yaw * (float)Math.PI / 180.0f);
            float f2 = MathHelper.cos(yaw * (float)Math.PI / 180.0f);
            double motionX = (strafe *= f) * f2 - (forward *= f) * f1;
            double motionZ = forward * f2 + strafe * f1;
            return new double[]{motionX, motionZ};
        }
        return null;
    }

    public static Class246 moveFlyingVec(float strafe, float forward, boolean onGround, float yaw, boolean sprinting) {
        double[] values = Class210.moveFlying(strafe, forward, onGround, yaw, sprinting);
        if (values == null) {
            return null;
        }
        return new Class246(values[0], values[1]);
    }

    public static Double moveFlyingSpeed(float strafe, float forward, boolean onGround, float yaw, boolean sprinting) {
        double[] speed = Class210.moveFlying(strafe, forward, onGround, yaw, sprinting);
        if (speed == null) {
            return null;
        }
        return Math.hypot(speed[0], speed[1]);
    }

    public static Double moveFlyingSpeed(boolean sprinting) {
        double[] speed = Class210.moveFlying(0.98f, 0.98f, Class210.mc.thePlayer.onGround, 180.0f, sprinting);
        if (speed == null) {
            return null;
        }
        return Math.hypot(speed[0], speed[1]);
    }

    public static void partialStrafeMax(double maxStrafe) {
        double motionX = Class210.mc.thePlayer.motionX;
        double motionZ = Class210.mc.thePlayer.motionZ;
        Class210.strafe();
        Class210.mc.thePlayer.motionX = motionX + Math.max(-maxStrafe, Math.min(maxStrafe, Class210.mc.thePlayer.motionX - motionX));
        Class210.mc.thePlayer.motionZ = motionZ + Math.max(-maxStrafe, Math.min(maxStrafe, Class210.mc.thePlayer.motionZ - motionZ));
    }

    public static void partialStrafePercent(double percentage) {
        percentage /= 100.0;
        percentage = Math.min(1.0, Math.max(0.0, percentage));
        double motionX = Class210.mc.thePlayer.motionX;
        double motionZ = Class210.mc.thePlayer.motionZ;
        Class210.strafe();
        Class210.mc.thePlayer.motionX = motionX + (Class210.mc.thePlayer.motionX - motionX) * percentage;
        Class210.mc.thePlayer.motionZ = motionZ + (Class210.mc.thePlayer.motionZ - motionZ) * percentage;
    }

    public static double moveMaxFlying(boolean onGround) {
        float friction = 0.02f;
        float playerWalkSpeed = Class210.mc.thePlayer.getAIMoveSpeed() / 2.0f;
        float strafe = 0.98f;
        float forward = 0.98f;
        float yaw = 180.0f;
        if (onGround) {
            float f4 = 0.54600006f;
            float f = 0.9999998f;
            friction = playerWalkSpeed * 0.9999998f;
        }
        friction = (float)((double)friction + (double)(onGround ? playerWalkSpeed : 0.02f) * 0.3);
        float f = strafe * strafe + forward * forward;
        if (f >= 1.0E-4f) {
            if ((f = (float)Math.sqrt(f)) < 1.0f) {
                f = 1.0f;
            }
            f = friction / f;
            float f1 = MathHelper.sin(yaw * (float)Math.PI / 180.0f);
            float f2 = MathHelper.cos(yaw * (float)Math.PI / 180.0f);
            double motionX = (strafe *= f) * f2 - (forward *= f) * f1;
            double motionZ = forward * f2 + strafe * f1;
            return Math.hypot(motionX, motionZ);
        }
        return 0.0;
    }

    public static float simulationStrafeAngle(float currentMoveYaw, float maxAngle) {
        float target = (float)Math.toDegrees(Class210.direction());
        currentMoveYaw = Math.abs(currentMoveYaw - target) <= maxAngle ? target : (currentMoveYaw > target ? (currentMoveYaw -= maxAngle) : (currentMoveYaw += maxAngle));
        float workingYaw = currentMoveYaw;
        Class210.strafe(Class210.speed(), workingYaw);
        return workingYaw;
    }

    public static void useDiagonalSpeed() {
        boolean active;
        KeyBinding[] gameSettings = new KeyBinding[]{Class210.mc.gameSettings.keyBindForward, Class210.mc.gameSettings.keyBindRight, Class210.mc.gameSettings.keyBindBack, Class210.mc.gameSettings.keyBindLeft};
        int[] down = new int[]{0};
        Arrays.stream(gameSettings).forEach(keyBinding -> {
            down[0] = down[0] + (keyBinding.isKeyDown() ? 1 : 0);
        });
        boolean bl = active = down[0] == 1;
        if (!active) {
            return;
        }
        double groundIncrease = 0.0026000750109401644;
        double airIncrease = 5.199896488849598E-4;
        double increase = Class210.mc.thePlayer.onGround ? 0.0026000750109401644 : 5.199896488849598E-4;
        Class210.moveFlying(increase);
    }

    public static void moveFlying(double increase) {
        if (!Class210.isMoving()) {
            return;
        }
        double yaw = Class210.direction();
        Class210.mc.thePlayer.motionX += (double)(-MathHelper.sin((float)yaw)) * increase;
        Class210.mc.thePlayer.motionZ += (double)MathHelper.cos((float)yaw) * increase;
    }

    public static double getBaseMoveSpeed() {
        double baseSpeed = (double) Class210.mc.thePlayer.capabilities.getWalkSpeed() * 2.873;
        if (Class210.mc.thePlayer.isPotionActive(Potion.moveSlowdown)) {
            baseSpeed /= 1.0 + 0.2 * (double)(Class210.mc.thePlayer.getActivePotionEffect(Potion.moveSlowdown).getAmplifier() + 1);
        }
        if (Class210.mc.thePlayer.isPotionActive(Potion.moveSpeed)) {
            baseSpeed *= 1.0 + 0.2 * (double)(Class210.mc.thePlayer.getActivePotionEffect(Potion.moveSpeed).getAmplifier() + 1);
        }
        return baseSpeed;
    }

    public static void setSpeed(double moveSpeed, float yaw, double strafe, double forward) {
        if (forward != 0.0) {
            if (strafe > 0.0) {
                yaw += (float)(forward > 0.0 ? -45 : 45);
            } else if (strafe < 0.0) {
                yaw += (float)(forward > 0.0 ? 45 : -45);
            }
            strafe = 0.0;
            if (forward > 0.0) {
                forward = 1.0;
            } else if (forward < 0.0) {
                forward = -1.0;
            }
        }
        if (strafe > 0.0) {
            strafe = 1.0;
        } else if (strafe < 0.0) {
            strafe = -1.0;
        }
        double mx = Math.cos(Math.toRadians(yaw + 90.0f));
        double mz = Math.sin(Math.toRadians(yaw + 90.0f));
        Class210.mc.thePlayer.motionX = forward * moveSpeed * mx + strafe * moveSpeed * mz;
        Class210.mc.thePlayer.motionZ = forward * moveSpeed * mz - strafe * moveSpeed * mx;
    }

    public static void setSpeed(double moveSpeed) {
        Class210.setSpeed(moveSpeed, Class210.mc.thePlayer.rotationYaw, Class210.mc.thePlayer.movementInput.getMoveStrafe(), Class210.mc.thePlayer.movementInput.getMoveForward());
    }

    public static double getHorizontalSpeed() {
        return Class210.getHorizontalSpeed(Class210.mc.thePlayer);
    }

    public static double getHorizontalSpeed(Entity entity) {
        return Math.sqrt(entity.motionX * entity.motionX + entity.motionZ * entity.motionZ);
    }

    public static boolean isOnGround(double height) {
        return !Class210.mc.theWorld.getCollidingBoundingBoxes(Class210.mc.thePlayer, Class210.mc.thePlayer.getEntityBoundingBox().offset(0.0, -height, 0.0)).isEmpty();
    }

    public static float getPlayerDirection() {
        float direction = Class210.mc.thePlayer.rotationYaw;
        if (Class210.mc.thePlayer.moveForward > 0.0f) {
            if (Class210.mc.thePlayer.moveStrafing > 0.0f) {
                direction -= 45.0f;
            } else if (Class210.mc.thePlayer.moveStrafing < 0.0f) {
                direction += 45.0f;
            }
        } else if (Class210.mc.thePlayer.moveForward < 0.0f) {
            direction = Class210.mc.thePlayer.moveStrafing > 0.0f ? (direction -= 135.0f) : (Class210.mc.thePlayer.moveStrafing < 0.0f ? (direction += 135.0f) : (direction -= 180.0f));
        } else if (Class210.mc.thePlayer.moveStrafing > 0.0f) {
            direction -= 90.0f;
        } else if (Class210.mc.thePlayer.moveStrafing < 0.0f) {
            direction += 90.0f;
        }
        return direction;
    }

    public static float[] incrementMoveDirection(float forward, float strafe) {
        if (forward != 0.0f || strafe != 0.0f) {
            float value;
            float f = value = forward != 0.0f ? Math.abs(forward) : Math.abs(strafe);
            if (forward > 0.0f) {
                if (strafe > 0.0f) {
                    strafe = 0.0f;
                } else if (strafe == 0.0f) {
                    strafe = -value;
                } else if (strafe < 0.0f) {
                    forward = 0.0f;
                }
            } else if (forward == 0.0f) {
                forward = strafe > 0.0f ? value : -value;
            } else if (strafe < 0.0f) {
                strafe = 0.0f;
            } else if (strafe == 0.0f) {
                strafe = value;
            } else if (strafe > 0.0f) {
                forward = 0.0f;
            }
        }
        return new float[]{forward, strafe};
    }

    public static boolean isMoveKeybind() {
        GameSettings gameSettings = Class210.mc.gameSettings;
        return gameSettings.keyBindForward.isKeyDown() || gameSettings.keyBindBack.isKeyDown() || gameSettings.keyBindLeft.isKeyDown() || gameSettings.keyBindRight.isKeyDown();
    }

    private Class210() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

