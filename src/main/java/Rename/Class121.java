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

import java.util.function.BiConsumer;
import java.util.function.BiFunction;

public final class Class121<A, B>
extends Class123<A, B> {
    private final A a;
    private final B b;

    Class121(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public static <A, B> Class121<A, B> of(A a, B b) {
        return new Class121<A, B>(a, b);
    }

    public Class123<A, A> pairOfFirst() {
        return of(this.a);
    }

    public Class123<B, B> pairOfSecond() {
        return of(this.b);
    }

    @Override
    public A getFirst() {
        return this.a;
    }

    @Override
    public B getSecond() {
        return this.b;
    }

    @Override
    public <R> R apply(BiFunction<? super A, ? super B, ? extends R> func) {
        return func.apply(this.a, this.b);
    }

    @Override
    public void use(BiConsumer<? super A, ? super B> func) {
        func.accept(this.a, this.b);
    }
}

