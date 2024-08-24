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
public class Class274
extends Class252 {
    private final Class257 mode = new Class257("Mode", "Class76", "Class76");
    private final Class259 speed = new Class259("Class273", 0.45, 10.0, 0.1, 0.1);

    public Class274() {
        super("Class274", Class263.Movement);
    }

    @Class14
    public void onUpdate(Class50 event) {
        this.setSuffix(this.mode.getMode());
        if (this.mode.is("Class76")) {
            if (Class274.mc.thePlayer.onGround && Class210.isMoving()) {
                Class274.mc.thePlayer.jump();
                Class210.moveFlying(this.speed.getValue());
            }
            if (Class274.mc.thePlayer.onGround) {
                this.setState(false);
            }
        }
    }
}

