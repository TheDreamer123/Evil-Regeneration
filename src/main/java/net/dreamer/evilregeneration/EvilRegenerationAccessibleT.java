package net.dreamer.evilregeneration;

import net.minecraft.recipe.Ingredient;

public interface EvilRegenerationAccessibleT<T> {
    T getInput();
    Ingredient getIngredient();
    T getOutput();
}
