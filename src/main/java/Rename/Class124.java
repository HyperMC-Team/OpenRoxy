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

import java.util.Base64;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Class124 {
    public static String findLongestModuleName(List<Class252> modules) {
        return Collections.max(modules, Comparator.comparing(module -> (module.getName() + (module.hasMode() ? " " + module.getSuffix() : "")).length())).getName();
    }

    public static String getLongestModeName(List<String> listOfWords) {
        String longestWord = null;
        for (String word : listOfWords) {
            if (longestWord != null && word.length() <= longestWord.length()) continue;
            longestWord = word;
        }
        return longestWord != null ? longestWord : "";
    }

    public static String b64(Object o) {
        return Base64.getEncoder().encodeToString(String.valueOf(o).getBytes());
    }
}

