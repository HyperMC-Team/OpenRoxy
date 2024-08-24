package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.io.BufferedInputStream;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import net.minecraft.util.ResourceLocation;

public class Class165 implements Class3 {
    public static void playSound(ResourceLocation location, float volume) {
        Class163.runAsync(() -> {
            try {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(mc.getResourceManager().getResource(location).getInputStream());
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(bufferedInputStream);
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
                float range = gainControl.getMaximum() - gainControl.getMinimum();
                float gain = range * volume + gainControl.getMinimum();
                gainControl.setValue(gain);
                clip.start();
            } catch (IOException | LineUnavailableException | UnsupportedAudioFileException var8) {
                var8.printStackTrace();
            }
        });
    }
}
