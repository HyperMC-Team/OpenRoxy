package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import com.google.common.collect.Multimap;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.BlockFalling;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAppleGold;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class Class219
implements Class3 {
    public static final int INCLUDE_ARMOR_BEGIN = 5;
    public static final int EXCLUDE_ARMOR_BEGIN = 9;
    public static final int ONLY_HOT_BAR_BEGIN = 36;
    public static final int END = 45;

    private Class219() {
    }

    public static boolean hasSpaceHotbar() {
        for (int i = 36; i < 45; ++i) {
            ItemStack itemStack = Class219.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
            if (itemStack != null) continue;
            return true;
        }
        return false;
    }

    public static int findSlotMatching(EntityPlayerSP player, Predicate<ItemStack> cond) {
        for (int i = 44; i >= 9; --i) {
            ItemStack stack = player.inventoryContainer.getSlot(i).getStack();
            if (!cond.test(stack)) continue;
            return i;
        }
        return -1;
    }

    public static int getItemFromHotbar(int id) {
        for (int i = 0; i < 9; ++i) {
            ItemStack stack = Class219.mc.thePlayer.inventory.getStackInSlot(i);
            if (stack == null || Item.getIdFromItem(stack.getItem()) != id) continue;
            return i;
        }
        return -1;
    }

    public static int findItem(int startSlot, int endSlot, Item item) {
        for (int i = startSlot; i < endSlot; ++i) {
            ItemStack stack = Class219.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
            if (stack == null || stack.getItem() != item) continue;
            return i;
        }
        return -1;
    }

    public static float getSwordStrength(ItemStack stack) {
        Item item = stack.getItem();
        if (item instanceof ItemSword sword) {
            float sharpness = (float)EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, stack) * 1.25f;
            float fireAspect = (float)EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, stack) * 1.5f;
            return sword.getDamageVsEntity() + sharpness + fireAspect;
        }
        return 0.0f;
    }

    public static boolean hasFreeSlots(EntityPlayerSP player) {
        for (int i = 9; i < 45; ++i) {
            if (player.inventoryContainer.getSlot(i).getHasStack()) continue;
            return true;
        }
        return false;
    }

    public static boolean isValidStack(EntityPlayerSP player, ItemStack stack) {
        if (stack == null) {
            return false;
        }
        Item item = stack.getItem();
        if (item instanceof ItemSword) {
            return Class219.isBestSword(player, stack);
        }
        if (item instanceof ItemArmor) {
            return Class219.isBestArmor(player, stack);
        }
        if (item instanceof ItemTool) {
            return Class219.isBestTool(player, stack);
        }
        if (item instanceof ItemBow) {
            return Class219.isBestBow(player, stack);
        }
        if (item instanceof ItemFood) {
            return Class219.isGoodFood(stack);
        }
        if (item instanceof ItemBlock) {
            return Class219.isStackValidToPlace(stack);
        }
        if (item instanceof ItemPotion) {
            return Class219.isBuffPotion(stack);
        }
        return Class219.isGoodItem(item);
    }

    public static void swap(int slot, int switchSlot) {
        Minecraft.getMinecraft().playerController.windowClick(Minecraft.getMinecraft().thePlayer.inventoryContainer.windowId, slot, switchSlot, 2, Minecraft.getMinecraft().thePlayer);
    }

    public static void click(int slot) {
        Class219.mc.playerController.windowClick(Class219.mc.thePlayer.openContainer.windowId, slot, 0, 1, Class219.mc.thePlayer);
    }

    public static void swapSilent(int slot, int switchSlot) {
        short short1 = Class219.mc.thePlayer.openContainer.getNextTransactionID(Class219.mc.thePlayer.inventory);
        Class212.sendPacket(new Class360(Class219.mc.thePlayer.inventoryContainer.windowId, slot, switchSlot, 2, Class219.mc.thePlayer.inventory.getStackInSlot(slot), short1));
    }

    public static boolean isGoodItem(Item item) {
        return item instanceof ItemEnderPearl || item == Items.snowball || item == Items.egg || item == Items.arrow || item == Items.lava_bucket || item == Items.water_bucket;
    }

    public static boolean isBestSword(EntityPlayerSP player, ItemStack itemStack) {
        double damage = 0.0;
        ItemStack bestStack = null;
        for (int i = 9; i < 45; ++i) {
            double newDamage;
            ItemStack stack = player.inventoryContainer.getSlot(i).getStack();
            if (stack == null || !(stack.getItem() instanceof ItemSword) || !((newDamage = Class219.getItemDamage(stack)) > damage)) continue;
            damage = newDamage;
            bestStack = stack;
        }
        return bestStack == itemStack || Class219.getItemDamage(itemStack) > damage;
    }

    public static boolean isBestArmor(EntityPlayerSP player, ItemStack itemStack) {
        ItemArmor itemArmor = (ItemArmor)itemStack.getItem();
        double reduction = 0.0;
        ItemStack bestStack = null;
        for (int i = 5; i < 45; ++i) {
            double newReduction;
            ItemStack stack = player.inventoryContainer.getSlot(i).getStack();
            if (stack == null || !(stack.getItem() instanceof ItemArmor stackArmor) || stack.getItem().getUnlocalizedName().equalsIgnoreCase("item.helmetChain") || stack.getItem().getUnlocalizedName().equalsIgnoreCase("item.leggingsChain")) continue;
            if (stackArmor.armorType != itemArmor.armorType || !((newReduction = Class219.getDamageReduction(stack)) > reduction)) continue;
            reduction = newReduction;
            bestStack = stack;
        }
        return bestStack == itemStack || Class219.getDamageReduction(itemStack) > reduction;
    }

    public static int getToolType(ItemStack stack) {
        ItemTool tool = (ItemTool)stack.getItem();
        if (tool instanceof ItemPickaxe) {
            return 0;
        }
        if (tool instanceof ItemAxe) {
            return 1;
        }
        if (tool instanceof ItemSpade) {
            return 2;
        }
        return -1;
    }

    public static boolean isBestTool(EntityPlayerSP player, ItemStack itemStack) {
        int type = Class219.getToolType(itemStack);
        Tool bestTool = new Tool(-1, -1.0, null);
        for (int i = 9; i < 45; ++i) {
            double efficiency;
            ItemStack stack = player.inventoryContainer.getSlot(i).getStack();
            if (stack == null || !(stack.getItem() instanceof ItemTool) || type != Class219.getToolType(stack) || !((efficiency = Class219.getToolEfficiency(stack)) > bestTool.getEfficiency())) continue;
            bestTool = new Tool(i, efficiency, stack);
        }
        return bestTool.getStack() == itemStack || (double) Class219.getToolEfficiency(itemStack) > bestTool.getEfficiency();
    }

    public static boolean isBestBow(EntityPlayerSP player, ItemStack itemStack) {
        double bestBowDmg = -1.0;
        ItemStack bestBow = null;
        for (int i = 9; i < 45; ++i) {
            double damage;
            ItemStack stack = player.inventoryContainer.getSlot(i).getStack();
            if (stack == null || !(stack.getItem() instanceof ItemBow) || !((damage = Class219.getBowDamage(stack)) > bestBowDmg)) continue;
            bestBow = stack;
            bestBowDmg = damage;
        }
        return itemStack == bestBow || Class219.getBowDamage(itemStack) > bestBowDmg;
    }

    public static double getDamageReduction(ItemStack stack) {
        double reduction = 0.0;
        ItemArmor armor = (ItemArmor)stack.getItem();
        reduction += armor.damageReduceAmount;
        if (stack.isItemEnchanted()) {
            reduction += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.protection.effectId, stack) * 0.25;
        }
        return reduction;
    }

    public static boolean isBuffPotion(ItemStack stack) {
        ItemPotion potion = (ItemPotion)stack.getItem();
        List<PotionEffect> effects = potion.getEffects(stack);
        for (PotionEffect effect : effects) {
            if (!Potion.potionTypes[effect.getPotionID()].isBadEffect()) continue;
            return false;
        }
        return true;
    }

    public static double getBowDamage(ItemStack stack) {
        double damage = 0.0;
        if (stack.getItem() instanceof ItemBow && stack.isItemEnchanted()) {
            damage += EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, stack);
        }
        return damage;
    }

    public static boolean isGoodFood(ItemStack stack) {
        ItemFood food = (ItemFood)stack.getItem();
        return food instanceof ItemAppleGold || food.getHealAmount(stack) >= 4 && food.getSaturationModifier(stack) >= 0.3f;
    }

    public static float getToolEfficiency(ItemStack itemStack) {
        ItemTool tool = (ItemTool)itemStack.getItem();
        float efficiency = tool.getToolMaterial().getEfficiencyOnProperMaterial();
        int lvl = EnchantmentHelper.getEnchantmentLevel(Enchantment.efficiency.effectId, itemStack);
        if (efficiency > 1.0f && lvl > 0) {
            efficiency += (float)(lvl * lvl + 1);
        }
        return efficiency;
    }

    public static double getItemDamage(ItemStack stack) {
        double damage = 0.0;
        Multimap<String, AttributeModifier> attributeModifierMap = stack.getAttributeModifiers();
        for (String attributeName : attributeModifierMap.keySet()) {
            if (!attributeName.equals("generic.attackDamage")) continue;
            Iterator attributeModifiers = attributeModifierMap.get(attributeName).iterator();
            if (!attributeModifiers.hasNext()) break;
            damage += ((AttributeModifier)attributeModifiers.next()).getAmount();
            break;
        }
        if (stack.isItemEnchanted()) {
            damage += EnchantmentHelper.getEnchantmentLevel(Enchantment.fireAspect.effectId, stack);
            damage += (double)EnchantmentHelper.getEnchantmentLevel(Enchantment.sharpness.effectId, stack) * 1.25;
        }
        return damage;
    }

    public static void windowClick(Minecraft mc, int windowId, int slotId, int mouseButtonClicked, ClickType mode) {
        mc.playerController.windowClick(windowId, slotId, mouseButtonClicked, mode.ordinal(), mc.thePlayer);
    }

    public static void windowClick(Minecraft mc, int slotId, int mouseButtonClicked, ClickType mode) {
        mc.playerController.windowClick(mc.thePlayer.inventoryContainer.windowId, slotId, mouseButtonClicked, mode.ordinal(), mc.thePlayer);
    }

    public static boolean isStackValidToPlace(ItemStack stack) {
        return stack.stackSize >= 1 && Class219.validateBlock(Block.getBlockFromItem(stack.getItem()), BlockAction.PLACE);
    }

    public static boolean validateBlock(Block block, BlockAction action) {
        if (block instanceof BlockContainer) {
            return false;
        }
        Material material = block.getMaterial();
        return switch (action.ordinal()) {
            default -> throw new MatchException(null, null);
            case 0 -> {
                yield !(block instanceof BlockFalling) && block.isFullBlock() && block.isFullCube();
            }
            case 1 -> material.isReplaceable();
            case 2 -> block.isFullBlock() && block.isFullCube();
        };
    }

    private static class Tool {
        private final int slot;
        private final double efficiency;
        private final ItemStack stack;

        public Tool(int slot, double efficiency, ItemStack stack) {
            this.slot = slot;
            this.efficiency = efficiency;
            this.stack = stack;
        }

        public int getSlot() {
            return this.slot;
        }

        public double getEfficiency() {
            return this.efficiency;
        }

        public ItemStack getStack() {
            return this.stack;
        }
    }

    public enum ClickType {
        CLICK,
        SHIFT_CLICK,
        SWAP_WITH_HOT_BAR_SLOT,
        PLACEHOLDER,
        DROP_ITEM

    }

    public enum BlockAction {
        PLACE,
        REPLACE,
        PLACE_ON

    }
}

