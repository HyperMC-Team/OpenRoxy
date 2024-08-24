package net.optifine.expr;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.src.Config;
import net.minecraft.util.MathHelper;
import net.optifine.expr.ExpressionType;
import net.optifine.expr.IExpression;
import net.optifine.expr.IExpressionBool;
import net.optifine.expr.IExpressionFloat;
import net.optifine.expr.IParameters;
import net.optifine.expr.Parameters;
import net.optifine.expr.ParametersVariable;
import net.optifine.shaders.uniform.Smoother;
import net.optifine.util.MathUtils;

public enum FunctionType {
    PLUS(10, ExpressionType.FLOAT, "+", ExpressionType.FLOAT, ExpressionType.FLOAT),
    MINUS(10, ExpressionType.FLOAT, "-", ExpressionType.FLOAT, ExpressionType.FLOAT),
    MUL(11, ExpressionType.FLOAT, "*", ExpressionType.FLOAT, ExpressionType.FLOAT),
    DIV(11, ExpressionType.FLOAT, "/", ExpressionType.FLOAT, ExpressionType.FLOAT),
    MOD(11, ExpressionType.FLOAT, "%", ExpressionType.FLOAT, ExpressionType.FLOAT),
    NEG(12, ExpressionType.FLOAT, "neg", ExpressionType.FLOAT),
    PI(ExpressionType.FLOAT, "pi"),
    SIN(ExpressionType.FLOAT, "sin", ExpressionType.FLOAT),
    COS(ExpressionType.FLOAT, "cos", ExpressionType.FLOAT),
    ASIN(ExpressionType.FLOAT, "asin", ExpressionType.FLOAT),
    ACOS(ExpressionType.FLOAT, "acos", ExpressionType.FLOAT),
    TAN(ExpressionType.FLOAT, "tan", ExpressionType.FLOAT),
    ATAN(ExpressionType.FLOAT, "atan", ExpressionType.FLOAT),
    ATAN2(ExpressionType.FLOAT, "atan2", ExpressionType.FLOAT, ExpressionType.FLOAT),
    TORAD(ExpressionType.FLOAT, "torad", ExpressionType.FLOAT),
    TODEG(ExpressionType.FLOAT, "todeg", ExpressionType.FLOAT),
    MIN(ExpressionType.FLOAT, "min", new ParametersVariable().first(ExpressionType.FLOAT).repeat(ExpressionType.FLOAT)),
    MAX(ExpressionType.FLOAT, "max", new ParametersVariable().first(ExpressionType.FLOAT).repeat(ExpressionType.FLOAT)),
    CLAMP(ExpressionType.FLOAT, "clamp", ExpressionType.FLOAT, ExpressionType.FLOAT, ExpressionType.FLOAT),
    ABS(ExpressionType.FLOAT, "abs", ExpressionType.FLOAT),
    FLOOR(ExpressionType.FLOAT, "floor", ExpressionType.FLOAT),
    CEIL(ExpressionType.FLOAT, "ceil", ExpressionType.FLOAT),
    EXP(ExpressionType.FLOAT, "exp", ExpressionType.FLOAT),
    FRAC(ExpressionType.FLOAT, "frac", ExpressionType.FLOAT),
    LOG(ExpressionType.FLOAT, "log", ExpressionType.FLOAT),
    POW(ExpressionType.FLOAT, "pow", ExpressionType.FLOAT, ExpressionType.FLOAT),
    RANDOM(ExpressionType.FLOAT, "random"),
    ROUND(ExpressionType.FLOAT, "round", ExpressionType.FLOAT),
    SIGNUM(ExpressionType.FLOAT, "signum", ExpressionType.FLOAT),
    SQRT(ExpressionType.FLOAT, "sqrt", ExpressionType.FLOAT),
    FMOD(ExpressionType.FLOAT, "fmod", ExpressionType.FLOAT, ExpressionType.FLOAT),
    TIME(ExpressionType.FLOAT, "time"),
    IF(ExpressionType.FLOAT, "if", new ParametersVariable().first(ExpressionType.BOOL, ExpressionType.FLOAT).repeat(ExpressionType.BOOL, ExpressionType.FLOAT).last(ExpressionType.FLOAT)),
    NOT(12, ExpressionType.BOOL, "!", ExpressionType.BOOL),
    AND(3, ExpressionType.BOOL, "&&", ExpressionType.BOOL, ExpressionType.BOOL),
    OR(2, ExpressionType.BOOL, "||", ExpressionType.BOOL, ExpressionType.BOOL),
    GREATER(8, ExpressionType.BOOL, ">", ExpressionType.FLOAT, ExpressionType.FLOAT),
    GREATER_OR_EQUAL(8, ExpressionType.BOOL, ">=", ExpressionType.FLOAT, ExpressionType.FLOAT),
    SMALLER(8, ExpressionType.BOOL, "<", ExpressionType.FLOAT, ExpressionType.FLOAT),
    SMALLER_OR_EQUAL(8, ExpressionType.BOOL, "<=", ExpressionType.FLOAT, ExpressionType.FLOAT),
    EQUAL(7, ExpressionType.BOOL, "==", ExpressionType.FLOAT, ExpressionType.FLOAT),
    NOT_EQUAL(7, ExpressionType.BOOL, "!=", ExpressionType.FLOAT, ExpressionType.FLOAT),
    BETWEEN(7, ExpressionType.BOOL, "between", ExpressionType.FLOAT, ExpressionType.FLOAT, ExpressionType.FLOAT),
    EQUALS(7, ExpressionType.BOOL, "equals", ExpressionType.FLOAT, ExpressionType.FLOAT, ExpressionType.FLOAT),
    IN(ExpressionType.BOOL, "in", new ParametersVariable().first(ExpressionType.FLOAT).repeat(ExpressionType.FLOAT).last(ExpressionType.FLOAT)),
    SMOOTH(ExpressionType.FLOAT, "smooth", new ParametersVariable().first(ExpressionType.FLOAT).repeat(ExpressionType.FLOAT).maxCount(4)),
    TRUE(ExpressionType.BOOL, "true"),
    FALSE(ExpressionType.BOOL, "false"),
    VEC2(ExpressionType.FLOAT_ARRAY, "vec2", ExpressionType.FLOAT, ExpressionType.FLOAT),
    VEC3(ExpressionType.FLOAT_ARRAY, "vec3", ExpressionType.FLOAT, ExpressionType.FLOAT, ExpressionType.FLOAT),
    VEC4(ExpressionType.FLOAT_ARRAY, "vec4", ExpressionType.FLOAT, ExpressionType.FLOAT, ExpressionType.FLOAT, ExpressionType.FLOAT);

    private final int precedence;
    private final ExpressionType expressionType;
    private final String name;
    private final IParameters parameters;
    public static FunctionType[] VALUES;
    private static final Map<Integer, Float> mapSmooth;

    FunctionType(ExpressionType expressionType, String name, ExpressionType... parameterTypes) {
        this(0, expressionType, name, parameterTypes);
    }

    FunctionType(int precedence, ExpressionType expressionType, String name, ExpressionType... parameterTypes) {
        this(precedence, expressionType, name, new Parameters(parameterTypes));
    }

    FunctionType(ExpressionType expressionType, String name, IParameters parameters) {
        this(0, expressionType, name, parameters);
    }

    FunctionType(int precedence, ExpressionType expressionType, String name, IParameters parameters) {
        this.precedence = precedence;
        this.expressionType = expressionType;
        this.name = name;
        this.parameters = parameters;
    }

    public String getName() {
        return this.name;
    }

    public int getPrecedence() {
        return this.precedence;
    }

    public ExpressionType getExpressionType() {
        return this.expressionType;
    }

    public IParameters getParameters() {
        return this.parameters;
    }

    public int getParameterCount(IExpression[] arguments) {
        return this.parameters.getParameterTypes(arguments).length;
    }

    public ExpressionType[] getParameterTypes(IExpression[] arguments) {
        return this.parameters.getParameterTypes(arguments);
    }

    public float evalFloat(IExpression[] args) {
        switch (this.ordinal()) {
            case 0: {
                return FunctionType.evalFloat(args, 0) + FunctionType.evalFloat(args, 1);
            }
            case 1: {
                return FunctionType.evalFloat(args, 0) - FunctionType.evalFloat(args, 1);
            }
            case 2: {
                return FunctionType.evalFloat(args, 0) * FunctionType.evalFloat(args, 1);
            }
            case 3: {
                return FunctionType.evalFloat(args, 0) / FunctionType.evalFloat(args, 1);
            }
            case 4: {
                float f = FunctionType.evalFloat(args, 0);
                float f1 = FunctionType.evalFloat(args, 1);
                return f - f1 * (float)((int)(f / f1));
            }
            case 5: {
                return -FunctionType.evalFloat(args, 0);
            }
            case 6: {
                return MathHelper.PI;
            }
            case 7: {
                return MathHelper.sin(FunctionType.evalFloat(args, 0));
            }
            case 8: {
                return MathHelper.cos(FunctionType.evalFloat(args, 0));
            }
            case 9: {
                return MathUtils.asin(FunctionType.evalFloat(args, 0));
            }
            case 10: {
                return MathUtils.acos(FunctionType.evalFloat(args, 0));
            }
            case 11: {
                return (float)Math.tan(FunctionType.evalFloat(args, 0));
            }
            case 12: {
                return (float)Math.atan(FunctionType.evalFloat(args, 0));
            }
            case 13: {
                return (float)MathHelper.atan2(FunctionType.evalFloat(args, 0), FunctionType.evalFloat(args, 1));
            }
            case 14: {
                return MathUtils.toRad(FunctionType.evalFloat(args, 0));
            }
            case 15: {
                return MathUtils.toDeg(FunctionType.evalFloat(args, 0));
            }
            case 16: {
                return this.getMin(args);
            }
            case 17: {
                return this.getMax(args);
            }
            case 18: {
                return Float.valueOf(MathHelper.clamp_float(FunctionType.evalFloat(args, 0), FunctionType.evalFloat(args, 1), FunctionType.evalFloat(args, 2))).floatValue();
            }
            case 19: {
                return MathHelper.abs(FunctionType.evalFloat(args, 0));
            }
            case 22: {
                return (float)Math.exp(FunctionType.evalFloat(args, 0));
            }
            case 20: {
                return MathHelper.floor_float(FunctionType.evalFloat(args, 0));
            }
            case 21: {
                return MathHelper.ceiling_float_int(FunctionType.evalFloat(args, 0));
            }
            case 23: {
                return (float)MathHelper.func_181162_h(FunctionType.evalFloat(args, 0));
            }
            case 24: {
                return (float)Math.log(FunctionType.evalFloat(args, 0));
            }
            case 25: {
                return (float)Math.pow(FunctionType.evalFloat(args, 0), FunctionType.evalFloat(args, 1));
            }
            case 26: {
                return (float)Math.random();
            }
            case 27: {
                return Math.round(FunctionType.evalFloat(args, 0));
            }
            case 28: {
                return Math.signum(FunctionType.evalFloat(args, 0));
            }
            case 29: {
                return MathHelper.sqrt_float(FunctionType.evalFloat(args, 0));
            }
            case 30: {
                float f2 = FunctionType.evalFloat(args, 0);
                float f3 = FunctionType.evalFloat(args, 1);
                return f2 - f3 * (float)MathHelper.floor_float(f2 / f3);
            }
            case 31: {
                Minecraft minecraft = Minecraft.getMinecraft();
                WorldClient world = minecraft.theWorld;
                if (world == null) {
                    return 0.0f;
                }
                return (float)(world.getTotalWorldTime() % 24000L) + Config.renderPartialTicks;
            }
            case 32: {
                int i = (args.length - 1) / 2;
                for (int k = 0; k < i; ++k) {
                    int l = k * 2;
                    if (!FunctionType.evalBool(args, l)) continue;
                    return FunctionType.evalFloat(args, l + 1);
                }
                return FunctionType.evalFloat(args, i * 2);
            }
            case 45: {
                int j = (int)FunctionType.evalFloat(args, 0);
                float f4 = FunctionType.evalFloat(args, 1);
                float f5 = args.length > 2 ? FunctionType.evalFloat(args, 2) : 1.0f;
                float f6 = args.length > 3 ? FunctionType.evalFloat(args, 3) : f5;
                return Smoother.getSmoothValue(j, f4, f5, f6);
            }
        }
        Config.warn("Unknown function type: " + this);
        return 0.0f;
    }

    private float getMin(IExpression[] exprs) {
        if (exprs.length == 2) {
            return Math.min(FunctionType.evalFloat(exprs, 0), FunctionType.evalFloat(exprs, 1));
        }
        float f = FunctionType.evalFloat(exprs, 0);
        for (int i = 1; i < exprs.length; ++i) {
            float f1 = FunctionType.evalFloat(exprs, i);
            if (!(f1 < f)) continue;
            f = f1;
        }
        return f;
    }

    private float getMax(IExpression[] exprs) {
        if (exprs.length == 2) {
            return Math.max(FunctionType.evalFloat(exprs, 0), FunctionType.evalFloat(exprs, 1));
        }
        float f = FunctionType.evalFloat(exprs, 0);
        for (int i = 1; i < exprs.length; ++i) {
            float f1 = FunctionType.evalFloat(exprs, i);
            if (!(f1 > f)) continue;
            f = f1;
        }
        return f;
    }

    private static float evalFloat(IExpression[] exprs, int index) {
        IExpressionFloat iexpressionfloat = (IExpressionFloat)exprs[index];
        return iexpressionfloat.eval();
    }

    public boolean evalBool(IExpression[] args) {
        switch (this.ordinal()) {
            case 46: {
                return true;
            }
            case 47: {
                return false;
            }
            case 33: {
                return !FunctionType.evalBool(args, 0);
            }
            case 34: {
                return FunctionType.evalBool(args, 0) && FunctionType.evalBool(args, 1);
            }
            case 35: {
                return FunctionType.evalBool(args, 0) || FunctionType.evalBool(args, 1);
            }
            case 36: {
                return FunctionType.evalFloat(args, 0) > FunctionType.evalFloat(args, 1);
            }
            case 37: {
                return FunctionType.evalFloat(args, 0) >= FunctionType.evalFloat(args, 1);
            }
            case 38: {
                return FunctionType.evalFloat(args, 0) < FunctionType.evalFloat(args, 1);
            }
            case 39: {
                return FunctionType.evalFloat(args, 0) <= FunctionType.evalFloat(args, 1);
            }
            case 40: {
                return FunctionType.evalFloat(args, 0) == FunctionType.evalFloat(args, 1);
            }
            case 41: {
                return FunctionType.evalFloat(args, 0) != FunctionType.evalFloat(args, 1);
            }
            case 42: {
                float f = FunctionType.evalFloat(args, 0);
                return f >= FunctionType.evalFloat(args, 1) && f <= FunctionType.evalFloat(args, 2);
            }
            case 43: {
                float f1 = FunctionType.evalFloat(args, 0) - FunctionType.evalFloat(args, 1);
                float f2 = FunctionType.evalFloat(args, 2);
                return Math.abs(f1) <= f2;
            }
            case 44: {
                float f3 = FunctionType.evalFloat(args, 0);
                for (int i = 1; i < args.length; ++i) {
                    float f4 = FunctionType.evalFloat(args, i);
                    if (f3 != f4) continue;
                    return true;
                }
                return false;
            }
        }
        Config.warn("Unknown function type: " + this);
        return false;
    }

    private static boolean evalBool(IExpression[] exprs, int index) {
        IExpressionBool iexpressionbool = (IExpressionBool)exprs[index];
        return iexpressionbool.eval();
    }

    public float[] evalFloatArray(IExpression[] args) {
        switch (this.ordinal()) {
            case 48: {
                return new float[]{FunctionType.evalFloat(args, 0), FunctionType.evalFloat(args, 1)};
            }
            case 49: {
                return new float[]{FunctionType.evalFloat(args, 0), FunctionType.evalFloat(args, 1), FunctionType.evalFloat(args, 2)};
            }
            case 50: {
                return new float[]{FunctionType.evalFloat(args, 0), FunctionType.evalFloat(args, 1), FunctionType.evalFloat(args, 2), FunctionType.evalFloat(args, 3)};
            }
        }
        Config.warn("Unknown function type: " + this);
        return null;
    }

    public static FunctionType parse(String str) {
        for (int i = 0; i < VALUES.length; ++i) {
            FunctionType functiontype = VALUES[i];
            if (!functiontype.getName().equals(str)) continue;
            return functiontype;
        }
        return null;
    }

    static {
        VALUES = FunctionType.values();
        mapSmooth = new HashMap<Integer, Float>();
    }
}

