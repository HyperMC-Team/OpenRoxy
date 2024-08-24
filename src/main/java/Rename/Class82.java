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

import java.io.IOException;

import tech.skidonion.obfuscator.annotations.StringEncryption;

@StringEncryption
public class Class82 {
    private String ircPrefix = "!";
    private Class81 client;

    public void initialize() {
        Class13.Method1(this);
        this.client = new Class81();
    }

    @Class14
    public void onChatInput(Class42 event) {
        String message = event.getMessage();
        if (!message.startsWith(this.ircPrefix) || !Class262.getModule(Class272.class).isState()) {
            return;
        }
        event.setCancelled(true);
        String msg = message.substring(this.ircPrefix.length());
        this.sendPacket(new Class59(msg, Class87.uid, this.getRank(Class87.uid)));
    }

    public String getRank(String uid) {
        return switch (uid) {
            case "zyyzs", "KuChaZi" -> "Admin";
            default -> "Class57";
        };
    }

    public void sendPacket(Class76 packet) {
        try {
            this.client.getPacketManager().sendPacket(this.client.getPacketBuffer(), packet, 2);
        }
        catch (IOException e) {
            Class83.error("Error sending packet: " + e.getMessage());
        }
    }

    public String getIrcPrefix() {
        return this.ircPrefix;
    }

    public Class81 getClient() {
        return this.client;
    }

    public void setIrcPrefix(String ircPrefix) {
        this.ircPrefix = ircPrefix;
    }

    public void setClient(Class81 client) {
        this.client = client;
    }
}

