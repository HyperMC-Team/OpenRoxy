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

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.MathHelper;
import org.lwjgl.input.Keyboard;

public class Class113
        extends Gui {
    public Class119 font;
    private float xPosition;
    private float yPosition;
    private float radius = 2.0f;
    private float alpha = 1.0f;
    private float width;
    private float height;
    private float textAlpha = 1.0f;
    private Color outline = Color.WHITE;
    private Color fill = Class460.tripleColor(32);
    private Color focusedTextColor = new Color(224, 224, 224);
    private Color unfocusedTextColor = new Color(130, 130, 130);
    private String text = "";
    private String backgroundText;
    private int maxStringLength = 32;
    private boolean drawingBackground = true;
    private boolean canLoseFocus = true;
    private boolean isFocused;
    private int lineScrollOffset;
    private int cursorPosition;
    private int selectionEnd;
    private final Class139 textColor = new Class140(250, 1.0);
    private final Class139 cursorBlinkAnimation = new Class140(750, 1.0);
    private final Class150 timerUtil = new Class150();
    private boolean visible = true;

    public Class113(Class119 font) {
        this.font = font;
    }

    public Class113(Class119 font, float x, float y, float par5Width, float par6Height) {
        this.font = font;
        this.xPosition = x;
        this.yPosition = y;
        this.width = par5Width;
        this.height = par6Height;
    }

    public void setText(String text) {
        this.text = text.length() > this.maxStringLength ? text.substring(0, this.maxStringLength) : text;
        this.setCursorPositionZero();
    }

    public String getText() {
        return this.text;
    }

    public String getSelectedText() {
        int i = Math.min(this.cursorPosition, this.selectionEnd);
        int j = Math.max(this.cursorPosition, this.selectionEnd);
        return this.text.substring(i, j);
    }

    public void writeText(String text) {
        int l;
        String  s = "";
        String s1 = ChatAllowedCharacters.filterAllowedCharacters(text);
        int min = Math.min(this.cursorPosition, this.selectionEnd);
        int max = Math.max(this.cursorPosition, this.selectionEnd);
        int len = this.maxStringLength - this.text.length() - (min - max);
        if (this.text.length() > 0) {
            s = s + this.text.substring(0, min);
        }
        if (len < s1.length()) {
            s = s + s1.substring(0, len);
            l = len;
        } else {
            s = s + s1;
            l = s1.length();
        }
        if (this.text.length() > 0 && max < this.text.length()) {
            s = s + this.text.substring(max);
        }
        this.text = s;
        this.moveCursorBy(min - this.selectionEnd + l);
    }

    public void deleteWords(int num) {
        if (this.text.length() != 0) {
            if (this.selectionEnd != this.cursorPosition) {
                this.writeText("");
            } else {
                this.deleteFromCursor(this.getNthWordFromCursor(num) - this.cursorPosition);
            }
        }
    }

    public void deleteFromCursor(int num) {
        if (this.text.length() != 0) {
            if (this.selectionEnd != this.cursorPosition) {
                this.writeText("");
            } else {
                boolean negative = num < 0;
                int i = negative ? this.cursorPosition + num : this.cursorPosition;
                int j = negative ? this.cursorPosition : this.cursorPosition + num;
                String s = "";
                if (i >= 0) {
                    s = this.text.substring(0, i);
                }
                if (j < this.text.length()) {
                    s = s + this.text.substring(j);
                }
                this.text = s;
                if (negative) {
                    this.moveCursorBy(num);
                }
            }
        }
    }

    public int getNthWordFromCursor(int n) {
        return this.getNthWordFromPos(n, this.getCursorPosition());
    }

    public int getNthWordFromPos(int n, int pos) {
        return this.func_146197_a(n, pos);
    }

    public int func_146197_a(int n, int pos) {
        int i = pos;
        boolean negative = n < 0;
        int j = Math.abs(n);
        for (int k = 0; k < j; ++k) {
            if (!negative) {
                int l = this.text.length();
                if ((i = this.text.indexOf(32, i)) == -1) {
                    i = l;
                    continue;
                }
                while (i < l && this.text.charAt(i) == ' ') {
                    ++i;
                }
                continue;
            }
            while (i > 0 && this.text.charAt(i - 1) == ' ') {
                --i;
            }
            while (i > 0 && this.text.charAt(i - 1) != ' ') {
                --i;
            }
        }
        return i;
    }

    public void moveCursorBy(int p_146182_1_) {
        this.setCursorPosition(this.selectionEnd + p_146182_1_);
    }

    public void setCursorPosition(int p_146190_1_) {
        this.cursorPosition = p_146190_1_;
        int i = this.text.length();
        this.cursorPosition = MathHelper.clamp_int(this.cursorPosition, 0, i);
        this.setSelectionPos(this.cursorPosition);
    }

    public void setCursorPositionZero() {
        this.setCursorPosition(0);
    }

    public void setCursorPositionEnd() {
        this.setCursorPosition(this.text.length());
    }

    public boolean keyTyped(char cha, int keyCode) {
        if (!this.isFocused) {
            return false;
        }
        this.timerUtil.reset();
        if (GuiScreen.isKeyComboCtrlA(keyCode)) {
            this.setCursorPositionEnd();
            this.setSelectionPos(0);
            return true;
        }
        if (GuiScreen.isKeyComboCtrlC(keyCode)) {
            GuiScreen.setClipboardString(this.getSelectedText());
            return true;
        }
        if (GuiScreen.isKeyComboCtrlV(keyCode)) {
            this.writeText(GuiScreen.getClipboardString());
            return true;
        }
        if (GuiScreen.isKeyComboCtrlX(keyCode)) {
            GuiScreen.setClipboardString(this.getSelectedText());
            this.writeText("");
            return true;
        }
        switch (keyCode) {
            case 14: {
                if (GuiScreen.isCtrlKeyDown()) {
                    this.deleteWords(-1);
                } else {
                    this.deleteFromCursor(-1);
                }
                return true;
            }
            case 199: {
                if (GuiScreen.isShiftKeyDown()) {
                    this.setSelectionPos(0);
                } else {
                    this.setCursorPositionZero();
                }
                return true;
            }
            case 203: {
                if (GuiScreen.isShiftKeyDown()) {
                    if (GuiScreen.isCtrlKeyDown()) {
                        this.setSelectionPos(this.getNthWordFromPos(-1, this.getSelectionEnd()));
                    } else {
                        this.setSelectionPos(this.getSelectionEnd() - 1);
                    }
                } else if (GuiScreen.isCtrlKeyDown()) {
                    this.setCursorPosition(this.getNthWordFromCursor(-1));
                } else {
                    this.moveCursorBy(-1);
                }
                return true;
            }
            case 205: {
                if (GuiScreen.isShiftKeyDown()) {
                    if (GuiScreen.isCtrlKeyDown()) {
                        this.setSelectionPos(this.getNthWordFromPos(1, this.getSelectionEnd()));
                    } else {
                        this.setSelectionPos(this.getSelectionEnd() + 1);
                    }
                } else if (GuiScreen.isCtrlKeyDown()) {
                    this.setCursorPosition(this.getNthWordFromCursor(1));
                } else {
                    this.moveCursorBy(1);
                }
                return true;
            }
            case 207: {
                if (GuiScreen.isShiftKeyDown()) {
                    this.setSelectionPos(this.text.length());
                } else {
                    this.setCursorPositionEnd();
                }
                return true;
            }
            case 211: {
                if (GuiScreen.isCtrlKeyDown()) {
                    this.deleteWords(1);
                } else {
                    this.deleteFromCursor(1);
                }
                return true;
            }
        }
        if (ChatAllowedCharacters.isAllowedCharacter(cha)) {
            this.writeText(Character.toString(cha));
            return true;
        }
        return false;
    }

    public void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        boolean flag = Class465.isHovering(this.xPosition, this.yPosition, this.width, this.height, mouseX, mouseY);
        if (this.canLoseFocus) {
            this.setFocused(flag);
        }
        if (this.isFocused && flag && mouseButton == 0) {
            float xPos = this.xPosition;
            if (this.backgroundText != null && this.backgroundText.equals("Search")) {
                xPos += 13.0f;
            }
            float i = (float)mouseX - xPos;
            String s = this.font.trimStringToWidth(this.text.substring(this.lineScrollOffset), (int)this.getWidth());
            this.setCursorPosition(this.font.trimStringToWidth(s, (int)i).length() + this.lineScrollOffset);
        }
    }

    public void drawTextBox() {
        if (this.getVisible()) {
            boolean cursorBlink;
            if (this.isFocused()) {
                Keyboard.enableRepeatEvents(true);
            }
            Color textColorWithAlpha = this.focusedTextColor;
            if (this.textAlpha != 1.0f) {
                textColorWithAlpha = Class460.applyOpacity(this.focusedTextColor, this.textAlpha);
            }
            float xPos = this.xPosition + 3.0f;
            float yPos = this.yPosition + this.font.getMiddleOfBox(this.height);
            if (this.isDrawingBackground()) {
                if (this.outline != null) {
                    Class148.drawRound(this.xPosition - 1.0f, this.yPosition - 1.0f, this.width + 2.0f, this.height + 2.0f, this.radius + 1.0f, this.outline);
                }
                Class148.drawRound(this.xPosition, this.yPosition, this.width, this.height, this.radius, Class460.applyOpacity(this.fill, this.alpha));
            } else {
                float rectHeight = 1.0f;
                Gui.drawRect2(this.xPosition, this.yPosition + this.height - rectHeight, this.width, rectHeight, Class460.interpolateColor(this.focusedTextColor, this.unfocusedTextColor, this.textColor.getOutput().floatValue()));
            }
            this.textColor.setDirection(this.isFocused() ? Class141.BACKWARDS : Class141.FORWARDS);
            if (this.backgroundText != null) {
                Color backgroundTextColor = Class460.applyOpacity(Class460.applyOpacity(this.unfocusedTextColor, this.textAlpha), this.textColor.getOutput().floatValue());
                if (this.backgroundText.equals("Search")) {
                    Class120.iconFont16.drawString("B", xPos + 1.5f, this.yPosition + Class120.iconFont16.getMiddleOfBox(this.getHeight()), Class460.applyOpacity(this.unfocusedTextColor, this.textAlpha));
                    xPos += 15.0f;
                }
                if (this.text.equals("") && !this.textColor.finished(Class141.BACKWARDS)) {
                    this.font.drawString(this.backgroundText, xPos, yPos, backgroundTextColor);
                }
            }
            int cursorPos = this.cursorPosition - this.lineScrollOffset;
            int selEnd = this.selectionEnd - this.lineScrollOffset;
            String text = this.font.trimStringToWidth(this.text.substring(this.lineScrollOffset), (int)this.getWidth());
            boolean cursorInBounds = cursorPos >= 0 && cursorPos <= text.length();
            boolean canShowCursor = this.isFocused && cursorInBounds;
            float j1 = xPos;
            if (selEnd > text.length()) {
                selEnd = text.length();
            }
            if (text.length() > 0) {
                String s1 = cursorInBounds ? text.substring(0, cursorPos) : text;
                j1 = (float)this.font.drawStringWithShadow(s1, xPos, yPos, textColorWithAlpha.getRGB()) + 0.5f;
            }
            boolean cursorEndPos = this.cursorPosition < this.text.length() || this.text.length() >= this.getMaxStringLength();
            float k1 = j1;
            if (!cursorInBounds) {
                k1 = cursorPos > 0 ? xPos + this.width : xPos;
            } else if (cursorEndPos) {
                k1 = j1;
                j1 -= 1.0f;
            }
            if (text.length() > 0 && cursorInBounds && cursorPos < text.length()) {
                j1 = this.font.drawStringWithShadow(text.substring(cursorPos), j1 + 2.0f, yPos, textColorWithAlpha.getRGB());
            }
            boolean bl = cursorBlink = this.timerUtil.hasTimeElapsed(2000L) || cursorEndPos;
            if (canShowCursor) {
                if (cursorBlink) {
                    if (this.cursorBlinkAnimation.isDone()) {
                        this.cursorBlinkAnimation.changeDirection();
                    }
                } else {
                    this.cursorBlinkAnimation.setDirection(Class141.FORWARDS);
                }
                Gui.drawRect2(k1 + 1.0f, yPos - 2.0f, 0.5, this.font.getHeight() + 3, Class460.applyOpacity(textColorWithAlpha, this.cursorBlinkAnimation.getOutput().floatValue()).getRGB());
            }
            if (selEnd != cursorPos) {
                int l1 = (int)(xPos + this.font.getStringWidth(text.substring(0, selEnd)));
                int offset = selEnd > cursorPos ? 2 : 0;
                float widthOffset = selEnd > cursorPos ? 0.5f : 0.0f;
                this.drawSelectionBox(k1 + (float)offset, yPos - 1.0f, (float)l1 + widthOffset, yPos + 1.0f + (float)this.font.getHeight());
            }
        }
    }

    private void drawSelectionBox(float x, float y, float width, float height) {
        if (x < width) {
            float i = x;
            x = width;
            width = i;
        }
        if (y < height) {
            float j = y;
            y = height;
            height = j;
        }
        if (width > this.xPosition + this.width) {
            width = this.xPosition + this.width;
        }
        if (x > this.xPosition + this.width) {
            x = this.xPosition + this.width;
        }
        Tessellator tessellator = Tessellator.getInstance();
        WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        GlStateManager.color(0.0f, 0.0f, 255.0f, 255.0f);
        GlStateManager.disableTexture2D();
        GlStateManager.enableColorLogic();
        GlStateManager.colorLogicOp(5387);
        worldrenderer.begin(7, DefaultVertexFormats.POSITION);
        worldrenderer.pos(x, height, 0.0).endVertex();
        worldrenderer.pos(width, height, 0.0).endVertex();
        worldrenderer.pos(width, y, 0.0).endVertex();
        worldrenderer.pos(x, y, 0.0).endVertex();
        tessellator.draw();
        GlStateManager.disableColorLogic();
        GlStateManager.enableTexture2D();
    }

    public void setMaxStringLength(int len) {
        this.maxStringLength = len;
        if (this.text.length() > len) {
            this.text = this.text.substring(0, len);
        }
    }

    public int getMaxStringLength() {
        return this.maxStringLength;
    }

    public int getCursorPosition() {
        return this.cursorPosition;
    }

    public void setTextColor(Color color) {
        this.focusedTextColor = color;
    }

    public void setDisabledTextColour(Color color) {
        this.unfocusedTextColor = color;
    }

    public int getSelectionEnd() {
        return this.selectionEnd;
    }

    public float getWidth() {
        boolean flag;
        boolean bl = flag = this.backgroundText != null && this.backgroundText.equals("Search");
        return this.isDrawingBackground() ? this.width - (float)(flag ? 17 : 4) : this.width;
    }

    public float getRealWidth() {
        return this.isDrawingBackground() ? this.width - 4.0f : this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public void setSelectionPos(int selectionPos) {
        int i = this.text.length();
        if (selectionPos > i) {
            selectionPos = i;
        }
        if (selectionPos < 0) {
            selectionPos = 0;
        }
        this.selectionEnd = selectionPos;
        if (this.font != null) {
            if (this.lineScrollOffset > i) {
                this.lineScrollOffset = i;
            }
            float j = this.getWidth();
            String s = this.font.trimStringToWidth(this.text.substring(this.lineScrollOffset), (int)j);
            int k = s.length() + this.lineScrollOffset;
            if (selectionPos == this.lineScrollOffset) {
                this.lineScrollOffset -= this.font.trimStringToWidth(this.text, (int)j, true).length();
            }
            if (selectionPos > k) {
                this.lineScrollOffset += selectionPos - k;
            } else if (selectionPos <= this.lineScrollOffset) {
                this.lineScrollOffset -= this.lineScrollOffset - selectionPos;
            }
            this.lineScrollOffset = MathHelper.clamp_int(this.lineScrollOffset, 0, i);
        }
    }

    public void setCanLoseFocus(boolean canLoseFocus) {
        this.canLoseFocus = canLoseFocus;
    }

    public boolean getVisible() {
        return this.visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setFont(Class119 font) {
        this.font = font;
    }

    public float getXPosition() {
        return this.xPosition;
    }

    public float getYPosition() {
        return this.yPosition;
    }

    public float getRadius() {
        return this.radius;
    }

    public float getAlpha() {
        return this.alpha;
    }

    public void setXPosition(float xPosition) {
        this.xPosition = xPosition;
    }

    public void setYPosition(float yPosition) {
        this.yPosition = yPosition;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setTextAlpha(float textAlpha) {
        this.textAlpha = textAlpha;
    }

    public void setOutline(Color outline) {
        this.outline = outline;
    }

    public void setFill(Color fill) {
        this.fill = fill;
    }

    public Color getOutline() {
        return this.outline;
    }

    public Color getFill() {
        return this.fill;
    }

    public void setBackgroundText(String backgroundText) {
        this.backgroundText = backgroundText;
    }

    public void setDrawingBackground(boolean drawingBackground) {
        this.drawingBackground = drawingBackground;
    }

    public boolean isDrawingBackground() {
        return this.drawingBackground;
    }

    public void setFocused(boolean isFocused) {
        this.isFocused = isFocused;
    }

    public boolean isFocused() {
        return this.isFocused;
    }
}
