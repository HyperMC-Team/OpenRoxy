package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import tech.skidonion.obfuscator.annotations.Renamer;
import tech.skidonion.obfuscator.annotations.StringEncryption;

@Renamer
@StringEncryption
public class Class85
extends GuiButton {
    private int color;

    public Class85(int buttonId, int x, int y, int widthIn, int heightIn, String buttonText) {
        super(buttonId, x, y, widthIn, heightIn, buttonText);
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY) {
        if (this.visible) {
            this.hovered = mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
            Class230.drawRect(this.xPosition, this.yPosition, this.xPosition + this.width, this.yPosition + this.height, this.color);
            Class330.arial40.drawString(this.displayString, this.xPosition + this.width / 2 - Class330.arial40.getStringWidth(this.displayString) / 2, this.yPosition + this.height / 2 - Class330.arial40.getHeight() / 2, Color.WHITE.getRGB(), true);
        }
    }

    public void updateCoordinates(float x, float y) {
        this.xPosition = (int)x;
        this.yPosition = (int)y;
    }

    public boolean hovered(int mouseX, int mouseY) {
        return mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height;
    }

    public void setColor(int color) {
        this.color = color;
    }
}

