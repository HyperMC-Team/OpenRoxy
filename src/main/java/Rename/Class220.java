package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.util.ArrayList;
import java.util.Comparator;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAnvilBlock;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public final class Class220
implements Class3 {
    public static boolean scoreTeam(EntityPlayer entityPlayer) {
        return Class220.mc.thePlayer.isOnSameTeam(entityPlayer);
    }

    public static boolean colorTeam(EntityPlayer entityPlayer) {
        String targetName = entityPlayer.getDisplayName().getFormattedText().replace("\u00a7r", "");
        String clientName = Class220.mc.thePlayer.getDisplayName().getFormattedText().replace("\u00a7r", "");
        return targetName.startsWith("\u00a7" + clientName.charAt(1));
    }

    public static boolean isBlockBlacklisted(Item item) {
        return item instanceof ItemAnvilBlock || item.getUnlocalizedName().contains("sand") || item.getUnlocalizedName().contains("gravel") || item.getUnlocalizedName().contains("ladder") || item.getUnlocalizedName().contains("tnt") || item.getUnlocalizedName().contains("chest") || item.getUnlocalizedName().contains("web");
    }

    public static boolean armorTeam(EntityPlayer entityPlayer) {
        if (Class220.mc.thePlayer.inventory.armorInventory[3] != null && entityPlayer.inventory.armorInventory[3] != null) {
            ItemStack myHead = Class220.mc.thePlayer.inventory.armorInventory[3];
            ItemArmor myItemArmor = (ItemArmor)myHead.getItem();
            ItemStack entityHead = entityPlayer.inventory.armorInventory[3];
            ItemArmor entityItemArmor = (ItemArmor)entityHead.getItem();
            return String.valueOf(entityItemArmor.getColor(entityHead)).equals("10511680") || myItemArmor.getColor(myHead) == entityItemArmor.getColor(entityHead);
        }
        return false;
    }

    public static boolean canEntityBeSeen(Entity e) {
        boolean see;
        Vec3 vec1 = new Vec3(Class220.mc.thePlayer.posX, Class220.mc.thePlayer.posY + (double) Class220.mc.thePlayer.getEyeHeight(), Class220.mc.thePlayer.posZ);
        AxisAlignedBB box = e.getEntityBoundingBox();
        Vec3 vec2 = new Vec3(e.posX, e.posY + (double)(e.getEyeHeight() / 1.32f), e.posZ);
        double minx = e.posX - 0.25;
        double maxx = e.posX + 0.25;
        double miny = e.posY;
        double maxy = e.posY + Math.abs(e.posY - box.maxY);
        double minz = e.posZ - 0.25;
        double maxz = e.posZ + 0.25;
        boolean bl = see = Class220.mc.theWorld.rayTraceBlocks(vec1, vec2) == null;
        if (see) {
            return true;
        }
        vec2 = new Vec3(maxx, miny, minz);
        boolean bl2 = see = Class220.mc.theWorld.rayTraceBlocks(vec1, vec2) == null;
        if (see) {
            return true;
        }
        vec2 = new Vec3(minx, miny, minz);
        boolean bl3 = see = Class220.mc.theWorld.rayTraceBlocks(vec1, vec2) == null;
        if (see) {
            return true;
        }
        vec2 = new Vec3(minx, miny, maxz);
        boolean bl4 = see = Class220.mc.theWorld.rayTraceBlocks(vec1, vec2) == null;
        if (see) {
            return true;
        }
        vec2 = new Vec3(maxx, miny, maxz);
        boolean bl5 = see = Class220.mc.theWorld.rayTraceBlocks(vec1, vec2) == null;
        if (see) {
            return true;
        }
        vec2 = new Vec3(maxx, maxy, minz);
        boolean bl6 = see = Class220.mc.theWorld.rayTraceBlocks(vec1, vec2) == null;
        if (see) {
            return true;
        }
        vec2 = new Vec3(minx, maxy, minz);
        boolean bl7 = see = Class220.mc.theWorld.rayTraceBlocks(vec1, vec2) == null;
        if (see) {
            return true;
        }
        vec2 = new Vec3(minx, maxy, maxz - 0.1);
        boolean bl8 = see = Class220.mc.theWorld.rayTraceBlocks(vec1, vec2) == null;
        if (see) {
            return true;
        }
        vec2 = new Vec3(maxx, maxy, maxz);
        see = Class220.mc.theWorld.rayTraceBlocks(vec1, vec2) == null;
        return see;
    }

    public static Block block(double x, double y, double z) {
        return Class220.mc.theWorld.getBlockState(new BlockPos(x, y, z)).getBlock();
    }

    public static Class217 getEnumFacing(Vec3 position) {
        for (int x2 = -1; x2 <= 1; x2 += 2) {
            if (Class220.block(position.xCoord + (double)x2, position.yCoord, position.zCoord) instanceof BlockAir) continue;
            if (x2 > 0) {
                return new Class217(EnumFacing.WEST, new Vec3(x2, 0.0, 0.0));
            }
            return new Class217(EnumFacing.EAST, new Vec3(x2, 0.0, 0.0));
        }
        for (int y2 = -1; y2 <= 1; y2 += 2) {
            if (Class220.block(position.xCoord, position.yCoord + (double)y2, position.zCoord) instanceof BlockAir || y2 >= 0) continue;
            return new Class217(EnumFacing.UP, new Vec3(0.0, y2, 0.0));
        }
        for (int z2 = -1; z2 <= 1; z2 += 2) {
            if (Class220.block(position.xCoord, position.yCoord, position.zCoord + (double)z2) instanceof BlockAir) continue;
            if (z2 < 0) {
                return new Class217(EnumFacing.SOUTH, new Vec3(0.0, 0.0, z2));
            }
            return new Class217(EnumFacing.NORTH, new Vec3(0.0, 0.0, z2));
        }
        return null;
    }

    public static Vec3 getPlacePossibility(double offsetX, double offsetY, double offsetZ) {
        ArrayList<Vec3> possibilities = new ArrayList<Vec3>();
        int range = (int)(5.0 + (Math.abs(offsetX) + Math.abs(offsetZ)));
        for (int x = -range; x <= range; ++x) {
            for (int y = -range; y <= range; ++y) {
                for (int z = -range; z <= range; ++z) {
                    Block block = Class220.blockRelativeToPlayer(x, y, z);
                    if (block instanceof BlockAir) continue;
                    for (int x2 = -1; x2 <= 1; x2 += 2) {
                        possibilities.add(new Vec3(Class220.mc.thePlayer.posX + (double)x + (double)x2, Class220.mc.thePlayer.posY + (double)y, Class220.mc.thePlayer.posZ + (double)z));
                    }
                    for (int y2 = -1; y2 <= 1; y2 += 2) {
                        possibilities.add(new Vec3(Class220.mc.thePlayer.posX + (double)x, Class220.mc.thePlayer.posY + (double)y + (double)y2, Class220.mc.thePlayer.posZ + (double)z));
                    }
                    for (int z2 = -1; z2 <= 1; z2 += 2) {
                        possibilities.add(new Vec3(Class220.mc.thePlayer.posX + (double)x, Class220.mc.thePlayer.posY + (double)y, Class220.mc.thePlayer.posZ + (double)z + (double)z2));
                    }
                }
            }
        }
        possibilities.removeIf(vec3 -> Class220.mc.thePlayer.getDistance(vec3.xCoord, vec3.yCoord, vec3.zCoord) > 5.0 || !(Class220.block(vec3.xCoord, vec3.yCoord, vec3.zCoord) instanceof BlockAir));
        if (possibilities.isEmpty()) {
            return null;
        }
        possibilities.sort(Comparator.comparingDouble(vec3 -> {
            double d0 = Class220.mc.thePlayer.posX + offsetX - vec3.xCoord;
            double d1 = Class220.mc.thePlayer.posY - 1.0 + offsetY - vec3.yCoord;
            double d2 = Class220.mc.thePlayer.posZ + offsetZ - vec3.zCoord;
            return MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);
        }));
        return possibilities.get(0);
    }

    public static Block blockRelativeToPlayer(double offsetX, double offsetY, double offsetZ) {
        return Class220.mc.theWorld.getBlockState(new BlockPos(Class220.mc.thePlayer).add(offsetX, offsetY, offsetZ)).getBlock();
    }

    private Class220() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static class hyt {
        public static boolean isHoldingGodAxe(EntityPlayer player) {
            ItemStack holdingItem = player.getEquipmentInSlot(0);
            return hyt.isGodAxe(holdingItem);
        }

        public static boolean isGodAxe(ItemStack stack) {
            if (stack == null) {
                return false;
            }
            if (stack.getItem() != Items.golden_axe) {
                return false;
            }
            int durability = stack.getMaxDamage() - stack.getItemDamage();
            if (durability > 2) {
                return false;
            }
            NBTTagList enchantmentTagList = stack.getEnchantmentTagList();
            if (enchantmentTagList == null) {
                return false;
            }
            for (int i = 0; i < enchantmentTagList.tagCount(); ++i) {
                int level;
                NBTTagCompound nbt = (NBTTagCompound)enchantmentTagList.get(i);
                if (!nbt.hasKey("id") || !nbt.hasKey("lvl") || nbt.getInteger("id") != 16 || (level = nbt.getInteger("lvl")) < 666) continue;
                return true;
            }
            return false;
        }

        public static boolean isKBBall(ItemStack stack) {
            if (stack == null) {
                return false;
            }
            if (stack.getItem() != Items.slime_ball) {
                return false;
            }
            NBTTagList enchantmentTagList = stack.getEnchantmentTagList();
            if (enchantmentTagList == null) {
                return false;
            }
            for (int i = 0; i < enchantmentTagList.tagCount(); ++i) {
                int level;
                NBTTagCompound nbt = (NBTTagCompound)enchantmentTagList.get(i);
                if (!nbt.hasKey("id") || !nbt.hasKey("lvl") || nbt.getInteger("id") != 19 || (level = nbt.getInteger("lvl")) < 2) continue;
                return true;
            }
            return false;
        }

        public static int hasEatenGoldenApple(EntityPlayer player) {
            PotionEffect regenPotion = player.getActivePotionEffect(Potion.regeneration);
            if (regenPotion == null) {
                return -1;
            }
            if (regenPotion.getAmplifier() < 4) {
                return -1;
            }
            return regenPotion.getDuration();
        }

        public static int isRegen(EntityPlayer player) {
            PotionEffect regenPotion = player.getActivePotionEffect(Potion.regeneration);
            if (regenPotion == null) {
                return -1;
            }
            return regenPotion.getDuration();
        }

        public static int isStrength(EntityPlayer player) {
            PotionEffect strengthPotion = player.getActivePotionEffect(Potion.damageBoost);
            if (strengthPotion == null) {
                return -1;
            }
            return strengthPotion.getDuration();
        }

        public static boolean isInLobby() {
            return Class3.mc.theWorld.playerEntities.stream().anyMatch(e -> e.getName().contains("\u95ee\u9898\u53cd\u9988"));
        }
    }
}

