package net.spigbop.hotc.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.spigbop.hotc.Constants;
import net.spigbop.hotc.block.entity.AbstractCrockPotBlockEntity;
import net.spigbop.hotc.menu.CrockPotMenu;

public record CookPacket(int containerId) implements CustomPacketPayload {
    public static final Type<CookPacket> TYPE = new Type<>(ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID,
        "cook"
    ));

    public static final StreamCodec<FriendlyByteBuf, CookPacket> CODEC = StreamCodec.composite(ByteBufCodecs.INT,
        CookPacket::containerId,
        CookPacket::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public static void handle(Player player) {
        if (player.containerMenu instanceof CrockPotMenu menu
            && menu.getContainer() instanceof AbstractCrockPotBlockEntity be) {
            be.tryStartCook();
        }
    }
}
