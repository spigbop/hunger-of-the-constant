package net.spigbop.hotc.block;

import java.util.function.ToIntFunction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;

public class ModBlocks {
    private static ToIntFunction<BlockState> litBlockEmission(int lightValue) {
        return (s) -> (Boolean) s.getValue(BlockStateProperties.LIT)
            ? lightValue
            : 0;
    }

    public static final Block CROCK_POT = new CrockPotBlock(BlockBehaviour.Properties
        .of()
        .mapColor(MapColor.RAW_IRON)
        .instrument(NoteBlockInstrument.BASS)
        .sound(SoundType.LANTERN)
        //.lightLevel(litBlockEmission(10))
        .strength(2.0F)
        .noOcclusion()
        .ignitedByLava());

    public static final Block MANDRAKE = new MandrakeBlock(BlockBehaviour.Properties
        .of()
        .mapColor(MapColor.TERRACOTTA_WHITE)
        .instrument(NoteBlockInstrument.FLUTE)
        .sound(ModSoundType.MANDRAKE)
        .strength(0.6F)
        .noOcclusion()
        .ignitedByLava());
}
