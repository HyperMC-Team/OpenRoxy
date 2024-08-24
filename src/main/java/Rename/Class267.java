package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.CopyOnWriteArrayList;

import net.minecraft.client.gui.GuiDownloadTerrain;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.login.server.S00PacketDisconnect;
import net.minecraft.network.login.server.S01PacketEncryptionRequest;
import net.minecraft.network.status.server.S00PacketServerInfo;
import net.minecraft.network.status.server.S01PacketPong;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.viamcp.vialoadingbase.ViaLoadingBase;
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
public class Class267
extends Class252 {
    private static final Class257 mode = new Class257("Mode", "GrimAC", "WatchDog", "GrimAC");
    private final Class254 badPacketsF = new Class254("BadPacketF", false);
    private final Class254 test = new Class254("Test", false);
    private final Class254 higherVersion = new Class254("Move 1.17+", false);
    private final Class254 fastBreak = new Class254("Fast Break", false);
    private final Class254 fabricatedPlace = new Class254("FabricatedPlace", true);
    private static boolean lastResult = false;
    int lastSlot;
    boolean lastSprinting;
    boolean c03Check;
    public static List<Packet<INetHandler>> storedPackets = new CopyOnWriteArrayList<Packet<INetHandler>>();
    public static ConcurrentLinkedDeque<Integer> pingPackets = new ConcurrentLinkedDeque();

    public Class267() {
        super("Disabler", Class263.Misc);
    }

    @Class14
    public void onWorld(Class55 event) {
        if (this.isNull()) {
            return;
        }
        this.lastSlot = -1;
        this.lastSprinting = false;
        this.c03Check = false;
    }

    @Class14
    public void onUpdate(Class50 event) {
        if (this.isNull()) {
            return;
        }
        if (!Class267.getGrimPost()) {
            Class267.processPackets();
        }
    }

    @Class14
    public void onMotion(Class27 event) {
        this.setSuffix(mode.getMode());
    }

    @Class14(value=0)
    public void onSend(Class35 event) {
        Packet<?> packet = event.getPacket();
        if (mode.is("GrimAC")) {
            Class360 pkt;
            Packet<?> packet2;
            if (this.badPacketsF.isEnabled() && packet instanceof Class364 c0b) {
                if (c0b.getAction() == Class364.Action.START_SPRINTING) {
                    if (this.lastSprinting) {
                        event.setCancelled(true);
                    }
                    this.lastSprinting = true;
                }
                if (c0b.getAction() == Class364.Action.STOP_SPRINTING) {
                    if (!this.lastSprinting) {
                        event.setCancelled(true);
                    }
                    this.lastSprinting = false;
                }
            }
            if (this.test.isEnabled() && (packet2 = event.getPacket()) instanceof Class360 && ((pkt = (Class360)packet2).getWindowId() <= 0 || pkt.getSlotId() >= 100 || pkt.getUsedButton() < 0)) {
                event.setCancelled(true);
            }
            if (this.higherVersion.isEnabled() && packet instanceof Class354 wrapped) {
                if (!(packet instanceof Class354.C06PacketPlayerPosLook)) {
                    if (wrapped.isMoving()) {
                        mc.getNetHandler().addToSendQueueUnregistered(new Class354.C06PacketPlayerPosLook(wrapped.getX(), wrapped.getY(), wrapped.getZ(), Class20.rotations.x, Class20.rotations.y, wrapped.isOnGround()));
                    } else if (wrapped.rotating) {
                        mc.getNetHandler().addToSendQueueUnregistered(new Class354.C06PacketPlayerPosLook(Class267.mc.thePlayer.posX, Class267.mc.thePlayer.posY, Class267.mc.thePlayer.posZ, wrapped.getYaw(), wrapped.getPitch(), wrapped.isOnGround()));
                    } else {
                        mc.getNetHandler().addToSendQueueUnregistered(new Class354.C06PacketPlayerPosLook(Class267.mc.thePlayer.posX, Class267.mc.thePlayer.posY, Class267.mc.thePlayer.posZ, Class20.rotations.x, Class20.rotations.y, wrapped.isOnGround()));
                    }
                }
            }
            if (this.fabricatedPlace.isEnabled() && event.getPacket() instanceof Class355) {
                ((Class355)event.getPacket()).facingX = 0.5f;
                ((Class355)event.getPacket()).facingY = 0.5f;
                ((Class355)event.getPacket()).facingZ = 0.5f;
            }
            if (packet instanceof Class333 c09) {
                int slot = c09.getSlotId();
                if (slot == this.lastSlot && slot != -1) {
                    event.setCancelled(true);
                }
                this.lastSlot = c09.getSlotId();
            }
            if (packet instanceof Class334 c02) {
                if (c02.getAction() == Class334.Action.INTERACT && c02.getEntityFromWorld(Class267.mc.theWorld) instanceof EntityPlayer) {
                    event.setCancelled(true);
                }
                if (c02.getAction() == Class334.Action.INTERACT_AT && c02.getEntityFromWorld(Class267.mc.theWorld) instanceof EntityPlayer) {
                    event.setCancelled(true);
                }
            }
            if (ViaLoadingBase.getInstance().getTargetVersion().isNewerThanOrEqualTo(ProtocolVersion.v1_9) && (packet instanceof Class360 || packet instanceof Class355 || packet instanceof Class364)) {
                Class212.sendC0F();
            }
            if (this.fastBreak.isEnabled() && event.getPacket() instanceof Class365 && ((Class365)event.getPacket()).getStatus() == Class365.Action.STOP_DESTROY_BLOCK) {
                BlockPos blockPos = ((Class365)event.getPacket()).getPosition();
                mc.getNetHandler().addToSendQueue(new Class365(Class365.Action.ABORT_DESTROY_BLOCK, blockPos, EnumFacing.DOWN));
            }
            if (Class20.active && packet instanceof Class354 c03) {
                if (c03.rotating) {
                    c03.setYaw(Class267.getRandomYaw(c03.getYaw()));
                }
            }
        }
    }

    @Class14
    public void onHigher(Class36 event) {
        Class364 c0b;
        Packet<?> packet = event.getPacket();
        if (packet instanceof Class364 && (c0b = (Class364)packet).getAction().equals(Class364.Action.START_SPRINTING)) {
            this.c03Check = true;
        }
        if (event.getPacket() instanceof Class354) {
            this.c03Check = false;
        }
    }

    public static float getRandomYaw(float requestedYaw) {
        int rand = Class171.getRandomInRange(1, 200);
        return requestedYaw + (float)(360 * rand);
    }

    public static boolean getGrimPost() {
        boolean result;
        Class267 dis = Class262.getModule(Class267.class);
        boolean bl = result = mode.is("GrimAC") && dis.isState() && Class267.mc.thePlayer != null && !(Class267.mc.currentScreen instanceof GuiDownloadTerrain) && !Class267.noPost();
        if (lastResult && !result) {
            lastResult = false;
            mc.addScheduledTask(Class267::processPackets);
        }
        lastResult = result;
        return lastResult;
    }

    public static boolean grimPostDelay(Packet<?> packet) {
        if (Class267.mc.thePlayer == null) {
            return false;
        }
        if (Class267.mc.currentScreen instanceof GuiDownloadTerrain) {
            return false;
        }
        if (packet instanceof S00PacketServerInfo) {
            return false;
        }
        if (packet instanceof S01PacketEncryptionRequest) {
            return false;
        }
        if (packet instanceof Class412) {
            return false;
        }
        if (packet instanceof S00PacketDisconnect) {
            return false;
        }
        if (packet instanceof Class413) {
            return false;
        }
        if (packet instanceof Class386) {
            return false;
        }
        if (packet instanceof S01PacketPong) {
            return false;
        }
        if (packet instanceof Class387) {
            return false;
        }
        if (packet instanceof Class411) {
            return false;
        }
        if (packet instanceof Class404) {
            return false;
        }
        if (packet instanceof Class375) {
            return false;
        }
        if (packet instanceof Class420) {
            return false;
        }
        if (packet instanceof Class378) {
            return false;
        }
        if (packet instanceof Class419) {
            return false;
        }
        if (packet instanceof Class388) {
            return false;
        }
        if (packet instanceof Class385) {
            return false;
        }
        if (packet instanceof Class341) {
            return false;
        }
        if (packet instanceof Class409) {
            return false;
        }
        if (packet instanceof Class349 sPacketEntityVelocity) {
            return sPacketEntityVelocity.getEntityID() == Class267.mc.thePlayer.getEntityId();
        }
        return packet instanceof Class384 || packet instanceof Class391 || packet instanceof Class351 || packet instanceof Class406 || packet instanceof Class415 || packet instanceof Class376 || packet instanceof Class382 || packet instanceof Class394 || packet instanceof Class402 || packet instanceof Class371 || packet instanceof Class398 || packet instanceof Class397 || packet instanceof Class345 || packet instanceof Class403 || packet instanceof Class393 || packet instanceof Class377 || packet instanceof Class340;
    }

    public static void processPackets() {
        if (!storedPackets.isEmpty()) {
            for (Packet<INetHandler> packet : storedPackets) {
                Class33 event = new Class33(packet, mc.getNetHandler());
                Class13.Method7(event);
                if (event.isCancelled() || mc.getNetHandler() == null) continue;
                packet.processPacket(mc.getNetHandler());
            }
            storedPackets.clear();
        }
    }

    public static boolean noPost() {
        return Class262.getModule(Class304.class).isState() || Class19.storing;
    }

    public static void fixC0F(Class353 packet) {
        short id = packet.getUid();
        if (id >= 0 || pingPackets.isEmpty()) {
            Class212.sendPacketNoEvent(packet);
        } else {
            int current;
            do {
                current = pingPackets.getFirst();
                Class212.sendPacketNoEvent(new Class353(packet.getWindowId(), (short)current, true));
                pingPackets.pollFirst();
            } while (current != id && !pingPackets.isEmpty());
        }
    }
}

