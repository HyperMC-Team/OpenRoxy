package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.awt.Color;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityEnderChest;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */
public class Class280
extends Class252 {
    private final Class259 red = new Class259("Red", 0.0, 255.0, 0.0, 1.0);
    private final Class259 green = new Class259("Green", 0.0, 255.0, 0.0, 1.0);
    private final Class259 blue = new Class259("Blue", 0.0, 255.0, 0.0, 1.0);
    private final Class254 rainbow = new Class254("Rainbow", false);
    private final Class254 outline = new Class254("Outline", false);
    private final Class254 shade = new Class254("Shade", false);
    private final Class254 disableIfOpened = new Class254("Disable if Opened", false);
    private int rgb = 0;

    public Class280() {
        super("Class280", Class263.Render);
    }

    private int color() {
        this.rgb = new Color((int)this.red.getValue().doubleValue(), (int)this.green.getValue().doubleValue(), (int)this.blue.getValue().doubleValue()).getRGB();
        return this.rgb;
    }

    @Class14
    public void onRender3D(Class39 ev) {
        if (this.isNull()) {
            return;
        }
        int rgb = this.rainbow.isEnabled() ? Class280.getChroma(2L, 0L) : this.color();
        for (TileEntity tileEntity : Class280.mc.theWorld.loadedTileEntityList) {
            if (tileEntity instanceof TileEntityChest) {
                if (this.disableIfOpened.isEnabled() && ((TileEntityChest)tileEntity).lidAngle > 0.0f) continue;
                Class230.renderBlock(tileEntity.getPos(), rgb, this.outline.isEnabled(), this.shade.isEnabled());
                continue;
            }
            if (!(tileEntity instanceof TileEntityEnderChest) || this.disableIfOpened.isEnabled() && ((TileEntityEnderChest)tileEntity).lidAngle > 0.0f) continue;
            Class230.renderBlock(tileEntity.getPos(), rgb, this.outline.isEnabled(), this.shade.isEnabled());
        }
    }

    public static int getChroma(long speed, long ... delay) {
        long time = System.currentTimeMillis() + (delay.length > 0 ? delay[0] : 0L);
        return Color.getHSBColor((float)(time % (15000L / speed)) / (15000.0f / (float)speed), 1.0f, 1.0f).getRGB();
    }
}

