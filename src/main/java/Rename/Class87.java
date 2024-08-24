package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.Renamer;
import tech.skidonion.obfuscator.annotations.StringEncryption;

@Renamer
@StringEncryption
@NativeObfuscation
public class Class87
        extends GuiScreen {
    private final File files;
    private String status;
    private final boolean launched;
    private boolean darkTheme;
    private boolean falseError;
    private float fraction;
    public int alpha;
    private final int hwidy;
    private final Color black;
    private final Color blueish;
    private final Color blue;
    public static String uid;
    private float hHeight;
    private float hWidth;
    private float errorBoxHeight;
    Class85 button;
    Class85 hwid;
    Class86 field;

    public Class87() {
        this.files = new File(Minecraft.getMinecraft().mcDataDir, "usename.lol");
        this.launched = true;
        this.darkTheme = false;
        this.alpha = 0;
        this.hwidy = 65;
        this.black = new Color(40, 46, 51);
        this.blueish = new Color(0, 150, 135);
        this.blue = new Color(-13930063);
        this.hHeight = 540.0f;
        this.hWidth = 960.0f;
        this.errorBoxHeight = 0.0f;
        this.button = new Class85(0, (int)this.hWidth - 70, (int)(this.hHeight + 5.0f), 140, 30, "LogIn");
        this.hwid = new Class85(1, (int)this.hWidth - 70, (int)(this.hHeight - (float)this.hwidy), 140, 20, "");
        this.status = "Please Login";
    }

    @Override
    public void initGui() {
        if (!Class262.getModule(Class272.class).isState()) {
            Class262.getModule(Class272.class).setState(true);
        }
        Display.setTitle("Bloodline - Not logged in");
        this.buttonList.add(this.button);
        this.buttonList.add(this.hwid);
        this.field = new Class86(1, this.mc.fontRendererObj, (int)this.hWidth - 70, (int)this.hHeight - 35, 140, 30, "ShaBi");
        this.alpha = 100;
        this.darkTheme = true;
        this.tryAutoLogin();
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        ScaledResolution sr = new ScaledResolution(this.mc);
        int h = sr.getScaledHeight();
        int w = sr.getScaledWidth();
        Class130.render(w, h);
        GlStateManager.disableCull();
        GL11.glBegin(7);
        GL11.glVertex2f(-1.0f, -1.0f);
        GL11.glVertex2f(-1.0f, 1.0f);
        GL11.glVertex2f(1.0f, 1.0f);
        GL11.glVertex2f(1.0f, -1.0f);
        GL11.glEnd();
        GL20.glUseProgram(0);
        if (this.launched && this.darkTheme && this.fraction != 1.0049993f) {
            this.fraction = 1.0049993f;
        }
        if (this.darkTheme && this.fraction < 1.0f) {
            this.fraction += 0.015f;
        } else if (!this.darkTheme && this.fraction > 0.0f) {
            this.fraction -= 0.015f;
        }
        if (mouseX <= 20 && mouseY <= 20 && this.alpha < 255) {
            ++this.alpha;
        } else if (this.alpha > 100) {
            --this.alpha;
        }
        Color white = Color.WHITE;
        Color shitGray = new Color(150, 150, 150);
        this.button.setColor(this.interpolateColor(this.button.hovered(mouseX, mouseY) ? this.blue.brighter() : this.blue, this.button.hovered(mouseX, mouseY) ? this.blueish.brighter() : this.blueish, this.fraction));
        this.field.setColor(this.interpolateColor(white, this.black, this.fraction));
        this.field.setTextColor(this.interpolateColor(shitGray, white, this.fraction));
        this.button.updateCoordinates(this.hWidth - 70.0f, this.hHeight + 5.0f);
        this.hwid.updateCoordinates(this.hWidth - 70.0f, this.hHeight + 30.0f);
        this.field.updateCoordinates(this.hWidth - 70.0f, this.hHeight - 35.0f);
        int scaledWidthScaled = sr.getScaledWidth();
        int scaledHeightScaled = sr.getScaledHeight();
        this.hHeight += ((float)scaledHeightScaled / 2.0f - this.hHeight) * 0.02f;
        this.hWidth = (float)scaledWidthScaled / 2.0f;
        Gui.drawRect(0, 0, scaledWidthScaled, scaledHeightScaled, new Color(0, 0, 0, 155).getRGB());
        Color vis = new Color(this.interpolateColor(this.blue, this.blueish, this.fraction));
        Class230.drawBorderedRect(this.hWidth - 90.0f, this.hHeight - 55.0f, this.hWidth + 90.0f, this.hHeight + 55.0f, 0.3f, new Color(0, 201, 208, 179).getRGB(), new Color(0, 167, 255, 50).getRGB());
        Class115.tenacityBoldFont32.drawString("Bloodline", this.hWidth - Class115.tenacityBoldFont32.getStringWidth("Bloodline") / 2.0f + 12.0f, this.hHeight - 90.0f, this.interpolateColor(this.blue, this.blueish, this.fraction));
        Class115.tenacityFont16.drawString("GetHWID", this.hWidth - Class115.tenacityBoldFont32.getStringWidth("GetHWID") / 2.0f + 12.0f, this.hHeight + 40.0f, this.interpolateColor(this.blue, this.blueish, this.fraction));
        Class330.stylesicons.drawString("L", this.hWidth - 72.0f, this.hHeight - 90.0f, this.interpolateColor(this.blue, this.blueish, this.fraction));
        this.button.drawButton(this.mc, mouseX, mouseY);
        if (this.status.startsWith("ShaBi") || this.status.startsWith("Initializing") || this.status.startsWith("Logging")) {
            Class330.posterama16.drawString(this.status, this.hWidth - (float) Class330.posterama16.getStringWidth(this.status) / 2.0f, this.hHeight + 45.0f, this.interpolateColor(new Color(150, 150, 150), white, this.fraction));
            this.errorBoxHeight = 0.0f;
        } else if (this.status.equals("Success")) {
            this.errorBoxHeight += (10.0f - this.errorBoxHeight) * 0.01f;
            Class230.drawBorderedRect(this.hWidth - (float) Class330.posterama16.getStringWidth(this.status) / 2.0f - 10.0f, this.errorBoxHeight, this.hWidth + (float) Class330.posterama16.getStringWidth(this.status) / 2.0f + 10.0f, this.errorBoxHeight + 12.0f, 1.0f, new Color(170, 253, 126).getRGB(), this.interpolateColor(new Color(232, 255, 213), new Color(232, 255, 213).darker().darker(), this.fraction));
            Class330.posterama16.drawString(this.status, this.hWidth - (float) Class330.posterama16.getStringWidth(this.status) / 2.0f, this.errorBoxHeight + 7.0f - (float) Class330.posterama16.getHeight() / 2.0f, new Color(201, 255, 167).darker().getRGB(), true);
        } else {
            this.errorBoxHeight += (10.0f - this.errorBoxHeight) * 0.01f;
            Class230.drawBorderedRect(this.hWidth - (float) Class330.posterama16.getStringWidth(this.status) / 2.0f - 10.0f, this.errorBoxHeight, this.hWidth + (float) Class330.posterama16.getStringWidth(this.status) / 2.0f + 10.0f, this.errorBoxHeight + 12.0f, 1.0f, -664863, this.interpolateColor(new Color(-465432), new Color(-465432).darker().darker(), this.fraction));
            Class330.posterama16.drawString(this.status, this.hWidth - (float) Class330.posterama16.getStringWidth(this.status) / 2.0f, this.errorBoxHeight + 7.0f - (float) Class330.posterama16.getHeight() / 2.0f, -1347963, true);
        }
        this.field.drawTextBox();
        Class330.posterama18.drawString("Made by KuChaZi", this.hWidth - (float) Class330.posterama18.getStringWidth("Made by KuChaZi") / 2.0f, scaledHeightScaled - Class330.posterama18.getHeight() - 4, new Color(150, 150, 150).getRGB());
        if (!this.button.enabled) {
            this.status = "Success";
        }
        if (this.falseError) {
            this.mouseClicked(sr.getScaledWidth() / 2, sr.getScaledHeight() / 2 + 20, 0);
            this.falseError = false;
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        if (button == this.button) {
            if (this.field.getText().isEmpty()) {
                this.status = "ID不能为空";
                this.mc.displayGuiScreen(new Class131());
                Class165.playSound(new ResourceLocation("bloodline/sb.wav"), 0.9f);
                System.out.println("2222222");
            }
          //  this.performLogin(this.field.getText());
        } else if (button == this.hwid) {
            this.copyHwidToClipboard();
            this.status = "HWID已复制";
        }
    }

    private void tryAutoLogin() {
        if (this.files.exists()) {
            try {
                String savedUid = new String(Files.readAllBytes(this.files.toPath()));
                this.field.setText(savedUid);
                Class162.print("正在自动登录...");
              //  this.performLogin(savedUid);
            }
            catch (IOException e) {
                Class162.print("登录失败,(((" + e.getMessage());
            }
        }
    }

    @NativeObfuscation.Inline
    private void performLogin(String uid) {
    }

    public void handleSuccessfulLogin() throws IOException, InterruptedException {
    }

    public void handleFailedLogin() throws InterruptedException {
        this.status = "验证中...";
        Thread.sleep(1500L);
        this.mc.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("random.orb"), -1.0f));
        this.status = "验证失败";
    }

    public void handleError(Exception e) {
        this.mc.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("random.orb"), -1.0f));
        this.status = "连接错误";
        this.mc.shutdown();
        e.printStackTrace();
    }

    private void copyHwidToClipboard() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        StringSelection selection = new StringSelection(this.getHwid());
        clipboard.setContents(selection, selection);
    }

    @NativeObfuscation.Inline
    public String getHwid() {
        try {
            String main = System.getenv("COMPUTERNAME") + System.getenv("USERDOMAIN") + System.getenv("USERNAME");
            StringBuilder builder = new StringBuilder();
            for (byte b : MessageDigest.getInstance("MD5").digest(main.getBytes())) {
                builder.append(Integer.toHexString(b & 0xFF | 0x100), 1, 3);
            }
            return builder.toString();
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "Error";
        }
    }

    private int interpolateColor(Color startColor, Color endColor, float fraction) {
        int red = (int)((float)startColor.getRed() + (float)(endColor.getRed() - startColor.getRed()) * fraction);
        int green = (int)((float)startColor.getGreen() + (float)(endColor.getGreen() - startColor.getGreen()) * fraction);
        int blue = (int)((float)startColor.getBlue() + (float)(endColor.getBlue() - startColor.getBlue()) * fraction);
        return new Color(red, green, blue).getRGB();
    }

    @Override
    protected void keyTyped(char typedChar, int keyCode) throws IOException {
        if (keyCode == 1) {
            this.status = "null";
        } else {
            this.field.textboxKeyTyped(typedChar, keyCode);
        }
    }

    @Override
    public void updateScreen() {
        this.field.updateCursorCounter();
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) {
        try {
            super.mouseClicked(mouseX, mouseY, mouseButton);
        }
        catch (IOException ioexception) {
            ioexception.printStackTrace();
        }
        this.field.mouseClicked(mouseX, mouseY, mouseButton);
    }

    @Override
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}
