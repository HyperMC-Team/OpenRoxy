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

public class Class100
        implements Class107 {
    private float x;
    private float y;
    private float alpha;
    public Color accentColor;
    public Color textColor = new Color(191, 191, 191);
    private Runnable clickAction;
    private boolean clickable = true;
    private Class119 iconFont = iconFont16;
    private final Class139 hoverAnimation = new Class140(250, 1.0);
    private String icon;
    private Class151 tooltip;

    public Class100(String icon) {
        this.icon = icon;
    }

    public Class100(String icon, String tooltip) {
        this.icon = icon;
        this.tooltip = new Class151(tooltip);
    }

    @Override
    public void initGui() {
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
    }

    @Override
    public void drawScreen(int mouseX, int mouseY) {
        float iconHeight;
        float iconWidth = this.iconFont.getStringWidth(this.icon);
        boolean hovering = Class111.isHovering(this.x - 3.0f, this.y - 3.0f, iconWidth + 6.0f, (iconHeight = (float)this.iconFont.getHeight()) + 6.0f, mouseX, mouseY);
        this.hoverAnimation.setDirection(hovering ? Class141.FORWARDS : Class141.BACKWARDS);
        Color iconColor = Class460.interpolateColorC(this.textColor, this.accentColor, this.hoverAnimation.getOutput().floatValue());
        this.iconFont.drawString(this.icon, this.x, this.y, Class460.applyOpacity(iconColor, this.alpha));
        if (this.tooltip != null) {
            Class1.instance.Method14().addTooltip(this.tooltip);
            this.tooltip.setHovering(hovering);
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        boolean hovering = Class111.isHovering(this.x - 3.0f, this.y - 3.0f, iconFont16.getStringWidth(this.icon) + 6.0f, iconFont16.getHeight() + 6, mouseX, mouseY);
        if (this.clickable && button == 0 && hovering && this.clickAction != null) {
            this.clickAction.run();
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
    }

    public float getWidth() {
        return this.iconFont.getStringWidth(this.icon);
    }

    public float getHeight() {
        return this.iconFont.getHeight();
    }

    public void setTooltip(String tooltipText) {
        if (this.tooltip == null) {
            this.tooltip = new Class151(tooltipText);
        } else {
            this.tooltip.setTip(tooltipText);
        }
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public void setAccentColor(Color accentColor) {
        this.accentColor = accentColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public void setClickAction(Runnable clickAction) {
        this.clickAction = clickAction;
    }

    public void setClickable(boolean clickable) {
        this.clickable = clickable;
    }

    public void setIconFont(Class119 iconFont) {
        this.iconFont = iconFont;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getAlpha() {
        return this.alpha;
    }

    public String getIcon() {
        return this.icon;
    }
}
