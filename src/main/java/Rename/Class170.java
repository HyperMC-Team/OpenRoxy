package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import net.minecraft.util.MathHelper;

public class Class170 {
    public static final float PI = (float)Math.PI;
    public static final float TO_RADIANS = (float)Math.PI / 180;
    public static final float TO_DEGREES = 57.295776f;
    public static final float[] COSINE = new float[361];
    public static final float[] SINE = new float[361];

    public static void calculate() {
        for (int i = 0; i <= 360; ++i) {
            Class170.COSINE[i] = MathHelper.cos((float)i * ((float)Math.PI / 180));
            Class170.SINE[i] = MathHelper.sin((float)i * ((float)Math.PI / 180));
        }
    }

    public static int toIntDegree(float angle) {
        return (int)(angle % 360.0f + 360.0f) % 360;
    }
}

