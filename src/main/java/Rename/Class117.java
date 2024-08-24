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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.util.HashMap;

import net.minecraft.client.Minecraft;

public class Class117 {
    public static HashMap<String, Class116> draggables = new HashMap();
    private static final File DRAG_DATA = new File(Minecraft.getMinecraft().mcDataDir + "/" + Class1.instance.Method4(), "Class96.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().setLenient().create();

    public static void saveDragData() {
        if (!DRAG_DATA.exists()) {
            DRAG_DATA.getParentFile().mkdirs();
        }
        try {
            Files.write(DRAG_DATA.toPath(), GSON.toJson(draggables.values()).getBytes(StandardCharsets.UTF_8));
        }
        catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Failed to save draggables");
        }
    }

    public static void loadDragData() {
        Class116[] draggings;
        if (!DRAG_DATA.exists()) {
            System.out.println("No drag data found");
            return;
        }
        try {
            draggings = GSON.fromJson(new String(Files.readAllBytes(DRAG_DATA.toPath()), StandardCharsets.UTF_8), Class116[].class);
        }
        catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Failed to load draggables");
            return;
        }
        for (Class116 dragging : draggings) {
            if (!draggables.containsKey(dragging.getName())) continue;
            Class116 currentDrag = draggables.get(dragging.getName());
            currentDrag.setX(dragging.getX());
            currentDrag.setY(dragging.getY());
            draggables.put(dragging.getName(), currentDrag);
        }
    }
}

