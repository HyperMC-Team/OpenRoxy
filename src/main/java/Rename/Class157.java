package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;

public final class Class157
implements Class3 {
    public static Class152 getBlockUnder(double playerPos, int maxRange) {
        return Class157.getBlockInfo(Class157.mc.thePlayer.posX, playerPos, Class157.mc.thePlayer.posZ, maxRange);
    }

    public static Class152 getBlockInfo() {
        BlockPos belowBlockPos = new BlockPos(Class157.mc.thePlayer.posX, Class157.getYLevel(), Class157.mc.thePlayer.posZ);
        if (Class157.mc.theWorld.getBlockState(belowBlockPos).getBlock() instanceof BlockAir) {
            for (int x = 0; x < 4; ++x) {
                for (int z = 0; z < 4; ++z) {
                    for (int i = 1; i > -3; i -= 2) {
                        BlockPos blockPos = belowBlockPos.add(x * i, 0, z * i);
                        if (!(Class157.mc.theWorld.getBlockState(blockPos).getBlock() instanceof BlockAir)) continue;
                        for (EnumFacing direction : EnumFacing.values()) {
                            BlockPos block = blockPos.offset(direction);
                            Material material = Class157.mc.theWorld.getBlockState(block).getBlock().getMaterial();
                            if (!material.isSolid() || material.isLiquid()) continue;
                            return new Class152(block, direction.getOpposite());
                        }
                    }
                }
            }
        }
        return null;
    }

    public static double getYLevel() {
        return Class157.mc.thePlayer.posY - 1.0;
    }

    public static Class152 getBlockInfo(double x, double y, double z, int maxRange) {
        BlockPos pos = new BlockPos(x, y, z);
        EnumFacing playerDirectionFacing = Class157.getHorizontalFacing(Class157.getPlayerDirection()).getOpposite();
        ArrayList<EnumFacing> facingValues = new ArrayList<EnumFacing>();
        facingValues.add(playerDirectionFacing);
        for (EnumFacing facing : EnumFacing.values()) {
            if (facing == playerDirectionFacing || facing == EnumFacing.UP) continue;
            facingValues.add(facing);
        }
        CopyOnWriteArrayList<BlockPos> blockPos1 = new CopyOnWriteArrayList<BlockPos>();
        blockPos1.add(pos);
        for (int i = 0; i < maxRange; ++i) {
            ArrayList<BlockPos> blockPosArrayList = new ArrayList(blockPos1);
            if (!blockPos1.isEmpty()) {
                for (BlockPos blockPos : blockPos1) {
                    for (EnumFacing facing : facingValues) {
                        BlockPos offset = blockPos.offset(facing);
                        if (Class157.isAirOrLiquid(offset)) {
                            blockPos1.add(offset);
                            continue;
                        }
                        return new Class152(offset, facing.getOpposite());
                    }
                }
            }
            for (BlockPos blockPos : blockPosArrayList) {
                blockPos1.remove(blockPos);
            }
            blockPosArrayList.clear();
        }
        return null;
    }

    public static float getPlayerDirection() {
        float direction = Class157.mc.thePlayer.rotationYaw;
        if (Class157.mc.thePlayer.moveForward > 0.0f) {
            if (Class157.mc.thePlayer.moveStrafing > 0.0f) {
                direction -= 45.0f;
            } else if (Class157.mc.thePlayer.moveStrafing < 0.0f) {
                direction += 45.0f;
            }
        } else if (Class157.mc.thePlayer.moveForward < 0.0f) {
            direction = Class157.mc.thePlayer.moveStrafing > 0.0f ? (direction -= 135.0f) : (Class157.mc.thePlayer.moveStrafing < 0.0f ? (direction += 135.0f) : (direction -= 180.0f));
        } else if (Class157.mc.thePlayer.moveStrafing > 0.0f) {
            direction -= 90.0f;
        } else if (Class157.mc.thePlayer.moveStrafing < 0.0f) {
            direction += 90.0f;
        }
        return direction;
    }

    public static Vec3 getPlacePossibility(double offsetX, double offsetY, double offsetZ) {
        List<Vec3> possibilities = new ArrayList();
        int range = (int)(5.0 + Math.abs(offsetX) + Math.abs(offsetZ));

        for(int x = -range; x <= range; ++x) {
            for(int y = -range; y <= range; ++y) {
                for(int z = -range; z <= range; ++z) {
                    Block block = blockRelativeToPlayer(x, y, z);
                    if (!(block instanceof BlockAir)) {
                        for(int x2 = -1; x2 <= 1; x2 += 2) {
                            possibilities.add(
                                    new Vec3(mc.thePlayer.posX + (double)x + (double)x2, mc.thePlayer.posY + (double)y, mc.thePlayer.posZ + (double)z)
                            );
                        }

                        for(int y2 = -1; y2 <= 1; y2 += 2) {
                            possibilities.add(
                                    new Vec3(mc.thePlayer.posX + (double)x, mc.thePlayer.posY + (double)y + (double)y2, mc.thePlayer.posZ + (double)z)
                            );
                        }

                        for(int z2 = -1; z2 <= 1; z2 += 2) {
                            possibilities.add(
                                    new Vec3(mc.thePlayer.posX + (double)x, mc.thePlayer.posY + (double)y, mc.thePlayer.posZ + (double)z + (double)z2)
                            );
                        }
                    }
                }
            }
        }

        possibilities.removeIf(
                vec3 -> mc.thePlayer.getDistance(vec3.xCoord, vec3.yCoord, vec3.zCoord) > 5.0
                        || !(block(vec3.xCoord, vec3.yCoord, vec3.zCoord) instanceof BlockAir)
        );
        if (possibilities.isEmpty()) {
            return null;
        } else {
            possibilities.sort(Comparator.comparingDouble(vec3 -> {
                double d0 = mc.thePlayer.posX + offsetX - vec3.xCoord;
                double d1 = mc.thePlayer.posY - 1.0 + offsetY - vec3.yCoord;
                double d2 = mc.thePlayer.posZ + offsetZ - vec3.zCoord;
                return MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);
            }));
            return possibilities.getFirst();
        }
    }


    public static Block block(double x, double y, double z) {
        return Class157.mc.theWorld.getBlockState(new BlockPos(x, y, z)).getBlock();
    }

    public static Block blockRelativeToPlayer(double offsetX, double offsetY, double offsetZ) {
        return Class157.mc.theWorld.getBlockState(new BlockPos(Class157.mc.thePlayer).add(offsetX, offsetY, offsetZ)).getBlock();
    }

    public static EnumFacing getEnumFacing(Vec3 position) {
        for (int x2 = -1; x2 <= 1; x2 += 2) {
            if (Class157.block(position.xCoord + (double)x2, position.yCoord, position.zCoord) instanceof BlockAir) continue;
            if (x2 > 0) {
                return EnumFacing.WEST;
            }
            return EnumFacing.EAST;
        }
        for (int y2 = -1; y2 <= 1; y2 += 2) {
            if (Class157.block(position.xCoord, position.yCoord + (double)y2, position.zCoord) instanceof BlockAir || y2 >= 0) continue;
            return EnumFacing.UP;
        }
        for (int z2 = -1; z2 <= 1; z2 += 2) {
            if (Class157.block(position.xCoord, position.yCoord, position.zCoord + (double)z2) instanceof BlockAir) continue;
            if (z2 < 0) {
                return EnumFacing.SOUTH;
            }
            return EnumFacing.NORTH;
        }
        return null;
    }

    public static Vec3 getVec3(BlockPos pos, EnumFacing facing, boolean randomised) {
        Vec3 vec3 = new Vec3(pos);
        double amount1 = 0.5;
        double amount2 = 0.5;
        if (randomised) {
            amount1 = 0.45 + Math.random() * 0.1;
            amount2 = 0.45 + Math.random() * 0.1;
        }
        if (facing == EnumFacing.UP) {
            vec3 = vec3.addVector(amount1, 1.0, amount2);
        } else if (facing == EnumFacing.DOWN) {
            vec3 = vec3.addVector(amount1, 0.0, amount2);
        } else if (facing == EnumFacing.EAST) {
            vec3 = vec3.addVector(1.0, amount1, amount2);
        } else if (facing == EnumFacing.WEST) {
            vec3 = vec3.addVector(0.0, amount1, amount2);
        } else if (facing == EnumFacing.NORTH) {
            vec3 = vec3.addVector(amount1, amount2, 0.0);
        } else if (facing == EnumFacing.SOUTH) {
            vec3 = vec3.addVector(amount1, amount2, 1.0);
        }
        return vec3;
    }

    public static Vec3 getHitVector(Class152 blockData) {
        BlockPos pos = blockData.getPos();
        EnumFacing facing = blockData.getFacing();
        return Class157.getHitVector(pos, facing);
    }

    public static Vec3 getHitVector(BlockPos pos, EnumFacing facing) {
        double x = (double)pos.getX() + 0.5;
        double y = (double)pos.getY() + 0.5;
        double z = (double)pos.getZ() + 0.5;
        switch (facing) {
            case DOWN: {
                y -= 0.5;
                break;
            }
            case UP: {
                y += 0.5;
                break;
            }
            case NORTH: {
                z -= 0.5;
                break;
            }
            case SOUTH: {
                z += 0.5;
                break;
            }
            case WEST: {
                x -= 0.5;
                break;
            }
            case EAST: {
                x += 0.5;
            }
        }
        return new Vec3(x, y, z);
    }

    public static EnumFacing getHorizontalFacing(float yaw) {
        return EnumFacing.getHorizontal(MathHelper.floor_double((double)(yaw * 4.0f / 360.0f) + 0.5) & 3);
    }

    public static boolean isAir(BlockPos pos) {
        Block block = Class157.mc.theWorld.getBlockState(pos).getBlock();
        return block instanceof BlockAir;
    }

    public static boolean isAirOrLiquid(BlockPos pos) {
        Block block = Class157.mc.theWorld.getBlockState(pos).getBlock();
        return block instanceof BlockAir || block instanceof BlockLiquid;
    }

    public static MovingObjectPosition raytrace(float yaw, float pitch) {
        float partialTicks = Class157.mc.timer.renderPartialTicks;
        float blockReachDistance = Class157.mc.playerController.getBlockReachDistance();
        Vec3 vec3 = Class157.mc.thePlayer.getPositionEyes(partialTicks);
        Vec3 vec31 = Class157.mc.thePlayer.getVectorForRotation(pitch, yaw);
        Vec3 vec32 = vec3.addVector(vec31.xCoord * (double)blockReachDistance, vec31.yCoord * (double)blockReachDistance, vec31.zCoord * (double)blockReachDistance);
        return Class157.mc.theWorld.rayTraceBlocks(vec3, vec32, false, false, true);
    }

    public static MovingObjectPosition raytraceLegit(float yaw, float pitch, float lastYaw, float lastPitch) {
        float partialTicks = Class157.mc.timer.renderPartialTicks;
        float blockReachDistance = Class157.mc.playerController.getBlockReachDistance();
        Vec3 vec3 = Class157.mc.thePlayer.getPositionEyes(partialTicks);
        float f = lastPitch + (pitch - lastPitch) * partialTicks;
        float f1 = lastYaw + (yaw - lastYaw) * partialTicks;
        Vec3 vec31 = Class157.mc.thePlayer.getVectorForRotation(f, f1);
        Vec3 vec32 = vec3.addVector(vec31.xCoord * (double)blockReachDistance, vec31.yCoord * (double)blockReachDistance, vec31.zCoord * (double)blockReachDistance);
        return Class157.mc.theWorld.rayTraceBlocks(vec3, vec32, false, false, true);
    }

    public static boolean isBlockUnder() {
        for (int y = (int) Class157.mc.thePlayer.posY; y >= 0; --y) {
            if (Class157.mc.theWorld.getBlockState(new BlockPos(Class157.mc.thePlayer.posX, y, Class157.mc.thePlayer.posZ)).getBlock() instanceof BlockAir) continue;
            return true;
        }
        return false;
    }

    public static boolean isBlockUnder(int distance) {
        for (int y = (int) Class157.mc.thePlayer.posY; y >= (int) Class157.mc.thePlayer.posY - distance; --y) {
            if (Class157.mc.theWorld.getBlockState(new BlockPos(Class157.mc.thePlayer.posX, y, Class157.mc.thePlayer.posZ)).getBlock() instanceof BlockAir) continue;
            return true;
        }
        return false;
    }

    public static boolean negativeExpand(double negativeExpandValue) {
        return Class157.mc.theWorld.getBlockState(new BlockPos(Class157.mc.thePlayer.posX + negativeExpandValue, Class157.mc.thePlayer.posY - 1.0, Class157.mc.thePlayer.posZ + negativeExpandValue)).getBlock() instanceof BlockAir && Class157.mc.theWorld.getBlockState(new BlockPos(Class157.mc.thePlayer.posX - negativeExpandValue, Class157.mc.thePlayer.posY - 1.0, Class157.mc.thePlayer.posZ - negativeExpandValue)).getBlock() instanceof BlockAir && Class157.mc.theWorld.getBlockState(new BlockPos(Class157.mc.thePlayer.posX - negativeExpandValue, Class157.mc.thePlayer.posY - 1.0, Class157.mc.thePlayer.posZ)).getBlock() instanceof BlockAir && Class157.mc.theWorld.getBlockState(new BlockPos(Class157.mc.thePlayer.posX + negativeExpandValue, Class157.mc.thePlayer.posY - 1.0, Class157.mc.thePlayer.posZ)).getBlock() instanceof BlockAir && Class157.mc.theWorld.getBlockState(new BlockPos(Class157.mc.thePlayer.posX, Class157.mc.thePlayer.posY - 1.0, Class157.mc.thePlayer.posZ + negativeExpandValue)).getBlock() instanceof BlockAir && Class157.mc.theWorld.getBlockState(new BlockPos(Class157.mc.thePlayer.posX, Class157.mc.thePlayer.posY - 1.0, Class157.mc.thePlayer.posZ - negativeExpandValue)).getBlock() instanceof BlockAir;
    }

    private Class157() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}

