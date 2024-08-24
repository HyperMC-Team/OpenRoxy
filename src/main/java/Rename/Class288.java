package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.nio.charset.StandardCharsets;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
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
public class Class288
extends Class252 {
    public final Class257 mode = new Class257("Mode", "HYT", "HYT", "Heypixel");
    private boolean isFirst = true;
    private final Class239 stopWatch = new Class239();

    public Class288() {
        super("Protocol", Class263.Misc);
    }

    @Class14
    public void onMotion(Class27 event) {
        this.setSuffix(this.mode.getMode());
    }

    @Class14
    public void onPacketEvent(Class35 event) {
        Packet<?> packet = event.getPacket();
        if (this.mode.is("Class159")) {
            if (packet instanceof Class356) {
                event.setCancelled(true);
                Class159.Method1();
                Class159.Method2();
                Class159.Method5();
                this.isFirst = true;
                this.stopWatch.reset();
            }
            if (packet instanceof Class361 customPayload) {
                String channelName = customPayload.getChannelName();
                System.out.println("Class440: " + channelName);
                Class162.addChatMessage("Class440: " + channelName);
                PacketBuffer data = customPayload.getBufferData();
                if (data != null && data.readableBytes() > 0) {
                    byte[] payloadBytes = new byte[data.readableBytes()];
                    data.readBytes(payloadBytes);
                    String payloadContent = new String(payloadBytes, StandardCharsets.UTF_8);
                    System.out.println("Payload content: " + payloadContent);
                    Class162.addChatMessage("Payload content: " + payloadContent);
                }
            }
        }
    }

    @Class14
    public void onTick(Class50 event) {
        try {
            if (this.isFirst && this.isNull()) {
                Class159.Method5();
                this.stopWatch.reset();
            } else if (this.stopWatch.finished(5000L)) {
                Class159.Method3();
                this.stopWatch.reset();
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }
}

