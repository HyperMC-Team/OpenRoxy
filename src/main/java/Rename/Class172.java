package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.util.Random;
import org.apache.commons.lang3.RandomStringUtils;

public class Class172 {
    private static final Random rand = new Random();

    public static int nextInt(int in, int out) {
        int max = Math.max(in, out);
        int min = Math.min(in, out);
        return rand.nextInt(max - min + 1) + min;
    }

    public static String randomName() {
        return RandomStringUtils.random(Class172.nextInt(14, 8), "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
    }
}

