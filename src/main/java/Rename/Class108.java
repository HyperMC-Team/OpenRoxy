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

import org.lwjgl.input.Mouse;

public class Class108 {
    private float maxScroll = Float.MAX_VALUE;
    private float minScroll = 0.0f;
    private float rawScroll = 0.0f;
    private float scroll;
    private Class139 scrollAnimation = new Class149(0, 0.0, Class141.BACKWARDS);

    public void onScroll(int ms) {
        this.scroll = this.rawScroll - this.scrollAnimation.getOutput().floatValue();
        this.rawScroll += (float)Mouse.getDWheel() / 4.0f;
        this.rawScroll = Math.max(Math.min(this.minScroll, this.rawScroll), -this.maxScroll);
        this.scrollAnimation = new Class149(ms, this.rawScroll - this.scroll, Class141.BACKWARDS);
    }

    public boolean isScrollAnimationDone() {
        return this.scrollAnimation.isDone();
    }

    public float getScroll() {
        this.scroll = this.rawScroll - this.scrollAnimation.getOutput().floatValue();
        return this.scroll;
    }

    public float getMaxScroll() {
        return this.maxScroll;
    }

    public float getMinScroll() {
        return this.minScroll;
    }

    public float getRawScroll() {
        return this.rawScroll;
    }

    public void setMaxScroll(float maxScroll) {
        this.maxScroll = maxScroll;
    }

    public void setMinScroll(float minScroll) {
        this.minScroll = minScroll;
    }

    public void setRawScroll(float rawScroll) {
        this.rawScroll = rawScroll;
    }

    public Class139 getScrollAnimation() {
        return this.scrollAnimation;
    }
}

