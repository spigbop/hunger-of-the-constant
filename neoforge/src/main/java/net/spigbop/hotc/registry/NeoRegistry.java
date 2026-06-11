package net.spigbop.hotc.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.spigbop.hotc.Constants;
import net.spigbop.hotc.block.ModBlocks;
import net.spigbop.hotc.commands.CookDebugCommand;
import net.spigbop.hotc.item.ModCreativeModeTabs;
import net.spigbop.hotc.item.ModItems;
import net.spigbop.util.AutoRegistry;

public class NeoRegistry {
    @SubscribeEvent
    public static void registerCommands(RegisterCommandsEvent event) {
        CookDebugCommand.register(event.getDispatcher(), event.getBuildContext());
    }

    @SubscribeEvent
    public static void register(RegisterEvent event) {
        // Register Blocks
        event.register(
            BuiltInRegistries.BLOCK.key(), helper -> {
                AutoRegistry
                    .getObjectsFrom(ModBlocks.class, Block.class)
                    .forEach((item, name) -> {
                        helper.register(
                            ResourceLocation.fromNamespaceAndPath(
                                Constants.MOD_ID,
                                name
                            ), item
                        );
                    });
            }
        );

        // Register Items
        event.register(
            BuiltInRegistries.ITEM.key(), helper -> {
                AutoRegistry
                    .getObjectsFrom(ModItems.class, Item.class)
                    .forEach((item, name) -> {
                        helper.register(
                            ResourceLocation.fromNamespaceAndPath(
                                Constants.MOD_ID,
                                name
                            ), item
                        );
                    });
            }
        );

        // Register Tabs
        event.register(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(), helper -> {
                AutoRegistry
                    .getObjectsFrom(
                        ModCreativeModeTabs.class,
                        CreativeModeTab.class
                    )
                    .forEach((tab, name) -> {
                        helper.register(
                            ResourceLocation.fromNamespaceAndPath(
                                Constants.MOD_ID,
                                name
                            ), tab
                        );
                    });
            }
        );
    }
}
