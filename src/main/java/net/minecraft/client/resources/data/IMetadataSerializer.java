package net.minecraft.client.resources.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.TypeAdapterFactory;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.IMetadataSectionSerializer;
import net.minecraft.util.ChatStyle;
import net.minecraft.util.EnumTypeAdapterFactory;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IRegistry;
import net.minecraft.util.RegistrySimple;

public class IMetadataSerializer {
    private final IRegistry<String, Registration<? extends IMetadataSection>> metadataSectionSerializerRegistry = new RegistrySimple<String, Registration<? extends IMetadataSection>>();
    private final GsonBuilder gsonBuilder = new GsonBuilder();
    private Gson gson;

    public IMetadataSerializer() {
        this.gsonBuilder.registerTypeHierarchyAdapter(IChatComponent.class, (Object)new IChatComponent.Serializer());
        this.gsonBuilder.registerTypeHierarchyAdapter(ChatStyle.class, (Object)new ChatStyle.Serializer());
        this.gsonBuilder.registerTypeAdapterFactory((TypeAdapterFactory)new EnumTypeAdapterFactory());
    }

    public <T extends IMetadataSection> void registerMetadataSectionType(IMetadataSectionSerializer<T> metadataSectionSerializer, Class<T> clazz) {
        this.metadataSectionSerializerRegistry.putObject(metadataSectionSerializer.getSectionName(), new Registration<T>(this, metadataSectionSerializer, clazz));
        this.gsonBuilder.registerTypeAdapter(clazz, metadataSectionSerializer);
        this.gson = null;
    }

    public <T extends IMetadataSection> T parseMetadataSection(String sectionName, JsonObject json) {
        if (sectionName == null) {
            throw new IllegalArgumentException("Metadata section name cannot be null");
        }
        if (!json.has(sectionName)) {
            return (T)((IMetadataSection)null);
        }
        if (!json.get(sectionName).isJsonObject()) {
            throw new IllegalArgumentException("Invalid metadata for '" + sectionName + "' - expected object, found " + String.valueOf(json.get(sectionName)));
        }
        Registration<? extends IMetadataSection> registration = this.metadataSectionSerializerRegistry.getObject(sectionName);
        if (registration == null) {
            throw new IllegalArgumentException("Don't know how to handle metadata section '" + sectionName + "'");
        }
        return (T)((IMetadataSection)this.getGson().fromJson((JsonElement)json.getAsJsonObject(sectionName), registration.clazz));
    }

    private Gson getGson() {
        if (this.gson == null) {
            this.gson = this.gsonBuilder.create();
        }
        return this.gson;
    }

    class Registration<T extends IMetadataSection> {
        final IMetadataSectionSerializer<T> section;
        final Class<T> clazz;

        private Registration(IMetadataSerializer this$0, IMetadataSectionSerializer<T> metadataSectionSerializer, Class<T> clazzToRegister) {
            this.section = metadataSectionSerializer;
            this.clazz = clazzToRegister;
        }
    }
}

