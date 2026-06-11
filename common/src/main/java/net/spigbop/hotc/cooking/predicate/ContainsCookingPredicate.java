package net.spigbop.hotc.cooking.predicate;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

public class ContainsCookingPredicate extends CookingPredicate {
    public static final MapCodec<ContainsCookingPredicate> CODEC = RecordCodecBuilder.mapCodec(
        i -> i
            .group(Ingredient.CODEC
                .fieldOf("ingredient")
                .forGetter(p -> p.ingredient))
            .apply(i, ContainsCookingPredicate::new));

    private final Ingredient ingredient;

    public ContainsCookingPredicate(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public ContainsCookingPredicate(TagKey<Item> tag) {
        this.ingredient = Ingredient.of(tag);
    }

    public ContainsCookingPredicate(Item... items) {
        this.ingredient = Ingredient.of(items);
    }

    @Override
    public MapCodec<? extends CookingPredicate> codec() {
        return CODEC;
    }

    @Override
    public boolean test(Level _level, ItemStack... items) {
        for (ItemStack item : items) {
            if (ingredient.test(item)) {
                return true;
            }
        }

        return false;
    }
}
