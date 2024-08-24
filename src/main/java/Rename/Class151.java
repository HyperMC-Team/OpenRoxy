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

public class Class151
implements Class107 {
    private boolean hovering = false;
    private boolean round = true;
    private final Class139 fadeInAnimation = new Class140(250, 1.0).setDirection(Class141.BACKWARDS);
    private String tooltip;
    private String additionalInformation;
    private float width = 150.0f;
    private float height = 40.0f;

    public Class151(String tooltip) {
        this.tooltip = tooltip;
    }

    public Class151() {
    }

    @Override
    public void initGui() {
    }

    @Override
    public void keyTyped(char typedChar, int keyCode) {
    }

    @Override
    public void drawScreen(int mouseX, int mouseY) {
        this.fadeInAnimation.setDirection(this.hovering ? Class141.FORWARDS : Class141.BACKWARDS);
        float x = mouseX - 2;
        float y = mouseY + 13;
        float fadeAnim = this.fadeInAnimation.getOutput().floatValue();
        if (this.tooltip == null || this.fadeInAnimation.finished(Class141.BACKWARDS)) {
            return;
        }
        if (this.tooltip.contains("\n")) {
            Class147.scissorStart(x - 1.5f, y - 1.5f, (this.width + 4.0f) * fadeAnim, this.height + 4.0f);
            Class148.drawRound(x - 0.75f, y - 0.75f, this.width + 1.5f, this.height + 1.5f, 3.0f, Class460.tripleColor(45, fadeAnim));
            Class148.drawRound(x, y, this.width, this.height, 2.5f, Class460.applyOpacity(Class460.tripleColor(15), fadeAnim));
            Class122<Float, Float> whPair = tenacityFont14.drawNewLineText(this.tooltip, x + 2.0f, y + 2.0f, Class460.applyOpacity(-1, fadeAnim), 3.0f);
            float additionalHeight = 0.0f;
            if (this.additionalInformation != null) {
                additionalHeight = tenacityFont14.drawWrappedText(this.additionalInformation, x + 2.0f, y + 1.5f + whPair.getSecond().floatValue(), Class460.applyOpacity(-1, fadeAnim), this.width, 3.0f);
            }
            Class147.scissorEnd();
            this.width = this.additionalInformation != null ? Math.max(150.0f, whPair.getFirst().floatValue() + 4.0f) : whPair.getFirst().floatValue() + 4.0f;
            this.height = whPair.getSecond().floatValue() + additionalHeight;
        } else {
            this.width = tenacityFont14.getStringWidth(this.tooltip) + 4.0f;
            this.height = tenacityFont14.getHeight() + 2;
            Class147.scissorStart(x - 1.5f, y - 1.5f, (this.width + 4.0f) * fadeAnim, this.height + 4.0f);
            if (this.round) {
                Class148.drawRound(x - 0.75f, y - 0.75f, this.width + 1.5f, this.height + 1.5f, 3.0f, Class460.tripleColor(45, fadeAnim));
                Class148.drawRound(x, y, this.width, this.height, 2.5f, Class460.applyOpacity(Class460.tripleColor(15), fadeAnim));
            } else {
                Class147.drawBorderedRect(x, y, this.width, this.height, 1.0f, Class460.tripleColor(15, fadeAnim).getRGB(), Class460.tripleColor(45, fadeAnim).getRGB());
            }
            tenacityFont14.drawCenteredString(this.tooltip, x + this.width / 2.0f, y + tenacityFont14.getMiddleOfBox(this.height), Class460.applyOpacity(-1, fadeAnim));
            Class147.scissorEnd();
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
    }

    @Override
    public void mouseReleased(int mouseX, int mouseY, int state) {
    }

    public void setTip(String tooltip) {
        this.tooltip = tooltip;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public void setHovering(boolean hovering) {
        this.hovering = hovering;
    }

    public boolean isHovering() {
        return this.hovering;
    }

    public void setRound(boolean round) {
        this.round = round;
    }

    public Class139 getFadeInAnimation() {
        return this.fadeInAnimation;
    }
}

