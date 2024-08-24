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

public class Class96 {
    private float x;
    private float y;
    private float initialX;
    private float initialY;
    private float startX;
    private float startY;
    private boolean dragging;

    public Class96(float initialXVal, float initialYVal) {
        this.initialX = initialXVal;
        this.initialY = initialYVal;
        this.x = initialXVal;
        this.y = initialYVal;
    }

    public final void onDraw(int mouseX, int mouseY) {
        if (this.dragging) {
            this.x = (float)mouseX - this.startX;
            this.y = (float)mouseY - this.startY;
        }
    }

    public final void onClick(int mouseX, int mouseY, int button, boolean canDrag) {
        if (button == 0 && canDrag) {
            this.dragging = true;
            this.startX = (int)((float)mouseX - this.x);
            this.startY = (int)((float)mouseY - this.y);
        }
    }

    public final void onRelease(int button) {
        if (button == 0) {
            this.dragging = false;
        }
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getInitialX() {
        return this.initialX;
    }

    public float getInitialY() {
        return this.initialY;
    }

    public float getStartX() {
        return this.startX;
    }

    public float getStartY() {
        return this.startY;
    }

    public boolean isDragging() {
        return this.dragging;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setInitialX(float initialX) {
        this.initialX = initialX;
    }

    public void setInitialY(float initialY) {
        this.initialY = initialY;
    }

    public void setStartX(float startX) {
        this.startX = startX;
    }

    public void setStartY(float startY) {
        this.startY = startY;
    }

    public void setDragging(boolean dragging) {
        this.dragging = dragging;
    }
}

