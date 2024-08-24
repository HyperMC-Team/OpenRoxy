package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

public final class Class32
extends Class4 {
    private float forward;
    private float strafe;
    private float friction;
    private float yaw;

    public void setSpeed(double speed, double motionMultiplier) {
        this.setFriction((float)(this.getForward() != 0.0f && this.getStrafe() != 0.0f ? speed * (double)0.98f : speed));
        Class32.mc.thePlayer.motionX *= motionMultiplier;
        Class32.mc.thePlayer.motionZ *= motionMultiplier;
    }

    public void setSpeed(double speed) {
        this.setFriction((float)(this.getForward() != 0.0f && this.getStrafe() != 0.0f ? speed * (double)0.98f : speed));
        Class210.stop();
    }

    public float getForward() {
        return this.forward;
    }

    public float getStrafe() {
        return this.strafe;
    }

    public float getFriction() {
        return this.friction;
    }

    public float getYaw() {
        return this.yaw;
    }

    public void setForward(float forward) {
        this.forward = forward;
    }

    public void setStrafe(float strafe) {
        this.strafe = strafe;
    }

    public void setFriction(float friction) {
        this.friction = friction;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public Class32(float forward, float strafe, float friction, float yaw) {
        this.forward = forward;
        this.strafe = strafe;
        this.friction = friction;
        this.yaw = yaw;
    }
}

