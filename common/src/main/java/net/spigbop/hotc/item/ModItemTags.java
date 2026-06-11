package net.spigbop.hotc.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.spigbop.hotc.Constants;

public class ModItemTags {
    public static final TagKey<Item> ACORNS =
        TagKey.create(Registries.ITEM,
            ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "acorns"));
    public static final TagKey<Item> BERRIES =
        TagKey.create(Registries.ITEM,
            ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "berries"));
}
