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

public enum Class136 {
    SUCCESS(new Color(0, 255, 81), "o"),
    DISABLE(new Color(255, 0, 0), "p"),
    INFO(new Color(23, 139, 255), "m"),
    WARNING(Color.YELLOW, "r");

    private final Color color;
    private final String icon;

    public Color getColor() {
        return this.color;
    }

    public String getIcon() {
        return this.icon;
    }

    Class136(Color color, String icon) {
        this.color = color;
        this.icon = icon;
    }
}

