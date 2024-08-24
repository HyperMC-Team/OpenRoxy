package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.io.File;
import java.nio.ByteBuffer;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import tech.skidonion.obfuscator.annotations.Renamer;
import tech.skidonion.obfuscator.annotations.StringEncryption;

@Renamer
@StringEncryption
public class Class130 {
    private static FFmpegFrameGrabber frameGrabber;
    private static Class129 renderer;
    private static double frameRate;
    private static int ticks;
    private static boolean flag;
    private static long time;
    private static Tessellator tessellator;
    private static WorldRenderer worldRenderer;
    public static boolean suspended;
    private static boolean stopped;

    public static void init(File file) {
        Frame frame;
        frameGrabber = new FFmpegFrameGrabber(file.getPath());
        frameGrabber.setPixelFormat(2);
        frameGrabber.setOption("loglevel", "quiet");
        renderer = new Class129();
        tessellator = Tessellator.getInstance();
        worldRenderer = tessellator.getWorldRenderer();
        time = 0L;
        ticks = 0;
        flag = false;
        stopped = false;
        try {
            frameGrabber.start();
        } catch (FFmpegFrameGrabber.Exception e) {
            throw new RuntimeException(e);
        }
        frameRate = frameGrabber.getFrameRate();
        while (true) {
            try {
                if (!((frame = frameGrabber.grab()) != null && frame.image == null)) break;
            } catch (FFmpegFrameGrabber.Exception e) {
                throw new RuntimeException(e);
            }
        }
        if (frame != null) {
            renderer.setBuffer((ByteBuffer)frame.image[0], frame.imageWidth, frame.imageHeight);
            time = System.currentTimeMillis();
            ++ticks;
            Thread thread = Class130.getThread();
            thread.start();
        }
    }

    private static Thread getThread() {
        Thread thread = new Thread("Video Background") {
            public void run() {
                while(true) {
                    try {
                        synchronized(this) {
                            if (!Class130.flag
                                    || (double)(System.currentTimeMillis() - Class130.time) > 700.0 / Class130.frameRate && !Class130.suspended) {
                                Class130.doGetBuffer();
                            }

                            if (Class130.time != 0L) {
                                continue;
                            }
                        }
                    } catch (Exception var4) {
                        var4.printStackTrace();
                        this.interrupt();
                    }

                    return;
                }
            }
        };
        thread.setDaemon(true);
        return thread;
    }


    private static void doGetBuffer() throws FFmpegFrameGrabber.Exception {
        int fLength = frameGrabber.getLengthInFrames() - 5;
        if (ticks < fLength) {
            Frame frame = frameGrabber.grab();
            if (frame != null && frame.image != null) {
                if (renderer != null) {
                    renderer.setBuffer((ByteBuffer)frame.image[0], frame.imageWidth, frame.imageHeight);
                    time = System.currentTimeMillis();
                    ++ticks;
                } else {
                    System.err.println("Class129 Error");
                }
            }
        } else {
            ticks = 0;
            frameGrabber.setFrameNumber(0);
        }
        if (!flag) {
            flag = true;
        }
    }

    public static void render(int width, int height) {
        if (!stopped) {
            suspended = false;
            renderer.onDrawFrame();
            GlStateManager.enableBlend();
            GL11.glDisable(2929);
            GL11.glEnable(3042);
            OpenGlHelper.glBlendFunc(770, 771, 1, 0);
            GlStateManager.enableAlpha();
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            GlStateManager.disableAlpha();
            GL11.glMatrixMode(5889);
            GL11.glPushMatrix();
            GL11.glLoadIdentity();
            GLU.gluOrtho2D(0.0f, (float)width, (float)height, 0.0f);
            GL11.glMatrixMode(5888);
            GL11.glPushMatrix();
            GL11.glLoadIdentity();
            worldRenderer.begin(7, DefaultVertexFormats.POSITION_TEX);
            worldRenderer.pos(0.0, height, 0.0).tex(0.0, 1.0).endVertex();
            worldRenderer.pos(width, height, 0.0).tex(1.0, 1.0).endVertex();
            worldRenderer.pos(width, 0.0, 0.0).tex(1.0, 0.0).endVertex();
            worldRenderer.pos(0.0, 0.0, 0.0).tex(0.0, 0.0).endVertex();
            tessellator.draw();
            GL11.glMatrixMode(5889);
            GL11.glPopMatrix();
            GL11.glMatrixMode(5888);
            GL11.glPopMatrix();
            GL11.glEnable(2929);
            GlStateManager.disableBlend();
            Class230.resetColor();
        }
    }

    public static void stop() {
        if (!stopped) {
            tessellator = null;
            worldRenderer = null;
            renderer = null;
            time = 0L;
            ticks = 0;
            try {
                frameGrabber.stop();
            } catch (FFmpegFrameGrabber.Exception e) {
                throw new RuntimeException(e);
            }
            try {
                frameGrabber.release();
            } catch (FFmpegFrameGrabber.Exception e) {
                throw new RuntimeException(e);
            }
            frameGrabber = null;
            stopped = true;
        }
    }

    static {
        suspended = false;
        stopped = false;
    }
}

