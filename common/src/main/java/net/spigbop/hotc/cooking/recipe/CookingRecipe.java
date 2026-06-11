package net.spigbop.hotc.cooking.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import java.util.List;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.spigbop.hotc.cooking.predicate.CookingPredicate;

public class CookingRecipe {
    public static final Codec<CookingRecipe> CODEC = RecordCodecBuilder.create(i -> i
        .group(
            Codec.INT.fieldOf("priority").forGetter(CookingRecipe::priority),
            CookingPredicate.CODEC
                .listOf()
                .fieldOf("predicates")
                .forGetter(CookingRecipe::predicates),
            Codec.FLOAT.fieldOf("time").forGetter(CookingRecipe::time),
            ItemStack.CODEC.fieldOf("result").forGetter(CookingRecipe::result)
        )
        .apply(i, CookingRecipe::new));

    private final int priority;
    private final List<CookingPredicate> predicates;
    private final float time;
    private final ItemStack result;

    public CookingRecipe(
        int priority,
        List<CookingPredicate> predicates,
        float time,
        ItemStack result
    ) {
        this.priority = priority;
        this.predicates = predicates;
        this.time = time;
        this.result = result;
    }

    public int priority() {
        return priority;
    }

    public List<CookingPredicate> predicates() {
        return predicates;
    }

    public float time() {
        return time;
    }

    public ItemStack result() {
        return result;
    }

    /**
     * Returns true if all predicates pass
     */
    public boolean matches(Level level, ItemStack... items) {
        return predicates.stream().allMatch(p -> p.test(level, items));
    }
}
