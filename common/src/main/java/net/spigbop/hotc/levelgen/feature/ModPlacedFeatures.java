package net.spigbop.hotc.levelgen.feature;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.spigbop.hotc.Constants;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> MANDRAKE = ResourceKey.create(
        Registries.PLACED_FEATURE,
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "mandrake")
    );
}
