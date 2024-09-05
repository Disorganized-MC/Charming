package net.disorganized.charming;

import net.disorganized.charming.content.ItemNotchesComponent;
import net.disorganized.charming.registry.CharmComponents;
import net.disorganized.charming.registry.CharmRegistry;
import net.disorganized.charming.registry.CharmTags;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.item.Items;
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

		DefaultItemComponentEvents.MODIFY.register(Charming::modifyItems);

		LOGGER.info("Initialized!");
	}


	public static void modifyItems(DefaultItemComponentEvents.ModifyContext context) {
		context.modify(Items.DIAMOND_CHESTPLATE, builder -> builder.add(CharmComponents.ITEM_NOTCHES, new ItemNotchesComponent(1)));
	}

}