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
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import tech.skidonion.obfuscator.annotations.Renamer;
import tech.skidonion.obfuscator.annotations.StringEncryption;

@Renamer
@StringEncryption
public class Class102
extends GuiScreen {
    public static final Class96 drag = new Class96(40.0f, 40.0f);
    public static boolean searching = false;
    private final Color backgroundColor = new Color(30, 31, 35);
    private final Color categoryColor = new Color(47, 49, 54);
    private final Color lighterGray = new Color(68, 71, 78);
    private final List<Class94> circleClicks = new ArrayList<Class94>();
    private final List<Class95> categories = new ArrayList<Class95>(){
        {
            for (Class263 category : Class263.values()) {
                this.add(new Class93(category));
            }
        }
    };
    public float rectHeight = 255.0f;
    public float rectWidth = 370.0f;
    private Class263 currentCategory = Class263.Combat;
    private Class139 openingAnimation;
    private Class139 expandedAnimation;
    private Class105 modpanel;
    private HashMap<Class263, ArrayList<Class104>> moduleRects;
    private boolean firstOpen = true;
    public boolean typing;
    private float adjustment = 0.0f;
    private final List<Class104> searchResults = new ArrayList<Class104>();
    private final List<String> searchTerms = new ArrayList<String>();
    private String searchText;

    @Override
    public void onDrag(int mouseX, int mouseY) {
        if (this.firstOpen) {
            drag.setX((float)this.width / 2.0f - this.rectWidth / 2.0f);
            drag.setY((float)this.height / 2.0f - this.rectHeight / 2.0f);
            this.firstOpen = false;
        }
        drag.onDraw(mouseX, mouseY);
    }

    @Override
    public void initGui() {
        if (this.firstOpen) {
            drag.setX((float)this.width / 2.0f - this.rectWidth / 2.0f);
            drag.setY((float)this.height / 2.0f - this.rectHeight / 2.0f);
            this.firstOpen = false;
        }
        if (this.modpanel == null) {
            this.modpanel = new Class105();
        }
        this.currentCategory = Class282.getActiveCategory();
        this.categories.forEach(Class95::initGui);
        this.openingAnimation = new Class140(300, 1.0);
        this.expandedAnimation = new Class140(250, 1.0);
        if (this.moduleRects != null) {
            this.moduleRects.forEach((cat, list) -> list.forEach(Class104::initGui));
        }
        this.modpanel.initGui();
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
        if (keyCode == 1 && !this.typing) {
            this.openingAnimation.setDirection(Class141.BACKWARDS);
            Class282.setActiveCategory(this.currentCategory);
        }
        this.modpanel.keyTyped(typedChar, keyCode);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        boolean focusedConfigGui;
        if (Class103.reloadModules || this.moduleRects == null) {
            if (this.moduleRects == null) {
                this.moduleRects = new HashMap();
            } else {
                this.moduleRects.clear();
            }
            for (Class263 category : Class263.values()) {
                ArrayList<Class104> modules = new ArrayList<Class104>();
                for (Class252 module : Class1.instance.Method11().getModulesInCategory(category)) {
                    modules.add(new Class104(module));
                }
                this.moduleRects.put(category, modules);
            }
            this.moduleRects.forEach((cat, list) -> list.forEach(Class104::initGui));
            this.modpanel.refreshSettingMap();
            Class103.reloadModules = false;
            return;
        }
        boolean bl = this.typing = this.modpanel.isTyping() || Class1.instance.Method14().isFocused() && Class1.instance.Method14().isTyping();
        if (!this.typing) {
            Class271.updateStates();
        }
        int fakeMouseX = (focusedConfigGui = Class1.instance.Method14().isFocused()) ? 0 : mouseX;
        int fakeMouseY = focusedConfigGui ? 0 : mouseY;
        this.adjustment = 125.0f;
        drag.onDraw(fakeMouseX, fakeMouseY);
        float x = drag.getX();
        float y = drag.getY();
        if (!this.openingAnimation.isDone()) {
            x -= (float)this.width + this.rectWidth / 2.0f;
            x += ((float)this.width + this.rectWidth / 2.0f) * this.openingAnimation.getOutput().floatValue();
        } else if (this.openingAnimation.getDirection().equals(Class141.BACKWARDS)) {
            this.mc.displayGuiScreen(null);
            return;
        }
        Class148.drawRound(x, y, this.rectWidth, this.rectHeight, 10.0f, this.backgroundColor);
        float catWidth = 100.0f - 55.0f * this.expandedAnimation.getOutput().floatValue();
        boolean hoveringCat = Class465.isHovering(x, y, catWidth, this.rectHeight, fakeMouseX, fakeMouseY);
        boolean searching = false;
        if (this.expandedAnimation.isDone()) {
            this.expandedAnimation.setDirection(hoveringCat && !searching ? Class141.BACKWARDS : Class141.FORWARDS);
        }
        Class148.drawRound(x, y, 100.0f - 55.0f * this.expandedAnimation.getOutput().floatValue(), this.rectHeight, 10.0f, this.categoryColor);
        this.adjustWidth(55.0f - 55.0f * this.expandedAnimation.getOutput().floatValue());
        Class232.initStencilToWrite();
        Gui.drawRect2(x, y, 100.0f - 55.0f * this.expandedAnimation.getOutput().floatValue(), this.rectHeight, -1);
        Class232.readStencilBuffer(1);
        GL11.glEnable(3042);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("bloodline/clickgui/modernlogo.png"));
        Gui.drawModalRectWithCustomSizedTexture(x + 9.0f + 3.0f * this.expandedAnimation.getOutput().floatValue(), y + 6.0f, 0.0f, 0.0f, 20.5f, 20.5f, 20.5f, 20.5f);
        GL11.glDisable(3042);
        GL11.glEnable(3042);
        this.mc.getTextureManager().bindTexture(new ResourceLocation("bloodline/icon/clickgui/Combat.png"));
        Gui.drawModalRectWithCustomSizedTexture(x + 9.0f + 3.0f * this.expandedAnimation.getOutput().floatValue(), y + 40.0f, 0.0f, 0.0f, 20.5f, 20.5f, 20.5f, 20.5f);
        GL11.glDisable(3042);
        Gui.drawRect2(x + 10.0f, y + 35.0f, 80.0f - 55.0f * this.expandedAnimation.getOutput().floatValue(), 1.0, this.lighterGray.getRGB());
        float xAdjust = 10.0f * this.expandedAnimation.getOutput().floatValue();
        Class120.tenacityFont20.drawString("BloodLine", x + 35.0f + xAdjust, y + 13.0f, -1);
        Class120.tenacityFont14.drawString(Class1.instance.Method6(), x + 41.0f + Class120.tenacityFont18.getStringWidth("Tenacity") + xAdjust, y + 15.5f, new Color(98, 98, 98));
        int spacing = 0;
        for (Class95 category : this.categories) {
            category.x = x + 8.0f + 4.0f * this.expandedAnimation.getOutput().floatValue();
            category.y = y + 50.0f + (float)spacing;
            Class93 currentCatego = (Class93)category;
            currentCatego.expandAnimation = this.expandedAnimation;
            currentCatego.currentCategory = searching ? null : this.currentCategory;
            category.drawScreen(fakeMouseX, fakeMouseY);
            spacing += 30;
        }
        Class232.uninitStencilBuffer();
        float recWidth = 100.0f - 55.0f * this.expandedAnimation.getOutput().floatValue();
        Class232.initStencilToWrite();
        Class148.drawRound(x, y, this.rectWidth, this.rectHeight, 10.0f, this.backgroundColor);
        Class232.readStencilBuffer(1);
        this.modpanel.x = x + recWidth + 10.0f;
        this.modpanel.y = y + 20.0f;
        this.modpanel.bigRecty = y;
        this.modpanel.modules = this.getModuleRects(this.currentCategory);
        this.modpanel.currentCategory = searching ? null : this.currentCategory;
        this.modpanel.expandAnim = this.expandedAnimation;
        this.modpanel.drawScreen(fakeMouseX, fakeMouseY);
        Class232.uninitStencilBuffer();
        for (Class94 clickCircle : this.circleClicks) {
            clickCircle.drawScreen(fakeMouseX, fakeMouseY);
        }
        this.rectWidth = 370.0f + this.adjustment;
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        float rectWidth = 400.0f;
        double x = drag.getX();
        double y = drag.getY();
        boolean canDrag = Class465.isHovering((float)x, (float)y, rectWidth, 20.0f, mouseX, mouseY);
        if (!Class1.instance.Method14().isFocused()) {
            drag.onClick(mouseX, mouseY, mouseButton, canDrag);
            this.circleClicks.removeIf(clickCircle1 -> clickCircle1.fadeAnimation.isDone() && clickCircle1.fadeAnimation.getDirection().equals(Class141.BACKWARDS));
            Class94 clickCircle = new Class94();
            clickCircle.x = mouseX;
            clickCircle.y = mouseY;
            this.circleClicks.add(clickCircle);
            for (Class95 category : this.categories) {
                category.mouseClicked(mouseX, mouseY, mouseButton);
                if (!category.hovering) continue;
                this.currentCategory = ((Class93)category).category;
                return;
            }
            this.modpanel.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    @Override
    protected void mouseReleased(int mouseX, int mouseY, int state) {
        if (!Class1.instance.Method14().isFocused()) {
            drag.onRelease(state);
            this.modpanel.mouseReleased(mouseX, mouseY, state);
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }

    public void adjustWidth(float adjustment) {
        this.adjustment += adjustment;
    }

    public List<Class104> getModuleRects(Class263 category) {
        return this.moduleRects.get(category);
    }
}

