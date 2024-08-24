package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
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
public class Class308
extends Class252 {
    public Class308() {
        super("Class308", Class263.World);
    }

    @Class14
    public void onUpdate(Class27 event) {
        if (event.isOnGround()) {
            if (Class308.getBlockUnderPlayer(Class308.mc.thePlayer) instanceof BlockAir) {
                if (Class308.mc.thePlayer.onGround) {
                    KeyBinding.setKeyBindState(Class308.mc.gameSettings.keyBindSneak.getKeyCode(), true);
                }
            } else if (Class308.mc.thePlayer.onGround) {
                KeyBinding.setKeyBindState(Class308.mc.gameSettings.keyBindSneak.getKeyCode(), false);
            }
        }
    }

    @Override
    public void onEnable() {
        if (Class308.mc.thePlayer == null) {
            return;
        }
        Class308.mc.thePlayer.setSneaking(false);
        super.onEnable();
    }

    @Override
    public void onDisable() {
        KeyBinding.setKeyBindState(Class308.mc.gameSettings.keyBindSneak.getKeyCode(), false);
        super.onDisable();
    }

    public static Block getBlock(BlockPos pos) {
        return Class308.mc.theWorld.getBlockState(pos).getBlock();
    }

    public static Block getBlockUnderPlayer(EntityPlayer player) {
        return Class308.getBlock(new BlockPos(player.posX, player.posY - 1.0, player.posZ));
    }
}

