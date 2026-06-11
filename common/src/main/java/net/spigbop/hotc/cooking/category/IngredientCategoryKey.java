package net.spigbop.hotc.cooking.category;

import com.mojang.serialization.Codec;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class IngredientCategoryKey {
    public static final Codec<IngredientCategoryKey> CODEC = ResourceLocation.CODEC.xmap(IngredientCategoryKey::of,
        IngredientCategoryKey::id
    );

    private final ResourceLocation id;

    private IngredientCategoryKey(ResourceLocation id) {
        this.id = id;
    }

    public static IngredientCategoryKey of(ResourceLocation id) {
        return new IngredientCategoryKey(id);
    }

    public static IngredientCategoryKey of(String namespace, String path) {
        return new IngredientCategoryKey(ResourceLocation.fromNamespaceAndPath(namespace,
            path
        ));
    }

    public ResourceLocation id() {
        return id;
    }

    public boolean contains(ItemStack item, RegistryAccess registryAccess) {
        return IngredientCategoryManager.INSTANCE
            .get(this)
            .map(cat -> cat.matches(item, registryAccess))
            .orElse(false);
    }

    public boolean contains(Item item, RegistryAccess registryAccess) {
        return IngredientCategoryManager.INSTANCE
            .get(this)
            .map(cat -> cat.matches(new ItemStack(item), registryAccess))
            .orElse(false);
    }

    public float getValue(Item item, RegistryAccess registryAccess) {
        return IngredientCategoryManager.INSTANCE
            .get(this)
            .map(cat -> cat.getValue(new ItemStack(item), registryAccess))
            .orElse(0.0f);
    }

    public float getValue(ItemStack item, RegistryAccess registryAccess) {
        return IngredientCategoryManager.INSTANCE
            .get(this)
            .map(cat -> cat.getValue(item, registryAccess))
            .orElse(0.0f);
    }
}
