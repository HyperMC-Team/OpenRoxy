package Rename;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class Class478 {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static JsonObject fromJson(String s) {
        return gson.fromJson(s, JsonObject.class);
    }

    public static <T> String toJson(T obj) {
        return gson.toJson(obj);
    }

    public static <K, V> Map<K, V> formJson(String s, Class<K> kClass, Class<V> vClass) {
        return gson.fromJson(s, TypeToken.getParameterized(Map.class, new Type[]{kClass, vClass}).getType());
    }

    public static <T> List<T> fromJson(JsonElement e, Class<T> c) {
        return gson.fromJson(e, TypeToken.getParameterized(List.class, new Type[]{c}).getType());
    }

    public static <T> T fromJson(String s, Class<T> c) {
        return gson.fromJson(s, c);
    }
}

