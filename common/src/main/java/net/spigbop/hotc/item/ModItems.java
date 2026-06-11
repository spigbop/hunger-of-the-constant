package net.spigbop.hotc.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.spigbop.hotc.block.ModBlocks;

public class ModItems {
    public static final Item MONSTER_MEAT = new Item(new Item.Properties().food(
        new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.25F)
            .effect(new MobEffectInstance(MobEffects.POISON, 80), 0.5F)
            .build()));
    public static final Item HONEY = new Item(new Item.Properties().food(new FoodProperties.Builder()
        .nutrition(1)
        .saturationModifier(0.3F)
        .build()));
    public static final Item MANDRAKE = new Item(new Item.Properties().food(new FoodProperties.Builder()
        .nutrition(10)
        .saturationModifier(1.2F)
        .build()));

    public static final Item CROCK_POT = new BlockItem(
        ModBlocks.CROCK_POT,
        new Item.Properties()
    );

    public static final Item WET_GOOP = new Item(new Item.Properties().food(new FoodProperties.Builder()
        .nutrition(0)
        .saturationModifier(0.0F)
        .alwaysEdible()
        .build()));
    public static final Item MEATBALLS = new Item(new Item.Properties().food(new FoodProperties.Builder()
        .nutrition(9)
        .saturationModifier(0.8F)
        .build()));
    public static final Item HONEY_HAM = new Item(new Item.Properties().food(
        new FoodProperties.Builder()
            .nutrition(10)
            .saturationModifier(0.9F)
            .build()));
    public static final Item MONSTER_LASAGNA = new Item(new Item.Properties().food(
        new FoodProperties.Builder()
            .nutrition(5)
            .saturationModifier(0.3F)
            .effect(new MobEffectInstance(MobEffects.POISON, 200), 0.9F)
            .build()));
    public static final Item RATATOUILLE = new Item(new Item.Properties().food(
        new FoodProperties.Builder()
            .nutrition(4)
            .saturationModifier(0.8F)
            .build()));
    public static final Item TRAIL_MIX = new Item(new Item.Properties().food(new FoodProperties.Builder()
        .nutrition(2)
        .saturationModifier(0.4F)
        .fast()
        .effect(new MobEffectInstance(MobEffects.REGENERATION, 80), 1.0F)
        .build()));
    public static final Item TAFFY = new Item(new Item.Properties().food(new FoodProperties.Builder()
        .nutrition(2)
        .saturationModifier(0.4F)
        .fast()
        .effect(new MobEffectInstance(MobEffects.LUCK, 60), 0.2F)
        .build()));
    public static final Item FIST_FULL_OF_JAM = new Item(new Item.Properties().food(
        new FoodProperties.Builder()
            .nutrition(5)
            .saturationModifier(0.4F)
            .build()));
    public static final Item MEATY_STEW = new Item(new Item.Properties().food(
        new FoodProperties.Builder()
            .nutrition(20)
            .saturationModifier(1.0F)
            .build()));
    public static final Item MANDRAKE_SOUP = new Item(new Item.Properties().food(
        new FoodProperties.Builder()
            .nutrition(20)
            .saturationModifier(1.2F)
            .alwaysEdible()
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 2400), 1.0F)
            .effect(new MobEffectInstance(MobEffects.REGENERATION, 200), 1.0F)
            .build()));
}
