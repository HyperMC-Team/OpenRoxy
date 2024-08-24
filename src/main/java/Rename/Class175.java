package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

public class Class175
extends Class192 {
    @Override
    public String getName() {
        return "attack";
    }

    @Override
    public void animate(EntityLivingBase argEntity, ModelBase argModel, Class204 argData) {
        Class193 model = (Class193)argModel;
        Class203 data = (Class203)argData;
        EntityPlayer player = (EntityPlayer)argEntity;
        if (player.getCurrentEquippedItem() != null) {
            if (data.ticksAfterPunch < 10.0f) {
                if (data.currentAttack == 1) {
                    Class176.animate((EntityPlayer)argEntity, model, data);
                    Class206.animate(model, "player", "attack");
                    Class206.animate(model, "player", "attack_0");
                } else if (data.currentAttack == 2) {
                    Class177.animate((EntityPlayer)argEntity, model, data);
                    Class206.animate(model, "player", "attack");
                    Class206.animate(model, "player", "attack_1");
                } else if (data.currentAttack == 3) {
                    Class178.animate((EntityPlayer)argEntity, model, data);
                    Class206.animate(model, "player", "attack");
                    Class206.animate(model, "player", "attack_2");
                }
            } else if (data.ticksAfterPunch < 60.0f) {
                Class181.animate((EntityPlayer)argEntity, model, data);
                Class206.animate(model, "player", "attack_stance");
            }
        } else if (data.ticksAfterPunch < 10.0f) {
            Class179.animate((EntityPlayer)argEntity, model, data);
            Class206.animate(model, "player", "attack");
            Class206.animate(model, "player", "punch");
        } else if (data.ticksAfterPunch < 60.0f) {
            Class180.animate((EntityPlayer)argEntity, model, data);
            Class206.animate(model, "player", "punch_stance");
        }
    }
}

