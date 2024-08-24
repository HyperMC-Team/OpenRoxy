package Rename;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class Class354
implements Packet<INetHandlerPlayServer> {
    protected double x;
    protected double y;
    protected double z;
    protected float yaw;
    protected float pitch;
    protected boolean onGround;
    protected boolean moving;
    public boolean rotating;

    public Class354() {
    }

    public Class354(boolean isOnGround) {
        this.onGround = isOnGround;
    }

    @Override
    public void processPacket(INetHandlerPlayServer handler) {
        handler.processPlayer(this);
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.onGround = buf.readUnsignedByte() != 0;
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeByte(this.onGround ? 1 : 0);
    }

    public double getPositionX() {
        return this.x;
    }

    public double getPositionY() {
        return this.y;
    }

    public double getPositionZ() {
        return this.z;
    }

    public float getYaw() {
        return this.yaw;
    }

    public float getPitch() {
        return this.pitch;
    }

    public boolean isOnGround() {
        return this.onGround;
    }

    public boolean isMoving() {
        return this.moving;
    }

    public boolean getRotating() {
        return this.rotating;
    }

    public void setMoving(boolean isMoving) {
        this.moving = isMoving;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public static class C06PacketPlayerPosLook
    extends Class354 {
        public C06PacketPlayerPosLook() {
            this.moving = true;
            this.rotating = true;
        }

        public C06PacketPlayerPosLook(double playerX, double playerY, double playerZ, float playerYaw, float playerPitch, boolean playerIsOnGround) {
            this.x = playerX;
            this.y = playerY;
            this.z = playerZ;
            this.yaw = playerYaw;
            this.pitch = playerPitch;
            this.onGround = playerIsOnGround;
            this.rotating = true;
            this.moving = true;
        }

        @Override
        public void readPacketData(PacketBuffer buf) throws IOException {
            this.x = buf.readDouble();
            this.y = buf.readDouble();
            this.z = buf.readDouble();
            this.yaw = buf.readFloat();
            this.pitch = buf.readFloat();
            super.readPacketData(buf);
        }

        @Override
        public void writePacketData(PacketBuffer buf) throws IOException {
            buf.writeDouble(this.x);
            buf.writeDouble(this.y);
            buf.writeDouble(this.z);
            buf.writeFloat(this.yaw);
            buf.writeFloat(this.pitch);
            super.writePacketData(buf);
        }
    }

    public static class C05PacketPlayerLook
    extends Class354 {
        public C05PacketPlayerLook() {
            this.rotating = true;
        }

        public C05PacketPlayerLook(float playerYaw, float playerPitch, boolean isOnGround) {
            this.yaw = playerYaw;
            this.pitch = playerPitch;
            this.onGround = isOnGround;
            this.rotating = true;
        }

        @Override
        public void readPacketData(PacketBuffer buf) throws IOException {
            this.yaw = buf.readFloat();
            this.pitch = buf.readFloat();
            super.readPacketData(buf);
        }

        @Override
        public void writePacketData(PacketBuffer buf) throws IOException {
            buf.writeFloat(this.yaw);
            buf.writeFloat(this.pitch);
            super.writePacketData(buf);
        }
    }

    public static class C04PacketPlayerPosition
    extends Class354 {
        public C04PacketPlayerPosition() {
            this.moving = true;
        }

        public C04PacketPlayerPosition(double posX, double posY, double posZ, boolean isOnGround) {
            this.x = posX;
            this.y = posY;
            this.z = posZ;
            this.onGround = isOnGround;
            this.moving = true;
        }

        @Override
        public void readPacketData(PacketBuffer buf) throws IOException {
            this.x = buf.readDouble();
            this.y = buf.readDouble();
            this.z = buf.readDouble();
            super.readPacketData(buf);
        }

        @Override
        public void writePacketData(PacketBuffer buf) throws IOException {
            buf.writeDouble(this.x);
            buf.writeDouble(this.y);
            buf.writeDouble(this.z);
            super.writePacketData(buf);
        }
    }
}

