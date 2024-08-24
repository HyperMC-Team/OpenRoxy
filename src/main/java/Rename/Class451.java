package Rename;

import org.apache.commons.codec.binary.Base32;

public class Class451 {
    public static final String field_3939 = "?:P)(OL><KI*&UJM785^%TGB416$#EDCXSW@!QAZ";
    private static final byte[] field_3603 = new byte[]{7, 56, 3, 55, 108, 28, 105, 105, 83, 34, 22, 27, 125, 23, 62, 74, 22, 81, 77, 44, 124, 48, 94, 126, 102, 39, 62, 91, 33, 60, 82, 44, 61, 93, 124, 64, 102, 68, 5, 31, 7, 25, 55, 66, 37, 114, 10, 50, 58, 115, 34, 67, 36, 12, 45, 95, 90, 39, 19, 13, 127, 16, 93, 99, 79, 103, 74, 66, 46, 116, 81, 23, 106, 96, 48, 92, 100, 54, 86, 3, 49, 119, 126, 40, 59, 103, 51, 4, 71, 41, 17, 56, 113, 88, 79, 47, 42, 125, 43, 84, 113, 68, 120, 52, 101, 112, 127, 120, 87, 95, 57, 8, 30, 76, 123, 38, 6, 40, 24, 88, 43, 14, 121, 72, 57, 64, 121, 122, 11, 118, 78, 51, 37, 65, 31, 109, 114, 107, 96, 36, 98, 54, 106, 100, 119, 59, 109, 42, 75, 107, 2, 11, 72, 97, 14, 50, 24, 122, 117, 15, 97, 90, 110, 19, 71, 49, 33, 53, 12, 20, 20, 73, 26, 76, 101, 104, 69, 9, 111, 32, 21, 4, 28, 35, 91, 99, 6, 1, 98, 18, 1, 112, 9, 8, 27, 63, 89, 123, 85, 69, 116, 45, 70, 0, 111, 17, 25, 15, 46, 89, 118, 77, 110, 38, 78, 16, 5, 61, 82, 63, 80, 35, 67, 52, 18, 80, 13, 53, 2, 127, 30, 92, 87, 86, 73, 115, 75, 83, 26, 21, 58, 85, 32, 29, 70, 108, 84, 104, 10, 60, 29, 94, 41, 47, 65, 117};
    public static final Base32 field_3805 = new Base32();
    public static final String field_1323 = "1qaz2wsx3edc4ds6g4f4g65a7ujm8ik,9ol.0p;/";
    public static final String field_1716 = "!QAZ@WSX#EDC$RFV%TGB^YHN&UJM*IK<(OL>)P:?";
    public static final String field_2006 = "!QAZ@WSX#EDCARFKAREWRTG8734TGYQR";
    public static final String field_1039 = "DES";

    public static int processByte(int n, long l) {
        return n ^ field_3603[(int)(l % (long)field_3603.length)];
    }

    public static void processData(byte[] byArray, long l) {
        for (int i = 0; i < byArray.length; ++i) {
            int n = i;
            byArray[n] = (byte)(byArray[n] ^ field_3603[(int)(((long)i + l) % (long)field_3603.length)]);
        }
    }

    public static void processData(byte[] byArray, long l, int n, int n2) {
        for (int i = 0; i < n2; ++i) {
            int n3 = i + n;
            byArray[n3] = (byte)(byArray[n3] ^ field_3603[(int)(((long)i + l) % (long)field_3603.length)]);
        }
    }
}

