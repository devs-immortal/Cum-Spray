package net.immortaldevs.cumspray;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.registry.Registry;

public final class CumSpraySoundEvents {
    public static final SoundEvent ENTITY_WANDERING_TRADER_EJACULATE = new SoundEvent(CumSpray.id("entity.wandering_trader.ejaculate"));

    public static void init() {
        Registry.register(Registry.SOUND_EVENT, ENTITY_WANDERING_TRADER_EJACULATE.getId(), ENTITY_WANDERING_TRADER_EJACULATE);
    }
}
