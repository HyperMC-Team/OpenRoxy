package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.awt.Color;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StringUtils;

import static java.lang.Long.compare;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
public class Class279
extends Class252 {
    public final Class254 importantModules = new Class254("Important", false);
    private final Class257 textShadow = new Class257("Text Shadow", "Black", "Colored", "Black", "None");
    private final Class257 rectangle = new Class257("Rectangle", "Top", "None", "Top", "Side", "Outline");
    private final Class254 partialGlow = new Class254("Partial Glow", true);
    private final Class254 minecraftFont = new Class254("Minecraft Font", false);
    private final Class258 fontSettings = new Class258("Font Settings", new Class254("Bold", false), new Class254("Small Font", false), this.minecraftFont);
    public final Class259 height = new Class259("Height", 11.0, 20.0, 9.0, 0.5);
    private final Class257 animation = new Class257("Class125", "Scale in", "Move in", "Scale in");
    private final Class259 colorIndex = new Class259("Color Seperation", 20.0, 100.0, 5.0, 1.0);
    private final Class259 colorSpeed = new Class259("Color Class273", 15.0, 30.0, 2.0, 1.0);
    private final Class254 background = new Class254("Background", true);
    private final Class254 backgroundColor = new Class254("Background Color", false);
    private final Class254 Blur = new Class254("Blur", false);
    private final Class259 backgroundAlpha = new Class259("Background Alpha", 0.35, 1.0, 0.0, 0.01);
    public Class118 font = Class115.tenacityFont.size(20);
    public List<Class252> modules;
    public Class116 arraylistDrag = Class1.instance.Method2(this, "arraylist", 2.0f, 1.0f);
    public String longest = "";
    Class252 lastModule;
    int lastCount;
    public Class279() {
        super("ArrayList", Class263.Render, "Displays your active modules");
        this.backgroundAlpha.addParent(this.background, Class253.BOOLEAN_CONDITION);
        this.backgroundColor.addParent(this.background, Class253.BOOLEAN_CONDITION);
        this.partialGlow.addParent(this.rectangle, modeSetting -> !modeSetting.is("None"));
    }

    private String formatModule(Class252 module) {
        String name = module.getName();
        name = name.replaceAll(" ", "");
        String formatText = "%s %s%s";
        String suffix = module.getSuffix();
        if (suffix == null || suffix.isEmpty()) {
            return name;
        }
        return String.format(formatText, name, EnumChatFormatting.GRAY, suffix);
    }
    public List<Class252> getModules() {
        Stream<Class252> stream = Class1.instance.Method11().getModules().stream();

        stream = stream.sorted((mod1, mod2) -> compare(Class330.arial26.getStringWidth(formatModule(mod2)),
                Class330.arial26.getStringWidth(formatModule(mod1))));

        return stream.collect(Collectors.toList());
    }


    @Class14
    public void onShaderEvent(Class40 e) {
        if (this.modules == null) {
            return;
        }
        float yOffset = 0.0f;
        ScaledResolution sr = new ScaledResolution(mc);
        int count = 0;
        modules = getModules().stream().filter(Class252::isState).collect(Collectors.toList());
        for (Class252 module : this.modules) {
            if (this.importantModules.isEnabled() && module.getCategory() == Class263.Render) continue;
            Class139 moduleAnimation = module.getAnimation();
            if (!module.isState() && moduleAnimation.finished(Class141.BACKWARDS)) continue;
            Object displayText = module.getName() + (module.hasMode() ? " \u00a77" + module.getSuffix() : "");
            displayText = this.applyText((String)displayText);
            float textWidth = this.font.getStringWidth((String)displayText);
            float xValue = (float)sr.getScaledWidth() - this.arraylistDrag.getX();
            boolean flip = xValue <= (float)sr.getScaledWidth() / 2.0f;
            float x = flip ? xValue : (float)sr.getScaledWidth() - (textWidth + this.arraylistDrag.getX());
            float y = yOffset + this.arraylistDrag.getY();
            float heightVal = this.height.getValue().floatValue() + 1.0f;
            boolean scaleIn = false;
            switch (this.animation.getMode()) {
                case "Move in": {
                    if (flip) {
                        x -= Math.abs((moduleAnimation.getOutput().floatValue() - 1.0f) * ((float)sr.getScaledWidth() - (this.arraylistDrag.getX() + textWidth)));
                        break;
                    }
                    x += Math.abs((moduleAnimation.getOutput().floatValue() - 1.0f) * (this.arraylistDrag.getX() + textWidth));
                    break;
                }
                case "Scale in": {
                    if (!moduleAnimation.isDone()) {
                        Class147.scaleStart(x + this.font.getStringWidth((String)displayText) / 2.0f, y + heightVal / 2.0f - (float)this.font.getHeight() / 2.0f, moduleAnimation.getOutput().floatValue());
                    }
                    scaleIn = true;
                }
            }
            int index = (int)((double)count * this.colorIndex.getValue());
            Class123<Color, Color> colors = Class123.of(new Color(213, 63, 119), new Color(157, 68, 110));
            Color textcolor = Class460.interpolateColorsBackAndForth(this.colorSpeed.getValue().intValue(), index, Class318.getClientColors().getFirst(), Class318.getClientColors().getSecond(), false);
            if (this.background.isEnabled()) {
                int rectangleColor;
                float offset;
                float f = offset = this.minecraftFont.isEnabled() ? 4.0f : 5.0f;
                int rectColor = this.Blur.isEnabled() ? textcolor.getRGB() : (this.rectangle.getMode().equals("Outline") && this.partialGlow.isEnabled() ? textcolor.getRGB() : Color.BLACK.getRGB());
                Gui.drawRect2(x - 2.0f, y, this.font.getStringWidth((String)displayText) + offset, heightVal, scaleIn ? Class460.applyOpacity(rectColor, moduleAnimation.getOutput().floatValue()) : rectColor);
                float offset2 = this.minecraftFont.isEnabled() ? 1.0f : 0.0f;
                int n = rectangleColor = this.partialGlow.isEnabled() ? textcolor.getRGB() : Color.BLACK.getRGB();
                if (scaleIn) {
                    rectangleColor = Class460.applyOpacity(rectangleColor, moduleAnimation.getOutput().floatValue());
                }
                switch (this.rectangle.getMode()) {
                    default: {
                        break;
                    }
                    case "Top": {
                        if (count != 0) break;
                        Gui.drawRect2(x - 2.0f, y - 1.0f, textWidth + 5.0f - offset2, 9.0, rectangleColor);
                        break;
                    }
                    case "Side": {
                        if (flip) {
                            Gui.drawRect2(x - 3.0f, y, 9.0, heightVal, textcolor.getRGB());
                            break;
                        }
                        Gui.drawRect2(x + textWidth - 7.0f, y, 9.0, heightVal, rectangleColor);
                    }
                    case "Outline":
                }
            }
            if (this.animation.is("Scale in") && !moduleAnimation.isDone()) {
                Class147.scaleEnd();
            }
            yOffset += moduleAnimation.getOutput().floatValue() * heightVal;
            ++count;
        }
    }

    @Class14
    public void onRender2DEvent(Class38 e) {
        this.font = this.getFont();
        modules = getModules().stream().filter(Class252::isState).collect(Collectors.toList());
        Object longestModule = "";
        float longestWidth = 0.0f;
        double yOffset = 0.0;
        ScaledResolution sr = new ScaledResolution(mc);
        int count = 0;
        for (Class252 module : this.modules) {
            float offset;
            double xValue;
            if (this.importantModules.isEnabled() && module.getCategory() == Class263.Render) continue;
            Class139 moduleAnimation = module.getAnimation();
            moduleAnimation.setDirection(module.isState() ? Class141.FORWARDS : Class141.BACKWARDS);
            if (!module.isState() && moduleAnimation.finished(Class141.BACKWARDS)) continue;
            Object displayText = module.getName() + (module.hasMode() ? " \u00a77" + module.getSuffix() : "");
            float textWidth = this.font.getStringWidth((String)(displayText = this.applyText((String)displayText)));
            if (textWidth > longestWidth) {
                longestModule = displayText;
                longestWidth = textWidth;
            }
            boolean flip = (xValue = (float)sr.getScaledWidth() - this.arraylistDrag.getX()) <= (double)((float)sr.getScaledWidth() / 2.0f);
            float x = (float)(flip ? xValue : (double)((float)sr.getScaledWidth() - (textWidth + this.arraylistDrag.getX())));
            float alphaAnimation = 1.0f;
            float y = (float)(yOffset + (double)this.arraylistDrag.getY());
            float heightVal = (float)(this.height.getValue() + 1.0);
            switch (this.animation.getMode()) {
                case "Move in": {
                    if (flip) {
                        x -= Math.abs((moduleAnimation.getOutput().floatValue() - 1.0f) * ((float)sr.getScaledWidth() - (this.arraylistDrag.getX() - textWidth)));
                        break;
                    }
                    x += Math.abs((moduleAnimation.getOutput().floatValue() - 1.0f) * (this.arraylistDrag.getX() + textWidth));
                    break;
                }
                case "Scale in": {
                    if (!moduleAnimation.isDone()) {
                        Class147.scaleStart(x + this.font.getStringWidth((String)displayText) / 2.0f, y + heightVal / 2.0f - (float)this.font.getHeight() / 2.0f, moduleAnimation.getOutput().floatValue());
                    }
                    alphaAnimation = moduleAnimation.getOutput().floatValue();
                }
            }
            int index = (int)((double)count * this.colorIndex.getValue());
            Class123<Color, Color> colors = Class123.of(new Color(213, 63, 119), new Color(157, 68, 110));
            Color textcolor = Class460.interpolateColorsBackAndForth(this.colorSpeed.getValue().intValue(), index, Class318.getClientColors().getFirst(), Class318.getClientColors().getSecond(), false);
            if (this.background.isEnabled()) {
                offset = this.minecraftFont.isEnabled() ? 4.0f : 5.0f;
                Object color = this.backgroundColor.isEnabled() ? textcolor : new Color(10, 10, 10);
                Gui.drawRect2(x - 2.0f, y, this.font.getStringWidth((String)displayText) + offset, heightVal, Class460.applyOpacity((Color)color, this.backgroundAlpha.getValue().floatValue() * alphaAnimation).getRGB());
            }
            offset = this.minecraftFont.isEnabled() ? 1.0f : 0.0f;
            switch (this.rectangle.getMode()) {
                default: {
                    break;
                }
                case "Top": {
                    if (count != 0) break;
                    Gui.drawRect2(x - 2.0f, y - 1.0f, textWidth + 5.0f - offset, 1.0, textcolor.getRGB());
                    break;
                }
                case "Side": {
                    if (flip) {
                        Gui.drawRect2(x - 3.0f, y, 1.0, heightVal, textcolor.getRGB());
                        break;
                    }
                    Gui.drawRect2(x + textWidth + 1.0f, y, 1.0, heightVal, textcolor.getRGB());
                    break;
                }
                case "Outline": {
                    if (count != 0) {
                        String modText = this.applyText(this.lastModule.getName() + (this.lastModule.hasMode() ? " " + this.lastModule.getSuffix() : ""));
                        float texWidth = this.font.getStringWidth(modText) - textWidth;
                        if (flip) {
                            Gui.drawRect2(x + textWidth + 3.0f, y, 1.0, heightVal, textcolor.getRGB());
                            Gui.drawRect2(x + textWidth + 3.0f, y, texWidth + 1.0f, 1.0, textcolor.getRGB());
                        } else {
                            Gui.drawRect2(x - (3.0f + texWidth), y, texWidth + 1.0f, 1.0, textcolor.getRGB());
                            Gui.drawRect2(x - 3.0f, y, 1.0, heightVal, textcolor.getRGB());
                        }
                        if (count == this.lastCount - 1) {
                            Gui.drawRect2(x - 3.0f, y + heightVal, textWidth + 6.0f, 1.0, textcolor.getRGB());
                        }
                    } else {
                        if (flip) {
                            Gui.drawRect2(x + textWidth + 3.0f, y, 1.0, heightVal, textcolor.getRGB());
                        } else {
                            Gui.drawRect2(x - 3.0f, y, 1.0, heightVal, textcolor.getRGB());
                        }
                        Gui.drawRect2(x - 3.0f, y - 1.0f, textWidth + 6.0f, 1.0, textcolor.getRGB());
                    }
                    if (flip) {
                        Gui.drawRect2(x - 3.0f, y, 1.0, heightVal, textcolor.getRGB());
                        break;
                    }
                    Gui.drawRect2(x + textWidth + 1.0f, y, 1.0, heightVal, textcolor.getRGB());
                }
            }
            float textYOffset = this.minecraftFont.isEnabled() ? 0.5f : 0.0f;
            y += textYOffset;
            Color color = Class460.applyOpacity(textcolor, alphaAnimation);
            switch (this.textShadow.getMode()) {
                case "None": {
                    this.font.drawString((String)displayText, x, y + this.font.getMiddleOfBox(heightVal), color.getRGB());
                    break;
                }
                case "Colored": {
                    Class147.resetColor();
                    this.font.drawString(StringUtils.stripColorCodes((String)displayText), x + 1.0f, y + this.font.getMiddleOfBox(heightVal) + 1.0f, Class460.darker(color, 0.5f).getRGB());
                    Class147.resetColor();
                    this.font.drawString((String)displayText, x, y + this.font.getMiddleOfBox(heightVal), color.getRGB());
                    break;
                }
                case "Black": {
                    Class147.resetColor();
                    float f = this.minecraftFont.isEnabled() ? 1.0f : 0.5f;
                    this.font.drawString(StringUtils.stripColorCodes((String)displayText), x + f, y + this.font.getMiddleOfBox(heightVal) + f, Class460.applyOpacity(Color.BLACK, alphaAnimation));
                    Class147.resetColor();
                    this.font.drawString((String)displayText, x, y + this.font.getMiddleOfBox(heightVal), color.getRGB());
                }
            }
            if (this.animation.is("Scale in") && !moduleAnimation.isDone()) {
                Class147.scaleEnd();
            }
            this.lastModule = module;
            yOffset += moduleAnimation.getOutput().floatValue() * heightVal;
            ++count;
        }
        this.lastCount = count;
        this.longest = (String) longestModule;
    }

    private String applyText(String text) {
        if (this.minecraftFont.isEnabled() && this.fontSettings.getSetting("Bold").isEnabled()) {
            return "\u00a7l" + text.replace("\u00a77", "\u00a77\u00a7l");
        }
        return text;
    }

    private Class118 getFont() {
        boolean smallFont = this.fontSettings.getSetting("Small Font").isEnabled();
        if (this.fontSettings.getSetting("Bold").isEnabled()) {
            if (smallFont) {
                return Class115.tenacityBoldFont18;
            }
            return Class115.tenacityBoldFont20;
        }
        return smallFont ? Class115.tenacityFont18 : Class115.tenacityFont20;
    }
}

