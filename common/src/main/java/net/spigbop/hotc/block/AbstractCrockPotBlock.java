package net.spigbop.hotc.block;

import java.util.List;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.spigbop.hotc.cooking.predicate.AlwaysCookingPredicate;
import net.spigbop.hotc.cooking.recipe.CookingRecipe;
import net.spigbop.hotc.cooking.recipe.CookingRecipeManager;
import net.spigbop.hotc.item.ModItems;

public abstract class AbstractCrockPotBlock extends BaseEntityBlock {
    protected AbstractCrockPotBlock(Properties properties) {
        super(properties);
    }

    public static CookingRecipe failStartCook() {
        return new CookingRecipe(
            -10,
            List.of(AlwaysCookingPredicate.INSTANCE),
            0.25f,
            new ItemStack(ModItems.WET_GOOP, 1)
        );
    }

    public static CookingRecipe startCook(Level level, ItemStack... slots) {
        if (slots.length != 4) {
            return failStartCook();
        }

        return CookingRecipeManager.INSTANCE
            .findMatch(level, level.getRandom(), slots)
            .orElse(failStartCook());
    }

    public static ItemStack finishCook(CookingRecipe recipe) {
        return recipe.result().copy();
    }
}
