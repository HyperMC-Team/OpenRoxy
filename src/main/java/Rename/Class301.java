package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

public class Class301
extends Class252 {
    private final Class257 mode = new Class257("Mode", "Spoof", "Vulcan", "Spoof");

    public Class301() {
        super("Class301", Class263.Player);
    }

    @Override
    public void onEnable() {
        super.onEnable();
    }

    @Class14
    public void onMotion(Class27 event) {
        this.setSuffix(this.mode.getMode());
        switch (this.mode.getMode()) {
            case "Spoof": {
                if (!(Class301.mc.thePlayer.fallDistance > 3.0f)) break;
                event.setOnGround(true);
                break;
            }
            case "Vulcan": {
                if (!(Class301.mc.thePlayer.fallDistance >= 3.0f)) break;
                Class301.mc.thePlayer.motionY = -0.07;
                event.setOnGround(true);
                event.setYaw((float)((double)event.getYaw() + 0.07));
                Class301.mc.thePlayer.fallDistance = 0.0f;
            }
        }
    }
}

