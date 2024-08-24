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

import net.minecraft.client.gui.ScaledResolution;
import tech.skidonion.obfuscator.annotations.Renamer;
import tech.skidonion.obfuscator.annotations.StringEncryption;

@Renamer
@StringEncryption
public class Class111
        implements Class107 {
    private boolean focused;
    private float rectWidth;
    private float rectHeight;
    private final Class139 openAnimation = new Class140(250, 1.0).setDirection(Class141.BACKWARDS);
    private final Class139 hoverAnimation = new Class140(250, 1.0).setDirection(Class141.BACKWARDS);
    private final Class139 clickAnimation = new Class140(250, 1.0).setDirection(Class141.BACKWARDS);
    private final Class112 hotbar = new Class112();
    private HashMap<String, Class137> panels;
    private HashMap<String, Class98> forms;
    private static Class98 currentForm;
    private final List<Class151> tooltips = new ArrayList<Class151>();
    public boolean typing = false;
    private final Color greenEnabledColor = new Color(70, 220, 130);
    private final Color redBadColor = new Color(209, 56, 56);
    private final Class139 formFadeAnimation = new Class140(250, 1.0).setDirection(Class141.BACKWARDS);
    private Class96 drag;
    private Class150 timerUtil;
    private float animateX = 0.0f;
    private boolean canSnap;

    @Override
    public void onDrag(int mouseX, int mouseY) {
        if (this.drag != null) {
            this.drag.onDraw(mouseX, mouseY);
        }
    }

    @Override
    public void initGui() {
        if (this.panels == null) {
            this.panels = new HashMap();
        }
        if (this.forms == null) {
            this.forms = new HashMap();
        }
        this.hotbar.initGui();
        this.panels.values().forEach(Class107::initGui);
        this.focused = false;
        currentForm = null;
        this.timerUtil = new Class150();
        this.rectWidth = 550.0f;
        this.rectHeight = 350.0f;
        ScaledResolution sr = new ScaledResolution(mc);
        this.drag = new Class96(sr.getScaledWidth() - 40, (float)sr.getScaledHeight() / 2.0f - this.rectHeight / 2.0f);
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        if (this.focused) {
            if (currentForm != null) {
                currentForm.keyTyped(typedChar, keyCode);
                if (keyCode == 1) {
                    this.formFadeAnimation.setDirection(Class141.BACKWARDS);
                    currentForm.clear();
                }
                return;
            }
            this.hotbar.keyTyped(typedChar, keyCode);
            if (keyCode == 1) {
                if (this.hotbar.searchField.isFocused() || !this.hotbar.searchField.getText().equals("")) {
                    this.hotbar.setCurrentPanel(this.hotbar.getCarouselButtons().getCurrentButton());
                }
                this.hotbar.searchField.setText("");
                this.hotbar.searchField.setFocused(false);
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY) {
        ScaledResolution sr = new ScaledResolution(mc);
        this.clickAnimation.setDirection(this.focused ? Class141.FORWARDS : Class141.BACKWARDS);
        if (this.animateX == 0.0f) {
            this.animateX = this.drag.getX();
        } else if (this.clickAnimation.isDone()) {
            this.animateX = this.drag.getX();
        }
        if (this.clickAnimation.getDirection().forwards() && !this.clickAnimation.isDone()) {
            this.drag.setX(Class146.interpolateFloat(this.animateX, (float)sr.getScaledWidth() / 2.0f - this.rectWidth / 2.0f, this.clickAnimation.getOutput()));
        } else {
            this.drag.setX(Class146.interpolateFloat(this.drag.getInitialX(), this.animateX, this.clickAnimation.getOutput()));
        }
        float x = Class146.interpolateFloat(sr.getScaledWidth(), this.drag.getX(), this.openAnimation.getOutput());
        float y = this.drag.getY();
        boolean hovering = Class111.isHovering(x, y, this.rectWidth, this.rectHeight, mouseX, mouseY);
        if (this.clickAnimation.isDone()) {
            this.hoverAnimation.setDirection(hovering || this.focused ? Class141.FORWARDS : Class141.BACKWARDS);
        }
        if (this.clickAnimation.finished(Class141.FORWARDS)) {
            this.canSnap = this.drag.getX() + this.rectWidth / 2.0f + this.rectWidth / 4.0f > (float)sr.getScaledWidth();
            this.hoverAnimation.setDirection(this.canSnap ? Class141.BACKWARDS : this.hoverAnimation.getDirection());
        } else {
            this.canSnap = false;
        }
        Color color = Class460.tripleColor(35, 0.7f + 0.25f * this.hoverAnimation.getOutput().floatValue() + 0.05f * this.clickAnimation.getOutput().floatValue());
        Class148.drawRound(x + 0.625f, y + 0.625f, this.rectWidth - 1.25f, this.rectHeight - 1.25f, 5.0f, color);
        float alpha = 0.25f + 0.15f * this.hoverAnimation.getOutput().floatValue() + 0.6f * this.clickAnimation.getOutput().floatValue();
        this.hotbar.x = x;
        this.hotbar.y = y;
        this.hotbar.width = this.rectWidth;
        this.hotbar.height = 36.0f;
        this.hotbar.alpha = alpha;
        this.hotbar.drawScreen(mouseX, mouseY);
        boolean bl = this.typing = this.hotbar.searchField.isFocused() || !this.hotbar.searchField.getText().equals("") || currentForm != null;
        if (!this.focused) {
            return;
        }
        if (this.panels.containsKey(this.hotbar.getCurrentPanel())) {
            Class137 panel = this.panels.get(this.hotbar.getCurrentPanel());
            panel.setX(x);
            panel.setY(y + this.hotbar.height);
            panel.setWidth(this.rectWidth);
            panel.setHeight(this.rectHeight - this.hotbar.height);
            panel.setAlpha(alpha);
            panel.drawScreen(mouseX, mouseY);
        }
        if (this.formFadeAnimation.finished(Class141.BACKWARDS)) {
            currentForm = null;
        }
        if (!this.formFadeAnimation.isDone() || this.formFadeAnimation.finished(Class141.FORWARDS)) {
            float formAnim = this.formFadeAnimation.getOutput().floatValue();
            Class148.drawRound(x + 0.625f, y + 0.625f, this.rectWidth - 1.25f, this.rectHeight - 1.25f, 5.0f, Class460.applyOpacity(Color.BLACK, 0.4f * formAnim));
            currentForm.setX(x + this.rectWidth / 2.0f - currentForm.getWidth() / 2.0f);
            currentForm.setY(y + this.rectHeight / 2.0f - currentForm.getHeight() / 2.0f);
            currentForm.setAlpha(alpha * formAnim);
            currentForm.drawScreen(mouseX, mouseY);
        }
        this.tooltips.forEach(tooltip -> tooltip.drawScreen(mouseX, mouseY));
    }

    public void drawForEffects(boolean bloom) {
        ScaledResolution sr = new ScaledResolution(mc);
        float x = Class146.interpolateFloat(sr.getScaledWidth(), this.drag.getX(), this.openAnimation.getOutput());
        float y = this.drag.getY();
        Class148.drawRound(x + 0.625f, y + 0.625f, this.rectWidth - 1.25f, this.rectHeight - 1.25f, 5.0f, Color.BLACK);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        boolean hovering = Class111.isHovering(this.drag.getX(), this.drag.getY(), this.rectWidth, this.rectHeight, mouseX, mouseY);
        if (!this.focused && hovering) {
            this.focused = true;
        } else if (this.focused) {
            if (currentForm != null) {
                currentForm.mouseClicked(mouseX, mouseY, button);
                return;
            }
            boolean hoveringTop = Class111.isHovering(this.drag.getX(), this.drag.getY(), this.rectWidth, 40.0f, mouseX, mouseY);
            if (!this.hotbar.searchField.isFocused() && !this.hotbar.getCarouselButtons().isHovering()) {
                this.drag.onClick(mouseX, mouseY, button, hoveringTop);
            }
            this.hotbar.mouseClicked(mouseX, mouseY, button);
            if (this.panels.containsKey(this.hotbar.getCurrentPanel())) {
                this.panels.get(this.hotbar.getCurrentPanel()).mouseClicked(mouseX, mouseY, button);
            }
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int button) {
        this.drag.onRelease(button);
        if (this.canSnap) {
            this.focused = false;
        }
    }

    public void addTooltip(Class151 tooltip) {
        if (this.tooltips.contains(tooltip)) {
            return;
        }
        this.tooltips.add(tooltip);
    }

    public Class98 displayForm(String form) {
        if (form == null) {
            currentForm.clear();
            this.formFadeAnimation.setDirection(Class141.BACKWARDS);
            return null;
        }
        currentForm = this.forms.get(form);
        this.formFadeAnimation.setDirection(Class141.FORWARDS);
        return currentForm;
    }

    public static boolean isHovering(float x, float y, float width, float height, int mouseX, int mouseY) {
        return currentForm == null && Class465.isHovering(x, y, width, height, mouseX, mouseY);
    }

    public boolean isFocused() {
        return this.focused;
    }

    public float getRectWidth() {
        return this.rectWidth;
    }

    public float getRectHeight() {
        return this.rectHeight;
    }

    public Class139 getOpenAnimation() {
        return this.openAnimation;
    }

    public Class139 getHoverAnimation() {
        return this.hoverAnimation;
    }

    public Class139 getClickAnimation() {
        return this.clickAnimation;
    }

    public Class112 getHotbar() {
        return this.hotbar;
    }

    public HashMap<String, Class137> getPanels() {
        return this.panels;
    }

    public HashMap<String, Class98> getForms() {
        return this.forms;
    }

    public List<Class151> getTooltips() {
        return this.tooltips;
    }

    public boolean isTyping() {
        return this.typing;
    }

    public Color getGreenEnabledColor() {
        return this.greenEnabledColor;
    }

    public Color getRedBadColor() {
        return this.redBadColor;
    }

    public Class139 getFormFadeAnimation() {
        return this.formFadeAnimation;
    }

    public Class96 getDrag() {
        return this.drag;
    }

    public Class150 getTimerUtil() {
        return this.timerUtil;
    }

    public float getAnimateX() {
        return this.animateX;
    }

    public boolean isCanSnap() {
        return this.canSnap;
    }

    public void setFocused(boolean focused) {
        this.focused = focused;
    }
}
