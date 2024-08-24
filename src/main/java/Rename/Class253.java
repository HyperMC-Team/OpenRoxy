package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.util.function.Predicate;

public class Class253<T extends Class261> {
    public static final Predicate<Class254> BOOLEAN_CONDITION = Class254::isEnabled;
    private final T parent;
    private final Predicate<T> condition;

    public Class253(T parent, Predicate<T> condition) {
        this.parent = parent;
        this.condition = condition;
    }

    public boolean isValid() {
        return this.condition.test(this.parent) && this.parent.getParents().stream().allMatch(Class253::isValid);
    }

    public T getParent() {
        return this.parent;
    }
}

