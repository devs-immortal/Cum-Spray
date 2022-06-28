package net.immortaldevs.cumspray;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.immortaldevs.cumspray.classes.CumSpraySoundEvents;
import net.immortaldevs.cumspray.registries.CumSprayItems;
import net.immortaldevs.cumspray.registries.CumSprayRecipeSerialisers;
import net.immortaldevs.cumspray.classes.CUMmandHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import static net.immortaldevs.cumspray.registries.CUMponentInit.CUM_COATED;

public final class CumSpray implements ModInitializer {
    public static final String CUMSPRAY = "cum_spray";

    @Override
    public void onInitialize() {
        CumSprayItems.init();
        CumSprayRecipeSerialisers.init();
        CumSpraySoundEvents.init();
        CUMmandHandler.register();
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            ItemStack stackInHand = player.getStackInHand(hand);
            if (stackInHand.isOf(Items.SPONGE)) {
                if (entity.getComponent(CUM_COATED).cum_coated) {
                    entity.getComponent(CUM_COATED).cum_coated = false;
                    CUM_COATED.sync(entity);
                    world.playSound(null, entity.getBlockPos(), SoundEvents.BLOCK_WOOL_HIT, SoundCategory.PLAYERS, 1, 1);
                    return ActionResult.SUCCESS;
                }
            }
            if (stackInHand.isOf(Items.GLASS_BOTTLE)) {
                world.playSound(null, entity.getBlockPos(), CumSpraySoundEvents.ENTITY_WANDERING_TRADER_EJACULATE, SoundCategory.PLAYERS, 1, 1);
                if (!player.isCreative()) {
                    stackInHand.decrement(1);
                }
                player.incrementStat(Stats.CRAFTED.getOrCreateStat(CumSprayItems.CUM_BOTTLE));
                player.giveItemStack(new ItemStack(CumSprayItems.CUM_BOTTLE));
                return ActionResult.SUCCESS;
            }
            return ActionResult.FAIL;
        });
        UseItemCallback.EVENT.register((player, world, hand) -> {
            ItemStack stackInHand = player.getStackInHand(hand);
            if (player.isInSneakingPose() && player.getComponent(CUM_COATED).cum_coated) {
                if (stackInHand.isOf(Items.SPONGE)) {
                    player.getComponent(CUM_COATED).cum_coated = false;
                    CUM_COATED.sync(player);
                    return TypedActionResult.success(new ItemStack(Items.SPONGE));
                }
            }
            return TypedActionResult.pass(ItemStack.EMPTY);
        });
    }

    public static Identifier id(String path) {
        return new Identifier(CUMSPRAY, path);
    }
}
