package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.Vec3;
import org.lwjgl.opengl.GL11;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
public class Class307
extends Class252 {
    private final Class257 mod = new Class257("Mode", "FDP", "Novoline", "FDP");
    private final Class259 size = new Class259("Size", 100.0, 500.0, 10.0, 1.0);
    private final List<Vec3> path = new ArrayList<Vec3>();

    public Class307() {
        super("Class307", Class263.Render);
    }

    @Class14
    public void onRender3D(Class39 event) {
        if (this.mod.is("FDP")) {
            if (Class307.mc.thePlayer.lastTickPosX != Class307.mc.thePlayer.posX || Class307.mc.thePlayer.lastTickPosY != Class307.mc.thePlayer.posY || Class307.mc.thePlayer.lastTickPosZ != Class307.mc.thePlayer.posZ) {
                this.path.add(new Vec3(Class307.mc.thePlayer.posX, Class307.mc.thePlayer.posY, Class307.mc.thePlayer.posZ));
            }
            while (this.path.size() > this.size.getValue().intValue()) {
                this.path.remove(0);
            }
            Class147.renderBreadCrumbs(this.path, Class318.getClientColors().getFirst(), Class318.getClientColors().getSecond());
        } else if (this.mod.is("Novoline")) {
            for (EntityPlayer entityPlayer : Class307.mc.theWorld.playerEntities) {
                ArrayList<Point> points = entityPlayer.points;
                boolean render = entityPlayer != Class307.mc.thePlayer || Class307.mc.gameSettings.thirdPersonView != 0;
                points.removeIf(p -> p.age >= (float)this.size.getValue().intValue());
                double x = entityPlayer.lastTickPosX + (entityPlayer.posX - entityPlayer.lastTickPosX) * (double)event.getPartialTicks();
                double y = entityPlayer.lastTickPosY + (entityPlayer.posY - entityPlayer.lastTickPosY) * (double)event.getPartialTicks();
                double z = entityPlayer.lastTickPosZ + (entityPlayer.posZ - entityPlayer.lastTickPosZ) * (double)event.getPartialTicks();
                points.add(new Point(x, y, z));
                if (render) {
                    GL11.glPushMatrix();
                    GL11.glDisable(3008);
                    GL11.glEnable(3042);
                    GL11.glEnable(2848);
                    GL11.glDisable(3553);
                    GL11.glBlendFunc(770, 771);
                    GL11.glDisable(2884);
                }
                int yOffset = 0;
                for (Point t : points) {
                    if (points.indexOf(t) >= points.size() - 1) continue;
                    Point temp = points.get(points.indexOf(t) + 1);
                    float a = 200.0f * ((float)points.indexOf(t) / (float)points.size());
                    if (render) {
                        Color color = Class472.interpolateColorsBackAndForth(5, yOffset, Class318.getClientColors().getFirst(), Class318.getClientColors().getSecond(), false);
                        Color c = Class472.injectAlpha(color, (int)a);
                        GL11.glBegin(8);
                        double d = t.x;
                        mc.getRenderManager();
                        double x2 = d - RenderManager.getRenderPosX();
                        double d2 = t.y;
                        mc.getRenderManager();
                        double y2 = d2 - RenderManager.getRenderPosY();
                        double d3 = t.z;
                        mc.getRenderManager();
                        double z2 = d3 - RenderManager.getRenderPosZ();
                        double d4 = temp.x;
                        mc.getRenderManager();
                        double x1 = d4 - RenderManager.getRenderPosX();
                        double d5 = temp.y;
                        mc.getRenderManager();
                        double y1 = d5 - RenderManager.getRenderPosY();
                        double d6 = temp.z;
                        mc.getRenderManager();
                        double z1 = d6 - RenderManager.getRenderPosZ();
                        Class472.glColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), 0).getRGB());
                        GL11.glVertex3d(x2, y2 + (double)entityPlayer.height - 0.1, z2);
                        Class472.glColor(c.getRGB());
                        GL11.glVertex3d(x2, y2 + 0.2, z2);
                        GL11.glVertex3d(x1, y1 + (double)entityPlayer.height - 0.1, z1);
                        GL11.glVertex3d(x1, y1 + 0.2, z1);
                        GL11.glEnd();
                    }
                    t.age += 1.0f;
                    ++yOffset;
                }
                if (!render) continue;
                GlStateManager.resetColor();
                GL11.glDisable(3042);
                GL11.glEnable(3008);
                GL11.glEnable(3553);
                GL11.glEnable(2884);
                GL11.glDisable(2848);
                GL11.glDisable(3042);
                GL11.glPopMatrix();
            }
        }
    }

    public static class Point {
        public final double x;
        public final double y;
        public final double z;
        public float age = 0.0f;

        public Point(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}

