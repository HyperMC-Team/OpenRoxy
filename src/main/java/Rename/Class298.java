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
import tech.skidonion.obfuscator.annotations.StringEncryption;

@StringEncryption
public class Class298
extends Class252 {
    public static boolean sprint = true;

    public Class298() {
        super("Class298", Class263.Movement);
    }

    @Class14
    public void onUpdate(Class50 event) {
        Class298.mc.gameSettings.keyBindSprint.setPressed(true);
        if (Class298.mc.thePlayer.sprintToggleTimer <= 0 && !Class298.mc.thePlayer.isInWeb && Class298.mc.gameSettings.keyBindForward.isKeyDown() && Class298.mc.thePlayer.isUsingItem()) {
            Class298.mc.thePlayer.setSprinting(true);
        }
    }
}

