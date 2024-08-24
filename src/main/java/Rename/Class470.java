package Rename;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

public class Class470 {
    public final List<Ripple> ripples = new ArrayList<Ripple>();

    public void addRipple(float x, float y, float radius, float speed) {
        this.ripples.add(new Ripple(x, y, radius, speed));
    }

    public void mouseClicked(float mouseX, float mouseY, float speed) {
        this.ripples.add(new Ripple(mouseX, mouseY, 100.0f, speed));
    }

    public void mouseClicked(float mouseX, float mouseY) {
        this.ripples.add(new Ripple(mouseX, mouseY, 100.0f, 1.0f));
    }

    public void draw(float x, float y, float width, float height) {
        GL11.glDepthMask(true);
        GL11.glClearDepth(1.0);
        GL11.glClear(256);
        GL11.glDepthFunc(519);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glColorMask(false, false, false, false);
        Class465.drawRect(x, y, width, height, -1);
        GL11.glDepthMask(false);
        GL11.glColorMask(true, true, true, true);
        GL11.glDepthFunc(514);
        for (Ripple c : this.ripples) {
            c.progress = Class461.animateSmooth(c.progress, c.topRadius, c.speed / 50.0f);
            Class465.drawCircle2(c.x, c.y, c.progress, new Color(1.0f, 1.0f, 1.0f, (1.0f - Math.min(1.0f, Math.max(0.0f, c.progress / c.topRadius))) / 2.0f).getRGB());
        }
        GL11.glDepthMask(true);
        GL11.glClearDepth(1.0);
        GL11.glClear(256);
        GL11.glDepthFunc(515);
        GL11.glDepthMask(false);
        GL11.glDisable(2929);
    }

    public void draw(Runnable context) {
        GL11.glDepthMask(true);
        GL11.glClearDepth(1.0);
        GL11.glClear(256);
        GL11.glDepthFunc(519);
        GL11.glEnable(2929);
        GL11.glDepthMask(true);
        GL11.glColorMask(false, false, false, false);
        context.run();
        GL11.glDepthMask(false);
        GL11.glColorMask(true, true, true, true);
        GL11.glDepthFunc(514);
        for (Ripple c : this.ripples) {
            c.progress = Class461.animateSmooth(c.progress, c.topRadius, c.speed / 50.0f);
            Class465.drawCircle2(c.x, c.y, c.progress, new Color(1.0f, 1.0f, 1.0f, (1.0f - Math.min(1.0f, Math.max(0.0f, c.progress / c.topRadius))) / 2.0f).getRGB());
        }
        GL11.glDepthMask(true);
        GL11.glClearDepth(1.0);
        GL11.glClear(256);
        GL11.glDepthFunc(515);
        GL11.glDepthMask(false);
        GL11.glDisable(2929);
    }

    public static class Ripple {
        public float x;
        public float y;
        public float topRadius;
        public float speed;
        public float alpha;
        public float progress;
        public boolean complete;

        public Ripple(float x, float y, float rad, float speed) {
            this.x = x;
            this.y = y;
            this.alpha = 200.0f;
            this.topRadius = rad;
            this.speed = speed;
        }
    }
}

