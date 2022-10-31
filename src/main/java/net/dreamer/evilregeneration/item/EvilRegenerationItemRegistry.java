package net.dreamer.evilregeneration.item;

import net.dreamer.evilregeneration.EvilRegeneration;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EvilRegenerationItemRegistry {
    public static final Item TOTEM_OF_DYING = new TotemOfDyingItem(new Item.Settings().group(ItemGroup.COMBAT).maxCount(1));

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(EvilRegeneration.MOD_ID,"totem_of_dying"),TOTEM_OF_DYING);
    }
}
