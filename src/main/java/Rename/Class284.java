package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.block.BlockGlass;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
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
public class Class284
extends Class252 {
    private final Class257 modes = new Class257("Mode", "HYT", "HYT", "Hypixel");
    private final Class257 mode = new Class257("Mode", "Solo insane", "Solo normal", "Solo insane");
    private final Class259 delays = new Class259("HypDelay", 1500.0, 4000.0, 0.0, 50.0);
    private final Class259 delay = new Class259("HytDelay", 25.0, 30.0, 20.0, 1.0);
    private final Class254 debug = new Class254("Debug", true);
    private final Class241 timers = new Class241();
    private final String winMessage = "You won! Want to play again? Click here!";
    private final String loseMessage = "You died! Want to play again? Click here!";
    private boolean waiting;
    private int timer = 0;
    private boolean game = false;

    public Class284() {
        super("Class284", Class263.Player);
    }

    @Override
    public void onEnable() {
        this.waiting = false;
        this.timers.reset();
        this.game = false;
        this.timer = 0;
    }

    @Override
    public void onDisable() {
        this.game = false;
        this.timer = 0;
    }

    @Class14
    public void onMotion(Class27 event) {
        this.setSuffix(this.modes.getMode());
    }

    @Class14
    public void onUpdate(Class50 event) {
        if (this.modes.is("HYT")) {
            if (Class262.getModule(Class304.class).isState()) {
                for (Map.Entry<BlockPos, Block> block : Class153.searchBlocks(3).entrySet()) {
                    BlockPos blockpos = block.getKey();
                    if (!(block.getValue() instanceof BlockGlass)) continue;
                    mc.getNetHandler().getNetworkManager().sendPacket(new Class365(Class365.Action.ABORT_DESTROY_BLOCK, blockpos, EnumFacing.DOWN));
                    mc.getNetHandler().getNetworkManager().sendPacket(new Class365(Class365.Action.STOP_DESTROY_BLOCK, blockpos, EnumFacing.DOWN));
                    Class284.mc.theWorld.setBlockState(blockpos, Blocks.air.getDefaultState(), 2);
                }
            }
            if ((double)this.timer >= this.delay.getValue()) {
                this.game = false;
                this.timer = 0;
                Class262.getModule(Class304.class).setState(false);
                Class262.getModule(Class304.class).releasemode.setMode("Latency");
            }
            if (this.game) {
                ++this.timer;
            }
            if (this.debug.isEnabled() && this.game) {
                Class162.addChatMessage(String.valueOf(this.timer));
            }
        }
    }

    @Class14
    public void onTick(Class52 event) {
        if (this.modes.is("Hypixel") && this.waiting && (double)this.timers.getTimeElapsed() >= this.delays.getValue()) {
            String command = "";
            switch (this.mode.getMode()) {
                case "Solo normal": {
                    command = "/play solo_normal";
                    break;
                }
                case "Solo insane": {
                    command = "/play solo_insane";
                }
            }
            Class284.mc.thePlayer.sendChatMessage(command);
            this.timers.reset();
            this.waiting = false;
        }
    }

    @Class14
    public void onPacket(Class33 event) {
        Packet<?> packet = event.getPacket();
        if (packet instanceof Class420) {
            String text = ((Class420)packet).getChatComponent().getUnformattedText();
            if (text.contains("You won! Want to play again? Click here!") && text.length() < "You won! Want to play again? Click here!".length() + 3 || text.contains("You died! Want to play again? Click here!") && text.length() < "You died! Want to play again? Click here!".length() + 3) {
                this.waiting = true;
                this.timers.reset();
            }
            if (text.contains("\u5f00\u59cb\u5012\u8ba1\u65f6: 3 \u79d2") && Class262.getModule(Class304.class).releasemode.setMode("Instant")) {
                Class262.getModule(Class304.class).setState(true);
            }
            if (text.contains("\u5f00\u59cb\u5012\u8ba1\u65f6: 1 \u79d2")) {
                this.game = true;
            }
        }
    }
}

