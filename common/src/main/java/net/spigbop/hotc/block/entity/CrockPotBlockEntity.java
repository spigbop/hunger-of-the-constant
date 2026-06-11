package net.spigbop.hotc.block.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class CrockPotBlockEntity extends AbstractCrockPotBlockEntity {
    public CrockPotBlockEntity(BlockPos pos, BlockState blockState) {
        super(ModBlockEntityTypes.CROCK_POT, pos, blockState);
    }

    public static void tick(
        Level level,
        BlockPos pos,
        BlockState state,
        CrockPotBlockEntity entity
    ) {
        entity.cookTick();
    }
}
