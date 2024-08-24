package net.minecraftforge.client.model;

import com.google.common.base.Optional;
import net.minecraftforge.client.model.IModelPart;
import net.minecraftforge.client.model.TRSRTransformation;

public interface IModelState {
    public Optional<TRSRTransformation> apply(Optional<? extends IModelPart> var1);
}

