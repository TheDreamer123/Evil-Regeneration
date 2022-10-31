package net.dreamer.evilregeneration.mixin.server;

import net.dreamer.evilregeneration.EvilRegenerationAccessibleT;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BrewingRecipeRegistry.Recipe.class)
public abstract class RecipeMixin<T> implements EvilRegenerationAccessibleT<T> {
    @Shadow @Final T input;
    @Shadow @Final Ingredient ingredient;
    @Shadow @Final T output;

    public T getInput() {
        return input;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public T getOutput() {
        return output;
    }
}
