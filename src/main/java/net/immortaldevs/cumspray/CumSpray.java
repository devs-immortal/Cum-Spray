package net.immortaldevs.cumspray;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;

public final class CumSpray implements ModInitializer {
	public static final String CUMSPRAY = "cumspray";

	@Override
	public void onInitialize() {
		CumSprayItems.init();
		CumSprayRecipeSerialisers.init();
		CumSpraySoundEvents.init();
	}

	public static Identifier id(String path) {
		return new Identifier(CUMSPRAY, path);
	}
}
