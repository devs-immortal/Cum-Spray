package net.immortaldevs.cumspray.mixin;

import net.immortaldevs.cumspray.CumSprayItems;
import net.immortaldevs.cumspray.CumSpraySoundEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.WanderingTraderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WanderingTraderEntity.class)
public abstract class WanderingTraderEntityMixin extends LivingEntity {
    private WanderingTraderEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "interactMob",
            at = @At("HEAD"),
            cancellable = true)
    private void interactMob(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        ItemStack stack = player.getStackInHand(hand);
        if (stack.isOf(Items.GLASS_BOTTLE)) {
            this.playSound(CumSpraySoundEvents.ENTITY_WANDERING_TRADER_EJACULATE,
                    this.getSoundVolume(),
                    this.getSoundPitch());
            ItemStack result = ItemUsage.exchangeStack(stack,
                    player,
                    CumSprayItems.CUM_SPRAY.getDefaultStack());
            player.setStackInHand(hand, result);
            cir.setReturnValue(ActionResult.success(this.world.isClient));
        }
    }
}
