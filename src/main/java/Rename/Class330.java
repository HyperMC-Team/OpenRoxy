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

import java.awt.Font;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;

import net.minecraft.client.Minecraft;
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
public class Class330 {
    public static final File fold = new File(Minecraft.getMinecraft().mcDataDir, Class1.instance.Method4());
    public static File fontsDir = new File(fold, "Fonts");
    public static Class469 arial10;
    public static Class469 arial12;
    public static Class469 arial14;
    public static Class469 arial16;
    public static Class469 arial18;
    public static Class469 arial20;
    public static Class469 arial22;
    public static Class469 arial24;
    public static Class469 arial26;
    public static Class469 thin40;
    public static Class469 thin16;
    public static Class469 arial64;
    public static Class469 arial28;
    public static Class469 arial32;
    public static Class469 arial40;
    public static Class469 arial42;
    public static Class469 splash40;
    public static Class469 splash18;
    public static Class469 icon22;
    public static Class469 Tahoma12;
    public static Class469 Tahoma14;
    public static Class469 Tahoma16;
    public static Class469 Tahoma18;
    public static Class469 Tahoma20;
    public static Class469 Tahoma22;
    public static Class469 Tahoma24;
    public static Class469 Tahoma26;
    public static Class469 Tahoma28;
    public static Class469 Tahoma32;
    public static Class469 Tahoma40;
    public static Class469 Tahoma42;
    public static Class469 icontestFont35;
    public static Class469 icontestFont90;
    public static Class469 icontestFont80;
    public static Class469 icontestFont20;
    public static Class469 icontestFont40;
    public static Class469 lettuceFont20;
    public static Class469 lettuceFont24;
    public static Class469 lettuceBoldFont26;
    public static Class469 infoFontBold;
    public static Class469 titleFontBold;
    public static Class469 bold22;
    public static Class469 infoFont;
    public static Class469 titleFont;
    public static Class469 edit10;
    public static Class469 edit12;
    public static Class469 edit13;
    public static Class469 edit16;
    public static Class469 edit18;
    public static Class469 edit20;
    public static Class469 edit22;
    public static Class469 edit26;
    public static Class469 posterama12;
    public static Class469 posterama16;
    public static Class469 posterama18;
    public static Class469 posterama20;
    public static Class469 posterama26;
    public static Class469 notifications;
    public static Class469 stylesicons;
    public static Class469 opensans18;
    public static Class469 opensans20;
    public static Class469 opensans22;
    private static File fontFile;
    private static File editFontFile;
    private static File sfthinFile;
    private static File boldFile;
    private static File tahoma1File;
    private static File icontFile;
    private static File iconfontFile;
    private static File geologicaFile;
    private static File geologicaboldFile;
    private static File posteramaFile;
    private static File notificationsFile;
    private static File stylesiconsFile;
    private static File opensansFile;

    public static void downloadFont() {
        fontFile = new File(fontsDir, "font.ttf");
        editFontFile = new File(fontsDir, "edit.ttf");
        sfthinFile = new File(fontsDir, "sfthin.ttf");
        boldFile = new File(fontsDir, "bold.ttf");
        tahoma1File = new File(fontsDir, "tahoma1.ttf");
        icontFile = new File(fontsDir, "icont.ttf");
        iconfontFile = new File(fontsDir, "iconfont.ttf");
        geologicaFile = new File(fontsDir, "geologica.ttf");
        geologicaboldFile = new File(fontsDir, "geologica-bold.ttf");
        posteramaFile = new File(fontsDir, "fz.ttf");
        notificationsFile = new File(fontsDir, "notifications.ttf");
        stylesiconsFile = new File(fontsDir, "stylesicons.ttf");
        opensansFile = new File(fontsDir, "opensans.ttf");
        Class162.print("Download Fonts...");
        if (!fontFile.exists()) {
            Class161.download("http://123.136.94.3/font.ttf", fontFile);
        }
        if (!editFontFile.exists()) {
            Class161.download("http://123.136.94.3/edit.ttf", editFontFile);
        }
        if (!sfthinFile.exists()) {
            Class161.download("http://123.136.94.3/sfthin.ttf", sfthinFile);
        }
        if (!boldFile.exists()) {
            Class161.download("http://123.136.94.3/bold.ttf", boldFile);
        }
        if (!tahoma1File.exists()) {
            Class161.download("http://123.136.94.3/tahoma1.ttf", tahoma1File);
        }
        if (!icontFile.exists()) {
            Class161.download("http://123.136.94.3/icont.ttf", icontFile);
        }
        if (!iconfontFile.exists()) {
            Class161.download("http://123.136.94.3/iconfont.ttf", iconfontFile);
        }
        if (!geologicaFile.exists()) {
            Class161.download("http://123.136.94.3/geologica.ttf", geologicaFile);
        }
        if (!geologicaboldFile.exists()) {
            Class161.download("http://123.136.94.3/geologica-bold.ttf", geologicaboldFile);
        }
        if (!posteramaFile.exists()) {
            Class161.download("http://123.136.94.3/fz.ttf", posteramaFile);
        }
        if (!notificationsFile.exists()) {
            Class161.download("http://123.136.94.3/notifications.ttf", notificationsFile);
        }
        if (!stylesiconsFile.exists()) {
            Class161.download("http://123.136.94.3/stylesicons.ttf", stylesiconsFile);
        }
        if (!opensansFile.exists()) {
            Class161.download("http://123.136.94.3/opensans.ttf", opensansFile);
        }
    }

    public static void init() {
        if (!fold.exists()) {
            fontsDir.mkdir();
        }
        if (!fontsDir.exists()) {
            fontsDir.mkdir();
        }
        edit10 = new Class469(Class330.getFont(editFontFile, 10.0f));
        edit12 = new Class469(Class330.getFont(editFontFile, 12.0f));
        edit13 = new Class469(Class330.getFont(editFontFile, 13.0f));
        edit16 = new Class469(Class330.getFont(editFontFile, 16.0f));
        edit18 = new Class469(Class330.getFont(editFontFile, 18.0f));
        edit20 = new Class469(Class330.getFont(editFontFile, 20.0f));
        edit22 = new Class469(Class330.getFont(editFontFile, 22.0f));
        edit26 = new Class469(Class330.getFont(editFontFile, 26.0f));
        thin40 = new Class469(Class330.getFont(sfthinFile, 40.0f));
        thin16 = new Class469(Class330.getFont(sfthinFile, 16.0f));
        arial10 = new Class469(Class330.getFont(fontFile, 10.0f));
        arial12 = new Class469(Class330.getFont(fontFile, 12.0f));
        arial14 = new Class469(Class330.getFont(fontFile, 14.0f));
        arial16 = new Class469(Class330.getFont(fontFile, 16.0f));
        arial18 = new Class469(Class330.getFont(fontFile, 18.0f));
        arial20 = new Class469(Class330.getFont(fontFile, 20.0f));
        arial22 = new Class469(Class330.getFont(fontFile, 22.0f));
        arial24 = new Class469(Class330.getFont(fontFile, 24.0f));
        arial26 = new Class469(Class330.getFont(fontFile, 26.0f));
        arial28 = new Class469(Class330.getFont(fontFile, 28.0f));
        arial32 = new Class469(Class330.getFont(fontFile, 32.0f));
        arial40 = new Class469(Class330.getFont(fontFile, 40.0f));
        arial42 = new Class469(Class330.getFont(fontFile, 42.0f));
        splash40 = new Class469(Class330.getFont(fontFile, 40.0f));
        splash18 = new Class469(Class330.getFont(fontFile, 18.0f));
        arial64 = new Class469(Class330.getFont(fontFile, 64.0f));
        Tahoma12 = new Class469(Class330.getFont(fontFile, 12.0f));
        Tahoma14 = new Class469(Class330.getFont(fontFile, 14.0f));
        Tahoma16 = new Class469(Class330.getFont(fontFile, 16.0f));
        Tahoma18 = new Class469(Class330.getFont(fontFile, 18.0f));
        Tahoma20 = new Class469(Class330.getFont(fontFile, 20.0f));
        Tahoma22 = new Class469(Class330.getFont(fontFile, 22.0f));
        Tahoma24 = new Class469(Class330.getFont(fontFile, 24.0f));
        Tahoma26 = new Class469(Class330.getFont(fontFile, 26.0f));
        Tahoma28 = new Class469(Class330.getFont(fontFile, 28.0f));
        Tahoma32 = new Class469(Class330.getFont(fontFile, 32.0f));
        Tahoma40 = new Class469(Class330.getFont(fontFile, 40.0f));
        Tahoma42 = new Class469(Class330.getFont(fontFile, 42.0f));
        bold22 = new Class469(Class330.getFont(boldFile, 18.0f));
        titleFontBold = new Class469(Class330.getFont(tahoma1File, 18.0f));
        infoFontBold = new Class469(Class330.getFont(tahoma1File, 15.0f));
        titleFont = new Class469(Class330.getFont(tahoma1File, 19.0f));
        infoFont = new Class469(Class330.getFont(tahoma1File, 12.0f));
        icontestFont90 = new Class469(Class330.getFont(icontFile, 90.0f));
        icontestFont80 = new Class469(Class330.getFont(icontFile, 80.0f));
        icontestFont35 = new Class469(Class330.getFont(icontFile, 35.0f));
        icon22 = new Class469(Class330.getFont(iconfontFile, 22.0f));
        icontestFont20 = new Class469(Class330.getFont(icontFile, 20.0f));
        icontestFont40 = new Class469(Class330.getFont(icontFile, 40.0f));
        lettuceFont20 = new Class469(Class330.getFont(geologicaFile, 20.0f));
        lettuceFont24 = new Class469(Class330.getFont(geologicaFile, 24.0f));
        lettuceBoldFont26 = new Class469(Class330.getFont(geologicaboldFile, 26.0f));
        posterama12 = new Class469(Class330.getFont(posteramaFile, 12.0f));
        posterama16 = new Class469(Class330.getFont(posteramaFile, 16.0f));
        posterama18 = new Class469(Class330.getFont(posteramaFile, 18.0f));
        posterama20 = new Class469(Class330.getFont(posteramaFile, 20.0f));
        posterama26 = new Class469(Class330.getFont(posteramaFile, 26.0f));
        notifications = new Class469(Class330.getFont(notificationsFile, 18.0f));
        stylesicons = new Class469(Class330.getFont(stylesiconsFile, 50.0f));
        opensans18 = new Class469(Class330.getFont(opensansFile, 18.0f));
        opensans20 = new Class469(Class330.getFont(opensansFile, 20.0f));
        opensans22 = new Class469(Class330.getFont(opensansFile, 22.0f));
    }

    private static Font getFont(File file, float fontSize) {
        try {
            InputStream inputStream = Files.newInputStream(file.toPath());
            return Font.createFont(0, inputStream).deriveFont(fontSize);
        }
        catch (Exception e) {
            Class162.print(e);
            return new Font("default", 0, 10);
        }
    }
}

