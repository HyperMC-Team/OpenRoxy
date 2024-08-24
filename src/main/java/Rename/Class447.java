package Rename;

import org.lwjgl.input.Mouse;

public class Class447 {
    private float x;
    private float y;
    private float width;
    private float height;
    private float dragX;
    private float dragY;
    private float limitHeight;
    private boolean dragging;
    private boolean dragged;

    public void handleDrag(int mouseX, int mouseY, int mouseButton, boolean onMouse) {
        if (onMouse) {
            if (Class465.isHovering(this.x, this.y, this.width, this.limitHeight, mouseX, mouseY) && mouseButton == 0) {
                this.dragging = true;
                this.dragged = true;
                this.dragX = (float)mouseX - this.x;
                this.dragY = (float)mouseY - this.y;
            }
        } else if (this.dragging) {
            if (!Mouse.isButtonDown(0)) {
                this.dragging = false;
            }
            this.x = (float)mouseX - this.dragX;
            this.y = (float)mouseY - this.dragY;
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

    public float getDragX() {
        return this.dragX;
    }

    public float getDragY() {
        return this.dragY;
    }

    public float getLimitHeight() {
        return this.limitHeight;
    }

    public boolean isDragging() {
        return this.dragging;
    }

    public boolean isDragged() {
        return this.dragged;
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

    public void setDragX(float dragX) {
        this.dragX = dragX;
    }

    public void setDragY(float dragY) {
        this.dragY = dragY;
    }

    public void setLimitHeight(float limitHeight) {
        this.limitHeight = limitHeight;
    }

    public void setDragging(boolean dragging) {
        this.dragging = dragging;
    }

    public void setDragged(boolean dragged) {
        this.dragged = dragged;
    }
}

