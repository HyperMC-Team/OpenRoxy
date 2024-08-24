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
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.Session;

public final class Class91
extends GuiScreen {
    private final GuiScreen parentScreen;
    private GuiTextField username;
    private final Class88 loginButton = new Class88("CrackLogin", 0.0f, 0.0f, 0.0f, 0.0f);
    private final Class88 microsoftButton = new Class88("Microsoft", 0.0f, 0.0f, 0.0f, 0.0f);
    private final Class88 randomButton = new Class88("RandomName", 0.0f, 0.0f, 0.0f, 0.0f);
    private final Class88 backButton = new Class88("Back", 0.0f, 0.0f, 0.0f, 0.0f);
    public List<String> logs = new ArrayList<String>();
    private final Class90 microsoftLoginHandler = new Class90();

    public Class91(GuiScreen parentScreen) {
        this.parentScreen = parentScreen;
    }

    @Override
    public void initGui() {
        this.username = new GuiTextField(0, this.mc.fontRendererObj, 0, 0, 148, 20);
        this.username.setText("");
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution sr = new ScaledResolution(this.mc);
        int tW = 180;
        int tH = 24;
        int h = sr.getScaledHeight();
        int w = sr.getScaledWidth();
        int hxX = this.width / 2 - tW / 2;
        int hxY = this.height / 2 - tH / 2 - 48;
        Class130.render(w, h);
        Class330.arial40.drawCenteredString("Alt Manager", (float)this.width / 2.0f, hxY - 8, Color.WHITE.getRGB());
        Class330.arial20.drawCenteredString(EnumChatFormatting.YELLOW + "Username: " + EnumChatFormatting.RESET + this.mc.session.getUsername(), (float)this.width / 2.0f, hxY + 170, Color.WHITE.getRGB());
        this.username.drawTextBox();
        this.username.xPosition = hxX;
        this.username.yPosition = hxY + 24;
        this.username.width = tW;
        this.username.height = tH;
        if (!this.username.isFocused() && this.username.getText().isEmpty()) {
            this.mc.fontRendererObj.drawStringWithShadow("Username", hxX + 4, hxY + 32, new Color(160, 160, 160).getRGB());
        }
        this.loginButton.drawButton(mouseX, mouseY);
        this.loginButton.update(hxX, hxY + tH * 2 + 4, tW, tH);
        this.microsoftButton.drawButton(mouseX, mouseY);
        this.microsoftButton.update(hxX, hxY + tH * 3 + 6, tW, tH);
        this.randomButton.drawButton(mouseX, mouseY);
        this.randomButton.update(hxX, hxY + tH * 4 + 8, tW, tH);
        this.backButton.drawButton(mouseX, mouseY);
        this.backButton.update(hxX, hxY + tH * 5 + 10, tW, tH);
        this.backButton.displayName = this.microsoftLoginHandler.logging ? EnumChatFormatting.RED + "Stop" : "Back";
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == 1 && this.microsoftLoginHandler.logging) {
            this.microsoftLoginHandler.stop();
            return;
        }
        this.username.textboxKeyTyped(typedChar, keyCode);
        super.keyTyped(typedChar, keyCode);
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        this.username.mouseClicked(mouseX, mouseY, mouseButton);
        this.backButton.clicked(mouseButton, () -> {
            if (this.microsoftLoginHandler.logging) {
                this.microsoftLoginHandler.stop();
            } else {
                this.mc.displayGuiScreen(this.parentScreen);
            }
        });
        if (this.microsoftLoginHandler.logging) {
            return;
        }
        this.loginButton.clicked(mouseButton, () -> {
            if (!this.username.getText().isEmpty()) {
                this.mc.session = new Session(this.username.getText(), "", "", "legacy");
            }
        });
        this.microsoftButton.clicked(mouseButton, () -> this.microsoftLoginHandler.start(this));
        this.randomButton.clicked(mouseButton, () -> {
            this.mc.session = new Session(Class91.randomString("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_", 10), "", "", "legacy");
        });
    }

    public static String randomString(String pool, int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            builder.append(pool.charAt(ThreadLocalRandom.current().nextInt(0, pool.length())));
        }
        return builder.toString();
    }
}

