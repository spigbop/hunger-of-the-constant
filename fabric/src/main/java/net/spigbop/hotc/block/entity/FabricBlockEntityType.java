package net.spigbop.hotc.block.entity;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.spigbop.hotc.Constants;
import net.spigbop.hotc.block.ModBlocks;

public class FabricBlockEntityType {
    public static void register() {
        ModBlockEntityTypes.CROCK_POT = FabricBlockEntityTypeBuilder
            .create(CrockPotBlockEntity::new, ModBlocks.CROCK_POT)
            .build();
        Registry.register(
            BuiltInRegistries.BLOCK_ENTITY_TYPE,
            ResourceLocation.fromNamespaceAndPath(
                Constants.MOD_ID,
                "crock_pot"
            ),
            ModBlockEntityTypes.CROCK_POT
        );
    }
}
