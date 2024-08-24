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

public class Class93
        extends Class95 {
    public final Class263 category;
    public Class263 currentCategory;
    private Class139 hoverAnimation;
    private Class139 enableAnimation;
    public Class139 expandAnimation;

    public Class93(Class263 category) {
        this.category = category;
    }

    @Override
    public void initGui() {
        this.hoverAnimation = new Class142(200, 1.0);
        this.enableAnimation = new Class140(250, 1.0);
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
    }

    @Override
    public void drawScreen(int mouseX, int mouseY) {
        boolean hovering = Class465.isHovering(this.x, this.y - 3.0f, 83 - (this.expandAnimation.getDirection().forwards() ? 62 : 0), 18.0f, mouseX, mouseY);
        this.hoverAnimation.setDirection(hovering ? Class141.FORWARDS : Class141.BACKWARDS);
        this.hoverAnimation.setDuration(hovering ? 200 : 350);
        this.enableAnimation.setDirection(this.currentCategory == this.category ? Class141.FORWARDS : Class141.BACKWARDS);
        int color = Class460.interpolateColor(new Color(68, 71, 78), new Color(115, 115, 115), this.hoverAnimation.getOutput().floatValue());
        color = Class460.interpolateColor(new Color(color), new Color(-1), this.enableAnimation.getOutput().floatValue());
        GlStateManager.color(1.0f, 1.0f, 1.0f);
        GlStateManager.color(1.0f, 1.0f, 1.0f);
        float xDiff = 10.0f * this.expandAnimation.getOutput().floatValue();
        tenacityFont24.drawString(this.category.name(), this.x + 27.0f + xDiff, this.y, color);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        boolean hovering = Class465.isHovering(this.x, this.y - 3.0f, 83 - (this.expandAnimation.getDirection().forwards() ? 62 : 0), 18.0f, mouseX, mouseY);
        this.hovering = hovering && button == 0;
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int button) {
    }
}
