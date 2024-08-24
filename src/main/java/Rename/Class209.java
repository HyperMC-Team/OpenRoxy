package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class Class209 {
    public static List<Class209> animatedEntities = new ArrayList<Class209>();
    public static Map<String, Class201> skinMap = Maps.newHashMap();
    public static Class201 playerRenderer;
    public String id;
    public String displayName;
    public Entity entity;
    public Class<? extends Entity> entityClass;
    public Render renderer;
    public List<Class192> animations = new ArrayList<Class192>();

    public Class209(String argID, String argDisplayName, Entity argEntity, Class<? extends Entity> argClass, Render argRenderer) {
        this.id = argID;
        this.displayName = argDisplayName;
        this.entityClass = argClass;
        this.renderer = argRenderer;
        this.entity = argEntity;
    }

    public Class209 add(Class192 argGroup) {
        this.animations.add(argGroup);
        return this;
    }

    public static void register() {
        animatedEntities.clear();
        Class209.registerEntity(new Class209("player", "Player", Minecraft.getMinecraft().thePlayer, EntityPlayer.class, new Class201(Minecraft.getMinecraft().getRenderManager())).add(new Class189()).add(new Class191()).add(new Class187()).add(new Class188()).add(new Class184()).add(new Class175()).add(new Class190()).add(new Class183()).add(new Class186()).add(new Class185()).add(new Class182()));
        playerRenderer = new Class201(Minecraft.getMinecraft().getRenderManager());
        skinMap.put("default", playerRenderer);
        skinMap.put("slim", new Class201(Minecraft.getMinecraft().getRenderManager(), true));
    }

    public static void registerEntity(Class209 argEntity) {
        animatedEntities.add(argEntity);
    }

    public Class192 get(String argName) {
        for (Class192 animation : this.animations) {
            if (!animation.getName().equalsIgnoreCase(argName)) continue;
            return animation;
        }
        return null;
    }

    public static Class209 getByEntity(Entity argEntity) {
        for (Class209 animatedEntity : animatedEntities) {
            if (!animatedEntity.entityClass.isInstance(argEntity)) continue;
            return animatedEntity;
        }
        return null;
    }

    public static Class201 getPlayerRenderer(AbstractClientPlayer player) {
        String s = player.getSkinType();
        Class201 renderPlayer = skinMap.get(s);
        return renderPlayer != null ? renderPlayer : playerRenderer;
    }
}

