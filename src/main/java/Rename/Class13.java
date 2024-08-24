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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public final class Class13 {
    private static final Map<Class<? extends Class7>, List<MethodData>> REGISTRY_MAP = new HashMap<Class<? extends Class7>, List<MethodData>>();

    private Class13() {
    }

    public static void Method1(Object object) {
        for (Method method : object.getClass().getDeclaredMethods()) {
            if (Class13.Method6(method)) continue;
            Class13.Method1(method, object);
        }
    }

    public static void Method1(Object object, Class<? extends Class7> eventClass) {
        for (Method method : object.getClass().getDeclaredMethods()) {
            if (Class13.Method6(method, eventClass)) continue;
            Class13.Method1(method, object);
        }
    }

    public static void Method2(Object object) {
        for (List<MethodData> dataList : REGISTRY_MAP.values()) {
            for (MethodData data : dataList) {
                if (!data.getSource().equals(object)) continue;
                dataList.remove(data);
            }
        }
        Class13.Method4(true);
    }

    public static void Method2(Object object, Class<? extends Class7> eventClass) {
        if (REGISTRY_MAP.containsKey(eventClass)) {
            for (MethodData data : REGISTRY_MAP.get(eventClass)) {
                if (!data.getSource().equals(object)) continue;
                REGISTRY_MAP.get(eventClass).remove(data);
            }
            Class13.Method4(true);
        }
    }

    private static void Method1(Method method, Object object) {
        Class<?> indexClass = method.getParameterTypes()[0];
        final MethodData data = new MethodData(object, method, method.getAnnotation(Class14.class).value());
        if (!data.getTarget().isAccessible()) {
            data.getTarget().setAccessible(true);
        }
        if (REGISTRY_MAP.containsKey(indexClass)) {
            if (!REGISTRY_MAP.get(indexClass).contains(data)) {
                REGISTRY_MAP.get(indexClass).add(data);
                Class13.Method5((Class<? extends Class7>) indexClass);
            }
        } else {
            REGISTRY_MAP.put((Class<? extends Class7>) indexClass, new CopyOnWriteArrayList<MethodData>(){
                private static final long serialVersionUID = 666L;
                {
                    this.add(data);
                }
            });
        }
    }

    public static void Method3(Class<? extends Class7> indexClass) {
        Iterator<Map.Entry<Class<? extends Class7>, List<MethodData>>> mapIterator = REGISTRY_MAP.entrySet().iterator();
        while (mapIterator.hasNext()) {
            if (!mapIterator.next().getKey().equals(indexClass)) continue;
            mapIterator.remove();
            break;
        }
    }

    public static void Method4(boolean onlyEmptyEntries) {
        Iterator<Map.Entry<Class<? extends Class7>, List<MethodData>>> mapIterator = REGISTRY_MAP.entrySet().iterator();
        while (mapIterator.hasNext()) {
            if (onlyEmptyEntries && !mapIterator.next().getValue().isEmpty()) continue;
            mapIterator.remove();
        }
    }

    private static void Method5(Class<? extends Class7> indexClass) {
        CopyOnWriteArrayList<MethodData> sortedList = new CopyOnWriteArrayList<MethodData>();
        for (byte priority : Class11.VALUE_ARRAY) {
            for (MethodData data : REGISTRY_MAP.get(indexClass)) {
                if (data.getPriority() != priority) continue;
                sortedList.add(data);
            }
        }
        REGISTRY_MAP.put(indexClass, sortedList);
    }

    private static boolean Method6(Method method) {
        return method.getParameterTypes().length != 1 || !method.isAnnotationPresent(Class14.class);
    }

    private static boolean Method6(Method method, Class<? extends Class7> eventClass) {
        return Class13.Method6(method) || !method.getParameterTypes()[0].equals(eventClass);
    }

    public static Class7 Method7(Class7 event) {
        block5: {
            List<MethodData> dataList = REGISTRY_MAP.get(event.getClass());
            if (dataList == null) break block5;
            if (event instanceof Class8 stoppable) {
                for (MethodData data : dataList) {
                    Class13.Method8(data, event);
                    if (!stoppable.isStopped()) {
                        continue;
                    }
                    break;
                }
            } else {
                for (MethodData data : dataList) {
                    Class13.Method8(data, event);
                }
            }
        }
        return event;
    }

    private static void Method8(MethodData data, Class7 argument) {
        try {
            data.getTarget().invoke(data.getSource(), argument);
        }
        catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException exception) {
            // empty catch block
        }
    }

    private static final class MethodData {
        private final Object source;
        private final Method target;
        private final byte priority;

        public MethodData(Object source, Method target, byte priority) {
            this.source = source;
            this.target = target;
            this.priority = priority;
        }

        public Object getSource() {
            return this.source;
        }

        public Method getTarget() {
            return this.target;
        }

        public byte getPriority() {
            return this.priority;
        }
    }
}

