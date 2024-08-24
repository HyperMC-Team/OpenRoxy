package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

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
public class Class317
extends Class252 {
    private float old;

    public Class317() {
        super("Class317", Class263.Render);
    }

    @Override
    public void onEnable() {
        this.old = Class317.mc.gameSettings.gammaSetting;
    }

    @Class14
    private void onTick(Class52 event) {
        Class317.mc.gameSettings.gammaSetting = 1.5999999E7f;
    }

    @Override
    public void onDisable() {
        Class317.mc.gameSettings.gammaSetting = this.old;
    }
}

