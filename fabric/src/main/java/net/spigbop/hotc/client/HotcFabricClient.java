package net.spigbop.hotc.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.spigbop.hotc.block.entity.ModBlockEntityTypes;
import net.spigbop.hotc.client.renderer.block.CrockPotBlockEntityRenderer;
import net.spigbop.hotc.client.renderer.model.block.CrockPotModel;
import net.spigbop.hotc.client.screen.CrockPotScreen;
import net.spigbop.hotc.menu.ModMenuTypes;
import net.spigbop.hotc.platform.ClientPacketSender;

public class HotcFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MenuScreens.register(ModMenuTypes.CROCK_POT, CrockPotScreen::new);
        ClientPacketSender.sender = ClientPlayNetworking::send;

        EntityModelLayerRegistry.registerModelLayer(
            CrockPotModel.LAYER_LOCATION,
            CrockPotModel::createBodyLayer
        );
        BlockEntityRenderers.register(
            ModBlockEntityTypes.CROCK_POT,
            CrockPotBlockEntityRenderer::new
        );
    }
}
