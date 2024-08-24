package Rename;

import org.lwjgl.input.Mouse;

public class Class455 {
    private float target;
    private float maxTarget;
    private final Class471 scrollAnim = new Class471();

    public void use() {
        int wheel = Mouse.getDWheel();
        if (wheel != 0) {
            this.target = wheel > 0 ? (this.target = this.target + 15.0f) : (this.target = this.target - 15.0f);
        }
        this.target = -Math.max(0.0f, Math.min(-this.target, this.maxTarget));
    }

    public void animate() {
        this.scrollAnim.animate(this.target, 22);
    }

    public float getAnimationTarget() {
        return this.scrollAnim.getOutput();
    }

    public float getTarget() {
        return this.target;
    }

    public float getMaxTarget() {
        return this.maxTarget;
    }

    public Class471 getScrollAnim() {
        return this.scrollAnim;
    }

    public void setTarget(float target) {
        this.target = target;
    }

    public void setMaxTarget(float maxTarget) {
        this.maxTarget = maxTarget;
    }
}

