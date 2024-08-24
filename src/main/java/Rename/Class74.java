package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.io.IOException;

public class Class74
implements Class76 {
    private String users;

    @Override
    public void read(Class77 buffer) throws IOException {
        this.users = buffer.readString();
    }

    @Override
    public void write(Class77 buffer) throws IOException {
        buffer.writeString(this.users);
    }

    public String getUsers() {
        return this.users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    public Class74() {
    }

    public Class74(String users) {
        this.users = users;
    }
}

