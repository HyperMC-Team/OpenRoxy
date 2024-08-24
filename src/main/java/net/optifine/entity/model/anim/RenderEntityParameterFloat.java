package net.optifine.entity.model.anim;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.EntityLivingBase;
import net.optifine.expr.ExpressionType;
import net.optifine.expr.IExpressionFloat;

public enum RenderEntityParameterFloat implements IExpressionFloat
{
    LIMB_SWING("limb_swing"),
    LIMB_SWING_SPEED("limb_speed"),
    AGE("age"),
    HEAD_YAW("head_yaw"),
    HEAD_PITCH("head_pitch"),
    SCALE("scale"),
    HEALTH("health"),
    HURT_TIME("hurt_time"),
    IDLE_TIME("idle_time"),
    MAX_HEALTH("max_health"),
    MOVE_FORWARD("move_forward"),
    MOVE_STRAFING("move_strafing"),
    PARTIAL_TICKS("partial_ticks"),
    POS_X("pos_x"),
    POS_Y("pos_y"),
    POS_Z("pos_z"),
    REVENGE_TIME("revenge_time"),
    SWING_PROGRESS("swing_progress");

    private String name;
    private RenderManager renderManager;
    private static final RenderEntityParameterFloat[] VALUES;

    private RenderEntityParameterFloat(String name) {
        this.name = name;
        this.renderManager = Minecraft.getMinecraft().getRenderManager();
    }

    public String getName() {
        return this.name;
    }

    @Override
    public ExpressionType getExpressionType() {
        return ExpressionType.FLOAT;
    }

    @Override
    public float eval() {
        Render render = this.renderManager.renderRender;
        if (render == null) {
            return 0.0f;
        }
        if (render instanceof RendererLivingEntity) {
            RendererLivingEntity rendererlivingentity = (RendererLivingEntity)render;
            switch (this.ordinal()) {
                case 0: {
                    return rendererlivingentity.renderLimbSwing;
                }
                case 1: {
                    return rendererlivingentity.renderLimbSwingAmount;
                }
                case 2: {
                    return rendererlivingentity.renderAgeInTicks;
                }
                case 3: {
                    return rendererlivingentity.renderHeadYaw;
                }
                case 4: {
                    return rendererlivingentity.renderHeadPitch;
                }
                case 5: {
                    return rendererlivingentity.renderScaleFactor;
                }
            }
            EntityLivingBase entitylivingbase = rendererlivingentity.renderEntity;
            if (entitylivingbase == null) {
                return 0.0f;
            }
            switch (this.ordinal()) {
                case 6: {
                    return entitylivingbase.getHealth();
                }
                case 7: {
                    return entitylivingbase.hurtTime;
                }
                case 8: {
                    return entitylivingbase.getAge();
                }
                case 9: {
                    return entitylivingbase.getMaxHealth();
                }
                case 10: {
                    return entitylivingbase.moveForward;
                }
                case 11: {
                    return entitylivingbase.moveStrafing;
                }
                case 13: {
                    return (float)entitylivingbase.posX;
                }
                case 14: {
                    return (float)entitylivingbase.posY;
                }
                case 15: {
                    return (float)entitylivingbase.posZ;
                }
                case 16: {
                    return entitylivingbase.getRevengeTimer();
                }
                case 17: {
                    return entitylivingbase.getSwingProgress(rendererlivingentity.renderPartialTicks);
                }
            }
        }
        return 0.0f;
    }

    public static RenderEntityParameterFloat parse(String str) {
        if (str == null) {
            return null;
        }
        for (int i = 0; i < VALUES.length; ++i) {
            RenderEntityParameterFloat renderentityparameterfloat = VALUES[i];
            if (!renderentityparameterfloat.getName().equals(str)) continue;
            return renderentityparameterfloat;
        }
        return null;
    }

    static {
        VALUES = RenderEntityParameterFloat.values();
    }
}

