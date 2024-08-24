package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.BlockPos;

public class Class235 {
    private double x;
    private double y;
    private double z;
    private float yaw;
    private float pitch;

    public Class235(double x2, double y2, double z, float yaw, float pitch) {
        this.x = x2;
        this.y = y2;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public Class235(double x2, double y2, double z) {
        this.x = x2;
        this.y = y2;
        this.z = z;
        this.yaw = 0.0f;
        this.pitch = 0.0f;
    }

    public Class235(BlockPos pos) {
        this.x = pos.getX();
        this.y = pos.getY();
        this.z = pos.getZ();
        this.yaw = 0.0f;
        this.pitch = 0.0f;
    }

    public Class235(int x2, int y2, int z) {
        this.x = x2;
        this.y = y2;
        this.z = z;
        this.yaw = 0.0f;
        this.pitch = 0.0f;
    }

    public Class235(EntityLivingBase entity) {
        this.x = entity.posX;
        this.y = entity.posY;
        this.z = entity.posZ;
        this.yaw = 0.0f;
        this.pitch = 0.0f;
    }

    public Class235 add(int x2, int y2, int z) {
        this.x += x2;
        this.y += y2;
        this.z += z;
        return this;
    }

    public Class235 add(double x2, double y2, double z) {
        this.x += x2;
        this.y += y2;
        this.z += z;
        return this;
    }

    public Class235 subtract(int x2, int y2, int z) {
        this.x -= x2;
        this.y -= y2;
        this.z -= z;
        return this;
    }

    public Class235 subtract(double x2, double y2, double z) {
        this.x -= x2;
        this.y -= y2;
        this.z -= z;
        return this;
    }

    public Block getBlock() {
        return Minecraft.getMinecraft().theWorld.getBlockState(this.toBlockPos()).getBlock();
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x2) {
        this.x = x2;
    }

    public double getY() {
        return this.y;
    }

    public void setY(double y2) {
        this.y = y2;
    }

    public double getZ() {
        return this.z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public float getYaw() {
        return this.yaw;
    }

    public Class235 setYaw(float yaw) {
        this.yaw = yaw;
        return this;
    }

    public float getPitch() {
        return this.pitch;
    }

    public Class235 setPitch(float pitch) {
        this.pitch = pitch;
        return this;
    }

    public BlockPos toBlockPos() {
        return new BlockPos(this.x, this.y, this.z);
    }

    public double distanceTo(Class235 loc) {
        double dx = loc.x - this.x;
        double dz = loc.z - this.z;
        double dy = loc.y - this.y;
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }
}

