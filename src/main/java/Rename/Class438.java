package Rename;

import com.google.gson.JsonObject;
import net.minecraft.network.PacketBuffer;

public class Class438
implements Class441 {
    private String data;

    @Override
    public void process() {
        JsonObject object = Class478.fromJson(this.data);
        if (object.get("hudMsgType").getAsString().equals("CENTER_UP_SCROLL")) {
            int priority = object.get("priority").getAsInt();
            String string = object.get("contents").getAsString();
        }
    }

    @Override
    public void readPacketData(PacketBuffer packetBuffer) {
        this.data = packetBuffer.readStringFromBuffer(Short.MAX_VALUE);
    }

    @Override
    public int getPacketId() {
        return 2141;
    }

    public String getData() {
        return this.data;
    }
}

