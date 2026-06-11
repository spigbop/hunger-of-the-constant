package net.spigbop.hotc;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.event.AddReloadListenerEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.spigbop.hotc.block.entity.ModBlockEntityTypes;
import net.spigbop.hotc.block.entity.NeoBlockEntityTypes;
import net.spigbop.hotc.client.screen.CrockPotScreen;
import net.spigbop.hotc.cooking.category.IngredientCategoryManager;
import net.spigbop.hotc.cooking.recipe.CookingRecipeManager;
import net.spigbop.hotc.menu.ModMenuTypes;
import net.spigbop.hotc.menu.NeoMenuTypes;
import net.spigbop.hotc.network.CookPacket;
import net.spigbop.hotc.registry.NeoRegistry;

@Mod(Constants.MOD_ID)
public class HotcNeo {
    public HotcNeo(IEventBus eventBus) {
        Hotc.init();
        eventBus.addListener((AddReloadListenerEvent e) -> {
            e.addListener(IngredientCategoryManager.INSTANCE);
        });
        eventBus.addListener((AddReloadListenerEvent e) -> {
            e.addListener(CookingRecipeManager.INSTANCE);
        });

        NeoBlockEntityTypes.REGISTER.register(eventBus);
        NeoMenuTypes.REGISTER.register(eventBus);
        eventBus.addListener((FMLCommonSetupEvent e) -> {
            ModBlockEntityTypes.CROCK_POT = NeoBlockEntityTypes.CROCK_POT.get();
            ModMenuTypes.CROCK_POT = NeoMenuTypes.CROCK_POT.get();
        });

        eventBus.addListener((RegisterMenuScreensEvent e) -> {
            e.register(ModMenuTypes.CROCK_POT, CrockPotScreen::new);
        });

        eventBus.register(NeoRegistry.class);

        eventBus.addListener((RegisterPayloadHandlersEvent e) -> {
            e.registrar("1").playToServer(
                CookPacket.TYPE,
                CookPacket.CODEC,
                (packet, context) -> context.enqueueWork(() -> CookPacket.handle(
                    context.player()))
            );
        });
    }
}