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

import net.minecraft.entity.Entity;

public class Class56 {
    public static String[] friends = new String[0];

    public static boolean isIRCFriend(Entity entity) {
        for (String name : friends) {
            if (!entity.getName().equals(name)) continue;
            return true;
        }
        return false;
    }
}

