package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MovingObjectPosition;
import org.lwjgl.input.Mouse;
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
public class Class305
extends Class252 {
    private final Class254 spoof = new Class254("Item spoof", true);
    private int oldSlot;
    private boolean wasDigging;

    public Class305() {
        super("Class305", Class263.Player);
    }

    @Override
    public void onDisable() {
        if (this.wasDigging) {
            Class305.mc.thePlayer.inventory.currentItem = this.oldSlot;
            this.wasDigging = false;
        }
        Class1.instance.Method12().stopSpoofing();
    }

    @Class14(value=3)
    public void onTick(Class52 event) {
        if ((Mouse.isButtonDown(0) || Class305.mc.gameSettings.keyBindAttack.isKeyDown()) && Class305.mc.objectMouseOver != null && Class305.mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
            Block block = Class305.mc.theWorld.getBlockState(Class305.mc.objectMouseOver.getBlockPos()).getBlock();
            float strength = 0.0f;
            if (!this.wasDigging) {
                this.oldSlot = Class305.mc.thePlayer.inventory.currentItem;
                if (this.spoof.isEnabled()) {
                    Class1.instance.Method12().startSpoofing(this.oldSlot);
                }
            }
            for (int i = 0; i <= 8; ++i) {
                float slotStrength;
                ItemStack stack = Class305.mc.thePlayer.inventory.getStackInSlot(i);
                if (stack == null || !((slotStrength = stack.getStrVsBlock(block)) > strength)) continue;
                Class305.mc.thePlayer.inventory.currentItem = i;
                strength = slotStrength;
            }
            this.wasDigging = true;
        } else if (this.wasDigging) {
            Class305.mc.thePlayer.inventory.currentItem = this.oldSlot;
            Class1.instance.Method12().stopSpoofing();
            this.wasDigging = false;
        } else {
            this.oldSlot = Class305.mc.thePlayer.inventory.currentItem;
        }
    }
}

