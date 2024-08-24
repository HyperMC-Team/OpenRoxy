package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import tech.skidonion.obfuscator.annotations.StringEncryption;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
@StringEncryption
public class Class326
extends Class252 {
    private final Class257 mode = new Class257("Mode", "Text", "Text", "Test", "Test2", "Logo", "Xylitol", "Naven");
    private final Class254 username = new Class254("UserName", true);
    private final Class254 version = new Class254("Version", true);
    private final Class254 times = new Class254("Time", true);
    private final Class254 fpss = new Class254("FPS", true);
    private final Class254 change = new Class254("Change", false);
    private long lastUpdateTime = 0L;
    private int i = 0;

    public Class326() {
        super("Class326", Class263.Render);
        this.username.addParent(this.mode, m -> this.mode.is("Naven"));
        this.version.addParent(this.mode, m -> this.mode.is("Naven"));
        this.times.addParent(this.mode, m -> this.mode.is("Naven"));
        this.fpss.addParent(this.mode, m -> this.mode.is("Naven"));
        this.change.addParent(this.mode, s -> this.mode.is("Xylitol"));
    }

    @Class14
    public void onRender2D(Class38 event) {
        if (this.isNull()) {
            return;
        }
        this.onRender();
    }

    @Class14
    public void onUpdate(Class50 e) {
        String n = Class318.name();
        long currentTime = System.currentTimeMillis();
        if (currentTime - this.lastUpdateTime >= 900L) {
            this.i = this.i >= n.length() ? 0 : ++this.i;
            this.lastUpdateTime = currentTime;
        }
    }

    private void onRender() {
        Class123<Color, Color> clientColors = Class318.getClientColors();
        switch (this.mode.getMode()) {
            case "Text": {
                float xVal = 6.0f;
                float yVal = 6.0f;
                float versionWidth = Class115.tenacityFont16.getStringWidth(Class1.instance.Method6() + "(" + Class87.uid + ")");
                float versionX = xVal + Class115.tenacityBoldFont40.getStringWidth(Class318.name());
                float width = versionX + versionWidth - xVal;
                Class465.resetColor();
                Class229.applyGradientHorizontal(xVal, yVal, width, 20.0f, 1.0f, clientColors.getFirst(), clientColors.getSecond(), () -> {
                    Class465.setAlphaLimit(0.0f);
                    Class115.tenacityBoldFont40.drawString(Class318.name(), xVal, yVal, 0);
                    Class115.tenacityFont16.drawString(Class1.instance.Method6() + "(" + Class87.uid + ")", versionX, yVal, 0);
                });
                break;
            }
            case "Test": {
                float x = 5.0f;
                float y = 7.0f;
                String clientName = Class318.name();
                Class468.drawRound(x, y, Class330.posterama18.getStringWidth(Class318.clientName.getString()) + 5, 14.0f, 1.0f, new Color(0, 0, 0, 130));
                Class468.drawRound(x + (float) Class330.edit20.getStringWidth(clientName) + 12.0f, y, Class330.edit16.getStringWidth("Class273") + 15, 14.0f, 1.0f, new Color(0, 0, 0, 130));
                Class468.drawRound(x + (float) Class330.edit20.getStringWidth(clientName) + (float) Class330.edit16.getStringWidth("Class273") + 33.0f, y, Class330.edit16.getStringWidth("Fps") + 15, 14.0f, 1.0f, new Color(0, 0, 0, 130));
                Class330.posterama16.drawStringWithShadow(Class318.clientName.getString(), x + 5.0f, y + 6.0f, Class318.color(0).getRGB());
                Class330.edit16.drawStringWithShadow("Class273", x + (float) Class330.edit20.getStringWidth(clientName) + 12.0f, y + 4.0f, Color.WHITE.getRGB());
                Class468.circle(x + (float) Class330.edit20.getStringWidth(clientName) + 10.0f + (float) Class330.edit16.getStringWidth("Class273") + 6.0f, y + 3.0f, 8.0, 360.0, false, new Color(0, 0, 0, 70));
                Class468.circle(x + (float) Class330.edit20.getStringWidth(clientName) + 10.0f + (float) Class330.edit16.getStringWidth("Class273") + 6.0f, y + 3.0f, 8.0, this.calculateBPS() / 10.0 * 360.0, false, Color.WHITE);
                Class330.edit16.drawStringWithShadow("Fps", x + (float) Class330.edit20.getStringWidth(clientName) + (float) Class330.edit16.getStringWidth("Class273") + 35.0f, y + 4.0f, Color.WHITE.getRGB());
                Class468.circle(x + (float) Class330.edit20.getStringWidth(clientName) + (float) Class330.edit16.getStringWidth("Class273") + 35.0f + (float) Class330.edit16.getStringWidth("Fps") + 3.0f, y + 3.0f, 8.0, 360.0, false, new Color(0, 0, 0, 70));
                Class468.circle(x + (float) Class330.edit20.getStringWidth(clientName) + (float) Class330.edit16.getStringWidth("Class273") + 35.0f + (float) Class330.edit16.getStringWidth("Fps") + 3.0f, y + 3.0f, 8.0, (double)Minecraft.getDebugFPS() * 0.8, false, Color.WHITE);
                break;
            }
            case "Test2": {
                Long dNow = new Date().getTime();
                SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
                String Time = ft.format(dNow);
                String clientName = Class318.name();
                String ip = Class213.getIp().toLowerCase().contains("nyaproxy") ? "NyaProxy" : Class213.getIp();
                String formattedClientName = clientName + ChatFormatting.WHITE;
                String watermark = formattedClientName + " | " + Class87.uid + " | " + Time + " | " + ip;
                double watermarkWidth = Class330.edit20.getStringWidth(watermark);
                float x = 5.0f;
                float y = 5.0f;
                Class228.drawGlow((int)x, (int)(y + 2.0f), (int)(watermarkWidth + 10.0), 20.0f, 8, new Color(0, 0, 0, 100));
                Class330.edit20.drawStringWithShadow(watermark, x + 5.0f, y + 8.0f, Class318.color(2).getRGB());
                break;
            }
            case "Xylitol": {
                String username = Class87.uid;
                String n = Class318.name();
                StringBuilder nm = new StringBuilder(n);
                nm.delete(this.i, 4);
                float userwidth = Class330.arial16.getStringWidth(username) + 6;
                float iconwidth = Class330.icon22.getStringWidth("t") + 12;
                float namewidth = Class330.arial24.getStringWidth(this.change.isEnabled() ? nm.toString() : n);
                float allwidth = namewidth + iconwidth;
                float maxwidth = Math.max(allwidth, userwidth);
                Class225.blurArea(6.0f, 6.0f, maxwidth + 6.0f, 36.8f, 22.0f);
                Class468.drawRound(6.0f, 6.0f, maxwidth, 30.0f, 2.0f, new Color(20, 20, 20, 100));
                Class318.drawLine(6.0, 11.0, 2.0, 8.0, Class318.color(0));
                Class330.icon22.drawStringDynamic("t", 12.0, 12.0);
                Class330.arial24.drawStringDynamic(this.change.isEnabled() ? nm.toString() : n, maxwidth - (float) Class330.arial24.getStringWidth(this.change.isEnabled() ? nm.toString() : n) + 4.0f, 11.0);
                Class330.arial16.drawCenteredString(username, 6.0f + maxwidth / 2.0f, 25.0f, Color.WHITE.getRGB());
                Class228.drawGlow(6.0f, 6.0f, maxwidth, 30.0f, 12, new Color(0, 0, 0, 20));
                break;
            }
            case "Naven": {
                float x = 5.0f;
                float y = 7.0f;
                String CLIENTNAME = Class318.name().toUpperCase();
                String CLIENTVERSION = Class1.instance.Method6();
                Long dNow = new Date().getTime();
                SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
                String Time = ft.format(dNow);
                Object user = this.username.isEnabled() ? " | " + Class87.uid : "";
                Object ver = this.version.isEnabled() ? " | " + CLIENTVERSION : "";
                Object time = this.times.isEnabled() ? " | " + Time : "";
                Object fps = this.fpss.isEnabled() ? " | " + Minecraft.getDebugFPS() + " FPS" : "";
                String water = user + (String)ver + fps + time;
                double waterwidth = Class330.opensans18.getStringWidth((String)user) + Class330.opensans18.getStringWidth((String)ver) + Class330.opensans18.getStringWidth((String)time) + Class330.opensans18.getStringWidth((String)fps);
                Class224.drawRoundedRect(x, y - 1.6f, waterwidth + (double) Class330.opensans20.getStringWidth(CLIENTNAME) + 12.0, y + 18.0f, 10.0, Integer.MIN_VALUE);
                Class224.drawRoundedRect(x, y, x, y, 0.0, new Color(255, 255, 255, 255).getRGB());
                Class228.drawGlow(x, y, (float)(waterwidth + (double) Class330.arial22.getStringWidth(CLIENTNAME) + 7.0), 20.0f, 12, new Color(25, 25, 25, 174));
                Class468.drawRound(x + 2.0f, y + 9.0f - 10.0f, x + (float) Class330.opensans20.getStringWidth(CLIENTNAME) + 160.0f, 2.5f, 2.0f, new Color(160, 42, 42));
                Class330.opensans20.drawString(CLIENTNAME, x + 4.0f, y + 4.0f, Class318.color(0).getRGB());
                Class330.opensans18.drawString(water, x + (float) Class330.opensans20.getStringWidth(CLIENTNAME) + 4.0f, y + 4.0f, new Color(255, 255, 255).getRGB());
                break;
            }
            case "Logo": {
                Class465.drawImageTest(new ResourceLocation("bloodline/icon/djh.png"), 0, 0, 107, 142);
            }
        }
    }

    private double calculateBPS() {
        double bps = Math.hypot(Class326.mc.thePlayer.posX - Class326.mc.thePlayer.prevPosX, Class326.mc.thePlayer.posZ - Class326.mc.thePlayer.prevPosZ) * (double) Class326.mc.timer.timerSpeed * 20.0;
        return (double)Math.round(bps * 100.0) / 100.0;
    }
}

