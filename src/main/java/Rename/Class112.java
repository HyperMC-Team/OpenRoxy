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
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.awt.Color;

import net.minecraft.client.gui.Gui;

public class Class112
        implements Class107 {
    public float x;
    public float y;
    public float width;
    public float height;
    public float alpha;
    public final Class113 searchField = new Class113(tenacityFont20);
    private final Class139 searchAnimation = new Class140(250, 1.0).setDirection(Class141.BACKWARDS);
    public final Class97 searchType = new Class97("Type", "Configs", "Scripts");
    private final Class92 carouselButtons = new Class92("Scripts", "Configs", "Info");
    private final Class100 refreshButton = new Class100("D", "Refresh all of the cloud and local data");
    private String currentPanel;
    int ticks = 0;
    private final Class150 refreshTimer = new Class150();
    private final Class139 refreshText = new Class140(250, 1.0);

    @Override
    public void initGui() {
        this.currentPanel = this.carouselButtons.getCurrentButton();
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
        if (keyCode != 1) {
            this.searchField.keyTyped(typedChar, keyCode);
        }
    }

    @Override
    public void drawScreen(int mouseX, int mouseY) {
        boolean searching = this.searchField.isFocused() || !this.searchField.getText().equals("");
        Class148.drawRound(this.x + 0.625f, this.y + 0.625f, this.width - 1.25f, this.height - 1.25f, 5.0f, Class460.tripleColor(25, this.alpha));
        Gui.drawRect2(this.x, this.y + this.height - 4.0f, this.width, 4.0, Class460.tripleColor(25, this.alpha * this.alpha * this.alpha).getRGB());
        Color textColor = Class460.applyOpacity(Color.WHITE, this.alpha);
        tenacityBoldFont32.drawString("Tenacity", this.x + 9.5f, this.y + tenacityBoldFont32.getMiddleOfBox(this.height), textColor);
        tenacityFont18.drawString(Class1.instance.Method6(), this.x + 9.5f + tenacityBoldFont32.getStringWidth("Tenacity") - 2.0f, this.y + tenacityBoldFont32.getMiddleOfBox(this.height) - 2.5f, Class460.applyOpacity(textColor, 0.5f));
        this.searchAnimation.setDirection(this.searchField.isFocused() || !this.searchField.getText().equals("") ? Class141.FORWARDS : Class141.BACKWARDS);
        float searchAnim = this.searchAnimation.getOutput().floatValue();
        float carouselAlpha = this.alpha * (1.0f - searchAnim);
        this.carouselButtons.setAlpha(carouselAlpha);
        this.carouselButtons.setRectWidth(75.0f);
        this.carouselButtons.setRectHeight(20.5f);
        this.carouselButtons.setX(this.x + this.width / 2.0f - this.carouselButtons.getTotalWidth() / 2.0f);
        this.carouselButtons.setY(this.y + this.height / 2.0f - this.carouselButtons.getRectHeight() / 2.0f);
        this.carouselButtons.drawScreen(mouseX, mouseY);
        if (!searching) {
            this.refreshText.setDirection(this.refreshTimer.getTime() > 12000L ? Class141.BACKWARDS : Class141.FORWARDS);
            tenacityFont14.drawString(String.valueOf(Math.round((float)this.refreshTimer.getTime() / 1000.0f)), this.refreshButton.getX() - 15.0f, this.y + tenacityFont14.getMiddleOfBox(this.height), Class460.applyOpacity(-1, this.refreshText.getOutput().floatValue()));
            this.refreshButton.setAlpha(carouselAlpha);
            this.refreshButton.setX(this.carouselButtons.getX() - (this.refreshButton.getWidth() + 10.0f));
            this.refreshButton.setY(this.carouselButtons.getY() + 1.0f + this.carouselButtons.getRectHeight() / 2.0f - this.refreshButton.getHeight() / 2.0f);
            this.refreshButton.setAccentColor(Color.WHITE);
            this.refreshButton.setIconFont(iconFont20);
        }
        this.searchField.setRadius(5.0f);
        this.searchField.setFill(Class460.tripleColor(17, this.alpha));
        this.searchField.setOutline(Class460.applyOpacity(Color.WHITE, 0.0f));
        this.searchField.setHeight(this.carouselButtons.getRectHeight());
        this.searchField.setWidth(145.5f + 200.0f * searchAnim);
        float searchX = this.x + this.width - (this.searchField.getRealWidth() + 11.0f);
        this.searchField.setXPosition(Class146.interpolateFloat(searchX, this.x + this.width / 2.0f - this.searchField.getRealWidth() / 2.0f, searchAnim));
        this.searchField.setYPosition(this.y + this.height / 2.0f - this.searchField.getHeight() / 2.0f);
        this.searchField.setBackgroundText("Search");
        this.searchField.drawTextBox();
        if (!this.searchAnimation.isDone() || this.searchAnimation.finished(Class141.FORWARDS)) {
            this.searchType.setWidth(75.0f);
            this.searchType.setHeight(this.carouselButtons.getRectHeight() - 5.5f);
            this.searchType.setX(this.x + this.width - (this.searchType.getWidth() + 11.0f));
            this.searchType.setY(this.y + this.height / 2.0f - this.searchType.getHeight() / 2.0f);
            this.searchType.setAlpha(this.alpha * searchAnim);
            this.searchType.setAccentColor(Class460.applyOpacity(Color.CYAN, this.searchType.getAlpha()));
            this.searchType.drawScreen(mouseX, mouseY);
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        this.searchField.mouseClicked(mouseX, mouseY, button);
        if (this.searchField.isFocused() || !this.searchField.getText().equals("")) {
            this.searchType.mouseClicked(mouseX, mouseY, button);
            this.currentPanel = "Search";
            return;
        }
        if (this.refreshTimer.hasTimeElapsed(12000L)) {
            this.refreshButton.mouseClicked(mouseX, mouseY, button);
        }
        this.carouselButtons.mouseClicked(mouseX, mouseY, button);
        this.currentPanel = this.carouselButtons.getCurrentButton();
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
    }

    public Class92 getCarouselButtons() {
        return this.carouselButtons;
    }

    public String getCurrentPanel() {
        return this.currentPanel;
    }

    public void setCurrentPanel(String currentPanel) {
        this.currentPanel = currentPanel;
    }
}
