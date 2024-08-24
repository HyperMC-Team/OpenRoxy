package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.awt.Color;
import java.util.List;
import java.util.Optional;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.init.Items;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.inventory.ContainerBrewingStand;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.ContainerDispenser;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.ContainerHopper;
import net.minecraft.inventory.ContainerHorseInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSnowball;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.network.Packet;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.BlockPos;
import org.apache.commons.lang3.ArrayUtils;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.glu.GLU;
import tech.skidonion.obfuscator.annotations.Renamer;
import tech.skidonion.obfuscator.annotations.StringEncryption;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
@Renamer
@StringEncryption
public class Class285
extends Class252 {
    private final Class259 delay = new Class259("Delay", 0.0, 1000.0, 0.0, 10.0);
    public Class254 silentValue = new Class254("Silent", true);
    public final Class254 chestView = new Class254("Stealing View", false);
    private final Class254 autodis = new Class254("AutoDisable", true);
    private int nextDelay = 0;
    static Class475 timer = new Class475();
    static Class475 openChestTimer = new Class475();
    private BlockPos currentContainerPos;
    private static final int[] itemHelmet = new int[]{298, 302, 306, 310, 314};
    private static final int[] itemChestPlate = new int[]{299, 303, 307, 311, 315};
    private static final int[] itemLeggings = new int[]{300, 304, 308, 312, 316};
    private static final int[] itemBoots = new int[]{301, 305, 309, 313, 317};

    public Class285() {
        super("Class285", Class263.Player);
    }

    @Class14
    public void onRender2D(Class38 event) {
        if (this.chestView.isEnabled()) {
            if (Class285.mc.thePlayer.openContainer == null || Class285.mc.currentScreen == null) {
                return;
            }
            Container container = Class285.mc.thePlayer.openContainer;
            if (!(container instanceof ContainerChest || container instanceof ContainerFurnace || container instanceof ContainerBeacon || container instanceof ContainerDispenser || container instanceof ContainerHopper || container instanceof ContainerHorseInventory || container instanceof ContainerBrewingStand)) {
                return;
            }
            int slots = container.inventorySlots.size();
            int scaleFactor = event.getScaledResolution().getScaleFactor();
            if (slots > 0) {
                Class250 projection = this.calculate(this.currentContainerPos, scaleFactor);
                if (projection == null) {
                    return;
                }
                float roundX = (float)projection.x - 82.0f;
                float roundY = (float)projection.y / 1.5f;
                GlStateManager.pushMatrix();
                GlStateManager.translate(roundX + 82.0f, roundY + 30.0f, 0.0f);
                GlStateManager.translate(-(roundX + 82.0f), -(roundY + 30.0f), 0.0f);
                Class468.drawRound(roundX, roundY, 164.0f, 60.0f, 3.0f, new Color(0, 0, 0, 120));
                double startX = roundX + 5.0f;
                double startY = roundY + 5.0f;
                RenderItem itemRender = mc.getRenderItem();
                GlStateManager.pushMatrix();
                RenderHelper.enableGUIStandardItemLighting();
                itemRender.zLevel = 200.0f;
                for (Slot slot : container.inventorySlots) {
                    if (slot.inventory.equals(Class285.mc.thePlayer.inventory)) continue;
                    int x = (int)(startX + (double)(slot.slotNumber % 9 * 18));
                    int y = (int)(startY + (double)(slot.slotNumber / 9 * 18));
                    itemRender.renderItemAndEffectIntoGUI(slot.getStack(), x, y);
                }
                GlStateManager.popMatrix();
                itemRender.zLevel = 0.0f;
                GlStateManager.popMatrix();
                GlStateManager.disableLighting();
            }
        }
    }

    @Class14
    public void onSend(Class35 event) {
        Block block;
        Class355 wrapper;
        Packet<?> packet;
        if (this.chestView.isEnabled() && (packet = event.getPacket()) instanceof Class355 && (wrapper = (Class355)packet).getPosition() != null && (block = Class285.mc.theWorld.getBlockState(wrapper.getPosition()).getBlock()) instanceof BlockContainer) {
            this.currentContainerPos = wrapper.getPosition();
        }
    }

    @Class14
    public void onMotion(Class27 event) {
        Container i3;
        Container i22;
        if (this.isGapple()) {
            return;
        }
        if (this.isNull()) {
            return;
        }
        if (Class285.mc.thePlayer.ticksExisted < 10 && this.autodis.isEnabled()) {
            this.setState(false);
        }
        if (Class285.mc.thePlayer.openContainer == null) {
            return;
        }
        Container container = Class285.mc.thePlayer.openContainer;
        if (container instanceof ContainerFurnace container2) {
            if (this.isFurnaceEmpty(container2) && openChestTimer.delay(100.0f) && timer.delay(100.0f)) {
                Class285.mc.thePlayer.closeScreen();
                return;
            }
            for (int i222 = 0; i222 < container2.tileFurnace.getSizeInventory(); ++i222) {
                if (container2.tileFurnace.getStackInSlot(i222) == null || !timer.delay(this.nextDelay)) continue;
                Class285.mc.playerController.windowClick(container2.windowId, i222, 0, 1, Class285.mc.thePlayer);
                this.nextDelay = (int)(this.delay.getValue() * Class171.getRandomInRange(0.75, 1.25));
                timer.reset();
            }
        }
        if ((i22 = Class285.mc.thePlayer.openContainer) instanceof ContainerBrewingStand) {
            ContainerBrewingStand container2 = (ContainerBrewingStand)i22;
            if (this.isBrewingStandEmpty(container2) && openChestTimer.delay(100.0f) && timer.delay(100.0f)) {
                Class285.mc.thePlayer.closeScreen();
                return;
            }
            for (int i33 = 0; i33 < container2.tileBrewingStand.getSizeInventory(); ++i33) {
                if (container2.tileBrewingStand.getStackInSlot(i33) == null || !timer.delay(this.nextDelay)) continue;
                Class285.mc.playerController.windowClick(container2.windowId, i33, 0, 1, Class285.mc.thePlayer);
                this.nextDelay = (int)(this.delay.getValue() * Class171.getRandomInRange(0.75, 1.25));
                timer.reset();
            }
        }
        if ((i3 = Class285.mc.thePlayer.openContainer) instanceof ContainerChest) {
            ContainerChest container3 = (ContainerChest)i3;
            if (this.isChestEmpty(container3) && openChestTimer.delay(100.0f) && timer.delay(100.0f)) {
                Class285.mc.thePlayer.closeScreen();
                return;
            }
            for (int i4 = 0; i4 < container3.getLowerChestInventory().getSizeInventory(); ++i4) {
                if (container3.getLowerChestInventory().getStackInSlot(i4) == null || !timer.delay(this.nextDelay) || !this.isItemUseful(container3, i4)) continue;
                Class285.mc.playerController.windowClick(container3.windowId, i4, 0, 1, Class285.mc.thePlayer);
                this.nextDelay = (int)(this.delay.getValue() * Class171.getRandomInRange(0.75, 1.25));
                timer.reset();
            }
        }
    }

    private boolean isChestEmpty(ContainerChest c) {
        for (int i = 0; i < c.getLowerChestInventory().getSizeInventory(); ++i) {
            if (c.getLowerChestInventory().getStackInSlot(i) == null || !this.isItemUseful(c, i)) continue;
            return false;
        }
        return true;
    }

    private boolean isFurnaceEmpty(ContainerFurnace c) {
        for (int i = 0; i < c.tileFurnace.getSizeInventory(); ++i) {
            if (c.tileFurnace.getStackInSlot(i) == null) continue;
            return false;
        }
        return true;
    }

    private boolean isBrewingStandEmpty(ContainerBrewingStand c) {
        for (int i = 0; i < c.tileBrewingStand.getSizeInventory(); ++i) {
            if (c.tileBrewingStand.getStackInSlot(i) == null) continue;
            return false;
        }
        return true;
    }

    private boolean isItemUseful(ContainerChest c, int i) {
        ItemStack itemStack = c.getLowerChestInventory().getStackInSlot(i);
        Item item = itemStack.getItem();
        return item instanceof ItemAxe || item instanceof ItemPickaxe || item instanceof ItemFood || item instanceof ItemBow || item == Items.arrow || item instanceof ItemPotion && !Class285.isPotionNegative(itemStack) || item instanceof ItemSword && Class285.isBestSword(c, itemStack) || item instanceof ItemArmor && Class285.isBestArmor(c, itemStack) || item instanceof ItemBlock || item instanceof ItemEnderPearl || item instanceof ItemSnowball || item instanceof ItemEgg;
    }

    public static boolean isPotionNegative(ItemStack itemStack) {
        ItemPotion potion = (ItemPotion)itemStack.getItem();
        List<PotionEffect> potionEffectList = potion.getEffects(itemStack);
        return potionEffectList.stream().map(potionEffect -> Potion.potionTypes[potionEffect.getPotionID()]).anyMatch(Potion::isBadEffect);
    }

    public static boolean isBestSword(ContainerChest c, ItemStack item) {
        return true;
    }

    public static float getSwordDamage(ItemStack itemStack) {
        float damage = 0.0f;
        Optional attributeModifier = itemStack.getAttributeModifiers().values().stream().findFirst();
        if (attributeModifier.isPresent()) {
            damage = (float)((AttributeModifier)attributeModifier.get()).getAmount();
        }
        return damage + EnchantmentHelper.getModifierForCreature(itemStack, EnumCreatureAttribute.UNDEFINED);
    }

    public static boolean isBestArmor(ContainerChest c, ItemStack item) {
        float temppro;
        int i;
        float itempro1 = ((ItemArmor)item.getItem()).damageReduceAmount;
        float itempro2 = 0.0f;
        if (Class285.isContain(itemHelmet, Item.getIdFromItem(item.getItem()))) {
            for (i = 0; i < 45; ++i) {
                if (!Class285.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack() || !Class285.isContain(itemHelmet, Item.getIdFromItem(Class285.mc.thePlayer.inventoryContainer.getSlot(i).getStack().getItem())) || !((temppro = (float)((ItemArmor) Class285.mc.thePlayer.inventoryContainer.getSlot(i).getStack().getItem()).damageReduceAmount) > itempro2)) continue;
                itempro2 = temppro;
            }
            for (i = 0; i < c.getLowerChestInventory().getSizeInventory(); ++i) {
                if (c.getLowerChestInventory().getStackInSlot(i) == null || !Class285.isContain(itemHelmet, Item.getIdFromItem(c.getLowerChestInventory().getStackInSlot(i).getItem())) || !((temppro = (float)((ItemArmor)c.getLowerChestInventory().getStackInSlot(i).getItem()).damageReduceAmount) > itempro2)) continue;
                itempro2 = temppro;
            }
        }
        if (Class285.isContain(itemChestPlate, Item.getIdFromItem(item.getItem()))) {
            for (i = 0; i < 45; ++i) {
                if (!Class285.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack() || !Class285.isContain(itemChestPlate, Item.getIdFromItem(Class285.mc.thePlayer.inventoryContainer.getSlot(i).getStack().getItem())) || !((temppro = (float)((ItemArmor) Class285.mc.thePlayer.inventoryContainer.getSlot(i).getStack().getItem()).damageReduceAmount) > itempro2)) continue;
                itempro2 = temppro;
            }
            for (i = 0; i < c.getLowerChestInventory().getSizeInventory(); ++i) {
                if (c.getLowerChestInventory().getStackInSlot(i) == null || !Class285.isContain(itemChestPlate, Item.getIdFromItem(c.getLowerChestInventory().getStackInSlot(i).getItem())) || !((temppro = (float)((ItemArmor)c.getLowerChestInventory().getStackInSlot(i).getItem()).damageReduceAmount) > itempro2)) continue;
                itempro2 = temppro;
            }
        }
        if (Class285.isContain(itemLeggings, Item.getIdFromItem(item.getItem()))) {
            for (i = 0; i < 45; ++i) {
                if (!Class285.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack() || !Class285.isContain(itemLeggings, Item.getIdFromItem(Class285.mc.thePlayer.inventoryContainer.getSlot(i).getStack().getItem())) || !((temppro = (float)((ItemArmor) Class285.mc.thePlayer.inventoryContainer.getSlot(i).getStack().getItem()).damageReduceAmount) > itempro2)) continue;
                itempro2 = temppro;
            }
            for (i = 0; i < c.getLowerChestInventory().getSizeInventory(); ++i) {
                if (c.getLowerChestInventory().getStackInSlot(i) == null || !Class285.isContain(itemLeggings, Item.getIdFromItem(c.getLowerChestInventory().getStackInSlot(i).getItem())) || !((temppro = (float)((ItemArmor)c.getLowerChestInventory().getStackInSlot(i).getItem()).damageReduceAmount) > itempro2)) continue;
                itempro2 = temppro;
            }
        }
        if (Class285.isContain(itemBoots, Item.getIdFromItem(item.getItem()))) {
            for (i = 0; i < 45; ++i) {
                if (!Class285.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack() || !Class285.isContain(itemBoots, Item.getIdFromItem(Class285.mc.thePlayer.inventoryContainer.getSlot(i).getStack().getItem())) || !((temppro = (float)((ItemArmor) Class285.mc.thePlayer.inventoryContainer.getSlot(i).getStack().getItem()).damageReduceAmount) > itempro2)) continue;
                itempro2 = temppro;
            }
            for (i = 0; i < c.getLowerChestInventory().getSizeInventory(); ++i) {
                if (c.getLowerChestInventory().getStackInSlot(i) == null || !Class285.isContain(itemBoots, Item.getIdFromItem(c.getLowerChestInventory().getStackInSlot(i).getItem())) || !((temppro = (float)((ItemArmor)c.getLowerChestInventory().getStackInSlot(i).getItem()).damageReduceAmount) > itempro2)) continue;
                itempro2 = temppro;
            }
        }
        return itempro1 == itempro2;
    }

    public static boolean isContain(int[] arr, int targetValue) {
        return ArrayUtils.contains(arr, targetValue);
    }

    public Class250 calculate(BlockPos blockPos, int factor) {
        try {
            mc.getRenderManager();
            double renderX = RenderManager.getRenderPosX();
            mc.getRenderManager();
            double renderY = RenderManager.getRenderPosY();
            mc.getRenderManager();
            double renderZ = RenderManager.getRenderPosZ();
            double x = (double)blockPos.getX() + 0.5 - renderX;
            double y = (double)blockPos.getY() + 0.5 - renderY;
            double z = (double)blockPos.getZ() + 0.5 - renderZ;
            Class249 projectedCenter = Class285.project(x, y, z, factor);
            if (projectedCenter != null && projectedCenter.getZ() >= 0.0 && projectedCenter.getZ() < 1.0) {
                return new Class250(projectedCenter.getX(), projectedCenter.getY(), projectedCenter.getX(), projectedCenter.getY());
            }
            return null;
        }
        catch (Exception e) {
            return null;
        }
    }

    private static Class249 project(double x, double y, double z, int factor) {
        if (GLU.gluProject((float)x, (float)y, (float)z, ActiveRenderInfo.MODELVIEW, ActiveRenderInfo.PROJECTION, ActiveRenderInfo.VIEWPORT, ActiveRenderInfo.OBJECTCOORDS)) {
            return new Class249(ActiveRenderInfo.OBJECTCOORDS.get(0) / (float)factor, ((float)Display.getHeight() - ActiveRenderInfo.OBJECTCOORDS.get(1)) / (float)factor, ActiveRenderInfo.OBJECTCOORDS.get(2));
        }
        return null;
    }
}

