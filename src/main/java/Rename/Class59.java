package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.io.IOException;

public class Class59
implements Class76 {
    private String message;
    private String username;
    private String rank;

    @Override
    public void read(Class77 buffer) throws IOException {
        this.message = buffer.readString();
        this.username = buffer.readString();
        this.rank = buffer.readString();
    }

    @Override
    public void write(Class77 buffer) throws IOException {
        buffer.writeString(this.message);
        buffer.writeString(this.username);
        buffer.writeString(this.rank);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getMessage() {
        return this.message;
    }

    public String getUsername() {
        return this.username;
    }

    public String getRank() {
        return this.rank;
    }

    public Class59() {
    }

    public Class59(String message, String username, String rank) {
        this.message = message;
        this.username = username;
        this.rank = rank;
    }
}

