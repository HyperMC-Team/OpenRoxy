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

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Class24 {
    @Expose
    @SerializedName(value="name")
    public String name;
    @Expose
    @SerializedName(value="value")
    public Object value;

    public Class24(String name, Object value) {
        this.name = name;
        this.value = value;
    }
}

