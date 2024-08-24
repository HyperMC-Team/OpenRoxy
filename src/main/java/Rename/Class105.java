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

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.client.gui.Gui;
import org.lwjgl.opengl.GL11;

public class Class105
        extends Class106 {
    public List<Class104> modules = new ArrayList<Class104>();
    public Class139 expandAnim;
    public Class139 expandAnim2;
    Class108 settingScroll = new Class108();
    private HashMap<Class252, Class110> settingsPanelHashMap;
    private HashMap<Class263, Class108> scrollHashMap;
    private boolean rightClicked = false;
    public Class263 currentCategory;
    private boolean typing = false;
    private Class104 currentlySelected;

    @Override
    public void initGui() {
        this.expandAnim2 = new Class140(300, 1.0, Class141.FORWARDS);
        this.expandAnim2.setDirection(Class141.BACKWARDS);
        if (this.scrollHashMap == null) {
            this.scrollHashMap = new HashMap();
            for (Class263 category : Class263.values()) {
                this.scrollHashMap.put(category, new Class108());
            }
            this.scrollHashMap.put(null, new Class108());
        }
        this.refreshSettingMap();
    }

    public void refreshSettingMap() {
        if (this.settingsPanelHashMap == null || Class103.reloadModules) {
            this.settingsPanelHashMap = new HashMap();
            for (Class252 module : Class1.instance.Method11().getModules()) {
                Class110 settingsPanel = new Class110(module);
                settingsPanel.initGui();
                this.settingsPanelHashMap.put(module, settingsPanel);
            }
        } else {
            this.settingsPanelHashMap.forEach((m, p) -> p.initGui());
        }
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        this.modules.forEach(moduleRect -> moduleRect.keyTyped(typedChar, keyCode));
        if (this.currentlySelected != null) {
            this.settingsPanelHashMap.get(this.currentlySelected.module).keyTyped(typedChar, keyCode);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY) {
        this.typing = false;
        if (Class103.reloadModules) {
            this.initGui();
            Class103.reloadModules = false;
            return;
        }
        this.expandAnim2.setDirection(this.rightClicked ? Class141.FORWARDS : Class141.BACKWARDS);
        int spacing = 0;
        Class108 scroll = this.scrollHashMap.get(this.currentCategory);
        if (!Class465.isHovering(this.x + (250.0f + 55.0f * this.expandAnim.getOutput().floatValue()), this.y, 135.0f, 250.0f, mouseX, mouseY) || this.currentlySelected == null) {
            scroll.onScroll(25);
        }
        double scrollVal = Class146.roundToHalf(scroll.getScroll());
        tenacityFont18.drawCenteredString("Click your scroll wheel while hovering a module to change a keybind", this.x + 152.5f, (float)((double)(this.y - 15.0f) + scrollVal), new Color(128, 134, 141, 150));
        scroll.setMaxScroll(Math.max(0, (this.modules.size() - 4) * 50));
        for (Class104 module : this.modules) {
            module.rectWidth = 305.0f;
            module.x = this.x;
            module.y = (float)((double)(this.y + (float)spacing) + scrollVal);
            module.bigRecty = this.bigRecty;
            if (!this.typing) {
                this.typing = module.binding != null;
            }
            module.drawSettingThing = this.currentlySelected == module;
            module.drawScreen(mouseX, mouseY);
            spacing += 50;
            if (!module.rightClicked) continue;
            if (this.currentlySelected == module) {
                this.rightClicked = false;
                this.currentlySelected = null;
                module.rightClicked = false;
                continue;
            }
            this.rightClicked = true;
            this.settingScroll = new Class108();
            this.currentlySelected = module;
            module.rightClicked = false;
        }
        if (this.currentlySelected != null) {
            if (Class465.isHovering(this.x + 305.0f, this.y, 135.0f, 250.0f, mouseX, mouseY)) {
                this.settingScroll.onScroll(25);
            }
            float newX = this.x + 5.0f + 305.0f;
            Class148.drawRound(newX, this.y - 20.0f, 130.0f, 255.0f, 8.0f, new Color(47, 49, 54));
            Class465.setAlphaLimit(0.0f);
            Gui.drawGradientRect2(newX - 0.5f, this.y, 130.5, 8.0, new Color(0, 0, 0, 70).getRGB(), new Color(0, 0, 0, 0).getRGB());
            tenacityBoldFont22.drawCenteredString(this.currentlySelected.module.getName(), newX + 62.5f, this.y - 15.0f, -1);
            GL11.glEnable(3089);
            Class147.scissor(newX, this.y + 0.5f, 135.0, 255.0);
            Class110 settingsPanel = this.settingsPanelHashMap.get(this.currentlySelected.module);
            this.settingScroll.setMaxScroll(Math.max(0.0f, settingsPanel.maxScroll - 100.0f));
            settingsPanel.x = this.x + 305.0f;
            settingsPanel.y = (float)((double)(this.y - 20.0f) + Class146.roundToHalf(this.settingScroll.getScroll()));
            settingsPanel.drawScreen(mouseX, mouseY);
            if (!this.typing) {
                this.typing = settingsPanel.typing;
            }
            GL11.glDisable(3089);
        }
        Class282.modernClickGui.adjustWidth(125.0f * this.expandAnim2.getOutput().floatValue());
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        this.modules.forEach(moduleRect -> moduleRect.mouseClicked(mouseX, mouseY, button));
        if (this.currentlySelected != null) {
            this.settingsPanelHashMap.get(this.currentlySelected.module).mouseClicked(mouseX, mouseY, button);
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        this.modules.forEach(moduleRect -> moduleRect.mouseReleased(mouseX, mouseY, state));
        if (this.currentlySelected != null) {
            this.settingsPanelHashMap.get(this.currentlySelected.module).mouseReleased(mouseX, mouseY, state);
        }
    }

    public boolean isTyping() {
        return this.typing;
    }
}
