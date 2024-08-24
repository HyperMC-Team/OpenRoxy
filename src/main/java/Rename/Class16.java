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

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import org.lwjgl.input.Keyboard;
import tech.skidonion.obfuscator.annotations.Renamer;
import tech.skidonion.obfuscator.annotations.StringEncryption;

@Renamer
@StringEncryption
public class Class16
implements Class3 {
    private static final int CACHE_SIZE = 10;
    private final LinkedList<String> chatCache = new LinkedList();

    @Class14
    public void onChat(Class42 event) {
        String msg = event.getMessage();
        if (!msg.startsWith(".")) {
            return;
        }
        this.addToCache(msg);
        String[] command = msg.substring(1).split(" ");
        if (command.length == 0) {
            return;
        }
        switch (command[0]) {
            case "bind": {
                try {
                    Class252 module = Class262.getModuleByName(command[1]);
                    module.setKey(Keyboard.getKeyIndex(command[2].toUpperCase()));
                    Class162.addChatMessage("Bind " + module.getName() + " to " + command[2].toUpperCase());
                    event.setCancelled(true);
                }
                catch (Exception e) {
                    event.setCancelled(true);
                    Class162.print(e.getMessage());
                }
                event.setCancelled(true);
                break;
            }
            case "binds": {
                for (Class252 module : Class262.getModules()) {
                    if (module.getKey() == 0) continue;
                    Class162.addChatMessage(module.getName() + " " + Keyboard.getKeyName(module.getKey()));
                }
                event.setCancelled(true);
                break;
            }
            case "config": {
                if (command[1].equals("load")) {
                    Class1.instance.Method9().loadConfig(Class1.instance.Method9().readConfigData(new File(Minecraft.getMinecraft().mcDataDir + "/" + Class1.instance.Method4() + "/Configs/" + command[2] + ".json").toPath()));
                    Class162.addChatMessage("Config loaded");
                }
                event.setCancelled(true);
            }
        }
    }

    private void addToCache(String msg) {
        if (this.chatCache.size() >= 10) {
            this.chatCache.removeFirst();
        }
        this.chatCache.addLast(msg);
        this.updateChatHistory(msg);
    }

    private void updateChatHistory(String msg) {
        GuiChat guiChat = (GuiChat) Class16.mc.currentScreen;
        if (guiChat != null) {
            List<String> history = Class16.mc.ingameGUI.getChatGUI().getSentMessages();
            if (history.size() >= 10) {
                history.remove(0);
            }
            history.add(msg);
        }
    }
}

