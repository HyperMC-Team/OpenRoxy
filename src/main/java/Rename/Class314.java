package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import net.minecraft.util.ResourceLocation;
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
public class Class314
extends Class252 {
    private final Class257 capemods = new Class257("Style", "Yuzaki", "JiaRan", "Chimera", "Hanabi", "Astolfo", "Yuzaki", "KuChaZi", "Furina", "Mika", "Nekocat", "Nekocat2", "Paimon");

    public Class314() {
        super("Class314", Class263.Render);
    }

    @Override
    public void onDisable() {
        Class314.mc.thePlayer.setLocationOfCape(null);
        super.onDisable();
    }

    @Class14
    public void onUpdate(Class50 event) {
        if (this.isNull()) {
            return;
        }
        Class314.mc.thePlayer.setLocationOfCape(new ResourceLocation("bloodline/cape/" + this.capemods.getMode().toLowerCase() + ".png"));
    }
}

