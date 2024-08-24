package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

public final class Class155 {
    private final Class154 placeInfo;
    public final Class156 rotation;

    public Class154 getPlaceInfo() {
        return this.placeInfo;
    }

    public Class156 getRotation() {
        return this.rotation;
    }

    public Class155(Class154 placeInfo, Class156 rotation) {
        this.placeInfo = placeInfo;
        this.rotation = rotation;
    }

    public Class154 component1() {
        return this.placeInfo;
    }

    public Class156 component2() {
        return this.rotation;
    }

    public Class155 copy(Class154 placeInfo, Class156 rotation) {
        return new Class155(placeInfo, rotation);
    }

    public static Class155 copy$default(Class155 var0, Class154 var1, Class156 var2, int var3, Object var4) {
        if ((var3 & 1) != 0) {
            var1 = var0.placeInfo;
        }
        if ((var3 & 2) != 0) {
            var2 = var0.rotation;
        }
        return var0.copy(var1, var2);
    }

    public String toString() {
        return "Class155(placeInfo=" + this.placeInfo + ", rotation=" + this.rotation + ")";
    }

    public int hashCode() {
        return (this.placeInfo != null ? this.placeInfo.hashCode() : 0) * 31 + (this.rotation != null ? this.rotation.hashCode() : 0);
    }

    public boolean equals(Object var1) {
        if (this != var1) {
            if (!(var1 instanceof Class155 var2)) {
                return false;
            }
            return this.placeInfo.equals(var2.placeInfo) && this.rotation.equals(var2.rotation);
        }
        return true;
    }
}

