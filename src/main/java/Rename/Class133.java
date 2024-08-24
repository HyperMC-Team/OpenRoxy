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

import java.awt.Color;

import net.minecraft.util.ResourceLocation;
import tech.skidonion.obfuscator.annotations.Renamer;
import tech.skidonion.obfuscator.annotations.StringEncryption;

@Renamer
@StringEncryption
public class Class133
implements Class107 {
    public final String text;
    private Class139 hoverAnimation;
    public float x;
    public float y;
    public float width;
    public float height;
    public Runnable clickAction;
    private static final ResourceLocation rs = new ResourceLocation("bloodline/bg/menu-rect.png");

    public Class133(String text) {
        this.text = text;
    }

    @Override
    public void initGui() {
        this.hoverAnimation = new Class140(200, 1.0);
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
    }

    @Override
    public void drawScreen(int mouseX, int mouseY) {
        boolean hovered = Class133.isHovering(this.x, this.y, this.width, this.height, mouseX, mouseY);
        this.hoverAnimation.setDirection(hovered ? Class141.FORWARDS : Class141.BACKWARDS);
        Color rectColor = new Color(35, 37, 43, 102);
        rectColor = Class472.interpolateColorC(rectColor, Class472.brighter(rectColor, 0.4f), this.hoverAnimation.getOutput().floatValue());
        Class468.drawRound(this.x, this.y, this.width, this.height, 3.0f, rectColor);
        Class228.drawGlow(this.x, this.y, this.width, this.height, 22, new Color(0, 0, 0, 120));
        Class115.tenacityFont24.drawCenteredString(this.text, this.x + this.width / 2.0f, this.y + 8.0f, -1);
    }

    public void drawOutline() {
        Class147.drawImage(rs, this.x, this.y, this.width, this.height);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        boolean hovered = Class133.isHovering(this.x, this.y, this.width, this.height, mouseX, mouseY);
        if (hovered) {
            this.clickAction.run();
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
    }

    public static boolean isHovering(float x, float y, float width, float height, int mouseX, int mouseY) {
        return (float)mouseX >= x && (float)mouseY >= y && (float)mouseX < x + width && (float)mouseY < y + height;
    }
}

