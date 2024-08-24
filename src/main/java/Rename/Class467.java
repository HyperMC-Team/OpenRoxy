package Rename;

import java.awt.Color;
import net.minecraft.client.gui.GuiScreen;

public class Class467
extends GuiScreen {
    public final String text;
    private Class459 displayAnimation;
    private Class459 hoverAnimation = new Class474(500, 1.0);
    public float x;
    public float y;
    public float width;
    public float height;
    public Runnable clickAction;
    public Class469 font = Class330.arial20;

    public Class467(String text, Runnable clickAction) {
        this.text = text;
        this.displayAnimation = new Class474(1000, 255.0);
        this.font = Class330.arial20;
        this.clickAction = clickAction;
    }

    public Class467(String text) {
        this.text = text;
        this.displayAnimation = new Class474(1000, 255.0);
        this.font = Class330.arial20;
    }

    @Override
    public void initGui() {
        this.hoverAnimation = new Class474(500, 1.0);
        this.displayAnimation.setDirection(Class473.FORWARDS);
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float ticks) {
        boolean hovered = Class465.isHovering(this.x, this.y, this.width, this.height, mouseX, mouseY);
        this.hoverAnimation.setDirection(hovered ? Class473.FORWARDS : Class473.BACKWARDS);
        Color rectColor = new Color(32, 32, 32, (int)(this.displayAnimation.getOutput() * Math.max(0.7, this.hoverAnimation.getOutput())));
        Class468.drawRound(this.x, this.y, this.width, this.height, 4.0f, rectColor);
        this.font.drawCenteredString(this.text, this.x + this.width / 2.0f, this.y + this.font.getMiddleOfBox(this.height) + 2.0f, new Color(255, 255, 255, (int)this.displayAnimation.getOutput()).getRGB());
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        boolean hovered = Class465.isHovering(this.x, this.y, this.width, this.height, mouseX, mouseY);
        if (hovered) {
            this.clickAction.run();
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
    }

    @Override
    public void onGuiClosed() {
        this.displayAnimation.setDirection(Class473.BACKWARDS);
    }

    public void setDisplayAnimation(Class459 displayAnimation) {
        this.displayAnimation = displayAnimation;
    }

    public void setHoverAnimation(Class459 hoverAnimation) {
        this.hoverAnimation = hoverAnimation;
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

    public void setClickAction(Runnable clickAction) {
        this.clickAction = clickAction;
    }

    public void setFont(Class469 font) {
        this.font = font;
    }

    public String getText() {
        return this.text;
    }

    public Class459 getDisplayAnimation() {
        return this.displayAnimation;
    }

    public Class459 getHoverAnimation() {
        return this.hoverAnimation;
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

    public Runnable getClickAction() {
        return this.clickAction;
    }

    public Class469 getFont() {
        return this.font;
    }
}

