package net.spigbop.hotc.client.renderer.model.block;// Made with Blockbench 5.1.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.spigbop.hotc.Constants;

public class CrockPotModel {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
        ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, "crock_pot"),
        "main"
    );
    private final ModelPart stand;
    private final ModelPart pot;
    private final ModelPart handle;

    public CrockPotModel(ModelPart root) {
        this.stand = root.getChild("stand");
        this.pot = root.getChild("pot");
        this.handle = root.getChild("handle");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition stand = partdefinition.addOrReplaceChild(
            "stand", CubeListBuilder
                .create()
                .texOffs(0, 67)
                .mirror()
                .addBox(
                    0.0F,
                    -3.0F,
                    -1.5F,
                    3.0F,
                    3.0F,
                    3.0F,
                    new CubeDeformation(0.0F)
                )
                .mirror(false)
                .texOffs(30, 69)
                .mirror()
                .addBox(
                    -2.0F,
                    -2.0F,
                    0.0F,
                    2.0F,
                    2.0F,
                    2.0F,
                    new CubeDeformation(0.0F)
                )
                .mirror(false)
                .texOffs(12, 69)
                .mirror()
                .addBox(
                    -0.5F,
                    -2.0F,
                    1.5F,
                    2.0F,
                    2.0F,
                    2.0F,
                    new CubeDeformation(0.0F)
                )
                .mirror(false)
                .texOffs(40, 63)
                .mirror()
                .addBox(
                    2.5F,
                    -2.0F,
                    1.5F,
                    2.0F,
                    2.0F,
                    2.0F,
                    new CubeDeformation(0.0F)
                )
                .mirror(false)
                .texOffs(39, 69)
                .mirror()
                .addBox(
                    2.5F,
                    -2.0F,
                    -3.0F,
                    2.0F,
                    2.0F,
                    2.0F,
                    new CubeDeformation(0.0F)
                )
                .mirror(false)
                .texOffs(12, 63)
                .mirror()
                .addBox(
                    2.5F,
                    -3.0F,
                    -1.5F,
                    2.0F,
                    3.0F,
                    2.0F,
                    new CubeDeformation(0.0F)
                )
                .mirror(false)
                .texOffs(21, 69)
                .mirror()
                .addBox(
                    -2.0F,
                    -2.0F,
                    1.5F,
                    2.0F,
                    2.0F,
                    2.0F,
                    new CubeDeformation(0.0F)
                )
                .mirror(false), PartPose.offset(-1.5F, 24.0F, 0.0F)
        );

        PartDefinition coal_r1 = stand.addOrReplaceChild(
            "coal_r1",
            CubeListBuilder
                .create()
                .texOffs(20, 63)
                .mirror()
                .addBox(
                    -2.0F,
                    -2.0F,
                    0.0F,
                    2.0F,
                    2.0F,
                    3.0F,
                    new CubeDeformation(0.0F)
                )
                .mirror(false),
            PartPose.offsetAndRotation(1.5F, 0.0F, -1.5F, 0.0F, -1.5708F, 0.0F)
        );

        PartDefinition twig_r1 = stand.addOrReplaceChild(
            "twig_r1",
            CubeListBuilder.create().texOffs(24, 48).mirror().addBox(
                -3.0F,
                -11.0F,
                0.0F,
                3.0F,
                11.0F,
                3.0F,
                new CubeDeformation(0.0F)
            ).mirror(false),
            PartPose.offsetAndRotation(3.0F, 0.15F, -6.0F, 0.0873F, 0.0F, 0.0F)
        );

        PartDefinition twig_r2 = stand.addOrReplaceChild(
            "twig_r2",
            CubeListBuilder.create().texOffs(12, 48).mirror().addBox(
                0.0F,
                -11.0F,
                0.0F,
                3.0F,
                11.0F,
                3.0F,
                new CubeDeformation(0.0F)
            ).mirror(false),
            PartPose.offsetAndRotation(
                5.4F,
                0.0F,
                1.5F,
                -0.0869F,
                -0.7816F,
                0.1231F
            )
        );

        PartDefinition twig_r3 = stand.addOrReplaceChild(
            "twig_r3",
            CubeListBuilder.create().texOffs(0, 48).mirror().addBox(
                -3.0F,
                -11.0F,
                0.0F,
                3.0F,
                11.0F,
                3.0F,
                new CubeDeformation(0.0F)
            ).mirror(false),
            PartPose.offsetAndRotation(
                -2.4F,
                0.0F,
                1.5F,
                -0.0869F,
                0.7816F,
                -0.1231F
            )
        );

        PartDefinition pot = partdefinition.addOrReplaceChild(
            "pot",
            CubeListBuilder.create().texOffs(32, 40).mirror().addBox(
                -8.0F,
                -13.9F,
                -3.0F,
                2.0F,
                2.0F,
                6.0F,
                new CubeDeformation(0.0F)
            ).mirror(false).texOffs(32, 32).addBox(
                5.5F,
                -13.9F,
                -3.0F,
                2.0F,
                2.0F,
                6.0F,
                new CubeDeformation(0.0F)
            ).texOffs(0, 0).mirror().addBox(
                -6.0F,
                -15.9F,
                -6.0F,
                12.0F,
                6.0F,
                12.0F,
                new CubeDeformation(0.0F)
            ).mirror(false),
            PartPose.offset(0.0F, 23.25F, 0.0F)
        );

        PartDefinition handle = partdefinition.addOrReplaceChild(
            "handle",
            CubeListBuilder.create().texOffs(0, 18).mirror().addBox(
                -3.0F,
                -18.65F,
                -4.5F,
                9.0F,
                2.0F,
                9.0F,
                new CubeDeformation(0.0F)
            ).mirror(false).texOffs(0, 36).addBox(
                -2.0F,
                -21.15F,
                -1.5F,
                7.0F,
                3.0F,
                3.0F,
                new CubeDeformation(0.0F)
            ),
            PartPose.offset(-1.5F, 24.0F, 0.0F)
        );

        return LayerDefinition.create(meshdefinition, 48, 96);
    }

    public void renderToBuffer(
        PoseStack poseStack,
        VertexConsumer vertexConsumer,
        int packedLight,
        int packedOverlay,
        float bobX,
        float bobY,
        float bobZ,
        boolean open
    ) {
        poseStack.mulPose(Axis.XP.rotationDegrees(180));
        poseStack.translate(0, -1.5, 0);
        stand.render(poseStack, vertexConsumer, packedLight, packedOverlay, -1);

        poseStack.pushPose();

        poseStack.translate(bobX, bobY, bobZ);
        pot.render(poseStack, vertexConsumer, packedLight, packedOverlay, -1);

        if (!open) {
            handle.render(
                poseStack,
                vertexConsumer,
                packedLight,
                packedOverlay,
                -1
            );
        }

        poseStack.popPose();
    }
}