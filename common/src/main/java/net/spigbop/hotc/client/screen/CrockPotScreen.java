package net.spigbop.hotc.client.screen;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.spigbop.hotc.Constants;
import net.spigbop.hotc.menu.CrockPotMenu;
import net.spigbop.hotc.network.CookPacket;
import net.spigbop.hotc.platform.ClientPacketSender;

public class CrockPotScreen extends AbstractContainerScreen<CrockPotMenu> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID,
        "textures/gui/crock_pot.png"
    );

    public CrockPotScreen(
        CrockPotMenu menu,
        Inventory playerInventory,
        Component title
    ) {
        super(menu, playerInventory, title);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void init() {
        super.init();
        addRenderableWidget(Button.builder(
            Component.translatable("gui.hotc.cook"),
            btn -> sendCookPacket()
        ).bounds(leftPos + 116, topPos + 55, 52, 20).build());
    }

    private void sendCookPacket() {
        ClientPacketSender.send(new CookPacket(menu.containerId));
    }

    @Override
    protected void renderBg(
        GuiGraphics graphics,
        float partialTick,
        int mouseX,
        int mouseY
    ) {
        graphics.blit(TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(
        GuiGraphics graphics,
        int mouseX,
        int mouseY,
        float partialTick
    ) {
        renderBackground(graphics, mouseX, mouseY, partialTick);
        super.render(graphics, mouseX, mouseY, partialTick);
        renderTooltip(graphics, mouseX, mouseY);
    }
}
