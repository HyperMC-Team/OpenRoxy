package net.minecraft.network;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import java.util.Map;

import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.client.C00PacketLoginStart;
import net.minecraft.network.login.client.C01PacketEncryptionResponse;
import net.minecraft.network.login.server.S00PacketDisconnect;
import net.minecraft.network.login.server.S01PacketEncryptionRequest;
import net.minecraft.network.login.server.S02PacketLoginSuccess;
import net.minecraft.network.login.server.S03PacketEnableCompression;
import Rename.Class359;
import Rename.Class362;
import Rename.Class334;
import Rename.Class354;
import Rename.Class365;
import Rename.Class355;
import Rename.Class333;
import Rename.Class331;
import Rename.Class364;
import Rename.Class332;
import Rename.Class363;
import Rename.Class360;
import Rename.Class353;
import Rename.Class343;
import Rename.Class336;
import Rename.Class357;
import Rename.Class335;
import Rename.Class352;
import Rename.Class356;
import Rename.Class337;
import Rename.Class361;
import Rename.Class358;
import Rename.Class366;
import Rename.Class371;
import Rename.Class411;
import Rename.Class420;
import Rename.Class341;
import Rename.Class376;
import Rename.Class400;
import Rename.Class398;
import Rename.Class401;
import Rename.Class351;
import Rename.Class374;
import Rename.Class338;
import Rename.Class367;
import Rename.Class368;
import Rename.Class369;
import Rename.Class339;
import Rename.Class345;
import Rename.Class418;
import Rename.Class373;
import Rename.Class349;
import Rename.Class402;
import Rename.Class397;
import Rename.Class406;
import Rename.Class404;
import Rename.Class415;
import Rename.Class370;
import Rename.Class419;
import Rename.Class347;
import Rename.Class407;
import Rename.Class344;
import Rename.Class388;
import Rename.Class386;
import Rename.Class394;
import Rename.Class382;
import Rename.Class380;
import Rename.Class383;
import Rename.Class396;
import Rename.Class384;
import Rename.Class381;
import Rename.Class389;
import Rename.Class399;
import Rename.Class342;
import Rename.Class346;
import Rename.Class403;
import Rename.Class340;
import Rename.Class378;
import Rename.Class393;
import Rename.Class390;
import Rename.Class391;
import Rename.Class405;
import Rename.Class395;
import Rename.Class385;
import Rename.Class408;
import Rename.Class392;
import Rename.Class412;
import Rename.Class417;
import Rename.Class348;
import Rename.Class379;
import Rename.Class350;
import Rename.Class372;
import Rename.Class375;
import Rename.Class377;
import Rename.Class413;
import Rename.Class416;
import Rename.Class422;
import Rename.Class410;
import Rename.Class387;
import Rename.Class414;
import Rename.Class421;
import Rename.Class409;
import Rename.Class423;
import Rename.Class424;
import net.minecraft.network.status.client.C00PacketServerQuery;
import net.minecraft.network.status.client.C01PacketPing;
import net.minecraft.network.status.server.S00PacketServerInfo;
import net.minecraft.network.status.server.S01PacketPong;
import org.apache.logging.log4j.LogManager;

public enum EnumConnectionState {
    HANDSHAKING(-1){
        {
            this.registerPacket(EnumPacketDirection.SERVERBOUND, C00Handshake.class);
        }
    }
    ,
    PLAY(0){
        {
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class371.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class411.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class420.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class341.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class376.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class400.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class398.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class401.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class351.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class374.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class338.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class367.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class368.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class369.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class339.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class345.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class418.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class373.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class349.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class402.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class397.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class397.S15PacketEntityRelMove.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class397.S16PacketEntityLook.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class397.S17PacketEntityLookMove.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class406.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class404.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class415.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class370.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class419.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class347.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class407.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class344.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class388.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class386.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class394.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class382.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class380.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class383.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class396.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class384.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class381.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class389.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class399.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class342.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class346.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class403.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class340.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class378.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class393.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class390.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class391.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class405.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class395.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class385.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class408.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class392.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class412.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class417.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class348.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class379.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class350.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class372.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class375.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class377.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class413.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class416.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class422.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class410.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class387.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class414.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class421.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class409.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class423.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, Class424.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, Class359.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, Class362.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, Class334.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, Class354.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, Class354.C04PacketPlayerPosition.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, Class354.C05PacketPlayerLook.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, Class354.C06PacketPlayerPosLook.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, Class365.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, Class355.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, Class333.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, Class331.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, Class364.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, Class332.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, Class363.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, Class360.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, Class353.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, Class343.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, Class336.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, Class357.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, Class335.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, Class352.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, Class356.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, Class337.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, Class361.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, Class358.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, Class366.class);
        }
    }
    ,
    STATUS(1){
        {
            this.registerPacket(EnumPacketDirection.SERVERBOUND, C00PacketServerQuery.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S00PacketServerInfo.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, C01PacketPing.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S01PacketPong.class);
        }
    }
    ,
    LOGIN(2){
        {
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S00PacketDisconnect.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S01PacketEncryptionRequest.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S02PacketLoginSuccess.class);
            this.registerPacket(EnumPacketDirection.CLIENTBOUND, S03PacketEnableCompression.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, C00PacketLoginStart.class);
            this.registerPacket(EnumPacketDirection.SERVERBOUND, C01PacketEncryptionResponse.class);
        }
    };

    private static int field_181136_e;
    private static int field_181137_f;
    private static final EnumConnectionState[] STATES_BY_ID;
    private static final Map<Class<? extends Packet>, EnumConnectionState> STATES_BY_CLASS;
    private final int id;
    private final Map<EnumPacketDirection, BiMap<Integer, Class<? extends Packet>>> directionMaps = Maps.newEnumMap(EnumPacketDirection.class);

    private EnumConnectionState(int protocolId) {
        this.id = protocolId;
    }

    protected EnumConnectionState registerPacket(EnumPacketDirection direction, Class <? extends Packet > packetClass)
    {
        BiMap < Integer, Class <? extends Packet >> bimap = (BiMap)this.directionMaps.get(direction);

        if (bimap == null)
        {
            bimap = HashBiMap. < Integer, Class <? extends Packet >> create();
            this.directionMaps.put(direction, bimap);
        }

        if (bimap.containsValue(packetClass))
        {
            String s = direction + " packet " + packetClass + " is already known to ID " + bimap.inverse().get(packetClass);
            LogManager.getLogger().fatal(s);
            throw new IllegalArgumentException(s);
        }
        else
        {
            bimap.put(Integer.valueOf(bimap.size()), packetClass);
            return this;
        }
    }


    public Integer getPacketId(EnumPacketDirection direction, Packet packetIn) {
        return (Integer)this.directionMaps.get((Object)direction).inverse().get(packetIn.getClass());
    }

    public Packet getPacket(EnumPacketDirection direction, int packetId) throws InstantiationException, IllegalAccessException {
        Class oclass = (Class)this.directionMaps.get((Object)direction).get((Object)packetId);
        return oclass == null ? null : (Packet)oclass.newInstance();
    }

    public int getId() {
        return this.id;
    }

    public static EnumConnectionState getById(int stateId) {
        return stateId >= field_181136_e && stateId <= field_181137_f ? STATES_BY_ID[stateId - field_181136_e] : null;
    }

    public static EnumConnectionState getFromPacket(Packet packetIn) {
        return STATES_BY_CLASS.get(packetIn.getClass());
    }

    static {
        field_181136_e = -1;
        field_181137_f = 2;
        STATES_BY_ID = new EnumConnectionState[field_181137_f - field_181136_e + 1];
        STATES_BY_CLASS = Maps.newHashMap();
        for (EnumConnectionState enumconnectionstate : EnumConnectionState.values()) {
            int i = enumconnectionstate.getId();
            if (i < field_181136_e || i > field_181137_f) {
                throw new Error("Invalid protocol ID " + Integer.toString(i));
            }
            EnumConnectionState.STATES_BY_ID[i - EnumConnectionState.field_181136_e] = enumconnectionstate;
            for (EnumPacketDirection enumpacketdirection : enumconnectionstate.directionMaps.keySet()) {
                for (Class oclass : enumconnectionstate.directionMaps.get((Object)enumpacketdirection).values()) {
                    if (STATES_BY_CLASS.containsKey(oclass) && STATES_BY_CLASS.get(oclass) != enumconnectionstate) {
                        throw new Error("Class76 " + String.valueOf(oclass) + " is already assigned to protocol " + String.valueOf((Object)STATES_BY_CLASS.get(oclass)) + " - can't reassign to " + String.valueOf((Object)enumconnectionstate));
                    }
                    try {
                        oclass.newInstance();
                    }
                    catch (Throwable var10) {
                        throw new Error("Class76 " + String.valueOf(oclass) + " fails instantiation checks! " + String.valueOf(oclass));
                    }
                    STATES_BY_CLASS.put(oclass, enumconnectionstate);
                }
            }
        }
    }
}

