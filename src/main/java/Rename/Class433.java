package Rename;

import net.minecraft.network.PacketBuffer;

public class Class433
implements Class441,
        Class3 {
    private String name;

    @Override
    public void process() {
        Class482.INSTANCE.sendPacket(new Class427("germ_gui_loading"));
        Class482.INSTANCE.sendPacket(new Class427(this.name));
        switch (this.name) {
            case "mainmenu": {
                Class445.INSTANCE.setGuiName(this.name);
                mc.addScheduledTask(() -> mc.displayGuiScreen(Class445.INSTANCE));
                break;
            }
            case "team_create": {
                Class443.INSTANCE.setCurrentType(Class443.Type.CREATE);
                mc.displayGuiScreen(Class443.INSTANCE);
                break;
            }
            case "team_main": {
                Class443.INSTANCE.setCurrentType(Class443.Type.MAIN);
                mc.displayGuiScreen(Class443.INSTANCE);
            }
        }
    }

    @Override
    public void readPacketData(PacketBuffer packetBuffer) {
        this.name = packetBuffer.readStringFromBuffer(Short.MAX_VALUE);
    }

    @Override
    public int getPacketId() {
        return 76;
    }
}

