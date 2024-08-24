package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.awt.Color;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
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
public class Class323
extends Class252 {
    private int maxString = 0;
    private final Map<Integer, Integer> potionMaxDurations = new HashMap<Integer, Integer>();
    private final Class471 widthanimation = new Class471();
    private final Class471 heightanimation = new Class471();
    private final Class464 animation = new Class464(200, 1.0, 1.3f);
    List<PotionEffect> effects = new ArrayList<PotionEffect>();

    public Class323() {
        super("Class323", Class263.Render);
    }

    public int getHieght() {
        int h = Class323.mc.thePlayer.getActivePotionEffects().size() == 1 ? 48 : 38;
        return h * Class323.mc.thePlayer.getActivePotionEffects().size();
    }

    @Class14
    public void onRender2D(Class38 event) {
        this.effects = Class323.mc.thePlayer.getActivePotionEffects().stream().sorted(Comparator.comparingInt(it -> (int) Class120.tenacityFont18.getStringWidth(this.get(it)))).collect(Collectors.toList());
        int x = 5;
        int y2 = 350;
        int offsetX = 21;
        int offsetY = 14;
        int i2 = 16;
        ArrayList<Integer> needRemove = new ArrayList<Integer>();
        for (Map.Entry<Integer, Integer> entry : this.potionMaxDurations.entrySet()) {
            if (Class323.mc.thePlayer.getActivePotionEffect(Potion.potionTypes[entry.getKey()]) != null) continue;
            needRemove.add(entry.getKey());
        }
        Iterator iterator = needRemove.iterator();
        while (iterator.hasNext()) {
            int id = (Integer)(iterator.next());
            this.potionMaxDurations.remove(id);
        }
        for (PotionEffect effect : this.effects) {
            if (this.potionMaxDurations.containsKey(effect.getPotionID()) && this.potionMaxDurations.get(effect.getPotionID()) >= effect.getDuration()) continue;
            this.potionMaxDurations.put(effect.getPotionID(), effect.getDuration());
        }
        float width = !this.effects.isEmpty() ? Math.max(50.0f + Class120.tenacityFont18.getStringWidth(this.get(this.effects.get(this.effects.size() - 1))), 60.0f + Class120.tenacityFont18.getStringWidth(this.get(this.effects.get(this.effects.size() - 1)))) : 0.0f;
        float height = this.effects.size() * 25;
        this.widthanimation.animate(width, 20);
        this.heightanimation.animate(height, 20);
        if (Class323.mc.currentScreen instanceof GuiChat && this.effects.isEmpty()) {
            this.animation.setDirection(Class473.FORWARDS);
        } else if (!(Class323.mc.currentScreen instanceof GuiChat)) {
            this.animation.setDirection(Class473.BACKWARDS);
        }
        Class147.scaleStart(x + 50, y2 + 15, (float)this.animation.getOutput());
        Class120.tenacityFont18.drawStringWithShadow("Potion Example", (float)x + 52.0f - Class120.tenacityFont18.getStringWidth("Potion Example") / 2.0f, (float)(y2 + 18 - Class120.tenacityFont18.getHeight() / 2), new Color(255, 255, 255, 80).getRGB());
        Class147.scaleEnd();
        if (this.effects.isEmpty()) {
            this.maxString = 0;
        }
        if (!this.effects.isEmpty()) {
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            GlStateManager.disableLighting();
            int l2 = 24;
            Class330.arial24.drawStringWithShadow("PotionList", x + 6, y2 - 14, Class318.color(0).getRGB());
            Class468.drawRound(x, y2 - 20, this.widthanimation.getOutput() - 10.0f, this.getHieght(), 3.0f, new Color(25, 25, 25, 30));
            Class228.drawGlow(x, y2 - 20, this.widthanimation.getOutput() - 10.0f, this.getHieght(), 6, new Color(25, 25, 25, 90));
            Class468.drawRound(x, y2 - 13, 2.0f, 7.0f, 0.0f, Class318.getClientColors().getFirst());
            for (PotionEffect potioneffect : this.effects) {
                Potion potion = Potion.potionTypes[potioneffect.getPotionID()];
                GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
                if (potion.hasStatusIcon()) {
                    mc.getTextureManager().bindTexture(new ResourceLocation("textures/gui/container/inventory.png"));
                    int i1 = potion.getStatusIconIndex();
                    GlStateManager.enableBlend();
                    Class323.mc.ingameGUI.drawTexturedModalRect(x + offsetX - 17, y2 + i2 - offsetY + 2, i1 % 8 * 18, 198 + i1 / 8 * 18, 18, 18);
                }
                float potionDurationRatio = (float)potioneffect.getDuration() / (float)(this.potionMaxDurations.get(potioneffect.getPotionID()) != null ? this.potionMaxDurations.get(potioneffect.getPotionID()) : 1);
                String s2 = Potion.getDurationString(potioneffect);
                String s1 = this.get(potioneffect);
                Class120.tenacityFont18.drawStringWithShadow(s1, (float)(x + offsetX + 7), (float)(y2 + i2 - offsetY + 2), -1);
                Class120.tenacityFont18.drawStringWithShadow(s2, (float)(x + offsetX + 7), (float)(y2 + i2 + 11 - offsetY + 2), -1);
                Class468.circle(x + offsetX - 20, y2 + i2 - offsetY - 1, 24.0, 360.0, false, new Color(0, 0, 0, 70));
                Class468.circle(x + offsetX - 20, y2 + i2 - offsetY - 1, 24.0, potionDurationRatio * 360.0f, false, Color.white);
                i2 = (int)((double)i2 + (double)l2 * 1.2);
                if (this.maxString >= Class323.mc.fontRendererObj.getStringWidth(s1)) continue;
                this.maxString = Class323.mc.fontRendererObj.getStringWidth(s1);
            }
        }
    }

    private String get(PotionEffect potioneffect) {
        Potion potion = Potion.potionTypes[potioneffect.getPotionID()];
        String s1 = I18n.format(potion.getName());
        s1 = s1 + " " + this.intToRomanByGreedy(potioneffect.getAmplifier() + 1);
        return s1;
    }

    private String intToRomanByGreedy(int num) {
        int[] values = new int[]{1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] symbols = new String[]{"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder stringBuilder = new StringBuilder();
        for (int i2 = 0; i2 < values.length && num >= 0; ++i2) {
            while (values[i2] <= num) {
                num -= values[i2];
                stringBuilder.append(symbols[i2]);
            }
        }
        return stringBuilder.toString();
    }
}

