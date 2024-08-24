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

public abstract class Class114<A, B, C>
implements Serializable {
    public static <A, B, C> Class114<A, B, C> of(A a, B b, C c) {
        return Class101.of(a, b, c);
    }

    public static <A> Class114<A, A, A> of(A a) {
        return Class101.of(a, a, a);
    }

    public abstract A getFirst();

    public abstract B getSecond();

    public abstract C getThird();

    public abstract <R> R apply(TriFunction<? super A, ? super B, ? super C, ? extends R> var1);

    public abstract void use(TriConsumer<? super A, ? super B, ? super C> var1);

    public int hashCode() {
        return Objects.hash(this.getFirst(), this.getSecond(), this.getThird());
    }

    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that instanceof Class114 other) {
            return Objects.equals(this.getFirst(), other.getFirst()) && Objects.equals(this.getSecond(), other.getSecond()) && Objects.equals(this.getThird(), other.getThird());
        }
        return false;
    }

    public interface TriConsumer<T, U, V> {
        void accept(T var1, U var2, V var3);
    }

    public interface TriFunction<T, U, V, R> {
        R apply(T var1, U var2, V var3);
    }
}

