package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import tech.skidonion.obfuscator.annotations.Renamer;
import tech.skidonion.obfuscator.annotations.StringEncryption;

@Renamer
@StringEncryption
public class Class266
extends Class252 {
    private EntityLivingBase target;
    private static int kills;
    Class241 time;
    public static String[] hytText;

    public Class266() {
        super("Class266", Class263.Misc);
    }

    @Class14
    public void onWorldLoad(Class55 event) {
        kills = 0;
    }

    @Class14
    public void onMotion(Class27 event) {
        this.setSuffix(kills);
    }

    @Class14
    public void onUpdate(Class50 event) {
        if (Class262.getModule(Class266.class).isState() && this.time.hasTimeElapsed(100L)) {
            Class266.mc.thePlayer.sendChatMessage(Class266.getRandomText());
            ++kills;
            this.time.reset();
        }
    }

    public static String getRandomText() {
        Random random = new Random();
        int index = random.nextInt(hytText.length);
        return hytText[index];
    }

    static {
        hytText = new String[]{"\u5176\u5b9ejnic\u662f\u8bb8\u9526\u826f\u5f00\u53d1\u7684 \u4ed6\u4e00\u624b\u521b\u5efa\u4e86\u4e2d\u56fdjnic", "Get best JNIObfuscator >> jnic.xinxin.cfd || \u559c\u6b22\u8bb8\u9526\u826f\u7684\u6765\u652f\u6301\u4e00\u4e0b\u6211\u7684JNIC\u54e6 \u7edd\u5bf9\u79d2\u6740\u5e7b\u5f71\u76fe", "\u4f60\u73b0\u5728\u6709\u6ca1\u6709\u5165\u6743\u4e86?", "SilenceFix is God in QuickMacro", "SilenceFix\u82b1\u96e8\u5ead\u7b2c\u4e00 \u83b7\u53d6\u53bbxinxin.fan", "\u662f\u6f0f\u9632\u54e5\u5417?", "\u548b\u4e86\u5144\u5f1f,\u8981\u6765\u5077\u5200?", "\u4f60\u662f\u51af\u51af\u6b7b\u4e86\u5417\uff1f", "\u6211\u53ef\u4e0d\u662f\u7a7f\u7532\u6b23\u6b23", "\u6211\u7b97\u4ec0\u4e48\u803b\u8fb1\uff0c\u6b23\u6b23\u4e5f\u4e0d\u662f\u5417", "\u5feb\u7ed9idan\u52a0\u4e91\u9ed1,idan\u8bc8\u9a97.", "SilenceFix\u7ecf\u8fc7\u82b1\u96e8\u5ead\u6700\u5f3a\u68c0\u6d4b,\u66fe\u7ecf\u9760\u81ea\u5df1\u628anaven\u6253\u9000\u7f51,\u628axylitol\u6253\u9000\u7f51\u68c0\u6d4b\u51faWNF\u5b58\u5728\u540e\u95e8", "\u4f60\u90a3\u4e48\u8214\u6b23\u6b23\u5e72\u561b\uff1f", "\u5b66\u751f\u515a\uff0c\u5185\u90e8\u662f60\u5143\u54c8 \u4f60\u7ed9\u4e86\u6b23\u6b23\u54e540\u5143 \u8bf7\u5728\u652f\u4ed8\u621120\u5143\u552e\u540e\u8d39\u54c8 \u56e0\u4e3a\u6b23\u6b23\u54e5\u662f\u66f4\u65b0\u53c2\u6570\u7684 \u6211\u5462\u662f\u6765\u641e\u552e\u540e\u6ef4 \u53ea\u8981\u6211\u5728\u7ebf \u552e\u540e\u4f1a\u7279\u522b\u7684\u597d\u5462 \u8bf7\u4f60\u653e\u5fc3 \u4e00\u5206\u4ef7\u94b1\u4e00\u5206\u8d27 \u8d2d\u4e70\u4e86\u6211\u4eec\u7684\u914d\u7f6e \u6211\u4eec\u662f\u7edd\u5bf9\u4e0d\u4f1a\u8ba9\u4f60\u5403\u4e8f\u7684\u5462\u54c8\u5b66\u751f\u515a\uff01", "SilenceFix-9.95 \u83b7\u53d6\u6700\u5f3a\u5de5\u827a\u914d\u7f6e\u5c31\u6765xinxin\u3002cfd\u54e6", "\u8001\u5f1f\u6562\u4e0d\u6562\u8ddf\u6211\u7684\u6700\u5f3a\u6b23\u6b23\u914d\u7f6e\u5bf9\u5200\u4e00\u4e0b\u5462?", "\u8bb8\u9526\u826f\u7279\u6b8a\u5192\u6ce1", "\u6700\u5f3a\u5ba2\u6237\u7aefS\ufff1ile\ufff1nc\ufff1eFix-Bet\ufff1a", "\u6211\u5f90\u9526\u826f\u7d27\u6025\u901a\u77e5\uff0c\u8bf7\u5927\u5bb6\u76f8\u4e92\u8f6c\u544a\uff0c\u4e0d\u8981\u5728\u4e0b\u8f7dWNF\u8131\u79bb\u76d2\u5b50\u4e86\uff0c\u5982\u679c\u4f60\u4e0d\u5bb3\u6015\u7535\u8111\u4e00\u7cfb\u5217\u7684\u540e\u679c\uff0c\u90a3\u6089\u542c\u5c0a\u4fbf\u3002", "\u6211\u4eec\u7684\u8bb8\u9526\u826f\u6b63\u5728\u4f7f\u7528\u6f0f\u7535\u9ed1\u89e3\u7ed5ID\u4e0d\u53ef\u5347\u7ea7\u7f8e\u7248128GB\u91d1\u8272\u540e\u76d6\u5168\u788e\u5c4f\u5e55\u8718\u86db\u7f51\u88c2\u7eb9\u53ea\u80fd\u63d21\u5361\u7535\u6c60\u9f13\u530545\u7535\u6c60\u5065\u5eb7\u5e76\u4e14\u4e3b\u677f\u65ad\u6865\u5927\u4fee\u76f8\u673a\u4e0d\u53ef\u7528\u9762\u5bb9\u635f\u574f\u5145\u7535\u53e3\u8fdb\u6c34\u5c4f\u5e55\u6f0f\u6db2\u51fa\u7ebf\u6309\u952e\u5835\u585e\u5587\u53ed\u70b8\u9ea6\u65e0\u9ea6\u514b\u98ce\u542c\u7b52\u635f\u574f\u7684iPhone14ProMax\u3002", "\u7ecf\u8fc7\u82b1\u96e8\u5ead\u6b23\u6b23\u6700\u5f3a\u68c0\u6d4b,\u66fe\u7ecf\u9760\u81ea\u5df1\u628anaven\u6253\u9000\u7f51,\u628axylitol\u6253\u9000\u7f51\u68c0\u6d4b\u51faWNF\u5b58\u5728\u540e\u95e8", "windows\u80fd\u9a97\u4f60\u5417?360\u7684\u90a3\u4e2a\u5927\u5bb6\u90fd\u77e5\u9053\u662f\u5047\u7684\u3002windows\u4e0d\u4f1a\u9a97\u4f60\u7684\u3002"};
    }
}

