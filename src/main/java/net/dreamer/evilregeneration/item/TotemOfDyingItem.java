package net.dreamer.evilregeneration.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class TotemOfDyingItem extends Item {
    public TotemOfDyingItem(Settings settings) {
        super(settings);
    }

    @Override
    public Text getName(ItemStack stack) {
        Text base = Text.translatable(this.getTranslationKey(stack));
        Style style = base.getStyle().withFormatting(Formatting.RED);
        return Text.translatable(this.getTranslationKey(stack)).setStyle(style);
    }
}
