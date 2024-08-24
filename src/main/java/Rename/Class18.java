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

import net.minecraft.network.Packet;

public final class Class18
implements Class3 {
    public static final Class18 INSTANCE = new Class18();
    public static Boolean pre = false;
    public static boolean cancelMove = false;
    private static double motionX = 0.0;
    private static double motionY = 0.0;
    private static double motionZ = 0.0;
    private static float fallDistance = 0.0f;
    private static int moveTicks = 0;

    public static float Method1() {
        return (float)Math.sqrt(Class18.mc.thePlayer.motionX * Class18.mc.thePlayer.motionX + Class18.mc.thePlayer.motionZ * Class18.mc.thePlayer.motionZ);
    }

    public static void Method2() {
        Class18.Method2(Class18.Method1());
    }

    public static boolean Method3() {
        return Class18.mc.thePlayer != null && (Class18.mc.thePlayer.movementInput.moveForward != 0.0f || Class18.mc.thePlayer.movementInput.moveStrafe != 0.0f);
    }

    public static void Method2(float speed) {
        if (!Class18.Method3()) {
            return;
        }
        double yaw = Class18.Method5();
        Class18.mc.thePlayer.motionX = -Math.sin(yaw) * (double)speed;
        Class18.mc.thePlayer.motionZ = Math.cos(yaw) * (double)speed;
    }

    public static void Method4(double length) {
        double yaw = Math.toRadians(Class18.mc.thePlayer.rotationYaw);
        Class18.mc.thePlayer.setPosition(Class18.mc.thePlayer.posX + -Math.sin(yaw) * length, Class18.mc.thePlayer.posY, Class18.mc.thePlayer.posZ + Math.cos(yaw) * length);
    }

    public static double Method5() {
        float rotationYaw = Class18.mc.thePlayer.rotationYaw;
        if (Class18.mc.thePlayer.moveForward < 0.0f) {
            rotationYaw += 180.0f;
        }
        float forward = 1.0f;
        if (Class18.mc.thePlayer.moveForward < 0.0f) {
            forward = -0.5f;
        } else if (Class18.mc.thePlayer.moveForward > 0.0f) {
            forward = 0.5f;
        }
        if (Class18.mc.thePlayer.moveStrafing > 0.0f) {
            rotationYaw -= 90.0f * forward;
        }
        if (Class18.mc.thePlayer.moveStrafing < 0.0f) {
            rotationYaw += 90.0f * forward;
        }
        return Math.toRadians(rotationYaw);
    }

    public static void Method6() {
        if (Class18.mc.thePlayer == null) {
            return;
        }
        if (cancelMove) {
            return;
        }
        cancelMove = true;
        motionX = Class18.mc.thePlayer.motionX;
        motionY = Class18.mc.thePlayer.motionY;
        motionZ = Class18.mc.thePlayer.motionZ;
        fallDistance = Class18.mc.thePlayer.fallDistance;
    }

    public static void Method7() {
        cancelMove = false;
        moveTicks = 0;
    }

    public static double Method8(float rotationYaw, double moveForward, double moveStrafing) {
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

    @Class14
    public void onMotion(Class26 event) {
        pre = false;
    }

    @Class14
    public void onUpdate(Class50 event) {
        if (cancelMove) {
            if (moveTicks > 0) {
                return;
            }
            Class18.mc.thePlayer.motionX = motionX;
            Class18.mc.thePlayer.motionZ = motionZ;
            Class18.mc.thePlayer.motionY = motionY;
            Class18.mc.thePlayer.fallDistance = fallDistance;
        }
    }

    @Class14
    public void onPacket(Class35 event) {
        if (event.getPacket() instanceof Class354 && cancelMove && moveTicks > 0) {
            motionX = Class18.mc.thePlayer.motionX;
            motionZ = Class18.mc.thePlayer.motionZ;
            motionY = Class18.mc.thePlayer.motionY;
            fallDistance = Class18.mc.thePlayer.fallDistance;
            --moveTicks;
        }
    }

    @Class14
    public void onTick(Class52 event) {
        if (Class18.mc.thePlayer == null) {
            Class18.Method7();
            return;
        }
        pre = true;
        if (cancelMove) {
            if (Class211.noMovePackets >= 20) {
                Class18.mc.thePlayer.motionX = motionX;
                Class18.mc.thePlayer.motionY = motionY;
                Class18.mc.thePlayer.motionZ = motionZ;
                Class18.mc.thePlayer.fallDistance = fallDistance;
                ++moveTicks;
            }
            if (moveTicks > 0) {
                return;
            }
            Class18.mc.thePlayer.motionX = motionX;
            Class18.mc.thePlayer.motionZ = motionZ;
            Class18.mc.thePlayer.motionY = motionY;
            Class18.mc.thePlayer.fallDistance = fallDistance;
        }
    }

    @Class14
    public void onMove(Class29 event) {
        if (cancelMove) {
            if (moveTicks > 0) {
                return;
            }
            event.setCancelled();
        }
    }

    @Class14
    public void onPacketReceive(Class33 event) {
        Class349 s12;
        Packet<?> packet = event.getPacket();
        if (packet instanceof Class349 && (s12 = (Class349)packet).getEntityID() == Class18.mc.thePlayer.getEntityId() && cancelMove) {
            Class18.mc.thePlayer.motionX = motionX;
            Class18.mc.thePlayer.motionY = motionY;
            Class18.mc.thePlayer.motionZ = motionZ;
            Class18.mc.thePlayer.fallDistance = fallDistance;
            ++moveTicks;
        }
    }
}

