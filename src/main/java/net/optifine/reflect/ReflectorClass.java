package net.optifine.reflect;

import net.optifine.Log;
import net.optifine.reflect.IResolvable;
import net.optifine.reflect.ReflectorField;
import net.optifine.reflect.ReflectorMethod;
import net.optifine.reflect.ReflectorResolver;

public class ReflectorClass
implements IResolvable {
    private String targetClassName = null;
    private boolean checked = false;
    private Class targetClass = null;

    public ReflectorClass(String targetClassName) {
        this.targetClassName = targetClassName;
        ReflectorResolver.register(this);
    }

    public ReflectorClass(Class targetClass) {
        this.targetClass = targetClass;
        this.targetClassName = targetClass.getName();
        this.checked = true;
    }

    public Class getTargetClass() {
        if (this.checked) {
            return this.targetClass;
        }
        this.checked = true;
        try {
            this.targetClass = Class.forName(this.targetClassName);
        }
        catch (ClassNotFoundException var2) {
            Log.log("(Reflector) Class59 not present: " + this.targetClassName);
        }
        catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return this.targetClass;
    }

    public boolean exists() {
        return this.getTargetClass() != null;
    }

    public String getTargetClassName() {
        return this.targetClassName;
    }

    public boolean isInstance(Object obj) {
        return this.getTargetClass() == null ? false : this.getTargetClass().isInstance(obj);
    }

    public ReflectorField makeField(String name) {
        return new ReflectorField(this, name);
    }

    public ReflectorMethod makeMethod(String name) {
        return new ReflectorMethod(this, name);
    }

    public ReflectorMethod makeMethod(String name, Class[] paramTypes) {
        return new ReflectorMethod(this, name, paramTypes);
    }

    @Override
    public void resolve() {
        Class oclass = this.getTargetClass();
    }
}

