package net.dreamer.evilregeneration.mixin.server;

import net.dreamer.evilregeneration.EvilRegenerationAccessibleT;
import net.dreamer.evilregeneration.item.EvilRegenerationItemRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.recipe.Ingredient;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

@Mixin(BrewingRecipeRegistry.class)
public abstract class BrewingRecipeRegistryMixin {
    @Shadow private static void registerPotionType(Item item) {}
    @Shadow private static void registerItemRecipe(Item input,Item ingredient,Item output) {}
    @Shadow @Final private static List<BrewingRecipeRegistry.Recipe<Item>> ITEM_RECIPES;
    @Shadow @Final private static List<Ingredient> POTION_TYPES;

    @Inject(at = @At("HEAD"), method = "registerPotionType", cancellable = true)
    private static void registerPotionTypeInject(Item item, CallbackInfo info) {
        if(item == Items.TOTEM_OF_UNDYING) {
            POTION_TYPES.add(Ingredient.ofItems(item));

            info.cancel();
        }
    }

    @Inject(at = @At("HEAD"), method = "registerItemRecipe", cancellable = true)
    private static void registerItemRecipeInject(Item input, Item ingredient, Item output, CallbackInfo info) {
        if(input == Items.TOTEM_OF_UNDYING) {
            ITEM_RECIPES.add(new BrewingRecipeRegistry.Recipe<>(input, Ingredient.ofItems(ingredient), output));

            info.cancel();
        }
    }

    @Inject(at = @At("TAIL"), method = "registerDefaults")
    private static void registerDefaultsInject(CallbackInfo info) {
        registerPotionType(Items.TOTEM_OF_UNDYING);
        registerItemRecipe(Items.TOTEM_OF_UNDYING, Items.FERMENTED_SPIDER_EYE, Items.TOTEM_OF_UNDYING);
    }

    @Inject(slice = @Slice(from = @At(value = "INVOKE",target = "Ljava/util/List;size()I", ordinal = 0)), at = @At(value = "INVOKE", target = "Ljava/util/List;get(I)Ljava/lang/Object;", ordinal = 0), locals = LocalCapture.CAPTURE_FAILEXCEPTION, method = "craft", cancellable = true)
    private static void craftInject(ItemStack ingredient,ItemStack input,CallbackInfoReturnable<ItemStack> cir,Potion potion,Item item,int i,int j) {
        if(input.getItem() == Items.TOTEM_OF_UNDYING && ingredient.getItem() == Items.FERMENTED_SPIDER_EYE) {
            ItemStack stack = new ItemStack(EvilRegenerationItemRegistry.TOTEM_OF_DYING);
            if(input.getNbt() != null && !input.getNbt().isEmpty()) stack.getOrCreateNbt().copyFrom(input.getNbt());

            BrewingRecipeRegistry.Recipe<?> recipe = ITEM_RECIPES.get(i);
            if (((EvilRegenerationAccessibleT<?>) recipe).getInput() == item && ((EvilRegenerationAccessibleT<?>) recipe).getIngredient().test(ingredient))
                cir.setReturnValue(stack);
        }
    }
}
