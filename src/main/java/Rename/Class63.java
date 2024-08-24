package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.io.IOException;

public class Class63
implements Class76 {
    private String IGN;

    @Override
    public void read(Class77 buffer) throws IOException {
        this.IGN = buffer.readString();
    }

    @Override
    public void write(Class77 buffer) throws IOException {
        buffer.writeString(this.IGN);
    }

    public String getIGN() {
        return this.IGN;
    }

    public void setIGN(String IGN) {
        this.IGN = IGN;
    }

    public Class63() {
    }

    public Class63(String IGN) {
        this.IGN = IGN;
    }
}

