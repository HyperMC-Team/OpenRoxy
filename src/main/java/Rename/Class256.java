package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
public class Class256
extends Class261 {
    private int code;

    public Class256(int code) {
        this.name = "Keybind";
        this.code = code;
    }

    public int getCode() {
        return this.code == -1 ? 0 : this.code;
    }

    public Integer getConfigValue() {
        return this.getCode();
    }

    public void setCode(int code) {
        this.code = code;
    }
}

