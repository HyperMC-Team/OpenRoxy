package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.util.function.Function;
import org.apache.commons.lang3.StringUtils;

public enum Class226 {
    LINEAR(x -> x),
    EASE_IN_QUAD(x -> x * x),
    EASE_OUT_QUAD(x -> x * (2.0 - x)),
    EASE_IN_OUT_QUAD(x -> x < 0.5 ? 2.0 * x * x : -1.0 + (4.0 - 2.0 * x) * x),
    EASE_IN_CUBIC(x -> x * x * x),
    EASE_OUT_CUBIC(x -> {
        x = x - 1.0;
        return x * x * x + 1.0;
    }),
    EASE_IN_OUT_CUBIC(x -> x < 0.5 ? 4.0 * x * x * x : (x - 1.0) * (2.0 * x - 2.0) * (2.0 * x - 2.0) + 1.0),
    EASE_IN_QUART(x -> x * x * x * x),
    EASE_OUT_QUART(x -> {
        x = x - 1.0;
        return 1.0 - x * x * x * x;
    }),
    EASE_IN_OUT_QUART(x -> {
        double d;
        if (x < 0.5) {
            d = 8.0 * x * x * x * x;
        } else {
            x = x - 1.0;
            d = 1.0 - 8.0 * x * x * x * x;
        }
        return d;
    }),
    EASE_IN_QUINT(x -> x * x * x * x * x),
    EASE_OUT_QUINT(x -> {
        x = x - 1.0;
        return 1.0 + x * x * x * x * x;
    }),
    EASE_IN_OUT_QUINT(x -> {
        double d;
        if (x < 0.5) {
            d = 16.0 * x * x * x * x * x;
        } else {
            x = x - 1.0;
            d = 1.0 + 16.0 * x * x * x * x * x;
        }
        return d;
    }),
    EASE_IN_SINE(x -> 1.0 - Math.cos(x * Math.PI / 2.0)),
    EASE_OUT_SINE(x -> Math.sin(x * Math.PI / 2.0)),
    EASE_IN_OUT_SINE(x -> 1.0 - Math.cos(Math.PI * x / 2.0)),
    EASE_IN_EXPO(x -> x == 0.0 ? 0.0 : Math.pow(2.0, 10.0 * x - 10.0)),
    EASE_OUT_EXPO(x -> x == 1.0 ? 1.0 : 1.0 - Math.pow(2.0, -10.0 * x)),
    EASE_IN_OUT_EXPO(x -> x == 0.0 ? 0.0 : (x == 1.0 ? 1.0 : (x < 0.5 ? Math.pow(2.0, 20.0 * x - 10.0) / 2.0 : (2.0 - Math.pow(2.0, -20.0 * x + 10.0)) / 2.0))),
    EASE_IN_CIRC(x -> 1.0 - Math.sqrt(1.0 - x * x)),
    EASE_OUT_CIRC(x -> {
        x = x - 1.0;
        return Math.sqrt(1.0 - x * x);
    }),
    EASE_IN_OUT_CIRC(x -> x < 0.5 ? (1.0 - Math.sqrt(1.0 - 4.0 * x * x)) / 2.0 : (Math.sqrt(1.0 - 4.0 * (x - 1.0) * x) + 1.0) / 2.0),
    SIGMOID(x -> 1.0 / (1.0 + Math.exp(-x.doubleValue()))),
    EASE_OUT_ELASTIC(x -> x == 0.0 ? 0.0 : (x == 1.0 ? 1.0 : Math.pow(2.0, -10.0 * x) * Math.sin((x * 10.0 - 0.75) * 2.0943951023931953) * 0.5 + 1.0)),
    EASE_IN_BACK(x -> 2.70158 * x * x * x - 1.70158 * x * x);

    private final Function<Double, Double> function;

    Class226(Function<Double, Double> function) {
        this.function = function;
    }

    public Function<Double, Double> getFunction() {
        return this.function;
    }

    public String toString() {
        return StringUtils.capitalize(super.toString().toLowerCase().replace("_", " "));
    }
}

