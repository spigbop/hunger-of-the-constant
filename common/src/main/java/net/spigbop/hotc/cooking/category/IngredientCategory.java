package net.spigbop.hotc.cooking.category;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.spigbop.hotc.Constants;

public record IngredientCategory(List<IngredientCategoryEntry> values) {
    public static final Codec<IngredientCategory> CODEC = RecordCodecBuilder.create(
        i -> i
            .group(IngredientCategoryEntry.CODEC
                .listOf()
                .fieldOf("values")
                .forGetter(c -> c.values))
            .apply(i, IngredientCategory::new));

    /**
     * Check if an ItemStack matches any entry in this category
     */
    public boolean matches(ItemStack stack, RegistryAccess registryAccess) {
        for (IngredientCategoryEntry entry : values) {
            if (entry.isTag()) {
                TagKey<Item> tag = TagKey.create(Registries.ITEM, entry.id());
                if (stack.is(tag)) return true;
            } else {
                if (stack.is(BuiltInRegistries.ITEM.get(entry.id()))) return true;
            }
        }
        return false;
    }

    /**
     * Get value for a matching stack, 0 if no match
     */
    public float getValue(ItemStack stack, RegistryAccess registryAccess) {
        for (IngredientCategoryEntry entry : values) {
            if (entry.isTag()) {
                TagKey<Item> tag = TagKey.create(
                    Registries.ITEM,
                    ResourceLocation.parse(entry.id().toString())
                );
                if (stack.is(tag)) {
                    return entry.value();
                }
            } else {
                if (stack.is(BuiltInRegistries.ITEM.get(entry.id()))) {
                    return entry.value();
                }
            }
        }
        return 0.0f;
    }
}
