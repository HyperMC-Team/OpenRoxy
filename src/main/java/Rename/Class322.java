package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import net.minecraft.client.gui.ScaledResolution;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
public class Class322
extends Class252 {
    private final Class259 time = new Class259("Time on Class107", 2.0, 10.0, 1.0, 0.5);
    private final Class257 modes = new Class257("Mode", "Default", "Default", "New");
    public static final Class254 toggleNotifications = new Class254("Show Toggle", true);
    public int offsetValue = 0;

    public Class322() {
        super("Notifications", Class263.Render);
    }

    public void render() {
        switch (this.modes.getMode()) {
            case "Default": {
                float yOffset = 0.0f;
                ScaledResolution sr = new ScaledResolution(Class115.mc);
                Class135.setToggleTime(this.time.getValue().floatValue());
                for (Class134 notification : Class135.getNotifications()) {
                    Class139 animation = notification.getAnimation();
                    animation.setDirection(notification.getTimerUtil().hasTimeElapsed((long)notification.getTime()) ? Class141.BACKWARDS : Class141.FORWARDS);
                    if (animation.finished(Class141.BACKWARDS)) {
                        Class135.getNotifications().remove(notification);
                        continue;
                    }
                    animation.setDuration(250);
                    int actualOffset = 10;
                    int notificationHeight = 24;
                    int notificationWidth = (int)Math.max(Class115.tenacityBoldFont20.getStringWidth(notification.getTitle()), Class115.tenacityFont18.getStringWidth(notification.getDescription())) + 25;
                    float x = (float)sr.getScaledWidth() - (float)(notificationWidth + 5) * animation.getOutput().floatValue();
                    float y = (float)sr.getScaledHeight() - (yOffset + 18.0f + 0.0f + (float)notificationHeight + 15.0f);
                    notification.drawDefault(x, y, notificationWidth, notificationHeight);
                    yOffset += (float)(notificationHeight + actualOffset) * animation.getOutput().floatValue();
                }
            }
            case "New": {
                ScaledResolution sr = new ScaledResolution(mc);
                float yOffset = 0.0f;
                Class135.setToggleTime(2.0f);
                for (Class134 notification : Class135.getNotifications()) {
                    Class139 animation = notification.getAnimation();
                    animation.setDirection(notification.getTimerUtil().hasTimeElapsed((long)notification.getTime()) ? Class141.BACKWARDS : Class141.FORWARDS);
                    if (animation.finished(Class141.BACKWARDS)) {
                        Class135.getNotifications().remove(notification);
                        continue;
                    }
                    animation.setDuration(200);
                    int actualOffset = 5;
                    int notificationHeight = 31;
                    int notificationWidth = Class330.arial20.getStringWidth(notification.getTitle()) + Class330.arial20.getStringWidth(notification.getDescription()) + 33;
                    float x2 = (float)((double)sr.getScaledWidth() - (double)(notificationWidth + 8) * animation.getOutput());
                    float y2 = (float)sr.getScaledHeight() - (yOffset + 18.0f + (float)this.offsetValue + (float)notificationHeight + 15.0f);
                    notification.drawLettuce(x2, y2, notificationWidth, notificationHeight);
                    yOffset = (float)((double)yOffset + (double)(notificationHeight + actualOffset) * animation.getOutput());
                }
                break;
            }
        }
    }
}

