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
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import net.minecraft.client.multiplayer.ServerData;
import net.viamcp.vialoadingbase.ViaLoadingBase;
import tech.skidonion.obfuscator.annotations.Renamer;
import tech.skidonion.obfuscator.annotations.StringEncryption;

@Renamer
@StringEncryption
public class Class2 {
    public static double Method1() {
        if (ViaLoadingBase.getInstance().getTargetVersion().isOlderThanOrEqualTo(ProtocolVersion.v1_8)) {
            return 0.005;
        }
        return 0.003;
    }

    public static float Method2() {
        if (ViaLoadingBase.getInstance().getTargetVersion().isOlderThanOrEqualTo(ProtocolVersion.v1_8)) {
            return 16.0f;
        }
        return 1.0f;
    }

    public static float Method3() {
        if (ViaLoadingBase.getInstance().getTargetVersion().isOlderThanOrEqualTo(ProtocolVersion.v1_8)) {
            return 0.125f;
        }
        return 0.1875f;
    }

    public static double Method4(double minX) {
        if (ViaLoadingBase.getInstance().getTargetVersion().isOlderThanOrEqualTo(ProtocolVersion.v1_8)) {
            return minX;
        }
        return 0.0625;
    }

    public static double Method5(double minY) {
        if (ViaLoadingBase.getInstance().getTargetVersion().isOlderThanOrEqualTo(ProtocolVersion.v1_8)) {
            return minY;
        }
        return 0.0;
    }

    public static double Method6(double minZ) {
        if (ViaLoadingBase.getInstance().getTargetVersion().isOlderThanOrEqualTo(ProtocolVersion.v1_8)) {
            return minZ;
        }
        return 0.0625;
    }

    public static double Method7(double maxX) {
        if (ViaLoadingBase.getInstance().getTargetVersion().isOlderThanOrEqualTo(ProtocolVersion.v1_8)) {
            return maxX;
        }
        return 0.9375;
    }

    public static double Method8(double maxY) {
        if (ViaLoadingBase.getInstance().getTargetVersion().isOlderThanOrEqualTo(ProtocolVersion.v1_8)) {
            return maxY;
        }
        return 0.09375;
    }

    public static double Method9(double maxZ) {
        if (ViaLoadingBase.getInstance().getTargetVersion().isOlderThanOrEqualTo(ProtocolVersion.v1_8)) {
            return maxZ;
        }
        return 0.9375;
    }

    public static void Method10(ServerData serverData) {
        if (serverData.serverIP.toLowerCase().contains("hypixel.net")) {
            Class162.print("\u771f\u7684Hypixel");
        } else {
            Class162.print("\u5047\u7684Hypixel");
        }
    }
}

