package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import io.netty.buffer.Unpooled;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.network.PacketBuffer;

public class Class159 {
    private static final String SEPARATOR_OF_MAC = ":";

    public static void Method1() {
        PacketBuffer buf = new PacketBuffer(Unpooled.buffer());
        buf.writeString(" pocketmoder:core heypixel:armourers_workshop bungeequeue:queue fml:loginwrapper forge:tier_sorting floodgate:custom floodgate:packet heypixel:s2cevent report:areport bungeecord:main heypixel:game_info fml:play floodgate:netease floodgate:transfer gameteam:redisteam armourers_workshop:play fml:handshake legacy:redisbungee geckolib3:main forge:split floodgate:form floodgate:skin ");
        Method7("REGISTER", buf);
    }

    public static void Method2() {
        PacketBuffer buf = new PacketBuffer(Unpooled.buffer());
        buf.writeString("forge");
        Method7("MC|Brand", buf);
    }

    public static void Method3() {
        PacketBuffer buf = new PacketBuffer(Unpooled.buffer());
        UUID uuid = UUID.randomUUID();
        for (char a : uuid.toString().toCharArray()) {
            buf.writeChar(a);
        }
        buf.writeBytes(Method10());
        Method7("heypixel:check", buf);
    }

    public static void Method5() {
        PacketBuffer buf = new PacketBuffer(Unpooled.buffer());
        buf.writeBytes(Method9(UUID.randomUUID().toString()));
        buf.writeBytes(Method9(UUID.randomUUID().toString()));
        buf.writeString("[minecraft, entityculling, armourers_workshop, immediatelyfast, culllessleaves, heypixel, nochatlagforge, memoryleakfix, reeses_sodium_options, forge, rubidium, embeddiumplus, iceberg, geckolib3]");
        buf.writeString("C:\\MCLDownload\\Game\\.minecraft");
        buf.writeString("C:\\MCLDownload\\ext\\jre-v64-220420\\jdk17\\bin\\java.exe");
        buf.writeString("9BFEBFBFF000806E9");
        buf.writeString("Intel(R) Core(TM) i9-14900K CPU @ 4.8GHz");
        buf.writeString("L1HF7990133");
        buf.writeString("MG28381782722");
        buf.writeString(Method8());
        buf.writeChar(97);
        buf.writeString("Apple");
        buf.writeChar(98);
        buf.writeString("unknown");
        buf.writeChar(99);
        buf.writeString("L1HF7990133");
        buf.writeChar(100);
        buf.writeString("SDK0K09938 WIN");
        buf.writeChar(97);
        buf.writeString("MG28381782722");
        buf.writeChar(98);
        buf.writeString("\\\\.\\PHYSICALDRIVE0");
        buf.writeChar(99);
        buf.writeString("MG BG400M8 SSD 512GB");
        Method7("heypixel:check", buf);
    }

    public static void Method6() {
    }

    private static void Method7(String channel, PacketBuffer buf) {
        Class361 packet = new Class361(channel, buf);
        Minecraft.getMinecraft().getNetHandler().addToSendQueue(packet);
    }

    public static String Method8() {
        Random random = new Random();
        CharSequence[] mac = new String[]{String.format("%02x", 82), String.format("%02x", 84), String.format("%02x", 0), String.format("%02x", random.nextInt(255)), String.format("%02x", random.nextInt(255)), String.format("%02x", random.nextInt(255))};
        return String.join(SEPARATOR_OF_MAC, mac);
    }

    public static byte[] Method9(String text) {
        byte[] byteArray = text.getBytes(StandardCharsets.UTF_8);
        byte[] result = new byte[byteArray.length];
        System.arraycopy(byteArray, 0, result, 0, byteArray.length);
        return result;
    }

    public static byte[] Method10() {
        long timestamp = System.currentTimeMillis();
        ByteBuffer buffer = ByteBuffer.allocate(8);
        buffer.putLong(timestamp);
        byte[] timestampBytes = buffer.array();
        byte[] otherData = new byte[]{};
        byte[] result = new byte[otherData.length + timestampBytes.length];
        System.arraycopy(otherData, 0, result, 0, otherData.length);
        System.arraycopy(timestampBytes, 0, result, otherData.length, timestampBytes.length);
        return result;
    }
}

