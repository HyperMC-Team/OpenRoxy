package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import netscape.javascript.JSObject;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
public abstract class Class261 {
    @Expose
    @SerializedName(value="name")
    public String name;
    private final List<Class253<? extends Class261>> parents = new ArrayList<Class253<? extends Class261>>();

    public boolean hasParent() {
        return !this.parents.isEmpty();
    }

    public void addParent(Class253<? extends Class261> parent) {
        this.parents.add(parent);
    }

    public <T extends Class261> void addParent(T parent, Predicate<T> condition) {
        this.addParent(new Class253<T>(parent, condition));
    }

    public static <T extends Class261> void addParent(T parent, Predicate<T> condition, Class261... settings) {
        Arrays.asList(settings).forEach(s -> s.addParent(new Class253<T>(parent, condition)));
    }

    public boolean cannotBeShown() {
        if (!this.hasParent()) {
            return false;
        }
        return this.getParents().stream().noneMatch(Class253::isValid);
    }

    public String getName() {
        return this.name;
    }

    public <T extends Class261> void addJSParent(T parent, JSObject scriptFunction) {
        Predicate<Class261> predicate;
        try {
            predicate = object -> (Boolean)scriptFunction.call(null, object);
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to create predicate for parent");
        }
        this.addParent(new Class253<Class261>(parent, predicate));
    }

    public abstract <T> T getConfigValue();

    public List<Class253<? extends Class261>> getParents() {
        return this.parents;
    }
}

