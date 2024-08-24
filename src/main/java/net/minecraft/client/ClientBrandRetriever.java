package net.minecraft.client;

import Rename.Class269;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import Rename.Class262;

public class ClientBrandRetriever {
    public static String getClientModName() {
        Class269 fakeForge = Class262.getModule(Class269.class);
        return fakeForge.isState() ? ClientBrandRetriever.getModName() : "vanilla";
    }

    private static String getModName() {
        ArrayList modNames = Lists.newArrayListWithExpectedSize((int)3);
        modNames.add("fml");
        modNames.add("forge");
        return Joiner.on((char)',').join((Iterable)modNames);
    }
}

