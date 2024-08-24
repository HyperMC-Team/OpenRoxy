package net.optifine.expr;

import net.optifine.expr.ExpressionType;
import net.optifine.expr.FunctionType;
import net.optifine.expr.IExpression;
import net.optifine.expr.IExpressionFloatArray;

public class FunctionFloatArray
implements IExpressionFloatArray {
    private final FunctionType type;
    private final IExpression[] arguments;

    public FunctionFloatArray(FunctionType type, IExpression[] arguments) {
        this.type = type;
        this.arguments = arguments;
    }

    @Override
    public float[] eval() {
        return this.type.evalFloatArray(this.arguments);
    }

    @Override
    public ExpressionType getExpressionType() {
        return ExpressionType.FLOAT_ARRAY;
    }

    public String toString() {
        return this.type + "()";
    }
}

