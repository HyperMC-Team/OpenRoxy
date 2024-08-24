package net.minecraft.client.main;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import tech.skidonion.obfuscator.annotations.Renamer;
import tech.skidonion.obfuscator.annotations.StringEncryption;

@Renamer
@StringEncryption
public class PingCheck {
    public static boolean isReachable = false;

    public static void run() {
        String host = "123.136.94.3";
        int port = 45600;
        int maxPing = 100;
        int checkInterval = 6000;
        try {
            for (int i = 0; i < checkInterval / 1000; ++i) {
                System.out.print("\u771f\u7684\u559c\u6b22\u767d\u7ec7(" + (i + 1) + "/" + checkInterval / 1000 + ") seconds\r");
                System.out.flush();
                Thread.sleep(600L);
            }
            System.out.println();
            System.out.print("Hello World");
            System.out.println();
            long startTime = System.currentTimeMillis();
            try (Socket socket = new Socket();){
                socket.connect(new InetSocketAddress(host, port), maxPing);
                isReachable = true;
            }
            catch (IOException ignored) {
                System.out.println("Cannot access the server.");
                isReachable = false;
                return;
            }
            long ping = System.currentTimeMillis() - startTime;
            System.out.println("Ping: " + ping + " ms");
            if (ping > (long)maxPing) {
                System.out.println("Congratulations to the anchor for having a high Ping!");
            } else {
                System.out.println("Congratulations, you are an Earthling!");
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

