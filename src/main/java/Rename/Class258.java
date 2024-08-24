package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
public class Class258
extends Class261 {
    private final Map<String, Class254> boolSettings;

    public Class258(String name, String ... booleanSettingNames) {
        this.name = name;
        this.boolSettings = new HashMap<String, Class254>();
        Arrays.stream(booleanSettingNames).forEach(boolName -> this.boolSettings.put(boolName.toLowerCase(), new Class254(boolName, false)));
    }

    public Class258(String name, Class254... booleanSettings) {
        this.name = name;
        this.boolSettings = new HashMap<String, Class254>();
        Arrays.stream(booleanSettings).forEach(booleanSetting -> this.boolSettings.put(booleanSetting.name.toLowerCase(), booleanSetting));
    }

    public Class254 getSetting(String settingName) {
        return this.boolSettings.computeIfAbsent(settingName.toLowerCase(), k -> null);
    }

    public boolean isEnabled(String settingName) {
        return this.boolSettings.get(settingName.toLowerCase()).isEnabled();
    }

    public Collection<Class254> getBoolSettings() {
        return this.boolSettings.values();
    }

    public HashMap<String, Boolean> getConfigValue() {
        HashMap<String, Boolean> booleans = new HashMap<String, Boolean>();
        for (Class254 booleanSetting : this.boolSettings.values()) {
            booleans.put(booleanSetting.name, booleanSetting.isEnabled());
        }
        return booleans;
    }
}

