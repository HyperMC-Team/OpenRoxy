package net.minecraft.client.main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.Objects;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import tech.skidonion.obfuscator.annotations.StringEncryption;

@StringEncryption
public class Progress
extends JFrame {
    public static boolean done = false;

    public Progress() {
        this.setSize(860, 576);
        this.setAlwaysOnTop(true);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setUndecorated(true);
        ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/assets/minecraft/bloodline/szy.png")));
        JLabel backgroundLabel = new JLabel(backgroundImage);
        this.add((Component)backgroundLabel, "Center");
        this.setVisible(true);
        for (int i = 0; i <= 100; ++i) {
            try {
                Thread.sleep(20L);
                continue;
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.setVisible(false);
    }
}

