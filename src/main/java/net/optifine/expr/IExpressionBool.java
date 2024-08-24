package net.optifine.expr;

import net.optifine.expr.ExpressionType;
import net.optifine.expr.IExpression;

public interface IExpressionBool
extends IExpression {
    boolean eval();

    @Override
    default ExpressionType getExpressionType() {
        return ExpressionType.BOOL;
    }
}

