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

import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class Class104
        extends Class95 {
    private int searchScore = 0;
    public final Class252 module;
    public Class139 settingAnimation;
    public Class139 hoverDescriptionAnimation;
    public Class252 binding;
    public float rectOffset = 0.0f;
    public boolean rightClicked = false;
    public boolean drawSettingThing = false;
    private Class139 rectScaleAnimation;
    private Class139 checkScaleAnimation;

    public Class104(Class252 module) {
        this.module = module;
    }

    @Override
    public void initGui() {
        this.hoverDescriptionAnimation = new Class140(250, 1.0);
        this.hoverDescriptionAnimation.setDirection(Class141.BACKWARDS);
        this.settingAnimation = new Class140(400, 1.0);
        this.settingAnimation.setDirection(Class141.BACKWARDS);
        this.rectScaleAnimation = new Class140(250, 1.0);
        this.rectScaleAnimation.setDirection(Class141.BACKWARDS);
        this.checkScaleAnimation = new Class144(550, 1.0, 3.8f, 2.0f, false);
        this.checkScaleAnimation.setDirection(Class141.BACKWARDS);
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        if (this.binding != null) {
            if (keyCode == 57 || keyCode == 1 || keyCode == 211) {
                this.binding.setKey(0);
            } else {
                this.binding.setKey(keyCode);
            }
            this.binding = null;
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY) {
        Class148.drawRound(this.x + 0.5f, this.y + 0.5f, this.rectWidth - 1.0f, 34.0f, 5.0f, new Color(47, 49, 54));
        if (this.rectScaleAnimation == null) {
            System.out.println("CRAZXy " + this.module.getName());
        }
        this.rectScaleAnimation.setDirection(this.module.isState() ? Class141.FORWARDS : Class141.BACKWARDS);
        this.checkScaleAnimation.setDirection(this.module.isState() ? Class141.FORWARDS : Class141.BACKWARDS);
        this.checkScaleAnimation.setDuration(this.module.isState() ? 550 : 250);
        Color clickModColor = Color.WHITE;
        Color clickModColor2 = Color.WHITE;
        Class123<Color, Color> colors = Class123.of(new Color(97, 194, 162), new Color(65, 130, 108));
        Class148.drawRound(this.x + 0.5f, this.y + 0.5f, 34.0f, 34.0f, 5.0f, new Color(68, 71, 78));
        Class147.drawGoodCircle(this.x + 17.5f, this.y + 17.5f, 5.0f, new Color(47, 49, 54).getRGB());
        float rectScale = this.rectScaleAnimation.getOutput().floatValue();
        GL11.glPushMatrix();
        GL11.glTranslatef(this.x + 17.0f, this.y + 17.0f, 0.0f);
        GL11.glScaled(rectScale, rectScale, 0.0);
        GL11.glTranslatef(-(this.x + 17.0f), -(this.y + 17.0f), 0.0f);
        GL11.glEnable(3042);
        float width = 35.0f;
        float height = 35.0f;
        Class148.drawGradientCornerLR(this.x + 0.5f, this.y + 0.5f, width - 1.0f, height - 1.0f, 5.0f, Class460.applyOpacity(colors.getFirst(), rectScale), Class460.applyOpacity(colors.getSecond(), rectScale));
        GL11.glPopMatrix();
        float textX = this.x + (18.0f - Class120.iconFont35.getStringWidth("o") / 2.0f);
        float textY = (float)((double)this.y + 18.5 - (double)((float) Class120.iconFont35.getHeight() / 2.0f));
        GL11.glPushMatrix();
        GL11.glTranslatef(textX + 9.0f, textY + 9.0f, 0.0f);
        GL11.glScaled(Math.max(0.0f, this.checkScaleAnimation.getOutput().floatValue()), Math.max(0.0f, this.checkScaleAnimation.getOutput().floatValue()), 0.0);
        GL11.glTranslatef(-(textX + 9.0f), -(textY + 9.0f), 0.0f);
        Class120.iconFont35.drawSmoothString("o", textX, textY, Class460.applyOpacity(-1, this.checkScaleAnimation.getOutput().floatValue()));
        GL11.glPopMatrix();
        boolean hoverModule = Class465.isHovering(this.x, this.y, this.rectWidth, 35.0f, mouseX, mouseY);
        this.hoverDescriptionAnimation.setDirection(hoverModule ? Class141.FORWARDS : Class141.BACKWARDS);
        this.hoverDescriptionAnimation.setDuration(hoverModule ? 300 : 400);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        float xStart = this.x + 55.0f + tenacityFont24.getStringWidth(this.module.getName());
        float yVal = this.y + 17.5f - (float)tenacityFont18.getHeight() / 2.0f;
        if (this.binding != this.module && (!this.hoverDescriptionAnimation.isDone() || this.hoverDescriptionAnimation.finished(Class141.FORWARDS))) {
            float hover = this.hoverDescriptionAnimation.getOutput().floatValue();
            float descWidth = 305.0f - (55.0f + tenacityFont24.getStringWidth(this.module.getName()) + 15.0f);
            tenacityFont18.drawWrappedText(this.module.getDescription(), xStart, yVal, new Color(128, 134, 141, (int)(255.0f * hover)).getRGB(), descWidth, 3.0f);
        } else if (this.binding == this.module) {
            tenacityFont18.drawString("Currently bound to " + Keyboard.getKeyName(this.module.getKey()), xStart, yVal, new Color(128, 134, 141).getRGB());
        }
        tenacityFont24.drawString(this.module.getName(), this.x + 42.0f, this.y + 17.5f - (float)tenacityFont24.getHeight() / 2.0f, -1);
        this.settingAnimation.setDirection(this.drawSettingThing ? Class141.FORWARDS : Class141.BACKWARDS);
        int interpolateColorr = Class460.interpolateColorsBackAndForth(40, 1, colors.getFirst(), colors.getSecond(), false).getRGB();
        Class148.drawRound(this.x + this.rectWidth - 14.5f, this.y + 0.5f, 14.0f, 34.0f, 5.0f, Class460.interpolateColorC(new Color(47, 49, 54), new Color(interpolateColorr), this.settingAnimation.getOutput().floatValue()));
        Class147.drawGoodCircle((double)(this.x + this.rectWidth) - 7.5, (double)this.y + 7.5, 2.5f, -1);
        Class147.drawGoodCircle((double)(this.x + this.rectWidth) - 7.5, (double)this.y + 17.5, 2.5f, -1);
        Class147.drawGoodCircle((double)(this.x + this.rectWidth) - 7.5, (double)this.y + 27.5, 2.5f, -1);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (Class465.isHovering(this.x, this.y, this.rectWidth, 35.0f, mouseX, mouseY) && this.y > this.bigRecty + this.rectOffset && this.y < this.bigRecty + 255.0f) {
            if (button == 0) {
                this.module.state();
            }
            if (button == 2) {
                this.binding = this.module;
            }
            this.rightClicked = button == 1;
        }
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int button) {
        if (button == 0) {
            this.binding = null;
        }
    }

    public int getSearchScore() {
        return this.searchScore;
    }

    public void setSearchScore(int searchScore) {
        this.searchScore = searchScore;
    }
}
