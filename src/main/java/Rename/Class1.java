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
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import java.io.File;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.viamcp.ViaLoading;
import net.viamcp.vialoadingbase.ViaLoadingBase;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.Renamer;
import tech.skidonion.obfuscator.annotations.StringEncryption;

@Renamer
@StringEncryption
@NativeObfuscation
public enum Class1 {
    instance;

    private final String name = "BloodLine_Recode";
    private final String date = "20240822";
    private final String version = "1.0";
    private String username;
    private Class262 moduleManager;
    private Class23 configManager;
    private Class22 videoComponent;
    private Class103 moduleCollection;
    private Class21 slotSpoofComponent;
    private Class15 badPacketsComponent;
    private Class111 sideGui;
    private Class82 ircServer;
    public static final int validationStatus = 0;
    private final HashMap<Object, Class252> moduleMap = new HashMap();
    private final ExecutorService executor = Executors.newFixedThreadPool(10);
    public String XuJingLiangSiMa = "Update Logs";

    @NativeObfuscation.Inline
    public void onStart() {
        Class330.init();
        Class120.setupFonts();
        Class234.init();
        this.username = Class87.uid;
        this.moduleManager = new Class262();
        this.moduleManager.initialize();
        this.moduleCollection = new Class103();
        for (Class252 module : Class262.getModules()) {
            this.moduleMap.put(module.getClass(), module);
        }
        this.moduleCollection.setModules(this.moduleMap);
        ViaLoading.load();
        Class13.Method1(this.moduleManager);
        Class13.Method1(new Class17());
        Class13.Method1(new Class19());
        Class13.Method1(Class18.INSTANCE);
        Class13.Method1(new Class20());
        Class13.Method1(new Class482());
        Class13.Method1(new Class452());
        Class13.Method1(new Class16());
        Class13.Method1(new Class214());
        //this.ircServer = new Class82();
        //this.ircServer.initialize();
        this.badPacketsComponent = new Class15();
        this.slotSpoofComponent = new Class21();
        this.videoComponent = new Class22();
        Class130.init(new File(Minecraft.getMinecraft().mcDataDir, "background.mp4"));
        this.sideGui = new Class111();
        this.configManager = new Class23();
        Class23.defaultConfig = new File(Minecraft.getMinecraft().mcDataDir + "/BloodLine_Recode/Config.json");
        this.configManager.collectConfigs();
        if (Class23.defaultConfig.exists()) {
            this.configManager.loadConfig(this.configManager.readConfigData(Class23.defaultConfig.toPath()));
        }
        ViaLoadingBase.getInstance().reload(ProtocolVersion.v1_12_2);
    }

    @NativeObfuscation.Inline
    public void Method1() {
        this.configManager.saveDefaultConfig();
        Class130.stop();
    }

    public Class116 Method2(Class252 module, String name, float x, float y) {
        Class117.draggables.put(name, new Class116(module, name, x, y));
        return Class117.draggables.get(name);
    }

    public GuiScreen Method3() {
        return new Class131();
    }

    public String Method4() {
        return this.name;
    }

    public String Method5() {
        return this.date;
    }

    public String Method6() {
        return this.version;
    }

    public String Method7() {
        return this.username;
    }

    public Class262 Method8() {
        return this.moduleManager;
    }

    public Class23 Method9() {
        return this.configManager;
    }

    public Class22 Method10() {
        return this.videoComponent;
    }

    public Class103 Method11() {
        return this.moduleCollection;
    }

    public Class21 Method12() {
        return this.slotSpoofComponent;
    }

    public Class15 Method13() {
        return this.badPacketsComponent;
    }

    public Class111 Method14() {
        return this.sideGui;
    }

    public Class82 Method15() {
        return this.ircServer;
    }

    public HashMap<Object, Class252> Method16() {
        return this.moduleMap;
    }

    public ExecutorService Method17() {
        return this.executor;
    }

    public String Method18() {
        return this.XuJingLiangSiMa;
    }
}

