package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.io.IOException;

public class Class70
implements Class76 {
    private String message;
    private String username;
    private String rank;
    private boolean isSystemMessage;

    @Override
    public void read(Class77 buffer) throws IOException {
        this.message = buffer.readString();
        this.username = buffer.readString();
        this.rank = buffer.readString();
        this.isSystemMessage = buffer.readBoolean();
    }

    @Override
    public void write(Class77 buffer) throws IOException {
        buffer.writeString(this.message);
        buffer.writeString(this.username);
        buffer.writeString(this.rank);
        buffer.writeBoolean(this.isSystemMessage);
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

    public boolean isSystemMessage() {
        return this.isSystemMessage;
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

    public void setSystemMessage(boolean isSystemMessage) {
        this.isSystemMessage = isSystemMessage;
    }

    public Class70() {
    }

    public Class70(String message, String username, String rank, boolean isSystemMessage) {
        this.message = message;
        this.username = username;
        this.rank = rank;
        this.isSystemMessage = isSystemMessage;
    }
}

