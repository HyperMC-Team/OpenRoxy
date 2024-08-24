package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.awt.Color;

public class Class88 {
    public String displayName;
    public float x;
    public float y;
    public float width;
    public float height;
    private final Class139 hoverAnimation = new Class140(500, 1.0);
    private boolean isHovered;

    public Class88(String displayName, float x, float y, float width, float height) {
        this.displayName = displayName;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void drawButton(int mouseX, int mouseY) {
        this.isHovered = Class88.isHovering(this.x, this.y, this.width, this.height, mouseX, mouseY);
        this.hoverAnimation.setDirection(this.isHovered ? Class141.FORWARDS : Class141.BACKWARDS);
        Color rectColor = new Color(35, 37, 43, 102);
        rectColor = Class472.interpolateColorC(rectColor, Class472.brighter(rectColor, 0.4f), this.hoverAnimation.getOutput().floatValue());
        Class468.drawRoundNoOffset(this.x, this.y, this.width, this.height, 2.0f, rectColor);
        Class330.arial18.drawCenteredString(this.displayName, this.x + this.width / 2.0f, this.y + this.height / 2.0f - 3.0f, -1);
    }

    public void clicked(int mouse, Runnable runnable) {
        if (this.isHovered && mouse == 0) {
            runnable.run();
        }
    }

    public void update(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public static boolean isHovering(float x, float y, float width, float height, int mouseX, int mouseY) {
        return (float)mouseX >= x && (float)mouseY >= y && (float)mouseX < x + width && (float)mouseY < y + height;
    }
}

