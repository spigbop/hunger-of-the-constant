package net.spigbop.hotc.registry;

import java.util.function.Function;
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
    public static void registerManagers() {
        ResourceManagerHelper
            .get(PackType.SERVER_DATA)
            .registerReloadListener(FabricIngredientCategoryManager.FABRIC_INSTANCE);
        ResourceManagerHelper
            .get(PackType.SERVER_DATA)
            .registerReloadListener(FabricCookingRecipeManager.FABRIC_INSTANCE);
    }

    public static void registerMenus() {
        ModMenuTypes.CROCK_POT = Registry.register(
            BuiltInRegistries.MENU,
            ResourceLocation.fromNamespaceAndPath(
                Constants.MOD_ID,
                "crock_pot"
            ),
            new MenuType<>(CrockPotMenu::new, FeatureFlags.VANILLA_SET)
        );
    }

    public static void registerPackets() {
        PayloadTypeRegistry
            .playC2S()
            .register(CookPacket.TYPE, CookPacket.CODEC);
        ServerPlayNetworking.registerGlobalReceiver(
            CookPacket.TYPE,
            (packet, context) -> CookPacket.handle(context.player())
        );
    }

    public static void registerAutos() {
        // Register Blocks
        registerAllFromClass(
            BuiltInRegistries.BLOCK,
            ModBlocks.class,
            Block.class
        );

        // Register Block Entities
        FabricBlockEntityType.register();

        // Register Items
        registerAllFromClass(
            BuiltInRegistries.ITEM,
            ModItems.class,
            Item.class
        );

        // Register Tabs
        registerAllFromClass(
            BuiltInRegistries.CREATIVE_MODE_TAB,
            ModCreativeModeTabs.class,
            CreativeModeTab.class
        );

        // Register Sounds
        registerAllFromClass(
            BuiltInRegistries.SOUND_EVENT,
            ModSoundEvents.class,
            SoundEvent.class,
            (sound) -> sound.getLocation().getPath()
        );
    }

    private static <T> void registerAllFromClass(
        Registry<T> registry,
        Class<?> registryClass,
        Class<T> type
    ) {
        AutoRegistry
            .getObjectsFrom(registryClass, type)
            .forEach((element, name) -> {
                Registry.register(
                    registry, ResourceKey.create(
                        registry.key(),
                        ResourceLocation.fromNamespaceAndPath(
                            Constants.MOD_ID,
                            name
                        )
                    ), element
                );
            });
    }

    private static <T> void registerAllFromClass(
        Registry<T> registry,
        Class<?> registryClass,
        Class<T> type,
        Function<T, String> nameProvider
    ) {
        AutoRegistry
            .getObjectsFrom(registryClass, type)
            .forEach((element, name) -> {
                Registry.register(
                    registry, ResourceKey.create(
                        registry.key(),
                        ResourceLocation.fromNamespaceAndPath(
                            Constants.MOD_ID,
                            nameProvider.apply(element)
                        )
                    ), element
                );
            });
    }

    public static void register() {
        registerManagers();
        registerAutos();
        registerMenus();
        registerPackets();
    }
}
