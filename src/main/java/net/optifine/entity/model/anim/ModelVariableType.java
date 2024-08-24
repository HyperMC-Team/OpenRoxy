package net.optifine.entity.model.anim;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.src.Config;

public enum ModelVariableType {
    POS_X("tx"),
    POS_Y("ty"),
    POS_Z("tz"),
    ANGLE_X("rx"),
    ANGLE_Y("ry"),
    ANGLE_Z("rz"),
    OFFSET_X("ox"),
    OFFSET_Y("oy"),
    OFFSET_Z("oz"),
    SCALE_X("sx"),
    SCALE_Y("sy"),
    SCALE_Z("sz");

    private String name;
    public static ModelVariableType[] VALUES;

    private ModelVariableType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public float getFloat(ModelRenderer mr) {
        switch (this.ordinal()) {
            case 0: {
                return mr.rotationPointX;
            }
            case 1: {
                return mr.rotationPointY;
            }
            case 2: {
                return mr.rotationPointZ;
            }
            case 3: {
                return mr.rotateAngleX;
            }
            case 4: {
                return mr.rotateAngleY;
            }
            case 5: {
                return mr.rotateAngleZ;
            }
            case 6: {
                return mr.offsetX;
            }
            case 7: {
                return mr.offsetY;
            }
            case 8: {
                return mr.offsetZ;
            }
            case 9: {
                return mr.scaleX;
            }
            case 10: {
                return mr.scaleY;
            }
            case 11: {
                return mr.scaleZ;
            }
        }
        Config.warn("GetFloat not supported for: " + String.valueOf((Object)this));
        return 0.0f;
    }

    public void setFloat(ModelRenderer mr, float val) {
        switch (this.ordinal()) {
            case 0: {
                mr.rotationPointX = val;
                return;
            }
            case 1: {
                mr.rotationPointY = val;
                return;
            }
            case 2: {
                mr.rotationPointZ = val;
                return;
            }
            case 3: {
                mr.rotateAngleX = val;
                return;
            }
            case 4: {
                mr.rotateAngleY = val;
                return;
            }
            case 5: {
                mr.rotateAngleZ = val;
                return;
            }
            case 6: {
                mr.offsetX = val;
                return;
            }
            case 7: {
                mr.offsetY = val;
                return;
            }
            case 8: {
                mr.offsetZ = val;
                return;
            }
            case 9: {
                mr.scaleX = val;
                return;
            }
            case 10: {
                mr.scaleY = val;
                return;
            }
            case 11: {
                mr.scaleZ = val;
                return;
            }
        }
        Config.warn("SetFloat not supported for: " + String.valueOf((Object)this));
    }

    public static ModelVariableType parse(String str) {
        for (int i = 0; i < VALUES.length; ++i) {
            ModelVariableType modelvariabletype = VALUES[i];
            if (!modelvariabletype.getName().equals(str)) continue;
            return modelvariabletype;
        }
        return null;
    }

    static {
        VALUES = ModelVariableType.values();
    }
}

