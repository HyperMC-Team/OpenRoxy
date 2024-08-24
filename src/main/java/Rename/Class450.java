package Rename;

import com.google.common.collect.Maps;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class Class450 {
    private static final Map<File, Class448> regionFileCache = Maps.newHashMap();

    public static boolean hasChunkData(File file, int chunkX, int chunkZ) {
        Class448 regionFile = Class450.getRegionFile(file, chunkX, chunkZ);
        return regionFile != null && regionFile.method_7275(chunkX & 0x1F, chunkZ & 0x1F);
    }

    public static synchronized void clearRegionFileCache() {
        for (Class448 regionFile : regionFileCache.values()) {
            try {
                if (regionFile == null) continue;
                regionFile.method_2558();
            }
            catch (IOException var3) {
                var3.printStackTrace();
            }
        }
        regionFileCache.clear();
    }

    public static synchronized Class448 getRegionFile(File directory, int regionX, int regionZ) {
        File regionDir = new File(directory, "region");
        File regionFile = new File(regionDir, "r." + (regionX >> 5) + "." + (regionZ >> 5) + ".mca");
        Class448 region = regionFileCache.get(regionFile);
        if (region != null) {
            return region;
        }
        if (regionDir.exists() && regionFile.exists()) {
            if (regionFileCache.size() >= 256) {
                Class450.clearRegionFileCache();
            }
            Class448 newRegion = new Class448(regionFile);
            regionFileCache.put(regionFile, newRegion);
            return newRegion;
        }
        return null;
    }

    public static DataInputStream getChunkInputStream(File directory, int chunkX, int chunkZ) {
        Class448 regionFile = Class450.getRegionFile(directory, chunkX, chunkZ);
        return regionFile == null ? null : regionFile.method_6317(chunkX & 0x1F, chunkZ & 0x1F);
    }
}

