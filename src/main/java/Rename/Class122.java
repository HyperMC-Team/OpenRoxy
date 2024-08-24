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
import java.util.function.UnaryOperator;

public class Class122<A, B>
extends Class123<A, B> {
    private A a;
    private B b;

    Class122(A a, B b) {
        this.a = a;
        this.b = b;
    }

    public static <A, B> Class122<A, B> of(A a, B b) {
        return new Class122<A, B>(a, b);
    }

    public static <A> Class122<A, A> of(A a) {
        return new Class122<A, A>(a, a);
    }

    public Class122<A, A> pairOfFirst() {
        return Class122.of(this.a);
    }

    public Class122<B, B> pairOfSecond() {
        return Class122.of(this.b);
    }

    @Override
    public A getFirst() {
        return this.a;
    }

    @Override
    public B getSecond() {
        return this.b;
    }

    public void setFirst(A a) {
        this.a = a;
    }

    public void setSecond(B b) {
        this.b = b;
    }

    @Override
    public <R> R apply(BiFunction<? super A, ? super B, ? extends R> func) {
        return func.apply(this.a, this.b);
    }

    @Override
    public void use(BiConsumer<? super A, ? super B> func) {
        func.accept(this.a, this.b);
    }

    public void computeFirst(UnaryOperator<A> operator) {
        this.a = operator.apply(this.a);
    }

    public void computeSecond(UnaryOperator<B> operator) {
        this.b = operator.apply(this.b);
    }
}

