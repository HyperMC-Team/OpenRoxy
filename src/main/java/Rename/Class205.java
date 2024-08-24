package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.MathHelper;

public class Class205 {
    public String anim;
    public String model;
    public List<Calculation> calculations = new ArrayList<Calculation>();
    public EnumBoxProperty prop;
    public Class327 axis;
    public float smooth;
    public EnumModifier mod;

    public Class205 setModifier(EnumModifier argMod) {
        this.mod = argMod;
        return this;
    }

    public float getNumber(float in) {
        return Calculation.calculateAll(this.mod, in, this.calculations);
    }

    public enum EnumModifier {
        COS,
        SIN

    }

    public static class Calculation {
        public EnumOperator operator;
        public float number;
        public String globalVar = null;

        public Calculation(EnumOperator argOperator, float argNumber) {
            this.operator = argOperator;
            this.number = argNumber;
        }

        public float calculate(float in) {
            float num = this.globalVar != null ? Class208.getGlobalVar(this.globalVar) : this.number;
            float out = 0.0f;
            if (this.operator == EnumOperator.ADD) {
                out = in + num;
            }
            if (this.operator == EnumOperator.SET) {
                out = num;
            }
            if (this.operator == EnumOperator.SUBSTRACT) {
                out = in - num;
            }
            if (this.operator == EnumOperator.MULTIPLY) {
                out = in * num;
            }
            if (this.operator == EnumOperator.DIVIDE) {
                out = in / num;
            }
            return out;
        }

        public static float calculateAll(EnumModifier mod, float in, List<Calculation> argCalc) {
            float out = in;
            for (int i = 0; i < argCalc.size(); ++i) {
                out = argCalc.get(i).calculate(out);
            }
            if (mod == EnumModifier.COS) {
                out = MathHelper.cos(out);
            }
            if (mod == EnumModifier.SIN) {
                out = MathHelper.sin(out);
            }
            return out;
        }
    }

    public enum EnumBoxProperty {
        ROT,
        SCALE,
        PREROT

    }

    public enum EnumOperator {
        SET,
        ADD,
        MULTIPLY,
        DIVIDE,
        SUBSTRACT

    }
}

