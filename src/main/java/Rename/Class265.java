package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
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
public class Class265
extends Class252 {
    private final Class257 mode = new Class257("Mode", "Custom", "Hypixel", "Hyt", "Matrix", "RankCheck");
    private final Class254 movedOnce = new Class254("Moved", false);
    private final Class254 touchedGround = new Class254("TouchedGround", false);
    private final Class254 wasInvisible = new Class254("Invisible", false);
    private final Class254 enableTicksExsitedCheck = new Class254("EnableTicksExisted", false);
    private final Class259 ticksExisted = new Class259("TicksExisted", 0.0, 10000.0, 0.0, 50.0);
    private final Class254 enableIDCheck = new Class254("IDCheck", false);
    private final Class259 maximumID = new Class259("MaximumID", 1500000.0, 1.0E7, 1000000.0, 100000.0);
    private final Class257 quickMacroMode = new Class257("Hyt Mode", "BW4v4", "BW1v1", "BW32", "BW16", "BW4v4");
    private static final List<String> playerName = new ArrayList<String>();
    private final ArrayList<Player> custombots = new ArrayList();
    private final ArrayList<UUID> matrixBot = new ArrayList();

    public Class265() {
        super("AntiBot", Class263.Combat);
    }

    @Override
    public void onDisable() {
        this.custombots.clear();
        this.matrixBot.clear();
        super.onDisable();
    }

    @Class14
    private void onWorldLoad(Class55 event) {
        this.custombots.clear();
        this.matrixBot.clear();
    }

    @Class14
    private void onUpdate(Class50 event) {
        this.setSuffix(this.mode.getMode());
        switch (this.mode.getMode()) {
            case "Hypixel": {
                for (Entity entity : Class265.mc.theWorld.getLoadedEntityList()) {
                    if (!(entity instanceof EntityPlayer) || !entity.getName().contains("\u00a7") && (!entity.hasCustomName() || !entity.getCustomNameTag().contains(entity.getName()))) continue;
                    Class265.mc.theWorld.removeEntity(entity);
                }
                break;
            }
            case "Custom": {
                this.removeOld();
                for (EntityPlayer entityPlayer : Class265.mc.theWorld.playerEntities) {
                    boolean exist = false;
                    for (Player player : this.custombots) {
                        if (!player.player.getName().equalsIgnoreCase(entityPlayer.getName())) continue;
                        exist = true;
                    }
                    if (exist) continue;
                    this.custombots.add(new Player(entityPlayer));
                }
                break;
            }
        }
    }

    @Class14
    private void onPacketReceive(Class33 event) {
        Packet<?> packet = event.getPacket();
        if (this.mode.is("Hyt") && packet instanceof Class420 s02PacketChat) {
            if (s02PacketChat.getChatComponent().getUnformattedText().contains("\u83b7\u5f97\u80dc\u5229!") || s02PacketChat.getChatComponent().getUnformattedText().contains("\u6e38\u620f\u5f00\u59cb ...")) {
                this.clearAll();
            }
            switch (this.quickMacroMode.getMode()) {
                case "BW4v4": 
                case "BW1v1": 
                case "BW32": {
                    this.handleKillMessages(s02PacketChat, "\u6740\u6b7b\u4e86 (.*?)\\(", "\u8d77\u5e8a\u6218\u4e89>> (.*?) (\\((((.*?) \u6b7b\u4e86!)))");
                    break;
                }
                case "BW16": {
                    this.handleKillMessages(s02PacketChat, "\u51fb\u8d25\u4e86 (.*?)!", "\u73a9\u5bb6 (.*?)\u6b7b\u4e86\uff01");
                }
            }
        }
        if (this.mode.is("Matrix") && packet instanceof Class412) {
            for (Class412.AddPlayerData data : ((Class412)packet).getEntries()) {
                if (!((Class412)packet).getAction().equals(Class412.Action.ADD_PLAYER) || !data.getProfile().getProperties().isEmpty() || ((Class412)packet).getEntries().size() != 1 || mc.getNetHandler() == null || mc.getNetHandler().getPlayerInfo(data.getProfile().getName()) == null || this.matrixBot.contains(data.getProfile().getId())) continue;
                this.matrixBot.add(data.getProfile().getId());
            }
        }
    }

    private void clearAll() {
        playerName.clear();
    }

    private void handleKillMessages(Class420 s02PacketChat, String pattern1, String pattern2) {
        String name;
        Matcher matcher1 = Pattern.compile(pattern1).matcher(s02PacketChat.getChatComponent().getUnformattedText());
        Matcher matcher2 = Pattern.compile(pattern2).matcher(s02PacketChat.getChatComponent().getUnformattedText());
        if (matcher1.find()) {
            name = matcher1.group(1).trim();
            if (!(s02PacketChat.getChatComponent().getUnformattedText().contains(": \u8d77\u5e8a\u6218\u4e89>>") && s02PacketChat.getChatComponent().getUnformattedText().contains(": \u6740\u6b7b\u4e86") || name.isEmpty())) {
                this.addPlayerName(name);
            }
        }
        if (matcher2.find()) {
            name = matcher2.group(1).trim();
            if (!s02PacketChat.getChatComponent().getUnformattedText().contains(": \u8d77\u5e8a\u6218\u4e89>>") && s02PacketChat.getChatComponent().getUnformattedText().contains(": \u6740\u6b7b\u4e86") && !name.isEmpty()) {
                this.addPlayerName(name);
            }
        }
    }

    private void addPlayerName(String name) {
        playerName.add(name);
        new Thread(() -> {
            try {
                Thread.sleep(6000L);
                playerName.remove(name);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }).start();
    }

    public boolean isServerBot(Entity entity) {
        if (!this.isState()) {
            return false;
        }
        if (this.mode.is("Hypixel")) {
            return !entity.getDisplayName().getFormattedText().startsWith("\u00a7") || entity.isInvisible() || entity.getDisplayName().getFormattedText().toLowerCase().contains("npc");
        }
        if (this.mode.is("RankCheck")) {
            return !entity.getDisplayName().getUnformattedText().contains("Marcel") && !entity.getDisplayName().getUnformattedText().contains("\u7ea2 ") && !entity.getDisplayName().getUnformattedText().contains("\u9ec4 ") && !entity.getDisplayName().getUnformattedText().contains("\u84dd ") && !entity.getDisplayName().getUnformattedText().contains("\u7eff ") && !entity.getDisplayName().getUnformattedText().contains("R ") && !entity.getDisplayName().getUnformattedText().contains("B ") && !entity.getDisplayName().getUnformattedText().contains("G ") && !entity.getDisplayName().getUnformattedText().contains("Y ") && !entity.getDisplayName().getUnformattedText().contains("VIP") && !entity.getDisplayName().getUnformattedText().contains("MVP") && !entity.getDisplayName().getUnformattedText().contains("vip") && !entity.getDisplayName().getUnformattedText().contains("mvp") && !entity.getDisplayName().getUnformattedText().contains("Vip") && !entity.getDisplayName().getUnformattedText().contains("Mvp") && !entity.getDisplayName().getUnformattedText().contains("\u7763\u5bdf");
        }
        if (this.mode.is("Hyt") && playerName.contains(entity.getName())) {
            return true;
        }
        if (this.mode.is("Custom")) {
            for (Player custom : this.custombots) {
                if (!(entity instanceof EntityPlayer) || !custom.player.getName().equalsIgnoreCase(entity.getName())) continue;
                if (this.movedOnce.isEnabled() && !custom.moved()) {
                    return true;
                }
                if (this.touchedGround.isEnabled() && !entity.onGround) {
                    return true;
                }
                if (this.wasInvisible.isEnabled() && entity.isInvisible()) {
                    return true;
                }
                if (this.enableTicksExsitedCheck.isEnabled() && (double)entity.ticksExisted <= this.ticksExisted.getValue()) {
                    return true;
                }
                if (!this.enableIDCheck.isEnabled() || !((double)entity.getEntityId() >= this.maximumID.getValue())) continue;
                return true;
            }
            return false;
        }
        if (this.mode.is("Matrix")) {
            return this.matrixBot.contains(entity.getUniqueID());
        }
        return false;
    }

    private void removeOld() {
        Iterator<Player> it = this.custombots.iterator();
        HashSet<String> playerNames = new HashSet<String>();
        for (EntityPlayer player : Class265.mc.theWorld.playerEntities) {
            playerNames.add(player.getName());
        }
        while (it.hasNext()) {
            Player player = it.next();
            if (playerNames.contains(player.player.getName())) continue;
            it.remove();
        }
    }

    private static class Player {
        private final EntityPlayer player;
        private final double firstX;
        private final double firstY;
        private final double firstZ;

        public Player(EntityPlayer player) {
            this.player = player;
            this.firstX = player.posX;
            this.firstY = player.posY;
            this.firstZ = player.posZ;
        }

        boolean moved() {
            return this.firstX != this.player.posX || this.firstY != this.player.posY || this.firstZ != this.player.posZ;
        }
    }
}

