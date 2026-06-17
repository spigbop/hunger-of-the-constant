package net.spigbop.hotc.loot;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.LootTable;
import net.spigbop.hotc.Constants;

public class ModLootTables {
    public static final ResourceKey<LootTable> BEE = ResourceKey.create(
        Registries.LOOT_TABLE,
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "entities/bee")
    );
    public static final ResourceKey<LootTable> FROG = ResourceKey.create(
        Registries.LOOT_TABLE,
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "entities/frog")
    );
    public static final ResourceKey<LootTable> CAVE_SPIDER = ResourceKey.create(
        Registries.LOOT_TABLE,
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "entities/cave_spider")
    );
    public static final ResourceKey<LootTable> ELDER_GUARDIAN = ResourceKey.create(
        Registries.LOOT_TABLE,
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "entities/elder_guardian")
    );
    public static final ResourceKey<LootTable> ENDER_DRAGON = ResourceKey.create(
        Registries.LOOT_TABLE,
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "entities/ender_dragon")
    );
    public static final ResourceKey<LootTable> ENDERMAN = ResourceKey.create(
        Registries.LOOT_TABLE,
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "entities/enderman")
    );
    public static final ResourceKey<LootTable> GUARDIAN = ResourceKey.create(
        Registries.LOOT_TABLE,
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "entities/guardian")
    );
    public static final ResourceKey<LootTable> RAVAGER = ResourceKey.create(
        Registries.LOOT_TABLE,
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "entities/ravager")
    );
    public static final ResourceKey<LootTable> SPIDER = ResourceKey.create(
        Registries.LOOT_TABLE,
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "entities/spider")
    );
    public static final ResourceKey<LootTable> WARDEN = ResourceKey.create(
        Registries.LOOT_TABLE,
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "entities/warden")
    );

    public record LootModifierContext(
        ResourceKey<LootTable> key,
        ResourceKey<LootTable> base
    )
    {}

    public static final LootModifierContext ADD_BEE = new LootModifierContext(BEE,
        EntityType.BEE.getDefaultLootTable()
    );
    public static final LootModifierContext ADD_FROG = new LootModifierContext(FROG,
        EntityType.FROG.getDefaultLootTable()
    );
    public static final LootModifierContext ADD_CAVE_SPIDER = new LootModifierContext(CAVE_SPIDER,
        EntityType.CAVE_SPIDER.getDefaultLootTable()
    );
    public static final LootModifierContext ADD_ELDER_GUARDIAN = new LootModifierContext(ELDER_GUARDIAN,
        EntityType.ELDER_GUARDIAN.getDefaultLootTable()
    );
    public static final LootModifierContext ADD_ENDER_DRAGON = new LootModifierContext(ENDER_DRAGON,
        EntityType.ENDER_DRAGON.getDefaultLootTable()
    );
    public static final LootModifierContext ADD_ENDERMAN = new LootModifierContext(ENDERMAN,
        EntityType.ENDERMAN.getDefaultLootTable()
    );
    public static final LootModifierContext ADD_GUARDIAN = new LootModifierContext(GUARDIAN,
        EntityType.GUARDIAN.getDefaultLootTable()
    );
    public static final LootModifierContext ADD_RAVAGER = new LootModifierContext(RAVAGER,
        EntityType.RAVAGER.getDefaultLootTable()
    );
    public static final LootModifierContext ADD_SPIDER = new LootModifierContext(SPIDER,
        EntityType.SPIDER.getDefaultLootTable()
    );
    public static final LootModifierContext ADD_WARDEN = new LootModifierContext(WARDEN,
        EntityType.WARDEN.getDefaultLootTable()
    );
}
