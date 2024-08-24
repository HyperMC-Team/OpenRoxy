package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.nio.ByteBuffer;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import tech.skidonion.obfuscator.annotations.Renamer;
import tech.skidonion.obfuscator.annotations.StringEncryption;

@Renamer
@StringEncryption
public class Class129 {
    private int imageWidth;
    private int imageHeight;
    private int internalformat;
    private ByteBuffer imageBuffer;

    public void setBuffer(ByteBuffer buffer, int width, int height) {
        this.internalformat = 6407;
        this.imageWidth = width;
        this.imageHeight = height;
        this.imageBuffer = buffer;
    }

    public void onDrawFrame() {
        GL13.glActiveTexture(33984);
        GL11.glBindTexture(3553, -1);
        GL11.glTexParameteri(3553, 10241, 9728);
        GL11.glTexParameteri(3553, 10240, 9728);
        GL11.glTexImage2D(3553, 0, this.internalformat, this.imageWidth, this.imageHeight, 0, this.internalformat, 5121, this.imageBuffer);
    }

    public ByteBuffer getImageBuffer() {
        return this.imageBuffer;
    }
}

