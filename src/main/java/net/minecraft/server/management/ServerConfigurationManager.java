package net.minecraft.server.management;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.mojang.authlib.GameProfile;
import io.netty.buffer.Unpooled;
import java.io.File;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import Rename.Class411;
import Rename.Class420;
import Rename.Class341;
import Rename.Class400;
import Rename.Class401;
import Rename.Class374;
import Rename.Class347;
import Rename.Class344;
import Rename.Class342;
import Rename.Class412;
import Rename.Class417;
import Rename.Class375;
import Rename.Class377;
import Rename.Class416;
import Rename.Class387;
import net.minecraft.potion.PotionEffect;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.ServerScoreboard;
import net.minecraft.scoreboard.Team;
import net.minecraft.server.MinecraftServer;
import net.minecraft.stats.StatList;
import net.minecraft.stats.StatisticsFile;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.minecraft.world.WorldSettings;
import net.minecraft.world.border.IBorderListener;
import net.minecraft.world.border.WorldBorder;
import net.minecraft.world.demo.DemoWorldManager;
import net.minecraft.world.storage.IPlayerFileData;
import net.minecraft.world.storage.WorldInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class ServerConfigurationManager {
    public static final File FILE_PLAYERBANS = new File("banned-players.json");
    public static final File FILE_IPBANS = new File("banned-ips.json");
    public static final File FILE_OPS = new File("ops.json");
    public static final File FILE_WHITELIST = new File("whitelist.json");
    private static final Logger logger = LogManager.getLogger();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
    private final MinecraftServer mcServer;
    private final List<EntityPlayerMP> playerEntityList = Lists.newArrayList();
    private final Map<UUID, EntityPlayerMP> uuidToPlayerMap = Maps.newHashMap();
    private final UserListBans bannedPlayers = new UserListBans(FILE_PLAYERBANS);
    private final BanList bannedIPs = new BanList(FILE_IPBANS);
    private final UserListOps ops = new UserListOps(FILE_OPS);
    private final UserListWhitelist whiteListedPlayers = new UserListWhitelist(FILE_WHITELIST);
    private final Map<UUID, StatisticsFile> playerStatFiles = Maps.newHashMap();
    private IPlayerFileData playerNBTManagerObj;
    private boolean whiteListEnforced;
    protected int maxPlayers;
    private int viewDistance;
    private WorldSettings.GameType gameType;
    private boolean commandsAllowedForAll;
    private int playerPingIndex;

    public ServerConfigurationManager(MinecraftServer server) {
        this.mcServer = server;
        this.bannedPlayers.setLanServer(false);
        this.bannedIPs.setLanServer(false);
        this.maxPlayers = 8;
    }

    public void initializeConnectionToPlayer(NetworkManager netManager, EntityPlayerMP playerIn) {
        Entity entity;
        GameProfile gameprofile = playerIn.getGameProfile();
        PlayerProfileCache playerprofilecache = this.mcServer.getPlayerProfileCache();
        GameProfile gameprofile1 = playerprofilecache.getProfileByUUID(gameprofile.getId());
        String s = gameprofile1 == null ? gameprofile.getName() : gameprofile1.getName();
        playerprofilecache.addEntry(gameprofile);
        NBTTagCompound nbttagcompound = this.readPlayerDataFromFile(playerIn);
        playerIn.setWorld(this.mcServer.worldServerForDimension(playerIn.dimension));
        playerIn.theItemInWorldManager.setWorld((WorldServer)playerIn.worldObj);
        String s1 = "local";
        if (netManager.getRemoteAddress() != null) {
            s1 = netManager.getRemoteAddress().toString();
        }
        logger.info(playerIn.getName() + "[" + s1 + "] logged in with entity id " + playerIn.getEntityId() + " at (" + playerIn.posX + ", " + playerIn.posY + ", " + playerIn.posZ + ")");
        WorldServer worldserver = this.mcServer.worldServerForDimension(playerIn.dimension);
        WorldInfo worldinfo = worldserver.getWorldInfo();
        BlockPos blockpos = worldserver.getSpawnPoint();
        this.setPlayerGameTypeBasedOnOther(playerIn, null, worldserver);
        NetHandlerPlayServer nethandlerplayserver = new NetHandlerPlayServer(this.mcServer, netManager, playerIn);
        nethandlerplayserver.sendPacket(new Class411(playerIn.getEntityId(), playerIn.theItemInWorldManager.getGameType(), worldinfo.isHardcoreModeEnabled(), worldserver.provider.getDimensionId(), worldserver.getDifficulty(), this.getMaxPlayers(), worldinfo.getTerrainType(), worldserver.getGameRules().getBoolean("reducedDebugInfo")));
        nethandlerplayserver.sendPacket(new Class377("MC|Brand", new PacketBuffer(Unpooled.buffer()).writeString(this.getServerInstance().getServerModName())));
        nethandlerplayserver.sendPacket(new Class416(worldinfo.getDifficulty(), worldinfo.isDifficultyLocked()));
        nethandlerplayserver.sendPacket(new Class400(blockpos));
        nethandlerplayserver.sendPacket(new Class417(playerIn.capabilities));
        nethandlerplayserver.sendPacket(new Class374(playerIn.inventory.currentItem));
        playerIn.getStatFile().func_150877_d();
        playerIn.getStatFile().sendAchievements(playerIn);
        this.sendScoreboard((ServerScoreboard)worldserver.getScoreboard(), playerIn);
        this.mcServer.refreshStatusNextTick();
        ChatComponentTranslation chatcomponenttranslation = !playerIn.getName().equalsIgnoreCase(s) ? new ChatComponentTranslation("multiplayer.player.joined.renamed", playerIn.getDisplayName(), s) : new ChatComponentTranslation("multiplayer.player.joined", playerIn.getDisplayName());
        chatcomponenttranslation.getChatStyle().setColor(EnumChatFormatting.YELLOW);
        this.sendChatMsg(chatcomponenttranslation);
        this.playerLoggedIn(playerIn);
        nethandlerplayserver.setPlayerLocation(playerIn.posX, playerIn.posY, playerIn.posZ, playerIn.rotationYaw, playerIn.rotationPitch);
        this.updateTimeAndWeatherForPlayer(playerIn, worldserver);
        if (this.mcServer.getResourcePackUrl().length() > 0) {
            playerIn.loadResourcePack(this.mcServer.getResourcePackUrl(), this.mcServer.getResourcePackHash());
        }
        for (PotionEffect potioneffect : playerIn.getActivePotionEffects()) {
            nethandlerplayserver.sendPacket(new Class347(playerIn.getEntityId(), potioneffect));
        }
        playerIn.addSelfToInternalCraftingInventory();
        if (nbttagcompound != null && nbttagcompound.hasKey("Riding", 10) && (entity = EntityList.createEntityFromNBT(nbttagcompound.getCompoundTag("Riding"), worldserver)) != null) {
            entity.forceSpawn = true;
            worldserver.spawnEntityInWorld(entity);
            playerIn.mountEntity(entity);
            entity.forceSpawn = false;
        }
    }

    protected void sendScoreboard(ServerScoreboard scoreboardIn, EntityPlayerMP playerIn) {
        HashSet set = Sets.newHashSet();
        for (ScorePlayerTeam scoreplayerteam : scoreboardIn.getTeams()) {
            playerIn.playerNetServerHandler.sendPacket(new Class375(scoreplayerteam, 0));
        }
        for (int i = 0; i < 19; ++i) {
            ScoreObjective scoreobjective = scoreboardIn.getObjectiveInDisplaySlot(i);
            if (scoreobjective == null || set.contains(scoreobjective)) continue;
            for (Packet packet : scoreboardIn.func_96550_d(scoreobjective)) {
                playerIn.playerNetServerHandler.sendPacket(packet);
            }
            set.add(scoreobjective);
        }
    }

    public void setPlayerManager(WorldServer[] worldServers) {
        this.playerNBTManagerObj = worldServers[0].getSaveHandler().getPlayerNBTManager();
        worldServers[0].getWorldBorder().addListener(new IBorderListener(){

            @Override
            public void onSizeChanged(WorldBorder border, double newSize) {
                ServerConfigurationManager.this.sendPacketToAllPlayers(new Class387(border, Class387.Action.SET_SIZE));
            }

            @Override
            public void onTransitionStarted(WorldBorder border, double oldSize, double newSize, long time) {
                ServerConfigurationManager.this.sendPacketToAllPlayers(new Class387(border, Class387.Action.LERP_SIZE));
            }

            @Override
            public void onCenterChanged(WorldBorder border, double x, double z) {
                ServerConfigurationManager.this.sendPacketToAllPlayers(new Class387(border, Class387.Action.SET_CENTER));
            }

            @Override
            public void onWarningTimeChanged(WorldBorder border, int newTime) {
                ServerConfigurationManager.this.sendPacketToAllPlayers(new Class387(border, Class387.Action.SET_WARNING_TIME));
            }

            @Override
            public void onWarningDistanceChanged(WorldBorder border, int newDistance) {
                ServerConfigurationManager.this.sendPacketToAllPlayers(new Class387(border, Class387.Action.SET_WARNING_BLOCKS));
            }

            @Override
            public void onDamageAmountChanged(WorldBorder border, double newAmount) {
            }

            @Override
            public void onDamageBufferChanged(WorldBorder border, double newSize) {
            }
        });
    }

    public void preparePlayer(EntityPlayerMP playerIn, WorldServer worldIn) {
        WorldServer worldserver = playerIn.getServerForPlayer();
        if (worldIn != null) {
            worldIn.getPlayerManager().removePlayer(playerIn);
        }
        worldserver.getPlayerManager().addPlayer(playerIn);
        worldserver.theChunkProviderServer.loadChunk((int)playerIn.posX >> 4, (int)playerIn.posZ >> 4);
    }

    public int getEntityViewDistance() {
        return PlayerManager.getFurthestViewableBlock(this.getViewDistance());
    }

    public NBTTagCompound readPlayerDataFromFile(EntityPlayerMP playerIn) {
        NBTTagCompound nbttagcompound1;
        NBTTagCompound nbttagcompound = this.mcServer.worldServers[0].getWorldInfo().getPlayerNBTTagCompound();
        if (playerIn.getName().equals(this.mcServer.getServerOwner()) && nbttagcompound != null) {
            playerIn.readFromNBT(nbttagcompound);
            nbttagcompound1 = nbttagcompound;
            logger.debug("loading single player");
        } else {
            nbttagcompound1 = this.playerNBTManagerObj.readPlayerData(playerIn);
        }
        return nbttagcompound1;
    }

    protected void writePlayerData(EntityPlayerMP playerIn) {
        this.playerNBTManagerObj.writePlayerData(playerIn);
        StatisticsFile statisticsfile = this.playerStatFiles.get(playerIn.getUniqueID());
        if (statisticsfile != null) {
            statisticsfile.saveStatFile();
        }
    }

    public void playerLoggedIn(EntityPlayerMP playerIn) {
        this.playerEntityList.add(playerIn);
        this.uuidToPlayerMap.put(playerIn.getUniqueID(), playerIn);
        this.sendPacketToAllPlayers(new Class412(Class412.Action.ADD_PLAYER, playerIn));
        WorldServer worldserver = this.mcServer.worldServerForDimension(playerIn.dimension);
        worldserver.spawnEntityInWorld(playerIn);
        this.preparePlayer(playerIn, null);
        for (int i = 0; i < this.playerEntityList.size(); ++i) {
            EntityPlayerMP entityplayermp = this.playerEntityList.get(i);
            playerIn.playerNetServerHandler.sendPacket(new Class412(Class412.Action.ADD_PLAYER, entityplayermp));
        }
    }

    public void serverUpdateMountedMovingPlayer(EntityPlayerMP playerIn) {
        playerIn.getServerForPlayer().getPlayerManager().updateMountedMovingPlayer(playerIn);
    }

    public void playerLoggedOut(EntityPlayerMP playerIn) {
        playerIn.triggerAchievement(StatList.leaveGameStat);
        this.writePlayerData(playerIn);
        WorldServer worldserver = playerIn.getServerForPlayer();
        if (playerIn.ridingEntity != null) {
            worldserver.removePlayerEntityDangerously(playerIn.ridingEntity);
            logger.debug("removing player mount");
        }
        worldserver.removeEntity(playerIn);
        worldserver.getPlayerManager().removePlayer(playerIn);
        this.playerEntityList.remove(playerIn);
        UUID uuid = playerIn.getUniqueID();
        EntityPlayerMP entityplayermp = this.uuidToPlayerMap.get(uuid);
        if (entityplayermp == playerIn) {
            this.uuidToPlayerMap.remove(uuid);
            this.playerStatFiles.remove(uuid);
        }
        this.sendPacketToAllPlayers(new Class412(Class412.Action.REMOVE_PLAYER, playerIn));
    }

    public String allowUserToConnect(SocketAddress address, GameProfile profile) {
        if (this.bannedPlayers.isBanned(profile)) {
            UserListBansEntry userlistbansentry = (UserListBansEntry)this.bannedPlayers.getEntry(profile);
            String s1 = "You are banned from this server!\nReason: " + userlistbansentry.getBanReason();
            if (userlistbansentry.getBanEndDate() != null) {
                s1 = s1 + "\nYour ban will be removed on " + dateFormat.format(userlistbansentry.getBanEndDate());
            }
            return s1;
        }
        if (!this.canJoin(profile)) {
            return "You are not white-listed on this server!";
        }
        if (this.bannedIPs.isBanned(address)) {
            IPBanEntry ipbanentry = this.bannedIPs.getBanEntry(address);
            String s = "Your IP address is banned from this server!\nReason: " + ipbanentry.getBanReason();
            if (ipbanentry.getBanEndDate() != null) {
                s = s + "\nYour ban will be removed on " + dateFormat.format(ipbanentry.getBanEndDate());
            }
            return s;
        }
        return this.playerEntityList.size() >= this.maxPlayers && !this.bypassesPlayerLimit(profile) ? "The server is full!" : null;
    }

    public EntityPlayerMP createPlayerForUser(GameProfile profile) {
        UUID uuid = EntityPlayer.getUUID(profile);
        ArrayList<EntityPlayerMP> list = Lists.newArrayList();
        for (int i = 0; i < this.playerEntityList.size(); ++i) {
            EntityPlayerMP entityplayermp = this.playerEntityList.get(i);
            if (!entityplayermp.getUniqueID().equals(uuid)) continue;
            list.add(entityplayermp);
        }
        EntityPlayerMP entityplayermp2 = this.uuidToPlayerMap.get(profile.getId());
        if (entityplayermp2 != null && !list.contains(entityplayermp2)) {
            list.add(entityplayermp2);
        }
        for (EntityPlayerMP entityplayermp1 : list) {
            entityplayermp1.playerNetServerHandler.kickPlayerFromServer("You logged in from another location");
        }
        ItemInWorldManager iteminworldmanager = this.mcServer.isDemo() ? new DemoWorldManager(this.mcServer.worldServerForDimension(0)) : new ItemInWorldManager(this.mcServer.worldServerForDimension(0));
        return new EntityPlayerMP(this.mcServer, this.mcServer.worldServerForDimension(0), profile, iteminworldmanager);
    }

    public EntityPlayerMP recreatePlayerEntity(EntityPlayerMP playerIn, int dimension, boolean conqueredEnd) {
        playerIn.getServerForPlayer().getEntityTracker().removePlayerFromTrackers(playerIn);
        playerIn.getServerForPlayer().getEntityTracker().untrackEntity(playerIn);
        playerIn.getServerForPlayer().getPlayerManager().removePlayer(playerIn);
        this.playerEntityList.remove(playerIn);
        this.mcServer.worldServerForDimension(playerIn.dimension).removePlayerEntityDangerously(playerIn);
        BlockPos blockpos = playerIn.getBedLocation();
        boolean flag = playerIn.isSpawnForced();
        playerIn.dimension = dimension;
        ItemInWorldManager iteminworldmanager = this.mcServer.isDemo() ? new DemoWorldManager(this.mcServer.worldServerForDimension(playerIn.dimension)) : new ItemInWorldManager(this.mcServer.worldServerForDimension(playerIn.dimension));
        EntityPlayerMP entityplayermp = new EntityPlayerMP(this.mcServer, this.mcServer.worldServerForDimension(playerIn.dimension), playerIn.getGameProfile(), iteminworldmanager);
        entityplayermp.playerNetServerHandler = playerIn.playerNetServerHandler;
        entityplayermp.clonePlayer(playerIn, conqueredEnd);
        entityplayermp.setEntityId(playerIn.getEntityId());
        entityplayermp.setCommandStats(playerIn);
        WorldServer worldserver = this.mcServer.worldServerForDimension(playerIn.dimension);
        this.setPlayerGameTypeBasedOnOther(entityplayermp, playerIn, worldserver);
        if (blockpos != null) {
            BlockPos blockpos1 = EntityPlayer.getBedSpawnLocation(this.mcServer.worldServerForDimension(playerIn.dimension), blockpos, flag);
            if (blockpos1 != null) {
                entityplayermp.setLocationAndAngles((float)blockpos1.getX() + 0.5f, (float)blockpos1.getY() + 0.1f, (float)blockpos1.getZ() + 0.5f, 0.0f, 0.0f);
                entityplayermp.setSpawnPoint(blockpos, flag);
            } else {
                entityplayermp.playerNetServerHandler.sendPacket(new Class342(0, 0.0f));
            }
        }
        worldserver.theChunkProviderServer.loadChunk((int)entityplayermp.posX >> 4, (int)entityplayermp.posZ >> 4);
        while (!worldserver.getCollidingBoundingBoxes(entityplayermp, entityplayermp.getEntityBoundingBox()).isEmpty() && entityplayermp.posY < 256.0) {
            entityplayermp.setPosition(entityplayermp.posX, entityplayermp.posY + 1.0, entityplayermp.posZ);
        }
        entityplayermp.playerNetServerHandler.sendPacket(new Class401(entityplayermp.dimension, entityplayermp.worldObj.getDifficulty(), entityplayermp.worldObj.getWorldInfo().getTerrainType(), entityplayermp.theItemInWorldManager.getGameType()));
        BlockPos blockpos2 = worldserver.getSpawnPoint();
        entityplayermp.playerNetServerHandler.setPlayerLocation(entityplayermp.posX, entityplayermp.posY, entityplayermp.posZ, entityplayermp.rotationYaw, entityplayermp.rotationPitch);
        entityplayermp.playerNetServerHandler.sendPacket(new Class400(blockpos2));
        entityplayermp.playerNetServerHandler.sendPacket(new Class344(entityplayermp.experience, entityplayermp.experienceTotal, entityplayermp.experienceLevel));
        this.updateTimeAndWeatherForPlayer(entityplayermp, worldserver);
        worldserver.getPlayerManager().addPlayer(entityplayermp);
        worldserver.spawnEntityInWorld(entityplayermp);
        this.playerEntityList.add(entityplayermp);
        this.uuidToPlayerMap.put(entityplayermp.getUniqueID(), entityplayermp);
        entityplayermp.addSelfToInternalCraftingInventory();
        entityplayermp.setHealth(entityplayermp.getHealth());
        return entityplayermp;
    }

    public void transferPlayerToDimension(EntityPlayerMP playerIn, int dimension) {
        int i = playerIn.dimension;
        WorldServer worldserver = this.mcServer.worldServerForDimension(playerIn.dimension);
        playerIn.dimension = dimension;
        WorldServer worldserver1 = this.mcServer.worldServerForDimension(playerIn.dimension);
        playerIn.playerNetServerHandler.sendPacket(new Class401(playerIn.dimension, playerIn.worldObj.getDifficulty(), playerIn.worldObj.getWorldInfo().getTerrainType(), playerIn.theItemInWorldManager.getGameType()));
        worldserver.removePlayerEntityDangerously(playerIn);
        playerIn.isDead = false;
        this.transferEntityToWorld(playerIn, i, worldserver, worldserver1);
        this.preparePlayer(playerIn, worldserver);
        playerIn.playerNetServerHandler.setPlayerLocation(playerIn.posX, playerIn.posY, playerIn.posZ, playerIn.rotationYaw, playerIn.rotationPitch);
        playerIn.theItemInWorldManager.setWorld(worldserver1);
        this.updateTimeAndWeatherForPlayer(playerIn, worldserver1);
        this.syncPlayerInventory(playerIn);
        for (PotionEffect potioneffect : playerIn.getActivePotionEffects()) {
            playerIn.playerNetServerHandler.sendPacket(new Class347(playerIn.getEntityId(), potioneffect));
        }
    }

    public void transferEntityToWorld(Entity entityIn, int p_82448_2_, WorldServer oldWorldIn, WorldServer toWorldIn) {
        double d0 = entityIn.posX;
        double d1 = entityIn.posZ;
        double d2 = 8.0;
        float f = entityIn.rotationYaw;
        oldWorldIn.theProfiler.startSection("moving");
        if (entityIn.dimension == -1) {
            d0 = MathHelper.clamp_double(d0 / d2, toWorldIn.getWorldBorder().minX() + 16.0, toWorldIn.getWorldBorder().maxX() - 16.0);
            d1 = MathHelper.clamp_double(d1 / d2, toWorldIn.getWorldBorder().minZ() + 16.0, toWorldIn.getWorldBorder().maxZ() - 16.0);
            entityIn.setLocationAndAngles(d0, entityIn.posY, d1, entityIn.rotationYaw, entityIn.rotationPitch);
            if (entityIn.isEntityAlive()) {
                oldWorldIn.updateEntityWithOptionalForce(entityIn, false);
            }
        } else if (entityIn.dimension == 0) {
            d0 = MathHelper.clamp_double(d0 * d2, toWorldIn.getWorldBorder().minX() + 16.0, toWorldIn.getWorldBorder().maxX() - 16.0);
            d1 = MathHelper.clamp_double(d1 * d2, toWorldIn.getWorldBorder().minZ() + 16.0, toWorldIn.getWorldBorder().maxZ() - 16.0);
            entityIn.setLocationAndAngles(d0, entityIn.posY, d1, entityIn.rotationYaw, entityIn.rotationPitch);
            if (entityIn.isEntityAlive()) {
                oldWorldIn.updateEntityWithOptionalForce(entityIn, false);
            }
        } else {
            BlockPos blockpos = p_82448_2_ == 1 ? toWorldIn.getSpawnPoint() : toWorldIn.getSpawnCoordinate();
            d0 = blockpos.getX();
            entityIn.posY = blockpos.getY();
            d1 = blockpos.getZ();
            entityIn.setLocationAndAngles(d0, entityIn.posY, d1, 90.0f, 0.0f);
            if (entityIn.isEntityAlive()) {
                oldWorldIn.updateEntityWithOptionalForce(entityIn, false);
            }
        }
        oldWorldIn.theProfiler.endSection();
        if (p_82448_2_ != 1) {
            oldWorldIn.theProfiler.startSection("placing");
            d0 = MathHelper.clamp_int((int)d0, -29999872, 29999872);
            d1 = MathHelper.clamp_int((int)d1, -29999872, 29999872);
            if (entityIn.isEntityAlive()) {
                entityIn.setLocationAndAngles(d0, entityIn.posY, d1, entityIn.rotationYaw, entityIn.rotationPitch);
                toWorldIn.getDefaultTeleporter().placeInPortal(entityIn, f);
                toWorldIn.spawnEntityInWorld(entityIn);
                toWorldIn.updateEntityWithOptionalForce(entityIn, false);
            }
            oldWorldIn.theProfiler.endSection();
        }
        entityIn.setWorld(toWorldIn);
    }

    public void onTick() {
        if (++this.playerPingIndex > 600) {
            this.sendPacketToAllPlayers(new Class412(Class412.Action.UPDATE_LATENCY, this.playerEntityList));
            this.playerPingIndex = 0;
        }
    }

    public void sendPacketToAllPlayers(Packet packetIn) {
        for (int i = 0; i < this.playerEntityList.size(); ++i) {
            this.playerEntityList.get((int)i).playerNetServerHandler.sendPacket(packetIn);
        }
    }

    public void sendPacketToAllPlayersInDimension(Packet packetIn, int dimension) {
        for (int i = 0; i < this.playerEntityList.size(); ++i) {
            EntityPlayerMP entityplayermp = this.playerEntityList.get(i);
            if (entityplayermp.dimension != dimension) continue;
            entityplayermp.playerNetServerHandler.sendPacket(packetIn);
        }
    }

    public void sendMessageToAllTeamMembers(EntityPlayer player, IChatComponent message) {
        Team team = player.getTeam();
        if (team != null) {
            for (String s : team.getMembershipCollection()) {
                EntityPlayerMP entityplayermp = this.getPlayerByUsername(s);
                if (entityplayermp == null || entityplayermp == player) continue;
                entityplayermp.addChatMessage(message);
            }
        }
    }

    public void sendMessageToTeamOrEvryPlayer(EntityPlayer player, IChatComponent message) {
        Team team = player.getTeam();
        if (team == null) {
            this.sendChatMsg(message);
        } else {
            for (int i = 0; i < this.playerEntityList.size(); ++i) {
                EntityPlayerMP entityplayermp = this.playerEntityList.get(i);
                if (entityplayermp.getTeam() == team) continue;
                entityplayermp.addChatMessage(message);
            }
        }
    }

    public String func_181058_b(boolean p_181058_1_)
    {
        String s = "";
        List<EntityPlayerMP> list = Lists.newArrayList(this.playerEntityList);

        for (int i = 0; i < ((List)list).size(); ++i)
        {
            if (i > 0)
            {
                s = s + ", ";
            }

            s = s + ((EntityPlayerMP)list.get(i)).getName();

            if (p_181058_1_)
            {
                s = s + " (" + ((EntityPlayerMP)list.get(i)).getUniqueID().toString() + ")";
            }
        }

        return s;
    }

    public String[] getAllUsernames() {
        String[] astring = new String[this.playerEntityList.size()];
        for (int i = 0; i < this.playerEntityList.size(); ++i) {
            astring[i] = this.playerEntityList.get(i).getName();
        }
        return astring;
    }

    public GameProfile[] getAllProfiles() {
        GameProfile[] agameprofile = new GameProfile[this.playerEntityList.size()];
        for (int i = 0; i < this.playerEntityList.size(); ++i) {
            agameprofile[i] = this.playerEntityList.get(i).getGameProfile();
        }
        return agameprofile;
    }

    public UserListBans getBannedPlayers() {
        return this.bannedPlayers;
    }

    public BanList getBannedIPs() {
        return this.bannedIPs;
    }

    public void addOp(GameProfile profile) {
        this.ops.addEntry(new UserListOpsEntry(profile, this.mcServer.getOpPermissionLevel(), this.ops.bypassesPlayerLimit(profile)));
    }

    public void removeOp(GameProfile profile) {
        this.ops.removeEntry(profile);
    }

    public boolean canJoin(GameProfile profile) {
        return !this.whiteListEnforced || this.ops.hasEntry(profile) || this.whiteListedPlayers.hasEntry(profile);
    }

    public boolean canSendCommands(GameProfile profile) {
        return this.ops.hasEntry(profile) || this.mcServer.isSinglePlayer() && this.mcServer.worldServers[0].getWorldInfo().areCommandsAllowed() && this.mcServer.getServerOwner().equalsIgnoreCase(profile.getName()) || this.commandsAllowedForAll;
    }

    public EntityPlayerMP getPlayerByUsername(String username) {
        for (EntityPlayerMP entityplayermp : this.playerEntityList) {
            if (!entityplayermp.getName().equalsIgnoreCase(username)) continue;
            return entityplayermp;
        }
        return null;
    }

    public void sendToAllNear(double x, double y, double z, double radius, int dimension, Packet packetIn) {
        this.sendToAllNearExcept(null, x, y, z, radius, dimension, packetIn);
    }

    public void sendToAllNearExcept(EntityPlayer p_148543_1_, double x, double y, double z, double radius, int dimension, Packet p_148543_11_) {
        for (int i = 0; i < this.playerEntityList.size(); ++i) {
            double d2;
            double d1;
            double d0;
            EntityPlayerMP entityplayermp = this.playerEntityList.get(i);
            if (entityplayermp == p_148543_1_ || entityplayermp.dimension != dimension || !((d0 = x - entityplayermp.posX) * d0 + (d1 = y - entityplayermp.posY) * d1 + (d2 = z - entityplayermp.posZ) * d2 < radius * radius)) continue;
            entityplayermp.playerNetServerHandler.sendPacket(p_148543_11_);
        }
    }

    public void saveAllPlayerData() {
        for (int i = 0; i < this.playerEntityList.size(); ++i) {
            this.writePlayerData(this.playerEntityList.get(i));
        }
    }

    public void addWhitelistedPlayer(GameProfile profile) {
        this.whiteListedPlayers.addEntry(new UserListWhitelistEntry(profile));
    }

    public void removePlayerFromWhitelist(GameProfile profile) {
        this.whiteListedPlayers.removeEntry(profile);
    }

    public UserListWhitelist getWhitelistedPlayers() {
        return this.whiteListedPlayers;
    }

    public String[] getWhitelistedPlayerNames() {
        return this.whiteListedPlayers.getKeys();
    }

    public UserListOps getOppedPlayers() {
        return this.ops;
    }

    public String[] getOppedPlayerNames() {
        return this.ops.getKeys();
    }

    public void loadWhiteList() {
    }

    public void updateTimeAndWeatherForPlayer(EntityPlayerMP playerIn, WorldServer worldIn) {
        WorldBorder worldborder = this.mcServer.worldServers[0].getWorldBorder();
        playerIn.playerNetServerHandler.sendPacket(new Class387(worldborder, Class387.Action.INITIALIZE));
        playerIn.playerNetServerHandler.sendPacket(new Class341(worldIn.getTotalWorldTime(), worldIn.getWorldTime(), worldIn.getGameRules().getBoolean("doDaylightCycle")));
        if (worldIn.isRaining()) {
            playerIn.playerNetServerHandler.sendPacket(new Class342(1, 0.0f));
            playerIn.playerNetServerHandler.sendPacket(new Class342(7, worldIn.getRainStrength(1.0f)));
            playerIn.playerNetServerHandler.sendPacket(new Class342(8, worldIn.getThunderStrength(1.0f)));
        }
    }

    public void syncPlayerInventory(EntityPlayerMP playerIn) {
        playerIn.sendContainerToPlayer(playerIn.inventoryContainer);
        playerIn.setPlayerHealthUpdated();
        playerIn.playerNetServerHandler.sendPacket(new Class374(playerIn.inventory.currentItem));
    }

    public int getCurrentPlayerCount() {
        return this.playerEntityList.size();
    }

    public int getMaxPlayers() {
        return this.maxPlayers;
    }

    public String[] getAvailablePlayerDat() {
        return this.mcServer.worldServers[0].getSaveHandler().getPlayerNBTManager().getAvailablePlayerDat();
    }

    public void setWhiteListEnabled(boolean whitelistEnabled) {
        this.whiteListEnforced = whitelistEnabled;
    }

    public List<EntityPlayerMP> getPlayersMatchingAddress(String address) {
        ArrayList list = Lists.newArrayList();
        for (EntityPlayerMP entityplayermp : this.playerEntityList) {
            if (!entityplayermp.getPlayerIP().equals(address)) continue;
            list.add(entityplayermp);
        }
        return list;
    }

    public int getViewDistance() {
        return this.viewDistance;
    }

    public MinecraftServer getServerInstance() {
        return this.mcServer;
    }

    public NBTTagCompound getHostPlayerData() {
        return null;
    }

    public void setGameType(WorldSettings.GameType p_152604_1_) {
        this.gameType = p_152604_1_;
    }

    private void setPlayerGameTypeBasedOnOther(EntityPlayerMP p_72381_1_, EntityPlayerMP p_72381_2_, World worldIn) {
        if (p_72381_2_ != null) {
            p_72381_1_.theItemInWorldManager.setGameType(p_72381_2_.theItemInWorldManager.getGameType());
        } else if (this.gameType != null) {
            p_72381_1_.theItemInWorldManager.setGameType(this.gameType);
        }
        p_72381_1_.theItemInWorldManager.initializeGameType(worldIn.getWorldInfo().getGameType());
    }

    public void setCommandsAllowedForAll(boolean p_72387_1_) {
        this.commandsAllowedForAll = p_72387_1_;
    }

    public void removeAllPlayers() {
        for (int i = 0; i < this.playerEntityList.size(); ++i) {
            this.playerEntityList.get((int)i).playerNetServerHandler.kickPlayerFromServer("Server closed");
        }
    }

    public void sendChatMsgImpl(IChatComponent component, boolean isChat) {
        this.mcServer.addChatMessage(component);
        byte b0 = (byte)(isChat ? 1 : 0);
        this.sendPacketToAllPlayers(new Class420(component, b0));
    }

    public void sendChatMsg(IChatComponent component) {
        this.sendChatMsgImpl(component, true);
    }

    public StatisticsFile getPlayerStatsFile(EntityPlayer playerIn) {
        StatisticsFile statisticsfile;
        UUID uuid = playerIn.getUniqueID();
        StatisticsFile statisticsFile = statisticsfile = uuid == null ? null : this.playerStatFiles.get(uuid);
        if (statisticsfile == null) {
            File file3;
            File file1 = new File(this.mcServer.worldServerForDimension(0).getSaveHandler().getWorldDirectory(), "stats");
            File file2 = new File(file1, uuid.toString() + ".json");
            if (!file2.exists() && (file3 = new File(file1, playerIn.getName() + ".json")).exists() && file3.isFile()) {
                file3.renameTo(file2);
            }
            statisticsfile = new StatisticsFile(this.mcServer, file2);
            statisticsfile.readStatFile();
            this.playerStatFiles.put(uuid, statisticsfile);
        }
        return statisticsfile;
    }

    public void setViewDistance(int distance) {
        this.viewDistance = distance;
        if (this.mcServer.worldServers != null) {
            for (WorldServer worldserver : this.mcServer.worldServers) {
                if (worldserver == null) continue;
                worldserver.getPlayerManager().setPlayerViewRadius(distance);
            }
        }
    }

    public List<EntityPlayerMP> getPlayerList() {
        return this.playerEntityList;
    }

    public EntityPlayerMP getPlayerByUUID(UUID playerUUID) {
        return this.uuidToPlayerMap.get(playerUUID);
    }

    public boolean bypassesPlayerLimit(GameProfile p_183023_1_) {
        return false;
    }
}
