package net.minecraft.client.main;

import net.minecraft.client.main.Main;
import tech.skidonion.obfuscator.annotations.Renamer;
import tech.skidonion.obfuscator.annotations.StringEncryption;

@Renamer
@StringEncryption
public class JavaCheck {
    public static void run() {
        String java = System.getProperty("java.version");
        System.out.println("\u4f60\u7684\u6293\u54c7\u7248\u672c: " + java);
        if (java.startsWith("21")) {
            System.out.println("\u606d\u559c\u4e3b\u64ad\u8111\u5b50\u6b63\u5e38\u8fd0\u8f6c");
        } else {
            System.out.println("\u4e3b\u64ad\u6ca1\u7528\u6293\u54c721\u73a9\u4f60\u8001\u6bcd\u5462");
            Main.onStop();
        }
    }
}

