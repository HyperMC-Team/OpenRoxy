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
import com.google.gson.JsonObject;
import java.awt.Color;

public class Class255
extends Class261 {
    private float hue = 0.0f;
    private float saturation = 1.0f;
    private float brightness = 1.0f;
    private Rainbow rainbow = null;

    public Class255(String name, Color defaultColor) {
        this.name = name;
        this.setColor(defaultColor);
    }

    public Color getColor() {
        return this.isRainbow() ? this.getRainbow().getColor() : Color.getHSBColor(this.hue, this.saturation, this.brightness);
    }

    public Color getAltColor() {
        return this.isRainbow() ? this.getRainbow().getColor(40) : Class460.darker(this.getColor(), 0.6f);
    }

    public void setColor(Color color) {
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        this.hue = hsb[0];
        this.saturation = hsb[1];
        this.brightness = hsb[2];
    }

    public void setColor(float hue, float saturation, float brightness) {
        this.hue = hue;
        this.saturation = saturation;
        this.brightness = brightness;
    }

    public double getHue() {
        return this.hue;
    }

    public double getSaturation() {
        return this.saturation;
    }

    public double getBrightness() {
        return this.brightness;
    }

    public String getHexCode() {
        Color color = this.getColor();
        return String.format("%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
    }

    public void setRainbow(boolean rainbow) {
        this.rainbow = rainbow ? new Rainbow() : null;
    }

    public boolean isRainbow() {
        return this.rainbow != null;
    }

    public Object getConfigValue() {
        return this.isRainbow() ? this.getRainbow().getJsonObject() : Integer.valueOf(this.getColor().getRGB());
    }

    public void setHue(float hue) {
        this.hue = hue;
    }

    public void setSaturation(float saturation) {
        this.saturation = saturation;
    }

    public void setBrightness(float brightness) {
        this.brightness = brightness;
    }

    public Rainbow getRainbow() {
        return this.rainbow;
    }

    public static class Rainbow {
        private float saturation = 1.0f;
        private int speed = 15;

        public Color getColor() {
            return this.getColor(0);
        }

        public Color getColor(int index) {
            return Class460.rainbow(this.speed, index, this.saturation, 1.0f, 1.0f);
        }

        public JsonObject getJsonObject() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("saturation", Float.valueOf(this.saturation));
            jsonObject.addProperty("speed", this.speed);
            return jsonObject;
        }

        public float getSaturation() {
            return Math.max(0.0f, Math.min(1.0f, this.saturation));
        }

        public void setSaturation(float saturation) {
            this.saturation = saturation;
        }

        public int getSpeed() {
            return this.speed;
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }
    }
}

