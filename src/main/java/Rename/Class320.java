package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.ReadableVector3f;
import org.lwjgl.util.vector.Vector3f;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
public class Class320
extends Class252 {
    public static final ResourceLocation texture_NULL = new ResourceLocation("mobends/textures/white.png");
    public final Class254 swordTrail = new Class254("Sword Trail", true);
    public final Class254 spinAttack = new Class254("Spin attack", false);
    private boolean register = false;
    public float ticks = 0.0f;
    public float ticksPerFrame = 0.0f;

    public Class320() {
        super("MoreBends", Class263.Render);
    }

    @Override
    public void onEnable() {
        if (!this.register) {
            Class209.register();
            this.register = true;
        }
    }

    @Class14
    public void onRender3D(Class39 event) {
        if (!this.isState()) {
            return;
        }
        if (Class320.mc.theWorld == null) {
            return;
        }
        for (int i = 0; i < Class203.dataList.size(); ++i) {
            Class203.dataList.get(i).update(event.getPartialTicks());
        }
        if (Class320.mc.thePlayer != null) {
            float newTicks = (float) Class320.mc.thePlayer.ticksExisted + event.getPartialTicks();
            if (!Class320.mc.theWorld.isRemote || !mc.isGamePaused()) {
                this.ticksPerFrame = Math.min(Math.max(0.0f, newTicks - this.ticks), 1.0f);
                this.ticks = newTicks;
            } else {
                this.ticksPerFrame = 0.0f;
            }
        }
    }

    @Class14
    public void onTick(Class52 event) {
        if (!this.isState()) {
            return;
        }
        if (Class320.mc.theWorld == null) {
            return;
        }
        for (int i = 0; i < Class203.dataList.size(); ++i) {
            Class203 data = Class203.dataList.get(i);
            Entity entity = Class320.mc.theWorld.getEntityByID(data.entityID);
            if (entity != null) {
                if (!data.entityType.equalsIgnoreCase(entity.getName())) {
                    Class203.dataList.remove(data);
                    Class203.add(new Class203(entity.getEntityId()));
                    continue;
                }
                data.motion_prev.set(data.motion);
                data.motion.x = (float)entity.posX - data.position.x;
                data.motion.y = (float)entity.posY - data.position.y;
                data.motion.z = (float)entity.posZ - data.position.z;
                data.position = new Vector3f((float)entity.posX, (float)entity.posY, (float)entity.posZ);
                continue;
            }
            Class203.dataList.remove(data);
        }
    }

    public boolean onRenderLivingEvent(RendererLivingEntity renderer, EntityLivingBase entity, double x, double y, double z, float entityYaw, float partialTicks) {
        if (!this.isState() || renderer instanceof Class201) {
            return false;
        }
        Class209 animatedEntity = Class209.getByEntity(entity);
        if (animatedEntity != null && entity instanceof EntityPlayer) {
            AbstractClientPlayer player = (AbstractClientPlayer)entity;
            if (Class262.getModule(Class315.class).isState()) {
                GL11.glEnable(32823);
                GL11.glPolygonOffset(1.0f, -1100000.0f);
            }
            Class209.getPlayerRenderer(player).doRender(player, x, y, z, entityYaw, partialTicks);
            if (Class262.getModule(Class315.class).isState()) {
                GL11.glDisable(32823);
                GL11.glPolygonOffset(1.0f, 1100000.0f);
            }
            return true;
        }
        return false;
    }
}

