package io.github.boogiemonster1o1.essentials;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LegacyFabricEssentials implements ModInitializer {

	public static final Logger LOGGER = LogManager.getLogger(LegacyFabricEssentials.class);

	@Override
	public void onInitialize() {
		LOGGER.info("[LegacyFabricEssentials] Initializing!");
	}
}
