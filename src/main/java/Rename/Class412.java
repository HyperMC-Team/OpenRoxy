package Rename;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.io.IOException;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.WorldSettings;

public class Class412
implements Packet<INetHandlerPlayClient> {
    private Action action;
    private final List<AddPlayerData> players = Lists.newArrayList();

    public Class412() {
    }

    public Class412(Action actionIn, EntityPlayerMP ... players) {
        this.action = actionIn;
        for (EntityPlayerMP entityplayermp : players) {
            this.players.add(new AddPlayerData(this, entityplayermp.getGameProfile(), entityplayermp.ping, entityplayermp.theItemInWorldManager.getGameType(), entityplayermp.getTabListDisplayName()));
        }
    }

    public Class412(Action actionIn, Iterable<EntityPlayerMP> players) {
        this.action = actionIn;
        for (EntityPlayerMP entityplayermp : players) {
            this.players.add(new AddPlayerData(this, entityplayermp.getGameProfile(), entityplayermp.ping, entityplayermp.theItemInWorldManager.getGameType(), entityplayermp.getTabListDisplayName()));
        }
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.action = buf.readEnumValue(Action.class);
        int i = buf.readVarIntFromBuffer();
        for (int j = 0; j < i; ++j) {
            GameProfile gameprofile = null;
            int k = 0;
            WorldSettings.GameType worldsettings$gametype = null;
            IChatComponent ichatcomponent = null;
            switch (this.action.ordinal()) {
                case 0: {
                    gameprofile = new GameProfile(buf.readUuid(), buf.readStringFromBuffer(16));
                    int l = buf.readVarIntFromBuffer();
                    for (int i1 = 0; i1 < l; ++i1) {
                        String s = buf.readStringFromBuffer(Short.MAX_VALUE);
                        String s1 = buf.readStringFromBuffer(Short.MAX_VALUE);
                        if (buf.readBoolean()) {
                            gameprofile.getProperties().put(s, new Property(s, s1, buf.readStringFromBuffer(Short.MAX_VALUE)));
                            continue;
                        }
                        gameprofile.getProperties().put(s, new Property(s, s1));
                    }
                    worldsettings$gametype = WorldSettings.GameType.getByID(buf.readVarIntFromBuffer());
                    k = buf.readVarIntFromBuffer();
                    if (!buf.readBoolean()) break;
                    ichatcomponent = buf.readChatComponent();
                    break;
                }
                case 1: {
                    gameprofile = new GameProfile(buf.readUuid(), (String)null);
                    worldsettings$gametype = WorldSettings.GameType.getByID(buf.readVarIntFromBuffer());
                    break;
                }
                case 2: {
                    gameprofile = new GameProfile(buf.readUuid(), (String)null);
                    k = buf.readVarIntFromBuffer();
                    break;
                }
                case 3: {
                    gameprofile = new GameProfile(buf.readUuid(), (String)null);
                    if (!buf.readBoolean()) break;
                    ichatcomponent = buf.readChatComponent();
                    break;
                }
                case 4: {
                    gameprofile = new GameProfile(buf.readUuid(), (String)null);
                }
            }
            this.players.add(new AddPlayerData(this, gameprofile, k, worldsettings$gametype, ichatcomponent));
        }
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeEnumValue(this.action);
        buf.writeVarIntToBuffer(this.players.size());
        for (AddPlayerData s38packetplayerlistitem$addplayerdata : this.players) {
            switch (this.action.ordinal()) {
                case 0: {
                    buf.writeUuid(s38packetplayerlistitem$addplayerdata.getProfile().getId());
                    buf.writeString(s38packetplayerlistitem$addplayerdata.getProfile().getName());
                    buf.writeVarIntToBuffer(s38packetplayerlistitem$addplayerdata.getProfile().getProperties().size());
                    for (Property property : s38packetplayerlistitem$addplayerdata.getProfile().getProperties().values()) {
                        buf.writeString(property.getName());
                        buf.writeString(property.getValue());
                        if (property.hasSignature()) {
                            buf.writeBoolean(true);
                            buf.writeString(property.getSignature());
                            continue;
                        }
                        buf.writeBoolean(false);
                    }
                    buf.writeVarIntToBuffer(s38packetplayerlistitem$addplayerdata.getGameMode().getID());
                    buf.writeVarIntToBuffer(s38packetplayerlistitem$addplayerdata.getPing());
                    if (s38packetplayerlistitem$addplayerdata.getDisplayName() == null) {
                        buf.writeBoolean(false);
                        break;
                    }
                    buf.writeBoolean(true);
                    buf.writeChatComponent(s38packetplayerlistitem$addplayerdata.getDisplayName());
                    break;
                }
                case 1: {
                    buf.writeUuid(s38packetplayerlistitem$addplayerdata.getProfile().getId());
                    buf.writeVarIntToBuffer(s38packetplayerlistitem$addplayerdata.getGameMode().getID());
                    break;
                }
                case 2: {
                    buf.writeUuid(s38packetplayerlistitem$addplayerdata.getProfile().getId());
                    buf.writeVarIntToBuffer(s38packetplayerlistitem$addplayerdata.getPing());
                    break;
                }
                case 3: {
                    buf.writeUuid(s38packetplayerlistitem$addplayerdata.getProfile().getId());
                    if (s38packetplayerlistitem$addplayerdata.getDisplayName() == null) {
                        buf.writeBoolean(false);
                        break;
                    }
                    buf.writeBoolean(true);
                    buf.writeChatComponent(s38packetplayerlistitem$addplayerdata.getDisplayName());
                    break;
                }
                case 4: {
                    buf.writeUuid(s38packetplayerlistitem$addplayerdata.getProfile().getId());
                }
            }
        }
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handlePlayerListItem(this);
    }

    public List<AddPlayerData> getEntries() {
        return this.players;
    }

    public Action getAction() {
        return this.action;
    }

    public String toString() {
        return Objects.toStringHelper((Object)this).add("action", (Object)this.action).add("entries", this.players).toString();
    }

    public static enum Action {
        ADD_PLAYER,
        UPDATE_GAME_MODE,
        UPDATE_LATENCY,
        UPDATE_DISPLAY_NAME,
        REMOVE_PLAYER;

    }

    public class AddPlayerData {
        private final int ping;
        private final WorldSettings.GameType gamemode;
        private final GameProfile profile;
        private final IChatComponent displayName;

        public AddPlayerData(Class412 this$0, GameProfile profile, int pingIn, WorldSettings.GameType gamemodeIn, IChatComponent displayNameIn) {
            this.profile = profile;
            this.ping = pingIn;
            this.gamemode = gamemodeIn;
            this.displayName = displayNameIn;
        }

        public GameProfile getProfile() {
            return this.profile;
        }

        public int getPing() {
            return this.ping;
        }

        public WorldSettings.GameType getGameMode() {
            return this.gamemode;
        }

        public IChatComponent getDisplayName() {
            return this.displayName;
        }

        public String toString() {
            return Objects.toStringHelper((Object)this).add("latency", this.ping).add("gameMode", (Object)this.gamemode).add("profile", (Object)this.profile).add("displayName", this.displayName == null ? null : IChatComponent.Serializer.componentToJson(this.displayName)).toString();
        }
    }
}

