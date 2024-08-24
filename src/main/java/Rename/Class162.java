package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import com.mojang.realmsclient.gui.ChatFormatting;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.minecraft.util.ChatComponentText;
import tech.skidonion.obfuscator.annotations.Renamer;
import tech.skidonion.obfuscator.annotations.StringEncryption;

@Renamer
@StringEncryption
public class Class162
implements Class3 {
    private static final String prefix = "[" + ChatFormatting.DARK_AQUA + Class162.name() + ChatFormatting.RESET + "]";

    public static void print(Object message) {
        System.out.println(message);
    }

    public static void addChatMessage(String message) {
        if (Class162.mc.thePlayer == null) {
            return;
        }
        Class162.mc.thePlayer.addChatMessage(new ChatComponentText(prefix + " " + message));
    }

    public static void addIRCMessage(String message) {
        if (Class162.mc.thePlayer == null) {
            return;
        }
        Class162.mc.thePlayer.addChatMessage(new ChatComponentText(message));
    }

    private static String name() {
        String name = "Riec";
        if (!Class318.clientName.getString().isEmpty()) {
            name = Class318.clientName.getString().replace("%time%", new SimpleDateFormat("HH:mm").format(new Date()));
        }
        return name;
    }
}

