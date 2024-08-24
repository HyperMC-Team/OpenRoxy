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

import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class Class43
implements Class7 {
    private final BlockPos clickedBlock;
    private final EnumFacing enumFacing;

    public Class43(BlockPos clickedBlock, EnumFacing enumFacing) {
        this.clickedBlock = clickedBlock;
        this.enumFacing = enumFacing;
    }

    public BlockPos getClickedBlock() {
        return this.clickedBlock;
    }

    public EnumFacing getEnumFacing() {
        return this.enumFacing;
    }
}

