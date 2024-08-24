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
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.awt.Font;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class Class120
implements Class115 {
    public static final String BUG = "a";
    public static final String LIST = "b";
    public static final String BOMB = "c";
    public static final String EYE = "d";
    public static final String PERSON = "e";
    public static final String WHEELCHAIR = "f";
    public static final String SCRIPT = "g";
    public static final String SKIP_LEFT = "h";
    public static final String PAUSE = "i";
    public static final String PLAY = "j";
    public static final String SKIP_RIGHT = "k";
    public static final String SHUFFLE = "l";
    public static final String INFO = "m";
    public static final String SETTINGS = "n";
    public static final String CHECKMARK = "o";
    public static final String XMARK = "p";
    public static final String TRASH = "q";
    public static final String WARNING = "r";
    public static final String FOLDER = "s";
    public static final String LOAD = "t";
    public static final String SAVE = "u";
    public static final String UPVOTE_OUTLINE = "v";
    public static final String UPVOTE = "w";
    public static final String DOWNVOTE_OUTLINE = "x";
    public static final String DOWNVOTE = "y";
    public static final String DROPDOWN_ARROW = "z";
    public static final String PIN = "s";
    public static final String EDIT = "A";
    public static final String SEARCH = "B";
    public static final String UPLOAD = "C";
    public static final String REFRESH = "D";
    public static final String ADD_FILE = "E";
    public static final String STAR_OUTLINE = "F";
    public static final String STAR = "G";
    private static final HashMap<FontType, Map<Integer, Class119>> customFontMap = new HashMap();

    public static void setupFonts() {
        for (FontType type : FontType.values()) {
            type.setup();
            HashMap<Integer, Class119> fontSizes = new HashMap<Integer, Class119>();
            if (type.hasBold()) {
                for (int size : type.getSizes()) {
                    Class119 font = new Class119(type.fromSize(size));
                    font.setBoldFont(new Class119(type.fromBoldSize(size)));
                    fontSizes.put(size, font);
                }
            } else {
                for (int size : type.getSizes()) {
                    fontSizes.put(size, new Class119(type.fromSize(size)));
                }
            }
            customFontMap.put(type, fontSizes);
        }
    }

    private static Font getFontData(ResourceLocation location) {
        try {
            InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(location).getInputStream();
            return Font.createFont(0, is);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading font");
            return new Font("default", 0, 10);
        }
    }

    public enum FontType {
        TENACITY("tenacity", "tenacity-bold", 12, 14, 16, 18, 20, 22, 24, 26, 28, 32, 40, 80),
        TAHOMA("tahoma", "tahoma-bold", 10, 12, 14, 16, 18, 27),
        RUBIK("rubik", "rubik-bold", 13, 18),
        NEVERLOSE("neverlose", 12, 18, 22),
        ICON("icon", 16, 20, 26, 35, 40);

        private final ResourceLocation location;
        private final ResourceLocation boldLocation;
        private Font font;
        private Font boldFont;
        private final int[] sizes;

        FontType(String fontName, String boldName, int... sizes) {
            this.location = new ResourceLocation("bloodline/fonts/" + fontName + ".ttf");
            this.boldLocation = new ResourceLocation("bloodline/fonts/" + boldName + ".ttf");
            this.sizes = sizes;
        }

        FontType(String fontName, int... sizes) {
            this.location = new ResourceLocation("bloodline/fonts/" + fontName + ".ttf");
            this.boldLocation = null;
            this.sizes = sizes;
        }

        public boolean hasBold() {
            return this.boldLocation != null;
        }

        public Font fromSize(int size) {
            return this.font.deriveFont(0, size);
        }

        private Font fromBoldSize(int size) {
            return this.boldFont.deriveFont(0, size);
        }

        public void setup() {
            this.font = Class120.getFontData(this.location);
            if (this.boldLocation != null) {
                this.boldFont = Class120.getFontData(this.boldLocation);
            }
        }

        public Class119 size(int size) {
            return customFontMap.get(this).computeIfAbsent(size, k -> null);
        }

        public Class119 boldSize(int size) {
            return customFontMap.get(this).get(size).getBoldFont();
        }

        public ResourceLocation getLocation() {
            return this.location;
        }

        public ResourceLocation getBoldLocation() {
            return this.boldLocation;
        }

        public Font getFont() {
            return this.font;
        }

        public Font getBoldFont() {
            return this.boldFont;
        }

        public int[] getSizes() {
            return this.sizes;
        }
    }
}

