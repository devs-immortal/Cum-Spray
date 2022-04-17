package net.immortaldevs.cumspray;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.Items;
import net.minecraft.util.registry.Registry;

import static net.immortaldevs.cumspray.CumSpray.id;

public final class CumSprayItems {
    public static final Item CUM_SPRAY = new Item(new Item.Settings().group(ItemGroup.TOOLS).recipeRemainder(Items.GLASS_BOTTLE));

    public static void init() {
        Registry.register(Registry.ITEM, id("cum_spray"), CUM_SPRAY);
    }
}
