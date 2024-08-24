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
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

public enum Class141 {
    FORWARDS,
    BACKWARDS;


    public Class141 opposite() {
        if (this == FORWARDS) {
            return BACKWARDS;
        }
        return FORWARDS;
    }

    public boolean forwards() {
        return this == FORWARDS;
    }

    public boolean backwards() {
        return this == BACKWARDS;
    }
}

