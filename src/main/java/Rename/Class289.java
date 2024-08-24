package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import net.minecraft.item.ItemSword;
import net.minecraft.network.Packet;
import tech.skidonion.obfuscator.annotations.Renamer;
import tech.skidonion.obfuscator.annotations.StringEncryption;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
@Renamer
@StringEncryption
public class Class289
extends Class252 {
    private final Class254 c07 = new Class254("C07", true);
    private final Class254 c0e = new Class254("All C0E", true);
    private final Class254 c0f = new Class254("All C0F", true);
    private final Class254 c08 = new Class254("C08 only sword", true);

    public Class289() {
        super("Class289", Class263.Misc);
    }

    @Class14
    public void onPacketSend(Class35 event) {
        Packet<?> packet = event.getPacket();
        if (packet instanceof Class365 && ((Class365)packet).getStatus() == Class365.Action.RELEASE_USE_ITEM && this.c07.isEnabled()) {
            Class162.addChatMessage("C07");
        }
        if (packet instanceof Class355 && Class289.mc.thePlayer.getHeldItem().getItem() instanceof ItemSword && this.c08.isEnabled()) {
            Class162.addChatMessage("C08");
        }
        if (packet instanceof Class360 && this.c0e.isEnabled()) {
            Class162.addChatMessage("C0E");
        }
        if (packet instanceof Class353 && this.c0f.isEnabled()) {
            Class162.addChatMessage("C0F");
        }
    }
}

