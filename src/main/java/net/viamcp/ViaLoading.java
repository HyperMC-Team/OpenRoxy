package net.viamcp;

import Rename.Class162;
import net.viamcp.viamcp.ViaMCP;
import tech.skidonion.obfuscator.annotations.Renamer;
import tech.skidonion.obfuscator.annotations.StringEncryption;

@Renamer
@StringEncryption
public class ViaLoading {
    public static void load() {
        try {
            ViaMCP.create();
            ViaMCP.INSTANCE.initAsyncSlider();
        }
        catch (Exception e) {
            Class162.print(e.getMessage());
        }
    }
}

