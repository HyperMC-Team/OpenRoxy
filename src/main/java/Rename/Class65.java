package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

public class Class65
implements Class78<Class71> {
    @Override
    public void handle(Class71 packet) {
        if (packet.getStatus() == 1) {
            Class83.success("Handshake successful: " + packet.getMessage());
        } else {
            Class83.error("Handshake failed: " + packet.getMessage());
        }
    }
}

