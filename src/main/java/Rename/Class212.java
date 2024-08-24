package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import com.viaversion.viarewind.protocol.protocol1_8to1_9.Protocol1_8To1_9;
import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.protocol.Protocol;
import com.viaversion.viaversion.api.protocol.packet.PacketWrapper;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import com.viaversion.viaversion.api.type.Type;
import com.viaversion.viaversion.exception.CancelException;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.client.C00PacketLoginStart;
import net.minecraft.network.login.client.C01PacketEncryptionResponse;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.network.status.client.C00PacketServerQuery;
import net.minecraft.network.status.client.C01PacketPing;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.viamcp.vialoadingbase.ViaLoadingBase;
import tech.skidonion.obfuscator.annotations.Renamer;
import tech.skidonion.obfuscator.annotations.StringEncryption;

@Renamer
@StringEncryption
public class Class212
implements Class3 {
    public static void sendPacket(Packet packet) {
        mc.getNetHandler().addToSendQueue(packet);
    }

    public static void send1_12Block() {
        if (ViaLoadingBase.getInstance().getTargetVersion().isOlderThanOrEqualTo(ProtocolVersion.v1_8)) {
            Class212.sendPacket(new Class355(Class212.mc.thePlayer.getHeldItem()));
            return;
        }
        PacketWrapper useItem = PacketWrapper.create(29, null, Via.getManager().getConnectionManager().getConnections().iterator().next());
        useItem.write((Type)Type.VAR_INT, (Object)1);
        com.viaversion.viarewind.utils.PacketUtil.sendToServer(useItem, Protocol1_8To1_9.class, true, true);
    }

    public static void send1_12Block0() {
        if (ViaLoadingBase.getInstance().getTargetVersion().isOlderThanOrEqualTo(ProtocolVersion.v1_8)) {
            Class212.sendPacket(new Class355(Class212.mc.thePlayer.getHeldItem()));
            return;
        }
        PacketWrapper useItem = PacketWrapper.create(29, null, Via.getManager().getConnectionManager().getConnections().iterator().next());
        useItem.write((Type)Type.VAR_INT, (Object)0);
        com.viaversion.viarewind.utils.PacketUtil.sendToServer(useItem, Protocol1_8To1_9.class, true, true);
    }

    public static void sendPacketNoEvent(Packet<?> packet) {
        mc.getNetHandler().addToSendQueueUnregistered(packet);
    }

    public static void sendBlocking(boolean callEvent, boolean place) {
        Class355 packet;
        Class355 c08PacketPlayerBlockPlacement = packet = place ? new Class355(new BlockPos(-1, -1, -1), 255, Class212.mc.thePlayer.getHeldItem(), 0.0f, 0.0f, 0.0f) : new Class355(Class212.mc.thePlayer.getHeldItem());
        if (callEvent) {
            Class212.sendPacket(packet);
        } else {
            Class212.sendPacketNoEvent(packet);
        }
    }

    public static void sendC0F() {
        Class212.sendPacket(new Class353(Class171.getRandom(114514, 191981000), (short) Class171.getRandomInRange(114514, 191981000), true));
    }

    public static void sendC0FNoEvent() {
        Class212.sendPacketNoEvent(new Class353(Class171.getRandom(114514, 1919810), (short) Class171.getRandom(102, 1000024123), true));
    }

    public static void releaseUseItem(boolean callEvent) {
        Class365 packet = new Class365(Class365.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, EnumFacing.DOWN);
        if (callEvent) {
            Class212.sendPacket(packet);
        } else {
            Class212.sendPacketNoEvent(packet);
        }
    }

    public static boolean shouldIgnorePacket(Packet packet) {
        return packet instanceof C00PacketLoginStart || packet instanceof C01PacketEncryptionResponse || packet instanceof C00Handshake || packet instanceof C00PacketServerQuery || packet instanceof C01PacketPing;
    }

    public static void sendToServer(PacketWrapper packet, Class<? extends Protocol> packetProtocol, boolean skipCurrentPipeline, boolean currentThread) {
        try {
            if (currentThread) {
                packet.sendToServer(packetProtocol, skipCurrentPipeline);
            } else {
                packet.scheduleSendToServer(packetProtocol, skipCurrentPipeline);
            }
        }
        catch (CancelException cancelException) {
        }
        catch (Exception var6) {
            var6.printStackTrace();
        }
    }

    public static void handlePacket(Packet<INetHandlerPlayClient> packet) {
        NetHandlerPlayClient netHandler = mc.getNetHandler();
        if (packet instanceof Class371) {
            netHandler.handleKeepAlive((Class371)packet);
        } else if (packet instanceof Class411) {
            netHandler.handleJoinGame((Class411)packet);
        } else if (packet instanceof Class420) {
            netHandler.handleChat((Class420)packet);
        } else if (packet instanceof Class341) {
            netHandler.handleTimeUpdate((Class341)packet);
        } else if (packet instanceof Class376) {
            netHandler.handleEntityEquipment((Class376)packet);
        } else if (packet instanceof Class400) {
            netHandler.handleSpawnPosition((Class400)packet);
        } else if (packet instanceof Class398) {
            netHandler.handleUpdateHealth((Class398)packet);
        } else if (packet instanceof Class401) {
            netHandler.handleRespawn((Class401)packet);
        } else if (packet instanceof Class351) {
            netHandler.handlePlayerPosLook((Class351)packet);
        } else if (packet instanceof Class374) {
            netHandler.handleHeldItemChange((Class374)packet);
        } else if (packet instanceof Class418) {
            netHandler.handleSpawnPainting((Class418)packet);
        } else if (packet instanceof Class338) {
            netHandler.handleUseBed((Class338)packet);
        } else if (packet instanceof Class367) {
            netHandler.handleAnimation((Class367)packet);
        } else if (packet instanceof Class368) {
            netHandler.handleSpawnPlayer((Class368)packet);
        } else if (packet instanceof Class369) {
            netHandler.handleCollectItem((Class369)packet);
        } else if (packet instanceof Class339) {
            netHandler.handleSpawnObject((Class339)packet);
        } else if (packet instanceof Class345) {
            netHandler.handleSpawnMob((Class345)packet);
        } else if (packet instanceof Class373) {
            netHandler.handleSpawnExperienceOrb((Class373)packet);
        } else if (packet instanceof Class349) {
            netHandler.handleEntityVelocity((Class349)packet);
        } else if (packet instanceof Class402) {
            netHandler.handleDestroyEntities((Class402)packet);
        } else if (packet instanceof Class397) {
            netHandler.handleEntityMovement((Class397)packet);
        } else if (packet instanceof Class406) {
            netHandler.handleEntityTeleport((Class406)packet);
        } else if (packet instanceof Class415) {
            netHandler.handleEntityStatus((Class415)packet);
        } else if (packet instanceof Class404) {
            netHandler.handleEntityHeadLook((Class404)packet);
        } else if (packet instanceof Class370) {
            netHandler.handleEntityAttach((Class370)packet);
        } else if (packet instanceof Class419) {
            netHandler.handleEntityMetadata((Class419)packet);
        } else if (packet instanceof Class347) {
            netHandler.handleEntityEffect((Class347)packet);
        } else if (packet instanceof Class407) {
            netHandler.handleRemoveEntityEffect((Class407)packet);
        } else if (packet instanceof Class344) {
            netHandler.handleSetExperience((Class344)packet);
        } else if (packet instanceof Class388) {
            netHandler.handleEntityProperties((Class388)packet);
        } else if (packet instanceof Class386) {
            netHandler.handleChunkData((Class386)packet);
        } else if (packet instanceof Class394) {
            netHandler.handleMultiBlockChange((Class394)packet);
        } else if (packet instanceof Class382) {
            netHandler.handleBlockChange((Class382)packet);
        } else if (packet instanceof Class380) {
            netHandler.handleBlockAction((Class380)packet);
        } else if (packet instanceof Class383) {
            netHandler.handleBlockBreakAnim((Class383)packet);
        } else if (packet instanceof Class396) {
            netHandler.handleMapChunkBulk((Class396)packet);
        } else if (packet instanceof Class384) {
            netHandler.handleExplosion((Class384)packet);
        } else if (packet instanceof Class381) {
            netHandler.handleEffect((Class381)packet);
        } else if (packet instanceof Class389) {
            netHandler.handleSoundEffect((Class389)packet);
        } else if (packet instanceof Class399) {
            netHandler.handleParticles((Class399)packet);
        } else if (packet instanceof Class342) {
            netHandler.handleChangeGameState((Class342)packet);
        } else if (packet instanceof Class346) {
            netHandler.handleSpawnGlobalEntity((Class346)packet);
        } else if (packet instanceof Class403) {
            netHandler.handleOpenWindow((Class403)packet);
        } else if (packet instanceof Class340) {
            netHandler.handleCloseWindow((Class340)packet);
        } else if (packet instanceof Class378) {
            netHandler.handleSetSlot((Class378)packet);
        } else if (packet instanceof Class393) {
            netHandler.handleWindowItems((Class393)packet);
        } else if (packet instanceof Class390) {
            netHandler.handleWindowProperty((Class390)packet);
        } else if (packet instanceof Class391) {
            netHandler.handleConfirmTransaction((Class391)packet);
        } else if (packet instanceof Class405) {
            netHandler.handleUpdateSign((Class405)packet);
        } else if (packet instanceof Class395) {
            netHandler.handleMaps((Class395)packet);
        } else if (packet instanceof Class385) {
            netHandler.handleUpdateTileEntity((Class385)packet);
        } else if (packet instanceof Class408) {
            netHandler.handleSignEditorOpen((Class408)packet);
        } else if (packet instanceof Class392) {
            netHandler.handleStatistics((Class392)packet);
        } else if (packet instanceof Class412) {
            netHandler.handlePlayerListItem((Class412)packet);
        } else if (packet instanceof Class417) {
            netHandler.handlePlayerAbilities((Class417)packet);
        } else if (packet instanceof Class348) {
            netHandler.handleTabComplete((Class348)packet);
        } else if (packet instanceof Class379) {
            netHandler.handleScoreboardObjective((Class379)packet);
        } else if (packet instanceof Class350) {
            netHandler.handleUpdateScore((Class350)packet);
        } else if (packet instanceof Class372) {
            netHandler.handleDisplayScoreboard((Class372)packet);
        } else if (packet instanceof Class375) {
            netHandler.handleTeams((Class375)packet);
        } else if (packet instanceof Class377) {
            netHandler.handleCustomPayload((Class377)packet);
        } else if (packet instanceof Class413) {
            netHandler.handleDisconnect((Class413)packet);
        } else if (packet instanceof Class416) {
            netHandler.handleServerDifficulty((Class416)packet);
        } else if (packet instanceof Class422) {
            netHandler.handleCombatEvent((Class422)packet);
        } else if (packet instanceof Class410) {
            netHandler.handleCamera((Class410)packet);
        } else if (packet instanceof Class387) {
            netHandler.handleWorldBorder((Class387)packet);
        } else if (packet instanceof Class414) {
            netHandler.handleTitle((Class414)packet);
        } else if (packet instanceof Class421) {
            netHandler.handleSetCompressionLevel((Class421)packet);
        } else if (packet instanceof Class409) {
            netHandler.handlePlayerListHeaderFooter((Class409)packet);
        } else if (packet instanceof Class423) {
            netHandler.handleResourcePack((Class423)packet);
        } else {
            if (!(packet instanceof Class424)) {
                throw new IllegalArgumentException("Unable to match packet type to handle: " + packet.getClass());
            }
            netHandler.handleEntityNBT((Class424)packet);
        }
    }

    public static void readPacket(Packet<INetHandler> packet, INetHandler netHandler) {
        packet.processPacket(netHandler);
    }
}

