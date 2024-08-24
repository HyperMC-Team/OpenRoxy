package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.block.BlockAnvil;
import net.minecraft.block.BlockBeacon;
import net.minecraft.block.BlockBed;
import net.minecraft.block.BlockBrewingStand;
import net.minecraft.block.BlockButton;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockDaylightDetector;
import net.minecraft.block.BlockDispenser;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.BlockEnchantmentTable;
import net.minecraft.block.BlockEnderChest;
import net.minecraft.block.BlockFence;
import net.minecraft.block.BlockFenceGate;
import net.minecraft.block.BlockFurnace;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockNote;
import net.minecraft.block.BlockRedstoneComparator;
import net.minecraft.block.BlockRedstoneRepeater;
import net.minecraft.block.BlockSign;
import net.minecraft.block.BlockTrapDoor;
import net.minecraft.block.BlockWorkbench;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBanner;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemLeaves;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemSnow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public final class Class153 {
    private static final Minecraft mc;
    public static final List<Block> invalidBlocks;
    private static final List<Integer> nonValidItems;

    public static boolean isValid(Item item) {
        return item instanceof ItemBlock && !invalidBlocks.contains(((ItemBlock)item).getBlock());
    }

    public static boolean isInteractBlock(Block block) {
        return block instanceof BlockFence || block instanceof BlockFenceGate || block instanceof BlockDoor || block instanceof BlockChest || block instanceof BlockEnderChest || block instanceof BlockEnchantmentTable || block instanceof BlockFurnace || block instanceof BlockAnvil || block instanceof BlockBed || block instanceof BlockWorkbench || block instanceof BlockNote || block instanceof BlockTrapDoor || block instanceof BlockHopper || block instanceof BlockDispenser || block instanceof BlockDaylightDetector || block instanceof BlockRedstoneRepeater || block instanceof BlockRedstoneComparator || block instanceof BlockButton || block instanceof BlockBeacon || block instanceof BlockBrewingStand || block instanceof BlockSign;
    }

    public static Block getBlock(BlockPos blockPos) {
        return Class153.mc.theWorld.getBlockState(blockPos).getBlock();
    }

    public static boolean isValidBock(BlockPos blockPos) {
        Block block = Minecraft.getMinecraft().theWorld.getBlockState(blockPos).getBlock();
        return !(block instanceof BlockLiquid) && !(block instanceof BlockAir) && !(block instanceof BlockChest) && !(block instanceof BlockFurnace);
    }

    public static boolean isAirBlock(BlockPos blockPos) {
        Block block = Minecraft.getMinecraft().theWorld.getBlockState(blockPos).getBlock();
        return block instanceof BlockAir;
    }

    public static boolean isValidStack(ItemStack itemStack) {
        Item item = itemStack.getItem();
        if (!(item instanceof ItemSlab || item instanceof ItemLeaves || item instanceof ItemSnow || item instanceof ItemBanner || item instanceof ItemFlintAndSteel)) {
            int item2;
            Iterator<Integer> var2 = nonValidItems.iterator();
            do {
                if (var2.hasNext()) continue;
                return true;
            } while (!item.equals(Item.getItemById(item2 = var2.next().intValue())));
            return false;
        }
        return false;
    }

    public static Vec3 floorVec3(Vec3 vec3) {
        return new Vec3(Math.floor(vec3.xCoord), Math.floor(vec3.yCoord), Math.floor(vec3.zCoord));
    }

    public static Material getMaterial(BlockPos blockPos) {
        return Class153.getBlock(blockPos).getMaterial();
    }

    public static boolean isReplaceable(BlockPos blockPos) {
        return Class153.getMaterial(blockPos).isReplaceable();
    }

    public static IBlockState getState(BlockPos pos) {
        return Class153.mc.theWorld.getBlockState(pos);
    }

    public static boolean canBeClicked(BlockPos pos) {
        return Class153.getBlock(pos).canCollideCheck(Class153.getState(pos), false);
    }

    public static String getBlockName(int id) {
        return Block.getBlockById(id).getLocalizedName();
    }

    public static boolean isFullBlock(BlockPos blockPos) {
        AxisAlignedBB axisAlignedBB = Class153.getBlock(blockPos).getCollisionBoundingBox(Class153.mc.theWorld, blockPos, Class153.getState(blockPos));
        return axisAlignedBB != null && axisAlignedBB.maxX - axisAlignedBB.minX == 1.0 && axisAlignedBB.maxY - axisAlignedBB.minY == 1.0 && axisAlignedBB.maxZ - axisAlignedBB.minZ == 1.0;
    }

    public static double getCenterDistance(BlockPos blockPos) {
        return Class153.mc.thePlayer.getDistance((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.5, (double)blockPos.getZ() + 0.5);
    }

    public static List<BlockPos> searchBlock(int range) {
        return Class153.searchBlocks(Class153.mc.thePlayer, range);
    }

    public static List<BlockPos> searchBlocks(EntityLivingBase entity, int range) {
        return Class153.searchBlocks(entity, range, range, range);
    }

    public static List<BlockPos> searchBlocks(EntityLivingBase entity, int xRange, int yRange, int zRange) {
        ArrayList<BlockPos> foundBlocks = new ArrayList<BlockPos>();
        WorldClient world = Class153.mc.theWorld;
        BlockPos entityPos = entity.getPosition();
        if (world == null) {
            return foundBlocks;
        }
        int startX = entityPos.getX() - xRange;
        int endX = entityPos.getX() + xRange;
        int startY = entityPos.getY() - yRange;
        int endY = entityPos.getY() + yRange;
        int startZ = entityPos.getZ() - zRange;
        int endZ = entityPos.getZ() + zRange;
        for (int x = startX; x <= endX; ++x) {
            for (int y = startY; y <= endY; ++y) {
                for (int z = startZ; z <= endZ; ++z) {
                    BlockPos pos = new BlockPos(x, y, z);
                    Block block = world.getBlockState(pos).getBlock();
                    if (block instanceof BlockAir) continue;
                    foundBlocks.add(pos);
                }
            }
        }
        return foundBlocks;
    }

    public static Map<BlockPos, Block> searchBlocks(int radius) {
        HashMap<BlockPos, Block> blocks = new HashMap<BlockPos, Block>();
        for (int x = radius; x > -radius; --x) {
            for (int y = radius; y > -radius; --y) {
                for (int z = radius; z > -radius; --z) {
                    BlockPos blockPos = new BlockPos(Class153.mc.thePlayer.lastTickPosX + (double)x, Class153.mc.thePlayer.lastTickPosY + (double)y, Class153.mc.thePlayer.lastTickPosZ + (double)z);
                    Block block = Class153.getBlock(blockPos);
                    blocks.put(blockPos, block);
                }
            }
        }
        return blocks;
    }

    public static boolean collideBlock(AxisAlignedBB axisAlignedBB, ICollide collide) {
        for (int x = MathHelper.floor_double(Class153.mc.thePlayer.getEntityBoundingBox().minX); x < MathHelper.floor_double(Class153.mc.thePlayer.getEntityBoundingBox().maxX) + 1; ++x) {
            for (int z = MathHelper.floor_double(Class153.mc.thePlayer.getEntityBoundingBox().minZ); z < MathHelper.floor_double(Class153.mc.thePlayer.getEntityBoundingBox().maxZ) + 1; ++z) {
                Block block = Class153.getBlock(new BlockPos(x, axisAlignedBB.minY, z));
                if (block == null || collide.collideBlock(block)) continue;
                return false;
            }
        }
        return true;
    }

    public static boolean collideBlockIntersects(AxisAlignedBB axisAlignedBB, ICollide collide) {
        for (int x = MathHelper.floor_double(Class153.mc.thePlayer.getEntityBoundingBox().minX); x < MathHelper.floor_double(Class153.mc.thePlayer.getEntityBoundingBox().maxX) + 1; ++x) {
            for (int z = MathHelper.floor_double(Class153.mc.thePlayer.getEntityBoundingBox().minZ); z < MathHelper.floor_double(Class153.mc.thePlayer.getEntityBoundingBox().maxZ) + 1; ++z) {
                AxisAlignedBB boundingBox;
                BlockPos blockPos = new BlockPos(x, axisAlignedBB.minY, z);
                Block block = Class153.getBlock(blockPos);
                if (block == null || !collide.collideBlock(block) || (boundingBox = block.getCollisionBoundingBox(Class153.mc.theWorld, blockPos, Class153.getState(blockPos))) == null || !Class153.mc.thePlayer.getEntityBoundingBox().intersectsWith(boundingBox)) continue;
                return true;
            }
        }
        return false;
    }

    static {
        invalidBlocks = Arrays.asList(Blocks.enchanting_table, Blocks.furnace, Blocks.carpet, Blocks.crafting_table, Blocks.trapped_chest, Blocks.chest, Blocks.dispenser, Blocks.air, Blocks.water, Blocks.lava, Blocks.flowing_water, Blocks.flowing_lava, Blocks.sand, Blocks.snow_layer, Blocks.torch, Blocks.anvil, Blocks.jukebox, Blocks.stone_button, Blocks.wooden_button, Blocks.lever, Blocks.noteblock, Blocks.stone_pressure_plate, Blocks.light_weighted_pressure_plate, Blocks.wooden_pressure_plate, Blocks.heavy_weighted_pressure_plate, Blocks.stone_slab, Blocks.wooden_slab, Blocks.stone_slab2, Blocks.red_mushroom, Blocks.brown_mushroom, Blocks.yellow_flower, Blocks.red_flower, Blocks.anvil, Blocks.glass_pane, Blocks.stained_glass_pane, Blocks.iron_bars, Blocks.cactus, Blocks.ladder, Blocks.web);
        nonValidItems = Arrays.asList(30, 58, 116, 158, 23, 6, 54, 146, 130, 26, 50, 76, 46, 37, 38);
        mc = Minecraft.getMinecraft();
    }

    public interface ICollide {
        boolean collideBlock(Block var1);
    }
}

