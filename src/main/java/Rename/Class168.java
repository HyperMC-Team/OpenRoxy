package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import net.minecraft.client.settings.KeyBinding;
import org.lwjgl.input.Keyboard;

public class Class168
implements Class3 {
    public static boolean isPressed(KeyBinding key) {
        return Keyboard.isKeyDown(key.getKeyCode());
    }

    public static void resetKeybinding(KeyBinding key) {
        key.pressed = Class168.mc.currentScreen == null && Class168.isPressed(key);
    }

    public static void resetKeybindings(KeyBinding ... keys) {
        for (KeyBinding key : keys) {
            Class168.resetKeybinding(key);
        }
    }
}

