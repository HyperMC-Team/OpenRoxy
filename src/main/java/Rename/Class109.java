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
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class Class109 implements Class107 {
    private final Class252 module;
    private final HashMap<Class259, Float> numberSettingMap = new HashMap();
    private final HashMap<Class260, Class113> textFieldMap = new HashMap();
    private final HashMap<Class255, Class139> colorSettingMap = new HashMap();
    private final HashMap<Class257, Class139> modeSettingMap = new HashMap();
    private final HashMap<Class258, Class139> multiBoolMap = new HashMap();
    public float size;
    public Color actualColor;
    public float x;
    public float y;
    public float rectWidth;
    public Class261 draggingNumber;
    private boolean hueFlag;
    private boolean saturationFlag;
    public boolean typing;

    public Class109(Class252 module) {
        this.module = module;

        for(Class261 setting : module.getSettingsList()) {
            if (setting instanceof Class259) {
                this.numberSettingMap.put((Class259)setting, 0.0F);
            }

            if (setting instanceof Class260 stringSetting) {
                Class113 textField = new Class113(tenacityFont14);
                textField.setText(stringSetting.getString());
                textField.setCursorPositionZero();
                this.textFieldMap.put(stringSetting, textField);
            }

            if (setting instanceof Class255 colorSetting) {
                Class139 animation = new Class140(250, 1.0).setDirection(Class141.BACKWARDS);
                this.colorSettingMap.put(colorSetting, animation);
            }

            if (setting instanceof Class257 modeSetting) {
                Class139 animation = new Class140(250, 1.0).setDirection(Class141.BACKWARDS);
                this.modeSettingMap.put(modeSetting, animation);
            }

            if (setting instanceof Class258 multipleBoolSetting) {
                Class139 animation = new Class140(250, 1.0).setDirection(Class141.BACKWARDS);
                this.multiBoolMap.put(multipleBoolSetting, animation);
            }
        }
    }

    @Override
    public void initGui() {
        for(Map.Entry<Class260, Class113> entry : this.textFieldMap.entrySet()) {
            entry.getValue().setText(entry.getKey().getString());
            entry.getValue().setCursorPositionZero();
        }
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        for(Map.Entry<Class260, Class113> entry : this.textFieldMap.entrySet()) {
            entry.getValue().keyTyped(typedChar, keyCode);
        }
    }

    public void handle(int mouseX, int mouseY, int button, Class99 type) {
        this.typing = false;
        float settingHeight = 16.0F;
        float count = 0.0F;
        Color accentColor = this.actualColor;
        Color disabledColor = new Color(64, 68, 75);

        for(Class261 setting : this.module.getSettingsList()) {
            if (!setting.cannotBeShown() && !(setting instanceof Class256)) {
                float settingY = this.y + count * settingHeight;
                float middleSettingY = (float) Class146.roundToHalf(this.y + tenacityFont16.getMiddleOfBox(settingHeight) + count * settingHeight);
                if (setting instanceof Class259 numberSetting) {
                    tenacityFont16.drawString(setting.name, this.x + 5.0F, middleSettingY, -1);
                    String value = String.valueOf(Class146.round(numberSetting.getValue(), 2));
                    value = value.contains(".") ? value.replaceAll("0*$", "").replaceAll("\\.$", "") : value;
                    String maxValue = Double.toString(Class146.round(numberSetting.getMaxValue(), 2));
                    float valueWidth = tenacityFont14.getStringWidth(maxValue);
                    Gui.drawRect2(
                            this.x + this.rectWidth - (valueWidth + 7.0F),
                            settingY + 4.0F,
                            valueWidth + 4.0F,
                            8.0,
                            disabledColor.getRGB()
                    );
                    tenacityFont14.drawCenteredString(value, this.x + this.rectWidth - (valueWidth + 5.0F) + valueWidth / 2.0F, settingY + 6.0F, -1);
                    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                    float sliderWidth = 50.0F;
                    float sliderHeight = 2.0F;
                    float sliderX = this.x + this.rectWidth - (valueWidth + 4.0F + 10.0F + sliderWidth);
                    float sliderY = settingY + settingHeight / 2.0F - sliderHeight / 2.0F;
                    boolean hoveringSlider = Class147.isHovering(sliderX, sliderY - 2.0F, sliderWidth, sliderHeight + 4.0F, mouseX, mouseY);
                    if (type == Class99.RELEASE) {
                        this.draggingNumber = null;
                    }

                    if (type == Class99.CLICK && hoveringSlider && button == 0) {
                        this.draggingNumber = numberSetting;
                    }

                    double currentValue = numberSetting.getValue();
                    if (this.draggingNumber != null && this.draggingNumber == setting) {
                        float percent = Math.min(1.0F, Math.max(0.0F, ((float)mouseX - sliderX) / sliderWidth));
                        double newValue = (double)percent * (numberSetting.getMaxValue() - numberSetting.getMinValue()) + numberSetting.getMinValue();
                        numberSetting.setValue(newValue);
                    }

                    float sliderMath = (float)((currentValue - numberSetting.getMinValue()) / (numberSetting.getMaxValue() - numberSetting.getMinValue()));
                    this.numberSettingMap
                            .put(
                                    numberSetting,
                                    (float) Class147.animate(
                                            sliderWidth * sliderMath, this.numberSettingMap.get(numberSetting).floatValue(), 0.1
                                    )
                            );
                    Gui.drawRect2(sliderX, sliderY, sliderWidth, sliderHeight, disabledColor.getRGB());
                    Gui.drawRect2(
                            sliderX,
                            sliderY,
                            Math.max(3.0F, this.numberSettingMap.get(numberSetting)),
                            sliderHeight,
                            accentColor.getRGB()
                    );
                    float whiteRectWidth = 1.5F;
                    float whiteRectHeight = 6.0F;
                    Class147.resetColor();
                    Gui.drawRect2(
                            sliderX + Math.max(3.0F, this.numberSettingMap.get(numberSetting)),
                            sliderY + sliderHeight / 2.0F - whiteRectHeight / 2.0F,
                            whiteRectWidth,
                            whiteRectHeight,
                            -1
                    );
                }

                if (setting instanceof Class254 booleanSetting) {
                    tenacityFont16.drawString(setting.name, this.x + 5.0F, middleSettingY, -1);
                    boolean enabled = booleanSetting.isEnabled();
                    float boolWH = 10.0F;
                    float boolX = this.x + this.rectWidth - (boolWH + 6.0F);
                    float boolY = settingY + settingHeight / 2.0F - boolWH / 2.0F;
                    boolean hoveringBool = Class147.isHovering(boolX - 2.0F, boolY - 2.0F, boolWH + 4.0F, boolWH + 4.0F, mouseX, mouseY);
                    if (type == Class99.CLICK && hoveringBool && button == 0) {
                        booleanSetting.toggle();
                    }

                    Color rectColor = enabled ? accentColor : disabledColor.brighter();
                    Gui.drawRect2(boolX, boolY, boolWH, boolWH, rectColor.getRGB());
                    Gui.drawRect2(boolX + 0.5F, boolY + 0.5F, boolWH - 1.0F, boolWH - 1.0F, disabledColor.getRGB());
                    if (booleanSetting.isEnabled()) {
                        iconFont16.drawCenteredString("o", boolX + boolWH / 2.0F, boolY + iconFont16.getMiddleOfBox(boolWH) + 0.5F, Color.WHITE);
                    }
                }

                if (setting instanceof Class255 colorSetting) {
                    Class139 clickAnimation = this.colorSettingMap.get(colorSetting);
                    tenacityFont16.drawString(setting.name, this.x + 5.0F, middleSettingY, -1);
                    float colorWidth = 20.0F;
                    boolean hovered = Class147.isHovering(
                            this.x + this.rectWidth - (colorWidth + 5.0F), middleSettingY - 1.0F, colorWidth, 6.0F, mouseX, mouseY
                    );
                    if (hovered && button == 1 && type == Class99.CLICK) {
                        clickAnimation.changeDirection();
                    }

                    Gui.drawRect2(
                            this.x + this.rectWidth - (colorWidth + 5.0F),
                            middleSettingY - 1.0F,
                            colorWidth,
                            6.0,
                            hovered ? Class460.darker(colorSetting.getColor(), 0.7F).getRGB() : colorSetting.getColor().getRGB()
                    );
                    if (clickAnimation.isDone() && clickAnimation.getDirection().equals(Class141.FORWARDS) || !clickAnimation.isDone()) {
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
                        float gradientX = this.x + 5.0F;
                        float gradientY = settingY + 20.0F;
                        float gradientWidth = 115.0F;
                        float gradientHeight = 10.0F + 35.0F * clickAnimation.getOutput().floatValue();
                        if (type == Class99.CLICK && button == 0) {
                            if (Class147.isHovering(gradientX + gradientWidth + 5.0F, gradientY, 5.0F, gradientHeight, mouseX, mouseY)) {
                                this.draggingNumber = setting;
                                this.hueFlag = true;
                            }

                            if (Class147.isHovering(gradientX, gradientY, gradientWidth, gradientHeight, mouseX, mouseY)) {
                                this.draggingNumber = setting;
                                this.hueFlag = false;
                            }
                        }

                        if (type == Class99.RELEASE) {
                            this.draggingNumber = null;
                        }

                        if (this.draggingNumber != null && this.draggingNumber.equals(setting)) {
                            if (this.hueFlag) {
                                colorSetting.setHue(Math.min(1.0F, Math.max(0.0F, ((float)mouseY - gradientY) / gradientHeight)));
                            } else {
                                colorSetting.setBrightness(Math.min(1.0F, Math.max(0.0F, 1.0F - ((float)mouseY - gradientY) / gradientHeight)));
                                colorSetting.setSaturation(Math.min(1.0F, Math.max(0.0F, ((float)mouseX - gradientX) / gradientWidth)));
                            }
                        }

                        int hsbZeroOneOne = Class460.applyOpacity(Color.getHSBColor(hsb[0], 1.0F, 1.0F).getRGB(), clickAnimation.getOutput().floatValue());
                        int hsbZeroZeroOne = Class460.applyOpacity(Color.getHSBColor(hsb[0], 0.0F, 1.0F).getRGB(), clickAnimation.getOutput().floatValue());
                        int hsbZeroOneZero = Class460.applyOpacity(Color.getHSBColor(hsb[0], 1.0F, 0.0F).getRGB(), clickAnimation.getOutput().floatValue());
                        Gui.drawRect2(gradientX, gradientY, gradientWidth, gradientHeight, hsbZeroOneOne);
                        Gui.drawGradientRectSideways2(
                                gradientX,
                                gradientY,
                                gradientWidth,
                                gradientHeight,
                                hsbZeroZeroOne,
                                Class460.applyOpacity(Color.getHSBColor(hsb[0], 0.0F, 1.0F).getRGB(), 0.0F)
                        );
                        Gui.drawGradientRect2(
                                gradientX,
                                gradientY,
                                gradientWidth,
                                gradientHeight,
                                Class460.applyOpacity(Color.getHSBColor(hsb[0], 1.0F, 0.0F).getRGB(), 0.0F),
                                hsbZeroOneZero
                        );
                        int rectColor = Class460.applyOpacity(disabledColor.getRGB(), clickAnimation.getOutput().floatValue());
                        int textColor = Class460.applyOpacity(-1, clickAnimation.getOutput().floatValue());
                        float colorInfoWidth = tenacityFont14.getStringWidth("R: 255") + 6.0F;
                        Gui.drawRect2(gradientX, gradientY + gradientHeight + 4.0F, colorInfoWidth, 8.0, rectColor);
                        Gui.drawRect2(
                                gradientX + colorInfoWidth + 5.0F, gradientY + gradientHeight + 4.0F, colorInfoWidth, 8.0, rectColor
                        );
                        Gui.drawRect2(
                                gradientX + colorInfoWidth * 2.0F + 10.0F,
                                gradientY + gradientHeight + 4.0F,
                                colorInfoWidth,
                                8.0,
                                rectColor
                        );
                        int redColor = new Color(255, 0, 0, (int)(255.0F * clickAnimation.getOutput().floatValue())).getRGB();
                        int greenColor = new Color(0, 255, 0, (int)(255.0F * clickAnimation.getOutput().floatValue())).getRGB();
                        int blueColor = new Color(0, 0, 255, (int)(255.0F * clickAnimation.getOutput().floatValue())).getRGB();
                        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                        tenacityFont14.drawCenteredString(
                                "R§f: " + colorSetting.getColor().getRed(), gradientX + 2.5F + colorWidth / 2.0F, gradientY + gradientHeight + 6.0F, redColor
                        );
                        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                        tenacityFont14.drawCenteredString(
                                "G§f: " + colorSetting.getColor().getGreen(),
                                gradientX + colorInfoWidth + 5.0F + colorInfoWidth / 2.0F,
                                gradientY + gradientHeight + 6.0F,
                                greenColor
                        );
                        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                        tenacityFont14.drawCenteredString(
                                "B§f: " + colorSetting.getColor().getBlue(),
                                gradientX + colorInfoWidth * 2.0F + 12.5F + colorWidth / 2.0F,
                                gradientY + gradientHeight + 6.0F,
                                blueColor
                        );
                        float rainbowX = gradientX + colorInfoWidth * 2.0F + 10.0F + colorInfoWidth + 3.0F;
                        tenacityFont14.drawString("Rainbow: ", rainbowX, gradientY + gradientHeight + 6.0F, textColor);
                        float clickAnim = clickAnimation.getOutput().floatValue();
                        Class148.drawRound(
                                rainbowX + tenacityFont14.getStringWidth("Rainbow: ") + 4.0F,
                                gradientY + gradientHeight + 5.5F,
                                6.0F,
                                6.0F,
                                2.5F,
                                Class460.applyOpacity(colorSetting.isRainbow() ? this.actualColor : disabledColor, clickAnim)
                        );
                        if (type == Class99.CLICK
                                && button == 0
                                && Class147.isHovering(
                                rainbowX + tenacityFont14.getStringWidth("Rainbow: ") + 3.0F, gradientY + gradientHeight + 5.0F, 7.0F, 7.0F, mouseX, mouseY
                        )) {
                            colorSetting.setRainbow(!colorSetting.isRainbow());
                        }

                        float pickerY = gradientY + gradientHeight * (1.0F - hsb[2]);
                        float pickerX = gradientX + (gradientWidth * hsb[1] - 1.0F);
                        pickerY = Math.max(Math.min(gradientY + gradientHeight - 2.0F, pickerY), gradientY);
                        pickerX = Math.max(Math.min(gradientX + gradientWidth - 2.0F, pickerX), gradientX);
                        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                        GL11.glEnable(3042);
                        Class147.color(textColor);
                        mc.getTextureManager().bindTexture(new ResourceLocation("bloodline/colorpicker.png"));
                        Gui.drawModalRectWithCustomSizedTexture(pickerX, pickerY, 0.0F, 0.0F, 4.0F, 4.0F, 4.0F, 4.0F);
                        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                        Class147.color(textColor);
                        mc.getTextureManager().bindTexture(new ResourceLocation("bloodline/hue2.png"));
                        Gui.drawModalRectWithCustomSizedTexture(
                                gradientX + gradientWidth + 5.0F, gradientY, 0.0F, 0.0F, 5.0F, gradientHeight, 5.0F, gradientHeight
                        );
                        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                        Gui.drawRect2(gradientX + gradientWidth + 5.0F, gradientY + gradientHeight * hsb[0], 5.0, 1.0, textColor);
                        if (colorSetting.isRainbow()) {
                            Gui.drawGradientRect2(
                                    gradientX + gradientWidth + 15.0F, gradientY, 5.0, gradientHeight, textColor, Color.RED.getRGB()
                            );
                            Gui.drawRect2(
                                    gradientX + gradientWidth + 15.0F, gradientY + gradientHeight * hsb[1], 5.0, 1.0, Color.BLACK.getRGB()
                            );
                            boolean hoveringSat = Class147.isHovering(gradientX + gradientWidth + 15.0F, gradientY, 5.0F, gradientHeight, mouseX, mouseY);
                            if (type == Class99.CLICK && button == 0 && hoveringSat) {
                                this.saturationFlag = true;
                                this.draggingNumber = setting;
                            }

                            if (type == Class99.RELEASE && this.saturationFlag) {
                                this.saturationFlag = false;
                                this.draggingNumber = null;
                            }

                            if (this.saturationFlag) {
                                colorSetting.getRainbow().setSaturation(Math.min(1.0F, Math.max(0.0F, ((float)mouseY - gradientY) / gradientHeight)));
                            }
                        }
                    }

                    count += 4.0F * clickAnimation.getOutput().floatValue();
                }

                if (setting instanceof Class260 stringSetting) {
                    Class147.resetColor();
                    tenacityFont16.drawString(setting.name, this.x + 5.0F, middleSettingY, -1);
                    Class113 textField = this.textFieldMap.get(stringSetting);
                    float textFieldWidth = 60.0F;
                    float textFieldHeight = 10.0F;
                    textField.setBackgroundText("Type Here...");
                    textField.setXPosition(this.x + this.rectWidth - (textFieldWidth + 5.0F));
                    textField.setYPosition(settingY + settingHeight / 2.0F - textFieldHeight / 2.0F);
                    textField.setWidth(textFieldWidth);
                    textField.setHeight(textFieldHeight);
                    textField.setDrawingBackground(false);
                    if (type == Class99.CLICK) {
                        textField.mouseClicked(mouseX, mouseY, button);
                    }

                    textField.drawTextBox();
                    this.typing = textField.isFocused();
                    stringSetting.setString(textField.getText());
                }

                if (setting instanceof Class257 modeSetting) {
                    tenacityFont16.drawString(setting.name, this.x + 5.0F, middleSettingY, -1);
                    float modeRectWidth = tenacityFont14.getStringWidth(Class124.getLongestModeName(modeSetting.modes)) + 10.0F;
                    float modeSize = 10.0F;
                    float realY = settingY + settingHeight / 2.0F - modeSize / 2.0F;
                    boolean hovered = Class147.isHovering(this.x + this.rectWidth - (modeRectWidth + 5.0F), realY, modeRectWidth, modeSize, mouseX, mouseY);
                    Class139 openAnimation = this.modeSettingMap.get(modeSetting);
                    if (!openAnimation.isDone() || openAnimation.getDirection().equals(Class141.FORWARDS)) {
                        Color dropdownColor = Class460.darker(disabledColor, 0.8F);
                        Gui.drawRect2(
                                this.x + this.rectWidth - (modeRectWidth + 5.0F),
                                realY + modeSize,
                                modeRectWidth,
                                (float)(modeSetting.modes.size() - 1) * modeSize * openAnimation.getOutput().floatValue(),
                                dropdownColor.getRGB()
                        );
                        float seperation = 0.0F;

                        for(String mode : modeSetting.modes) {
                            if (!mode.equals(modeSetting.getMode())) {
                                float modeY = realY
                                        + 3.5F
                                        + 6.0F * openAnimation.getOutput().floatValue()
                                        + tenacityFont14.getMiddleOfBox(modeSize)
                                        + seperation;
                                boolean hoveringMode = Class147.isHovering(
                                        this.x + this.rectWidth - (modeRectWidth + 5.0F),
                                        modeY - tenacityFont14.getMiddleOfBox(modeSize),
                                        modeRectWidth,
                                        modeSize,
                                        mouseX,
                                        mouseY
                                )
                                        && openAnimation.isDone();
                                if (hoveringMode && button == 0 && type == Class99.CLICK) {
                                    modeSetting.setCurrentMode(mode);
                                    openAnimation.setDirection(Class141.BACKWARDS);
                                    return;
                                }

                                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                                Gui.drawRect2(
                                        this.x + this.rectWidth - (modeRectWidth + 5.0F),
                                        modeY - tenacityFont14.getMiddleOfBox(modeSize),
                                        modeRectWidth,
                                        modeSize,
                                        Class460.applyOpacity(hoveringMode ? accentColor : dropdownColor, openAnimation.getOutput().floatValue()).getRGB()
                                );
                                GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
                                tenacityFont14.drawString(
                                        mode,
                                        this.x + this.rectWidth - (modeRectWidth + 3.0F),
                                        modeY,
                                        Class460.applyOpacity(-1, openAnimation.getOutput().floatValue())
                                );
                                seperation += modeSize * openAnimation.getOutput().floatValue();
                            }
                        }
                    }

                    Gui.drawRect2(
                            this.x + this.rectWidth - (modeRectWidth + 5.0F),
                            realY,
                            modeRectWidth,
                            modeSize,
                            disabledColor.getRGB()
                    );
                    tenacityFont14.drawString(
                            modeSetting.getMode(), this.x + this.rectWidth - (modeRectWidth + 5.0F) + 2.0F, realY + tenacityFont14.getMiddleOfBox(modeSize), -1
                    );
                    if (hovered && button == 1 && type == Class99.CLICK) {
                        openAnimation.changeDirection();
                    }

                    Class147.drawClickGuiArrow(
                            this.x + this.rectWidth - 11.0F, realY + modeSize / 2.0F - 1.0F, 4.0F, openAnimation, -1
                    );
                    count += (2.0F + (float)(modeSetting.modes.size() - 1) * modeSize) / settingHeight * openAnimation.getOutput().floatValue();
                }

                if (setting instanceof Class258 multipleBoolSetting) {
                    Class139 openingAnimation = this.multiBoolMap.get(multipleBoolSetting);
                    tenacityFont16.drawString(setting.name, this.x + 5.0F, middleSettingY, -1);
                    float width = 65.0F;
                    float boolRectSize = 10.0F;
                    float realY = settingY + settingHeight / 2.0F - boolRectSize / 2.0F;
                    Collection<Class254> booleanSettings = multipleBoolSetting.getBoolSettings();
                    int settingsCount = booleanSettings.size();
                    int enabledCount = (int)booleanSettings.stream().filter(Class254::isEnabled).count();
                    if (!openingAnimation.isDone() || openingAnimation.getDirection().equals(Class141.FORWARDS)) {
                        Color dropdownColor = Class460.darker(disabledColor, 0.9F);
                        Gui.drawRect2(
                                this.x + this.rectWidth - (width + 5.0F),
                                realY + boolRectSize,
                                width,
                                (1.0F + (float)settingsCount * boolRectSize) * openingAnimation.getOutput().floatValue(),
                                dropdownColor.getRGB()
                        );
                        float seperation = 0.0F;

                        for(Class254 booleanSetting : booleanSettings) {
                            boolean enabled = booleanSetting.isEnabled();
                            float boolSettingY = realY
                                    + 4.0F
                                    + 7.0F * openingAnimation.getOutput().floatValue()
                                    + tenacityFont14.getMiddleOfBox(boolRectSize)
                                    + seperation;
                            boolean hovered = Class147.isHovering(
                                    this.x + this.rectWidth - (width + 5.0F),
                                    boolSettingY - tenacityFont14.getMiddleOfBox(boolRectSize),
                                    width,
                                    boolRectSize,
                                    mouseX,
                                    mouseY
                            );
                            if (hovered && button == 0 && type == Class99.CLICK) {
                                booleanSetting.toggle();
                            }

                            Color toggleColor = enabled ? accentColor : dropdownColor;
                            int hoverColor = hovered ? Class460.darker(toggleColor, 0.8F).getRGB() : toggleColor.getRGB();
                            Class147.resetColor();
                            Gui.drawRect2(
                                    this.x + this.rectWidth - (width + 5.0F),
                                    boolSettingY - tenacityFont14.getMiddleOfBox(boolRectSize),
                                    width,
                                    boolRectSize,
                                    Class460.applyOpacity(hoverColor, openingAnimation.getOutput().floatValue())
                            );
                            Class147.resetColor();
                            tenacityFont14.drawString(
                                    booleanSetting.name,
                                    this.x + this.rectWidth - (width + 2.0F),
                                    boolSettingY,
                                    Class460.applyOpacity(-1, openingAnimation.getOutput().floatValue())
                            );
                            seperation += boolRectSize * openingAnimation.getOutput().floatValue();
                        }
                    }

                    Gui.drawRect2(
                            this.x + this.rectWidth - (width + 5.0F), realY, width, boolRectSize, disabledColor.getRGB()
                    );
                    tenacityFont14.drawCenteredString(
                            enabledCount + "/" + settingsCount + " enabled",
                            this.x + this.rectWidth - (width + 5.0F) + width / 2.0F,
                            realY + tenacityFont14.getMiddleOfBox(boolRectSize),
                            -1
                    );
                    boolean hovering = Class147.isHovering(this.x + this.rectWidth - (width + 5.0F), realY, width, boolRectSize, mouseX, mouseY);
                    if (hovering && button == 1 && type == Class99.CLICK) {
                        openingAnimation.changeDirection();
                    }

                    Class147.drawClickGuiArrow(
                            this.x + this.rectWidth - 11.0F, realY + boolRectSize / 2.0F - 1.0F, 4.0F, openingAnimation, -1
                    );
                    count += (2.0F + (float)multipleBoolSetting.getBoolSettings().size() * boolRectSize)
                            / settingHeight
                            * openingAnimation.getOutput().floatValue();
                }

                ++count;
            }
        }

        this.size = count * settingHeight;
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
}
