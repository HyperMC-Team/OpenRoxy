package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import Rename.Class14;
import Rename.Class27;
import Rename.Class252;
import Rename.Class263;
import Rename.Class254;
import Rename.Class257;
import Rename.Class259;
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
public class Class306
extends Class252 {
    public final Class257 mode = new Class257("mode", "1.7", "Sigma", "1.7", "Jello", "External", "Styles", "Leaked", "Exhibition", "Exhibition2");
    public final Class259 speed = new Class259("SwingSpeed", 1.0, 1.5, 0.1, 0.1);
    public final Class259 x = new Class259("X", 0.0, 1.0, -1.0, 0.05);
    public final Class259 y = new Class259("Y", 0.0, 1.0, -1.0, 0.05);
    public final Class259 z = new Class259("Z", 0.0, 1.0, -1.0, 0.05);
    public final Class259 blockingX = new Class259("Blocking-X", 0.0, 1.0, -1.0, 0.05);
    public final Class259 blockingY = new Class259("Blocking-Y", 0.0, 1.0, -1.0, 0.05);
    public final Class259 blockingZ = new Class259("Blocking-Z", 0.0, 1.0, -1.0, 0.05);
    public final Class254 smooth = new Class254("Smooth", false);
    public final Class254 fake = new Class254("FakeBlock", false);

    public Class306() {
        super("Class306", Class263.Render);
    }

    @Class14
    private void onUpdate(Class27 event) {
        this.setSuffix(this.mode.getMode());
    }
}

