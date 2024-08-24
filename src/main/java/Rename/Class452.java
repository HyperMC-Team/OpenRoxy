package Rename;

import com.google.common.collect.Sets;
import java.util.HashSet;
import java.util.Properties;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.chunk.Chunk;
import tech.skidonion.obfuscator.annotations.Renamer;
import tech.skidonion.obfuscator.annotations.StringEncryption;

@Renamer
@StringEncryption
public class Class452 {
    private volatile String worldDirectoryName;
    private boolean isLoaded;
    private Class457 chunkManager;
    private static Class452 INSTANCE;

    public Class452() {
        INSTANCE = this;
    }

    public void loadWorldData(WorldClient worldClient) {
        if (!this.isLoaded) {
            Properties properties = new Properties();
            properties.setProperty("respath", "worlds/" + this.worldDirectoryName);
            this.setupChunkManager(worldClient, properties);
            this.isLoaded = true;
        }
    }

    public static Class452 getInstance() {
        return INSTANCE;
    }

    @Class14
    private void onWorld(Class55 event) {
        this.chunkManager = null;
        this.isLoaded = false;
        Class450.clearRegionFileCache();
    }

    @Class14
    public void onClientTick(Class52 clientTickEvent) {
        Entity entity;
        if (this.isLoaded() && (entity = Minecraft.getMinecraft().getRenderViewEntity()) != null && entity.worldObj instanceof WorldClient && entity.ticksExisted % 40 == 0) {
            HashSet loadedChunks = Sets.newHashSet();
            int renderDistance = Minecraft.getMinecraft().gameSettings.renderDistanceChunks;
            double posX = entity.posX;
            double posZ = entity.posZ;
            int chunkX = MathHelper.floor_double(posX / 16.0);
            int chunkZ = MathHelper.floor_double(posZ / 16.0);
            for (int xOffset = -renderDistance; xOffset <= renderDistance; ++xOffset) {
                for (int zOffset = -renderDistance; zOffset <= renderDistance; ++zOffset) {
                    int chunkPosX = chunkX + xOffset;
                    int chunkPosZ = chunkZ + zOffset;
                    loadedChunks.add(ChunkCoordIntPair.chunkXZ2Int(chunkPosX, chunkPosZ));
                    if (this.isChunkLoaded(chunkPosX, chunkPosZ)) continue;
                    this.loadChunk(chunkPosX, chunkPosZ);
                }
            }
            HashSet<Long> unloadedChunks = new HashSet<Long>(this.chunkManager.getLoadedChunks());
            unloadedChunks.removeAll(loadedChunks);
            for (Long l : unloadedChunks) {
                Chunk chunk = this.chunkManager.getLoadedChunk(l);
                this.unloadChunk(chunk.xPosition, chunk.zPosition);
            }
        }
    }

    public void unloadChunk(int x, int z) {
        Minecraft.getMinecraft().theWorld.doPreChunk(x, z, false);
    }

    public boolean loadChunk(int x, int z) {
        WorldClient worldClient = Minecraft.getMinecraft().theWorld;
        worldClient.doPreChunk(x, z, true);
        if (this.isChunkLoaded(x, z)) {
            worldClient.markBlockRangeForRenderUpdate(x << 4, 0, z << 4, (x << 4) + 15, 256, (z << 4) + 15);
            return true;
        }
        return false;
    }

    public boolean isChunkLoaded(int x, int z) {
        return this.chunkManager.isChunkLoaded(x, z);
    }

    private void setupChunkManager(WorldClient worldClient, Properties properties) {
        this.chunkManager = new Class457(worldClient, properties);
        Minecraft.getMinecraft().theWorld.clientChunkProvider = this.chunkManager;
        Minecraft.getMinecraft().theWorld.chunkProvider = this.chunkManager;
    }

    public void setWorldDirectoryName(String worldDirectoryName) {
        this.worldDirectoryName = worldDirectoryName;
    }

    public boolean isLoaded() {
        return this.isLoaded;
    }

    public Class457 getChunkManager() {
        return this.chunkManager;
    }
}

