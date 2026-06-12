package net.spigbop.hotc.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.spigbop.hotc.block.entity.CrockPotBlockEntity;
import net.spigbop.hotc.block.entity.ModBlockEntityTypes;
import org.jetbrains.annotations.Nullable;

public class CrockPotBlock extends AbstractCrockPotBlock {
    protected CrockPotBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return simpleCodec(CrockPotBlock::new);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(
        BlockPos blockPos,
        BlockState blockState
    ) {
        return new CrockPotBlockEntity(blockPos, blockState);
    }

    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(
        Level level,
        BlockState state,
        BlockEntityType<T> type
    ) {
        return createTickerHelper(
            type,
            ModBlockEntityTypes.CROCK_POT,
            CrockPotBlockEntity::tick
        );
    }

    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }

    private static final VoxelShape SHAPE = Block.box(1, 0, 1, 15, 20, 15);
    private static final VoxelShape COLLISION_SHAPE = Block.box(2, 0, 2, 14, 24,
        14);

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

    @Override
    protected InteractionResult useWithoutItem(
        BlockState state,
        Level level,
        BlockPos pos,
        Player player,
        BlockHitResult hitResult
    ) {
        if (!level.isClientSide) {
            BlockEntity entity = level.getBlockEntity(pos);
            if (entity instanceof CrockPotBlockEntity crockPot &&
                !crockPot.isCooking()) {
                player.openMenu(crockPot);
            }
        }
        return InteractionResult.SUCCESS;
    }
}
