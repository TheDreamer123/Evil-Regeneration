package net.dreamer.evilregeneration;

import net.dreamer.evilregeneration.particle.EvilRegenerationParticleTypeRegistry;
import net.dreamer.evilregeneration.particle.TotemOfDyingParticle;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;

public class EvilRegenerationClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ParticleFactoryRegistry.getInstance().register(EvilRegenerationParticleTypeRegistry.TOTEM_OF_DYING, TotemOfDyingParticle.Factory::new);
    }
}
