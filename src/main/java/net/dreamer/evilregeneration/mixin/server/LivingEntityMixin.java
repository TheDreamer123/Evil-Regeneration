package net.dreamer.evilregeneration.mixin.server;

import net.dreamer.evilregeneration.EvilRegeneration;
import net.dreamer.evilregeneration.item.EvilRegenerationItemRegistry;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow public abstract ItemStack getStackInHand(Hand hand);
    @Shadow public abstract boolean damage(DamageSource source,float amount);

    private static final DamageSource REGENERATION = new DamageSource(EvilRegeneration.MOD_ID + ":regeneration") {{
        this.setUnblockable();
        this.setBypassesArmor();
        this.setBypassesProtection();
    }};

    public LivingEntityMixin(EntityType<?> type,World world) {
        super(type,world);
    }

    @Inject(at = @At("TAIL"), method = "heal")
    public void healInject(CallbackInfo info) {
        LivingEntity living = (LivingEntity) (Entity) this;
        if(living instanceof PlayerEntity player && !player.getAbilities().creativeMode || !(living instanceof PlayerEntity)) {
            ItemStack stack = null;
            Hand[] var4 = Hand.values();
            for (Hand hand : var4) {
                ItemStack stack2 = this.getStackInHand(hand);
                if (stack2.isOf(EvilRegenerationItemRegistry.TOTEM_OF_DYING)) {
                    stack = stack2.copy();
                    stack2.decrement(1);
                    break;
                }
            }

            if(stack != null) {
                if (living instanceof ServerPlayerEntity serverPlayer) {
                    serverPlayer.incrementStat(Stats.USED.getOrCreateStat(EvilRegenerationItemRegistry.TOTEM_OF_DYING));
                    Criteria.USED_TOTEM.trigger(serverPlayer, stack);
                }

                damage(REGENERATION,Float.MAX_VALUE);
                this.world.sendEntityStatus(this, (byte)309849080);
            }
        }
    }
}
