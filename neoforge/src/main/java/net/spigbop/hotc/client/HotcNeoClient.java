package net.spigbop.hotc.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.spigbop.hotc.Constants;
import net.spigbop.hotc.block.entity.ModBlockEntityTypes;
import net.spigbop.hotc.client.renderer.block.CrockPotBlockEntityRenderer;
import net.spigbop.hotc.client.renderer.model.block.CrockPotModel;
import net.spigbop.hotc.platform.ClientPacketSender;

@EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT)
public class HotcNeoClient {
    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions e) {
        e.registerLayerDefinition(
            CrockPotModel.LAYER_LOCATION,
            CrockPotModel::createBodyLayer
        );
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers e) {
        e.registerBlockEntityRenderer(
            ModBlockEntityTypes.CROCK_POT,
            CrockPotBlockEntityRenderer::new
        );
    }

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        ClientPacketSender.sender = PacketDistributor::sendToServer;
    }
}
