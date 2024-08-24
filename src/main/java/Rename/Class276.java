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
public class Class276
extends Class252 {
    private final Class259 speed = new Class259("Class273", 1.0, 3.0, 1.0, 0.1);

    public Class276() {
        super("Class276", Class263.Player);
    }

    @Class14
    private void onUpdate(Class50 event) {
        if (this.isNull()) {
            return;
        }
        Class276.mc.playerController.blockHitDelay = 0;
        if ((double) Class276.mc.playerController.curBlockDamageMP > 1.0 / this.speed.getValue()) {
            Class276.mc.playerController.curBlockDamageMP = 1.0f;
        }
    }
}

