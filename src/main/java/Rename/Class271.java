package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.util.Arrays;
import java.util.List;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import tech.skidonion.obfuscator.annotations.StringEncryption;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
@StringEncryption
public final class Class271
extends Class252 {
    private boolean wasInContainer;
    private static final List<KeyBinding> keys = Arrays.asList(Class271.mc.gameSettings.keyBindForward, Class271.mc.gameSettings.keyBindBack, Class271.mc.gameSettings.keyBindLeft, Class271.mc.gameSettings.keyBindRight, Class271.mc.gameSettings.keyBindJump);

    public Class271() {
        super("Class271", Class263.Movement);
    }

    public static void updateStates() {
        if (Class271.mc.currentScreen != null) {
            keys.forEach(k -> KeyBinding.setKeyBindState(k.getKeyCode(), GameSettings.isKeyDown(k)));
        }
    }

    @Class14
    public void onMotionEvent(Class27 e) {
        boolean inContainer = Class271.mc.currentScreen instanceof GuiContainer;
        if (this.wasInContainer && !inContainer) {
            this.wasInContainer = false;
            Class168.resetKeybindings(Class271.mc.gameSettings.keyBindForward, Class271.mc.gameSettings.keyBindBack, Class271.mc.gameSettings.keyBindLeft, Class271.mc.gameSettings.keyBindRight, Class271.mc.gameSettings.keyBindJump);
        }
        if (inContainer) {
            this.wasInContainer = true;
            Class271.updateStates();
        }
    }
}

