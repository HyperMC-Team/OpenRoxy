package net.minecraft.util;

public class MovementInput {
    public float moveStrafe;
    public float moveForward;
    public boolean jump;
    public boolean sneak;

    public void updatePlayerMoveState() {
    }

    public float getMoveStrafe() {
        return this.moveStrafe;
    }

    public float getMoveForward() {
        return this.moveForward;
    }
}

