package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.BlockWeb;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import tech.skidonion.obfuscator.annotations.StringEncryption;

@StringEncryption
public class Class270
extends Class252 {
    private final Class257 mode = new Class257("Mode", "Vanilla", "Vanilla", "GrimAC");
    private final List<BlockPos> pos = new ArrayList<BlockPos>();
    private boolean pass = true;

    public Class270() {
        super("Class270", Class263.Movement);
    }

    @Class14
    public void onUpdate(Class50 event) {
        if (Class270.mc.thePlayer.isInWeb) {
            Class270.mc.thePlayer.isInWeb = false;
        }
    }

    @Class14
    public void onWorld(Class55 event) {
        this.pos.clear();
    }

    @Class14
    public void onTick(Class52 event) {
        if (this.isNull()) {
            return;
        }
        if (this.mode.getMode().equals("GrimAC")) {
            for (int i = -2; i <= 2; ++i) {
                for (int j = -2; j < 2; ++j) {
                    for (int k = -2; k < 2; ++k) {
                        BlockPos pos = Class270.mc.thePlayer.getPosition().add(i, j, k);
                        if (Class270.mc.theWorld.getBlockState(pos) == null || !(Class270.mc.theWorld.getBlockState(pos).getBlock() instanceof BlockWeb) || this.pos.contains(pos)) continue;
                        mc.getNetHandler().addToSendQueue(new Class365(Class365.Action.START_DESTROY_BLOCK, pos, EnumFacing.DOWN));
                        mc.getNetHandler().addToSendQueue(new Class365(Class365.Action.STOP_DESTROY_BLOCK, pos, EnumFacing.DOWN));
                        Class270.mc.theWorld.setBlockToAir(pos);
                        this.pass = true;
                    }
                }
            }
        }
    }
}

