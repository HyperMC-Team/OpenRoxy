package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.io.IOException;

public class Class66
implements Class78<Class72>,
        Class3 {
    @Override
    public void handle(Class72 packet) {
        if (packet.isSuccess()) {
            try {
                Class66.mc.guiLogin.handleSuccessfulLogin();
            }
            catch (IOException | InterruptedException e) {
                Class66.mc.guiLogin.handleError(e);
            }
        } else {
            try {
                Class66.mc.guiLogin.handleSuccessfulLogin();
            }
            catch (IOException | InterruptedException e) {
                Class66.mc.guiLogin.handleError(e);
            }
        }
    }
}

