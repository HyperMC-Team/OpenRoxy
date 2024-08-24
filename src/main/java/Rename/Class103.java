package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class Class103 {
    public static boolean reloadModules;
    private HashMap<Object, Class252> modules = new HashMap();

    public List<Class252> getModules() {
        return new ArrayList<Class252>(this.modules.values());
    }

    public HashMap<Object, Class252> getModuleMap() {
        return this.modules;
    }

    public List<Class252> getModulesInCategory(Class263 c) {
        return this.modules.values().stream().filter(m -> m.getCategory() == c).collect(Collectors.toList());
    }

    public Class252 get(Class<? extends Class252> mod) {
        return this.modules.get(mod);
    }

    public <T extends Class252> T getModule(Class<T> mod) {
        return (T)this.modules.get(mod);
    }

    public List<Class252> getModulesThatContainText(String text) {
        return this.getModules().stream().filter(m -> m.getName().toLowerCase().contains(text.toLowerCase())).collect(Collectors.toList());
    }

    public Class252 getModuleByName(String name) {
        return this.modules.values().stream().filter(m -> m.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public List<Class252> getModulesContains(String text) {
        return this.modules.values().stream().filter(m -> m.getName().toLowerCase().contains(text.toLowerCase())).collect(Collectors.toList());
    }

    public final List<Class252> getToggledModules() {
        return this.modules.values().stream().filter(Class252::isState).collect(Collectors.toList());
    }

    public final List<Class252> getArraylistModules(Class279 arraylistMod, List<Class252> modules) {
        return modules.stream().filter(module -> module.isState() && (!arraylistMod.importantModules.isEnabled() || !module.getCategory().equals(Class263.Render))).collect(Collectors.toList());
    }

    public void setModules(HashMap<Object, Class252> modules) {
        this.modules = modules;
    }
}

