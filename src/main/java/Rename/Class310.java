package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import net.minecraft.item.ItemBlock;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
public class Class310
extends Class252 {
    public Class259 tickDelay = new Class259("Tick delay", 0.0, 3.0, 0.0, 1.0);
    private boolean noblock;

    public Class310() {
        super("Class310", Class263.World);
    }

    @Class14
    public void onTick(Class52 e) {
        if (this.only()) {
            Class310.mc.rightClickDelayTimer = this.tickDelay.getValue().intValue();
            this.noblock = false;
        } else if (Class310.mc.rightClickDelayTimer < 6 && !this.noblock) {
            Class310.mc.rightClickDelayTimer = 6;
            this.noblock = true;
        }
    }

    private boolean only() {
        return Class310.mc.thePlayer.getHeldItem().getItem() instanceof ItemBlock;
    }
}

