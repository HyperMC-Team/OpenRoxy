package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.io.IOException;

public class Class61
implements Class76 {
    private int protocolVersion;

    @Override
    public void read(Class77 buffer) throws IOException {
        this.protocolVersion = buffer.readInt();
    }

    @Override
    public void write(Class77 buffer) throws IOException {
        buffer.writeInt(this.protocolVersion);
    }

    public int getProtocolVersion() {
        return this.protocolVersion;
    }

    public void setProtocolVersion(int protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public Class61() {
    }

    public Class61(int protocolVersion) {
        this.protocolVersion = protocolVersion;
    }
}

