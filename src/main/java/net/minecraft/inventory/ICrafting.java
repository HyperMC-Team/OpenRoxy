package net.minecraft.inventory;

import java.util.List;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public interface ICrafting {
    public void updateCraftingInventory(Container var1, List<ItemStack> var2);

    public void sendSlotContents(Container var1, int var2, ItemStack var3);

    public void sendProgressBarUpdate(Container var1, int var2, int var3);

    public void sendAllWindowProperties(Container var1, IInventory var2);
}

