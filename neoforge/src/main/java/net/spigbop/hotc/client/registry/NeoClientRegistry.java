package net.spigbop.hotc.client.registry;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.spigbop.hotc.Constants;
import net.spigbop.hotc.block.entity.NeoBlockEntityTypes;
import net.spigbop.hotc.client.renderer.block.CrockPotBlockEntityRenderer;
import net.spigbop.hotc.client.renderer.model.block.CrockPotModel;
import net.spigbop.hotc.client.screen.CrockPotScreen;
import net.spigbop.hotc.menu.NeoMenuTypes;

@EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT)
public class NeoClientRegistry {
    @SubscribeEvent
    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(NeoMenuTypes.CROCK_POT.get(), CrockPotScreen::new);
    }

    @SubscribeEvent
    public static void registerModels(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(
            CrockPotModel.LAYER_LOCATION,
            CrockPotModel::createBodyLayer
        );
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(
            NeoBlockEntityTypes.CROCK_POT.get(),
            CrockPotBlockEntityRenderer::new
        );
    }
}
