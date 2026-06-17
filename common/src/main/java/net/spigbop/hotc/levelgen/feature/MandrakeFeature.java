package net.spigbop.hotc.levelgen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.spigbop.hotc.block.ModBlocks;

public class MandrakeFeature extends Feature<NoneFeatureConfiguration> {
    public MandrakeFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext featurePlaceContext) {
        WorldGenLevel level = featurePlaceContext.level();
        BlockPos origin = featurePlaceContext.origin();

        for (int y = 0; y < 10; y++) {
            BlockPos testPos = origin.below(y);
            if (level.getBlockState(testPos).is(BlockTags.DIRT)) {
                BlockPos above = testPos.above();
                if (level.getBlockState(above).isAir()) {
                    level.setBlock(above, ModBlocks.MANDRAKE.defaultBlockState(), 3);
                    return true;
                }
            }
        }

        return false;
    }
}
