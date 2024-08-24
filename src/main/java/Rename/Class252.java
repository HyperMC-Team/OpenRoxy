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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemSword;
public class Class252
implements Class3 {
    @Expose
    @SerializedName(value="name")
    private String name;
    private final Class263 category;
    private String description = "";
    private int key;
    @Expose
    @SerializedName(value="state")
    private boolean state;
    private final CopyOnWriteArrayList<Class261> settingsList = new CopyOnWriteArrayList();
    private final Class139 animation = new Class140(250, 1.0).setDirection(Class141.BACKWARDS);
    @Expose
    @SerializedName(value="settings")
    public Class24[] cfgSettings;
    public static ScaledResolution sr;
    private String suffix;

    public Class252(String name, Class263 category) {
        this.name = name;
        this.category = category;
        this.suffix = "";
    }

    public Class252(String name, Class263 category, int key) {
        this.name = name;
        this.category = category;
        this.key = key;
    }

    public Class252(String name, Class263 category, String description) {
        this.name = name;
        this.category = category;
        this.description = description;
    }

    public void state() {
        boolean bl = this.state = !this.state;
        if (Class252.mc.theWorld != null) {
            Class252.mc.theWorld.playSound(Class252.mc.thePlayer.posX, Class252.mc.thePlayer.posY, Class252.mc.thePlayer.posZ, "random.click", 0.5f, this.state ? 0.6f : 0.5f, false);
        }
        if (this.state) {
            this.onEnable();
            Class13.Method1(this);
            Class135.post(Class136.SUCCESS, this.name, "Enabled");
        } else {
            Class13.Method2(this);
            this.onDisable();
            Class135.post(Class136.DISABLE, this.name, "Disabled");
        }
    }

    public void setSuffix(Object suffix) {
        this.suffix = String.valueOf(suffix);
    }

    public final String getDisplayName() {
        return this.getDisplayName(ChatFormatting.GRAY);
    }

    public final String getDisplayName(ChatFormatting formatting) {
        String tag = this.getSuffix();
        if (tag == null || tag.isEmpty()) {
            return this.name;
        }
        return this.name + formatting + " " + tag;
    }

    public void setKey(int key) {
        this.key = key;
        for (Class261 s : this.settingsList) {
            if (!(s instanceof Class256 keybindSetting)) continue;
            keybindSetting.setCode(key);
        }
    }

    public void add(Class261... settings) {
        this.settingsList.addAll(Arrays.asList(settings));
    }

    public boolean isSword() {
        return Class252.mc.thePlayer.getHeldItem() != null && Class252.mc.thePlayer.getHeldItem().getItem() instanceof ItemSword;
    }

    public boolean isFood() {
        return Class252.mc.thePlayer.getHeldItem() != null && Class252.mc.thePlayer.getHeldItem().getItem() instanceof ItemFood;
    }

    public boolean isBow() {
        return Class252.mc.thePlayer.getHeldItem() != null && Class252.mc.thePlayer.getHeldItem().getItem() instanceof ItemBow;
    }

    public boolean isBlock() {
        return Class252.mc.thePlayer.getHeldItem() != null && Class252.mc.thePlayer.getHeldItem().getItem() instanceof ItemBlock;
    }

    public void setState(boolean state) {
        if (this.state == state) {
            return;
        }
        this.state = state;
        if (state) {
            this.onEnable();
            Class13.Method1(this);
            Class135.post(Class136.SUCCESS, this.name, "Enabled");
        } else {
            Class13.Method2(this);
            this.onDisable();
            Class135.post(Class136.DISABLE, this.name, "Disabled");
        }
    }

    public boolean isGapple() {
        return Class262.getModule(Class264.class).isState();
    }

    public boolean isNull() {
        return Class252.mc.thePlayer == null || Class252.mc.theWorld == null;
    }

    public void onEnable() {
    }

    public void onDisable() {
    }

    public boolean hasMode() {
        return this.suffix != null;
    }

    public String getName() {
        return this.name;
    }

    public Class263 getCategory() {
        return this.category;
    }

    public String getDescription() {
        return this.description;
    }

    public int getKey() {
        return this.key;
    }

    public boolean isState() {
        return this.state;
    }

    public CopyOnWriteArrayList<Class261> getSettingsList() {
        return this.settingsList;
    }

    public Class139 getAnimation() {
        return this.animation;
    }

    public Class24[] getCfgSettings() {
        return this.cfgSettings;
    }

    public String getSuffix() {
        return this.suffix;
    }

    public Class252(String name, Class263 category, String description, int key, boolean state, Class24[] cfgSettings, String suffix) {
        this.name = name;
        this.category = category;
        this.description = description;
        this.key = key;
        this.state = state;
        this.cfgSettings = cfgSettings;
        this.suffix = suffix;
    }
}

