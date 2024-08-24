package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Class254
extends Class261 {
    @Expose
    @SerializedName(value="name")
    private boolean state;

    public Class254(String name, boolean state) {
        this.name = name;
        this.state = state;
    }

    public boolean isEnabled() {
        return this.state;
    }

    public void toggle() {
        this.setState(!this.isEnabled());
    }

    public Boolean getConfigValue() {
        return this.isEnabled();
    }

    public void setState(boolean state) {
        this.state = state;
    }
}

