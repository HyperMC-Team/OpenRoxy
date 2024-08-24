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

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.gui.ScaledResolution;
import tech.skidonion.obfuscator.annotations.NativeObfuscation;
import tech.skidonion.obfuscator.annotations.Renamer;
import tech.skidonion.obfuscator.annotations.StringEncryption;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
@Renamer
@NativeObfuscation
@StringEncryption
public class Class262
implements Class3 {
    public static List<Class252> modules = new ArrayList<Class252>();

    @NativeObfuscation.Inline
    public void initialize() {
        this.addModule(new Class291());
        this.addModule(new Class294());
        this.addModule(new Class313());
        this.addModule(new Class264());
        this.addModule(new Class295());
        this.addModule(new Class293());
        this.addModule(new Class265());
        this.addModule(new Class312());
        this.addModule(new Class292());
        this.addModule(new Class298());
        this.addModule(new Class273());
        this.addModule(new Class270());
        this.addModule(new Class271());
        this.addModule(new Class274());
        this.addModule(new Class296());
        this.addModule(new Class297());
        this.addModule(new Class278());
        this.addModule(new Class310());
        this.addModule(new Class308());
        this.addModule(new Class309());
        this.addModule(new Class277());
        this.addModule(new Class311());
        this.addModule(new Class267());
        this.addModule(new Class290());
        this.addModule(new Class266());
        this.addModule(new Class288());
        this.addModule(new Class286());
        this.addModule(new Class269());
        this.addModule(new Class268());
        this.addModule(new Class289());
        this.addModule(new Class272());
        this.addModule(new Class287());
        this.addModule(new Class284());
        this.addModule(new Class305());
        this.addModule(new Class303());
        this.addModule(new Class300());
        this.addModule(new Class285());
        this.addModule(new Class304());
        this.addModule(new Class275());
        this.addModule(new Class276());
        this.addModule(new Class301());
        this.addModule(new Class302());
        this.addModule(new Class299());
        this.addModule(new Class322());
        this.addModule(new Class281());
        this.addModule(new Class323());
        this.addModule(new Class307());
        this.addModule(new Class280());
        this.addModule(new Class306());
        this.addModule(new Class279());
        this.addModule(new Class325());
        this.addModule(new Class314());
        this.addModule(new Class315());
        this.addModule(new Class319());
        this.addModule(new Class320());
        this.addModule(new Class317());
        this.addModule(new Class321());
        this.addModule(new Class318());
        this.addModule(new Class316());
        this.addModule(new Class326());
        this.addModule(new Class324());
        this.addModule(new Class283());
    }

    @Class14
    public void onKeyPress(Class46 event) {
        for (Class252 m : modules) {
            if (event.getKey() == 54) {
                mc.displayGuiScreen(new Class102());
            }
            if (m.getKey() != event.getKey()) continue;
            m.state();
        }
    }

    @Class14
    public void onTick(Class52 event) {
        if (!Class262.getModule(Class272.class).isState()) {
            Class262.getModule(Class272.class).state();
        }
        Class252.sr = new ScaledResolution(mc);
    }

    public static <T> T getModule(Class<T> clazz) {
        for (Class252 m : modules) {
            if (m.getClass() != clazz) continue;
            return (T)m;
        }
        return (T)new Class252("Null", Class263.Combat);
    }

    private void addModule(Class252 module) {
        for (Field field : module.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object obj = field.get(module);
                if (!(obj instanceof Class261)) continue;
                module.add((Class261)obj);
            }
            catch (IllegalAccessException e) {
                Class162.print(e.getMessage());
            }
        }
        module.add(new Class256(module.getKey()));
        modules.add(module);
    }

    public static Class252 getModuleByName(String name) {
        for (Class252 m : modules) {
            if (!m.getName().equalsIgnoreCase(name)) continue;
            return m;
        }
        return new Class252("Null", Class263.Combat);
    }

    public static List<Class252> getModules() {
        return modules;
    }
}

