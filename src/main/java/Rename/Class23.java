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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.internal.LinkedTreeMap;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import net.minecraft.client.Minecraft;

public class Class23 {
    public static final List<Class25> localConfigs = new ArrayList<Class25>();
    public static File defaultConfig;
    public final File file;
    private final Gson gson;

    public Class23() {
        this.file = new File(Minecraft.getMinecraft().mcDataDir, "/" + Class1.instance.Method4() + "/Configs");
        this.gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
    }

    public void collectConfigs() {
        localConfigs.clear();
        this.file.mkdirs();
        Arrays.stream(Objects.requireNonNull(this.file.listFiles())).forEach(f -> localConfigs.add(new Class25(f.getName().split("\\.")[0])));
    }

    public boolean saveConfig(String name, String content) {
        Class25 localConfig = new Class25(name);
        localConfig.getFile().getParentFile().mkdirs();
        try {
            Files.write(localConfig.getFile().toPath(), content.getBytes(StandardCharsets.UTF_8));
            return true;
        }
        catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean saveConfig(String name) {
        return this.saveConfig(name, this.serialize());
    }

    public boolean delete(String configName) {
        List configsMatch = localConfigs.stream().filter(localConfig -> localConfig.getName().equals(configName)).collect(Collectors.toList());
        try {
            Class25 configToDelete = (Class25)configsMatch.get(0);
            Files.deleteIfExists(configToDelete.getFile().toPath());
        }
        catch (IOException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void saveDefaultConfig() {
        defaultConfig.getParentFile().mkdirs();
        try {
            Files.write(defaultConfig.toPath(), this.serialize().getBytes(StandardCharsets.UTF_8));
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to save " + defaultConfig);
        }
    }

    public String serialize() {
        for (Class252 module : Class262.modules) {
            ArrayList<Class24> settings = new ArrayList<Class24>();
            for (Class261 setting : module.getSettingsList()) {
                Class24 cfgSetting = new Class24(null, null);
                cfgSetting.name = setting.name;
                cfgSetting.value = setting.getConfigValue();
                settings.add(cfgSetting);
            }
            module.cfgSettings = settings.toArray(new Class24[0]);
        }
        return this.gson.toJson(Class262.modules);
    }

    public String readConfigData(Path configPath) {
        try {
            return new String(Files.readAllBytes(configPath));
        }
        catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public void loadConfig(String data) {
        Class252[] modules = this.gson.fromJson(data, Class252[].class);
        for (Class252 module : Class262.modules) {
            for (Class252 configModule : modules) {
                if (!module.getName().equalsIgnoreCase(configModule.getName())) continue;
                try {
                    if (module.isState() != configModule.isState()) {
                        module.state();
                    }
                    for (Class261 setting : module.getSettingsList()) {
                        for (Class24 cfgSetting : configModule.cfgSettings) {
                            String value;
                            if (!setting.name.equals(cfgSetting.name)) continue;
                            if (setting instanceof Class256) {
                                module.setKey(Double.valueOf(String.valueOf(cfgSetting.value)).intValue());
                            }
                            if (setting instanceof Class254) {
                                ((Class254)setting).setState(Boolean.parseBoolean(String.valueOf(cfgSetting.value)));
                            }
                            if (setting instanceof Class257 ms) {
                                String value2 = String.valueOf(cfgSetting.value);
                                if (ms.modes.contains(value2)) {
                                    ms.setCurrentMode(value2);
                                } else {
                                    ms.setCurrentMode(ms.modes.getFirst());
                                }
                            }
                            if (setting instanceof Class259 ss) {
                                double value3;
                                try {
                                    value3 = Double.parseDouble(String.valueOf(cfgSetting.value));
                                }
                                catch (NumberFormatException e) {
                                    value3 = ss.getDefaultValue();
                                }
                                ss.setValue(value3);
                            }
                            if (setting instanceof Class258 mbs) {
                                LinkedTreeMap<String, Boolean> boolMap = (LinkedTreeMap)cfgSetting.value;

                                for(String s : boolMap.keySet()) {
                                    Class254 childSetting = mbs.getSetting(s);
                                    if (childSetting != null && boolMap.get(s) != null) {
                                        childSetting.setState(boolMap.get(s));
                                    }
                                }
                            }
                            if (setting instanceof Class255 colorSetting) {
                                if (JsonParser.parseString(cfgSetting.value.toString()).isJsonObject()) {
                                    JsonObject colorObject = JsonParser.parseString(cfgSetting.value.toString()).getAsJsonObject();
                                    colorSetting.setRainbow(true);
                                    float saturation = colorObject.get("saturation").getAsFloat();
                                    int speed = colorObject.get("speed").getAsInt();
                                    colorSetting.getRainbow().setSaturation(saturation);
                                    colorSetting.getRainbow().setSpeed(speed);
                                } else {
                                    int color = Double.valueOf(String.valueOf(cfgSetting.value)).intValue();
                                    Color c = new Color(color);
                                    float[] hsb = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), null);
                                    colorSetting.setColor(hsb[0], hsb[1], hsb[2]);
                                }
                            }
                            if (!(setting instanceof Class260) || (value = String.valueOf(cfgSetting.value)) == null) continue;
                            ((Class260)setting).setString(value);
                        }
                    }
                }
                catch (Exception e) {
                    Class162.print(e.getMessage());
                }
            }
        }
    }
}

