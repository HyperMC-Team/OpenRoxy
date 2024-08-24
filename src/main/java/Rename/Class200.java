package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import Rename.Class194;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;

public class Class200
extends LayerArmorBase<ModelBiped> {
    public Class200(RendererLivingEntity<?> rendererIn) {
        super(rendererIn);
    }

    @Override
    protected void initArmor() {
        this.modelLeggings = new Class194(0.5f);
        this.modelArmor = new Class194(1.0f);
    }

    @Override
    protected void setModelPartVisible(ModelBiped p_177179_1_, int p_177179_2_) {
        this.func_177194_a(p_177179_1_);
        switch (p_177179_2_) {
            case 1: {
                p_177179_1_.bipedRightLeg.showModel = true;
                p_177179_1_.bipedLeftLeg.showModel = true;
                break;
            }
            case 2: {
                p_177179_1_.bipedBody.showModel = true;
                p_177179_1_.bipedRightLeg.showModel = true;
                p_177179_1_.bipedLeftLeg.showModel = true;
                break;
            }
            case 3: {
                p_177179_1_.bipedBody.showModel = true;
                p_177179_1_.bipedRightArm.showModel = true;
                p_177179_1_.bipedLeftArm.showModel = true;
                break;
            }
            case 4: {
                p_177179_1_.bipedHead.showModel = true;
                p_177179_1_.bipedHeadwear.showModel = true;
            }
        }
    }

    protected void func_177194_a(ModelBiped p_177194_1_) {
        p_177194_1_.setInvisible(false);
    }
}

