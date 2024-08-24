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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.security.SecureRandom;
import java.text.DecimalFormat;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;

public class Class146 {
    public static final DecimalFormat DF_0 = new DecimalFormat("0");
    public static final DecimalFormat DF_1 = new DecimalFormat("0.0");
    public static final DecimalFormat DF_2 = new DecimalFormat("0.00");
    public static final DecimalFormat DF_1D = new DecimalFormat("0.#");
    public static final DecimalFormat DF_2D = new DecimalFormat("0.##");
    public static final SecureRandom secureRandom = new SecureRandom();

    public static int getRandomInRange(int min, int max) {
        return (int)(Math.random() * (double)(max - min) + (double)min);
    }

    public static double[] yawPos(double value) {
        return Class146.yawPos(Minecraft.getMinecraft().thePlayer.rotationYaw * MathHelper.deg2Rad, value);
    }

    public static double[] yawPos(float yaw, double value) {
        return new double[]{(double)(-MathHelper.sin(yaw)) * value, (double)MathHelper.cos(yaw) * value};
    }

    public static float getRandomInRange(float min, float max) {
        SecureRandom random = new SecureRandom();
        return random.nextFloat() * (max - min) + min;
    }

    public static double getRandomInRange(double min, double max) {
        SecureRandom random = new SecureRandom();
        return min == max ? min : random.nextDouble() * (max - min) + min;
    }

    public static int getRandomNumberUsingNextInt(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public static double lerp(double old, double newVal, double amount) {
        return (1.0 - amount) * old + amount * newVal;
    }

    public static Double interpolate(double oldValue, double newValue, double interpolationValue) {
        return oldValue + (newValue - oldValue) * interpolationValue;
    }

    public static float interpolateFloat(float oldValue, float newValue, double interpolationValue) {
        return Class146.interpolate(oldValue, newValue, (float)interpolationValue).floatValue();
    }

    public static int interpolateInt(int oldValue, int newValue, double interpolationValue) {
        return Class146.interpolate(oldValue, newValue, (float)interpolationValue).intValue();
    }

    public static float calculateGaussianValue(float x, float sigma) {
        double output = 1.0 / Math.sqrt(Math.PI * 2 * (double)(sigma * sigma));
        return (float)(output * Math.exp((double)(-(x * x)) / (2.0 * (double)(sigma * sigma))));
    }

    public static double roundToHalf(double d) {
        return (double)Math.round(d * 2.0) / 2.0;
    }

    public static double round(double num, double increment) {
        BigDecimal bd = new BigDecimal(num);
        bd = bd.setScale((int)increment, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static String round(String value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.stripTrailingZeros();
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.toString();
    }

    public static float getRandomFloat(float max, float min) {
        SecureRandom random = new SecureRandom();
        return random.nextFloat() * (max - min) + min;
    }

    public static int getNumberOfDecimalPlace(double value) {
        BigDecimal bigDecimal = new BigDecimal(value);
        return Math.max(0, bigDecimal.stripTrailingZeros().scale());
    }
}

