package net.immortaldevs.cumspray.mixin;

import net.immortaldevs.cumspray.Util;
import net.immortaldevs.divineintervention.injection.ModifyOperand;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.MutableText;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@SuppressWarnings("unused")
@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @Shadow
    public abstract @Nullable NbtCompound getNbt();

    @ModifyOperand(method = "getTooltip",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/text/MutableText;formatted(Lnet/minecraft/util/Formatting;)Lnet/minecraft/text/MutableText;",
                    ordinal = 0))
    private MutableText tooltipColour(MutableText value) {
        return Util.rainbow(value, this.getNbt());
    }

    @ModifyOperand(method = "toHoverableText",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/text/MutableText;formatted(Lnet/minecraft/util/Formatting;)Lnet/minecraft/text/MutableText;",
                    ordinal = 1))
    private MutableText textColour(MutableText value) {
        return Util.rainbow(value, this.getNbt());
    }
}
