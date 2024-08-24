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

import java.util.concurrent.CopyOnWriteArrayList;

public class Class135 {
    private static float toggleTime = 2.0f;
    private static final CopyOnWriteArrayList<Class134> notifications = new CopyOnWriteArrayList();

    public static void post(Class136 type, String title, String description) {
        Class135.post(new Class134(type, title, description));
    }

    public static void post(Class136 type, String title, String description, float time) {
        Class135.post(new Class134(type, title, description, time));
    }

    private static void post(Class134 notification) {
        if (Class262.getModule(Class322.class).isState()) {
            notifications.add(notification);
        }
    }

    public static float getToggleTime() {
        return toggleTime;
    }

    public static void setToggleTime(float toggleTime) {
        Class135.toggleTime = toggleTime;
    }

    public static CopyOnWriteArrayList<Class134> getNotifications() {
        return notifications;
    }
}

