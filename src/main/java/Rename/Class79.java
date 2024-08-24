package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */


import java.io.EOFException;
import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class Class79 {
    private final Map<Integer, Supplier<Class76>> serverPacketRegistry = new HashMap();
    private final Map<Class<? extends Class76>, Class78<?>> packetHandlers = new HashMap();
    private final List<Class76> sendQueue = new ArrayList();

    public Class76 processPacket(Class77 buffer) throws IOException {
        try {
            int packetId = buffer.readInt();
            Supplier<Class76> packetSupplier = this.serverPacketRegistry.get(packetId);
            if (packetSupplier == null) {
                throw new IOException("Unregistered packet ID: " + packetId);
            } else {
                Class76 packet = packetSupplier.get();
                packet.read(buffer);
                Class78<Class76> handler = (Class78)this.packetHandlers.get(packet.getClass());
                if (handler != null) {
                    handler.handle(packet);
                    return packet;
                } else {
                    throw new IOException("No handler registered for packet: " + packet.getClass().getName());
                }
            }
        } catch (EOFException var6) {
            throw new IOException("Reached end of stream unexpectedly. Connection may have been lost.", var6);
        } catch (SocketException var7) {
            throw new IOException("Socket exception, connection might be lost", var7);
        }
    }

    public void sendPacket(Class77 buffer, Class76 packet, int packetId) throws IOException {
        buffer.writeInt(packetId);
        packet.write(buffer);
        buffer.flush();
    }

    public void registerServerPacket(int packetId, Supplier<Class76> packetSupplier) {
        this.serverPacketRegistry.put(packetId, packetSupplier);
    }

    public <T extends Class76> void registerPacketHandler(Class<T> packetClass, Class78<T> handler) {
        this.packetHandlers.put(packetClass, handler);
    }
}
