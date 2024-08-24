package net.minecraft.network;

import Rename.Class411;
import Rename.Class401;
import Rename.Class351;
import net.minecraft.src.Config;
import net.minecraft.util.IThreadListener;

public class PacketThreadUtil {
    public static int lastDimensionId = Integer.MIN_VALUE;

    public static <T extends INetHandler> void checkThreadAndEnqueue(final Packet<T> p_180031_0_, final T p_180031_1_, IThreadListener p_180031_2_) throws ThreadQuickExitException {
        if (!p_180031_2_.isCallingFromMinecraftThread()) {
            p_180031_2_.addScheduledTask(new Runnable(){

                @Override
                public void run() {
                    PacketThreadUtil.clientPreProcessPacket(p_180031_0_);
                    p_180031_0_.processPacket(p_180031_1_);
                }
            });
            throw ThreadQuickExitException.INSTANCE;
        }
        PacketThreadUtil.clientPreProcessPacket(p_180031_0_);
    }

    protected static void clientPreProcessPacket(Packet p_clientPreProcessPacket_0_) {
        if (p_clientPreProcessPacket_0_ instanceof Class351) {
            Config.getRenderGlobal().onPlayerPositionSet();
        }
        if (p_clientPreProcessPacket_0_ instanceof Class401) {
            Class401 s07packetrespawn = (Class401)p_clientPreProcessPacket_0_;
            lastDimensionId = s07packetrespawn.getDimensionID();
        } else if (p_clientPreProcessPacket_0_ instanceof Class411) {
            Class411 s01packetjoingame = (Class411)p_clientPreProcessPacket_0_;
            lastDimensionId = s01packetjoingame.getDimension();
        } else {
            lastDimensionId = Integer.MIN_VALUE;
        }
    }
}

