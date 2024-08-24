package net.minecraft.client.entity;

import Rename.Class13;
import Rename.Class49;
import Rename.Class50;
import Rename.Class26;
import Rename.Class27;
import Rename.Class31;
import Rename.Class264;
import Rename.Class267;
import Rename.Class247;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.MovingSoundMinecartRiding;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiCommandBlock;
import net.minecraft.client.gui.GuiEnchantment;
import net.minecraft.client.gui.GuiHopper;
import net.minecraft.client.gui.GuiMerchant;
import net.minecraft.client.gui.GuiRepair;
import net.minecraft.client.gui.GuiScreenBook;
import net.minecraft.client.gui.inventory.GuiBeacon;
import net.minecraft.client.gui.inventory.GuiBrewingStand;
import net.minecraft.client.gui.inventory.GuiChest;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.client.gui.inventory.GuiDispenser;
import net.minecraft.client.gui.inventory.GuiEditSign;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.client.gui.inventory.GuiScreenHorseInventory;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import Rename.Class362;
import Rename.Class354;
import Rename.Class365;
import Rename.Class331;
import Rename.Class364;
import Rename.Class332;
import Rename.Class363;
import Rename.Class335;
import Rename.Class337;
import net.minecraft.potion.Potion;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatFileWriter;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MovementInput;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IInteractionObject;
import net.minecraft.world.World;

public class EntityPlayerSP
extends AbstractClientPlayer {
    public final NetHandlerPlayClient sendQueue;
    private final StatFileWriter statWriter;
    public float renderPitchHead;
    public float prevRenderPitchHead;
    public int offGroundTicks;
    private double lastReportedPosX;
    private double lastReportedPosY;
    private double lastReportedPosZ;
    private float lastReportedYaw;
    private float lastReportedPitch;
    private boolean serverSneakState;
    public boolean serverSprintState;
    public int positionUpdateTicks;
    private boolean hasValidHealth;
    private String clientBrand;
    public MovementInput movementInput;
    protected Minecraft mc;
    public int sprintToggleTimer;
    public int sprintingTicksLeft;
    public float renderArmYaw;
    public float renderArmPitch;
    public float prevRenderArmYaw;
    public float prevRenderArmPitch;
    private int horseJumpPowerCounter;
    private float horseJumpPower;
    public float timeInPortal;
    public float prevTimeInPortal;

    public EntityPlayerSP(Minecraft mcIn, World worldIn, NetHandlerPlayClient netHandler, StatFileWriter statFile) {
        super(worldIn, netHandler.getGameProfile());
        this.sendQueue = netHandler;
        this.statWriter = statFile;
        this.mc = mcIn;
        this.dimension = 0;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        return false;
    }

    @Override
    public void heal(float healAmount) {
    }

    @Override
    public void mountEntity(Entity entityIn) {
        super.mountEntity(entityIn);
        if (entityIn instanceof EntityMinecart) {
            this.mc.getSoundHandler().playSound(new MovingSoundMinecartRiding(this, (EntityMinecart)entityIn));
        }
    }

    @Override
    public void onUpdate() {
        Class13.Method7(new Class50());
        if (this.worldObj.isBlockLoaded(new BlockPos(this.posX, 0.0, this.posZ))) {
            this.prevRenderPitchHead = this.renderPitchHead;
            this.renderPitchHead = this.rotationPitch;
            super.onUpdate();
            if (this.isRiding()) {
                this.sendQueue.addToSendQueue(new Class354.C05PacketPlayerLook(this.rotationYaw, this.rotationPitch, this.onGround));
                this.sendQueue.addToSendQueue(new Class332(this.moveStrafing, this.moveForward, this.movementInput.jump, this.movementInput.sneak));
            } else {
                this.onUpdateWalkingPlayer();
            }
        }
        Class13.Method7(new Class49());
    }

    public void onUpdateWalkingPlayer() {
        boolean flag1;
        this.offGroundTicks = this.onGround ? 0 : ++this.offGroundTicks;
        Class27 preEvent = new Class27(this.rotationYaw, this.rotationPitch, this.onGround);
        Class13.Method7(preEvent);
        float yaw = preEvent.getYaw();
        float pitch = preEvent.getPitch();
        boolean flag = this.isSprinting();
        if (flag != this.serverSprintState) {
            if (flag) {
                this.sendQueue.addToSendQueue(new Class364(this, Class364.Action.START_SPRINTING));
            } else {
                this.sendQueue.addToSendQueue(new Class364(this, Class364.Action.STOP_SPRINTING));
            }
            this.serverSprintState = flag;
        }
        if ((flag1 = this.isSneaking()) != this.serverSneakState) {
            if (flag1) {
                this.sendQueue.addToSendQueue(new Class364(this, Class364.Action.START_SNEAKING));
            } else {
                this.sendQueue.addToSendQueue(new Class364(this, Class364.Action.STOP_SNEAKING));
            }
            this.serverSneakState = flag1;
        }
        if (this.isCurrentViewEntity()) {
            boolean flag3;
            double d0 = this.posX - this.lastReportedPosX;
            double d1 = this.getEntityBoundingBox().minY - this.lastReportedPosY;
            double d2 = this.posZ - this.lastReportedPosZ;
            double d3 = yaw - this.lastReportedYaw;
            double d4 = pitch - this.lastReportedPitch;
            boolean flag2 = d0 * d0 + d1 * d1 + d2 * d2 > 9.0E-4 || this.positionUpdateTicks >= 20;
            boolean bl = flag3 = d3 != 0.0 || d4 != 0.0;
            if (this.ridingEntity == null) {
                if (flag2 && flag3) {
                    this.sendQueue.addToSendQueue(new Class354.C06PacketPlayerPosLook(this.posX, this.getEntityBoundingBox().minY, this.posZ, yaw, pitch, this.onGround));
                } else if (flag2) {
                    this.sendQueue.addToSendQueue(new Class354.C04PacketPlayerPosition(this.posX, this.getEntityBoundingBox().minY, this.posZ, this.onGround));
                } else if (flag3) {
                    this.sendQueue.addToSendQueue(new Class354.C05PacketPlayerLook(yaw, pitch, this.onGround));
                } else {
                    this.sendQueue.addToSendQueue(new Class354(this.onGround));
                }
            } else {
                this.sendQueue.addToSendQueue(new Class354.C06PacketPlayerPosLook(this.motionX, -999.0, this.motionZ, yaw, pitch, this.onGround));
                flag2 = false;
            }
            Class267.processPackets();
            ++this.positionUpdateTicks;
            if (flag2) {
                this.lastReportedPosX = this.posX;
                this.lastReportedPosY = this.getEntityBoundingBox().minY;
                this.lastReportedPosZ = this.posZ;
                this.positionUpdateTicks = 0;
            }
            if (flag3) {
                this.lastReportedYaw = yaw;
                this.lastReportedPitch = pitch;
            }
        }
        Class26 postEvent = new Class26();
        Class13.Method7(postEvent);
    }

    @Override
    public EntityItem dropOneItem(boolean dropAll) {
        Class365.Action c07packetplayerdigging$action = dropAll ? Class365.Action.DROP_ALL_ITEMS : Class365.Action.DROP_ITEM;
        this.sendQueue.addToSendQueue(new Class365(c07packetplayerdigging$action, BlockPos.ORIGIN, EnumFacing.DOWN));
        return null;
    }

    @Override
    protected void joinEntityItemWithWorld(EntityItem itemIn) {
    }

    public void sendChatMessage(String message) {
        this.sendQueue.addToSendQueue(new Class362(message));
    }

    @Override
    public void swingItem() {
        super.swingItem();
        this.sendQueue.addToSendQueue(new Class331());
    }

    @Override
    public void respawnPlayer() {
        this.sendQueue.addToSendQueue(new Class337(Class337.EnumState.PERFORM_RESPAWN));
    }

    @Override
    protected void damageEntity(DamageSource damageSrc, float damageAmount) {
        if (!this.isEntityInvulnerable(damageSrc)) {
            this.setHealth(this.getHealth() - damageAmount);
        }
    }

    @Override
    public void closeScreen() {
        this.sendQueue.addToSendQueue(new Class363(this.openContainer.windowId));
        this.closeScreenAndDropStack();
    }

    public void closeScreenAndDropStack() {
        this.inventory.setItemStack(null);
        super.closeScreen();
        this.mc.displayGuiScreen(null);
    }

    public void setPlayerSPHealth(float health) {
        if (this.hasValidHealth) {
            float f = this.getHealth() - health;
            if (f <= 0.0f) {
                this.setHealth(health);
                if (f < 0.0f) {
                    this.hurtResistantTime = this.maxHurtResistantTime / 2;
                }
            } else {
                this.lastDamage = f;
                this.setHealth(this.getHealth());
                this.hurtResistantTime = this.maxHurtResistantTime;
                this.damageEntity(DamageSource.generic, f);
                this.maxHurtTime = 10;
                this.hurtTime = 10;
            }
        } else {
            this.setHealth(health);
            this.hasValidHealth = true;
        }
    }

    @Override
    public void addStat(StatBase stat, int amount) {
        if (stat != null && stat.isIndependent) {
            super.addStat(stat, amount);
        }
    }

    @Override
    public void sendPlayerAbilities() {
        this.sendQueue.addToSendQueue(new Class335(this.capabilities));
    }

    @Override
    public boolean isUser() {
        return true;
    }

    protected void sendHorseJump() {
        this.sendQueue.addToSendQueue(new Class364(this, Class364.Action.RIDING_JUMP, (int)(this.getHorseJumpPower() * 100.0f)));
    }

    public void sendHorseInventory() {
        this.sendQueue.addToSendQueue(new Class364(this, Class364.Action.OPEN_INVENTORY));
    }

    public void setClientBrand(String brand) {
        this.clientBrand = brand;
    }

    public String getClientBrand() {
        return this.clientBrand;
    }

    public StatFileWriter getStatFileWriter() {
        return this.statWriter;
    }

    @Override
    public void addChatComponentMessage(IChatComponent chatComponent) {
        this.mc.ingameGUI.getChatGUI().printChatMessage(chatComponent);
    }

    @Override
    protected boolean pushOutOfBlocks(double x, double y, double z) {
        if (this.noClip) {
            return false;
        }
        BlockPos blockpos = new BlockPos(x, y, z);
        double d0 = x - (double)blockpos.getX();
        double d1 = z - (double)blockpos.getZ();
        if (!this.isOpenBlockSpace(blockpos)) {
            int i = -1;
            double d2 = 9999.0;
            if (this.isOpenBlockSpace(blockpos.west()) && d0 < d2) {
                d2 = d0;
                i = 0;
            }
            if (this.isOpenBlockSpace(blockpos.east()) && 1.0 - d0 < d2) {
                d2 = 1.0 - d0;
                i = 1;
            }
            if (this.isOpenBlockSpace(blockpos.north()) && d1 < d2) {
                d2 = d1;
                i = 4;
            }
            if (this.isOpenBlockSpace(blockpos.south()) && 1.0 - d1 < d2) {
                d2 = 1.0 - d1;
                i = 5;
            }
            float f = 0.1f;
            if (i == 0) {
                this.motionX = -f;
            }
            if (i == 1) {
                this.motionX = f;
            }
            if (i == 4) {
                this.motionZ = -f;
            }
            if (i == 5) {
                this.motionZ = f;
            }
        }
        return false;
    }

    private boolean isOpenBlockSpace(BlockPos pos) {
        return !this.worldObj.getBlockState(pos).getBlock().isNormalCube() && !this.worldObj.getBlockState(pos.up()).getBlock().isNormalCube();
    }

    @Override
    public void setSprinting(boolean sprinting) {
        super.setSprinting(sprinting);
        this.sprintingTicksLeft = sprinting ? 600 : 0;
    }

    public void setXPStats(float currentXP, int maxXP, int level) {
        this.experience = currentXP;
        this.experienceTotal = maxXP;
        this.experienceLevel = level;
    }

    @Override
    public void addChatMessage(IChatComponent component) {
        this.mc.ingameGUI.getChatGUI().printChatMessage(component);
    }

    @Override
    public boolean canCommandSenderUseCommand(int permLevel, String commandName) {
        return permLevel <= 0;
    }

    @Override
    public BlockPos getPosition() {
        return new BlockPos(this.posX + 0.5, this.posY + 0.5, this.posZ + 0.5);
    }

    @Override
    public void playSound(String name, float volume, float pitch) {
        this.worldObj.playSound(this.posX, this.posY, this.posZ, name, volume, pitch, false);
    }

    @Override
    public boolean isServerWorld() {
        return true;
    }

    public boolean isRidingHorse() {
        return this.ridingEntity != null && this.ridingEntity instanceof EntityHorse && ((EntityHorse)this.ridingEntity).isHorseSaddled();
    }

    public float getHorseJumpPower() {
        return this.horseJumpPower;
    }

    @Override
    public void openEditSign(TileEntitySign signTile) {
        this.mc.displayGuiScreen(new GuiEditSign(signTile));
    }

    @Override
    public void openEditCommandBlock(CommandBlockLogic cmdBlockLogic) {
        this.mc.displayGuiScreen(new GuiCommandBlock(cmdBlockLogic));
    }

    @Override
    public void displayGUIBook(ItemStack bookStack) {
        Item item = bookStack.getItem();
        if (item == Items.writable_book) {
            this.mc.displayGuiScreen(new GuiScreenBook(this, bookStack, true));
        }
    }

    @Override
    public void displayGUIChest(IInventory chestInventory) {
        String s;
        String string = s = chestInventory instanceof IInteractionObject ? ((IInteractionObject)((Object)chestInventory)).getGuiID() : "minecraft:container";
        if ("minecraft:chest".equals(s)) {
            this.mc.displayGuiScreen(new GuiChest(this.inventory, chestInventory));
        } else if ("minecraft:hopper".equals(s)) {
            this.mc.displayGuiScreen(new GuiHopper(this.inventory, chestInventory));
        } else if ("minecraft:furnace".equals(s)) {
            this.mc.displayGuiScreen(new GuiFurnace(this.inventory, chestInventory));
        } else if ("minecraft:brewing_stand".equals(s)) {
            this.mc.displayGuiScreen(new GuiBrewingStand(this.inventory, chestInventory));
        } else if ("minecraft:beacon".equals(s)) {
            this.mc.displayGuiScreen(new GuiBeacon(this.inventory, chestInventory));
        } else if (!"minecraft:dispenser".equals(s) && !"minecraft:dropper".equals(s)) {
            this.mc.displayGuiScreen(new GuiChest(this.inventory, chestInventory));
        } else {
            this.mc.displayGuiScreen(new GuiDispenser(this.inventory, chestInventory));
        }
    }

    @Override
    public void displayGUIHorse(EntityHorse horse, IInventory horseInventory) {
        this.mc.displayGuiScreen(new GuiScreenHorseInventory(this.inventory, horseInventory, horse));
    }

    @Override
    public void displayGui(IInteractionObject guiOwner) {
        String s = guiOwner.getGuiID();
        if ("minecraft:crafting_table".equals(s)) {
            this.mc.displayGuiScreen(new GuiCrafting(this.inventory, this.worldObj));
        } else if ("minecraft:enchanting_table".equals(s)) {
            this.mc.displayGuiScreen(new GuiEnchantment(this.inventory, this.worldObj, guiOwner));
        } else if ("minecraft:anvil".equals(s)) {
            this.mc.displayGuiScreen(new GuiRepair(this.inventory, this.worldObj));
        }
    }

    @Override
    public void displayVillagerTradeGui(IMerchant villager) {
        this.mc.displayGuiScreen(new GuiMerchant(this.inventory, villager, this.worldObj));
    }

    @Override
    public void onCriticalHit(Entity entityHit) {
        this.mc.effectRenderer.emitParticleAtEntity(entityHit, EnumParticleTypes.CRIT);
    }

    @Override
    public void onEnchantmentCritical(Entity entityHit) {
        this.mc.effectRenderer.emitParticleAtEntity(entityHit, EnumParticleTypes.CRIT_MAGIC);
    }

    @Override
    public boolean isSneaking() {
        boolean flag = this.movementInput != null ? this.movementInput.sneak : false;
        return flag && !this.sleeping;
    }

    public boolean isMoving() {
        return this.moveForward != 0.0f || this.moveStrafing != 0.0f;
    }

    @Override
    public void updateEntityActionState() {
        super.updateEntityActionState();
        if (this.isCurrentViewEntity()) {
            this.moveStrafing = this.movementInput.moveStrafe;
            this.moveForward = this.movementInput.moveForward;
            this.isJumping = this.movementInput.jump;
            this.prevRenderArmYaw = this.renderArmYaw;
            this.prevRenderArmPitch = this.renderArmPitch;
            this.renderArmPitch = (float)((double)this.renderArmPitch + (double)(this.rotationPitch - this.renderArmPitch) * 0.5);
            this.renderArmYaw = (float)((double)this.renderArmYaw + (double)(this.rotationYaw - this.renderArmYaw) * 0.5);
        }
    }

    protected boolean isCurrentViewEntity() {
        return this.mc.getRenderViewEntity() == this;
    }

    @Override
    public void onLivingUpdate() {
        boolean flag3;
        if (this.sprintingTicksLeft > 0) {
            --this.sprintingTicksLeft;
            if (this.sprintingTicksLeft == 0) {
                this.setSprinting(false);
            }
        }
        if (this.sprintToggleTimer > 0) {
            --this.sprintToggleTimer;
        }
        this.prevTimeInPortal = this.timeInPortal;
        if (this.inPortal) {
            if (this.mc.currentScreen != null && !this.mc.currentScreen.doesGuiPauseGame()) {
                this.mc.displayGuiScreen(null);
            }
            if (this.timeInPortal == 0.0f) {
                this.mc.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("portal.trigger"), this.rand.nextFloat() * 0.4f + 0.8f));
            }
            this.timeInPortal += 0.0125f;
            if (this.timeInPortal >= 1.0f) {
                this.timeInPortal = 1.0f;
            }
            this.inPortal = false;
        } else if (this.isPotionActive(Potion.confusion) && this.getActivePotionEffect(Potion.confusion).getDuration() > 60) {
            this.timeInPortal += 0.006666667f;
            if (this.timeInPortal > 1.0f) {
                this.timeInPortal = 1.0f;
            }
        } else {
            if (this.timeInPortal > 0.0f) {
                this.timeInPortal -= 0.05f;
            }
            if (this.timeInPortal < 0.0f) {
                this.timeInPortal = 0.0f;
            }
        }
        if (this.timeUntilPortal > 0) {
            --this.timeUntilPortal;
        }
        boolean flag = this.movementInput.jump;
        boolean flag1 = this.movementInput.sneak;
        float f = 0.8f;
        boolean flag2 = this.movementInput.moveForward >= f;
        this.movementInput.updatePlayerMoveState();
        Class31 event = new Class31();
        Class13.Method7(event);
        if ((this.isUsingItem() || Class264.eating) && !this.isRiding() && !event.isCancelled()) {
            this.movementInput.moveStrafe *= 0.2f;
            this.movementInput.moveForward *= 0.2f;
            this.sprintToggleTimer = 0;
        }
        this.pushOutOfBlocks(this.posX - (double)this.width * 0.35, this.getEntityBoundingBox().minY + 0.5, this.posZ + (double)this.width * 0.35);
        this.pushOutOfBlocks(this.posX - (double)this.width * 0.35, this.getEntityBoundingBox().minY + 0.5, this.posZ - (double)this.width * 0.35);
        this.pushOutOfBlocks(this.posX + (double)this.width * 0.35, this.getEntityBoundingBox().minY + 0.5, this.posZ - (double)this.width * 0.35);
        this.pushOutOfBlocks(this.posX + (double)this.width * 0.35, this.getEntityBoundingBox().minY + 0.5, this.posZ + (double)this.width * 0.35);
        boolean bl = flag3 = (float)this.getFoodStats().getFoodLevel() > 6.0f || this.capabilities.allowFlying;
        if (!(!this.onGround || flag1 || flag2 || !(this.movementInput.moveForward >= f) || this.isSprinting() || !flag3 || this.isUsingItem() && !event.isCancelled() || this.isPotionActive(Potion.blindness))) {
            if (this.sprintToggleTimer <= 0 && !this.mc.gameSettings.keyBindSprint.isKeyDown()) {
                this.sprintToggleTimer = 7;
            } else {
                this.setSprinting(true);
            }
        }
        if (!this.isSprinting() && this.movementInput.moveForward >= f && flag3 && (!this.isUsingItem() || event.isCancelled()) && !this.isPotionActive(Potion.blindness) && this.mc.gameSettings.keyBindSprint.isKeyDown()) {
            this.setSprinting(true);
        }
        if (this.isSprinting() && (this.movementInput.moveForward < f || this.isCollidedHorizontally || !flag3)) {
            this.setSprinting(false);
        }
        if (this.capabilities.allowFlying) {
            if (this.mc.playerController.isSpectatorMode()) {
                if (!this.capabilities.isFlying) {
                    this.capabilities.isFlying = true;
                    this.sendPlayerAbilities();
                }
            } else if (!flag && this.movementInput.jump) {
                if (this.flyToggleTimer == 0) {
                    this.flyToggleTimer = 7;
                } else {
                    this.capabilities.isFlying = !this.capabilities.isFlying;
                    this.sendPlayerAbilities();
                    this.flyToggleTimer = 0;
                }
            }
        }
        if (this.capabilities.isFlying && this.isCurrentViewEntity()) {
            if (this.movementInput.sneak) {
                this.motionY -= (double)(this.capabilities.getFlySpeed() * 3.0f);
            }
            if (this.movementInput.jump) {
                this.motionY += (double)(this.capabilities.getFlySpeed() * 3.0f);
            }
        }
        if (this.isRidingHorse()) {
            if (this.horseJumpPowerCounter < 0) {
                ++this.horseJumpPowerCounter;
                if (this.horseJumpPowerCounter == 0) {
                    this.horseJumpPower = 0.0f;
                }
            }
            if (flag && !this.movementInput.jump) {
                this.horseJumpPowerCounter = -10;
                this.sendHorseJump();
            } else if (!flag && this.movementInput.jump) {
                this.horseJumpPowerCounter = 0;
                this.horseJumpPower = 0.0f;
            } else if (flag) {
                ++this.horseJumpPowerCounter;
                this.horseJumpPower = this.horseJumpPowerCounter < 10 ? (float)this.horseJumpPowerCounter * 0.1f : 0.8f + 2.0f / (float)(this.horseJumpPowerCounter - 9) * 0.1f;
            }
        } else {
            this.horseJumpPower = 0.0f;
        }
        super.onLivingUpdate();
        if (this.onGround && this.capabilities.isFlying && !this.mc.playerController.isSpectatorMode()) {
            this.capabilities.isFlying = false;
            this.sendPlayerAbilities();
        }
    }

    public Class247 getPreviousRotation() {
        return new Class247(this.lastReportedYaw, this.lastReportedPitch);
    }
}

