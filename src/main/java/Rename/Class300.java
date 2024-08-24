package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiIngameMenu;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAppleGold;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemEgg;
import net.minecraft.item.ItemEnderPearl;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemSnowball;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraft.network.Packet;
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
public class Class300
extends Class252 {
    private final Class259 delay = new Class259("Delay", 0.0, 300.0, 0.0, 10.0);
    public final Class254 instant = new Class254("Instant", false);
    public final Class254 openinv = new Class254("OpenInv", false);
    private final Class254 autodis = new Class254("AutoDisable", true);
    public String[] serverItems = new String[]{"\u9009\u62e9\u6e38\u620f", "\u52a0\u5165\u6e38\u620f", "\u804c\u4e1a\u9009\u62e9\u83dc\u5355", "\u79bb\u5f00\u5bf9\u5c40", "\u518d\u6765\u4e00\u5c40", "selector", "tracking compass", "(right click)", "tienda ", "perfil", "salir", "shop", "collectibles", "game", "profil", "lobby", "show all", "hub", "friends only", "cofre", "(click", "teleport", "play", "exit", "hide all", "jeux", "gadget", " (activ", "emote", "amis", "bountique", "choisir", "choose "};
    private final int[] bestArmorPieces = new int[4];
    private final List<Integer> trash = new ArrayList<Integer>();
    private final int[] bestToolSlots = new int[3];
    private final List<Integer> gappleStackSlots = new ArrayList<Integer>();
    private int bestSwordSlot;
    private int bestsbSlot;
    private int bestPearlSlot;
    private int bestBowSlot;
    private boolean serverOpen;
    private boolean clientOpen;
    private boolean nextTickCloseInventory;
    private int ticksSinceLastClick;

    public Class300() {
        super("Class300", Class263.Player);
    }

    @Class14
    public void onTick(Class52 event) {
        if (Class300.mc.thePlayer == null || Class300.mc.theWorld == null) {
            return;
        }
        if (Class300.mc.thePlayer.ticksExisted < 10 && this.autodis.isEnabled()) {
            this.setState(false);
        }
    }

    @Class14
    private void onPacket(Class33 event) {
        if (this.isGapple()) {
            return;
        }
        Packet<?> packet = event.getPacket();
        if (packet instanceof Class403) {
            this.clientOpen = false;
            this.serverOpen = false;
        }
    }

    @Class14
    private void onPacketSend(Class35 event) {
        if (this.isGapple()) {
            return;
        }
        Packet<?> packet = event.getPacket();
        if (packet instanceof Class337 clientStatus) {
            if (clientStatus.getStatus() == Class337.EnumState.OPEN_INVENTORY_ACHIEVEMENT) {
                this.clientOpen = true;
                this.serverOpen = true;
            }
        } else if (packet instanceof Class363 packetCloseWindow) {
            if (packetCloseWindow.windowId == Class300.mc.thePlayer.inventoryContainer.windowId) {
                this.clientOpen = false;
                this.serverOpen = false;
            }
        } else if (packet instanceof Class360 && !Class300.mc.thePlayer.isUsingItem()) {
            this.ticksSinceLastClick = 0;
        }
    }

    private boolean dropItem(List<Integer> listOfSlots) {
        if (!listOfSlots.isEmpty()) {
            int slot = listOfSlots.removeFirst();
            Class219.windowClick(mc, slot, 1, Class219.ClickType.DROP_ITEM);
            return true;
        }
        return false;
    }

    @Class14
    private void onMotion(Class26 event) {
        if (this.isGapple()) {
            return;
        }
        if (!Class300.mc.thePlayer.isUsingItem() && (Class300.mc.currentScreen == null || Class300.mc.currentScreen instanceof GuiChat || Class300.mc.currentScreen instanceof GuiInventory || Class300.mc.currentScreen instanceof GuiIngameMenu)) {
            ++this.ticksSinceLastClick;
            if ((double)this.ticksSinceLastClick < Math.floor(this.delay.getValue() / 50.0) && !this.instant.isEnabled()) {
                return;
            }
            if (this.clientOpen || Class300.mc.currentScreen == null && !this.openinv.isEnabled()) {
                boolean busy;
                this.clear();
                for (int slot = 5; slot < 45; ++slot) {
                    ItemStack stack = Class300.mc.thePlayer.inventoryContainer.getSlot(slot).getStack();
                    if (stack == null) continue;
                    if (stack.getItem() instanceof ItemSnowball || stack.getItem() instanceof ItemEgg) {
                        this.bestsbSlot = slot;
                    }
                    if (stack.getItem() instanceof ItemSword && Class219.isBestSword(Class300.mc.thePlayer, stack)) {
                        this.bestSwordSlot = slot;
                        continue;
                    }
                    if (stack.getItem() instanceof ItemTool && Class219.isBestTool(Class300.mc.thePlayer, stack)) {
                        int toolType = Class219.getToolType(stack);
                        if (toolType == -1 || slot == this.bestToolSlots[toolType]) continue;
                        this.bestToolSlots[toolType] = slot;
                        continue;
                    }
                    Item toolType = stack.getItem();
                    if (toolType instanceof ItemArmor armor) {
                        if (Class219.isBestArmor(Class300.mc.thePlayer, stack)) {
                            int pieceSlot = this.bestArmorPieces[armor.armorType];
                            if (pieceSlot != -1 && slot == pieceSlot) continue;
                            this.bestArmorPieces[armor.armorType] = slot;
                            continue;
                        }
                    }
                    if (stack.getItem() instanceof ItemBow && Class219.isBestBow(Class300.mc.thePlayer, stack)) {
                        if (slot == this.bestBowSlot) continue;
                        this.bestBowSlot = slot;
                        continue;
                    }
                    if (stack.getItem() instanceof ItemAppleGold) {
                        this.gappleStackSlots.add(slot);
                        continue;
                    }
                    if (stack.getItem() instanceof ItemEnderPearl) {
                        this.bestPearlSlot = slot;
                        continue;
                    }
                    if (this.trash.contains(slot) || Class300.isValidStack(stack)) continue;
                    if (!Arrays.stream(this.serverItems).noneMatch(stack.getDisplayName()::contains)) continue;
                    this.trash.add(slot);
                }
                boolean bl = busy = !this.trash.isEmpty() || this.equipArmor(false) || this.sortItems(false) || Class262.getModule(Class278.class).isState();
                if (!busy) {
                    if (this.nextTickCloseInventory) {
                        this.close();
                        this.nextTickCloseInventory = false;
                    } else {
                        this.nextTickCloseInventory = true;
                    }
                    return;
                }
                boolean waitUntilNextTick = !this.serverOpen;
                this.open();
                if (this.nextTickCloseInventory) {
                    this.nextTickCloseInventory = false;
                }
                if (waitUntilNextTick) {
                    return;
                }
                if (this.equipArmor(true)) {
                    return;
                }
                if (this.dropItem(this.trash)) {
                    return;
                }
                this.sortItems(true);
            }
        }
    }

    private boolean sortItems(boolean moveItems) {
        block16: {
            int mostBlocksSlot;
            int goodBlockSlot;
            block17: {
                int goodsbSlot = 44;
                if (this.bestsbSlot != -1 && this.bestsbSlot != goodsbSlot) {
                    if (moveItems) {
                        this.putItemInSlot(goodsbSlot, this.bestsbSlot);
                        this.bestsbSlot = goodsbSlot;
                    }
                    return true;
                }
                int goodSwordSlot = 36;
                if (this.bestSwordSlot != -1 && this.bestSwordSlot != goodSwordSlot) {
                    if (moveItems) {
                        this.putItemInSlot(goodSwordSlot, this.bestSwordSlot);
                        this.bestSwordSlot = goodSwordSlot;
                    }
                    return true;
                }
                int goodBowSlot = 40;
                if (this.bestBowSlot != -1 && this.bestBowSlot != goodBowSlot) {
                    if (moveItems) {
                        this.putItemInSlot(goodBowSlot, this.bestBowSlot);
                        this.bestBowSlot = goodBowSlot;
                    }
                    return true;
                }
                int goodGappleSlot = 38;
                if (!this.gappleStackSlots.isEmpty()) {
                    this.gappleStackSlots.sort(Comparator.comparingInt(slot -> Class300.mc.thePlayer.inventoryContainer.getSlot(slot.intValue()).getStack().stackSize));
                    int bestGappleSlot = this.gappleStackSlots.getFirst();
                    if (bestGappleSlot != goodGappleSlot) {
                        if (moveItems) {
                            this.putItemInSlot(goodGappleSlot, bestGappleSlot);
                            this.gappleStackSlots.set(0, goodGappleSlot);
                        }
                        return true;
                    }
                }
                int[] toolSlots = new int[]{39, 41, 42};
                for (int toolSlot : this.bestToolSlots) {
                    int type;
                    if (toolSlot == -1 || (type = Class219.getToolType(Class300.mc.thePlayer.inventoryContainer.getSlot(toolSlot).getStack())) == -1 || toolSlot == toolSlots[type]) continue;
                    if (moveItems) {
                        this.putToolsInSlot(type, toolSlots);
                    }
                    return true;
                }
                goodBlockSlot = 37;
                mostBlocksSlot = this.getMostBlocks();
                if (mostBlocksSlot == -1 || mostBlocksSlot == goodBlockSlot) break block16;
                Slot dss = Class300.mc.thePlayer.inventoryContainer.getSlot(goodBlockSlot);
                ItemStack dsis = dss.getStack();
                if (dsis == null || !(dsis.getItem() instanceof ItemBlock) || dsis.stackSize < Class300.mc.thePlayer.inventoryContainer.getSlot(mostBlocksSlot).getStack().stackSize) break block17;
                if (Arrays.stream(this.serverItems).noneMatch(dsis.getDisplayName().toLowerCase()::contains)) break block16;
            }
            this.putItemInSlot(goodBlockSlot, mostBlocksSlot);
        }
        int goodPearlSlot = 43;
        if (this.bestPearlSlot != -1 && this.bestPearlSlot != goodPearlSlot) {
            if (moveItems) {
                this.putItemInSlot(goodPearlSlot, this.bestPearlSlot);
                this.bestPearlSlot = goodPearlSlot;
            }
            return true;
        }
        return false;
    }

    public int getMostBlocks() {
        int stack = 0;
        int biggestSlot = -1;
        for (int i = 9; i < 45; ++i) {
            Slot slot = Class300.mc.thePlayer.inventoryContainer.getSlot(i);
            ItemStack is = slot.getStack();
            if (is == null || !(is.getItem() instanceof ItemBlock) || is.stackSize <= stack) continue;
            if (!Arrays.stream(this.serverItems).noneMatch(is.getDisplayName().toLowerCase()::contains)) continue;
            stack = is.stackSize;
            biggestSlot = i;
        }
        return biggestSlot;
    }

    private boolean equipArmor(boolean moveItems) {
        for (int i = 0; i < this.bestArmorPieces.length; ++i) {
            int armorPieceSlot;
            ItemStack stack;
            int piece = this.bestArmorPieces[i];
            if (piece == -1 || (stack = Class300.mc.thePlayer.inventoryContainer.getSlot(armorPieceSlot = i + 5).getStack()) != null) continue;
            if (moveItems) {
                Class219.windowClick(mc, piece, 0, Class219.ClickType.SHIFT_CLICK);
            }
            return true;
        }
        return false;
    }

    private void putItemInSlot(int slot, int slotIn) {
        Class219.windowClick(mc, slotIn, slot - 36, Class219.ClickType.SWAP_WITH_HOT_BAR_SLOT);
    }

    private void putToolsInSlot(int tool, int[] toolSlots) {
        int toolSlot = toolSlots[tool];
        Class219.windowClick(mc, this.bestToolSlots[tool], toolSlot - 36, Class219.ClickType.SWAP_WITH_HOT_BAR_SLOT);
        this.bestToolSlots[tool] = toolSlot;
    }

    private static boolean isValidStack(ItemStack stack) {
        return stack.getItem() instanceof ItemBlock && Class219.isStackValidToPlace(stack) || stack.getItem() instanceof ItemPotion && Class219.isBuffPotion(stack) || stack.getItem() instanceof ItemFood && Class219.isGoodFood(stack) || Class219.isGoodItem(stack.getItem());
    }

    @Override
    public void onEnable() {
        this.ticksSinceLastClick = 0;
        this.serverOpen = this.clientOpen = Class300.mc.currentScreen instanceof GuiInventory;
    }

    @Override
    public void onDisable() {
        this.close();
        this.clear();
    }

    private void open() {
        if (!this.clientOpen && !this.serverOpen) {
            Class300.mc.thePlayer.sendQueue.addToSendQueue(new Class337(Class337.EnumState.OPEN_INVENTORY_ACHIEVEMENT));
            this.serverOpen = true;
        }
    }

    private void close() {
        if (!this.clientOpen && this.serverOpen) {
            Class300.mc.thePlayer.sendQueue.addToSendQueue(new Class363(Class300.mc.thePlayer.inventoryContainer.windowId));
            this.serverOpen = false;
        }
    }

    private void clear() {
        this.trash.clear();
        this.bestBowSlot = -1;
        this.bestSwordSlot = -1;
        this.bestsbSlot = -1;
        this.gappleStackSlots.clear();
        Arrays.fill(this.bestArmorPieces, -1);
        Arrays.fill(this.bestToolSlots, -1);
    }
}

