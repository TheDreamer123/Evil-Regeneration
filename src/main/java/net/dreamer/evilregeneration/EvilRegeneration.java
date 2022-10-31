package net.dreamer.evilregeneration;

import net.dreamer.evilregeneration.item.EvilRegenerationItemRegistry;
import net.dreamer.evilregeneration.particle.EvilRegenerationParticleTypeRegistry;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EvilRegeneration implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("Evil Regeneration");
	public static String MOD_ID = "evilregeneration";

	@Override
	public void onInitialize() {
		LOGGER.info("If you think about it... The totems revives death to kill you.");



		EvilRegenerationItemRegistry.register();
		EvilRegenerationParticleTypeRegistry.register();
	}
}
