package net.immortaldevs.cumspray.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.render.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Matrix4f;

public final class CumSprayRenderLayers extends RenderLayer {
    public static final RenderLayer CUM_COATING = RenderLayer.of("cum_coating",
            VertexFormats.POSITION_TEXTURE,
            VertexFormat.DrawMode.QUADS,
            256,
            MultiPhaseParameters.builder()
                    .shader(ENTITY_GLINT_SHADER)
                    .texture(new Texture(
                            new Identifier("cum_spray", "textures/misc/cum_coating.png"),
                            false,
                            false))
                    .writeMaskState(COLOR_MASK)
                    .cull(DISABLE_CULLING)
                    .depthTest(EQUAL_DEPTH_TEST)
                    .transparency(GLINT_TRANSPARENCY)
                    .target(ITEM_TARGET)
                    .texturing(new Texturing("cum_coating",
                            () -> RenderSystem.setTextureMatrix(Matrix4f.scale(8f, 8f, 8f)),
                            RenderSystem::resetTextureMatrix))
                    .build(false));

    public static final RenderLayer ENTITY_CUM_COATING = RenderLayer.of("entity_cum_coating",
            VertexFormats.POSITION_TEXTURE,
            VertexFormat.DrawMode.QUADS,
            256,
            MultiPhaseParameters.builder()
                    .shader(ENTITY_GLINT_SHADER)
                    .texture(new Texture(
                            new Identifier("cum_spray", "textures/misc/cum_coating.png"),
                            false,
                            false))
                    .writeMaskState(COLOR_MASK)
                    .cull(DISABLE_CULLING)
                    .depthTest(EQUAL_DEPTH_TEST)
                    .transparency(GLINT_TRANSPARENCY)
                    .target(ITEM_TARGET)
                    .texturing(new Texturing("cum_coating",
                            () -> RenderSystem.setTextureMatrix(Matrix4f.scale(1f, 1f, 1f)),
                            RenderSystem::resetTextureMatrix))
                    .build(false));

    private CumSprayRenderLayers(String name, VertexFormat vertexFormat, VertexFormat.DrawMode drawMode, int expectedBufferSize, boolean hasCrumbling, boolean translucent, Runnable startAction, Runnable endAction) {
        super(name, vertexFormat, drawMode, expectedBufferSize, hasCrumbling, translucent, startAction, endAction);
    }
}
