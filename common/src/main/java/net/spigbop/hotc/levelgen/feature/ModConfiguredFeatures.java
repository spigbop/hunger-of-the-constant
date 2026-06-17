package net.spigbop.hotc.levelgen.feature;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.spigbop.hotc.Constants;

public class ModConfiguredFeatures {
    public static final ResourceKey<ConfiguredFeature<?, ?>> MANDRAKE = ResourceKey.create(
        Registries.CONFIGURED_FEATURE,
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "mandrake")
    );
}
