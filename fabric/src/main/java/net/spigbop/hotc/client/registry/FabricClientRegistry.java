package net.spigbop.hotc.client.registry;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.spigbop.hotc.block.entity.ModBlockEntityTypes;
import net.spigbop.hotc.client.renderer.block.CrockPotBlockEntityRenderer;
import net.spigbop.hotc.client.renderer.model.block.CrockPotModel;
import net.spigbop.hotc.client.screen.CrockPotScreen;
import net.spigbop.hotc.menu.ModMenuTypes;

public class FabricClientRegistry {
    public static void registerScreens() {
        MenuScreens.register(ModMenuTypes.CROCK_POT, CrockPotScreen::new);
    }

    public static void registerModels() {
        EntityModelLayerRegistry.registerModelLayer(
            CrockPotModel.LAYER_LOCATION,
            CrockPotModel::createBodyLayer
        );
    }

    public static void registerRenderers() {
        BlockEntityRenderers.register(
            ModBlockEntityTypes.CROCK_POT,
            CrockPotBlockEntityRenderer::new
        );
    }

    public static void register() {
        registerScreens();
        registerModels();
        registerRenderers();
    }
}
