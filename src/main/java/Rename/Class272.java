package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.io.IOException;
import java.util.Objects;

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
public class Class272
extends Class252 {
    String name = "";

    public Class272() {
        super("IRC", Class263.Misc);
    }

    @Override
    public void onEnable() {
        Class1.instance.Method17().execute(() -> {
            try {
                Class1.instance.Method15().getClient().connect("103.40.13.87", 28673);
            }
            catch (IOException e) {
                Class162.print("Failed to connect to the server: " + e.getMessage());
            }
        });
    }

    @Override
    public void onDisable() {
        Class1.instance.Method15().getClient().close();
    }

    @Class14
    public void onWorldChange(Class55 e) {
        try {
            if (!Objects.equals(this.name, Class272.mc.thePlayer.getName())) {
                Class1.instance.Method15().getClient().getPacketManager().sendPacket(Class1.instance.Method15().getClient().getPacketBuffer(), new Class63(Class272.mc.thePlayer.getName()), 5);
                this.name = Class272.mc.thePlayer.getName();
            }
        }
        catch (IOException ex) {
            Class162.addChatMessage(ex.getMessage());
        }
    }
}

