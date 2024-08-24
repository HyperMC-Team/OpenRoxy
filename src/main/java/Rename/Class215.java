package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

public class Class215
implements Class3 {
    public static Class247 currentRotation = new Class247(0.0f, 0.0f);

    public static void getCurrentRotation(Class354 rotationPacket) {
        if (rotationPacket.rotating) {
            currentRotation = new Class247(rotationPacket.getYaw(), rotationPacket.getPitch());
        }
    }
}

