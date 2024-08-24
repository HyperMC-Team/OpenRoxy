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

import java.awt.Color;

public class Class134
implements Class115 {
    private final Class136 notificationType;
    private final String title;
    private final String description;
    private final float time;
    private final Class150 timerUtil;
    private final Class139 animation;

    public Class134(Class136 type, String title, String description) {
        this(type, title, description, Class135.getToggleTime());
    }

    public Class134(Class136 type, String title, String description, float time) {
        this.title = title;
        this.description = description;
        this.time = (long)(time * 1000.0f);
        this.timerUtil = new Class150();
        this.notificationType = type;
        this.animation = new Class143(250, 1.0);
    }

    public void drawDefault(float x, float y, float width, float height) {
        Class148.drawRound(x, y, width, height, 2.0f, new Color(0, 0, 0, 100));
        tenacityBoldFont20.drawString(this.getTitle(), x + 11.0f, y + 2.0f, new Color(255, 255, 255, 255));
        tenacityFont18.drawString(this.getDescription(), x + 11.0f, y + 6.0f + (float)tenacityFont18.getHeight(), new Color(255, 255, 255, 255));
        Class148.drawRound(x + 2.0f, y + 6.0f, 2.0f, 12.0f, 0.5f, this.notificationType.getColor());
        Class148.drawGradientHorizontal(x, y, (this.getTime() - (float)this.getTimerUtil().getTime()) / this.getTime() * width, height, 2.0f, new Color(0, 249, 255, 119), new Color(211, 24, 255, 97));
    }

    public void drawLettuce(float x2, float y2, float width, float height) {
        Class468.drawGradientRoundLR(x2, y2 - 10.0f, (this.getTime() - (float)this.getTimerUtil().getTime()) / this.getTime() * width, height - 10.0f, 2.0f, this.notificationType.getColor(), this.notificationType.getColor());
        Color textColor = Class460.applyOpacity(Color.WHITE.brighter(), 80.0f);
        Class468.drawRound(x2, y2 - 10.0f, width, height - 10.0f, 2.0f, new Color(0, 0, 0, 100));
        Class330.arial20.drawString(this.getTitle() + " " + this.getDescription() + "!", x2 + 12.0f, y2 + 7.0f - 10.0f, textColor.getRGB());
        Class468.drawRound(x2 + 2.0f, y2 + 10.0f - 10.0f, 2.0f, 2.0f, 0.5f, this.notificationType.getColor());
    }

    public Class136 getNotificationType() {
        return this.notificationType;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public float getTime() {
        return this.time;
    }

    public Class150 getTimerUtil() {
        return this.timerUtil;
    }

    public Class139 getAnimation() {
        return this.animation;
    }
}

