package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import org.lwjgl.util.vector.Vector3f;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
public class Class329 {
    public Vector3f vOld = new Vector3f(0.0f, 0.0f, 0.0f);
    public Vector3f vSmooth = new Vector3f(0.0f, 0.0f, 0.0f);
    public Vector3f vFinal = new Vector3f(0.0f, 0.0f, 0.0f);
    public Vector3f smoothness = new Vector3f(1.0f, 1.0f, 1.0f);
    public Vector3f completion = new Vector3f(0.0f, 0.0f, 0.0f);

    public void setSmooth(Vector3f argRotation, float argSmooth) {
        if (this.vFinal != argRotation) {
            this.vFinal = argRotation;
            this.vOld = this.vSmooth;
            this.completion = new Vector3f(0.0f, 0.0f, 0.0f);
            this.smoothness = new Vector3f(argSmooth, argSmooth, argSmooth);
        }
    }

    public void setSmoothZero() {
        this.setSmoothZero(1.0f);
    }

    public void setSmoothZero(float argSmooth) {
        this.setSmooth(new Vector3f(0.0f, 0.0f, 0.0f), argSmooth);
    }

    public void setSmooth(Vector3f argRotation) {
        this.setSmooth(argRotation, 1.0f);
    }

    public void setSmooth(Class327 argAxis, float argRot, float argSmooth) {
        if ((argAxis == Class327.X ? this.vFinal.x : (argAxis == Class327.Y ? this.vFinal.y : this.vFinal.z)) != argRot) {
            if (argAxis == Class327.X) {
                this.vFinal.x = argRot;
            }
            if (argAxis == Class327.Y) {
                this.vFinal.y = argRot;
            }
            if (argAxis == Class327.Z) {
                this.vFinal.z = argRot;
            }
            if (argAxis == Class327.X) {
                this.vOld.x = this.vSmooth.x;
            }
            if (argAxis == Class327.Y) {
                this.vOld.y = this.vSmooth.y;
            }
            if (argAxis == Class327.Z) {
                this.vOld.z = this.vSmooth.z;
            }
            if (argAxis == Class327.X) {
                this.completion.x = 0.0f;
            }
            if (argAxis == Class327.Y) {
                this.completion.y = 0.0f;
            }
            if (argAxis == Class327.Z) {
                this.completion.z = 0.0f;
            }
        }
        if (argAxis == Class327.X) {
            this.smoothness.x = argSmooth;
        }
        if (argAxis == Class327.Y) {
            this.smoothness.y = argSmooth;
        }
        if (argAxis == Class327.Z) {
            this.smoothness.z = argSmooth;
        }
    }

    public void setSmoothX(float argX, float argSmooth) {
        if (this.vFinal.x != argX) {
            this.vFinal.x = argX;
            this.vOld.x = this.vSmooth.x;
            this.completion.x = 0.0f;
        }
        this.smoothness.x = argSmooth;
    }

    public void setSmoothY(float argY, float argSmooth) {
        if (this.vFinal.y != argY) {
            this.vFinal.y = argY;
            this.vOld.y = this.vSmooth.y;
            this.completion.y = 0.0f;
        }
        this.smoothness.y = argSmooth;
    }

    public void setSmoothZ(float argZ, float argSmooth) {
        if (this.vFinal.z != argZ) {
            this.vFinal.z = argZ;
            this.vOld.z = this.vSmooth.z;
            this.completion.z = 0.0f;
        }
        this.smoothness.z = argSmooth;
    }

    public void setSmoothX(float argX) {
        this.setSmoothX(argX, 0.6f);
    }

    public void setSmoothY(float argY) {
        this.setSmoothY(argY, 0.6f);
    }

    public void setSmoothZ(float argZ) {
        this.setSmoothZ(argZ, 0.6f);
    }

    public void setX(float argX) {
        this.vOld.x = argX;
        this.vSmooth.x = argX;
        this.vFinal.x = argX;
        this.completion.x = 1.0f;
    }

    public void setY(float argY) {
        this.vOld.y = argY;
        this.vSmooth.y = argY;
        this.vFinal.y = argY;
        this.completion.y = 1.0f;
    }

    public void setZ(float argZ) {
        this.vOld.z = argZ;
        this.vSmooth.z = argZ;
        this.vFinal.z = argZ;
        this.completion.z = 1.0f;
    }

    public void set(Class329 argV) {
        this.completion = argV.completion;
        this.smoothness = argV.smoothness;
        this.vFinal = argV.vFinal;
        this.vOld = argV.vOld;
        this.vSmooth = argV.vSmooth;
    }

    public float getX() {
        return this.vSmooth.x;
    }

    public float getY() {
        return this.vSmooth.y;
    }

    public float getZ() {
        return this.vSmooth.z;
    }

    public void update(float argTicksPerFrame) {
        this.completion.x += argTicksPerFrame * this.smoothness.x;
        this.completion.y += argTicksPerFrame * this.smoothness.y;
        this.completion.z += argTicksPerFrame * this.smoothness.z;
        this.completion = Class328.max(this.completion, 1.0f);
        if (this.completion.x >= 1.0f) {
            this.vOld.x = this.vSmooth.x = this.vFinal.x;
        } else {
            this.vSmooth.x = this.vOld.x + (this.vFinal.x - this.vOld.x) * this.completion.x;
        }
        if (this.completion.y >= 1.0f) {
            this.vOld.y = this.vSmooth.y = this.vFinal.y;
        } else {
            this.vSmooth.y = this.vOld.y + (this.vFinal.y - this.vOld.y) * this.completion.y;
        }
        if (this.completion.z >= 1.0f) {
            this.vOld.z = this.vSmooth.z = this.vFinal.z;
        } else {
            this.vSmooth.z = this.vOld.z + (this.vFinal.z - this.vOld.z) * this.completion.z;
        }
        this.setSmooth(new Vector3f(0.0f, 0.0f, 0.0f), 0.5f);
    }
}

