package net.minecraft.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.StatCollector;

public class EntityDamageSourceIndirect
extends EntityDamageSource {
    private Entity indirectEntity;

    public EntityDamageSourceIndirect(String damageTypeIn, Entity source, Entity indirectEntityIn) {
        super(damageTypeIn, source);
        this.indirectEntity = indirectEntityIn;
    }

    @Override
    public Entity getSourceOfDamage() {
        return this.damageSourceEntity;
    }

    @Override
    public Entity getEntity() {
        return this.indirectEntity;
    }

    @Override
    public IChatComponent getDeathMessage(EntityLivingBase entityLivingBaseIn) {
        IChatComponent ichatcomponent = this.indirectEntity == null ? this.damageSourceEntity.getDisplayName() : this.indirectEntity.getDisplayName();
        ItemStack itemstack = this.indirectEntity instanceof EntityLivingBase ? ((EntityLivingBase)this.indirectEntity).getHeldItem() : null;
        String s = "death.attack." + this.damageType;
        String s1 = s + ".item";
        return itemstack != null && itemstack.hasDisplayName() && StatCollector.canTranslate(s1) ? new ChatComponentTranslation(s1, entityLivingBaseIn.getDisplayName(), ichatcomponent, itemstack.getChatComponent()) : new ChatComponentTranslation(s, entityLivingBaseIn.getDisplayName(), ichatcomponent);
    }
}

