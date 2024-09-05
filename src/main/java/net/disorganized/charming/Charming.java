package net.disorganized.charming;

import net.disorganized.charming.registry.CharmComponents;
import net.disorganized.charming.registry.CharmRegistry;
import net.disorganized.charming.registry.CharmTags;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Charming implements ModInitializer {

	public static final String MOD_ID = "charming";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	public static Identifier of(String path) {
		return Identifier.of(MOD_ID, path);
	}

	@Override
	public void onInitialize() {
		CharmRegistry.register();

		CharmComponents.init();
		CharmTags.init();

		LOGGER.info("Initialized!");
	}

}