package net.immortaldevs.cumspray;

import net.minecraft.recipe.SpecialRecipeSerializer;
import net.minecraft.util.registry.Registry;

import static net.immortaldevs.cumspray.CumSpray.id;

public final class CumSprayRecipeSerialisers {
    public static final SpecialRecipeSerializer<CumRecipe> CUM = new SpecialRecipeSerializer<>(CumRecipe::new);

    public static void init() {
        Registry.register(Registry.RECIPE_SERIALIZER, id("crafting_special_cum"), CUM);
    }
}
