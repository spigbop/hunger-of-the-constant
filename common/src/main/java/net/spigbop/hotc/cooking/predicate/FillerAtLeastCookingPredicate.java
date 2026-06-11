package net.spigbop.hotc.cooking.predicate;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.spigbop.hotc.cooking.category.IngredientCategoryKey;

public class FillerAtLeastCookingPredicate extends CookingPredicate {
    public static final MapCodec<FillerAtLeastCookingPredicate> CODEC = RecordCodecBuilder.mapCodec(
        i -> i.group(
            IngredientCategoryKey.CODEC
                .fieldOf("category")
                .forGetter(p -> p.category),
            Codec.FLOAT.fieldOf("value").forGetter(p -> p.value)
        ).apply(i, FillerAtLeastCookingPredicate::new));

    private final IngredientCategoryKey category;
    private final float value;

    public FillerAtLeastCookingPredicate(
        IngredientCategoryKey category,
        float value
    ) {
        this.category = category;
        this.value = value;
    }

    @Override
    public MapCodec<? extends CookingPredicate> codec() {
        return CODEC;
    }

    @Override
    public boolean test(Level level, ItemStack... items) {
        float value = 0.0f;

        for (ItemStack item : items) {
            value += category.getValue(item, level.registryAccess());
        }

        return value >= this.value;
    }
}
