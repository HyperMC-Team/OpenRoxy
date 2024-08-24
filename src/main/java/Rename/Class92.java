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
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Class92
        implements Class107 {
    private float x;
    private float y;
    private float rectWidth;
    private float rectHeight;
    private float alpha;
    private boolean hovering = false;
    private Color backgroundColor = new Color(39, 39, 39);
    private String currentButton;
    private final Map<String, Class139> options;
    private final Class471 rectAnimation = new Class471();

    public Class92(String ... options) {
        this.options = Arrays.stream(options).collect(Collectors.toMap(Function.identity(), v -> new Class140(250, 1.0)));
        this.currentButton = options[0];
    }

    @Override
    public void initGui() {
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
    }

    @Override
    public void drawScreen(int mouseX, int mouseY) {
        float buttonWidth = (float)this.options.size() * this.rectWidth;
        Color textColor = Class460.applyOpacity(Color.WHITE, this.alpha);
        this.hovering = Class465.isHovering(this.x, this.y, buttonWidth, this.rectHeight, mouseX, mouseY);
        Class148.drawRound(this.x, this.y, buttonWidth, this.rectHeight, 5.0f, Class460.applyOpacity(this.backgroundColor, this.alpha));
        Color accentColor = Class460.applyOpacity(Color.CYAN, this.getAlpha());
        float coloredX = this.rectAnimation.getOutput();
        Class148.drawRound(this.x + coloredX, this.y, this.rectWidth, this.rectHeight, 5.0f, accentColor);
        int seperation = 0;
        for (Map.Entry<String, Class139> entry : this.options.entrySet()) {
            boolean hovering;
            float buttonX = this.x + (float)seperation;
            boolean isCurrentButton = entry.getKey().equals(this.currentButton);
            Class139 animation = entry.getValue();
            if (isCurrentButton) {
                this.rectAnimation.animate(seperation, 18);
            }
            animation.setDirection((hovering = Class111.isHovering(buttonX, this.y, buttonWidth, this.rectHeight, mouseX, mouseY)) ? Class141.FORWARDS : Class141.BACKWARDS);
            float textAlpha = 0.5f + 0.3f * animation.getOutput().floatValue();
            tenacityFont24.drawCenteredString(entry.getKey(), this.x + this.rectWidth / 2.0f + (float)seperation, this.y + tenacityFont24.getMiddleOfBox(this.rectHeight), Class460.applyOpacity(isCurrentButton ? textColor : Class460.applyOpacity(textColor, textAlpha), this.getAlpha()));
            seperation = (int)((float)seperation + this.rectWidth);
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        int seperation = 0;
        for (Map.Entry<String, Class139> entry : this.options.entrySet()) {
            float buttonX = this.x + (float)seperation;
            boolean hovering = Class111.isHovering(buttonX, this.y, this.rectWidth, this.rectHeight, mouseX, mouseY);
            if (hovering) {
                this.currentButton = entry.getKey();
            }
            seperation = (int)((float)seperation + this.rectWidth);
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
    }

    public float getTotalWidth() {
        return (float)this.options.size() * this.rectWidth;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getRectWidth() {
        return this.rectWidth;
    }

    public float getRectHeight() {
        return this.rectHeight;
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

    public void setRectWidth(float rectWidth) {
        this.rectWidth = rectWidth;
    }

    public void setRectHeight(float rectHeight) {
        this.rectHeight = rectHeight;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public boolean isHovering() {
        return this.hovering;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public String getCurrentButton() {
        return this.currentButton;
    }
}
