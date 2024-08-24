package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.io.IOException;

public class Class71
implements Class76 {
    private int status;
    private String message;

    public Class71(int status) {
        this.status = status;
    }

    @Override
    public void read(Class77 buffer) throws IOException {
        this.status = buffer.readInt();
        this.message = buffer.readString();
    }

    @Override
    public void write(Class77 buffer) throws IOException {
        buffer.writeInt(this.status);
        buffer.writeString(this.message);
    }

    public int getStatus() {
        return this.status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Class71() {
    }

    public Class71(int status, String message) {
        this.status = status;
        this.message = message;
    }
}

