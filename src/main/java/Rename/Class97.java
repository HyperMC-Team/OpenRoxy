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

import java.awt.Color;
import java.util.Arrays;
import java.util.List;

public class Class97
        implements Class107 {
    private float x;
    private float y;
    private float width;
    private float height;
    private float alpha;
    private Color accentColor;
    private final String name;
    private String selection;
    private List<String> options;
    private boolean opened = false;
    private boolean bypass = false;
    private final Class139 openAnimation = new Class140(250, 1.0).setDirection(Class141.BACKWARDS);
    private final Class139 hoverAnimation = new Class140(250, 1.0).setDirection(Class141.BACKWARDS);
    private final Class471 hoverRectAnimation = new Class471();
    private final Class139 hoverRectFadeAnimation = new Class140(250, 1.0).setDirection(Class141.BACKWARDS);

    public Class97(String name, String ... options) {
        this.name = name;
        this.selection = options[0];
        this.options = Arrays.asList(options);
    }

    public Class97(String name) {
        this.name = name;
    }

    @Override
    public void initGui() {
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
    }

    @Override
    public void drawScreen(int mouseX, int mouseY) {
        boolean hoveringMainRect = Class111.isHovering(this.x, this.y, this.width, this.height, mouseX, mouseY);
        if (this.bypass) {
            hoveringMainRect = Class465.isHovering(this.x, this.y, this.width, this.height, mouseX, mouseY);
        }
        this.hoverAnimation.setDirection(hoveringMainRect ? Class141.FORWARDS : Class141.BACKWARDS);
        Color textColor = Class460.applyOpacity(Color.WHITE, this.alpha);
        this.openAnimation.setDirection(this.opened ? Class141.FORWARDS : Class141.BACKWARDS);
        float openAnim = this.openAnimation.getOutput().floatValue();
        if (!this.openAnimation.isDone() || this.opened) {
            float dropdownY = this.getY() + (this.getHeight() + 4.0f) * openAnim;
            float dropdownHeight = (float)this.options.size() * this.getHeight();
            Class148.drawRound(this.getX(), dropdownY, this.getWidth(), dropdownHeight, 3.0f, Class460.tripleColor(17, this.getAlpha() * openAnim));
            boolean mouseOutsideRect = (float)mouseY < dropdownY || (float)mouseY > dropdownY + dropdownHeight || (float)mouseX < this.getX() || (float)mouseX > this.getX() + this.getWidth();
            this.hoverRectFadeAnimation.setDirection(mouseOutsideRect ? Class141.BACKWARDS : Class141.FORWARDS);
            this.hoverRectFadeAnimation.setDuration(mouseOutsideRect ? 200 : 350);
            Class148.drawRound(this.getX(), this.hoverRectAnimation.getOutput(), this.getWidth(), this.getHeight(), 3.0f, Class460.tripleColor(26, this.getAlpha() * this.hoverRectFadeAnimation.getOutput().floatValue() * openAnim));
            int seperation = 0;
            for (String option : this.options) {
                boolean hovering = Class111.isHovering(this.getX(), dropdownY + (float)seperation, this.getWidth(), this.getHeight(), mouseX, mouseY);
                if (this.bypass) {
                    hovering = Class465.isHovering(this.getX(), dropdownY + (float)seperation, this.getWidth(), this.getHeight(), mouseX, mouseY);
                }
                if (hovering) {
                    this.hoverRectAnimation.animate(dropdownY + (float)seperation, 18);
                }
                Color optionColor = this.selection.equals(option) ? this.accentColor : textColor;
                tenacityFont18.drawString(option, this.getX() + 5.0f, dropdownY + (float)seperation + tenacityFont18.getMiddleOfBox(this.getHeight()), Class460.applyOpacity(optionColor, openAnim));
                seperation = (int)((float)seperation + this.getHeight());
            }
        }
        Class148.drawRound(this.getX(), this.getY(), this.getWidth(), this.getHeight(), 3.0f, Class460.tripleColor(17 + (int)(3.0f * this.hoverAnimation.getOutput().floatValue()), this.getAlpha()));
        tenacityFont18.drawString("§l" + this.name + ":§r " + this.selection, this.getX() + 4.0f, this.getY() + tenacityFont18.getMiddleOfBox(this.getHeight()), textColor);
        float iconX = this.getX() + this.getWidth() - 10.0f;
        float iconY = this.getY() + iconFont16.getMiddleOfBox(this.getHeight());
        Class147.rotateStart(iconX, iconY, iconFont20.getStringWidth("z"), iconFont20.getHeight(), 180.0f * this.openAnimation.getOutput().floatValue());
        iconFont20.drawString("z", this.getX() + this.getWidth() - 10.0f, this.getY() + iconFont20.getMiddleOfBox(this.getHeight()) + 1.0f, textColor);
        Class147.rotateEnd();
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        boolean hoveringMainRect = Class111.isHovering(this.x, this.y, this.width, this.height, mouseX, mouseY);
        if (this.bypass) {
            hoveringMainRect = Class465.isHovering(this.x, this.y, this.width, this.height, mouseX, mouseY);
        }
        if (hoveringMainRect && button == 1) {
            boolean bl = this.opened = !this.opened;
        }
        if (this.opened) {
            float dropdownY = this.getY() + (this.getHeight() + 4.0f);
            int seperation = 0;
            for (String option : this.options) {
                boolean hovering = Class111.isHovering(this.getX(), dropdownY + (float)seperation, this.getWidth(), this.getHeight(), mouseX, mouseY);
                if (this.bypass) {
                    hovering = Class465.isHovering(this.getX(), dropdownY + (float)seperation, this.getWidth(), this.getHeight(), mouseX, mouseY);
                }
                if (hovering && button == 0) {
                    this.selection = option;
                    this.opened = false;
                }
                seperation = (int)((float)seperation + this.getHeight());
            }
        }
    }

    public boolean isClosed() {
        return this.openAnimation.finished(Class141.BACKWARDS);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
    }

    public void setupOptions(String ... options) {
        this.options = Arrays.asList(options);
        this.selection = options[0];
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public float getAlpha() {
        return this.alpha;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public void setAccentColor(Color accentColor) {
        this.accentColor = accentColor;
    }

    public String getSelection() {
        return this.selection;
    }

    public void setBypass(boolean bypass) {
        this.bypass = bypass;
    }
}
