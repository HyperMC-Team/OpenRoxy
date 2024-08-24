package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import tech.skidonion.obfuscator.annotations.Renamer;
import tech.skidonion.obfuscator.annotations.StringEncryption;

@Renamer
@StringEncryption
public final class Class20
implements Class3 {
    public static boolean active;
    private static boolean smoothed;
    public static Class247 rotations;
    public static Class247 lastRotations;
    public static Class247 targetRotations;
    public static Class247 lastServerRotations;
    private static double rotationSpeed;
    private static Class167 correctMovement;
    private static boolean forceSilent;

    public static void setRotations(Class247 rotations, double rotationSpeed, Class167 correctMovement) {
        targetRotations = rotations;
        Class20.rotationSpeed = rotationSpeed * 18.0;
        Class20.correctMovement = correctMovement;
        active = true;
        forceSilent = false;
        Class20.smooth();
    }

    public static void setRotations(Class247 rotations, double rotationSpeed, Class167 correctMovement, boolean silent) {
        targetRotations = rotations;
        Class20.rotationSpeed = rotationSpeed * 18.0;
        Class20.correctMovement = correctMovement;
        active = true;
        forceSilent = silent;
        Class20.smooth();
    }

    public static void setFollow(boolean follow) {
        if (targetRotations != null) {
            forceSilent = follow;
        }
    }

    public static double getRotationDifference(Class247 rotation) {
        return lastServerRotations == null ? 0.0 : Class20.getRotationDifference(rotation, lastServerRotations);
    }

    public static double getRotationDifference(Class247 a, Class247 b) {
        return Math.hypot(Class20.getAngleDifference(a.x, b.x), a.y - b.y);
    }

    public static double getRotationDifference(Class156 rotation) {
        return lastServerRotations == null ? 0.0 : Class20.getRotationDifference(rotation, lastServerRotations);
    }

    public static double getRotationDifference(Class156 a, Class247 b) {
        return Math.hypot(Class20.getAngleDifference(a.getYaw(), b.getX()), a.getPitch() - b.getY());
    }

    public static float getAngleDifference(float a, float b) {
        return ((a - b) % 360.0f + 540.0f) % 360.0f - 180.0f;
    }

    @Class14(value=100)
    public void onPreUpdate(Class50 event) {
        if (!active || rotations == null || lastRotations == null || targetRotations == null || lastServerRotations == null) {
            targetRotations = lastServerRotations = new Class247(Class20.mc.thePlayer.rotationYaw, Class20.mc.thePlayer.rotationPitch);
            lastRotations = lastServerRotations;
            rotations = lastServerRotations;
        }
        if (active) {
            Class20.smooth();
        }
        if (correctMovement == Class167.BACKWARDS_SPRINT && active && Math.abs((double) Class20.rotations.x - Math.toDegrees(Class210.direction())) > 45.0) {
            Class20.mc.gameSettings.keyBindSprint.pressed = false;
            Class20.mc.thePlayer.setSprinting(false);
        }
    }

    @Class14(value=0)
    public void onMove(Class30 event) {
        Class273 speed = Class262.getModule(Class273.class);
        if (speed.isState() && speed.strafe.isEnabled() && !Class262.getModule(Class304.class).isState() && !Class262.getModule(Class278.class).isState() && !forceSilent) {
            return;
        }
        if (active && correctMovement == Class167.NORMAL && rotations != null) {
            float yaw = Class20.rotations.x;
            Class210.fixMovement(event, yaw);
        }
    }

    @Class14(value=0)
    public void onLook(Class37 event) {
        if (active && rotations != null) {
            event.setRotation(rotations);
        }
    }

    @Class14(value=0)
    public void onStrafe(Class32 event) {
        if (active && (correctMovement == Class167.NORMAL || correctMovement == Class167.TRADITIONAL) && rotations != null) {
            event.setYaw(Class20.rotations.x);
        }
    }

    @Class14(value=0)
    public void onJump(Class28 event) {
        if (active && (correctMovement == Class167.NORMAL || correctMovement == Class167.TRADITIONAL || correctMovement == Class167.BACKWARDS_SPRINT) && rotations != null) {
            event.setYaw(Class20.rotations.x);
        }
    }

    @Class14(value=0)
    public void onPreMotionEvent(Class27 event) {
        if (active && rotations != null) {
            float yaw = Class20.rotations.x;
            float pitch = Class20.rotations.y;
            event.setYaw(yaw);
            event.setPitch(pitch);
            Class20.mc.thePlayer.renderYawOffset = yaw;
            Class20.mc.thePlayer.rotationYawHead = yaw;
            Class20.mc.thePlayer.renderPitchHead = pitch;
            lastServerRotations = new Class247(yaw, pitch);
            if (Math.abs((Class20.rotations.x - Class20.mc.thePlayer.rotationYaw) % 360.0f) < 1.0f && Math.abs(Class20.rotations.y - Class20.mc.thePlayer.rotationPitch) < 1.0f) {
                active = false;
                this.correctDisabledRotations();
            }
            lastRotations = rotations;
        } else {
            lastRotations = new Class247(Class20.mc.thePlayer.rotationYaw, Class20.mc.thePlayer.rotationPitch);
        }
        targetRotations = new Class247(Class20.mc.thePlayer.rotationYaw, Class20.mc.thePlayer.rotationPitch);
        smoothed = false;
    }

    private void correctDisabledRotations() {
        Class247 rotations = new Class247(Class20.mc.thePlayer.rotationYaw, Class20.mc.thePlayer.rotationPitch);
        Class247 fixedRotations = Class237.resetRotation(Class237.applySensitivityPatch(rotations, lastRotations));
        Class20.mc.thePlayer.rotationYaw = fixedRotations.x;
        Class20.mc.thePlayer.rotationPitch = fixedRotations.y;
    }

    public static void smooth() {
        if (!smoothed) {
            float lastYaw = Class20.lastRotations.x;
            float lastPitch = Class20.lastRotations.y;
            float targetYaw = Class20.targetRotations.x;
            float targetPitch = Class20.targetRotations.y;
            rotations = Class237.smooth(new Class247(lastYaw, lastPitch), new Class247(targetYaw, targetPitch), rotationSpeed + Math.random());
            if (correctMovement == Class167.NORMAL || correctMovement == Class167.TRADITIONAL) {
                Class20.mc.thePlayer.movementYaw = Class20.rotations.x;
            }
            Class20.mc.thePlayer.velocityYaw = Class20.rotations.x;
        }
        smoothed = true;
        Class20.mc.entityRenderer.getMouseOver(1.0f);
    }
}

