package net.spigbop.hotc.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class MandrakeBlock extends Block {
    public MandrakeBlock(Properties properties) {
        super(properties);
    }

    private static final VoxelShape SHAPE = Block.box(5, 0, 5, 11, 6, 11);
    private static final VoxelShape COLLISION_SHAPE = Block.box(6, 0, 6, 10, 4,
        10);

    @Override
    protected VoxelShape getShape(
        BlockState state,
        BlockGetter level,
        BlockPos pos,
        CollisionContext context
    ) {
        return SHAPE;
    }

    @Override
    protected VoxelShape getCollisionShape(
        BlockState state,
        BlockGetter level,
        BlockPos pos,
        CollisionContext context
    ) {
        return COLLISION_SHAPE;
    }
}
