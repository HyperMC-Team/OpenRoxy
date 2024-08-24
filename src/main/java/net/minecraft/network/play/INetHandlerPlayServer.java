package net.minecraft.network.play;

import net.minecraft.network.INetHandler;
import Rename.Class359;
import Rename.Class362;
import Rename.Class334;
import Rename.Class354;
import Rename.Class365;
import Rename.Class355;
import Rename.Class333;
import Rename.Class331;
import Rename.Class364;
import Rename.Class332;
import Rename.Class363;
import Rename.Class360;
import Rename.Class353;
import Rename.Class343;
import Rename.Class336;
import Rename.Class357;
import Rename.Class335;
import Rename.Class352;
import Rename.Class356;
import Rename.Class337;
import Rename.Class361;
import Rename.Class358;
import Rename.Class366;

public interface INetHandlerPlayServer
extends INetHandler {
    public void handleAnimation(Class331 var1);

    public void processChatMessage(Class362 var1);

    public void processTabComplete(Class352 var1);

    public void processClientStatus(Class337 var1);

    public void processClientSettings(Class356 var1);

    public void processConfirmTransaction(Class353 var1);

    public void processEnchantItem(Class336 var1);

    public void processClickWindow(Class360 var1);

    public void processCloseWindow(Class363 var1);

    public void processVanilla250Packet(Class361 var1);

    public void processUseEntity(Class334 var1);

    public void processKeepAlive(Class359 var1);

    public void processPlayer(Class354 var1);

    public void processPlayerAbilities(Class335 var1);

    public void processPlayerDigging(Class365 var1);

    public void processEntityAction(Class364 var1);

    public void processInput(Class332 var1);

    public void processHeldItemChange(Class333 var1);

    public void processCreativeInventoryAction(Class343 var1);

    public void processUpdateSign(Class357 var1);

    public void processPlayerBlockPlacement(Class355 var1);

    public void handleSpectate(Class358 var1);

    public void handleResourcePackStatus(Class366 var1);
}

