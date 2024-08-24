package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
public class Class257
extends Class261 {
    public final List<String> modes;
    private final HashMap<String, ArrayList<Class261>> childrenMap = new HashMap();
    private String defaultMode;
    private int modeIndex;
    @Expose
    @SerializedName(value="value")
    private String currentMode;

    public Class257(String name, String defaultMode, String ... modes) {
        this.name = name;
        this.modes = Arrays.asList(modes);
        this.modeIndex = this.modes.indexOf(defaultMode);
        if (this.currentMode == null) {
            this.currentMode = defaultMode;
        }
    }

    public String getMode() {
        return this.currentMode;
    }

    public boolean is(String mode) {
        return this.currentMode.equalsIgnoreCase(mode);
    }

    public boolean setMode(String mode) {
        this.modeIndex = this.modes.indexOf(mode);
        return true;
    }

    public void cycleForwards() {
        ++this.modeIndex;
        if (this.modeIndex > this.modes.size() - 1) {
            this.modeIndex = 0;
        }
        this.currentMode = this.modes.get(this.modeIndex);
    }

    public void cycleBackwards() {
        --this.modeIndex;
        if (this.modeIndex < 0) {
            this.modeIndex = this.modes.size() - 1;
        }
        this.currentMode = this.modes.get(this.modeIndex);
    }

    public void setCurrentMode(String currentMode) {
        this.currentMode = currentMode;
    }

    public String getConfigValue() {
        return this.currentMode;
    }
}

