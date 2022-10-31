package net.dreamer.evilregeneration.particle;

import net.dreamer.evilregeneration.EvilRegeneration;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EvilRegenerationParticleTypeRegistry {
    public static final DefaultParticleType TOTEM_OF_DYING = FabricParticleTypes.simple(true);

    public static void register() {
        Registry.register(Registry.PARTICLE_TYPE, new Identifier(EvilRegeneration.MOD_ID, "totem_of_dying"), TOTEM_OF_DYING);
    }
}
