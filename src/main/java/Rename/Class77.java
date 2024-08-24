package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Objects;

public class Class77 {
    private final DataInputStream dataInputStream;
    private final DataOutputStream dataOutputStream;

    public Class77(InputStream inputStream, OutputStream outputStream) {
        this.dataInputStream = new DataInputStream(new BufferedInputStream(inputStream));
        this.dataOutputStream = new DataOutputStream(new BufferedOutputStream(outputStream));
    }

    public byte readByte() throws IOException {
        return this.dataInputStream.readByte();
    }

    public int readInt() throws IOException {
        return this.dataInputStream.readInt();
    }

    public short readShort() throws IOException {
        return this.dataInputStream.readShort();
    }

    public long readLong() throws IOException {
        return this.dataInputStream.readLong();
    }

    public boolean readBoolean() throws IOException {
        return this.dataInputStream.readBoolean();
    }

    public String readString() throws IOException {
        return this.dataInputStream.readUTF();
    }

    public void writeByte(byte value) throws IOException {
        this.dataOutputStream.writeByte(value);
    }

    public void writeInt(int value) throws IOException {
        this.dataOutputStream.writeInt(value);
    }

    public void writeShort(short value) throws IOException {
        this.dataOutputStream.writeShort(value);
    }

    public void writeLong(long value) throws IOException {
        this.dataOutputStream.writeLong(value);
    }

    public void writeBoolean(boolean value) throws IOException {
        this.dataOutputStream.writeBoolean(value);
    }

    public void writeString(String value) throws IOException {
        this.dataOutputStream.writeUTF(Objects.requireNonNullElse(value, ""));
    }

    public int available() throws IOException {
        return this.dataInputStream.available();
    }

    public void flush() throws IOException {
        this.dataOutputStream.flush();
    }

    public void close() throws IOException {
        this.dataInputStream.close();
        this.dataOutputStream.close();
    }
}

