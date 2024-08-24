package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.awt.Color;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector3f;
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
public class Class321
extends Class252 {
    private final Map<Entity, Class251> entityPosition = new HashMap<Entity, Class251>();
    private final Frustum frustum = new Frustum();
    private final FloatBuffer windPos = BufferUtils.createFloatBuffer(4);
    private final IntBuffer intBuffer = GLAllocation.createDirectIntBuffer(16);
    private final FloatBuffer floatBuffer1 = GLAllocation.createDirectFloatBuffer(16);
    private final FloatBuffer floatBuffer2 = GLAllocation.createDirectFloatBuffer(16);
    Map<String, String> playerNamePrefixCache = new HashMap<String, String>();

    public Class321() {
        super("Class321", Class263.Render);
    }

    @Class14
    private void onRender3D(Class39 event) {
        this.entityPosition.clear();
        Class321.mc.theWorld.getLoadedEntityList().stream().filter(entity -> entity instanceof EntityPlayer).filter(this::isInView).forEach(entity -> this.entityPosition.put(entity, this.getEntityPositionsOn2D(entity)));
    }

    @Class14
    private void onRender(Class38 event) {
        for (Entity entity : this.entityPosition.keySet()) {
            EntityLivingBase renderingEntity = (EntityLivingBase)entity;
            String WarnRank = "";
            if (Class262.getModule(Class290.class).isState() && Class290.isSameTeam(renderingEntity)) {
                WarnRank = "\u00a7a[Team] ";
            } else if (Class220.hyt.isStrength((EntityPlayer)renderingEntity) > 0 && renderingEntity != Class321.mc.thePlayer && !Class290.isSameTeam(renderingEntity)) {
                WarnRank = "\u00a74[Strength] ";
            } else if (Class220.hyt.isRegen((EntityPlayer)renderingEntity) > 0 && renderingEntity != Class321.mc.thePlayer && !Class290.isSameTeam(renderingEntity)) {
                WarnRank = "\u00a74[Regen] ";
            } else if (Class220.hyt.isHoldingGodAxe((EntityPlayer)renderingEntity) && renderingEntity != Class321.mc.thePlayer && !Class290.isSameTeam(renderingEntity)) {
                WarnRank = "\u00a74[GodAxe] ";
            } else if (Class220.hyt.isKBBall(renderingEntity.getHeldItem()) && renderingEntity != Class321.mc.thePlayer && !Class290.isSameTeam(renderingEntity)) {
                WarnRank = "\u00a74[KBBall] ";
            } else if (Class220.hyt.hasEatenGoldenApple((EntityPlayer)renderingEntity) > 0 && renderingEntity != Class321.mc.thePlayer && !Class290.isSameTeam(renderingEntity)) {
                WarnRank = "\u00a74[GApple] ";
            } else if (Class56.isIRCFriend(renderingEntity)) {
                WarnRank = "\u00a7a[Friend] ";
            }
            if (Class321.mc.gameSettings.thirdPersonView == 0 && entity == Class321.mc.thePlayer) continue;
            Class251 pos = this.entityPosition.get(entity);
            float x = pos.getX();
            float y = pos.getY();
            float right = pos.getZ();
            EntityPlayer player = (EntityPlayer)entity;
            if (player.getName().isEmpty()) continue;
            String playerName = player.getName();
            int health = (int)player.getHealth();
            String prefix = this.playerNamePrefixCache.getOrDefault(playerName, "");
            float healthValue = player.getHealth() / player.getMaxHealth();
            Color healthColor = (double)healthValue > 0.75 ? new Color(66, 246, 123) : ((double)healthValue > 0.5 ? new Color(228, 255, 105) : ((double)healthValue > 0.35 ? new Color(236, 100, 64) : new Color(255, 65, 68)));
            String name = WarnRank + "\u00a7f" + prefix + player.getDisplayName().getFormattedText();
            StringBuilder text = new StringBuilder("\u00a7f" + name);
            text.append(String.format(" \u00a77[\u00a7r%sHP\u00a77]", health));
            double fontScale = 1.0;
            float middle = x + (right - x) / 2.0f;
            double fontHeight = (double) Class330.arial14.getHeight() * fontScale;
            float textWidth = Class330.arial14.getStringWidth(text.toString());
            GL11.glPushMatrix();
            GL11.glTranslated(middle -= (float)((double)textWidth * fontScale / 2.0), (double)y - (fontHeight + 2.0), 0.0);
            GL11.glScaled(fontScale, fontScale, 1.0);
            GL11.glTranslated(-middle, -((double)y - (fontHeight + 2.0)), 0.0);
            Class228.drawGlow(middle - 3.0f, (float)((double)y - (fontHeight + 7.0)), textWidth + 6.0f, (float)(fontHeight / fontScale), 12, new Color(10, 10, 10, 120));
            Class468.drawRound(middle - 3.0f, (float)((double)y - (fontHeight + 7.0)), textWidth + 6.0f, (float)(fontHeight / fontScale), 2.0f, new Color(10, 10, 10, 60));
            Class230.drawRect(middle - 3.0f, (float)((double)y - (fontHeight + 5.5)) + 1.0f, 1.5f, (float)(fontHeight / fontScale) - 4.0f, new Color(healthColor.getRGB()));
            Class230.resetColor();
            Class330.arial14.drawStringWithShadow(text.toString(), middle, (float)((double)y - (fontHeight + 4.0)), healthColor.getRGB());
            GL11.glPopMatrix();
        }
    }

    public Vector3f projectOn2D(float x, float y, float z, int scaleFactor) {
        GL11.glGetFloat(2982, this.floatBuffer1);
        GL11.glGetFloat(2983, this.floatBuffer2);
        GL11.glGetInteger(2978, this.intBuffer);
        if (GLU.gluProject(x, y, z, this.floatBuffer1, this.floatBuffer2, this.intBuffer, this.windPos)) {
            return new Vector3f(this.windPos.get(0) / (float)scaleFactor, ((float) Class321.mc.displayHeight - this.windPos.get(1)) / (float)scaleFactor, this.windPos.get(2));
        }
        return null;
    }

    public boolean isInView(Entity ent) {
        this.frustum.setPosition(Class321.mc.getRenderViewEntity().posX, Class321.mc.getRenderViewEntity().posY, Class321.mc.getRenderViewEntity().posZ);
        return this.frustum.isBoundingBoxInFrustum(ent.getEntityBoundingBox()) || ent.ignoreFrustumCheck;
    }

    public double[] getInterpolatedPos(Entity entity) {
        float ticks = Class321.mc.timer.renderPartialTicks;
        return new double[]{Class146.interpolate(entity.lastTickPosX, entity.posX, ticks) - Class321.mc.getRenderManager().viewerPosX, Class146.interpolate(entity.lastTickPosY, entity.posY, ticks) - Class321.mc.getRenderManager().viewerPosY, Class146.interpolate(entity.lastTickPosZ, entity.posZ, ticks) - Class321.mc.getRenderManager().viewerPosZ};
    }

    public AxisAlignedBB getInterpolatedBoundingBox(Entity entity) {
        double[] renderingEntityPos = this.getInterpolatedPos(entity);
        double entityRenderWidth = (double)entity.width / 1.5;
        return new AxisAlignedBB(renderingEntityPos[0] - entityRenderWidth, renderingEntityPos[1], renderingEntityPos[2] - entityRenderWidth, renderingEntityPos[0] + entityRenderWidth, renderingEntityPos[1] + (double)entity.height + (entity.isSneaking() ? -0.3 : 0.18), renderingEntityPos[2] + entityRenderWidth).expand(0.15, 0.15, 0.15);
    }

    public Class251 getEntityPositionsOn2D(Entity entity) {
        AxisAlignedBB bb = this.getInterpolatedBoundingBox(entity);
        List<Vector3f> vectors = Arrays.asList(new Vector3f((float)bb.minX, (float)bb.minY, (float)bb.minZ), new Vector3f((float)bb.minX, (float)bb.maxY, (float)bb.minZ), new Vector3f((float)bb.maxX, (float)bb.minY, (float)bb.minZ), new Vector3f((float)bb.maxX, (float)bb.maxY, (float)bb.minZ), new Vector3f((float)bb.minX, (float)bb.minY, (float)bb.maxZ), new Vector3f((float)bb.minX, (float)bb.maxY, (float)bb.maxZ), new Vector3f((float)bb.maxX, (float)bb.minY, (float)bb.maxZ), new Vector3f((float)bb.maxX, (float)bb.maxY, (float)bb.maxZ));
        Class251 entityPos = new Class251(Float.MAX_VALUE, Float.MAX_VALUE, -1.0f, -1.0f);
        ScaledResolution sr = new ScaledResolution(mc);
        for (Vector3f vector3f : vectors) {
            vector3f = this.projectOn2D(vector3f.x, vector3f.y, vector3f.z, sr.getScaleFactor());
            if (vector3f == null || !((double)vector3f.z >= 0.0) || !((double)vector3f.z < 1.0)) continue;
            entityPos.x = Math.min(vector3f.x, entityPos.x);
            entityPos.y = Math.min(vector3f.y, entityPos.y);
            entityPos.z = Math.max(vector3f.x, entityPos.z);
            entityPos.w = Math.max(vector3f.y, entityPos.w);
        }
        return entityPos;
    }
}

