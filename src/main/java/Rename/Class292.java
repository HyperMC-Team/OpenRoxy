package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
public class Class292
extends Class252 {
    private final Class254 display = new Class254("Display Class292", true);
    boolean gappleNoGround = false;

    public Class292() {
        super("Class292", Class263.Combat);
    }

    @Class14
    public void onWorld(Class55 event) {
        Class292.mc.theWorld.skiptick = 0;
    }

    @Class14
    public void onMoveInput(Class30 event) {
        if (Class267.noPost()) {
            this.reset();
            return;
        }
        if (Class291.target == null) {
            return;
        }
        if (this.isGapple() && !Class292.mc.thePlayer.onGround) {
            this.gappleNoGround = true;
        }
        if (!this.isGapple() && this.gappleNoGround && Class292.mc.thePlayer.onGround) {
            this.gappleNoGround = false;
        }
        if (this.cantCrit(Class291.target)) {
            this.reset();
        } else {
            Class291 aura = Class262.getModule(Class291.class);
            if (Class291.target != null) {
                if (!this.isNull() && Class292.mc.thePlayer.motionY < 0.0 && !Class292.mc.thePlayer.onGround && aura.isState() && Class292.mc.thePlayer.getClosestDistanceToEntity(Class291.target) <= 2.0f) {
                    ++Class292.mc.theWorld.skiptick;
                } else if (!this.isNull() && !aura.isState()) {
                    this.reset();
                }
            }
        }
    }

    @Class14
    public void onStrafe(Class32 event) {
        if (Class292.mc.thePlayer.onGround && !Class292.mc.gameSettings.keyBindJump.pressed && Class291.target != null && Class292.mc.thePlayer.getClosestDistanceToEntity(Class291.target) <= 2.0f) {
            Class292.mc.thePlayer.jump();
        }
    }

    @Class14
    public void onCritical(Class44 event) {
        if (event.getEntity() == Class291.target && this.display.isEnabled()) {
            Class162.addChatMessage("Crit");
        }
    }

    public boolean cantCrit(EntityLivingBase targetEntity) {
        EntityPlayerSP player = Class292.mc.thePlayer;
        return player.isOnLadder() || player.isInWeb || player.isInWater() || player.isInLava() || player.ridingEntity != null || targetEntity.hurtTime > 10 || targetEntity.getHealth() <= 0.0f || this.isGapple() || this.gappleNoGround;
    }

    private void reset() {
        Class292.mc.theWorld.skiptick = 0;
    }
}

