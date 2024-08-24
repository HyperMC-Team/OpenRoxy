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
public class Class328 {
    public static float max(float argNum, float argMax) {
        if (argNum > argMax) {
            return argMax;
        }
        return argNum;
    }

    public static Vector3f max(Vector3f argNum, float argMax) {
        if (argNum.x > argMax) {
            argNum.x = argMax;
        }
        if (argNum.y > argMax) {
            argNum.y = argMax;
        }
        if (argNum.z > argMax) {
            argNum.z = argMax;
        }
        return argNum;
    }

    public static float min(float argNum, float argMax) {
        if (argNum < argMax) {
            return argMax;
        }
        return argNum;
    }

    public static Vector3f translate(Vector3f num, Vector3f move) {
        num.x += move.x;
        num.y += move.y;
        num.z += move.z;
        return num;
    }

    public static Vector3f scale(Vector3f num, Vector3f move) {
        num.x *= move.x;
        num.y *= move.y;
        num.z *= move.z;
        return num;
    }

    public static Vector3f rotateX(Vector3f num, float rotation) {
        Vector3f y = new Vector3f();
        Vector3f z = new Vector3f();
        double cos = Math.cos((double)((180.0f + rotation) / 180.0f) * Math.PI);
        double sin = Math.sin((double)((180.0f + rotation) / 180.0f) * Math.PI);
        y.y = (float)cos;
        y.z = (float)sin;
        y.normalise();
        Vector3f vector3f = y;
        vector3f.y = vector3f.y * -num.y;
        y.z *= num.y;
        z.y = (float)sin;
        z.z = (float)cos;
        z.normalise();
        z.y *= -num.z;
        vector3f = z;
        vector3f.z = vector3f.z * -num.z;
        num = new Vector3f(num.x, y.y + z.y, y.z + z.z);
        return num;
    }

    public static Vector3f rotateY(Vector3f num, float rotation) {
        Vector3f x = new Vector3f();
        Vector3f z = new Vector3f();
        x.x = (float)Math.cos((double)(-rotation / 180.0f) * Math.PI);
        x.z = (float)Math.sin((double)(-rotation / 180.0f) * Math.PI);
        x.normalise();
        Vector3f vector3f = x;
        vector3f.x = vector3f.x * -num.x;
        x.z *= num.x;
        z.x = (float)Math.sin((double)(-rotation / 180.0f) * Math.PI);
        z.z = (float)Math.cos((double)(-rotation / 180.0f) * Math.PI);
        z.normalise();
        z.x *= num.z;
        z.z *= num.z;
        num = new Vector3f(x.x + z.x, num.y, x.z + z.z);
        return num;
    }

    public static Vector3f rotateZ(Vector3f num, float rotation) {
        Vector3f x = new Vector3f();
        Vector3f y = new Vector3f();
        double cos = Math.cos((double)((rotation - 90.0f) / 180.0f) * Math.PI);
        double sin = Math.sin((double)((rotation - 90.0f) / 180.0f) * Math.PI);
        x.x = (float)sin;
        x.y = (float)cos;
        x.normalise();
        Vector3f vector3f = x;
        vector3f.x = vector3f.x * -num.x;
        x.y *= num.x;
        y.x = (float)cos;
        y.y = (float)sin;
        y.normalise();
        y.x *= -num.y;
        vector3f = y;
        vector3f.y = vector3f.y * -num.y;
        num = new Vector3f(y.x + x.x, y.y + x.y, num.z);
        return num;
    }

    public static Vector3f[] translate(Vector3f[] nums, Vector3f move) {
        for (int i = 0; i < nums.length; ++i) {
            nums[i] = Class328.translate(nums[i], move);
        }
        return nums;
    }

    public static Vector3f[] scale(Vector3f[] nums, Vector3f move) {
        for (int i = 0; i < nums.length; ++i) {
            nums[i] = Class328.scale(nums[i], move);
        }
        return nums;
    }

    public static Vector3f[] rotateX(Vector3f[] nums, float move) {
        for (int i = 0; i < nums.length; ++i) {
            nums[i] = Class328.rotateX(nums[i], move);
        }
        return nums;
    }

    public static Vector3f[] rotateY(Vector3f[] nums, float move) {
        for (int i = 0; i < nums.length; ++i) {
            nums[i] = Class328.rotateY(nums[i], move);
        }
        return nums;
    }

    public static Vector3f[] rotateZ(Vector3f[] nums, float move) {
        for (int i = 0; i < nums.length; ++i) {
            nums[i] = Class328.rotateZ(nums[i], move);
        }
        return nums;
    }
}

