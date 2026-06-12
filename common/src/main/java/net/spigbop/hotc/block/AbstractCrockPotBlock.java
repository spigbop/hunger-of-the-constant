package net.spigbop.hotc.block;

import java.util.List;
import net.minecraft.core.BlockPos;
import net.minecraft.world.Containers;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.spigbop.hotc.block.entity.AbstractCrockPotBlockEntity;
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

    @Override
    protected boolean triggerEvent(
        BlockState state,
        Level level,
        BlockPos pos,
        int id,
        int param
    ) {
        if (level.getBlockEntity(pos) instanceof AbstractCrockPotBlockEntity be) {
            if (id == 1) {
                be.setOpenCount(param); // param is 1 = open, 0 = closed
                return true;
            } else if (id == 2) {
                be.setRattling(param); // param is 1 = yes, 0 = no
                return true;
            }
        }

        return super.triggerEvent(state, level, pos, id, param);
    }

    @Override
    protected void onRemove(
        BlockState state,
        Level level,
        BlockPos pos,
        BlockState newState,
        boolean movedByPiston
    ) {
        if (!state.is(newState.getBlock())) {
            if (level.getBlockEntity(pos) instanceof AbstractCrockPotBlockEntity be) {
                Containers.dropContents(level, pos, be);
                level.updateNeighbourForOutputSignal(pos, this);
            }
        }

        super.onRemove(state, level, pos, newState, movedByPiston);
    }
}
