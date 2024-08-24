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

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.lwjgl.opengl.GL11;

public class Class125 {
    private boolean rendered;
    private Class127 animType;
    private long animDuration = 250L;
    private Class126 state = Class126.IN;
    private final Class241 timer = new Class241();
    private boolean animDone;
    private boolean lastEnabled;
    private float timeElapsed;

    public void updateState(boolean rendered) {
        this.rendered = rendered;
        if (this.timer.getTimeElapsed() >= this.animDuration) {
            this.animDone = true;
        }
        if (this.animDone) {
            this.timer.reset();
        }
        if (rendered && !this.lastEnabled) {
            this.state = Class126.IN;
            if (!this.animDone) {
                this.timer.setTimeElapsed(this.animDuration - this.timer.getTimeElapsed());
            }
            this.animDone = false;
        } else if (!rendered && this.lastEnabled) {
            this.state = Class126.OUT;
            if (!this.animDone) {
                this.timer.setTimeElapsed(this.animDuration - this.timer.getTimeElapsed());
            }
            this.animDone = false;
        }
        this.timeElapsed = Math.max(this.timer.getTimeElapsed(), 1L);
        this.lastEnabled = rendered;
    }

    public void render(Class128 renderInstructions, float startX, float startY, float endX, float endY) {
        GL11.glPushMatrix();
        if (!this.animDone) {
            if (this.state == Class126.IN) {
                this.startAnimationIn(startX, startY, endX, endY);
            } else {
                this.startAnimationOut(startX, startY, endX, endY);
            }
        }
        renderInstructions.execute();
        if (!this.animDone) {
            if (this.state == Class126.IN) {
                this.stopAnimationIn(startX, startY, endX, endY);
            } else {
                this.stopAnimationOut(startX, startY, endX, endY);
            }
        }
        GL11.glPopMatrix();
    }

    public float getYMult() {
        this.timeElapsed = Math.max(this.timer.getTimeElapsed(), 1L);
        if (!this.animDone) {
            if (this.animType == Class127.POP || this.animType == Class127.SLIDE) {
                if (this.rendered) {
                    return this.timeElapsed / (float)this.animDuration;
                }
                return 1.0f - this.timeElapsed / (float)this.animDuration;
            }
            if (this.animType == Class127.POP2) {
                float anim1 = (float)this.animDuration * 0.9f;
                float anim2 = (float)this.animDuration * 0.1f;
                return this.rendered ? (anim1 >= this.timeElapsed ? 1.1f / anim1 * this.timeElapsed : 1.1f - 0.1f / anim2 * (this.timeElapsed - anim1)) : (this.timeElapsed >= anim2 ? 1.1f - 1.1f / anim1 * (this.timeElapsed - anim2) : 1.0f + 0.1f / anim2 * this.timeElapsed);
            }
        }
        return 1.0f;
    }

    private void startAnimationIn(float startX, float startY, float endX, float endY) {
        switch (this.animType) {
            case POP:
            case POP2: {
                this.startScaling(this.getYMult(), startX, startY, endX, endY);
                break;
            }
            case SLIDE: {
                ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
                int screenWidth = sr.getScaledWidth() + 3;
                float x = startX > (float)(sr.getScaledWidth() / 2) ? ((float)screenWidth - startX) * (1.0f - this.getYMult()) : -endX * (1.0f - this.getYMult());
                GL11.glTranslatef(x, 0.0f, 0.0f);
            }
        }
    }

    private void stopAnimationIn(float startX, float startY, float endX, float endY) {
        switch (this.animType) {
            case POP:
            case POP2: {
                this.stopScaling(this.getYMult(), startX, startY, endX, endY);
                break;
            }
            case SLIDE: {
                ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
                int screenWidth = sr.getScaledWidth() + 3;
                float x = startX > (float)(sr.getScaledWidth() / 2) ? ((float)screenWidth - startX) * (1.0f - this.getYMult()) : -endX * (1.0f - this.getYMult());
                GL11.glTranslatef(-x, 0.0f, 0.0f);
            }
        }
    }

    private void startAnimationOut(float startX, float startY, float endX, float endY) {
        switch (this.animType) {
            case POP:
            case POP2: {
                this.startScaling(this.getYMult(), startX, startY, endX, endY);
                break;
            }
            case SLIDE: {
                ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
                int screenWidth = sr.getScaledWidth() + 3;
                float x = startX > (float)(sr.getScaledWidth() / 2) ? ((float)screenWidth - startX) * (1.0f - this.getYMult()) : -endX * (1.0f - this.getYMult());
                GL11.glTranslatef(x, 0.0f, 0.0f);
            }
        }
    }

    private void stopAnimationOut(float startX, float startY, float endX, float endY) {
        switch (this.animType) {
            case POP:
            case POP2: {
                this.stopScaling(this.getYMult(), startX, startY, endX, endY);
                break;
            }
            case SLIDE: {
                ScaledResolution sr = new ScaledResolution(Minecraft.getMinecraft());
                int screenWidth = sr.getScaledWidth() + 3;
                float x = startX > (float)(sr.getScaledWidth() / 2) ? ((float)screenWidth - startX) * (1.0f - this.getYMult()) : -endX * (1.0f - this.getYMult());
                GL11.glTranslatef(-x, 0.0f, 0.0f);
            }
        }
    }

    private void startScaling(float mult, float startX, float startY, float endX, float endY) {
        float middleX = startX + (endX - startX) * 0.5f;
        float middleY = startY + (endY - startY) * 0.5f;
        float translateX = middleX - middleX * mult;
        float translateY = middleY - middleY * mult;
        GL11.glTranslatef(translateX, translateY, 1.0f);
        GL11.glScalef(mult, mult, 1.0f);
    }

    private void stopScaling(float mult, float startX, float startY, float endX, float endY) {
        float middleX = startX + (endX - startX) * 0.5f;
        float middleY = startY + (endY - startY) * 0.5f;
        float translateX = middleX - middleX * mult;
        float translateY = middleY - middleY * mult;
        GL11.glScalef(1.0f / mult, 1.0f / mult, 1.0f);
        GL11.glTranslatef(-translateX, -translateY, 1.0f);
    }

    public boolean isRendered() {
        return this.rendered;
    }

    public Class127 getAnimType() {
        return this.animType;
    }

    public long getAnimDuration() {
        return this.animDuration;
    }

    public Class126 getState() {
        return this.state;
    }

    public Class241 getTimer() {
        return this.timer;
    }

    public boolean isAnimDone() {
        return this.animDone;
    }

    public boolean isLastEnabled() {
        return this.lastEnabled;
    }

    public float getTimeElapsed() {
        return this.timeElapsed;
    }

    public void setAnimType(Class127 animType) {
        this.animType = animType;
    }

    public void setAnimDuration(long animDuration) {
        this.animDuration = animDuration;
    }
}
