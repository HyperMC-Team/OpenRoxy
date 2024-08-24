package Rename;

import java.awt.Color;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

public class Class443
extends GuiScreen {
    public static Class443 INSTANCE = new Class443();
    private final Map<Type, List<Class467>> buttons = new HashMap<Type, List<Class467>>();
    private final Map<SubType, Class442> dataMap = new HashMap<SubType, Class442>();
    private float x;
    private float y;
    private float width;
    private float height;
    private final Class447 dragComponent = new Class447();
    private static ScaledResolution scaledResolution;
    private Type currentType;
    private SubType currentSubType;
    private Class444 currentWindow;

    @Override
    public void initGui() {
        this.currentWindow = null;
        scaledResolution = new ScaledResolution(this.mc);
        if (this.currentType == Type.CREATE) {
            this.width = 320.0f;
            this.height = 150.0f;
        } else {
            this.width = 530.0f;
            this.height = 310.0f;
        }
        this.x = (float)scaledResolution.getScaledWidth() / 2.0f - this.width / 2.0f;
        this.y = (float)scaledResolution.getScaledHeight() / 2.0f - this.height / 2.0f;
        for (Class467 button : this.buttons.get((Object)this.currentType)) {
            button.initGui();
        }
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
        switch (this.currentType.ordinal()) {
            case 0: {
                float offsetY = 0.0f;
                for (Class467 button : this.buttons.get((Object)this.currentType)) {
                    button.setWidth(100.0f);
                    button.setHeight(35.0f);
                    button.setX(this.x + this.width / 2.0f - button.getWidth() / 2.0f);
                    button.setY(this.y + 30.0f + offsetY);
                    button.drawScreen(mouseX, mouseY, partialTicks);
                    offsetY += button.getHeight() + 15.0f;
                }
                break;
            }
            case 1: {
                float offsetX = 0.0f;
                for (Class467 button : this.buttons.get((Object)this.currentType)) {
                    button.setWidth(70.0f);
                    button.setHeight(20.0f);
                    button.setX(this.x + 30.0f + offsetX);
                    button.setY(this.y + this.height - button.getHeight() - 10.0f);
                    button.drawScreen(mouseX, mouseY, partialTicks);
                    offsetX += button.getWidth() + 10.0f;
                }
                break;
            }
        }
        if (this.currentWindow != null) {
            this.currentWindow.drawScreen(mouseX, mouseY, partialTicks);
        }
    }

    public void setCurrentType(Type currentType) {
        this.currentType = currentType;
        this.currentWindow = null;
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        this.dragComponent.handleDrag(mouseX, mouseY, mouseButton, true);
        for (Class467 button : this.buttons.get((Object)this.currentType)) {
            button.mouseClicked(mouseX, mouseY, mouseButton);
        }
        if (this.currentWindow != null) {
            this.currentWindow.mouseClicked(mouseX, mouseY, mouseButton);
        }
    }

    public Map<Type, List<Class467>> getButtons() {
        return this.buttons;
    }

    public Map<SubType, Class442> getDataMap() {
        return this.dataMap;
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

    public Class447 getDragComponent() {
        return this.dragComponent;
    }

    public Type getCurrentType() {
        return this.currentType;
    }

    public SubType getCurrentSubType() {
        return this.currentSubType;
    }

    public Class444 getCurrentWindow() {
        return this.currentWindow;
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

    public void setCurrentSubType(SubType currentSubType) {
        this.currentSubType = currentSubType;
    }

    public void setCurrentWindow(Class444 currentWindow) {
        this.currentWindow = currentWindow;
    }

    public static enum Type {
        CREATE,
        MAIN,
        LIST;

    }

    public static enum SubType {
        LIST,
        INPUT,
        INVITE,
        REQUEST,
        KICK;

        private final Class459 animation = new Class474(270, 1.0).setDirection(Class473.BACKWARDS);

        public void setCurrent() {
            if (INSTANCE.getCurrentSubType() != null) {
                Class443.INSTANCE.getCurrentSubType().animation.setState(false);
            }
            INSTANCE.setCurrentSubType(this);
            this.animation.setState(true);
            Class442 data = INSTANCE.getDataMap().get((Object)this);
            Class444 window = new Class444(data.getText(), this, data.getButtons(), INSTANCE);
            window.setX(50.0f);
            window.setY(50.0f);
            window.setWidth(100.0f);
            window.setHeight(Math.min(150.0f, 40.0f + (float)data.getButtons().size() * 18.0f));
            INSTANCE.setCurrentWindow(window);
        }

        public Class459 getAnimation() {
            return this.animation;
        }
    }
}

