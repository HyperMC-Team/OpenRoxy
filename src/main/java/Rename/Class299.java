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
public class Class299
extends Class252 {
    private final Class259 health = new Class259("Health", 2.0, 20.0, 0.1, 0.1);

    public Class299() {
        super("Class299", Class263.Player);
    }

    @Class14
    public void onUpdate(Class50 event) {
        if ((double) Class299.mc.thePlayer.getHealth() <= this.health.getValue()) {
            Class299.mc.thePlayer.sendChatMessage("欣欣哥的自动回城");
            Class299.mc.thePlayer.sendChatMessage("/hub");
            Class135.post(Class136.SUCCESS, "HUB", "SilenceFix教你如何逃逸呢");
        }
    }
}

