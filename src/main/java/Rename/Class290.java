package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
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
public class Class290
extends Class252 {
    private static final Class254 armor = new Class254("ArmorColor", true);
    private static final Class254 color = new Class254("Color", true);
    private static final Class254 scoreboard = new Class254("ScoreboardTeam", true);

    public Class290() {
        super("Class290", Class263.Misc);
    }

    public static boolean isSameTeam(Entity entity) {
        if (entity instanceof EntityPlayer entityPlayer) {
            return Class262.getModule(Class290.class).isState() && (armor.isEnabled() && Class220.armorTeam(entityPlayer) || color.isEnabled() && Class220.colorTeam(entityPlayer) || scoreboard.isEnabled() && Class220.scoreTeam(entityPlayer));
        }
        return false;
    }
}

