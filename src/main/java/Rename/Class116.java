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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.awt.Color;
import java.util.List;

import net.minecraft.client.gui.ScaledResolution;

public class Class116
implements Class115 {
    @Expose
    @SerializedName(value="x")
    private float xPos;
    @Expose
    @SerializedName(value="y")
    private float yPos;
    public float initialXVal;
    public float initialYVal;
    private float startX;
    private float startY;
    private boolean dragging;
    private float width;
    private float height;
    @Expose
    @SerializedName(value="name")
    private String name;
    private final Class252 module;
    public Class139 hoverAnimation = new Class140(250, 1.0, Class141.BACKWARDS);
    private String longestModule;

    public Class116(Class252 module, String name, float initialXVal, float initialYVal) {
        this.module = module;
        this.name = name;
        this.xPos = initialXVal;
        this.yPos = initialYVal;
        this.initialXVal = initialXVal;
        this.initialYVal = initialYVal;
    }

    public float getX() {
        return this.xPos;
    }

    public void setX(float x) {
        this.xPos = x;
    }

    public float getY() {
        return this.yPos;
    }

    public void setY(float y) {
        this.yPos = y;
    }

    public final void onDraw(int mouseX, int mouseY) {
        boolean hovering = Class147.isHovering(this.xPos, this.yPos, this.width, this.height, mouseX, mouseY);
        if (this.dragging) {
            this.xPos = (float)mouseX - this.startX;
            this.yPos = (float)mouseY - this.startY;
        }
        this.hoverAnimation.setDirection(hovering ? Class141.FORWARDS : Class141.BACKWARDS);
        if (!this.hoverAnimation.isDone() || this.hoverAnimation.finished(Class141.FORWARDS)) {
            Class148.drawRoundOutline(this.xPos - 4.0f, this.yPos - 4.0f, this.width + 8.0f, this.height + 8.0f, 10.0f, 2.0f, Class460.applyOpacity(Color.WHITE, 0.0f), Class460.applyOpacity(Color.WHITE, this.hoverAnimation.getOutput().floatValue()));
        }
    }

    public final void onDrawArraylist(Class279 arraylistMod, int mouseX, int mouseY) {
        ScaledResolution sr = new ScaledResolution(mc);
        List<Class252> modules = Class1.instance.Method11().getArraylistModules(arraylistMod, arraylistMod.modules);
        String longest = this.getLongestModule(arraylistMod);
        this.width = (float) Class146.roundToHalf(arraylistMod.font.getStringWidth(longest) + 5.0f);
        this.height = (float) Class146.roundToHalf((arraylistMod.height.getValue() + 1.0) * (double)modules.size());
        float textVal = arraylistMod.font.getStringWidth(longest);
        float xVal = (float)sr.getScaledWidth() - (textVal + 8.0f + this.xPos);
        if ((float)sr.getScaledWidth() - this.xPos <= (float)sr.getScaledWidth() / 2.0f) {
            xVal += textVal - 2.0f;
        }
        boolean hovering = Class147.isHovering(xVal, this.yPos - 8.0f, this.width + 20.0f, this.height + 16.0f, mouseX, mouseY);
        if (this.dragging) {
            this.xPos = -((float)mouseX - this.startX);
            this.yPos = (float)mouseY - this.startY;
        }
        this.hoverAnimation.setDirection(hovering ? Class141.FORWARDS : Class141.BACKWARDS);
        if (!this.hoverAnimation.isDone() || this.hoverAnimation.finished(Class141.FORWARDS)) {
            Class148.drawRoundOutline(xVal, this.yPos - 8.0f, this.width + 20.0f, this.height + 16.0f, 10.0f, 2.0f, Class460.applyOpacity(Color.BLACK, 0.0f * this.hoverAnimation.getOutput().floatValue()), Class460.applyOpacity(Color.WHITE, this.hoverAnimation.getOutput().floatValue()));
        }
    }

    public final void onClick(int mouseX, int mouseY, int button) {
        boolean canDrag = Class147.isHovering(this.xPos, this.yPos, this.width, this.height, mouseX, mouseY);
        if (button == 0 && canDrag) {
            this.dragging = true;
            this.startX = (int)((float)mouseX - this.xPos);
            this.startY = (int)((float)mouseY - this.yPos);
        }
    }

    public final void onClickArraylist(Class279 arraylistMod, int mouseX, int mouseY, int button) {
        ScaledResolution sr = new ScaledResolution(mc);
        String longest = this.getLongestModule(arraylistMod);
        float textVal = arraylistMod.font.getStringWidth(longest);
        float xVal = (float)sr.getScaledWidth() - (textVal + 8.0f + this.xPos);
        if ((float)sr.getScaledWidth() - this.xPos <= (float)sr.getScaledWidth() / 2.0f) {
            xVal += textVal - 2.0f;
        }
        boolean canDrag = Class147.isHovering(xVal, this.yPos - 8.0f, this.width + 20.0f, this.height + 16.0f, mouseX, mouseY);
        if (button == 0 && canDrag) {
            this.dragging = true;
            this.startX = (int)((float)mouseX + this.xPos);
            this.startY = (int)((float)mouseY - this.yPos);
        }
    }

    public final void onRelease(int button) {
        if (button == 0) {
            this.dragging = false;
        }
    }

    private String getLongestModule(Class279 arraylistMod) {
        return arraylistMod.longest;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public String getName() {
        return this.name;
    }

    public Class252 getModule() {
        return this.module;
    }
}

