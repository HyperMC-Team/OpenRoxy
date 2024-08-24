package net.minecraft.scoreboard;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import Rename.Class379;
import Rename.Class350;
import Rename.Class372;
import Rename.Class375;
import net.minecraft.server.MinecraftServer;

public class ServerScoreboard
extends Scoreboard {
    private final MinecraftServer scoreboardMCServer;
    private final Set<ScoreObjective> field_96553_b = Sets.newHashSet();
    private ScoreboardSaveData scoreboardSaveData;

    public ServerScoreboard(MinecraftServer mcServer) {
        this.scoreboardMCServer = mcServer;
    }

    @Override
    public void func_96536_a(Score p_96536_1_) {
        super.func_96536_a(p_96536_1_);
        if (this.field_96553_b.contains(p_96536_1_.getObjective())) {
            this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers(new Class350(p_96536_1_));
        }
        this.markSaveDataDirty();
    }

    @Override
    public void func_96516_a(String p_96516_1_) {
        super.func_96516_a(p_96516_1_);
        this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers(new Class350(p_96516_1_));
        this.markSaveDataDirty();
    }

    @Override
    public void func_178820_a(String p_178820_1_, ScoreObjective p_178820_2_) {
        super.func_178820_a(p_178820_1_, p_178820_2_);
        this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers(new Class350(p_178820_1_, p_178820_2_));
        this.markSaveDataDirty();
    }

    @Override
    public void setObjectiveInDisplaySlot(int p_96530_1_, ScoreObjective p_96530_2_) {
        ScoreObjective scoreobjective = this.getObjectiveInDisplaySlot(p_96530_1_);
        super.setObjectiveInDisplaySlot(p_96530_1_, p_96530_2_);
        if (scoreobjective != p_96530_2_ && scoreobjective != null) {
            if (this.func_96552_h(scoreobjective) > 0) {
                this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers(new Class372(p_96530_1_, p_96530_2_));
            } else {
                this.sendDisplaySlotRemovalPackets(scoreobjective);
            }
        }
        if (p_96530_2_ != null) {
            if (this.field_96553_b.contains(p_96530_2_)) {
                this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers(new Class372(p_96530_1_, p_96530_2_));
            } else {
                this.func_96549_e(p_96530_2_);
            }
        }
        this.markSaveDataDirty();
    }

    @Override
    public boolean addPlayerToTeam(String player, String newTeam) {
        if (super.addPlayerToTeam(player, newTeam)) {
            ScorePlayerTeam scoreplayerteam = this.getTeam(newTeam);
            this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers(new Class375(scoreplayerteam, Arrays.asList(player), 3));
            this.markSaveDataDirty();
            return true;
        }
        return false;
    }

    @Override
    public void removePlayerFromTeam(String p_96512_1_, ScorePlayerTeam p_96512_2_) {
        super.removePlayerFromTeam(p_96512_1_, p_96512_2_);
        this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers(new Class375(p_96512_2_, Arrays.asList(p_96512_1_), 4));
        this.markSaveDataDirty();
    }

    @Override
    public void onScoreObjectiveAdded(ScoreObjective scoreObjectiveIn) {
        super.onScoreObjectiveAdded(scoreObjectiveIn);
        this.markSaveDataDirty();
    }

    @Override
    public void onObjectiveDisplayNameChanged(ScoreObjective p_96532_1_) {
        super.onObjectiveDisplayNameChanged(p_96532_1_);
        if (this.field_96553_b.contains(p_96532_1_)) {
            this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers(new Class379(p_96532_1_, 2));
        }
        this.markSaveDataDirty();
    }

    @Override
    public void onScoreObjectiveRemoved(ScoreObjective p_96533_1_) {
        super.onScoreObjectiveRemoved(p_96533_1_);
        if (this.field_96553_b.contains(p_96533_1_)) {
            this.sendDisplaySlotRemovalPackets(p_96533_1_);
        }
        this.markSaveDataDirty();
    }

    @Override
    public void broadcastTeamCreated(ScorePlayerTeam playerTeam) {
        super.broadcastTeamCreated(playerTeam);
        this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers(new Class375(playerTeam, 0));
        this.markSaveDataDirty();
    }

    @Override
    public void sendTeamUpdate(ScorePlayerTeam playerTeam) {
        super.sendTeamUpdate(playerTeam);
        this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers(new Class375(playerTeam, 2));
        this.markSaveDataDirty();
    }

    @Override
    public void func_96513_c(ScorePlayerTeam playerTeam) {
        super.func_96513_c(playerTeam);
        this.scoreboardMCServer.getConfigurationManager().sendPacketToAllPlayers(new Class375(playerTeam, 1));
        this.markSaveDataDirty();
    }

    public void func_96547_a(ScoreboardSaveData p_96547_1_) {
        this.scoreboardSaveData = p_96547_1_;
    }

    protected void markSaveDataDirty() {
        if (this.scoreboardSaveData != null) {
            this.scoreboardSaveData.markDirty();
        }
    }

    public List<Packet> func_96550_d(ScoreObjective p_96550_1_) {
        ArrayList list = Lists.newArrayList();
        list.add(new Class379(p_96550_1_, 0));
        for (int i = 0; i < 19; ++i) {
            if (this.getObjectiveInDisplaySlot(i) != p_96550_1_) continue;
            list.add(new Class372(i, p_96550_1_));
        }
        for (Score score : this.getSortedScores(p_96550_1_)) {
            list.add(new Class350(score));
        }
        return list;
    }

    public void func_96549_e(ScoreObjective p_96549_1_) {
        List<Packet> list = this.func_96550_d(p_96549_1_);
        for (EntityPlayerMP entityplayermp : this.scoreboardMCServer.getConfigurationManager().getPlayerList()) {
            for (Packet packet : list) {
                entityplayermp.playerNetServerHandler.sendPacket(packet);
            }
        }
        this.field_96553_b.add(p_96549_1_);
    }

    public List<Packet> func_96548_f(ScoreObjective p_96548_1_) {
        ArrayList list = Lists.newArrayList();
        list.add(new Class379(p_96548_1_, 1));
        for (int i = 0; i < 19; ++i) {
            if (this.getObjectiveInDisplaySlot(i) != p_96548_1_) continue;
            list.add(new Class372(i, p_96548_1_));
        }
        return list;
    }

    public void sendDisplaySlotRemovalPackets(ScoreObjective p_96546_1_) {
        List<Packet> list = this.func_96548_f(p_96546_1_);
        for (EntityPlayerMP entityplayermp : this.scoreboardMCServer.getConfigurationManager().getPlayerList()) {
            for (Packet packet : list) {
                entityplayermp.playerNetServerHandler.sendPacket(packet);
            }
        }
        this.field_96553_b.remove(p_96546_1_);
    }

    public int func_96552_h(ScoreObjective p_96552_1_) {
        int i = 0;
        for (int j = 0; j < 19; ++j) {
            if (this.getObjectiveInDisplaySlot(j) != p_96552_1_) continue;
            ++i;
        }
        return i;
    }
}

