package net.spigbop.hotc.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.network.PacketDistributor;
import net.spigbop.hotc.Constants;
import net.spigbop.hotc.platform.ClientPacketSender;

@EventBusSubscriber(modid = Constants.MOD_ID, value = Dist.CLIENT)
public class NeoHotcClient {
    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        ClientPacketSender.sender = PacketDistributor::sendToServer;
    }
}
