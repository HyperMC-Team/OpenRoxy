package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.awt.Color;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.EntityLivingBase;
import tech.skidonion.obfuscator.annotations.Renamer;
import tech.skidonion.obfuscator.annotations.StringEncryption;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
@Renamer
@StringEncryption
public class Class325
extends Class252 {
    private final Class259 postx = new Class259("PostX", 640.0, 640.0, -480.0, 0.1);
    private final Class259 posty = new Class259("PostY", 290.0, 350.0, -280.0, 0.1);
    private int kills;
    EntityLivingBase target;
    private long startTime;

    public Class325() {
        super("SessionInfo", Class263.Render);
        this.resetTimer();
    }

    @Override
    public void onDisable() {
        this.kills = 0;
    }

    @Class14
    public void onUpdate(Class50 event) {
        this.target = Class291.target;
        if (this.target.getHealth() <= 0.0f) {
            ++this.kills;
        }
    }

    @Class14
    public void onRender2D(Class38 event) {
        ScaledResolution sr = new ScaledResolution(mc);
        int x = sr.getScaledWidth() / 2 - this.postx.getValue().intValue();
        int y = sr.getScaledHeight() / 2 - this.posty.getValue().intValue();
        double width = 170.0;
        double height = 68.0;
        Class228.drawGlow(x, y, (float)width, (float)height, 6, new Color(0, 0, 0, 90));
        Class148.drawRound(x, y, (float)width, (float)height, 2.0f, new Color(0, 0, 0, 20));
        Class318.drawLine(x, y + 5, 2.0, 10.0, Class318.color(0));
        Class330.arial24.drawStringDynamic("Session", x + 21, y + 6, 1, 6);
        Class330.icon22.drawStringDynamic("s", x + 7, y + 7, 1, 6);
        Class147.drawPlayerHead(Class325.mc.thePlayer.getLocationSkin(), x + 13, y + 24, 36, 36);
        Class330.arial18.drawStringWithShadow(Class325.mc.thePlayer.getName(), x + 55, y + 28, Color.WHITE.getRGB());
        Class330.arial18.drawStringWithShadow(this.getTime(), x + 55, y + 42, Color.GRAY.getRGB());
        Class330.arial18.drawStringWithShadow("Kills: " + this.kills, x + 55, y + 52, Color.GRAY.getRGB());
    }

    private void resetTimer() {
        this.startTime = System.currentTimeMillis();
    }

    private String getTime() {
        long currentTime = System.currentTimeMillis();
        long elapsed = currentTime - this.startTime;
        int seconds = (int)(elapsed / 1000L) % 60;
        int minutes = (int)(elapsed / 60000L) % 60;
        return String.format("%02dm %02ds", minutes, seconds);
    }
}

