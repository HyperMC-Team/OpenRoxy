package Rename;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Class481 {
    public static String encrypt(byte[] data, String key) {
        try {
            Cipher c = Cipher.getInstance("AES");
            c.init(1, new SecretKeySpec(key.getBytes(), "AES"));
            byte[] encVal = c.doFinal(data);
            return Base64.getEncoder().encodeToString(encVal);
        }
        catch (Exception var6) {
            var6.printStackTrace();
            return "null";
        }
    }

    public static byte[] decrypt(String data, String key) {
        try {
            Cipher c = Cipher.getInstance("AES");
            c.init(2, new SecretKeySpec(key.getBytes(), "AES"));
            return c.doFinal(Base64.getDecoder().decode(data.getBytes()));
        }
        catch (Exception var7) {
            var7.printStackTrace();
            return "null".getBytes();
        }
    }
}

