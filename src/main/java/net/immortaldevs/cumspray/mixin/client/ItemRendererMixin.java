package net.immortaldevs.cumspray.mixin.client;

import net.immortaldevs.cumspray.client.CumSprayRenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.VertexConsumers;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @ModifyVariable(method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformation$Mode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/render/item/ItemRenderer;renderBakedItemModel(Lnet/minecraft/client/render/model/BakedModel;Lnet/minecraft/item/ItemStack;IILnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumer;)V",
                    shift = At.Shift.BEFORE))
    private VertexConsumer modifyVertexConsumer(VertexConsumer value, ItemStack stack, ModelTransformation.Mode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers) {
        NbtCompound nbt = stack.getNbt();
        if (nbt == null || !nbt.getBoolean("cum_coated")) return value;
        return VertexConsumers.union(vertexConsumers.getBuffer(CumSprayRenderLayers.CUM_COATING), value);
    }
}
