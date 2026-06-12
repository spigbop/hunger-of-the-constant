package net.spigbop.hotc.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.spigbop.hotc.Constants;
import net.spigbop.hotc.block.entity.CrockPotBlockEntity;
import net.spigbop.hotc.client.renderer.model.block.CrockPotModel;
import org.joml.Quaternionf;

public class CrockPotBlockEntityRenderer
    implements BlockEntityRenderer<CrockPotBlockEntity>
{
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID,
        "textures/entity/crock_pot.png"
    );

    private final CrockPotModel model;
    private final ItemRenderer itemRenderer;

    public CrockPotBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.model = new CrockPotModel(context.bakeLayer(CrockPotModel.LAYER_LOCATION));
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(
        CrockPotBlockEntity entity,
        float partialTick,
        PoseStack poseStack,
        MultiBufferSource bufferSource,
        int packedLight,
        int packedOverlay
    ) {
        poseStack.pushPose();
        poseStack.translate(0.5, 0.0, 0.5);

        float bobY = 0, bobX = 0, bobZ = 0;
        if (entity.isRattling()) {
            float time = (entity.getLevel().getGameTime() + partialTick);
            bobY = (float) Math.sin(time) * 0.02f;
            bobX = (float) Math.cos(time * 0.4f) * 0.01f;
            bobZ = (float) Math.cos(time * 0.4f + 3.5f) * 0.01f;
        }

        boolean open = entity.isLidOpen();

        VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityCutout(
            TEXTURE));
        model.renderToBuffer(
            poseStack,
            consumer,
            packedLight,
            packedOverlay,
            bobX,
            bobY,
            bobZ,
            open
        );

        if (open) {
            ItemStack item = entity.getOutput();
            poseStack.mulPose(Axis.XP.rotationDegrees(180));
            Quaternionf rot = Minecraft.getInstance().gameRenderer
                .getMainCamera()
                .rotation();
            poseStack.translate(0, -0.4, 0);
            poseStack.mulPose(new Quaternionf(0, rot.y, 0, rot.w).normalize());
            itemRenderer.renderStatic(
                item,
                ItemDisplayContext.GROUND,
                packedLight,
                packedOverlay,
                poseStack,
                bufferSource,
                entity.getLevel(),
                (int) entity.getBlockPos().asLong()
            );
        }

        poseStack.popPose();
    }
}
