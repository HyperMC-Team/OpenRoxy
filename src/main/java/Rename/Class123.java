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

import java.io.Serializable;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public abstract class Class123<A, B>
implements Serializable {
    public static <A, B> Class123<A, B> of(A a, B b) {
        return Class121.of(a, b);
    }

    public static <A> Class123<A, A> of(A a) {
        return Class121.of(a, a);
    }

    public abstract A getFirst();

    public abstract B getSecond();

    public abstract <R> R apply(BiFunction<? super A, ? super B, ? extends R> var1);

    public abstract void use(BiConsumer<? super A, ? super B> var1);

    public int hashCode() {
        return Objects.hash(this.getFirst(), this.getSecond());
    }

    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof Class123 other) {
            return Objects.equals(this.getFirst(), other.getFirst()) && Objects.equals(this.getSecond(), other.getSecond());
        }
        return false;
    }
}

