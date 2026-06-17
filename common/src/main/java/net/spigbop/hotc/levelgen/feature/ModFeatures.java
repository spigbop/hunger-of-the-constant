package net.spigbop.hotc.levelgen.feature;

import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class ModFeatures {
    public static final Feature<NoneFeatureConfiguration> MANDRAKE = new MandrakeFeature(
        NoneFeatureConfiguration.CODEC);
}
