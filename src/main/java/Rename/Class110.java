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
import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class Class110 extends Class106 {
    private final Class252 module;
    private final HashMap<Class261, Class139> booleanSettingHashMap;
    private final HashMap<Class261, Class139> modeSettingHashMap;
    private final HashMap<Class261, Boolean> modeSettingClick;
    private final HashMap<Class261, HashMap<String, Class139>> modesHoverAnimation;
    private final HashMap<Class261, Class139> multiBoolSettingMap;
    private final HashMap<Class261, Boolean> multiBoolSettingClickMap;
    private final HashMap<Class259, Float> sliderMap;
    private final HashMap<Class261, HashMap<Class254, Class139>> boolHoverAnimation;
    private final HashMap<Class260, Class113> textFieldMap;
    public Class261 draggingNumber;
    public float maxScroll = 0.0F;
    private boolean hueFlag;
    private boolean saturationFlag;
    public boolean typing;

    public Class110(Class252 module) {
        this.module = module;
        this.booleanSettingHashMap = new HashMap();
        this.multiBoolSettingMap = new HashMap();
        this.multiBoolSettingClickMap = new HashMap();
        this.boolHoverAnimation = new HashMap();
        this.sliderMap = new HashMap();
        this.modeSettingHashMap = new HashMap();
        this.modeSettingClick = new HashMap();
        this.modesHoverAnimation = new HashMap();
        this.textFieldMap = new HashMap();

        for(Class261 setting : module.getSettingsList()) {
            if (setting instanceof Class254) {
                this.booleanSettingHashMap.put(setting, new Class140(250, 1.0));
            }

            if (setting instanceof Class258) {
                this.multiBoolSettingMap.put(setting, new Class140(250, 1.0));
                this.multiBoolSettingClickMap.put(setting, false);
                HashMap<Class254, Class139> boolMap = new HashMap();

                for(Class254 booleanSetting : ((Class258)setting).getBoolSettings()) {
                    boolMap.put(booleanSetting, new Class140(250, 1.0));
                }

                this.boolHoverAnimation.put(setting, boolMap);
            }

            if (setting instanceof Class259) {
                this.sliderMap.put((Class259)setting, 0.0F);
            }

            if (setting instanceof Class257 modeSetting) {
                HashMap<String, Class139> modesHashmap = new HashMap();
                this.modeSettingHashMap.put(setting, new Class140(250, 1.0));
                this.modeSettingClick.put(setting, false);

                for(String mode : modeSetting.modes) {
                    modesHashmap.put(mode, new Class140(250, 1.0));
                }

                this.modesHoverAnimation.put(setting, modesHashmap);
            }

            if (setting instanceof Class260 stringSetting) {
                Class113 textField = new Class113(tenacityFont18);
                textField.setText(stringSetting.getString());
                textField.setCursorPositionZero();
                this.textFieldMap.put(stringSetting, textField);
            }
        }
    }

    @Override
    public void initGui() {
        if (this.textFieldMap != null) {
            for(Map.Entry<Class260, Class113> entry : this.textFieldMap.entrySet()) {
                entry.getValue().setText(entry.getKey().getString());
                entry.getValue().setCursorPositionZero();
            }
        }
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        if (this.textFieldMap != null) {
            for(Map.Entry<Class260, Class113> entry : this.textFieldMap.entrySet()) {
                entry.getValue().keyTyped(typedChar, keyCode);
            }
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY) {
        this.handle(mouseX, mouseY, -1, Class99.DRAW);
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        this.handle(mouseX, mouseY, button, Class99.CLICK);
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
        this.handle(mouseX, mouseY, state, Class99.RELEASE);
    }

    public void handle(int mouseX, int mouseY, int button, Class99 type) {
        this.typing = false;
        if (type == Class99.RELEASE && button == 0) {
            this.draggingNumber = null;
        }

        Class123<Color, Color> colors = Class123.of(Color.CYAN);
        float count = 0.0F;
        float settingHeight = 22.0F;

        for(Class261 setting : this.module.getSettingsList()) {
            if (!setting.cannotBeShown() && !(setting instanceof Class256)) {
                float settingY = this.y + 30.0F + count * settingHeight;
                boolean isHoveringSetting = Class147.isHovering(this.x + 5.0F, settingY, 130.0F, settingHeight - 2.0F, mouseX, mouseY);
                if (setting instanceof Class254 booleanSetting) {
                    Class139 animation = this.booleanSettingHashMap.get(setting);
                    animation.setDirection(booleanSetting.isEnabled() ? Class141.FORWARDS : Class141.BACKWARDS);
                    if (type == Class99.CLICK && isHoveringSetting && button == 0) {
                        booleanSetting.toggle();
                    }

                    tenacityFont16.drawString(setting.name, this.x + 13.0F, settingY + tenacityFont16.getMiddleOfBox(8.0F), -1);
                    GL11.glEnable(3042);
                    Class148.drawRound(
                            this.x + 109.0F,
                            settingY,
                            18.0F,
                            8.0F,
                            4.0F,
                            Class460.interpolateColorC(new Color(30, 31, 35), colors.getFirst(), animation.getOutput().floatValue())
                    );
                    GlStateManager.color(1.0F, 1.0F, 1.0F);
                    Class147.drawGoodCircle(this.x + 113.0F + 10.0F * animation.getOutput().floatValue(), settingY + 4.0F, 4.0F, -1);
                    count -= 0.2F;
                }

                if (setting instanceof Class257 modeSetting) {
                    Class139 animation = this.modeSettingHashMap.get(setting);
                    if (type == Class99.CLICK && isHoveringSetting && button == 1) {
                        this.modeSettingClick.put(setting, !this.modeSettingClick.get(setting));
                    }

                    float stringWidth = tenacityFont16.getStringWidth(Class124.getLongestModeName(modeSetting.modes));
                    animation.setDirection(this.modeSettingClick.get(setting) ? Class141.FORWARDS : Class141.BACKWARDS);
                    float modeWidth = 15.0F + stringWidth;
                    float math = (float)((modeSetting.modes.size() - 1) * 15);
                    float boxX = this.x + 114.0F - stringWidth - 1.0F;
                    float nameWidth = tenacityFont16.getStringWidth(modeSetting.name);
                    float stringX = this.x + 13.0F;
                    if (nameWidth > boxX - stringX) {
                        tenacityFont16.drawWrappedText(
                                modeSetting.name, stringX, settingY - tenacityFont16.getMiddleOfBox(12.0F) / 2.0F, -1, boxX - stringX, 2.0F
                        );
                    } else {
                        tenacityFont16.drawString(modeSetting.name, this.x + 13.0F, settingY + 2.0F, -1);
                    }

                    Class148.drawRound(
                            boxX, settingY - 1.0F, modeWidth + 2.0F, 12.0F + math * animation.getOutput().floatValue(), 4.0F, new Color(68, 71, 78)
                    );
                    Class148.drawRound(
                            this.x + 114.0F - stringWidth, settingY, modeWidth, 10.0F + math * animation.getOutput().floatValue(), 3.0F, new Color(30, 31, 35)
                    );
                    float modeX = this.x + 114.0F - stringWidth + modeWidth / 2.0F;
                    Class147.resetColor();
                    tenacityFont16.drawCenteredString(modeSetting.getMode(), modeX, settingY + tenacityFont16.getMiddleOfBox(10.0F), -1);
                    int modeCount = 1;

                    for(String mode : modeSetting.modes) {
                        if (!mode.equalsIgnoreCase(modeSetting.getMode())) {
                            Class139 modeAnimation = (Class139)((HashMap)this.modesHoverAnimation.get(modeSetting)).get(mode);
                            boolean isHoveringMode = animation.getDirection().equals(Class141.FORWARDS)
                                    && Class147.isHovering(this.x + 115.0F - stringWidth, settingY + (float)(modeCount * 15), modeWidth, 11.0F, mouseX, mouseY);
                            if (type == Class99.CLICK && button == 0 && isHoveringMode) {
                                this.modeSettingClick.put(setting, !this.modeSettingClick.get(setting));
                                modeSetting.setCurrentMode(mode);
                            }

                            modeAnimation.setDirection(isHoveringMode ? Class141.FORWARDS : Class141.BACKWARDS);
                            modeAnimation.setDuration(isHoveringMode ? 200 : 350);
                            int colorInterpolate = Class460.interpolateColor(
                                    new Color(128, 134, 141), colors.getSecond(), modeAnimation.getOutput().floatValue()
                            );
                            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                            tenacityFont16.drawCenteredString(
                                    mode,
                                    modeX,
                                    settingY + 2.0F + (float)(modeCount * 15) * animation.getOutput().floatValue(),
                                    Class460.interpolateColor(new Color(40, 40, 40, 10), new Color(colorInterpolate), animation.getOutput().floatValue())
                            );
                            ++modeCount;
                        }
                    }

                    count += math / settingHeight * animation.getOutput().floatValue();
                }

                if (setting instanceof Class255 colorSetting) {
                    float height = (float)(colorSetting.isRainbow() ? 100 : 90);
                    Class148.drawRound(this.x + 12.0F, settingY - 1.0F, 117.0F, height, 4.0F, new Color(68, 71, 78));
                    Class148.drawRound(this.x + 13.0F, settingY, 115.0F, height - 2.0F, 3.0F, new Color(30, 31, 35));
                    tenacityFont16.drawCenteredString(colorSetting.name, this.x + 13.0F + 57.5F, settingY + 3.0F, -1);
                    if (colorSetting.isRainbow()) {
                        Color color = colorSetting.getColor();
                        int red = color.getRed();
                        int green = color.getGreen();
                        int blue = color.getBlue();
                        float[] hsb = Color.RGBtoHSB(red, green, blue, null);
                        colorSetting.setHue(hsb[0]);
                        colorSetting.setSaturation(hsb[1]);
                        colorSetting.setBrightness(hsb[2]);
                    }

                    float[] hsb = new float[]{(float)colorSetting.getHue(), (float)colorSetting.getSaturation(), (float)colorSetting.getBrightness()};
                    float gradientX = this.x + 17.0F;
                    float gradientY = settingY + 15.0F;
                    float gradientWidth = 97.0F;
                    float gradientHeight = 50.0F;
                    if (button == 0 && type == Class99.CLICK) {
                        if (Class147.isHovering(gradientX, gradientY + gradientHeight + 3.0F, gradientWidth + 10.0F, 6.0F, mouseX, mouseY)) {
                            this.draggingNumber = setting;
                            this.hueFlag = true;
                        }

                        if (Class147.isHovering(gradientX, gradientY, gradientWidth, gradientHeight, mouseX, mouseY)) {
                            this.draggingNumber = setting;
                            this.hueFlag = false;
                        }
                    }

                    if (this.draggingNumber != null && this.draggingNumber.equals(setting)) {
                        if (this.hueFlag) {
                            colorSetting.setHue(Math.min(1.0F, Math.max(0.0F, ((float)mouseX - gradientX) / (gradientWidth + 10.0F))));
                        } else {
                            colorSetting.setBrightness(Math.min(1.0F, Math.max(0.0F, 1.0F - ((float)mouseY - gradientY) / gradientHeight)));
                            colorSetting.setSaturation(Math.min(1.0F, Math.max(0.0F, ((float)mouseX - gradientX) / gradientWidth)));
                        }
                    }

                    Gui.drawRect2(
                            gradientX, gradientY, gradientWidth, gradientHeight, Color.getHSBColor(hsb[0], 1.0F, 1.0F).getRGB()
                    );
                    Gui.drawGradientRectSideways2(
                            gradientX,
                            gradientY,
                            gradientWidth,
                            gradientHeight,
                            Color.getHSBColor(hsb[0], 0.0F, 1.0F).getRGB(),
                            Class460.applyOpacity(Color.getHSBColor(hsb[0], 0.0F, 1.0F).getRGB(), 0.0F)
                    );
                    Gui.drawGradientRect2(
                            gradientX,
                            gradientY,
                            gradientWidth,
                            gradientHeight,
                            Class460.applyOpacity(Color.getHSBColor(hsb[0], 1.0F, 0.0F).getRGB(), 0.0F),
                            Color.getHSBColor(hsb[0], 1.0F, 0.0F).getRGB()
                    );
                    float pickerY = gradientY + gradientHeight * (1.0F - hsb[2]);
                    float pickerX = gradientX + (gradientWidth * hsb[1] - 1.0F);
                    pickerY = Math.max(Math.min(gradientY + gradientHeight - 2.0F, pickerY), gradientY);
                    pickerX = Math.max(Math.min(gradientX + gradientWidth - 2.0F, pickerX), gradientX);
                    GL11.glEnable(3042);
                    Class147.color(-1);
                    mc.getTextureManager().bindTexture(new ResourceLocation("bloodline/colorpicker.png"));
                    Gui.drawModalRectWithCustomSizedTexture(pickerX, pickerY, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, 4.0F);
                    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                    Gui.drawRect2(gradientX + gradientWidth + 5.0F, gradientY, 5.0, gradientHeight, colorSetting.getColor().getRGB());
                    Class147.color(-1);
                    mc.getTextureManager().bindTexture(new ResourceLocation("bloodline/hue.png"));
                    Gui.drawModalRectWithCustomSizedTexture(
                            gradientX, gradientY + gradientHeight + 4.5F, 0.0F, 0.0F, gradientWidth + 10.0F, 4.0F, gradientWidth + 10.0F, 4.0F
                    );
                    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                    Gui.drawRect2(gradientX + (gradientWidth + 10.0F) * hsb[0], (double)(gradientY + gradientHeight) + 3.5, 1.0, 6.0, -1);
                    tenacityFont14.drawString("Rainbow: ", gradientX, gradientY + gradientHeight + 14.0F, -1);
                    String text = colorSetting.isRainbow() ? "Enabled" : "Disabled";
                    float textX = gradientX + tenacityFont14.getStringWidth("Rainbow: ") + 4.0F;
                    float textY = gradientY + gradientHeight + 13.0F;
                    boolean hoveringRound = Class147.isHovering(
                            textX, textY, tenacityFont14.getStringWidth(text) + 4.0F, (float)(tenacityFont14.getHeight() + 2), mouseX, mouseY
                    );
                    if (type == Class99.CLICK && hoveringRound && button == 0) {
                        colorSetting.setRainbow(!colorSetting.isRainbow());
                    }

                    Color roundColor = colorSetting.isRainbow() ? new Color(0, 203, 33) : new Color(203, 0, 33);
                    Class148.drawRound(
                            textX,
                            textY,
                            tenacityFont14.getStringWidth(text) + 4.0F,
                            (float)(tenacityFont14.getHeight() + 2),
                            3.0F,
                            hoveringRound ? roundColor.brighter() : roundColor
                    );
                    tenacityFont14.drawCenteredString(
                            text,
                            textX + (tenacityFont14.getStringWidth(text) + 4.0F) / 2.0F,
                            textY + tenacityFont14.getMiddleOfBox((float)(tenacityFont14.getHeight() + 2)),
                            -1
                    );
                    if (colorSetting.isRainbow()) {
                        Gui.drawGradientRectSideways2(
                                gradientX, textY + 12.0F, gradientWidth + 10.0F, 4.0, Color.WHITE.getRGB(), Color.RED.getRGB()
                        );
                        Gui.drawRect2(gradientX + (gradientWidth + 10.0F) * hsb[1], textY + 11.0F, 1.0, 6.0, Color.BLACK.getRGB());
                        boolean hoveringSat = Class147.isHovering(gradientX, textY + 10.0F, gradientWidth + 10.0F, 6.0F, mouseX, mouseY);
                        if (type == Class99.CLICK && hoveringSat && button == 0) {
                            this.saturationFlag = true;
                            this.draggingNumber = setting;
                        }

                        if (type == Class99.RELEASE && this.saturationFlag) {
                            this.saturationFlag = false;
                        }

                        if (this.saturationFlag) {
                            colorSetting.getRainbow().setSaturation(Math.min(1.0F, Math.max(0.0F, ((float)mouseX - gradientX) / (gradientWidth + 10.0F))));
                        }
                    }

                    count = (float)((double)count + 3.5 + (double)(colorSetting.isRainbow() ? 0.5F : 0.0F));
                }

                if (setting instanceof Class259 numberSetting) {
                    tenacityFont16.drawString(setting.name, this.x + 13.0F, settingY + 2.0F, -1);
                    if (type == Class99.CLICK && isHoveringSetting && button == 0) {
                        this.draggingNumber = numberSetting;
                    }

                    double currentValue = numberSetting.getValue();
                    if (this.draggingNumber != null && this.draggingNumber == setting) {
                        float percent = Math.min(1.0F, Math.max(0.0F, ((float)mouseX - (this.x + 14.0F)) / 108.0F));
                        double newValue = (double)percent * (numberSetting.getMaxValue() - numberSetting.getMinValue()) + numberSetting.getMinValue();
                        numberSetting.setValue(newValue);
                    }

                    String value = Double.toString(Class146.round(currentValue, 2));
                    tenacityFont16.drawString(value, this.x + 120.0F - tenacityFont16.getStringWidth(value), settingY + 2.0F, -1);
                    float sliderMath = (float)((currentValue - numberSetting.getMinValue()) / (numberSetting.getMaxValue() - numberSetting.getMinValue()));
                    Class148.drawRound(this.x + 12.0F, settingY + 13.0F, 110.0F, 5.0F, 2.0F, new Color(30, 31, 35));
                    Color sliderColor = colors.getFirst();
                    float[] hsb = Color.RGBtoHSB(sliderColor.getRed(), sliderColor.getGreen(), sliderColor.getBlue(), null);
                    float saturation = hsb[1] * sliderMath;
                    float totalSliderWidth = 108.0F;
                    this.sliderMap
                            .put(
                                    numberSetting,
                                    (float) Class147.animate(
                                            totalSliderWidth * sliderMath, this.sliderMap.get(numberSetting).floatValue(), 0.1
                                    )
                            );
                    Class148.drawRound(
                            this.x + 13.0F,
                            settingY + 14.0F,
                            Math.max(4.0F, this.sliderMap.get(numberSetting)),
                            3.0F,
                            1.5F,
                            new Color(Color.HSBtoRGB(hsb[0], saturation, hsb[2]))
                    );
                    count = (float)((double)count + 0.5);
                }

                if (setting instanceof Class260 stringSetting) {
                    tenacityFont16.drawString(setting.name, this.x + 16.0F, settingY + 1.0F, -1);
                    Class113 textField = this.textFieldMap.get(stringSetting);
                    textField.setBackgroundText("Type here...");
                    textField.setXPosition(this.x + 17.0F);
                    textField.setYPosition(settingY + 12.0F);
                    textField.setWidth(100.0F);
                    textField.setHeight(15.0F);
                    textField.setOutline(new Color(68, 71, 78));
                    textField.setFill(new Color(30, 31, 35));
                    stringSetting.setString(textField.getText());
                    if (type == Class99.CLICK) {
                        textField.mouseClicked(mouseX, mouseY, button);
                    }

                    if (!this.typing) {
                        this.typing = textField.isFocused();
                    }

                    textField.drawTextBox();
                    count += 0.5F;
                }

                if (setting instanceof Class258 multiBoolSetting) {
                    Class139 animation = this.multiBoolSettingMap.get(setting);
                    if (type == Class99.CLICK && isHoveringSetting && button == 1) {
                        this.multiBoolSettingClickMap.put(setting, !this.multiBoolSettingClickMap.get(setting));
                    }

                    animation.setDirection(this.multiBoolSettingClickMap.get(setting) ? Class141.FORWARDS : Class141.BACKWARDS);
                    float math = (float)(multiBoolSetting.getBoolSettings().size() * 15);
                    float adjustment = math * animation.getOutput().floatValue();
                    Class147.renderRoundedRect(this.x + 11.5F, settingY - 4.5F, 118.0F, 19.0F + adjustment, 4.0F, new Color(68, 71, 78).getRGB());
                    Class147.renderRoundedRect(this.x + 13.0F, settingY - 3.0F, 115.0F, 16.0F + adjustment, 3.0F, new Color(30, 31, 35).getRGB());
                    tenacityFont16.drawCenteredString(
                            multiBoolSetting.name, this.x + 70.5F, settingY - 3.0F + 8.0F - (float)tenacityFont16.getHeight() / 2.0F, -1
                    );
                    int boolCount = 1;

                    for(Class254 booleanSetting : multiBoolSetting.getBoolSettings()) {
                        Class139 boolAnimation = (Class139)((HashMap)this.boolHoverAnimation.get(multiBoolSetting)).get(booleanSetting);
                        boolean isHoveringBool = animation.getDirection().equals(Class141.FORWARDS)
                                && Class147.isHovering(
                                this.x + 17.0F,
                                settingY + (float)(boolCount * 15),
                                tenacityFont16.getStringWidth(booleanSetting.name) + 13.0F,
                                11.0F,
                                mouseX,
                                mouseY
                        );
                        if (type == Class99.CLICK && button == 0 && isHoveringBool) {
                            multiBoolSetting.getSetting(booleanSetting.name).toggle();
                        }

                        boolAnimation.setDirection(isHoveringBool ? Class141.FORWARDS : Class141.BACKWARDS);
                        Color boolColor = booleanSetting.isEnabled() ? colors.getSecond() : new Color(128, 134, 141);
                        int colorInterpolate = Class460.interpolateColor(boolColor, boolColor.brighter(), boolAnimation.getOutput().floatValue());
                        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                        Class147.drawGoodCircle(
                                this.x + 20.0F,
                                settingY + 5.0F + (float)(boolCount * 15) * animation.getOutput().floatValue(),
                                2.0F,
                                Class460.interpolateColor(new Color(40, 40, 40, 10), new Color(colorInterpolate), animation.getOutput().floatValue())
                        );
                        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                        tenacityFont16.drawString(
                                booleanSetting.name,
                                this.x + 28.0F,
                                settingY + 2.0F + (float)(boolCount * 15) * animation.getOutput().floatValue(),
                                Class460.interpolateColor(new Color(40, 40, 40, 10), new Color(colorInterpolate), animation.getOutput().floatValue())
                        );
                        ++boolCount;
                    }

                    count += math / settingHeight * animation.getOutput().floatValue();
                }

                ++count;
            }
        }

        this.maxScroll = count * settingHeight;
    }
}
