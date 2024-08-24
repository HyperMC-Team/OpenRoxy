package Rename;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import net.minecraft.network.PacketBuffer;

public class Class434
implements Class441 {
    private String name;
    private int i;
    private String data;

    @Override
    public void process() {
        String prefix = this.data.split("<->")[0];
        if (prefix.equals("updateOption")) {
            String[] part = this.data.split("<->")[1].split("@");
            String type = part[1];
            String json = part[2];
            if (type.equals("variables")) {
                JsonObject object = Class478.fromJson(json);
                String subject = object.get("subject").getAsString();
                for (Class446 element : Class445.INSTANCE.getElements()) {
                    if (!element.getName().replace("fight_team", "team_fight").split("subject_")[1].equalsIgnoreCase(subject)) continue;
                    Class445.INSTANCE.setCurrentElement(element);
                    Class445.INSTANCE.getSwitchScreenAnim().setState(true);
                }
            } else if (type.equals("data")) {
                if (!Class445.INSTANCE.getCurrentElement().getSubElements().isEmpty()) {
                    return;
                }
                JsonObject object = Class478.fromJson(json);
                int index = 0;
                for (JsonElement element : object.getAsJsonArray("subs")) {
                    if (!element.isJsonObject()) continue;
                    JsonObject gameObj = element.getAsJsonObject();
                    String sid = gameObj.get("sid").getAsString();
                    String name = gameObj.get("name").getAsString().replace("&", "\u00a7");
                    List<String> desc = Arrays.stream(gameObj.get("desc").getAsString().replace("&", "\u00a7").replace("\u2726", "\u2606").split("\n")).collect(Collectors.toList());
                    Class454 gameSubElement = new Class454(index, sid, name, desc);
                    Class445.INSTANCE.getCurrentElement().getSubElements().add(gameSubElement);
                    ++index;
                }
            }
        } else if (prefix.equals("openChild")) {
            String[] part = this.data.split("<->")[1].split("_");
            if (part[0].equals("team")) {
                Class443.SubType type = Class443.SubType.valueOf(part[1].toUpperCase());
                Class427 packet = new Class427();
                packet.setMessage(this.name);
                packet.setMessage3(this.data.split("<->")[1]);
                packet.setN2(1);
                Class482.INSTANCE.sendPacket(packet);
                type.setCurrent();
            }
        } else if (prefix.equals("invalid")) {
            this.name = this.data.split("<->")[1].split("@")[0];
        }
    }

    @Override
    public void readPacketData(PacketBuffer packetBuffer) {
        this.name = packetBuffer.readStringFromBuffer(Short.MAX_VALUE);
        this.i = packetBuffer.readInt();
        this.data = packetBuffer.readStringFromBuffer(3276700);
    }

    @Override
    public int getPacketId() {
        return 79;
    }
}

