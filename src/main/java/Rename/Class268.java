package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.util.LinkedList;

import net.minecraft.client.entity.EntityOtherPlayerMP;
import tech.skidonion.obfuscator.annotations.StringEncryption;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
@StringEncryption
public class Class268
extends Class252 {
    private EntityOtherPlayerMP fakePlayer = null;
    private final LinkedList<double[]> positions = new LinkedList();

    public Class268() {
        super("Class268", Class263.Misc);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void onEnable() {
        if (Class268.mc.thePlayer == null) {
            return;
        }
        this.fakePlayer = new EntityOtherPlayerMP(Class268.mc.theWorld, Class268.mc.thePlayer.getGameProfile());
        this.fakePlayer.clonePlayer(Class268.mc.thePlayer, true);
        this.fakePlayer.copyLocationAndAnglesFrom(Class268.mc.thePlayer);
        this.fakePlayer.rotationYawHead = Class268.mc.thePlayer.rotationYawHead;
        Class268.mc.theWorld.addEntityToWorld(-1337, this.fakePlayer);
        LinkedList<double[]> linkedList = this.positions;
        synchronized (linkedList) {
            this.positions.add(new double[]{Class268.mc.thePlayer.posX, Class268.mc.thePlayer.getEntityBoundingBox().minY + (double)(Class268.mc.thePlayer.getEyeHeight() / 2.0f), Class268.mc.thePlayer.posZ});
            this.positions.add(new double[]{Class268.mc.thePlayer.posX, Class268.mc.thePlayer.getEntityBoundingBox().minY, Class268.mc.thePlayer.posZ});
        }
    }

    @Override
    public void onDisable() {
        if (Class268.mc.thePlayer == null) {
            return;
        }
        if (this.fakePlayer != null) {
            Class268.mc.theWorld.removeEntityFromWorld(this.fakePlayer.getEntityId());
            this.fakePlayer = null;
        }
    }
}

