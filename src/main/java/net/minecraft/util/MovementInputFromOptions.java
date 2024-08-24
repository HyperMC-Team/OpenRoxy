package net.minecraft.util;

import Rename.Class13;
import Rename.Class30;
import net.minecraft.client.settings.GameSettings;

public class MovementInputFromOptions
extends MovementInput {
    private final GameSettings gameSettings;

    public MovementInputFromOptions(GameSettings gameSettingsIn) {
        this.gameSettings = gameSettingsIn;
    }

    @Override
    public void updatePlayerMoveState() {
        this.moveStrafe = 0.0f;
        this.moveForward = 0.0f;
        if (this.gameSettings.keyBindForward.isKeyDown()) {
            this.moveForward += 1.0f;
        }
        if (this.gameSettings.keyBindBack.isKeyDown()) {
            this.moveForward -= 1.0f;
        }
        if (this.gameSettings.keyBindLeft.isKeyDown()) {
            this.moveStrafe += 1.0f;
        }
        if (this.gameSettings.keyBindRight.isKeyDown()) {
            this.moveStrafe -= 1.0f;
        }
        this.jump = this.gameSettings.keyBindJump.isKeyDown();
        this.sneak = this.gameSettings.keyBindSneak.isKeyDown();
        Class30 moveInputEvent = new Class30(this.moveForward, this.moveStrafe, this.jump, this.sneak, 0.3);
        Class13.Method7(moveInputEvent);
        double sneakMultiplier = moveInputEvent.getSneakSlowDownMultiplier();
        this.moveForward = moveInputEvent.getForward();
        this.moveStrafe = moveInputEvent.getStrafe();
        this.jump = moveInputEvent.isJump();
        this.sneak = moveInputEvent.isSneak();
        if (this.sneak) {
            this.moveStrafe = (float)((double)this.moveStrafe * sneakMultiplier);
            this.moveForward = (float)((double)this.moveForward * sneakMultiplier);
        }
    }
}

