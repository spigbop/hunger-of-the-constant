package net.spigbop.hotc.cooking.predicate;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.spigbop.hotc.cooking.category.IngredientCategoryKey;

public class ContainsFillerCookingPredicate extends CookingPredicate {
    public static final MapCodec<ContainsFillerCookingPredicate> CODEC = RecordCodecBuilder.mapCodec(
        i -> i
            .group(IngredientCategoryKey.CODEC
                .fieldOf("category")
                .forGetter(p -> p.category))
            .apply(i, ContainsFillerCookingPredicate::new));

    private final IngredientCategoryKey category;

    public ContainsFillerCookingPredicate(IngredientCategoryKey category) {
        this.category = category;
    }

    @Override
    public MapCodec<? extends CookingPredicate> codec() {
        return CODEC;
    }

    @Override
    public boolean test(Level level, ItemStack... items) {
        for (ItemStack item : items) {
            if (category.contains(item, level.registryAccess())) {
                return true;
            }
        }

        return false;
    }
}
