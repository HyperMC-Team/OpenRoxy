package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
public class Class311
extends Class252 {
    public Class311() {
        super("Class311", Class263.World);
    }

    private void pickBlock() {
        for (int i = 8; i >= 0; --i) {
            ItemStack stack = Class311.mc.thePlayer.inventory.getStackInSlot(i);
            if (stack == null || !(stack.getItem() instanceof ItemBlock) || Class220.isBlockBlacklisted(stack.getItem()) || stack.stackSize <= 0) continue;
            Class311.mc.thePlayer.inventory.currentItem = i;
            break;
        }
    }
}

