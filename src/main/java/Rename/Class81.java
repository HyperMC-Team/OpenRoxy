package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.io.IOException;
import java.net.Socket;

import tech.skidonion.obfuscator.annotations.StringEncryption;

@StringEncryption
public class Class81 {
    private Socket socket;
    private Class77 packetBuffer;
    private Class79 packetManager;
    private boolean isConnected = false;

    public void connect(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        this.isConnected = true;
        Class83.success("Connected to server at BaiZi's verification server");
        this.packetBuffer = new Class77(this.socket.getInputStream(), this.socket.getOutputStream());
        this.packetManager = new Class79();
        Class80 packetRegistry = new Class80(this.packetManager);
        packetRegistry.register();
        this.process();
    }

    private void process() {
        try {
            this.packetManager.sendPacket(this.packetBuffer, new Class61(1), 1);
            while (!this.socket.isClosed()) {
                Class71 wrapper;
                Class76 packet = this.packetManager.processPacket(this.packetBuffer);
                if (packet instanceof Class71 && (wrapper = (Class71)packet).getStatus() == 1) {
                    Class60 getRankPacket = new Class60(Class87.uid);
                    this.packetManager.sendPacket(this.packetBuffer, getRankPacket, 3);
                }
                if (!(packet instanceof Class75)) continue;
                this.packetManager.sendPacket(this.packetBuffer, new Class58(), 6);
            }
        }
        catch (Exception e) {
            Class83.error("Connection with server lost. Error: " + e);
        }
    }

    public void close() {
        try {
            if (this.packetBuffer != null) {
                this.packetBuffer.close();
            }
            if (this.socket != null) {
                this.socket.close();
                this.isConnected = false;
                Class83.info("Connection closed");
            }
        }
        catch (IOException e) {
            Class162.addChatMessage(e.getMessage());
            Class83.error("Error closing client resources: " + e.getMessage());
        }
    }

    public Socket getSocket() {
        return this.socket;
    }

    public Class77 getPacketBuffer() {
        return this.packetBuffer;
    }

    public Class79 getPacketManager() {
        return this.packetManager;
    }

    public boolean isConnected() {
        return this.isConnected;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void setPacketBuffer(Class77 packetBuffer) {
        this.packetBuffer = packetBuffer;
    }

    public void setPacketManager(Class79 packetManager) {
        this.packetManager = packetManager;
    }

    public void setConnected(boolean isConnected) {
        this.isConnected = isConnected;
    }
}

