package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import net.minecraft.network.Packet;

public class Class216
implements Class3 {
    private final CopyOnWriteArrayList<Packet<?>> client = new CopyOnWriteArrayList();

    public void startBlink(Class35 event, Packet<?> packetIn) {
        Packet<?> packet = event.getPacket();
        if (packet == packetIn) {
            event.setCancelled();
            this.client.add(packet);
        }
    }

    public void release() {
        if (!this.client.isEmpty()) {
            ArrayList<Packet<?>> remove = new ArrayList();

            for(Packet<?> packet : this.client) {
                Class212.sendPacketNoEvent(packet);
                remove.add(packet);
            }

            for(Packet<?> packet : remove) {
                this.client.remove(packet);
            }

            remove.clear();
        }
    }

    public CopyOnWriteArrayList<Packet<?>> getClient() {
        return this.client;
    }

    public static class Client {
        public static final CopyOnWriteArrayList<Packet<?>> client = new CopyOnWriteArrayList();

        public static boolean isBlinkPacket(Packet<?> packet) {
            return packet instanceof Class354 || packet instanceof Class364 || packet instanceof Class334 || packet instanceof Class331 || packet instanceof Class355 || packet instanceof Class333 || packet instanceof Class353;
        }

        public static void startBlink(Class35 event) {
            Packet<?> packet = event.getPacket();
            if (!event.isCancelled() && Client.isBlinkPacket(packet)) {
                event.setCancelled();
                client.add(packet);
            }
        }

        public static void release() {
            if (!client.isEmpty()) {
                ArrayList<Packet<?>> toRemove = new ArrayList();

                for(Packet<?> packet : client) {
                    if (isBlinkPacket(packet)) {
                        Class212.sendPacketNoEvent(packet);
                        toRemove.add(packet);
                    }
                }

                if (!toRemove.isEmpty()) {
                    for(Packet<?> p : toRemove) {
                        client.remove(p);
                    }
                }

                toRemove.clear();
            }
        }
    }
}

