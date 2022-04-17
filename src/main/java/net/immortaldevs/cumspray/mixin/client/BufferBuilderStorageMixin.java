package net.immortaldevs.cumspray.mixin.client;

import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
import net.immortaldevs.cumspray.client.CumSprayRenderLayers;
import net.minecraft.client.render.BufferBuilder;
import net.minecraft.client.render.BufferBuilderStorage;
import net.minecraft.client.render.RenderLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BufferBuilderStorage.class)
public abstract class BufferBuilderStorageMixin {
    @Shadow
    private static void assignBufferBuilder(Object2ObjectLinkedOpenHashMap<RenderLayer, BufferBuilder> builderStorage, RenderLayer layer) {
    }

    @Inject(method = "method_22999",
            at = @At("TAIL"),
            remap = false)
    private void method_2999(Object2ObjectLinkedOpenHashMap<RenderLayer, BufferBuilder> map, CallbackInfo ci) {
        assignBufferBuilder(map, CumSprayRenderLayers.CUM_COATING);
    }
}
