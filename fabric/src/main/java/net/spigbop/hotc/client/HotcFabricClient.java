package net.spigbop.hotc.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.spigbop.hotc.client.registry.FabricClientRegistry;
import net.spigbop.hotc.platform.ClientPacketSender;

public class HotcFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientPacketSender.sender = ClientPlayNetworking::send;
        FabricClientRegistry.register();
    }
}
