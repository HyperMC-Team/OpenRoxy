package Rename;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.util.ResourceLocation;
import net.optifine.util.MathUtils;
import org.lwjgl.input.Mouse;

public class Class445
extends GuiScreen {
    public static Class445 INSTANCE = new Class445();
    private float x;
    private float y;
    private float width;
    private float height;
    private final ResourceLocation germLogo = new ResourceLocation("quicksand/germ.png");
    private final List<Class446> elements = new ArrayList<Class446>();
    private Class446 currentElement;
    private String guiName;
    private final Class459 switchScreenAnim = new Class474(350, 1.0).setDirection(Class473.BACKWARDS);
    private final Class459 backHoverAnim = new Class474(270, 1.0).setDirection(Class473.BACKWARDS);
    private final Class447 dragComponent = new Class447();
    private final Class455 scroll = new Class455();
    private ScaledResolution scaledResolution;
    private Class470 backButtonRipple = new Class470();

    @Override
    public void initGui() {
        this.scaledResolution = new ScaledResolution(this.mc);
        this.width = 470.0f;
        this.height = 290.0f;
        this.x = (float)this.scaledResolution.getScaledWidth() / 2.0f - this.width / 2.0f;
        this.y = (float)this.scaledResolution.getScaledHeight() / 2.0f - this.height / 2.0f;
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.dragComponent.setX(this.x);
        this.dragComponent.setY(this.y);
        this.dragComponent.setWidth(this.width);
        this.dragComponent.setHeight(this.height);
        this.dragComponent.setLimitHeight(this.height);
        this.dragComponent.handleDrag(mouseX, mouseY, 0, false);
        this.x = this.dragComponent.getX();
        this.y = this.dragComponent.getY();
        Class468.drawRound(this.x, this.y, this.width, this.height, 7.0f, new Color(0, 0, 0, 120));
        float switchOutput = (float)this.switchScreenAnim.getOutput();
        float originalX = this.x;
        this.x = MathUtils.interpolateFloat(originalX, originalX - this.width, switchOutput);
        Class465.startGlScissor((int)originalX, (int)this.y, (int)this.width, (int)this.height);
        Class330.arial64.drawStringWithShadow("Germ Game", this.x + 20.0f, this.y + 8.0f, -1);
        int count = 0;
        float offsetX = 0.0f;
        float offsetY = 0.0f;
        for (Class446 element : this.elements) {
            float x = this.x + 20.0f + offsetX;
            float y = this.y + 60.0f + offsetY;
            float width = 110.0f;
            float height = 100.0f;
            boolean hovering = Class465.isHovering(x, y, width, height, mouseX, mouseY);
            element.setRunnable(() -> {
                if (Class465.isHovering(x, y, width, height, mouseX, mouseY)) {
                    element.click(this.guiName);
                }
            });
            if (element.getDefaultImage() != null && element.getGifImage() != null) {
                this.mc.getTextureManager().bindTexture(hovering ? element.getCurrentGifImage() : element.getDefaultImage());
                Class468.drawRoundTextured(x, y, width, height, 5.0f, 1.0f);
            }
            if (hovering && !this.switchScreenAnim.isState()) {
                float textOffsetY = 0.0f;
                for (String hoverDos : element.getHoverDos()) {
                    Class330.arial18.drawStringWithShadow(hoverDos, mouseX + 15, (float)(mouseY - (Class330.arial18.getHeight() + 4) * 2) + textOffsetY, -1);
                    textOffsetY += (float)(Class330.arial18.getHeight() + 4);
                }
            }
            if (++count % 3 == 0) {
                offsetY += height + 15.0f;
                offsetX = 0.0f;
                continue;
            }
            offsetX += width + 50.0f;
        }
        Class465.stopGlScissor();
        float doubleX = this.x + this.width;
        if (this.switchScreenAnim.isState() || !this.switchScreenAnim.isDone()) {
            Class465.startGlScissor((int)originalX, (int)this.y, (int)this.width, (int)this.height);
            Class468.drawRound(doubleX + 10.0f, this.y + 10.0f, 20.0f, 20.0f, 10.0f, new Color(0, 0, 0, 150));
            this.backButtonRipple.draw(() -> Class468.drawRound(doubleX + 10.0f, this.y + 10.0f, 20.0f, 20.0f, 10.0f, Color.WHITE));
            this.backHoverAnim.setState(Class465.isHovering(doubleX + 20.0f - (float) Class330.icon22.getStringWidth("y") / 2.0f, this.y + 20.0f - (float)(Class330.icon22.getHeight() / 2), Class330.icon22.getStringWidth("y"), Class330.icon22.getHeight(), mouseX, mouseY));
            Color hoverColor = Class460.interpolateColorC(Color.WHITE, Class460.darker(Color.WHITE, 0.6f), (float)this.backHoverAnim.getOutput());
            if (this.backHoverAnim.isState() && this.switchScreenAnim.isState() && Mouse.isButtonDown(0)) {
                this.backButtonRipple.mouseClicked(mouseX, mouseY);
                this.switchScreenAnim.setState(false);
            }
            Class330.icon22.drawStringWithShadow("y", doubleX + 20.0f - (float) Class330.icon22.getStringWidth("y") / 2.0f, this.y + 22.0f - (float)(Class330.icon22.getHeight() / 2), hoverColor.getRGB());
            Class330.arial22.drawCenteredStringWithShadow("Sub Game", doubleX + this.width / 2.0f, this.y + 10.0f, -1);
            if (this.currentElement != null) {
                float startX = doubleX + 30.0f;
                for (Class454 element : this.currentElement.getSubElements()) {
                    float x = startX + this.scroll.getAnimationTarget();
                    float y = this.y + 50.0f;
                    float width = 100.0f;
                    float height = 180.0f;
                    boolean hovering = Class465.isHovering(x, y, width, height, mouseX, mouseY);
                    element.getHoverAnim().setState(hovering);
                    element.setRunnable(() -> {
                        if (Class465.isHovering(x, y, width, height, mouseX, mouseY)) {
                            element.joinGame(this.guiName);
                        }
                    });
                    Color color = new Color(0, 0, 0, 150);
                    Color hovColor = Class460.interpolateColorC(color, new Color(0, 0, 0, 200), (float)element.getHoverAnim().getOutput());
                    Class468.drawRound(x, y, width, height, 7.0f, hovColor);
                    Class330.arial18.drawCenteredStringWithShadow(element.getName(), x + width / 2.0f, y + 12.0f, -1);
                    float descOffsetY = 0.0f;
                    for (String s : element.getDesc()) {
                        Class330.arial10.drawStringWithShadow(s, x + 5.0f, y + height - (float)(element.getDesc().size() * 10) + descOffsetY, -1);
                        descOffsetY += 10.0f;
                    }
                    element.getAnimation().draw(() -> Class468.drawRound(x, y, width, height, 7.0f, Color.WHITE));
                    startX += width + 20.0f;
                }
                float max = startX - doubleX - this.width;
                this.scroll.setMaxTarget(max);
                if (Class465.isHovering(doubleX, this.y, this.width, this.height, mouseX, mouseY)) {
                    this.scroll.use();
                }
                this.scroll.animate();
                float padding = 10.0f;
                float spacingWidth = this.width - 5.0f;
                float scrollWidth = max <= 0.0f ? spacingWidth : 50.0f * (spacingWidth / this.scroll.getMaxTarget()) - padding;
                float scrollX = -this.scroll.getAnimationTarget() / this.scroll.getMaxTarget() * (spacingWidth - scrollWidth) + padding / 4.0f;
                Class468.drawRound(doubleX + scrollX, this.y + this.height - 8.0f, scrollWidth, 5.0f, 2.0f, new Color(0, 0, 0, 150));
            }
            Class465.stopGlScissor();
        }
        this.x = originalX;
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        this.dragComponent.handleDrag(mouseX, mouseY, mouseButton, true);
        if (Class465.isHovering(this.x, this.y, this.width, this.height, mouseX, mouseY)) {
            for (Class446 germGameElement : this.elements) {
                if (germGameElement.getRunnable() == null) continue;
                germGameElement.getRunnable().run();
            }
            if (this.currentElement != null) {
                for (Class454 germGameSubElement : this.currentElement.getSubElements()) {
                    germGameSubElement.getAnimation().mouseClicked(mouseX, mouseY);
                    if (germGameSubElement.getRunnable() == null) continue;
                    germGameSubElement.getRunnable().run();
                }
            }
        }
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public ResourceLocation getGermLogo() {
        return this.germLogo;
    }

    public List<Class446> getElements() {
        return this.elements;
    }

    public Class446 getCurrentElement() {
        return this.currentElement;
    }

    public String getGuiName() {
        return this.guiName;
    }

    public Class459 getSwitchScreenAnim() {
        return this.switchScreenAnim;
    }

    public Class459 getBackHoverAnim() {
        return this.backHoverAnim;
    }

    public Class447 getDragComponent() {
        return this.dragComponent;
    }

    public Class455 getScroll() {
        return this.scroll;
    }

    public ScaledResolution getScaledResolution() {
        return this.scaledResolution;
    }

    public Class470 getBackButtonRipple() {
        return this.backButtonRipple;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setCurrentElement(Class446 currentElement) {
        this.currentElement = currentElement;
    }

    public void setGuiName(String guiName) {
        this.guiName = guiName;
    }

    public void setScaledResolution(ScaledResolution scaledResolution) {
        this.scaledResolution = scaledResolution;
    }

    public void setBackButtonRipple(Class470 backButtonRipple) {
        this.backButtonRipple = backButtonRipple;
    }
}

