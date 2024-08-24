package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;

public final class Class154 {
    private static final Minecraft mc = Minecraft.getMinecraft();
    private final BlockPos blockPos;
    private EnumFacing enumFacing;
    private Vec3 vec3;

    public Class154(BlockPos blockPos, EnumFacing enumFacing) {
        this.blockPos = blockPos;
        this.enumFacing = enumFacing;
    }

    public Class154(BlockPos blockPos, EnumFacing enumFacing, Vec3 vec3) {
        this.blockPos = blockPos;
        this.enumFacing = enumFacing;
        this.vec3 = vec3;
    }

    public BlockPos getBlockPos() {
        return this.blockPos;
    }

    public EnumFacing getEnumFacing() {
        return this.enumFacing;
    }

    public void setEnumFacing(EnumFacing enumFacing1) {
        this.enumFacing = enumFacing1;
    }

    public Vec3 getVec3() {
        return this.vec3;
    }

    public void setVec3(Vec3 vec3) {
        this.vec3 = vec3;
    }

    public Class154(BlockPos blockPos, EnumFacing enumFacing, Vec3 vec3, int n) {
        this(blockPos, enumFacing, vec3);
        if ((n & 4) != 0) {
            new Vec3((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.5, (double)blockPos.getZ() + 0.5);
        }
    }

    public static Class154 get(BlockPos blockPos) {
        if (Class153.isValidBock(blockPos.add(0, -1, 0))) {
            return new Class154(blockPos.add(0, -1, 0), EnumFacing.UP, null, 4);
        }
        if (Class153.isValidBock(blockPos.add(0, 0, 1))) {
            return new Class154(blockPos.add(0, 0, 1), EnumFacing.NORTH, null, 4);
        }
        if (Class153.isValidBock(blockPos.add(-1, 0, 0))) {
            return new Class154(blockPos.add(-1, 0, 0), EnumFacing.EAST, null, 4);
        }
        if (Class153.isValidBock(blockPos.add(0, 0, -1))) {
            return new Class154(blockPos.add(0, 0, -1), EnumFacing.SOUTH, null, 4);
        }
        return Class153.isValidBock(blockPos.add(1, 0, 0)) ? new Class154(blockPos.add(1, 0, 0), EnumFacing.WEST, null, 4) : null;
    }

    public static Block getBlock(BlockPos blockPos) {
        IBlockState blockState;
        return Class154.mc.theWorld != null && (blockState = Class154.mc.theWorld.getBlockState(blockPos)) != null ? blockState.getBlock() : null;
    }

    public static IBlockState getState(BlockPos blockPos) {
        return Class154.mc.theWorld.getBlockState(blockPos);
    }

    public static boolean isValidBock(BlockPos blockPos) {
        Block var10000 = Class154.getBlock(blockPos);
        return var10000 != null && var10000.canCollideCheck(Class154.getState(blockPos), false) && Class154.mc.theWorld.getWorldBorder().contains(blockPos);
    }

    public void setFacing(EnumFacing enumFacing) {
        this.enumFacing = enumFacing;
    }
}

