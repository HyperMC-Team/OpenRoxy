package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import net.minecraft.item.ItemStack;
import tech.skidonion.obfuscator.annotations.Renamer;
import tech.skidonion.obfuscator.annotations.StringEncryption;

@Renamer
@StringEncryption
public class Class21
implements Class3 {
    private int spoofedSlot;
    private boolean spoofing;

    public void startSpoofing(int slot) {
        this.spoofing = true;
        this.spoofedSlot = slot;
    }

    public void stopSpoofing() {
        this.spoofing = false;
    }

    public int getSpoofedSlot() {
        return this.spoofing ? this.spoofedSlot : Class21.mc.thePlayer.inventory.currentItem;
    }

    public ItemStack getSpoofedStack() {
        return this.spoofing ? Class21.mc.thePlayer.inventory.getStackInSlot(this.spoofedSlot) : Class21.mc.thePlayer.inventory.getCurrentItem();
    }

    public boolean isSpoofing() {
        return this.spoofing;
    }
}

