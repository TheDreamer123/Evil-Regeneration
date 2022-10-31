package net.dreamer.evilregeneration.mixin.server;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.BrewingStandScreenHandler;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BrewingStandScreenHandler.PotionSlot.class)
public class PotionSlotMixin extends Slot {
    public PotionSlotMixin(Inventory inventory,int index,int x,int y) {
        super(inventory,index,x,y);
    }

    @Inject(at = @At("HEAD"), method = "matches", cancellable = true)
    private static void matchesInject(ItemStack stack,CallbackInfoReturnable<Boolean> cir) {
        if(stack.getItem() == Items.TOTEM_OF_UNDYING) cir.setReturnValue(true);
    }
}
