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

import java.io.File;

import net.minecraft.client.Minecraft;

public class Class25 {
    private final String name;
    private final File file;

    public Class25(String name) {
        this.name = name;
        this.file = new File(Minecraft.getMinecraft().mcDataDir + "/" + Class1.instance.Method4() + "/Configs/" + name + ".json");
    }

    public String getName() {
        return this.name;
    }

    public File getFile() {
        return this.file;
    }
}

