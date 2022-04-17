package net.immortaldevs.cumspray.mixin.client;

import net.immortaldevs.cumspray.Util;
import net.immortaldevs.divineintervention.injection.ModifyOperand;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.item.ItemStack;
import net.minecraft.text.MutableText;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@SuppressWarnings("unused")
@Mixin(InGameHud.class)
public abstract class InGameHudMixin {
    @Shadow
    private ItemStack currentStack;

    @ModifyOperand(method = "renderHeldItemTooltip",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/text/MutableText;formatted(Lnet/minecraft/util/Formatting;)Lnet/minecraft/text/MutableText;",
                    ordinal = 0))
    private MutableText colourTooltip(MutableText value) {
        return Util.rainbow(value, this.currentStack.getNbt());
    }
}
