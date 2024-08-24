package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import Rename.Class3;
import net.minecraft.client.multiplayer.ServerData;

public class Class213 {
    public static String getIp() {
        ServerData serverData;
        String serverIp = "Singleplayer";
        if (Class3.mc.theWorld.isRemote && (serverData = Class3.mc.getCurrentServerData()) != null) {
            serverIp = serverData.serverIP;
        }
        return serverIp;
    }

    public static int getPing() {
        if (Class3.mc.isSingleplayer()) {
            return 0;
        }
        return (int) Class3.mc.getCurrentServerData().pingToServer;
    }
}

