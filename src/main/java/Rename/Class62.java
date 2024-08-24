package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.io.IOException;

public class Class62
implements Class76 {
    private String uid;
    private String hwid;

    @Override
    public void read(Class77 buffer) throws IOException {
        this.uid = buffer.readString();
        this.hwid = buffer.readString();
    }

    @Override
    public void write(Class77 buffer) throws IOException {
        buffer.writeString(this.uid);
        buffer.writeString(this.hwid);
    }

    public String getUid() {
        return this.uid;
    }

    public String getHwid() {
        return this.hwid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setHwid(String hwid) {
        this.hwid = hwid;
    }

    public Class62() {
    }

    public Class62(String uid, String hwid) {
        this.uid = uid;
        this.hwid = hwid;
    }
}

