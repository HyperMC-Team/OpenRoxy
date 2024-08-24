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
public class Class282 {
    public static final Class102 modernClickGui = new Class102();
    private static Class263 activeCategory = Class263.Combat;

    public static Class263 getActiveCategory() {
        return activeCategory;
    }

    public static void setActiveCategory(Class263 activeCategory) {
        Class282.activeCategory = activeCategory;
    }
}

