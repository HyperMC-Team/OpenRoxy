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
import java.util.function.BiConsumer;

public abstract class Class98
        implements Class107 {
    private float x;
    private float y;
    private float width;
    private float height;
    private float alpha;
    private final String title;
    private BiConsumer<String, String> uploadAction;
    private Class114.TriConsumer<String, String, String> triUploadAction;

    @Override
    public void drawScreen(int mouseX, int mouseY) {
        Class148.drawRound(this.x, this.y, this.width, this.height, 5.0f, Class460.tripleColor(37, this.alpha));
        tenacityBoldFont40.drawString(this.title, this.x + 5.0f, this.y + 3.0f, this.getTextColor());
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        if (!Class465.isHovering(this.x, this.y, this.width, this.height, mouseX, mouseY)) {
            Class1.instance.Method14().displayForm(null);
        }
    }

    public float getSpacing() {
        return 8.0f;
    }

    public Color getTextColor() {
        return Class460.applyOpacity(Color.WHITE, this.alpha);
    }

    public Color getAccentColor() {
        return Class460.applyOpacity(Color.CYAN, this.alpha);
    }

    public abstract void clear();

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getWidth() {
        return this.width;
    }

    public float getHeight() {
        return this.height;
    }

    public float getAlpha() {
        return this.alpha;
    }

    public String getTitle() {
        return this.title;
    }

    public BiConsumer<String, String> getUploadAction() {
        return this.uploadAction;
    }

    public Class114.TriConsumer<String, String, String> getTriUploadAction() {
        return this.triUploadAction;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public void setUploadAction(BiConsumer<String, String> uploadAction) {
        this.uploadAction = uploadAction;
    }

    public void setTriUploadAction(Class114.TriConsumer<String, String, String> triUploadAction) {
        this.triUploadAction = triUploadAction;
    }

    public Class98(String title) {
        this.title = title;
    }
}
