package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import net.minecraft.block.material.Material;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiContainerCreative;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
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
@NativeObfuscation
public class Class318
        extends Class252 {
    public static final Class260 clientName = new Class260("Class1 Name");
    public static final Class257 theme = Class234.getModeSetting("Class234 Selection", "MINT_BLUE");
    private final Class254 target = new Class254("TargetHUD", true);
    public final Class257 targetMode = new Class257("TargetHUDMods", "Naven", "Acrimony", "Naven", "Lovely", "Rise");
    private final Class259 X_post = new Class259("TatgetHUD-X", 0.0, 640.0, -520.0, 1.0);
    private final Class259 Y_post = new Class259("TargetHUD-Y", 0.0, 345.0, -305.0, 1.0);
    public final Class254 hotBar = new Class254("HotBar", false);
    public static final Class254 button = new Class254("Class88", false);
    private final Class254 health = new Class254("Health", false);
    private final Class254 potion = new Class254("Potion", false);
    private static final Class254 potionlow = new Class254("Lowercase", false);
    private final Class254 armor = new Class254("ArmorHUD", true);
    private final Class254 info = new Class254("Info", true);
    public static final Class255 color1 = new Class255("Color-1", new Color(126, 0, 252, 203));
    public static final Class255 color2 = new Class255("Color-2", new Color(191, 220, 238, 156));
    private static final Class459 openingAnimation = new Class474(175, 1.0, Class473.BACKWARDS);
    private final Random random = new Random();
    private final Class125 animation = new Class125();
    EntityPlayer targets;
    private int width = 0;
    public static int offsetValue = 0;

    public Class318() {
        super("Class318", Class263.Render);
        this.animation.setAnimDuration(400L);
        this.animation.setAnimType(Class127.SLIDE);
        this.targetMode.addParent(this.target, a -> this.target.isEnabled());
        this.X_post.addParent(this.target, a -> this.target.isEnabled());
        this.Y_post.addParent(this.target, a -> this.target.isEnabled());
    }

    @Class14
    public void onRender2D(Class38 event) {
        if (this.isNull()) {
            return;
        }
        Class318 hud = Class262.getModule(Class318.class);
        boolean inChat = Class318.mc.currentScreen instanceof GuiChat;
        if (hud.isState() && this.target.isEnabled()) {
            if (inChat) {
                this.animation.getTimer().setTimeElapsed(400L);
                this.render(Class318.mc.thePlayer, true);
                this.targets = null;
            } else if (this.isState()) {
                boolean canRender;
                boolean bl = canRender = Class262.getModule(Class291.class).isState() && Class291.getTarget() != null && Class291.getTarget() instanceof EntityPlayer;
                if (Class262.getModule(Class291.class).isState() && Class291.getTarget() != null && Class291.getTarget() instanceof EntityPlayer) {
                    this.targets = (EntityPlayer) Class291.getTarget();
                }
                this.render(this.targets, canRender);
            } else {
                this.animation.getTimer().setTimeElapsed(0L);
            }
        }
        if (this.hotBar.isEnabled() && hud.isState() && Class318.mc.theWorld != null) {
            int middleScreen = sr.getScaledWidth() / 2;
            Class468.drawRound(middleScreen - 91, sr.getScaledHeight() - 22, 182.0f, 20.0f, 3.0f, new Color(0, 0, 0, 160));
            Class468.drawRound(middleScreen - 91 + Class318.mc.thePlayer.inventory.currentItem * 20, sr.getScaledHeight() - 22, 20.0f, 20.0f, 3.0f, new Color(255, 255, 255, 80));
            GlStateManager.enableRescaleNormal();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            RenderHelper.enableGUIStandardItemLighting();
            for (int j = 0; j < 9; ++j) {
                int k = sr.getScaledWidth() / 2 - 90 + j * 20 + 2;
                int l = sr.getScaledHeight() - 16 - 3;
                this.renderHotBarItem(j, k, l, event.getPartialTicks(), Class318.mc.thePlayer);
            }
            RenderHelper.disableStandardItemLighting();
            GlStateManager.disableRescaleNormal();
            GlStateManager.disableBlend();
        }
        if (this.info.isEnabled() && hud.isState()) {
            Class318.oninfo();
        }
        if (this.health.isEnabled() && hud.isState()) {
            this.renderHealth();
        }
        if (this.armor.isEnabled() && hud.isState()) {
            this.drawArmor(sr);
        }
        if (this.potion.isEnabled() && hud.isState()) {
            this.renderpotion();
        }
    }

    @NativeObfuscation.Inline
    public static void oninfo() {
        float x2 = sr.getScaledWidth() - 3;
        float y2 = sr.getScaledHeight() - 10;
        String uname = Class87.uid;
        String date = Class1.instance.Method5();
        String version = Class1.instance.Method6();
        Class318.mc.fontRendererObj.drawStringWithShadow(ChatFormatting.GRAY + version + " - " + ChatFormatting.WHITE + date + ChatFormatting.GRAY + " - " + uname, x2 - (float) Class318.mc.fontRendererObj.getStringWidth(ChatFormatting.GRAY + version + " - " + ChatFormatting.WHITE + date + ChatFormatting.GRAY + " - " + uname), y2, -1);
    }

    public static Color color(int tick) {
        return new Color(Class465.colorSwitch(Class318.getClientColors().getFirst(), Class318.getClientColors().getSecond(), 2000.0f, -(tick * 200) / 40, 75L, 2.0));
    }

    public static void drawLine(double x, double y, double width, double height, Color color) {
        Gui.drawRect(x, y, x + width, y + height, color.getRGB());
    }

    private void renderpotion() {
        float yOffset = (float)(14.5 * openingAnimation.getOutput());
        ArrayList<PotionEffect> potions = new ArrayList<PotionEffect>(Class318.mc.thePlayer.getActivePotionEffects());
        potions.sort(Comparator.comparingDouble(e -> -Class115.fr.getStringWidth(I18n.format(e.getEffectName()))));
        int count = 0;
        for (PotionEffect effect : potions) {
            Potion potion = Potion.potionTypes[effect.getPotionID()];
            String name = I18n.format(potion.getName()) + (effect.getAmplifier() > 0 ? " " + Class221.generate(effect.getAmplifier() + 1) : "");
            Color c = new Color(potion.getLiquidColor());
            String str = Class318.get(name + " \u00a77- " + Potion.getDurationString(effect));
            Class115.fr.drawString(str, sr.getScaledWidth() - Class115.fr.getStringWidth(str) - 2, (float)(-10 + sr.getScaledHeight() - Class115.fr.getHeight() + (7 - 10 * (count + 1))) - yOffset, new Color(c.getRed(), c.getGreen(), c.getBlue(), 255).getRGB(), true);
            ++count;
        }
        offsetValue = count * Class115.fr.getHeight();
    }

    public static String get(String text) {
        return potionlow.isEnabled() ? text.toLowerCase() : text;
    }

    private void renderHealth() {
        boolean flag;
        ScaledResolution scaledResolution = new ScaledResolution(mc);
        GuiScreen screen = Class318.mc.currentScreen;
        float absorptionHealth = Class318.mc.thePlayer.getAbsorptionAmount();
        String string = Class171.DF_1D.format(Class318.mc.thePlayer.getHealth() / 2.0f) + "\u00a7c\u2764 " + (absorptionHealth <= 0.0f ? "" : "\u00a7e" + Class171.DF_1D.format(absorptionHealth / 2.0f) + "\u00a76\u2764");
        int offsetY = 0;
        if (Class318.mc.thePlayer.getHealth() >= 0.0f && Class318.mc.thePlayer.getHealth() < 10.0f || Class318.mc.thePlayer.getHealth() >= 10.0f && Class318.mc.thePlayer.getHealth() < 100.0f) {
            this.width = 3;
        }
        if (screen instanceof GuiInventory) {
            offsetY = 70;
        } else if (screen instanceof GuiContainerCreative) {
            offsetY = 80;
        } else if (screen instanceof GuiChest) {
            offsetY = ((GuiChest)screen).ySize / 2 - 15;
        }
        int x = new ScaledResolution(mc).getScaledWidth() / 2 - this.width;
        int y = new ScaledResolution(mc).getScaledHeight() / 2 + 25 + offsetY;
        Color color = Class223.blendColors(new float[]{0.0f, 0.5f, 1.0f}, new Color[]{new Color(255, 37, 0), Color.YELLOW, Color.GREEN}, Class318.mc.thePlayer.getHealth() / Class318.mc.thePlayer.getMaxHealth());
        Class318.mc.fontRendererObj.drawString(string, absorptionHealth > 0.0f ? (float)x - 15.5f : (float)x - 3.5f, y, color.getRGB(), true);
        GL11.glPushMatrix();
        mc.getTextureManager().bindTexture(Gui.icons);
        this.random.setSeed((long) Class318.mc.ingameGUI.getUpdateCounter() * 312871L);
        float width = (float)scaledResolution.getScaledWidth() / 2.0f - Class318.mc.thePlayer.getMaxHealth() / 2.5f * 10.0f / 2.0f;
        float maxHealth = Class318.mc.thePlayer.getMaxHealth();
        int lastPlayerHealth = Class318.mc.ingameGUI.lastPlayerHealth;
        int healthInt = MathHelper.ceiling_float_int(Class318.mc.thePlayer.getHealth());
        int l2 = -1;
        boolean bl = flag = Class318.mc.ingameGUI.healthUpdateCounter > (long) Class318.mc.ingameGUI.getUpdateCounter() && (Class318.mc.ingameGUI.healthUpdateCounter - (long) Class318.mc.ingameGUI.getUpdateCounter()) / 3L % 2L == 1L;
        if (Class318.mc.thePlayer.isPotionActive(Potion.regeneration)) {
            l2 = Class318.mc.ingameGUI.getUpdateCounter() % MathHelper.ceiling_float_int(maxHealth + 5.0f);
        }
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        for (int i6 = MathHelper.ceiling_float_int(maxHealth / 2.0f) - 1; i6 >= 0; --i6) {
            int xOffset = 16;
            if (Class318.mc.thePlayer.isPotionActive(Potion.poison)) {
                xOffset += 36;
            } else if (Class318.mc.thePlayer.isPotionActive(Potion.wither)) {
                xOffset += 72;
            }
            int k3 = 0;
            if (flag) {
                k3 = 1;
            }
            float renX = width + (float)(i6 % 10 * 8);
            float renY = (float)scaledResolution.getScaledHeight() / 2.0f + 15.0f + (float)offsetY;
            if (healthInt <= 4) {
                renY += (float)this.random.nextInt(2);
            }
            if (i6 == l2) {
                renY -= 2.0f;
            }
            int yOffset = 0;
            if (Class318.mc.theWorld.getWorldInfo().isHardcoreModeEnabled()) {
                yOffset = 5;
            }
            Gui.drawTexturedModalRect2(renX, renY, 16 + k3 * 9, 9 * yOffset, 9, 9);
            if (flag) {
                if (i6 * 2 + 1 < lastPlayerHealth) {
                    Gui.drawTexturedModalRect2(renX, renY, xOffset + 54, 9 * yOffset, 9, 9);
                }
                if (i6 * 2 + 1 == lastPlayerHealth) {
                    Gui.drawTexturedModalRect2(renX, renY, xOffset + 63, 9 * yOffset, 9, 9);
                }
            }
            if (i6 * 2 + 1 < healthInt) {
                Gui.drawTexturedModalRect2(renX, renY, xOffset + 36, 9 * yOffset, 9, 9);
            }
            if (i6 * 2 + 1 != healthInt) continue;
            Gui.drawTexturedModalRect2(renX, renY, xOffset + 45, 9 * yOffset, 9, 9);
        }
        GL11.glPopMatrix();
    }

    private void drawArmor(ScaledResolution sr) {
        ItemStack armorPiece;
        ArrayList<ItemStack> equipment = new ArrayList<ItemStack>();
        boolean inWater = Class318.mc.thePlayer.isEntityAlive() && Class318.mc.thePlayer.isInsideOfMaterial(Material.water);
        int x = -94;
        for (int i = 3; i >= 0; --i) {
            armorPiece = Class318.mc.thePlayer.inventory.armorInventory[i];
            if (armorPiece == null) continue;
            equipment.add(armorPiece);
        }
        Collections.reverse(equipment);
        Iterator iterator = equipment.iterator();
        while (iterator.hasNext()) {
            ItemStack itemStack;
            armorPiece = itemStack = (ItemStack)iterator.next();
            RenderHelper.enableGUIStandardItemLighting();
            x += 15;
            GlStateManager.pushMatrix();
            GlStateManager.disableAlpha();
            GlStateManager.clear(256);
            Class318.mc.getRenderItem().zLevel = -150.0f;
            int s = Class318.mc.thePlayer.capabilities.isCreativeMode ? 15 : 0;
            mc.getRenderItem().renderItemAndEffectIntoGUI(armorPiece, -x + sr.getScaledWidth() / 2 - 4, (int)((double)(sr.getScaledHeight() - (inWater ? 65 : 55) + s) - 16.0 * openingAnimation.getOutput()));
            Class318.mc.getRenderItem().zLevel = 0.0f;
            GlStateManager.disableBlend();
            GlStateManager.disableDepth();
            GlStateManager.disableLighting();
            GlStateManager.enableDepth();
            GlStateManager.enableAlpha();
            GlStateManager.popMatrix();
            armorPiece.getEnchantmentTagList();
        }
    }

    private void render(EntityPlayer entity, boolean render) {
        ScaledResolution sr = new ScaledResolution(mc);
        int x = sr.getScaledWidth() / 2 - this.X_post.getValue().intValue();
        int y = sr.getScaledHeight() / 2 - this.Y_post.getValue().intValue();
        this.animation.updateState(render);
        this.animation.setAnimDuration(400L);
        this.animation.setAnimType(Class127.SLIDE);
        if (entity == null) {
            return;
        }
        if (this.animation.isRendered() || !this.animation.isAnimDone()) {
            this.animation.render(() -> {
                switch (this.targetMode.getMode()) {
                    case "Lovely": {
                        float getMaxHel = Math.min(entity.getMaxHealth(), 20.0f);
                        float width = 5.9f;
                        float height = 56.0f;
                        Class228.drawGlow(x, y, 120.0f, 30.0f, 6, new Color(0, 0, 0, 60));
                        Class224.drawRoundedRect(x, y, x + 80 + 40, (double)y + 34.5, 2.0, Integer.MIN_VALUE);
                        Class224.drawRoundedRect(x, y, x, y, 0.0, new Color(255, 255, 255, 255).getRGB());
                        Class318.mc.fontRendererObj.drawStringWithShadow("Target: " + entity.getName(), x + 5, y + 5, new Color(255, 255, 255).getRGB());
                        Class318.mc.fontRendererObj.drawStringWithShadow("Health: " + Class171.DF_1.format(entity.getHealth()), (float)x + 5.0f, (float)y + 18.0f, Color.WHITE.getRGB());
                        Class318.mc.fontRendererObj.drawStringWithShadow(entity.getHealth() <= Class318.mc.thePlayer.getHealth() ? "W" : "L", x + 110, y + 14, entity.getHealth() <= Class318.mc.thePlayer.getHealth() ? Color.GREEN.getRGB() : Color.RED.getRGB());
                        Class224.drawRoundedRect(x, y, x, y, 0.0, new Color(255, 255, 255, 255).getRGB());
                        entity.animatedHealthBar = Class461.animate(entity.animatedHealthBar, entity.getHealth(), 0.15f);
                        Class468.drawRound((float)x + 1.0f, (float)y + height - 25.3f, entity.animatedHealthBar / entity.getMaxHealth() * Math.max(getMaxHel * width, (float) Class330.arial18.getStringWidth(entity.getName())), 3.0f, 0.0f, new Color(126, 0, 252, 203));
                        break;
                    }
                    case "Naven": {
                        float width = 130.0f;
                        float height = 50.0f;
                        Class148.drawRound(x, y, width, height, 5.0f, new Color(10, 10, 30, 120));
                        Class224.drawHead(((AbstractClientPlayer)entity).getLocationSkin(), x + 7, y + 7, 30, 30);
                        Class148.drawRound(x + 5, (float)y + height - 7.0f, entity.getHealth() / entity.getMaxHealth() * width - 10.0f, 3.0f, 2.0f, new Color(160, 42, 42));
                        DecimalFormat decimalFormat = new DecimalFormat("0.00");
                        Class330.arial18.drawString(entity.getName(), x + 40, y + 10, -1);
                        Class330.arial16.drawString("Health: " + decimalFormat.format(entity.getHealth()), x + 40, y + 22, -1);
                        Class330.arial16.drawString("Distance: " + decimalFormat.format(entity.getDistanceToEntity(Class318.mc.thePlayer)), x + 40, y + 30, -1);
                        break;
                    }
                    case "Rise": {
                        float height = 32.0f;
                        float width = Math.max(120.0f, (float) Class330.arial20.getStringWidth(this.target.getName()) + 51.0f);
                        float healthPercentme = MathHelper.clamp_float((Class318.mc.thePlayer.getHealth() + Class318.mc.thePlayer.getAbsorptionAmount()) / (Class318.mc.thePlayer.getMaxHealth() + Class318.mc.thePlayer.getAbsorptionAmount()), 0.0f, 1.0f);
                        Class148.drawRound(x, y, width, height, 5.0f, new Color(10, 10, 30, 120));
                        Class148.drawGradientHorizontal((float)x + 34.0f, (float)y + height - 13.0f, width - 37.2f, 8.0f, 1.0f, new Color(0, 0, 0, 150), new Color(0, 0, 0, 85));
                        Class148.drawGradientHorizontal((float)x + 34.0f, (float)y + height - 13.0f, entity.getHealth() / entity.getMaxHealth() * (width - 37.2f), 8.0f, 1.0f, new Color(181, 112, 255, 203), new Color(190, 109, 255, 203));
                        int scaleOffset = (int)((float)entity.hurtTime * 0.7f);
                        Class224.drawHead(((AbstractClientPlayer)entity).getLocationSkin(), x + 3 + scaleOffset / 2, y + 2 + scaleOffset / 2, 27 - scaleOffset, 27 - scaleOffset);
                        Class232.uninitStencilBuffer();
                        GlStateManager.disableBlend();
                        Class330.arial20.drawString(entity.getName(), (float)x + 33.0f, (float)((double)y + 3.5) + 1.0f, Color.WHITE.getRGB());
                        float healthPercent = MathHelper.clamp_float((entity.getHealth() + entity.getAbsorptionAmount()) / (entity.getMaxHealth() + entity.getAbsorptionAmount()), 0.0f, 1.0f);
                        String healthText = (int) Class146.round(healthPercent * 100.0f, 0.01) + ".0%";
                        Class115.tenacityBoldFont16.drawString(healthText, (float)(x + 63), (float)y + 20.8f, Color.WHITE.getRGB());
                        break;
                    }
                    case "Acrimony": {
                        Class224.drawRoundedRect(x, y, x + 140 + 20, y + 50 + 10, 2.0, Integer.MIN_VALUE);
                        Class224.drawRoundedRect(x, y, x, y, 0.0, new Color(255, 255, 255, 255).getRGB());
                        Class330.arial16.drawStringWithShadow("" + Math.round((double)entity.getHealth() - 0.5), (float)x + 44.5f, y + 26, new Color(255, 255, 255).getRGB());
                        Class330.arial10.drawStringWithShadow("" + Math.round(entity.posX - 0.5), (float)x + 85.5f, (double)y + 28.4, new Color(255, 255, 255).getRGB());
                        Class330.arial14.drawStringWithShadow("" + Math.round((double) Class318.mc.thePlayer.getDistanceToEntity(entity) - 0.5), (float)x + 67.2f, y + 26, new Color(255, 255, 255).getRGB());
                        Class465.drawLoadingCircleNormal(x + 50, y + 29, new Color(246, 174, 90, 200));
                        Class465.drawLoadingCircleFast(x + 50, y + 29, new Color(183, 211, 82, 200));
                        Class465.drawLoadingCircleSlow(x + 70, y + 29, new Color(227, 103, 103, 200));
                        Class465.drawLoadingCircleFast(x + 70, y + 29, new Color(182, 182, 84, 200));
                        Class465.drawLoadingCircleNormal(x + 90, y + 29, new Color(199, 19, 19, 200));
                        Class224.drawRoundedRect(x, y, x, y, 0.0, new Color(255, 255, 255, 255).getRGB());
                        Gui.drawRect(x, y + 50 + 8, x + 160, y + 50 + 10, new Color(1, 1, 1, 200).getRGB());
                        Class224.drawRoundedRect(x, y, x, y, 0.0, new Color(255, 255, 255, 255).getRGB());
                        GuiInventory.drawEntityOnScreen(x + 22, y + 54, 26, -entity.rotationYaw, entity.rotationPitch, entity);
                        Class330.arial26.drawStringWithShadow(entity.getName(), x + 44, y + 8, new Color(255, 255, 255).getRGB());
                        Class330.arial26.drawStringWithShadow(entity.getHealth() <= Class318.mc.thePlayer.getHealth() ? "Winning" : "Losing", x + 44, y + 44, entity.getHealth() <= Class318.mc.thePlayer.getHealth() ? Color.GREEN.getRGB() : Color.RED.getRGB());
                    }
                }
            }, x, y, x, y);
        }
    }

    private void renderHotBarItem(int index, int xPos, int yPos, float partialTicks, EntityPlayer entityPlayer) {
        ItemStack itemstack = entityPlayer.inventory.mainInventory[index];
        RenderItem itemRenderer = mc.getRenderItem();
        if (itemstack != null) {
            float f = (float)itemstack.animationsToGo - partialTicks;
            if (f > 0.0f) {
                GlStateManager.pushMatrix();
                float f1 = 1.0f + f / 5.0f;
                GlStateManager.translate(xPos + 8, yPos + 12, 0.0f);
                GlStateManager.scale(1.0f / f1, (f1 + 1.0f) / 2.0f, 1.0f);
                GlStateManager.translate(-(xPos + 8), -(yPos + 12), 0.0f);
            }
            itemRenderer.renderItemAndEffectIntoGUI(itemstack, xPos, yPos);
            if (f > 0.0f) {
                GlStateManager.popMatrix();
            }
            itemRenderer.renderItemOverlays(Class318.mc.fontRendererObj, itemstack, xPos, yPos);
        }
    }

    public static String name() {
        String name = "Rise";
        if (!clientName.getString().isEmpty()) {
            name = clientName.getString().replace("%time%", new SimpleDateFormat("HH:mm").format(new Date()));
        }
        return name;
    }

    public static Class123<Color, Color> getClientColors() {
        return Class234.getThemeColors(theme.getMode());
    }
}

