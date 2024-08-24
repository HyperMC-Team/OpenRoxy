package Rename;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import net.minecraft.network.PacketBuffer;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.BaseConstructor;
import org.yaml.snakeyaml.constructor.Constructor;

public class Class432
implements Class441,
        Class3 {
    private String type;
    private String name;
    private String data;

    @Override
    public void process() {
        Yaml yaml = new Yaml((BaseConstructor)new Constructor(Map.class, new LoaderOptions()));
        Map data = (Map)yaml.load(this.data);
        JsonElement element = Class478.fromJson(Class478.toJson(data), JsonElement.class);
        if (!element.isJsonObject()) {
            return;
        }
        JsonObject jsonObject = element.getAsJsonObject().getAsJsonObject(this.name);
        if ("mainmenu".equals(this.name)) {
            JsonObject relativeParts = jsonObject.getAsJsonObject("\u81ea\u9002\u5e94\u80cc\u666f").getAsJsonObject("relativeParts");
            JsonObject mainRelativeParts = relativeParts.getAsJsonObject("\u4e3b\u5206\u7c7b").getAsJsonObject("relativeParts");
            List<Class446> elements = Class445.INSTANCE.getElements();
            if (elements.isEmpty()) {
                for (Map.Entry entry : mainRelativeParts.entrySet()) {
                    if (!((String)entry.getKey()).startsWith("subject")) continue;
                    JsonObject subjectObj = ((JsonElement)entry.getValue()).getAsJsonObject();
                    String string = (String)entry.getKey();
                    String defaultPath = subjectObj.get("defaultPath").getAsString();
                    defaultPath = defaultPath.substring(defaultPath.indexOf("https"));
                    String hoverPath = subjectObj.get("hoverPath").getAsString();
                    hoverPath = hoverPath.substring(hoverPath.indexOf("https"));
                    String hoverDos = subjectObj.get("hoverDos").getAsString();
                    String clickName = subjectObj.get("clickScript").getAsString();
                    clickName = clickName.split("'")[1];
                    List<String> hoverDoes = Arrays.stream(hoverDos.split("\n")).filter(s -> s.startsWith("\u00a79")).collect(Collectors.toList());
                    Class446 gameElement = new Class446(string, defaultPath, hoverPath, hoverDoes, clickName);
                    elements.add(gameElement);
                }
            }
            Class432.mc.threadPool.execute(() -> {
                Class432 packet73;
                Class432 packet732 = packet73 = this;
                synchronized (packet732) {
                    try {
                        Class458.loadImage();
                    }
                    catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            });
        } else if (this.name.endsWith("_effect")) {
            JsonObject guiObject = jsonObject.getAsJsonObject("gui");
            JsonObject amountObject = null;
            JsonObject lastObject = null;
            int index = 0;
            for (Map.Entry entry : guiObject.entrySet()) {
                if (entry.getValue() instanceof JsonObject) {
                    if (index == 2) {
                        amountObject = (JsonObject)entry.getValue();
                    }
                    lastObject = (JsonObject)entry.getValue();
                }
                ++index;
            }
            if (amountObject != null) {
                this.putText(amountObject.getAsJsonArray("texts"));
            }
            if (lastObject == null) {
                return;
            }
            JsonObject relativeParts = lastObject.getAsJsonObject("relativeParts");
            for (Map.Entry entry : relativeParts.entrySet()) {
                JsonObject inRelativeParts = ((JsonElement)entry.getValue()).getAsJsonObject().getAsJsonObject("relativeParts");
                for (Map.Entry inEntry : inRelativeParts.entrySet()) {
                    this.putText(((JsonElement)inEntry.getValue()).getAsJsonObject().getAsJsonArray("texts"));
                }
            }
        } else if (this.name.startsWith("team_")) {
            if ("team_create".equals(this.name)) {
                Class443.Type type = Class443.Type.valueOf(this.name.split("_")[1].toUpperCase());
                if (this.check(type)) {
                    return;
                }
                for (Map.Entry entry : jsonObject.entrySet()) {
                    if (!((String)entry.getKey()).equals("create") && !((String)entry.getKey()).equals("join")) continue;
                    JsonObject object = ((JsonElement)entry.getValue()).getAsJsonObject();
                    String text = object.getAsJsonArray("texts").get(0).getAsString();
                    this.addPartyButton(type, text, object.get("clickScript").getAsString());
                }
            } else if ("team_main".equals(this.name)) {
                Class443.Type type = Class443.Type.valueOf(this.name.split("_")[1].toUpperCase());
                if (this.check(type)) {
                    return;
                }
                JsonObject buttons = jsonObject.getAsJsonObject("buttons").getAsJsonObject("relativeParts");
                for (Map.Entry entry : buttons.entrySet()) {
                    JsonObject object = ((JsonElement)entry.getValue()).getAsJsonObject();
                    String text = object.getAsJsonArray("texts").get(0).getAsString();
                    this.addPartyButton(type, text, object.get("clickScript").getAsString());
                }
            } else {
                Class443.SubType type = Class443.SubType.valueOf(this.name.split("_")[1].toUpperCase());
                Map<Class443.SubType, Class442> dataMap = Class443.INSTANCE.getDataMap();
                if (!dataMap.containsKey((Object)type)) {
                    dataMap.put(type, new Class442());
                }
                String text = jsonObject.getAsJsonObject("title").getAsJsonArray("texts").get(0).getAsString();
                List<Class467> buttons = dataMap.get((Object)type).getButtons();
                buttons.clear();
                dataMap.get((Object)type).setText(text);
                if (jsonObject.has("scroll")) {
                    JsonObject scroll = jsonObject.getAsJsonObject("scroll");
                    if (!scroll.has("scrollableParts")) {
                        return;
                    }
                    JsonObject object = scroll.getAsJsonObject("scrollableParts");
                    for (Map.Entry entry : object.entrySet()) {
                        JsonObject relativeParts = ((JsonElement)entry.getValue()).getAsJsonObject().getAsJsonObject("relativeParts");
                        JsonArray texts = relativeParts.getAsJsonObject("name").getAsJsonArray("texts");
                        if (texts.size() <= 0) continue;
                        String name = texts.get(0).getAsString();
                        for (Map.Entry e : relativeParts.entrySet()) {
                            if (!((String)e.getKey()).startsWith("bt")) continue;
                            String tooltip = "";
                            if (((JsonElement)e.getValue()).getAsJsonObject().has("tooltip")) {
                                tooltip = ((JsonElement)e.getValue()).getAsJsonObject().getAsJsonArray("tooltip").get(0).getAsString();
                            }
                            if (!((JsonElement)e.getValue()).getAsJsonObject().has("clickScript")) continue;
                            Runnable clickAction = this.getClickAction(((JsonElement)e.getValue()).getAsJsonObject().get("clickScript").getAsString());
                            buttons.add(new Class467(tooltip + " " + name, clickAction));
                        }
                    }
                } else if (jsonObject.has("input")) {
                    // empty if block
                }
            }
        }
    }

    private boolean check(Class443.Type type) {
        if (!Class443.INSTANCE.getButtons().containsKey((Object)type)) {
            Class443.INSTANCE.getButtons().put(type, new ArrayList());
        }
        return !Class443.INSTANCE.getButtons().get((Object)type).isEmpty();
    }

    private void addPartyButton(Class443.Type type, String text, String script) {
        Class443.INSTANCE.getButtons().get((Object)type).add(new Class467(text, this.getClickAction(script)));
    }

    private Runnable getClickAction(String script) {
        Matcher matcher = this.getMatcher(script);
        if (matcher.find()) {
            String string = new StringBuilder().insert(0, "GUI$").append(this.name).append("@").append(matcher.group(1)).toString();
            return () -> Class482.INSTANCE.sendPacket(new Class429(string, matcher.group(2)));
        }
        return null;
    }

    private Matcher getMatcher(String script) {
        String regex = "GuiScreen\\.post\\('([^']+)',\\s*(\\{[^}]+\\})\\);";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(script);
    }

    private void putText(JsonArray texts) {
    }

    @Override
    public void readPacketData(PacketBuffer packetBuffer) {
        this.type = packetBuffer.readStringFromBuffer(Short.MAX_VALUE);
        this.name = packetBuffer.readStringFromBuffer(Short.MAX_VALUE);
        this.data = packetBuffer.readStringFromBuffer(9999999);
    }

    @Override
    public int getPacketId() {
        return 73;
    }
}

