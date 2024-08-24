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

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.Display;
import tech.skidonion.obfuscator.annotations.Renamer;
import tech.skidonion.obfuscator.annotations.StringEncryption;

@Renamer
@StringEncryption
public class Class131
extends GuiScreen
implements GuiYesNoCallback {
    private float currentX = 0.0f;
    private float currentY = 0.0f;
    private final int photo = 1;
    private final Class238 time = new Class238();
    private final List<Class133> buttons = new ArrayList<Class133>(){
        {
            this.add(new Class133("SinglePlayer"));
            this.add(new Class133("MultiPlayer"));
            this.add(new Class133("AltManager"));
            this.add(new Class133("Settings"));
            this.add(new Class133("Exit"));
        }
    };


    @Override
    public void initGui() {
        this.buttons.forEach(Class133::initGui);
        super.initGui();
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) {
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution sr = new ScaledResolution(this.mc);
        int h = sr.getScaledHeight();
        int w = sr.getScaledWidth();
        float xDiff = ((float)mouseX - (float)h / 2.0f - this.currentX) / (float)sr.getScaleFactor();
        float yDiff = ((float)mouseY - (float)w / 2.0f - this.currentY) / (float)sr.getScaleFactor();
        this.currentX += xDiff * 0.3f;
        this.currentY += yDiff * 0.3f;
        GlStateManager.translate(this.currentX / 30.0f, this.currentY / 15.0f, 0.0f);
        Class130.render(w, h);
        GlStateManager.translate(-this.currentX / 30.0f, -this.currentY / 15.0f, 0.0f);
        float buttonWidth = 140.0f;
        float buttonHeight = 25.0f;
        int count = 0;
        for (Class133 button : this.buttons) {
            button.x = (float)this.width / 2.0f - buttonWidth / 2.0f;
            button.y = (float)this.height / 2.0f - buttonHeight / 2.0f - 25.0f + (float)count;
            button.width = buttonWidth;
            button.height = buttonHeight;
            button.clickAction = () -> {
                switch (button.text) {
                    case "SinglePlayer": {
                        this.mc.displayGuiScreen(new GuiSelectWorld(this));
                        break;
                    }
                    case "MultiPlayer": {
                        this.mc.displayGuiScreen(new GuiMultiplayer(this));
                        break;
                    }
                    case "AltManager": {
                        this.mc.displayGuiScreen(new Class91(this));
                        break;
                    }
                    case "Settings": {
                        this.mc.displayGuiScreen(new GuiOptions(this, this.mc.gameSettings));
                        break;
                    }
                    case "Exit": {
                        this.mc.shutdown();
                    }
                }
            };
            button.drawScreen(mouseX, mouseY);
            count += (int)(buttonHeight + 5.0f);
        }
        String username = Class87.uid;
        Display.setTitle("Minecraft 1.8.9  Use: " + username + "[\u5988\u5988\u5165]");
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        this.buttons.forEach(button -> button.mouseClicked(mouseX, mouseY, mouseButton));
    }
}

