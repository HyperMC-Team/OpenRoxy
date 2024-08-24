package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.io.IOException;

public class Class73
implements Class76 {
    private String rank;

    @Override
    public void read(Class77 buffer) throws IOException {
        this.rank = buffer.readString();
    }

    @Override
    public void write(Class77 buffer) throws IOException {
        buffer.writeString(this.rank);
    }

    public String getRank() {
        return this.rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public Class73() {
    }

    public Class73(String rank) {
        this.rank = rank;
    }
}

