package net.spigbop.hotc.client.renderer.block;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.spigbop.hotc.Constants;
import net.spigbop.hotc.block.entity.CrockPotBlockEntity;
import net.spigbop.hotc.client.renderer.model.block.CrockPotModel;

public class CrockPotBlockEntityRenderer
    implements BlockEntityRenderer<CrockPotBlockEntity>
{
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID,
        "textures/entity/crock_pot.png"
    );

    private final CrockPotModel model;

    public CrockPotBlockEntityRenderer(BlockEntityRendererProvider.Context context) {
        this.model = new CrockPotModel(context.bakeLayer(CrockPotModel.LAYER_LOCATION));
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

        float bobY = 0, wobble = 0;
        if (entity.isCooking()) {
            float time = (entity.getLevel().getGameTime() + partialTick) * 2.0f;
            bobY = (float) Math.sin(time) * 0.05f;
            Constants.LOG.info("{}", bobY);
            wobble = (float) Math.sin(time * 1.5f) * 2.0f;
        }

        VertexConsumer consumer = bufferSource.getBuffer(RenderType.entityCutout(TEXTURE));
        model.renderToBuffer(poseStack, consumer, packedLight, packedOverlay, bobY);

        poseStack.popPose();
    }
}
