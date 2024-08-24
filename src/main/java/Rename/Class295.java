package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.util.Objects;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
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
public class Class295
extends Class252 {
    private boolean attackEnemy;

    public Class295() {
        super("Class295", Class263.Combat);
    }

    @Override
    public void onEnable() {
        this.attackEnemy = false;
    }

    @Class14
    public void onAttack(Class52 event) {
        Class291 aura = Class262.getModule(Class291.class);
        if (Class291.target instanceof EntityPlayer) {
            this.attackEnemy = true;
        }
    }

    @Class14
    public void onPacketReceive(Class33 event) {
        if (this.attackEnemy) {
            Class334 c02;
            this.attackEnemy = false;
            int slot = -1;
            double maxDamage = 0.0;
            for (int i = 0; i < 9; ++i) {
                double damage;
                if (Class295.mc.thePlayer.inventory.getStackInSlot(i) == null || !(Class295.mc.thePlayer.inventory.getStackInSlot(i).getItem() instanceof ItemSword) || !((damage = (Class295.mc.thePlayer.inventory.getStackInSlot(i).getAttributeModifiers().get("generic.attackDamage").stream().findFirst().orElse(null) != null ? Objects.requireNonNull(Class295.mc.thePlayer.inventory.getStackInSlot(i).getAttributeModifiers().get("generic.attackDamage").stream().findFirst().orElse(null)).getAmount() : 0.0) + 1.25 * (double) Class295.getEnchantment(Class295.mc.thePlayer.inventory.getStackInSlot(i), Enchantment.sharpness)) > maxDamage)) continue;
                maxDamage = damage;
                slot = i;
            }
            if (slot == Class295.mc.thePlayer.inventory.currentItem || slot == -1) {
                return;
            }
            Class295.mc.thePlayer.inventory.currentItem = slot;
            Packet<?> packet = event.getPacket();
            if (packet instanceof Class334 && (c02 = (Class334)packet).getAction() == Class334.Action.ATTACK) {
                event.setCancelled(true);
                Class212.sendPacket(c02);
            }
        }
    }

    public static int getEnchantment(ItemStack itemStack, Enchantment enchantment) {
        if (itemStack == null || itemStack.getEnchantmentTagList() == null || itemStack.getEnchantmentTagList().hasNoTags()) {
            return 0;
        }
        for (int i = 0; i < itemStack.getEnchantmentTagList().tagCount(); ++i) {
            NBTTagCompound tagCompound = itemStack.getEnchantmentTagList().getCompoundTagAt(i);
            if (tagCompound.getShort("ench") != enchantment.effectId && tagCompound.getShort("id") != enchantment.effectId) continue;
            return tagCompound.getShort("lvl");
        }
        return 0;
    }
}

