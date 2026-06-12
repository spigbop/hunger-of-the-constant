package net.spigbop.hotc.registry;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.spigbop.hotc.Constants;
import net.spigbop.hotc.block.ModBlocks;
import net.spigbop.hotc.block.entity.FabricBlockEntityType;
import net.spigbop.hotc.commands.CookDebugCommand;
import net.spigbop.hotc.cooking.category.FabricIngredientCategoryManager;
import net.spigbop.hotc.cooking.recipe.FabricCookingRecipeManager;
import net.spigbop.hotc.item.ModCreativeModeTabs;
import net.spigbop.hotc.item.ModItems;
import net.spigbop.hotc.menu.CrockPotMenu;
import net.spigbop.hotc.menu.ModMenuTypes;
import net.spigbop.hotc.network.CookPacket;
import net.spigbop.hotc.sounds.ModSoundEvents;
import net.spigbop.util.AutoRegistry;

public class FabricRegistry {
    public static void register() {
        ResourceManagerHelper
            .get(PackType.SERVER_DATA)
            .registerReloadListener(FabricIngredientCategoryManager.FABRIC_INSTANCE);
        ResourceManagerHelper
            .get(PackType.SERVER_DATA)
            .registerReloadListener(FabricCookingRecipeManager.FABRIC_INSTANCE);

        // Register Blocks
        AutoRegistry
            .getObjectsFrom(ModBlocks.class, Block.class)
            .forEach((item, name) -> {
                Registry.register(
                    BuiltInRegistries.BLOCK, ResourceKey.create(
                        BuiltInRegistries.BLOCK.key(),
                        ResourceLocation.fromNamespaceAndPath(
                            Constants.MOD_ID,
                            name
                        )
                    ), item
                );
            });

        // Register Block Entities
        FabricBlockEntityType.register();

        // Register Items
        AutoRegistry
            .getObjectsFrom(ModItems.class, Item.class)
            .forEach((item, name) -> {
                Registry.register(
                    BuiltInRegistries.ITEM, ResourceKey.create(
                        BuiltInRegistries.ITEM.key(),
                        ResourceLocation.fromNamespaceAndPath(
                            Constants.MOD_ID,
                            name
                        )
                    ), item
                );
            });

        // Register Tabs
        AutoRegistry
            .getObjectsFrom(ModCreativeModeTabs.class, CreativeModeTab.class)
            .forEach((tab, name) -> {
                Registry.register(
                    BuiltInRegistries.CREATIVE_MODE_TAB, ResourceKey.create(
                        BuiltInRegistries.CREATIVE_MODE_TAB.key(),
                        ResourceLocation.fromNamespaceAndPath(
                            Constants.MOD_ID,
                            name
                        )
                    ), tab
                );
            });

        // Register Sounds
        AutoRegistry
            .getObjectsFrom(ModSoundEvents.class, SoundEvent.class)
            .forEach((sound, name) -> {
                Registry.register(
                    BuiltInRegistries.SOUND_EVENT, ResourceKey.create(
                        BuiltInRegistries.SOUND_EVENT.key(),
                        ResourceLocation.fromNamespaceAndPath(
                            Constants.MOD_ID,
                            sound.getLocation().getPath()
                        )
                    ), sound
                );
            });

        // Register Menus
        ModMenuTypes.CROCK_POT = Registry.register(
            BuiltInRegistries.MENU,
            ResourceLocation.fromNamespaceAndPath(
                Constants.MOD_ID,
                "crock_pot"
            ),
            new MenuType<>(CrockPotMenu::new, FeatureFlags.VANILLA_SET)
        );

        // Register Packets
        PayloadTypeRegistry
            .playC2S()
            .register(CookPacket.TYPE, CookPacket.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(
            CookPacket.TYPE,
            (packet, context) -> CookPacket.handle(context.player())
        );

        // Register Commands
        CommandRegistrationCallback.EVENT.register(((commandDispatcher, commandBuildContext, commandSelection) -> CookDebugCommand.register(commandDispatcher,
            commandBuildContext
        )));
    }
}
