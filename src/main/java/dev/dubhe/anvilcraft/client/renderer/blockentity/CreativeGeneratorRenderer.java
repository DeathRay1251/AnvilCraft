package dev.dubhe.anvilcraft.client.renderer.blockentity;

import dev.dubhe.anvilcraft.AnvilCraft;
import dev.dubhe.anvilcraft.block.entity.CreativeGeneratorBlockEntity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.resources.model.ModelResourceLocation;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import org.jetbrains.annotations.NotNull;

public class CreativeGeneratorRenderer implements BlockEntityRenderer<CreativeGeneratorBlockEntity> {
    public static final float ROTATION_MAGIC = 0.001220703125f;
    public static final ModelResourceLocation MODEL =
        ModelResourceLocation.standalone(AnvilCraft.of("block/creative_generator_cube"));

    /**
     * 创造发电机渲染
     */
    public CreativeGeneratorRenderer(BlockEntityRendererProvider.Context context) {
    }

    @Override
    public void render(
        @NotNull CreativeGeneratorBlockEntity blockEntity,
        float partialTick,
        @NotNull PoseStack poseStack,
        @NotNull MultiBufferSource buffer,
        int packedLight,
        int packedOverlay) {
        poseStack.pushPose();
        int power = blockEntity.getServerPower();
        float rotation = ((float) blockEntity.getTime() + partialTick) * power * ROTATION_MAGIC;
        final VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.solid());
        poseStack.translate(0.5F, 0.8f, 0.5F);
        poseStack.mulPose(Axis.YP.rotationDegrees(rotation));
        poseStack.mulPose(Axis.ZP.rotationDegrees(rotation));
        Minecraft.getInstance()
            .getBlockRenderer()
            .getModelRenderer()
            .renderModel(
                poseStack.last(),
                vertexConsumer,
                null,
                Minecraft.getInstance().getModelManager().getModel(MODEL),
                0,
                0,
                0,
                LightTexture.FULL_BLOCK,
                packedOverlay
            );
        poseStack.popPose();
    }
}
