package net.spigbop.hotc.platform;

import java.util.function.Consumer;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public class ClientPacketSender {
    public static Consumer<CustomPacketPayload> sender;

    public static void send(CustomPacketPayload packet) {
        sender.accept(packet);
    }
}
