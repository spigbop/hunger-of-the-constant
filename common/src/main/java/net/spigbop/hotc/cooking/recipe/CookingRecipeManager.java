package net.spigbop.hotc.cooking.recipe;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.util.RandomSource;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.spigbop.hotc.Constants;

public class CookingRecipeManager extends SimpleJsonResourceReloadListener {
    public static final String DIRECTORY_NAME = "constant_cooking_recipes";
    private static final Gson GSON = new GsonBuilder().create();

    public static CookingRecipeManager INSTANCE = new CookingRecipeManager();

    private List<CookingRecipe> recipes = List.of();

    public CookingRecipeManager() {
        super(GSON, DIRECTORY_NAME);
    }

    @Override
    protected void apply(
        Map<ResourceLocation, JsonElement> data,
        ResourceManager manager,
        ProfilerFiller profiler
    ) {
        List<CookingRecipe> loaded = new ArrayList<>();
        data.forEach((id, json) -> {
            //            Constants.LOG.info("Registering cooking recipe: " + id.toString());
            CookingRecipe.CODEC
                .parse(JsonOps.INSTANCE, json)
                .ifSuccess(loaded::add)
                .ifError(err -> Constants.LOG.error(
                    "Failed to load cooking recipe {}: {}",
                    id,
                    err.message()
                ));
        });

        // Sort by priority descending — highest priority matched first
        loaded.sort(Comparator
            .comparingInt(CookingRecipe::priority)
            .reversed());
        this.recipes = List.copyOf(loaded);
        Constants.LOG.info("Loaded {} cooking recipes", recipes.size());
    }

    /**
     * Returns the first matching recipe by priority, if several recipes match,
     * returns a random one in matching recipes.
     */
    public Optional<CookingRecipe> findMatch(
        Level level,
        RandomSource random,
        ItemStack... items
    ) {
        List<CookingRecipe> matching = recipes
            .stream()
            .filter(r -> r.matches(level, items))
            .toList();

        if (matching.isEmpty()) {
            return Optional.empty();
        }

        // Get the highest priority among matches
        int topPriority = matching.getFirst().priority();

        // Collect all recipes tied at that priority
        List<CookingRecipe> candidates = matching
            .stream()
            .filter(r -> r.priority() == topPriority)
            .toList();

        return Optional.of(candidates.get(random.nextInt(candidates.size())));
    }

    public List<CookingRecipe> all() {
        return recipes;
    }
}
