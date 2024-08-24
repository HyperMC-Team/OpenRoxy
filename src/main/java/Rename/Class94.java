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

import net.minecraft.client.renderer.GlStateManager;
import tech.skidonion.obfuscator.annotations.Renamer;
import tech.skidonion.obfuscator.annotations.StringEncryption;

@Renamer
@StringEncryption
public class Class94
        extends Class95 {
    public final Class139 fadeAnimation = new Class140(150, 1.0, Class141.FORWARDS);
    private final Class139 scaleAnimation = new Class140(450, 1.0);

    public Class94() {
        this.fadeAnimation.setDirection(Class141.FORWARDS);
    }

    @Override
    public void initGui() {
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
    }

    @Override
    public void drawScreen(int mouseX, int mouseY) {
        if (this.fadeAnimation.isDone() && this.fadeAnimation.getDirection().equals(Class141.FORWARDS)) {
            this.fadeAnimation.setDirection(Class141.BACKWARDS);
            this.fadeAnimation.setDuration(300);
        }
        GlStateManager.alphaFunc(516, 0.15f);
        int color = Class460.interpolateColor(new Color(249, 249, 249, 28), new Color(255, 255, 240), this.fadeAnimation.getOutput().floatValue());
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        Class147.drawUnfilledCircle(this.x, this.y, 1.0f + 6.0f * this.scaleAnimation.getOutput().floatValue(), 4.0f, color);
        GlStateManager.alphaFunc(516, 0.1f);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int button) {
    }
}
