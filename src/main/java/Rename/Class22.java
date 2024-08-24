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

import java.io.File;
import java.io.FileNotFoundException;

import net.minecraft.client.Minecraft;
import tech.skidonion.obfuscator.annotations.Renamer;
import tech.skidonion.obfuscator.annotations.StringEncryption;

@Renamer
@StringEncryption
public final class Class22 {
    public Class22() {
        File videoFile = new File(Minecraft.getMinecraft().mcDataDir, "background.mp4");
        if (!videoFile.exists()) {
            try {
                Class462.unpackFile(videoFile, "assets/minecraft/bloodline/background.mp4");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

