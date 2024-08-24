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

public class Class17
implements Class3 {
    @Class14
    public void onPacket(Class33 event) {
        if (event.getPacket() instanceof Class351 && Class17.mc.thePlayer.ticksExisted > 50) {
            Class212.sendPacket(new Class333(Class17.mc.thePlayer.inventory.currentItem % 8 + 1));
            Class212.sendC0F();
            Class212.sendPacket(new Class333(Class17.mc.thePlayer.inventory.currentItem));
        }
    }
}

