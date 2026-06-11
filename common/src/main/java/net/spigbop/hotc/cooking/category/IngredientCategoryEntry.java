package net.spigbop.hotc.cooking.category;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.ResourceLocation;

public class IngredientCategoryEntry {
    public static final Codec<IngredientCategoryEntry> CODEC = RecordCodecBuilder.create(i -> i.group(
        Codec.STRING.fieldOf("id").forGetter(e -> e.isTag ? "#" + e.id : e.id.toString()),
        Codec.FLOAT.fieldOf("value").forGetter(IngredientCategoryEntry::value)
    ).apply(i, IngredientCategoryEntry::parse));

    private final ResourceLocation id;
    private final boolean isTag;
    private final float value;

    public IngredientCategoryEntry(ResourceLocation id, boolean isTag, float value) {
        this.id = id;
        this.isTag = isTag;
        this.value = value;
    }

    // Parse from raw string, stripping # if present
    public static IngredientCategoryEntry parse(String raw, float value) {
        if (raw.startsWith("#")) {
            return new IngredientCategoryEntry(ResourceLocation.parse(raw.substring(1)), true, value);
        }
        return new IngredientCategoryEntry(ResourceLocation.parse(raw), false, value);
    }

    public ResourceLocation id() { return id; }
    public boolean isTag() { return isTag; }
    public float value() { return value; }
}