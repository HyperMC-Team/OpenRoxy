package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

public class Class80 {
    private final Class79 packetManager;

    public Class80(Class79 packetManager) {
        this.packetManager = packetManager;
    }

    public void register() {
        this.registerServerPackets();
        this.registerServerHandlers();
    }

    private void registerServerPackets() {
        this.packetManager.registerServerPacket(1, Class71::new);
        this.packetManager.registerServerPacket(2, Class73::new);
        this.packetManager.registerServerPacket(3, Class70::new);
        this.packetManager.registerServerPacket(4, Class72::new);
        this.packetManager.registerServerPacket(5, Class74::new);
        this.packetManager.registerServerPacket(6, Class75::new);
    }

    private void registerServerHandlers() {
        this.packetManager.registerPacketHandler(Class71.class, new Class65());
        this.packetManager.registerPacketHandler(Class73.class, new Class68());
        this.packetManager.registerPacketHandler(Class70.class, new Class64());
        this.packetManager.registerPacketHandler(Class72.class, new Class66());
        this.packetManager.registerPacketHandler(Class74.class, new Class67());
        this.packetManager.registerPacketHandler(Class75.class, new Class69());
    }
}

