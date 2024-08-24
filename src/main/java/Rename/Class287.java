package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import tech.skidonion.obfuscator.annotations.StringEncryption;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
@StringEncryption
public class Class287
extends Class252 {
    private final Class259 delay = new Class259("Delay", 1000.0, 5000.0, 100.0, 10.0);
    private final Class241 timer = new Class241();

    public Class287() {
        super("Class287", Class263.Misc);
    }

    @Class14
    public void onUpdate(Class50 event) {
        if (this.timer.hasReached(this.delay.getValue() * 1.5)) {
            Class212.sendPacketNoEvent(new Class362(Class266.getRandomText()));
            this.timer.reset();
        }
    }
}

