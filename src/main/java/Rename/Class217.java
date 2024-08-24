package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import net.minecraft.util.EnumFacing;
import net.minecraft.util.Vec3;

public class Class217 {
    public EnumFacing enumFacing;
    private final Vec3 offset;

    public Class217(EnumFacing enumFacing, Vec3 offset) {
        this.enumFacing = enumFacing;
        this.offset = offset;
    }

    public EnumFacing getEnumFacing() {
        return this.enumFacing;
    }

    public Vec3 getOffset() {
        return this.offset;
    }
}

