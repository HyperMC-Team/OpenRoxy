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
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

public final class Class101<A, B, C>
extends Class114<A, B, C> {
    private final A a;
    private final B b;
    private final C c;

    Class101(A a, B b, C c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public static <A, B, C> Class101<A, B, C> of(A a, B b, C c) {
        return new Class101<A, B, C>(a, b, c);
    }

    public Class114<A, A, A> pairOfFirst() {
        return of(this.a);
    }

    public Class114<B, B, B> pairOfSecond() {
        return of(this.b);
    }

    public Class114<C, C, C> pairOfThird() {
        return of(this.c);
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
    public C getThird() {
        return this.c;
    }

    @Override
    public <R> R apply(Class114.TriFunction<? super A, ? super B, ? super C, ? extends R> func) {
        return func.apply(this.a, this.b, this.c);
    }

    @Override
    public void use(Class114.TriConsumer<? super A, ? super B, ? super C> func) {
        func.accept(this.a, this.b, this.c);
    }
}

