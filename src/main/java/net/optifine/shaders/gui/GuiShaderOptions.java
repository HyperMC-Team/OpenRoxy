package net.optifine.shaders.gui;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.src.Config;
import net.minecraft.util.MathHelper;
import net.optifine.Lang;
import net.optifine.gui.GuiScreenOF;
import net.optifine.gui.TooltipManager;
import net.optifine.gui.TooltipProviderShaderOptions;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.config.ShaderOption;
import net.optifine.shaders.config.ShaderOptionProfile;
import net.optifine.shaders.config.ShaderOptionScreen;
import net.optifine.shaders.gui.GuiButtonShaderOption;
import net.optifine.shaders.gui.GuiSliderShaderOption;

public class GuiShaderOptions
extends GuiScreenOF {
    private GuiScreen prevScreen;
    protected String title = "Shader Options";
    private GameSettings settings;
    private TooltipManager tooltipManager = new TooltipManager(this, new TooltipProviderShaderOptions());
    private String screenName = null;
    private String screenText = null;
    private boolean changed = false;
    public static final String OPTION_PROFILE = "<profile>";
    public static final String OPTION_EMPTY = "<empty>";
    public static final String OPTION_REST = "*";

    public GuiShaderOptions(GuiScreen guiscreen, GameSettings gamesettings) {
        this.prevScreen = guiscreen;
        this.settings = gamesettings;
    }

    public GuiShaderOptions(GuiScreen guiscreen, GameSettings gamesettings, String screenName) {
        this(guiscreen, gamesettings);
        this.screenName = screenName;
        if (screenName != null) {
            this.screenText = Shaders.translate("screen." + screenName, screenName);
        }
    }

    @Override
    public void initGui() {
        this.title = I18n.format("of.options.shaderOptionsTitle", new Object[0]);
        int i = 100;
        int j = 0;
        int k = 30;
        int l = 20;
        int i1 = 120;
        int j1 = 20;
        int k1 = Shaders.getShaderPackColumns(this.screenName, 2);
        ShaderOption[] ashaderoption = Shaders.getShaderPackOptions(this.screenName);
        if (ashaderoption != null) {
            int l1 = MathHelper.ceiling_double_int((double)ashaderoption.length / 9.0);
            if (k1 < l1) {
                k1 = l1;
            }
            for (int i2 = 0; i2 < ashaderoption.length; ++i2) {
                ShaderOption shaderoption = ashaderoption[i2];
                if (shaderoption == null || !shaderoption.isVisible()) continue;
                int j2 = i2 % k1;
                int k2 = i2 / k1;
                int l2 = Math.min(this.width / k1, 200);
                j = (this.width - l2 * k1) / 2;
                int i3 = j2 * l2 + 5 + j;
                int j3 = k + k2 * l;
                int k3 = l2 - 10;
                String s = GuiShaderOptions.getButtonText(shaderoption, k3);
                GuiButtonShaderOption guibuttonshaderoption = Shaders.isShaderPackOptionSlider(shaderoption.getName()) ? new GuiSliderShaderOption(i + i2, i3, j3, k3, j1, shaderoption, s) : new GuiButtonShaderOption(i + i2, i3, j3, k3, j1, shaderoption, s);
                guibuttonshaderoption.enabled = shaderoption.isEnabled();
                this.buttonList.add(guibuttonshaderoption);
            }
        }
        this.buttonList.add(new GuiButton(201, this.width / 2 - i1 - 20, this.height / 6 + 168 + 11, i1, j1, I18n.format("controls.reset", new Object[0])));
        this.buttonList.add(new GuiButton(200, this.width / 2 + 20, this.height / 6 + 168 + 11, i1, j1, I18n.format("gui.done", new Object[0])));
    }

    public static String getButtonText(ShaderOption so, int btnWidth) {
        String s = so.getNameText();
        if (so instanceof ShaderOptionScreen) {
            ShaderOptionScreen shaderoptionscreen = (ShaderOptionScreen)so;
            return s + "...";
        }
        FontRenderer fontrenderer = Config.getMinecraft().fontRendererObj;
        int i = fontrenderer.getStringWidth(": " + Lang.getOff()) + 5;
        while (fontrenderer.getStringWidth(s) + i >= btnWidth && s.length() > 0) {
            s = s.substring(0, s.length() - 1);
        }
        String s1 = so.isChanged() ? so.getValueColor(so.getValue()) : "";
        String s2 = so.getValueText(so.getValue());
        return s + ": " + s1 + s2;
    }

    @Override
    protected void actionPerformed(GuiButton guibutton) {
        if (guibutton.enabled) {
            if (guibutton.id < 200 && guibutton instanceof GuiButtonShaderOption) {
                GuiButtonShaderOption guibuttonshaderoption = (GuiButtonShaderOption)guibutton;
                ShaderOption shaderoption = guibuttonshaderoption.getShaderOption();
                if (shaderoption instanceof ShaderOptionScreen) {
                    String s = shaderoption.getName();
                    GuiShaderOptions guishaderoptions = new GuiShaderOptions(this, this.settings, s);
                    this.mc.displayGuiScreen(guishaderoptions);
                    return;
                }
                if (GuiShaderOptions.isShiftKeyDown()) {
                    shaderoption.resetValue();
                } else if (guibuttonshaderoption.isSwitchable()) {
                    shaderoption.nextValue();
                }
                this.updateAllButtons();
                this.changed = true;
            }
            if (guibutton.id == 201) {
                ShaderOption[] ashaderoption = Shaders.getChangedOptions(Shaders.getShaderPackOptions());
                for (int i = 0; i < ashaderoption.length; ++i) {
                    ShaderOption shaderoption1 = ashaderoption[i];
                    shaderoption1.resetValue();
                    this.changed = true;
                }
                this.updateAllButtons();
            }
            if (guibutton.id == 200) {
                if (this.changed) {
                    Shaders.saveShaderPackOptions();
                    this.changed = false;
                    Shaders.uninit();
                }
                this.mc.displayGuiScreen(this.prevScreen);
            }
        }
    }

    @Override
    protected void actionPerformedRightClick(GuiButton btn) {
        if (btn instanceof GuiButtonShaderOption) {
            GuiButtonShaderOption guibuttonshaderoption = (GuiButtonShaderOption)btn;
            ShaderOption shaderoption = guibuttonshaderoption.getShaderOption();
            if (GuiShaderOptions.isShiftKeyDown()) {
                shaderoption.resetValue();
            } else if (guibuttonshaderoption.isSwitchable()) {
                shaderoption.prevValue();
            }
            this.updateAllButtons();
            this.changed = true;
        }
    }

    @Override
    public void onGuiClosed() {
        super.onGuiClosed();
        if (this.changed) {
            Shaders.saveShaderPackOptions();
            this.changed = false;
            Shaders.uninit();
        }
    }

    private void updateAllButtons() {
        for (GuiButton guibutton : this.buttonList) {
            if (!(guibutton instanceof GuiButtonShaderOption)) continue;
            GuiButtonShaderOption guibuttonshaderoption = (GuiButtonShaderOption)guibutton;
            ShaderOption shaderoption = guibuttonshaderoption.getShaderOption();
            if (shaderoption instanceof ShaderOptionProfile) {
                ShaderOptionProfile shaderoptionprofile = (ShaderOptionProfile)shaderoption;
                shaderoptionprofile.updateProfile();
            }
            guibuttonshaderoption.displayString = GuiShaderOptions.getButtonText(shaderoption, guibuttonshaderoption.getButtonWidth());
            guibuttonshaderoption.valueChanged();
        }
    }

    @Override
    public void drawScreen(int x, int y, float f) {
        this.drawDefaultBackground();
        if (this.screenText != null) {
            this.drawCenteredString(this.fontRendererObj, this.screenText, this.width / 2, 15, 0xFFFFFF);
        } else {
            this.drawCenteredString(this.fontRendererObj, this.title, this.width / 2, 15, 0xFFFFFF);
        }
        super.drawScreen(x, y, f);
        this.tooltipManager.drawTooltips(x, y, this.buttonList);
    }
}
