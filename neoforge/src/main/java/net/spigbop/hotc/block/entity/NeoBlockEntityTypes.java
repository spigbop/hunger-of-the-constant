package net.spigbop.hotc.block.entity;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.spigbop.hotc.Constants;
import net.spigbop.hotc.block.ModBlocks;

public class NeoBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> REGISTER = DeferredRegister.create(
        Registries.BLOCK_ENTITY_TYPE,
        Constants.MOD_ID
    );

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<CrockPotBlockEntity>> CROCK_POT = REGISTER.register(
        "crock_pot",
        () -> BlockEntityType.Builder
            .of(CrockPotBlockEntity::new, ModBlocks.CROCK_POT)
            .build(null)
    );
}
