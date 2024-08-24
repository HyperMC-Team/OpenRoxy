package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

public class Class208 {
    public static Class204 tempData;

    public static float getGlobalVar(String name) {
        if (name.equalsIgnoreCase("ticks")) {
            if (tempData == null) {
                return 0.0f;
            }
            return Class208.tempData.ticks;
        }
        if (name.equalsIgnoreCase("ticksAfterPunch")) {
            if (tempData == null) {
                return 0.0f;
            }
            return Class208.tempData.ticksAfterPunch;
        }
        return Float.POSITIVE_INFINITY;
    }
}

