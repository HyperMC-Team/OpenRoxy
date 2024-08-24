package Rename;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class Class444 {
    private final String text;
    private final Class443.SubType type;
    private final List<Class467> buttons;
    private float x;
    private float y;
    private float width;
    private float height;
    private final Class447 dragComponent = new Class447();
    private final Class455 scroll = new Class455();
    private GuiScreen prevGui;

    public Class444(String text, Class443.SubType type, List<Class467> buttons, GuiScreen prevGui) {
        this.text = text;
        this.type = type;
        this.buttons = buttons;
        this.prevGui = prevGui;
    }

    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        Class469 font16 = Class330.arial16;
        Class469 bold18 = Class330.arial18;
        this.dragComponent.setX(this.x);
        this.dragComponent.setY(this.y);
        this.dragComponent.setWidth(this.width);
        this.dragComponent.setHeight(this.height);
        this.dragComponent.setLimitHeight(this.height);
        this.dragComponent.handleDrag(mouseX, mouseY, 0, false);
        this.x = this.dragComponent.getX();
        this.y = this.dragComponent.getY();
        Class468.drawRound(this.x, this.y, this.width, this.height, 7.0f, new Color(0, 0, 0, 120));
        bold18.drawString(this.text, this.x + 6.0f, this.y + 8.0f, -1);
        bold18.drawString("\u25cf", this.x + this.width - 14.0f, this.y + 8.0f, new Color(233, 30, 99).getRGB());
        Class465.startGlScissor((int)this.x, (int)this.y + 22, (int)this.width, (int)this.height - 22);
        float offsetY = 0.0f;
        for (Class467 button : this.buttons) {
            button.setFont(font16);
            button.setWidth(font16.getStringWidth(button.getText()) + 15);
            button.setHeight(font16.getHeight() + 10);
            button.setX(this.x + this.width / 2.0f - button.getWidth() / 2.0f);
            button.setY(this.y + 22.0f + offsetY + this.scroll.getAnimationTarget());
            button.drawScreen(mouseX, mouseY, partialTicks);
            offsetY += button.getHeight() + 5.0f;
        }
        this.scroll.setMaxTarget(offsetY - this.height);
        if (Class465.isHovering(this.x, this.y, this.width, this.height, mouseX, mouseY)) {
            this.scroll.use();
        }
        this.scroll.animate();
        this.scroll.use();
        Class465.stopGlScissor();
    }

    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        this.dragComponent.handleDrag(mouseX, mouseY, mouseButton, true);
        if (Class465.isHovering(this.x + this.width - 14.0f, this.y + 8.0f, 12.0f, 12.0f, mouseX, mouseY)) {
            Minecraft.getMinecraft().displayGuiScreen(this.prevGui);
        }
        if (!Class465.isHovering(this.x, this.y + 22.0f, this.width, this.height, mouseX, mouseY)) {
            return;
        }
        for (Class467 button : this.buttons) {
            button.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    public String getText() {
        return this.text;
    }

    public Class443.SubType getType() {
        return this.type;
    }

    public List<Class467> getButtons() {
        return this.buttons;
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

    public Class447 getDragComponent() {
        return this.dragComponent;
    }

    public Class455 getScroll() {
        return this.scroll;
    }

    public GuiScreen getPrevGui() {
        return this.prevGui;
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

    public void setPrevGui(GuiScreen prevGui) {
        this.prevGui = prevGui;
    }
}

