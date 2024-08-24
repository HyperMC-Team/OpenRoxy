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

import net.minecraft.world.World;

public class Class54
extends Class4 {
    World oldWorld;
    World newWorld;

    public World getOldWorld() {
        return this.oldWorld;
    }

    public World getNewWorld() {
        return this.newWorld;
    }

    public void setOldWorld(World oldWorld) {
        this.oldWorld = oldWorld;
    }

    public void setNewWorld(World newWorld) {
        this.newWorld = newWorld;
    }

    public Class54(World oldWorld, World newWorld) {
        this.oldWorld = oldWorld;
        this.newWorld = newWorld;
    }
}

