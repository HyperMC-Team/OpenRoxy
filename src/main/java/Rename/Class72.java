package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.io.IOException;

public class Class72
implements Class76 {
    private boolean success;

    @Override
    public void read(Class77 buffer) throws IOException {
        this.success = buffer.readBoolean();
    }

    @Override
    public void write(Class77 buffer) throws IOException {
        buffer.writeBoolean(this.success);
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public Class72() {
    }

    public Class72(boolean success) {
        this.success = success;
    }
}

