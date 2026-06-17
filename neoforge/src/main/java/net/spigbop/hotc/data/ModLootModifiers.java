package net.spigbop.hotc.data;

import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;
import net.neoforged.neoforge.common.loot.AddTableLootModifier;
import net.neoforged.neoforge.common.loot.LootTableIdCondition;
import net.spigbop.hotc.Constants;
import net.spigbop.hotc.loot.ModLootTables;
import net.spigbop.util.AutoRegistry;

public class ModLootModifiers extends GlobalLootModifierProvider {
    public ModLootModifiers(
        PackOutput output,
        CompletableFuture<HolderLookup.Provider> registries
    ) {
        super(output, registries, Constants.MOD_ID);
    }

    private AddTableLootModifier addModifier(
        ResourceKey<LootTable> original,
        ResourceKey<LootTable> patch
    ) {
        return new AddTableLootModifier(
            new LootItemCondition[]{
                LootTableIdCondition.builder(original.location()).build()
            }, patch
        );
    }

    @Override
    protected void start() {
        AutoRegistry
            .getObjectsFrom(
                ModLootTables.class,
                ModLootTables.LootModifierContext.class
            )
            .forEach((ctx, name) -> {
                this.add(
                    name,
                    this.addModifier(
                        ctx.base(),
                        ctx.key()
                    )
                );
            });
    }
}
