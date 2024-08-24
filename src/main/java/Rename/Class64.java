package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.util.EnumChatFormatting;
import tech.skidonion.obfuscator.annotations.StringEncryption;

@StringEncryption
public class Class64
implements Class78<Class70> {
    @Override
    public void handle(Class70 packet) {
        String formattedMessage = this.buildMessage(packet.getRank(), packet.getUsername(), packet.getMessage());
        Class162.addIRCMessage(ChatFormatting.DARK_AQUA + "[Class272]" + formattedMessage);
    }

    private String buildMessage(String rank, String username, String message) {
        EnumChatFormatting rankColor = this.getRankColor(rank);
        return rankColor + "[" + rank + "] " + EnumChatFormatting.WHITE + "(" + username + ") " + EnumChatFormatting.GRAY + message;
    }

    private EnumChatFormatting getRankColor(String rank) {
        return switch (rank.toLowerCase()) {
            case "admin" -> EnumChatFormatting.RED;
            case "dev" -> EnumChatFormatting.LIGHT_PURPLE;
            case "user" -> EnumChatFormatting.BLUE;
            default -> EnumChatFormatting.WHITE;
        };
    }
}

