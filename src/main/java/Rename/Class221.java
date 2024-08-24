package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

public class Class221 {
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 3999;
    private static final String[] M = new String[]{"", "M", "MM", "MMM", "MMMM"};
    private static final String[] C = new String[]{"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    private static final String[] X = new String[]{"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    private static final String[] I = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

    public static String generate(int number) {
        if (number < 1 || number > 3999) {
            throw new IllegalArgumentException(String.format("The number must be in the range [%d, %d]", 1, 3999));
        }
        return M[number / 1000] + C[number % 1000 / 100] + X[number % 100 / 10] + I[number % 10];
    }
}

