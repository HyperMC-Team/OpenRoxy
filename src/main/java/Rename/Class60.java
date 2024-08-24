package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.io.IOException;

public class Class60
implements Class76 {
    private String username;

    @Override
    public void read(Class77 buffer) throws IOException {
        this.username = buffer.readString();
    }

    @Override
    public void write(Class77 buffer) throws IOException {
        buffer.writeString(this.username);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public Class60() {
    }

    public Class60(String username) {
        this.username = username;
    }
}

