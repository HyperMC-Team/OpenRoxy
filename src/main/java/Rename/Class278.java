package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockAir;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;
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
public class Class278
extends Class252 {
    public static final List<Block> invalidBlocks = Arrays.asList(Blocks.enchanting_table, Blocks.furnace, Blocks.carpet, Blocks.crafting_table, Blocks.trapped_chest, Blocks.chest, Blocks.dispenser, Blocks.air, Blocks.water, Blocks.lava, Blocks.flowing_water, Blocks.flowing_lava, Blocks.snow_layer, Blocks.torch, Blocks.anvil, Blocks.jukebox, Blocks.stone_button, Blocks.wooden_button, Blocks.lever, Blocks.noteblock, Blocks.stone_pressure_plate, Blocks.light_weighted_pressure_plate, Blocks.wooden_pressure_plate, Blocks.heavy_weighted_pressure_plate, Blocks.stone_slab, Blocks.wooden_slab, Blocks.stone_slab2, Blocks.red_mushroom, Blocks.brown_mushroom, Blocks.yellow_flower, Blocks.red_flower, Blocks.anvil, Blocks.glass_pane, Blocks.stained_glass_pane, Blocks.iron_bars, Blocks.cactus, Blocks.ladder, Blocks.web, Blocks.tnt);
    public static Class278 INSTANCE;
    public final Class254 swing = new Class254("Swing", true);
    public final Class254 sprintValue = new Class254("Class298", false);
    public final Class254 eagle = new Class254("Class308", false);
    public final Class254 telly = new Class254("Telly", true);
    public final Class254 autojump = new Class254("AutoJump", false);
    public final Class254 fakeslot = new Class254("FakeSlot", false);
    public final Class254 raycast = new Class254("RayCast", false);
    public final Class254 tower = new Class254("Tower", false);
    public final Class254 esp = new Class254("Class316", true);
    public final Class254 count = new Class254("Count", true);
    public boolean tip = false;
    protected Random rand = new Random();
    private final List<BlockPos> placedBlocks = new ArrayList<BlockPos>();
    private double countscale = 0.0;
    private boolean towers = false;
    private int ticks = 0;
    private int holdticks = 0;
    private int slot;
    private int oldSlot;
    private BlockPos data;
    private boolean canTellyPlace;
    private int prevItem = 0;
    private EnumFacing facing;
    static EntityPlayerSP player;
    public Class278() {
        super("Scaffold", Class263.World);
    }

    @Override
    public void onEnable() {
        this.countscale = 0.0;
        if (Class278.mc.thePlayer == null) {
            return;
        }
        this.oldSlot = Class278.mc.thePlayer.inventory.currentItem;
        this.prevItem = Class278.mc.thePlayer.inventory.currentItem;
        Class278.mc.thePlayer.setSprinting(this.sprintValue.isEnabled() || !this.canTellyPlace);
        Class278.mc.gameSettings.keyBindSprint.pressed = this.sprintValue.isEnabled() || !this.canTellyPlace;
        this.canTellyPlace = false;
        this.tip = false;
        this.data = null;
        this.slot = -1;
        this.facing = null;
    }

    @Override
    public void onDisable() {
        if (Class278.mc.thePlayer == null) {
            return;
        }
        if (this.fakeslot.isEnabled()) {
            Class1.instance.Method12().stopSpoofing();
        }
        KeyBinding.setKeyBindState(Class278.mc.gameSettings.keyBindSneak.getKeyCode(), false);
        Class278.mc.thePlayer.inventory.currentItem = this.prevItem;
        this.placedBlocks.clear();
    }

    @Class14
    public void onRender3D(Class39 event) {
        if (this.data == null) {
            return;
        }
        BlockPos currentBlockPos = this.data;
        Class154 placeInfo = Class154.get(currentBlockPos);
        if (Class153.isValidBock(currentBlockPos) && placeInfo != null && this.esp.isEnabled() && !this.placedBlocks.contains(currentBlockPos)) {
            this.placedBlocks.add(currentBlockPos);
        }
        Iterator<BlockPos> iterator = this.placedBlocks.iterator();
        while (iterator.hasNext()) {
            int maxDistance;
            BlockPos blockPos = iterator.next();
            double distance = Class278.mc.thePlayer.getDistance((double)blockPos.getX() + 0.5, (double)blockPos.getY() + 0.5, (double)blockPos.getZ() + 0.5);
            int alpha = (int)Math.max(0.0, 255.0 - distance / (double)(maxDistance = 8) * 255.0);
            if (alpha <= 0) {
                iterator.remove();
                continue;
            }
            Class230.renderBlock(blockPos, new Color(50, 160, 200, Math.min(255, alpha)).getRGB(), true, false);
        }
    }

    @Class14
    private void onRender2D(Class38 event) {
        ScaledResolution sr = event.getScaledResolution();
        float width = 50 + Class278.mc.fontRendererObj.getStringWidth(String.valueOf(Class278.mc.thePlayer.getHeldItem().stackSize));
        int x = (int)((float)(sr.getScaledWidth() / 2) - width / 2.0f);
        int y = sr.getScaledHeight() / 2 + 12;
        float height = 18.0f;
        if (this.count.isEnabled()) {
            GL11.glPushMatrix();
            GL11.glTranslated((float)x + width / 2.0f, (float)y + height / 2.0f, 0.0);
            GL11.glScaled(this.countscale, this.countscale, this.countscale);
            GL11.glTranslated(-((float)x + width / 2.0f), -((float)y + height / 2.0f), 0.0);
            Class148.drawRound(x, y, width + 5.0f, height, 3.0f, new Color(0, 0, 0, 50));
            Class228.drawGlow(x, y, width + 5.0f, height, 22, new Color(20, 20, 20, 128));
            if (Class278.mc.thePlayer.getHeldItem() == null || Class278.mc.thePlayer.getHeldItem().stackSize == 0) {
                Class278.mc.fontRendererObj.drawString("?", x + 4, y, new Color(40, 44, 52).getRGB());
            } else {
                this.drawItemStack(Class278.mc.thePlayer.getHeldItem(), (float)x + 2.5f, y + 1);
            }
            Class115.tenacityBoldFont18.drawString("Blocks " + Class278.mc.thePlayer.getHeldItem().stackSize, (float)(x + 21), (float)(y + 6), new Color(255, 255, 255).getRGB());
            GL11.glPopMatrix();
        }
    }

    @Class14
    public void onUpdate(Class27 event) {
        if (this.tower.isEnabled()) {
            this.onTower();
        }
        if (this.eagle.isEnabled()) {
            if (Class308.getBlockUnderPlayer(Class278.mc.thePlayer) instanceof BlockAir) {
                if (Class278.mc.thePlayer.onGround) {
                    KeyBinding.setKeyBindState(Class278.mc.gameSettings.keyBindSneak.getKeyCode(), true);
                }
            } else if (Class278.mc.thePlayer.onGround) {
                KeyBinding.setKeyBindState(Class278.mc.gameSettings.keyBindSneak.getKeyCode(), false);
            }
        }
        if (this.slot < 0) {
            return;
        }
        if (this.getBlockCount() <= 1) {
            int spoofSlot = this.getBestSpoofSlot();
            this.getBlock(spoofSlot);
        }
        if (this.slot < 0) {
            return;
        }
        Class278.mc.thePlayer.inventoryContainer.getSlot(this.slot + 36).getStack();
    }

    public void onTower() {
        boolean isKeyDown;
        boolean bl = isKeyDown = Class278.mc.gameSettings.keyBindJump.isKeyDown() && Class278.mc.gameSettings.keyBindSprint.isKeyDown();
        if (isKeyDown) {
            ++this.holdticks;
            this.towers = true;
            ++this.ticks;
            if (Class278.mc.thePlayer.onGround) {
                this.ticks = 0;
            }
            if (this.holdticks < 19) {
                Class278.mc.thePlayer.motionY = 0.41965;
                float speed = 0.241f;
                EntityPlayerSP thePlayer = Class278.mc.thePlayer;
                float rotationYaw = thePlayer.rotationYaw;
                if (Class210.isMoveKeybind()) {
                    float yaw = (float)Math.toRadians(rotationYaw);
                    thePlayer.motionX = -Math.sin(yaw) * (double)speed;
                    thePlayer.motionZ = Math.cos(yaw) * (double)speed;
                }
                if (this.ticks == 1) {
                    Class278.mc.thePlayer.motionY = 0.33;
                }
                if (this.ticks == 2) {
                    Class278.mc.thePlayer.motionY = 1.0 - Class278.mc.thePlayer.posY % 1.0;
                }
                if (this.ticks == 3) {
                    this.ticks = 0;
                }
            }
        }
        if (this.ticks >= 3) {
            this.ticks = 0;
        }
        if (!(!this.towers || Class278.mc.gameSettings.keyBindJump.isKeyDown() && Class278.mc.gameSettings.keyBindSprint.isKeyDown())) {
            Class278.mc.thePlayer.motionX = 0.0;
            this.ticks = 0;
            this.towers = false;
        }
        if (this.holdticks > 23 && Class278.mc.thePlayer.onGround) {
            this.holdticks = 0;
        }
    }

    @Class14
    public void onStrafe(Class32 event) {
        if (this.autojump.isEnabled() && Class278.mc.thePlayer.onGround && Class210.isMoving() && !Class278.mc.gameSettings.keyBindJump.isKeyDown()) {
            Class278.mc.thePlayer.jump();
        }
    }

    @Class14
    private void onTick(Class52 event) {
        Class278 scaffold;
        if (Class278.mc.thePlayer == null) {
            return;
        }
        if (this.slot < 0) {
            return;
        }
        if (!this.telly.isEnabled()) {
            this.canTellyPlace = true;
        }
        this.countscale = (scaffold = Class262.getModule(Class278.class)).isState() ? (double) Class461.moveUD((float)this.countscale, 1.0f, (float)(30.0 * Class465.deltaTime() + 2.0), (float)(20.0 * Class465.deltaTime() + 2.0)) : (double) Class461.moveUD((float)this.countscale, 0.0f, (float)(30.0 * Class465.deltaTime()), (float)(20.0 * Class465.deltaTime()));
        this.search();
    }

    @Class14
    private void onPlace(Class48 event) {
        this.slot = this.getBlockSlot();
        if (this.slot < 0) {
            return;
        }
        if (!this.telly.isEnabled()) {
            Class278.mc.thePlayer.setSprinting(this.sprintValue.isEnabled());
            Class278.mc.gameSettings.keyBindSprint.pressed = false;
        }
        event.setCancelled(true);
        if (Class278.mc.thePlayer == null) {
            return;
        }
        if (this.raycast.isEnabled()) {
            // empty if block
        }
        this.place();
        mc.sendClickBlockToController(Class278.mc.currentScreen == null && Class278.mc.gameSettings.keyBindAttack.isKeyDown() && Class278.mc.inGameHasFocus);
    }

    @Class14
    private void onUpdate(Class50 event) {
        this.slot = this.getBlockSlot();
        if (this.slot < 0) {
            return;
        }
        if (this.fakeslot.isEnabled()) {
            Class1.instance.Method12().startSpoofing(this.oldSlot);
        }
        Class278.mc.thePlayer.inventory.currentItem = this.slot;
        this.search();
        if (this.telly.isEnabled()) {
            if (this.canTellyPlace && !Class278.mc.thePlayer.onGround && Class210.isMoving()) {
                Class278.mc.thePlayer.setSprinting(false);
            }
            boolean bl = this.canTellyPlace = Class278.mc.thePlayer.offGroundTicks >= 1;
        }
        if (!this.canTellyPlace) {
            return;
        }
        if (this.data != null) {
            float yaw = Class237.getRotationBlock(this.data)[0];
            float pitch = Class237.getRotationBlock(this.data)[1];
            Class20.setRotations(new Class247(yaw, pitch), 180.0, Class167.NORMAL);
        }
    }

    private void place() {
        if (!this.canTellyPlace) {
            return;
        }
        this.slot = this.getBlockSlot();
        if (this.slot < 0) {
            return;
        }
        if (this.data != null) {
            if (this.facing == null) {
                return;
            }
            Vec3 hitvec = Class278.getVec3(this.data, this.facing);
            if (Class278.validateBlockRange(hitvec) && Class278.mc.playerController.onPlayerRightClick(Class278.mc.thePlayer, Class278.mc.theWorld, Class278.mc.thePlayer.getCurrentEquippedItem(), this.data, this.facing, hitvec)) {
                if (this.swing.isEnabled()) {
                    Class278.mc.thePlayer.swingItem();
                } else {
                    Class278.mc.thePlayer.sendQueue.addToSendQueue(new Class331());
                }
            }
        }
    }

    private void search() {
        EntityPlayerSP player = Class278.mc.thePlayer;
        WorldClient world = Class278.mc.theWorld;
        double posX = player.posX;
        double posZ = player.posZ;
        double minY = player.getEntityBoundingBox().minY;
        Vec3 vec3 = Class278.getPlacePossibility(0.0, 0.0, 0.0, true);
        if (vec3 == null) {
            return;
        }
        BlockPos pos = new BlockPos(vec3.xCoord, vec3.yCoord, vec3.zCoord);
        if (!Class278.mc.theWorld.getBlockState(pos).getBlock().getMaterial().isReplaceable()) {
            return;
        }
        for (EnumFacing facingType : EnumFacing.values()) {
            BlockPos neighbor = pos.offset(facingType);
            if (!Class278.canBeClick(neighbor)) continue;
            Vec3 dirVec = new Vec3(facingType.getDirectionVec());
            for (double xSearch = 0.5; xSearch <= 0.5; xSearch += 0.01) {
                for (double ySearch = 0.5; ySearch <= 0.5; ySearch += 0.01) {
                    double zSearch = 0.5;
                    while (zSearch <= 0.5) {
                        Vec3 eyesPos = new Vec3(posX, minY + (double) Class278.mc.thePlayer.getEyeHeight(), posZ);
                        Vec3 posVec = new Vec3(pos).addVector(xSearch, ySearch, zSearch);
                        Vec3 hitVec = posVec.add(new Vec3(dirVec.xCoord * 0.5, dirVec.yCoord * 0.5, dirVec.zCoord * 0.5));
                        double distanceSqPosVec = eyesPos.squareDistanceTo(posVec);
                        if (eyesPos.distanceTo(hitVec) > 5.0 || distanceSqPosVec > eyesPos.squareDistanceTo(posVec.add(dirVec)) || world.rayTraceBlocks(eyesPos, hitVec, false, true, false) != null) {
                            zSearch += 0.01;
                            continue;
                        }
                        double diffX = hitVec.xCoord - eyesPos.xCoord;
                        double diffY = hitVec.yCoord - eyesPos.yCoord;
                        double diffZ = hitVec.zCoord - eyesPos.zCoord;
                        double diffXZ = Math.sqrt(diffX * diffX + diffZ * diffZ);
                        if (facingType != EnumFacing.UP && facingType != EnumFacing.DOWN) {
                            double d = facingType == EnumFacing.NORTH || facingType == EnumFacing.SOUTH ? Math.abs(diffZ) : Math.abs(diffX);
                            if (d < 0.0) {
                                zSearch += 0.01;
                                continue;
                            }
                        }
                        Class247 rotation = new Class247(MathHelper.wrapAngleTo180_float((float)(Math.toDegrees(MathHelper.atan2(diffZ, diffX)) - 90.0)), MathHelper.wrapAngleTo180_float((float)(-Math.toDegrees(MathHelper.atan2(diffY, diffXZ)))));
                        Vec3 rotVec = Class278.getVectorForRotation(rotation);
                        Vec3 vector = eyesPos.addVector(rotVec.xCoord * 5.0, rotVec.yCoord * 5.0, rotVec.zCoord * 5.0);
                        MovingObjectPosition obj = Class278.mc.theWorld.rayTraceBlocks(eyesPos, vector, false, false, true);
                        if (obj == null) continue;
                        if (obj.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK || obj.getBlockPos().getX() != neighbor.getX() || obj.getBlockPos().getZ() != neighbor.getZ() || obj.getBlockPos().getY() != neighbor.getY() || obj.sideHit != facingType.getOpposite()) {
                            zSearch += 0.01;
                            continue;
                        }
                        this.data = neighbor;
                        this.facing = facingType.getOpposite();
                        return;
                    }
                }
            }
        }
    }

    public static boolean canBeClick(BlockPos pos) {
        return Class278.mc.theWorld.getBlockState(pos).getBlock().canCollideCheck(Class278.mc.theWorld.getBlockState(pos), false) && Class278.mc.theWorld.getWorldBorder().contains(pos);
    }

    public static Vec3 getPlacePossibility(double offsetX, double offsetY, double offsetZ, boolean searchUP) {
        ArrayList<Vec3> possibilities = new ArrayList();
        int range = (int)(6.0 + Math.abs(offsetX) + Math.abs(offsetZ));
        Vec3 playerPos = new Vec3(mc.thePlayer.posX + offsetX, mc.thePlayer.posY - 1.0 + offsetY, mc.thePlayer.posZ + offsetZ);
        if (!(mc.theWorld.getBlockState(new BlockPos(playerPos)).getBlock() instanceof BlockAir)) {
            return playerPos;
        } else {
            for(int x = -range; x <= range; ++x) {
                for(int y = -range; y <= 0; ++y) {
                    for(int z = -range; z <= range; ++z) {
                        Block block = mc.theWorld.getBlockState(new BlockPos(mc.thePlayer).add(x, y, z)).getBlock();
                        if (!(block instanceof BlockAir)) {
                            for(int x2 = -1; x2 <= 1; x2 += 2) {
                                possibilities.add(
                                        new Vec3(mc.thePlayer.posX + (double)x + (double)x2, mc.thePlayer.posY + (double)y, mc.thePlayer.posZ + (double)z)
                                );
                            }

                            for(int y2 = -1; y2 <= 1; y2 += 2) {
                                possibilities.add(
                                        new Vec3(mc.thePlayer.posX + (double)x, mc.thePlayer.posY + (double)y + (double)y2, mc.thePlayer.posZ + (double)z)
                                );
                            }

                            for(int z2 = -1; z2 <= 1; z2 += 2) {
                                possibilities.add(
                                        new Vec3(mc.thePlayer.posX + (double)x, mc.thePlayer.posY + (double)y, mc.thePlayer.posZ + (double)z + (double)z2)
                                );
                            }
                        }
                    }
                }
            }

            possibilities.removeIf(
                    vec3 -> {
                        BlockPos blockPos = new BlockPos(vec3.xCoord, vec3.yCoord, vec3.zCoord);
                        if (mc.thePlayer.getPosition().getX() == blockPos.getX()
                                && mc.thePlayer.getPosition().getY() == blockPos.getY()
                                && mc.thePlayer.getPosition().getZ() == blockPos.getZ()) {
                            return true;
                        } else {
                            BlockPos position = new BlockPos(vec3.xCoord, vec3.yCoord, vec3.zCoord);
                            return mc.thePlayer.getDistance((double)position.getX() + 0.5, (double)position.getY() + 0.5, (double)position.getZ() + 0.5) > 6.0
                                    || !(mc.theWorld.getBlockState(new BlockPos(vec3.xCoord, vec3.yCoord, vec3.zCoord)).getBlock() instanceof BlockAir);
                        }
                    }
            );
            possibilities.removeIf(
                    e -> {
                        boolean hasBlock = false;

                        for(EnumFacing facing : EnumFacing.values()) {
                            BlockPos position;
                            if (facing != EnumFacing.UP
                                    && (facing != EnumFacing.DOWN || searchUP)
                                    && mc.theWorld.getBlockState((position = new BlockPos(e.xCoord, e.yCoord, e.zCoord)).offset(facing)) != null
                                    && !(mc.theWorld.getBlockState(position.offset(facing)).getBlock() instanceof BlockAir)) {
                                BlockPos facePos = position.offset(facing);
                                if (mc.thePlayer.getDistance((double)position.getX() + 0.5, (double)position.getY() + 0.5, (double)position.getZ() + 0.5)
                                        > mc.thePlayer.getDistance((double)facePos.getX() + 0.5, (double)facePos.getY() + 0.5, (double)facePos.getZ() + 0.5)) {
                                    return true;
                                }

                                hasBlock = true;
                            }
                        }

                        if (e.yCoord > mc.thePlayer.getEntityBoundingBox().minY && !searchUP) {
                            return true;
                        } else {
                            return !hasBlock;
                        }
                    }
            );
            if (possibilities.isEmpty()) {
                return null;
            } else {
                possibilities.sort(Comparator.comparingDouble(vec3 -> {
                    double d0 = mc.thePlayer.posX + offsetX - vec3.xCoord;
                    double d1 = mc.thePlayer.posY - 1.0 + offsetY - vec3.yCoord;
                    double d2 = mc.thePlayer.posZ + offsetZ - vec3.zCoord;
                    return MathHelper.sqrt_double(d0 * d0 + d1 * d1 + d2 * d2);
                }));
                return possibilities.getFirst();
            }
        }
    }

    public static Vec3 getVectorForRotation(Class247 rotation) {
        float yawCos = (float)Math.cos(-rotation.x * ((float)Math.PI / 180) - (float)Math.PI);
        float yawSin = (float)Math.sin(-rotation.x * ((float)Math.PI / 180) - (float)Math.PI);
        float pitchCos = (float)(-Math.cos(-rotation.y * ((float)Math.PI / 180)));
        float pitchSin = (float)Math.sin(-rotation.y * ((float)Math.PI / 180));
        return new Vec3(yawSin * pitchCos, pitchSin, yawCos * pitchCos);
    }

    public int getBlockSlot() {
        for (int i = 0; i < 9; ++i) {
            if (!Class278.mc.thePlayer.inventoryContainer.getSlot(i + 36).getHasStack() || !(Class278.mc.thePlayer.inventoryContainer.getSlot(i + 36).getStack().getItem() instanceof ItemBlock)) continue;
            return i;
        }
        return -1;
    }

    public int getBlockCount() {
        int n = 0;
        for (int i = 36; i < 45; ++i) {
            if (!Class278.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack()) continue;
            ItemStack stack = Class278.mc.thePlayer.inventoryContainer.getSlot(i).getStack();
            Item item = stack.getItem();
            if (!(stack.getItem() instanceof ItemBlock) || !this.isValid(item)) continue;
            n += stack.stackSize;
        }
        return n;
    }

    public boolean isValid(Item item) {
        return item instanceof ItemBlock && !invalidBlocks.contains(((ItemBlock)item).getBlock());
    }

    private void getBlock(int switchSlot) {
        for (int i = 9; i < 45; ++i) {
            ItemStack is;
            if (!Class278.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack() || Class278.mc.currentScreen != null && !(Class278.mc.currentScreen instanceof GuiInventory) || !((is = Class278.mc.thePlayer.inventoryContainer.getSlot(i).getStack()).getItem() instanceof ItemBlock) || !this.isValid(is.getItem())) continue;
            if (36 + switchSlot == i) break;
            Class219.swap(i, switchSlot);
            break;
        }
    }

    int getBestSpoofSlot() {
        int spoofSlot = 5;
        for (int i = 36; i < 45; ++i) {
            if (Class278.mc.thePlayer.inventoryContainer.getSlot(i).getHasStack()) continue;
            spoofSlot = i - 36;
            break;
        }
        return spoofSlot;
    }

    private static boolean validateBlockRange(Vec3 pos) {
        double z;
        if (pos == null) {
            return false;
        }
        double x = pos.xCoord - player.posX;
        player = Class278.mc.thePlayer;
        double y = pos.yCoord - (player.posY + (double)player.getEyeHeight());
        return StrictMath.sqrt(x * x + y * y + (z = pos.zCoord - player.posZ) * z) <= 5.0;
    }

    public static Vec3 getVec3(BlockPos pos, EnumFacing face) {
        double x = (double)pos.getX() + 0.5;
        double y = (double)pos.getY() + 0.5;
        double z = (double)pos.getZ() + 0.5;
        if (face == EnumFacing.UP || face == EnumFacing.DOWN) {
            x += Class171.getRandomInRange(0.3, -0.3);
            z += Class171.getRandomInRange(0.3, -0.3);
        } else {
            y += 0.08;
        }
        if (face == EnumFacing.WEST || face == EnumFacing.EAST) {
            z += Class171.getRandomInRange(0.3, -0.3);
        }
        if (face == EnumFacing.SOUTH || face == EnumFacing.NORTH) {
            x += Class171.getRandomInRange(0.3, -0.3);
        }
        return new Vec3(x, y, z);
    }

    private void drawItemStack(ItemStack itemStack, float x, float y) {
        GlStateManager.pushMatrix();
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        RenderHelper.enableGUIStandardItemLighting();
        mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, (int)x, (int)y);
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableBlend();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.popMatrix();
    }

    public int getSlot() {
        return this.slot;
    }
}

