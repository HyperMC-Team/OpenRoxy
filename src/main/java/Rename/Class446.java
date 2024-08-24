package Rename;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.io.IOUtils;

public class Class446 {
    private final List<Class454> subElements = new ArrayList<Class454>();
    private final String name;
    private final String defaultPath;
    private final String hoverPath;
    private final List<String> hoverDos;
    private ResourceLocation defaultImage;
    private ResourceLocation hoverImage;
    private Class453.GifImage gifImage;
    private final List<Integer> delayList = new ArrayList<Integer>();
    private final Class475 updateTimer = new Class475();
    private int imageCount;
    private Runnable runnable;
    private final String clickName;

    public Class446(String name, String defaultPath, String hoverPath, List<String> hoverDos, String clickName) {
        this.name = name;
        this.defaultPath = defaultPath;
        this.hoverPath = hoverPath;
        this.hoverDos = hoverDos;
        this.clickName = clickName;
    }

    public synchronized void loadTexture() throws IOException {
        this.defaultImage = TextureUtil.loadTextureFormURL(this.defaultPath);
    }

    public synchronized void loadHoverTexture() {
        try {
            URI url = new URI(this.hoverPath);
            URLConnection urlConnection = url.toURL().openConnection();
            InputStream inputStream = urlConnection.getInputStream();
            this.gifImage = Class453.read(IOUtils.toByteArray(inputStream));
            for (int i = 0; i < this.gifImage.getFrameCount(); ++i) {
                BufferedImage bufferedImage = this.gifImage.getFrame(i);
                ResourceLocation resourceLocation = new ResourceLocation(String.valueOf(this.hoverPath.hashCode() + i));
                Minecraft.getMinecraft().addScheduledTask(() -> Minecraft.getMinecraft().getTextureManager().loadTexture(resourceLocation, new DynamicTexture(bufferedImage)));
                this.delayList.add(this.gifImage.getDelay(i));
            }
            inputStream.close();
        }
        catch (Exception e) {
            Class162.print(e.getMessage());
        }
    }

    public ResourceLocation getCurrentGifImage() {
        if (this.imageCount >= this.gifImage.getFrameCount()) {
            this.imageCount = 0;
        }
        if (!this.delayList.isEmpty() && this.updateTimer.hasTimeElapsed(this.delayList.get(this.imageCount) * 10)) {
            this.hoverImage = new ResourceLocation(String.valueOf(this.hoverPath.hashCode() + this.imageCount));
            this.updateTimer.reset();
            ++this.imageCount;
        }
        return this.hoverImage;
    }

    public void click(String guiName) {
        HashMap<String, Integer> data = new HashMap<String, Integer>();
        data.put("click", 1);
        String json = Class478.toJson(data);
        String message = new StringBuilder().insert(0, "GUI$").append(guiName).append("@").append(this.clickName).toString();
        Class482.INSTANCE.sendPacket(new Class429(message, json));
    }

    public List<Class454> getSubElements() {
        return this.subElements;
    }

    public String getName() {
        return this.name;
    }

    public String getDefaultPath() {
        return this.defaultPath;
    }

    public String getHoverPath() {
        return this.hoverPath;
    }

    public List<String> getHoverDos() {
        return this.hoverDos;
    }

    public ResourceLocation getDefaultImage() {
        return this.defaultImage;
    }

    public ResourceLocation getHoverImage() {
        return this.hoverImage;
    }

    public Class453.GifImage getGifImage() {
        return this.gifImage;
    }

    public List<Integer> getDelayList() {
        return this.delayList;
    }

    public Class475 getUpdateTimer() {
        return this.updateTimer;
    }

    public int getImageCount() {
        return this.imageCount;
    }

    public Runnable getRunnable() {
        return this.runnable;
    }

    public String getClickName() {
        return this.clickName;
    }

    public void setRunnable(Runnable runnable) {
        this.runnable = runnable;
    }
}

