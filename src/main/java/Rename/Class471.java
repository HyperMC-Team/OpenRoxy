package Rename;

public class Class471 {
    private float output;
    private float endpoint;
    private Class459 animation = new Class480(0, 0.0, Class473.BACKWARDS);

    public void animate(float destination, int ms) {
        this.output = (float)((double)this.endpoint - this.animation.getOutput());
        this.endpoint = destination;
        if (this.output != this.endpoint - destination) {
            this.animation = new Class480(ms, this.endpoint - this.output, Class473.BACKWARDS);
        }
    }

    public boolean isDone() {
        return this.output == this.endpoint || this.animation.isDone();
    }

    public float getOutput() {
        this.output = (float)((double)this.endpoint - this.animation.getOutput());
        return this.output;
    }

    public Class459 getAnimation() {
        return this.animation;
    }
}

