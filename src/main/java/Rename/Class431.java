package Rename;

public class Class431
implements Class441 {
    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            int number = (int)(Math.random() * (double)str.length());
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    @Override
    public void process() {
        if (Class437.flag) {
            Class482.INSTANCE.sendPacket(new Class428("3.4.2", Class431.getRandomString(20)));
            Class437.flag = false;
        }
    }

    @Override
    public int getPacketId() {
        return 72;
    }
}

