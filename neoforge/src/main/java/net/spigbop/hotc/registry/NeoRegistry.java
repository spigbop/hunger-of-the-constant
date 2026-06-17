package net.spigbop.hotc.registry;

import java.util.function.Function;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import net.spigbop.hotc.Constants;
import net.spigbop.hotc.block.ModBlocks;
import net.spigbop.hotc.block.entity.ModBlockEntityTypes;
import net.spigbop.hotc.block.entity.NeoBlockEntityTypes;
import net.spigbop.hotc.cooking.category.IngredientCategoryManager;
import net.spigbop.hotc.cooking.recipe.CookingRecipeManager;
import net.spigbop.hotc.item.ModCreativeModeTabs;
import net.spigbop.hotc.item.ModItems;
import net.spigbop.hotc.levelgen.feature.ModFeatures;
import net.spigbop.hotc.menu.ModMenuTypes;
import net.spigbop.hotc.menu.NeoMenuTypes;
import net.spigbop.hotc.network.CookPacket;
import net.spigbop.hotc.sounds.ModSoundEvents;
import net.spigbop.util.AutoRegistry;

@EventBusSubscriber(modid = Constants.MOD_ID)
public class NeoRegistry {
    @SubscribeEvent
    public static void registerManagers(AddReloadListenerEvent event) {
        event.addListener(IngredientCategoryManager.INSTANCE);
        event.addListener(CookingRecipeManager.INSTANCE);
    }

    @SubscribeEvent
    public static void registerPackets(RegisterPayloadHandlersEvent event) {
        event.registrar("1").playToServer(
            CookPacket.TYPE,
            CookPacket.CODEC,
            (packet, context) -> context.enqueueWork(() -> CookPacket.handle(
                context.player()))
        );
    }

    @SubscribeEvent
    public static void registerAutos(RegisterEvent event) {
        // Register Blocks
        event.register(
            BuiltInRegistries.BLOCK.key(),
            helper -> registerAllFromClass(helper, ModBlocks.class, Block.class)
        );

        // Register Items
        event.register(
            BuiltInRegistries.ITEM.key(),
            helper -> registerAllFromClass(helper, ModItems.class, Item.class)
        );

        // Register Tabs
        event.register(
            BuiltInRegistries.CREATIVE_MODE_TAB.key(),
            helper -> registerAllFromClass(
                helper,
                ModCreativeModeTabs.class,
                CreativeModeTab.class
            )
        );

        // Register Sounds
        event.register(
            BuiltInRegistries.SOUND_EVENT.key(), helper -> {
                registerAllFromClass(
                    helper,
                    ModSoundEvents.class,
                    SoundEvent.class,
                    (sound) -> sound.getLocation().getPath()
                );
            }
        );

        // Register Features
        event.register(
            BuiltInRegistries.FEATURE.key(), helper -> {
                registerAllFromClass(helper, ModFeatures.class, Feature.class);
            }
        );
    }

    private static <V, T extends V> void registerAllFromClass(
        RegisterEvent.RegisterHelper<V> helper,
        Class<?> registryClass,
        Class<T> type
    ) {
        AutoRegistry
            .getObjectsFrom(registryClass, type)
            .forEach((element, name) -> {
                helper.register(
                    ResourceLocation.fromNamespaceAndPath(
                        Constants.MOD_ID,
                        name
                    ), element
                );
            });
    }

    private static <V, T extends V> void registerAllFromClass(
        RegisterEvent.RegisterHelper<V> helper,
        Class<?> registryClass,
        Class<T> type,
        Function<T, String> nameProvider
    ) {
        AutoRegistry
            .getObjectsFrom(registryClass, type)
            .forEach((element, _autoName) -> {
                helper.register(
                    ResourceLocation.fromNamespaceAndPath(
                        Constants.MOD_ID,
                        nameProvider.apply(element)
                    ), element
                );
            });
    }

    @SubscribeEvent
    public static void injectInstances(FMLCommonSetupEvent event) {
        ModBlockEntityTypes.CROCK_POT = NeoBlockEntityTypes.CROCK_POT.get();
        ModMenuTypes.CROCK_POT = NeoMenuTypes.CROCK_POT.get();
    }

    public static void register(IEventBus eventBus) {
        NeoBlockEntityTypes.REGISTER.register(eventBus);
        NeoMenuTypes.REGISTER.register(eventBus);
    }
}
