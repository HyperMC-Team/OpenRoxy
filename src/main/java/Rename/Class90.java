package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.io.Closeable;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Session;
import org.apache.commons.io.IOUtils;

public class Class90 {
    private volatile Class89 microsoftLogin;
    public boolean logging = false;

    public void start(final Class91 manager) {
        this.logging = true;
        Thread thread = new Thread("Class89 Thread"){

            @Override
            public void run() {
                try {
                    Class90.this.microsoftLogin = new Class89(manager);
                    while (Class90.this.logging) {
                        if (!Class90.this.microsoftLogin.isLogged()) continue;
                        IOUtils.closeQuietly(Class90.this.microsoftLogin);
                        Class90.this.logging = false;
                        Minecraft.getMinecraft().session = new Session(Class90.this.microsoftLogin.getUserName(), Class90.this.microsoftLogin.getUuid(), Class90.this.microsoftLogin.getAccessToken(), "legacy");
                        break;
                    }
                }
                catch (Throwable e) {
                    Class90.this.logging = false;
                    e.printStackTrace();
                    IOUtils.closeQuietly(Class90.this.microsoftLogin);
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
    }

    public void stop() {
        if (this.microsoftLogin != null && this.logging) {
            this.microsoftLogin.close();
            this.logging = false;
        }
    }
}

