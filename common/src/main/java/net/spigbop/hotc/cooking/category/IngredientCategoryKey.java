package net.spigbop.hotc.cooking.category;

import com.mojang.serialization.Codec;
import net.minecraft.Util;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class IngredientCategoryKey {
    public static final Codec<IngredientCategoryKey> CODEC = ResourceLocation.CODEC.xmap(IngredientCategoryKey::of,
        IngredientCategoryKey::getLocation
    );

    private final ResourceLocation id;
    private String descriptionId;

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

    public ResourceLocation getLocation() {
        return id;
    }

    public String getOrCreateDescriptionId() {
        if (this.descriptionId == null) {
            this.descriptionId = Util.makeDescriptionId(
                "ingredient_category",
                this.id
            );
        }

        return this.descriptionId;
    }

    public Component getDisplayName() {
        return Component.translatable(this.getOrCreateDescriptionId());
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
