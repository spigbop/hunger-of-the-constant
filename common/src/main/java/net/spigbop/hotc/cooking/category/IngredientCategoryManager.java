package net.spigbop.hotc.cooking.category;

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import java.util.Map;
import java.util.Optional;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.ItemStack;
import net.spigbop.hotc.Constants;

public class IngredientCategoryManager
    extends SimpleJsonResourceReloadListener
{
    public static final String DIRECTORY_NAME = "constant_ingredients";
    private static final Gson GSON = new GsonBuilder().create();

    public static IngredientCategoryManager INSTANCE = new IngredientCategoryManager();

    private Map<ResourceLocation, IngredientCategory> categories = Map.of();

    public IngredientCategoryManager() {
        super(GSON, DIRECTORY_NAME);
    }

    @Override
    protected void apply(
        Map<ResourceLocation, JsonElement> data,
        ResourceManager manager,
        ProfilerFiller profiler
    ) {
        ImmutableMap.Builder<ResourceLocation, IngredientCategory> builder = ImmutableMap.builder();
        data.forEach((id, json) -> {
            DataResult<IngredientCategory> result = IngredientCategory.CODEC.parse(JsonOps.INSTANCE,
                json
            );
            result
                .ifSuccess(cat -> builder.put(id, cat))
                .ifError(err -> Constants.LOG.error(
                    "Failed to load ingredient category {}: {}",
                    id,
                    err.message()
                ));
        });
        this.categories = builder.build();

        Constants.LOG.info(
            "Loaded {} ingredient categories: {}",
            categories.size(),
            categories.keySet()
        );
    }

    public Optional<IngredientCategory> get(IngredientCategoryKey key) {
        return Optional.ofNullable(categories.get(key.getLocation()));
    }

    public Optional<IngredientCategory> get(ResourceLocation id) {
        return Optional.ofNullable(categories.get(id));
    }

    public boolean isInAnyCategory(
        RegistryAccess registryAccess,
        ItemStack item
    ) {
        return categories
            .values()
            .stream()
            .anyMatch(cat -> cat.matches(item, registryAccess));
    }
}
