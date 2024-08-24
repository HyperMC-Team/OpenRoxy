package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.block.BlockFurnace;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.inventory.ContainerChest;
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
public class Class277
extends Class252 {
    private final Class259 range = new Class259("Range", 3.0, 7.0, 1.0, 0.1);
    public static Class254 interactOnce = new Class254("InteractOnce", false);
    public Class240 waitBoxOpenTimer = new Class240();
    public static boolean isWaitingOpen = false;
    private BlockPos globalPos;
    private BlockPos openingPos;
    public static List<BlockPos> list = new ArrayList<BlockPos>();

    public Class277() {
        super("ContainerAura", Class263.World);
    }

    @Override
    public void onDisable() {
        list.clear();
    }

    @Class14
    public void onPre(Class27 e) {
        float radius;
        if (this.isGapple()) {
            return;
        }
        if (Class262.getModule(Class278.class).isState()) {
            return;
        }
        this.globalPos = null;
        if (Class277.mc.thePlayer.ticksExisted % 20 == 0 || Class291.target != null || Class277.mc.currentScreen instanceof GuiContainer || Class262.getModule(Class278.class).isState() || Class262.getModule(Class304.class).isState() || this.isFood()) {
            return;
        }
        for (float y = radius = this.range.getValue().floatValue(); y >= -radius; y -= 1.0f) {
            for (float x = -radius; x <= radius; x += 1.0f) {
                for (float z = -radius; z <= radius; z += 1.0f) {
                    float[] rotations;
                    BlockPos pos = new BlockPos(Class277.mc.thePlayer.posX - 0.5 + (double)x, Class277.mc.thePlayer.posY - 0.5 + (double)y, Class277.mc.thePlayer.posZ - 0.5 + (double)z);
                    Block block = Class277.mc.theWorld.getBlockState(pos).getBlock();
                    BlockPos targetPos = new BlockPos(Class277.mc.thePlayer.posX + (double)x, Class277.mc.thePlayer.posY + (double)y, Class277.mc.thePlayer.posZ + (double)z);
                    if (!(Class277.mc.thePlayer.getDistance(targetPos.getX(), targetPos.getY(), targetPos.getZ()) < (double) Class277.mc.playerController.getBlockReachDistance()) || !(block instanceof BlockChest) && !(block instanceof BlockFurnace) || list.contains(pos) || !Class236.overBlock(new Class247((rotations = Class237.getBlockRotations(pos.getX(), pos.getY() - 1, pos.getZ()))[0], rotations[1]), Class277.mc.objectMouseOver.sideHit, pos, false)) continue;
                    Class20.setRotations(new Class247(rotations[0], rotations[1]), 360.0, Class167.NORMAL, true);
                    this.globalPos = pos;
                    return;
                }
            }
        }
    }

    @Class14
    public void onPost(Class26 event) {
        if (this.isGapple()) {
            return;
        }
        if (Class262.getModule(Class278.class).isState()) {
            return;
        }
        if (isWaitingOpen) {
            if (this.waitBoxOpenTimer.isDelayComplete(600.0)) {
                isWaitingOpen = false;
            } else if (this.openingPos != null && Class277.mc.thePlayer.openContainer instanceof ContainerChest) {
                list.add(this.openingPos);
                this.openingPos = null;
                isWaitingOpen = false;
            }
        }
    }

    @Class14
    public void onPlace(Class48 event) {
        if (this.isGapple()) {
            return;
        }
        if (Class262.getModule(Class278.class).isState()) {
            return;
        }
        if (this.globalPos != null && !(Class277.mc.currentScreen instanceof GuiContainer) && list.size() < 50 && !isWaitingOpen && !list.contains(this.globalPos) && Class236.overBlock(Class20.rotations, Class277.mc.objectMouseOver.sideHit, this.globalPos, false)) {
            this.sendClick(this.globalPos);
            Class212.sendPacketNoEvent(new Class331());
            event.setShouldRightClick(false);
        }
    }

    @Class14
    public void onWorld(Class55 e) {
        list.clear();
    }

    @Class14
    public void onRender(Class39 e) {
        for (BlockPos pos : list) {
            double x = (double)pos.getX() - RenderManager.renderPosX;
            double y = (double)pos.getY() - RenderManager.renderPosY;
            double z = (double)pos.getZ() - RenderManager.renderPosZ;
            Class465.drawEntityESP(x, y, z, x + 1.0, y + 1.0, z + 1.0, 0.0f, 255.0f, 255.0f, 10.0f);
        }
    }

    public void sendClick(BlockPos pos) {
        Class355 packet = new Class355(pos, (double)pos.getY() + 0.5 < Class277.mc.thePlayer.posY + 1.7 ? 1 : 0, Class277.mc.thePlayer.getCurrentEquippedItem(), 0.0f, 0.0f, 0.0f);
        Class277.mc.thePlayer.sendQueue.addToSendQueue(packet);
        this.waitBoxOpenTimer.reset();
        isWaitingOpen = true;
        this.openingPos = this.globalPos;
        if (interactOnce.isEnabled()) {
            list.add(pos);
        }
    }
}

